<%-- 
    Document   : view_order
    Created on : Dec 9, 2019, 9:49:24 PM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View detail order Page</title>
        <style>
            body{
                font-family: 'Pacifico', cursive;
            }
            .textfield-control{
                width: 80%;
                margin: 0px;
                font-family: 'Pacifico', cursive;
                background-color: #333;
                color: wheat;
                /*                    text-align: center;*/
                margin-right: 50px;
                /*float: right;*/
                padding: 5px;
                border-radius: 20px;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div style="width: 100%; float: right; margin-top: 10px;">
            <s:if test="%{listDetail != null}">
                <s:if test="%{listDetail.size() > 0}">
                    <table border="0" style="width: 100%; text-align: center">
                        <thead>
                            <tr>
                                <th>HotelName</th>
                                <th>RoomID</th>
                                <th>Quantity</th>
                                <th>Checkin</th>
                                <th>Checkout</th>
                                <th>SubPrice</th>
                                <td></td>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="%{listDetail}">
                                <tr>
                                    <td><s:property value="%{hotelName}"/></td>
                                    <td><s:property value="%{roomID}"/></td>
                                    <td><s:property value="%{quantity}"/></td>
                                    <td><s:property value="%{checkIn}"/></td>
                                    <td><s:property value="%{checkOut}"/></td>
                                    <td><s:property value="%{subPrice}"/></td>
                                    <td>
                                        <form action="CreateFeedBackAction" method="POST">
                                            <input type="hidden" value="<s:property value="%{hotelID}"/>" name="hotelID"/>
                                            <input type="hidden" value="<s:property value="%{hotelName}"/>" name="hotelName"/>
                                            <input type="submit" value="Feed back"/>
                                        </form>
                                    </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>

                </s:if>
            </s:if>
        </div>
    </body>
</html>
