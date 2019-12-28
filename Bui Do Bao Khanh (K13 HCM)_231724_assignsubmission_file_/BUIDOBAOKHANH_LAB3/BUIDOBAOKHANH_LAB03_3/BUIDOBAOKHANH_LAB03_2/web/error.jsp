<%-- 
    Document   : error.jsp
    Created on : Dec 7, 2019, 12:14:22 PM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
        <style>
             body{
                font-family: 'Pacifico', cursive;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1>Error page</h1>
        <span style="color: red;"><s:property value="%{#request.ERROR}"/></span>
    </body>
</html>
