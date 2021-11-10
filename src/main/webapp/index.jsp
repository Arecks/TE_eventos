<%@page import="java.util.List"%>
<%@page import="com.emergentes.modelo.Seminario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Seminario> lista = (List<Seminario>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <link href="Estilos.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <center>
        <div class="div">
            <p>SEGUNDO PARCIAL TEM-742</p>
            <p>Nombre: Alex Efrain Alejo Quispe</p>
            <p>Carnet: 13278424 lp</p>
        </div>
    </center>
    <br>

    <h1 class="titulo">Registro de Seminarios</h1>
    <br>
    <br>
    <br>
    <p>
        <a href="MainController?op=nuevo" class="btn-bootstrap2">Nuevo Seminario</a>
    </p>
    <table  class="tabla">
        <tr>
            <th>Id</th>
            <th>Titulo</th>
            <th>Expositor</th>
            <th>Fecha</th>
            <th>Hora</th>
            <th>Cupos</th>
            <th></th>
            <th></th>
        </tr>

        <c:forEach var="item" items="${lista}">
            <tr>
                <td class="rojo">${item.id}</td>
                <td class="blanco">${item.titulo}</td>
                <td class="blanco">${item.expositor}</td>
                <td class="blanco">${item.fecha}</td>
                <td class="blanco">${item.hora}</td>
                <td class="blanco">${item.cupo}</td>
                <td><a href="MainController?op=editar&id=${item.id}" class="btn-bootstrap">Editar</a></td>
                <td><a href="MainController?op=eliminar&id=${item.id}" class="btn-bootstrap1" onclick="return(confirm('Esta seguro de eliminar?'))">Eliminar</a></td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
