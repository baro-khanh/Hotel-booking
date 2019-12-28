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
        <title>View order Page</title>
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
        <div style="width: 30%; float: left; margin-top: 10px;">
            <form action="SearchOrderByDateAction" method="POST">
                <input type="date" required="" name="searchDate" value="<s:property value="%{searchDate}"/> " class="textfield-control"/>
                <input type="submit" value="Search"/>
            </form>
        </div>
        <div style="width: 65%; float: right; margin-top: 10px;">
            <s:if test="%{listOrder != null}">
                <s:if test="%{listOrder.size() > 0}">
                    <table border="0" style="width: 100%; text-align: center">
                        <thead>
                            <tr>
                                <th>OrderID</th>
                                <th>Discount</th>
                                <th>Total</th>
                                <th>Order date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="%{listOrder}">
                                <tr>
                                    <td>
                                        <a href="SearchDetailOrderAction?action=<s:property value="%{orderID}"/>"># <s:property value="%{orderID}"/></a>
                                    </td>
                                    <td> <s:property value="%{discountCode}"/></td>
                                    <td> <s:property value="%{total}"/></td>
                                    <td> <s:property value="%{createDate}"/></td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>

                </s:if>
            </s:if>
        </div>
    </body>
</html>
