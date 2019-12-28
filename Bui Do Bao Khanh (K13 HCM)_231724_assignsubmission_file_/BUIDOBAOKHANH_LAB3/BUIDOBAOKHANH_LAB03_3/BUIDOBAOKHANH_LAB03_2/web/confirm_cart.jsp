<%-- 
    Document   : confirm_cart
    Created on : Dec 8, 2019, 1:53:22 PM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm cart Page</title>
        <style>
             body{
                font-family: 'Pacifico', cursive;
            }
            .order{
                margin-top: 10px;
                width: 60%;
                height: auto;
                /*background-color: beige;*/
                border: 1px dashed black;
                float: left;
                border-radius: 30px;
                padding-left: 10px;
            }
            .user{
                margin-top: 10px;
                width: 35%;
                height: auto;
                /*background-color: pink;*/
                float: right;
                border: 1px dashed black;
                border-radius: 30px;
                padding-bottom: 30px;
                padding-left: 5px;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="order">
            <s:iterator value="%{#session.CART.getCart().values()}">
                <div>
                    <h2><b><s:property value="%{hotelName}"/></b></h2>
                    <h3><s:property value="%{amountRoom}"/> x <s:property value="%{roomName}"/></h3>
                    Checkin: <s:property value="%{checkin}"/><br>
                    Checkout: <s:property value="%{checkout}"/><br>
                    Nights: <s:property value="%{amountNight}"/><br>
                    <span style="color: orange;">Subprice: <s:property value="%{price * amountNight * amountRoom}"/></span>
                    <hr>
                </div>
            </s:iterator>
            Provisional: <s:property value="%{#session.CART.getTotal()}"/> đ <br>
            Discount:
            <s:if test="%{#session.DISCOUNT != null}">
                <s:property value="%{#session.DISCOUNT.decrease}"/> %
            </s:if>
            <br>
            <span style="color: tomato">
                Total: 
                <s:if test="%{#session.DISCOUNT != null}">
                    <s:property value="%{#session.CART.getTotal() * (100 - #session.DISCOUNT.decrease)/100 }"/>đ
                </s:if>
                <s:else>
                    <s:property value="%{#session.CART.getTotal()}"/>đ
                </s:else>
            </span>
            <br>
            <form action="CheckEmptyRoomAction" method="POST" >
                <input type="submit" value="Confirm"/>
            </form>
            <form action="view_cart.jsp" >
                <input type="submit" value="Edit"/>
            </form>
        </div>
        <div class="user">
            <h1>USER INFORMATION</h1>
            Email: <s:property value="%{user.email}"/><br>
            Fullname: <s:property value="%{user.fullname}"/><br>
            Phone: <s:property value="%{user.phone}"/><br>
        </div>
    </body>
</html>
