<%@page import="com.emergentes.modelo.Seminario"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Seminario semi = (Seminario)request.getAttribute("semi");
%>
<!DOCTYPE html>
<html>
    <head>
          <link href="Estilos.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1  class="titulo"><c:if test="${semi.id == 0}">
                Nuevo Seminario
            </c:if>
                <c:if test="${semi.id != 0}">
                Editar Seminario
            </c:if>
            </h1>
        <div class="div1">
        <form action="MainController" method="post">
            <input type="hidden" name="id" value="${semi.id}">
            <table>
                

                
                <tr>
                    <td>Titulo: </td>
                    <td><input type="text" name="titulo" style="width:300px" value="${semi.titulo} "/></td>
                </tr>
                
                <tr>
                    <td>Expositor: </td>
                    <td><input type="text" name="expositor" style="width:300px" value="${semi.expositor}"/></td>
                </tr>
                 <tr>
                    <td>Fecha: </td>
                    <td><input type="date" name="fecha" value="${semi.fecha}"/></td>
                </tr>
                 <tr>
                    <td>Hora: </td>
                    <td><input type="text" name="hora" value="${semi.hora}"/></td>
                </tr>
                 <tr>
                    <td>Cupos:</td>
                    <td><input type="number" name="cupo" value="${semi.cupo}"/></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td><input type="submit"  value="Enviar"></td>
                </tr>
            </table>
        </form>
                </div>
    </body>
</html>
