/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.dtos;

/**
 *
 * @author buido
 */
public class OrderDetailDTO {

    private int orderDetailID, orderID, roomID, quantity, hotelID, statusID;
    private String checkIn, checkOut, hotelName;
    private float subPrice;

    public OrderDetailDTO(int orderID, int roomID, int quantity, int hotelID, String checkIn, String checkOut, float subPrice) {
        this.orderID = orderID;
        this.roomID = roomID;
        this.quantity = quantity;
        this.hotelID = hotelID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.subPrice = subPrice;
    }

    public OrderDetailDTO(int orderDetailID, int roomID, int quantity, int statusID, String checkIn, String checkOut, String hotelName, float subPrice) {
        this.orderDetailID = orderDetailID;
        this.roomID = roomID;
        this.quantity = quantity;
        this.statusID = statusID;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hotelName = hotelName;
        this.subPrice = subPrice;
    }
    
    

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public float getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(float subPrice) {
        this.subPrice = subPrice;
    }

    public int getStatusID() {
        return statusID;
    }

    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

}
