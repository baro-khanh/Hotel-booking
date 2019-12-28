<%-- 
    Document   : view_cart
    Created on : Dec 8, 2019, 12:11:44 AM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View cart Page</title>
        <style>
            body{
                font-family: 'Pacifico', cursive;
            }
            .cart{
                width: 74%;
                height: auto;
                /*background-color: pink;*/
                float: left;
                text-align: center;
                margin-top: 10px;
            }
            .info{
                width: 23%;
                height: auto;
                /*background-color: yellow;*/
                float: right;
                margin-top: 10px;
                border-left: 1px solid black;
                padding-left: 10px;
            }
            .form-control{
                width: 100%;
            }
            .textfield-control{
                width: 50%;
                font-family: 'Pacifico', cursive;
                background-color: #333;
                color: wheat;
                text-align: center;
                /*margin-right: 50px;*/
                margin-top: 5px;
                /*float: left;*/
            }
            .button-control{
                width: 100%;
                background-color: tomato;
                color: beige;
            }
        </style>
        <script>
            function myFunction(id) {
                var x = confirm("Do you want to delete this room in your cart ? ");
                if (x == true) {
                    var location = "DeleteCartAction?roomID=" + id;
                    window.location = location;
                }
            }
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="cart">
            <s:if test="%{#session.CART != null}">
                <table style="width: 100%;">
                    <thead>
                        <tr>
                            <th>Room name</th>
                            <th>Quantity room</th>
                            <th>CheckIn</th>
                            <th>CheckOut</th>
                            <th>Nights</th>
                            <th>Subprice</th>
                            <th>Update</th>
                            <th>Remove</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="%{#session.CART.getCart().values()}">
                            <tr>
                        <form method="POST" action="UpdateCartAction">
                            <td><s:property value="%{roomName}"/></td>
                            <td><input name="quantity" type="number" value="<s:property value="%{amountRoom}"/>" min="1" step="1" required="" class="textfield-control"/></td>
                            <td><s:property value="%{checkin}"/></td>
                            <td><s:property value="%{checkout}"/></td>
                            <td><s:property value="%{amountNight}"/></td>
                            <td><s:property value="%{price * amountRoom * amountNight}"/></td>
                            <td>
                                <s:hidden value="%{roomID}" name="roomID"/>
                                <input type="submit" value="Update" name="action"/>
                            </td>
                        </form>
                        <td>
                            <input type="submit" value="Remove" onclick="myFunction('<s:property value="%{roomID}"/>')"/>
                        </td>    
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </s:if>
            <s:else>
                Your cart is empty<br>
                <s:a href="search_page.jsp">Continuous shopping</s:a>
            </s:else>
            <span style="color: red;"><s:property value="%{#request.INVALID}"/></span>
        </div>
        <div class="info">
            <s:if test="%{#session.CART != null}">
                <s:if test="%{#session.CART.getCart().size() != 0}">
                    Total: <s:property value="%{#session.CART.getTotal()}"/> đ
                    <form action="ProcessBookingAction" method="POST" class="form-control">
                        <input type="submit" value="Proceed Booking" class="button-control"/>
                    </form>
                    <hr>
                    <form action="CheckSaleAction" method="POST" class="form-control">
                        <input type="text" name="saleCode" label="Sale code" value="<s:property value="%{#session.DISCOUNT.discountCode}"/>" />
                        <input type="submit"value="Agree" class="button-control"/>
                        <span style="color: orange"><s:property value="%{mess}"/></span>
                    </form>
                </s:if>
            </s:if>
        </div>
    </body>
</html>
