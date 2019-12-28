<%-- 
    Document   : search_page
    Created on : Dec 2, 2019, 4:15:26 PM
    Author     : buido
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        <s:head></s:head>
            <style>
                body{
                    margin: 0px;
                    font-family: 'Pacifico', cursive;
                    background-image: url(img/70759417_2941184955898788_543740967740.jpg);
                    background-position: center;
                    background-size: cover;
                    background-repeat: no-repeat;
                    background-attachment: fixed;
                    overflow: hidden;
                }
                .content{
                    width: 520px;
                    height: 300px;
                    color: black;
                    margin-top: 120px;
                    margin-left: 240px;
                    background:rgba(240,255,255,0.5);
                    text-align: center;
                    border-radius: 50px;
                    margin-bottom: 30px;
                    padding-right: 20px;
                    padding-bottom: 20px;
                }
                .textfield-control{
                    width: 100%;
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
                    width: 100%;
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
        <div class="content">
            <h1 style="margin: 0px;">LETOH</h1>
            <div style="width: 100%; float: left;">
                <form action="SearchHotelNameAction" method="POST" class="form-control">
                    <div style="width: 100%; float: left; ">
                        <input type="text" placeholder="Hotel name" name="search" class="textfield-control" label="Hotel name" style="width: 80%; margin-left: 50px;"/>
                    </div>
                    <div style="width: 10%; float: left;">
                        <input type="image" src="img/searchicon.png" alt="Submit" width="48" height="48" style="margin-top: -10px;">
                    </div>
                </form>
            </div>
            <div style="width: 100%; float: left;">
                <form action="SearchHotelAction" method="POST" style="width: 1000px; background-color: red;">
                    <div style="width: 100%; float: left; margin-left: 100px;">
                        <table style="text-align: center;">
                            <tr style="">
                                <td><s:select list="%{#session.LISTAREA}" lname = "name" label = "Area" headerKey="" name="areaID" cssClass="select-control" ></s:select></td>
                            </tr>
                            <tr>
                                <td>Amount Room:</td>
                                <td><input name="amount" class="textfield-control" type="number" step="1" min="1" label="" placeholder="Amount room"/></td>
                            </tr>
                            <tr>
                                <td>Checkin date:</td>
                                <td><input name="checkin" class="textfield-control" type="date" label="" id="checkin" /></td>
                            </tr>
                            <tr>
                                <td>Checkout date:</td>
                                <td><input name="checkout" class="textfield-control" type="date" label="" id="checkout"/></td>
                            </tr>
                        </table>
                    </div>
                    <div style="width: 10%; float: left;">
                        <input type="image" src="img/searchicon.png" alt="Submit" width="48" height="48" style="margin-top: -10px; margin-right: 100px;" onclick="return checkDateValidation()" >
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
