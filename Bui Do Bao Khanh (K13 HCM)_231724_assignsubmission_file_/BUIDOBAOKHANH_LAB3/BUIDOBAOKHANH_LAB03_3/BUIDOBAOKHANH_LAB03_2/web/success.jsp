<%-- 
    Document   : success
    Created on : Dec 2, 2019, 12:29:48 PM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Feedback success Page</title>
        <style>
            body{
                margin: 0px;
                font-family: 'Pacifico', cursive;
                background-color: beige;
                background-image: url(img/ivana-cajina-LlDQPnErFxo-unsplash.jpg);
                background-position: center;
                background-size: cover;
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-color: red;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <h1 style="color: beige;"><s:property value="%{#request.SUCCESS}"/></h1>
    </body>
</html>
