<%-- 
    Document   : list_hotel
    Created on : Dec 4, 2019, 12:19:28 AM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Page</title>
        <style>
            body{
                margin: 0px;
                font-family: 'Pacifico', cursive;
            }
            .img{
                width: 300px;
                height: 300px;
                float: left;
                border-radius: 20px;
            }
            .left-control{
                width: 25%;
                height: 580px;
                float: left;
                margin-top: 5px;
            }
            .right-control{
                /*margin-top: 5px;*/
                width: 73%;
                height: auto;
                float: right;
                text-align: center;
                /*padding-top: 80px;*/
            }
            .item{
                width: 97%;
                float: left;
                margin: 6px;
                padding: 6px;
                /*background-color: pink;*/
                border-radius: 20px;
                text-align: left;
            }
            .item:hover{
                background-color: #AFEEEE;
                border: 1px #008080 solid;
                -webkit-box-shadow: -31px 29px 19px -18px rgba(0,0,0,0.75);
                -moz-box-shadow: -31px 29px 19px -18px rgba(0,0,0,0.75);
                box-shadow: -31px 29px 19px -18px rgba(0,0,0,0.75);
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
            .select-control{
                width: 85%;
                margin: 0px;
                padding: 5px;
                font-family: 'Pacifico', cursive;
                background-color: #333;
                color: wheat;
                margin-right: 50px;
                border-radius: 20px;
            }
        </style>
        <script>
            function checkDateValidation() {
                var checkin = document.getElementById('checkin').value;
                var checkout = document.getElementById('checkout').value;
                var date = new Date().toJSON().split('T')[0];
                if (date > checkin || date > checkout) {
                    alert('Checkin and checkout date must be greater than today');
                    return false;
                } else {
                    if (checkin > checkout) {
                        alert('Checkin must be greater than checkout date');
                        return false;
                    }
                    return true
                }
            }
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="left-control">
            <form action="SearchHotelAction" method="POST" class="form-control">
                <div style="width: 100%; float: left; margin-left: 3px; ">
                    <s:select list="%{#session.LISTAREA}" lname = "name" headerKey="" name="areaID" cssClass="select-control" ></s:select>
                    <input name="amount" class="textfield-control" type="number" step="1" min="1" label="Amount Room" placeholder="Amount room" value="<s:property value="%{#request.AMOUNTROOM}"/>"/>
                    <input name="checkin" class="textfield-control" type="date" label="Checkin date" id="checkin" value="<s:property value="%{#request.CHECKIN}"/>" />
                    <input name="checkout" class="textfield-control" type="date" label="Checkout date" id="checkout" value="<s:property value="%{#request.CHECKOUT}"/>"/>
                </div>
                <div style="width: 10%; float: left; ">
                    <input type="image" src="img/searchicon.png" alt="Submit" width="48" height="48" style=" margin-bottom: 200px; margin-left: 80px;" onclick="return checkDateValidation()" >
                </div>
            </form>
        </div>
        <div class="right-control">
            <s:if test="%{listHotel != null}">
                <s:if test="%{listHotel.size() > 0}">
                    <s:iterator value="listHotel">
                        <div class="item" style="">
                            <div style=" float: left; width: 26%;">
                                <img src="img/<s:property value="%{photo}"/>" class="img"/>
                            </div>
                            <div style=" float: left; width: 60%; margin-left: 30px; padding-left: 70px; text-align: center;">
                                <h3><s:property value="%{hotelName}"/></h3>
                                <i>Address: <s:property value="%{address}"/></i><br>
                                <i>Phone: <s:property value="%{phone}"/></i><br>
                                <s:if test="%{#session.ROLE != null}">
                                    <s:if test="%{#session.ROLE != 'admin'}">
                                        <form action="EditHotelAction" method="POST">
                                            <input type= "hidden" value="<s:property value="%{#request.AMOUNTROOM}"/>" name="amountRoom"/>
                                            <input type= "hidden" value="<s:property value="%{#request.CHECKIN}"/>" name="checkin"/>
                                            <input type= "hidden" value="<s:property value="%{#request.CHECKOUT}"/>" name="checkout"/>
                                            <input type= "hidden" name="hotelID" value="<s:property value="%{hotelID}"/>"/>
                                            <input type = "submit" value="Book room" name="action"/>
                                        </form>
                                    </s:if>
                                </s:if>
                            </div>
                        </div>
                    </s:iterator>
                </s:if>
                <s:else>
                    No Hotel record found
                </s:else>

            </s:if>
        </div>
    </body>
</html>
