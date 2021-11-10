package com.emergentes.controlador;

import com.emergentes.modelo.Seminario;
import com.emergentes.utiles.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        PreparedStatement ps;
        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        ResultSet rs;
        String op;
        ArrayList<Seminario> lista = new ArrayList<Seminario>();
        int id;

        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";

        if (op.equals("list")) {
            try {

                //Operaciones para listar datos
                String sql = "select * from seminarios";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                //while
                while (rs.next()) {
                    Seminario semi = new Seminario();
                    semi.setId(rs.getInt("id"));
                    semi.setTitulo(rs.getString("titulo"));
                    semi.setExpositor(rs.getString("expositor"));
                    semi.setFecha(rs.getDate("fecha"));
                    semi.setHora(rs.getString("hora"));
                    semi.setCupo(rs.getInt("cupo"));

                    lista.add(semi);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (op.equals("nuevo")) {
            //Operaciones para desplegar el formulario 
            Seminario se = new Seminario();

            request.setAttribute("semi", se);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }

        if (op.equals("editar")) {
            id = Integer.parseInt(request.getParameter("id"));
            try {
                Seminario se1 = new Seminario();

                ps = conn.prepareStatement("select * from seminarios where id = ?");
                ps.setInt(1, id);
                rs = ps.executeQuery();

                if (rs.next()) {
                    se1.setId(rs.getInt("id"));
                    se1.setTitulo(rs.getString("titulo"));
                    se1.setExpositor(rs.getString("expositor"));
                    se1.setFecha(rs.getDate("fecha"));
                    se1.setHora(rs.getString("hora"));
                    se1.setCupo(rs.getInt("cupo"));
                }
                request.setAttribute("semi", se1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (op.equals("eliminar")) {
            //Operaciones para elimnar un registro
            id = Integer.parseInt(request.getParameter("id"));

            try {
                ps = conn.prepareStatement("delete from seminarios where id = ?");
                ps.setInt(1, id);

                ps.executeUpdate();
                response.sendRedirect("MainController");

            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String expositor = request.getParameter("expositor");
        String fecha = request.getParameter("fecha");
        String hora = request.getParameter("hora");
        int cupo = Integer.parseInt(request.getParameter("cupo"));

        Seminario sem = new Seminario();

        sem.setId(id);
        sem.setTitulo(titulo);
        sem.setExpositor(expositor);
        sem.setFecha(convierteFecha(fecha));
        sem.setHora(hora);
        sem.setCupo(cupo);

        ConexionDB canal = new ConexionDB();
        Connection conn = canal.conectar();
        PreparedStatement ps;
        ResultSet rs;

        if (id == 0) {
            //insertar registro
            String sql = "insert into seminarios (titulo,expositor,fecha,hora,cupo) values (?,?,?,?,?)";
            try {
                ps = conn.prepareStatement(sql);

                ps.setString(1, sem.getTitulo());
                ps.setString(2, sem.getExpositor());
                ps.setDate(3, sem.getFecha());
                ps.setString(4, sem.getHora());
                ps.setInt(5, sem.getCupo());

                ps.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            //update registro
            String sql1 = "update seminarios set titulo=?, expositor=? , fecha=?, hora=? ,cupo=? where id=?";
            try {
                ps = conn.prepareStatement(sql1);
                ps.setString(1, sem.getTitulo());
                ps.setString(2, sem.getExpositor());
                ps.setDate(3, sem.getFecha());
                ps.setString(4, sem.getHora());
                ps.setInt(5, sem.getCupo());
                ps.setInt(6, sem.getId());
                ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        response.sendRedirect("MainController");
    }

    public Date convierteFecha(String fecha) {
        Date fechaBD = null;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaTMP;
        try {
            fechaTMP = formato.parse(fecha);
            fechaBD = new Date(fechaTMP.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fechaBD;
    }

}
