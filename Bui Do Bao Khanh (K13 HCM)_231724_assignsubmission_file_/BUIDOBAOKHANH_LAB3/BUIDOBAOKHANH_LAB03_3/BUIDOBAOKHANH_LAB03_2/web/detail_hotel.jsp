<%-- 
    Document   : detailHotel.jsp
    Created on : Dec 7, 2019, 12:06:33 PM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail hotel Page</title>
        <style>
             body{
                font-family: 'Pacifico', cursive;
            }
            .hotel{
                width: 29%;
                float: left;
                height: auto;
                text-align: center;
                margin-top: 10px;
                /*background-color: red;*/
            }
            .room{
                width: 50%;
                height: 500px;
                /*background-color: green;*/
                float: left;
                margin-top: 10px;
                padding: 5px;
                text-align: left;
            }
            .find{
                width: 19%;
                float: left;
                /*margin-bottom: 10px;*/
                height: 50px;
                margin-top: 10px;
                /*background-color: yellow;*/
                float: left;
            }
            .img{
                width: 100%;
                height: 300px;
            }
            .select-control{
                width: 100%;
                font-family: 'Pacifico', cursive;
                background-color: #333;
                color: wheat;
                float: right;
                padding: 5px;
                border-radius: 20px;
            }
            .textfield-control{
                width: 90%;
                font-family: 'Pacifico', cursive;
                background-color: #333;
                color: wheat;
                text-align: center;
                /*margin-right: 50px;*/
                margin-top: 5px;
                float: right;
                padding: 5px;
                border-radius: 20px;
            }
            .form-control{
                width: 100%;
                /*background-color: red;*/
            }
            .img-small{
                width: 130px;
                height: 130px;
                margin-top: 2px;
            }
            .item{
                width: 100%;
                margin-bottom: 10px;
                float: left;
                border: 1px dashed black;
                font-size: 0.8em;
            }
            .item:hover{
                background-color: beige;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div>
            <div class="hotel">
                <div style=" float: left; width: 100%;">
                    <img src="img/<s:property value="%{hotelDTO.photo}"/>" class="img"/>
                </div>
                <div style=" float: left; width: 100%;">
                    <h3><s:property value="%{hotelDTO.hotelName}"/></h3>
                    <i>Address: <s:property value="%{hotelDTO.address}"/></i><br>
                    <i>Phone: <s:property value="%{hotelDTO.phone}"/></i><br>
                    <i>Available rooms: <s:property value="%{hotelDTO.totalRoom}"/></i>
                </div>
                <div style=" float: left; width: 100%; margin-bottom: 10px;">
                    <hr>
                    <h3>Feedback</h3>
                    <s:if test="%{listFeedback != null}">
                        <s:if test="%{listFeedback.size() > 0}">
                            <table border="0" style="width: 100%;">
                                <tbody>
                                    <s:iterator value="%{listFeedback}">
                                        <tr>
                                            <td style="color: #008080"><s:property value="%{email}"/>:</td>
                                            <td style="color: darkgray"><s:property value="%{feedback}"/></td>
                                        </tr>
                                    </s:iterator>
                                </tbody>
                            </table>

                        </s:if>
                        <s:else>
                            No feedback
                        </s:else>
                    </s:if>
                </div>
            </div>

            <div class="room">
                <s:if test="%{listRoom!=null}">
                    <s:if test="%{listRoom.size() > 0}">
                        <s:iterator value="%{listRoom}">
                            <div class="item">
                                <div style="width: 26%; float: left; margin-right: 5px;">
                                    <img src="img/<s:property value="%{photo}"/>" class="img-small"/>
                                </div>
                                <div style="float: left; width: 60%;">
                                    <h3 style="color: red;"><s:property value="%{roomName}"/></h3>
                                    <i>Description: <s:property value="%{des}"/></i><br><br>
                                    <i style="color: gray;">Only <s:property value="%{quantity}"/> rooms</i><br>
                                    <i style="color: orange;">Price: <s:property value="%{price}"/> đ </i>
                                    <form action="AddCartAction" method="POST" >
                                        <input type="hidden" value="<s:property value="%{roomID}"/>" name="roomID" />
                                        <input type="hidden" value="<s:property value="%{#request.CHECKIN}"/>" name="checkin"/>
                                        <input type="hidden"  value="<s:property value="%{#request.CHECKOUT}"/>" name="checkout" />
                                        <input type="hidden" value="<s:property value="%{#request.AMOUNTROOM}"/>" name="amountRoom"/>
                                        <input type="submit" value="Add to cart"/>
                                    </form>
                                </div>
                            </div>
                        </s:iterator>
                    </s:if>
                    <s:else>
                        Sorry, the hotel is out of room at this time
                    </s:else>
                </s:if>
            </div>

            <div class="find">
                <form action="EditHotelAction" method="POST" class="form-control">
                    <s:select list="%{listTypeRoom}" lname = "name" headerKey="" name="typeRoomID" cssClass="select-control" ></s:select>
                    <input name="amountRoom" value="<s:property value="%{amountRoom}"/>" required="" class="textfield-control" type="number" min="1" step="1" />
                    <input type="date" name="checkin" class="textfield-control" required="" value="<s:property value="%{checkin}"/>"/>
                    <input type="date" name="checkout" class="textfield-control" required="" value="<s:property value="%{checkout}"/>"/>
                    <input type="hidden" value="<s:property value="%{hotelDTO.hotelID}"/>" name="hotelID"/>
                    <input type="submit" value="Update" name="action"/>
                </form>
            </div>
        </div>
    </body>
</html>
