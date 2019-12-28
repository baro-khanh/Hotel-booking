<%-- 
    Document   : login
    Created on : Dec 3, 2019, 5:08:00 PM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
            body{
                margin: 0px;
                font-family: 'Pacifico', cursive;
                background-image: url(img/70396457_2941178645899419_19.jpg);
                background-position: center;
                background-size: cover;
                background-repeat: no-repeat;
                background-attachment: fixed;
                overflow: hidden;
            }
            .left-control{
                width: 45%;
                height: 580px;
                float: left;
                margin-top: 5px;
            }
            .right-control{
                margin-top: 5px;
                width: 53%;
                height: 350px;
                float: right;
                background:rgba(0,0,0,0.5);
                text-align: center;
                border-radius: 50px;
                padding-top: 80px;
            }
            .form-control{
                width: 100%;
                background-color: pink;
                color: beige;
            }
            .textfield-control{
                width: 100%;
                margin: 10px;
                font-family: 'Pacifico', cursive;
                background-color: #0B4C5F;
                color: wheat;
                border-radius: 20px;
                padding: 5px;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="left-control">
        </div>
        <div class="right-control">
            <h1 style="color: beige;">Login form</h1>
            <div style=" text-align: center; margin: 0px;">
                <s:form action="LoginAction" method="POST" class="form-control" >
                    <s:textfield type="text" name="emailID" label="Email" cssClass="textfield-control" placeholder="..." value="" />
                    <s:password type="password" name="password" label="Password" cssClass="textfield-control" placeholder="..." value=""/><br>
                    <s:submit type="submit" value="Login"/>
                </s:form>
            </div>
            <font color = "red">
            <s:property value="%{#request.INVALID}"/>
            </font>
        </div>
    </body>
</html>
