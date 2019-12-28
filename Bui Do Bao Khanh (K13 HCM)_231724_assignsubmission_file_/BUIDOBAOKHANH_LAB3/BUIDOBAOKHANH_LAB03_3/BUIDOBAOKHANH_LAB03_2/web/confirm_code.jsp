<%-- 
    Document   : confirm_code
    Created on : Dec 10, 2019, 6:40:24 PM
    Author     : buido
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm code Page</title>
        <style>
            body{
                font-family: 'Pacifico', cursive;
            }
            .form-control{
                width: 60%;
                /*background-color: aqua;*/
                padding-left: 30px;
                margin-left: 250px;
                border: 1px dashed black;
                border-radius: 30px;
                padding-bottom: 10px;
                padding-top: 10px;
            }
            .textfield-control{
                width:90%;
                height: 100px;
                font-family: 'Pacifico', cursive;
                background-color: #333;
                color: wheat;
                /*text-align: center;*/
                /*margin-right: 50px;*/
                /*margin-top: 5px;*/
                /*float: right;*/
                padding: 10px;
                border-radius: 20px;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div style="margin-top: 10px; width: 100%;">
            <form action="CheckConfirmCodeAction" method="POST" class="form-control">
                <span style="color: orange;">A confirmation code for the order has been sent to your email. Check and enter in the box below. Thank you</span><br>
                Confirm:<br><input type="text" class="textfield-control" required="" name="confirmcode"/><br>
                <input type="submit" value="Send"/>
            </form>
        </div>
    </body>
</html>
