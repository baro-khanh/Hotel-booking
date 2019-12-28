<%-- 
    Document   : write_feedback
    Created on : Dec 9, 2019, 10:54:06 PM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            <form action="InsertFeedBackAction" method="POST" class="form-control">
                Hotel: <s:property value="%{hotelName}"/><br>
                Email: <s:property value="%{email}"/><br>
                <input type="hidden" value="<s:property value="%{hotelID}"/>" name="hotelID"/>
                <input type="hidden" value="<s:property value="%{email}"/>" name="email"/>
                Feedback:<br> <textarea name="feedback" class="textfield-control"></textarea><br>
                <input type="submit" value="Send"/>
            </form>
        </div>
    </body>
</html>
