<%-- 
    Document   : header
    Created on : Dec 3, 2019, 12:14:07 PM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header Page</title>
        <style>
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
                color: wheat;
            }

            li {
                float: left;
            }

            li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <ul>
            <li><s:a href="LoadAreaAction">Home</s:a></li>
                <s:if test="%{#session.ROLE != null}">
                    <s:if test="%{#session.ROLE != 'admin' }">
                    <li><a href="view_cart.jsp">Card</a></li>
                    <li><a href="SearchOrderByDateAction">Order</a></li>
                    <li><a href="LogOutAction">Logout</a></li>
                    </s:if> 
                <li style="float: right; margin-top: 15px;"><s:property value="%{#session.USER}"/></li>
                </s:if>
                <s:else>
                <li><a href="register_user.jsp">Register</a></li> 
                <li><a href="login.jsp">Login</a></li> 
                </s:else>
        </ul>
    </body>
</html>
