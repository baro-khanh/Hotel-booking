<%-- 
    Document   : register_user
    Created on : Dec 3, 2019, 12:56:05 PM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register user Page</title>
        <style>
            body{
                font-family: 'Pacifico', cursive;
                margin: 0px;
                background-image: url(img/70747165_2941187059231911_71218.jpg);
                background-position: center;
                background-size: cover;
                background-repeat: no-repeat;
                background-attachment: fixed;
            }
            .left-control{
                width: 45%;
                height: 580px;
                float: left;
                margin-top: 5px;
            }
            .img{
                width: 550px;
                height: 580px;
                border-radius: 50px;
            }
            .right-control{
                margin-top: 5px;
                width: 53%;
                height: 500px;
                float: right;
                background:rgba(0,0,0,0.5);
                text-align: center;
                border-radius: 50px;
                padding-top: 50px;
            }
            .form-control{
                width: 100%;
                /*background-color: pink;*/
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
            <!--<img src="img/61231469_2335750753152544_1854791457957740544_n.jpg" class="img"/>-->
        </div>
        <div class="right-control">
            <h1 style="color: beige;">Register form</h1>
            <s:form action="RegisterUserAction" method="POST" cssClass="form-control" >
                <table style="width: 100%; margin-left: -30px;">
                    <tr>
                        <td><s:textfield type="text" name="emailID" label="Email" cssClass="textfield-control" placeholder="..." /></td>
                    </tr>
                    <tr>
                        <td><s:textfield type="text" name="fullname" label="Fullname" cssClass="textfield-control" placeholder="..."/></td>
                    </tr>
                    <tr>
                        <td><s:textfield type="text" name="phoneNo" label="Phone" cssClass="textfield-control" placeholder="..."/></td>
                    </tr>
                    <tr>
                        <td><s:password type="password" name="password" label="Password" cssClass="textfield-control" placeholder="..."/></td>
                    </tr>
                    <tr>
                        <td><s:password type="password" name="confirm" label="Confirm Password" cssClass="textfield-control" placeholder="..."/></td>
                    </tr>
                    <tr>
                        <td> <s:submit type="submit" value="Register"/></td>
                    </tr>
                </table>
            </s:form>
            <font color = "red">
            <s:if test="%{exception.message.indexOf('duplicate') > -1}">
                <s:property value="%{emailID}"/> is exsisted
            </s:if>
            </font>
        </div>
    </body>
</html>
