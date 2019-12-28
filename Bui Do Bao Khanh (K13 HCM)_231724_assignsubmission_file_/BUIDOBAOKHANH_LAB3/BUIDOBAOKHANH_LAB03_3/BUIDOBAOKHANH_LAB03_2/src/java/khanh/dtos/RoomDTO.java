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
public class RoomDTO {
//quantity la so luong phong trong trong ks
//amountRoom la so luong dat

    public RoomDTO() {
    }
    private int roomID, quantity, roomTypeID, hotelID, amountRoom;
    private String des, roomName, photo, checkin, checkout, hotelName;
    private boolean status;
    private float price;
    private long amountNight;

    public RoomDTO(int roomID, int quantity, String roomName) {
        this.roomID = roomID;
        this.quantity = quantity;
        this.roomName = roomName;
    }

    public RoomDTO(int roomID, int quantity, int roomTypeID, int hotelID, String des, String roomName, String photo, float price) {
        this.roomID = roomID;
        this.quantity = quantity;
        this.roomTypeID = roomTypeID;
        this.hotelID = hotelID;
        this.des = des;
        this.roomName = roomName;
        this.photo = photo;
        this.price = price;
    }

    public RoomDTO(int roomID, int quantity, String roomName, String photo, String checkin, String checkout, float price) {
        this.roomID = roomID;
        this.quantity = quantity;
        this.roomName = roomName;
        this.photo = photo;
        this.checkin = checkin;
        this.checkout = checkout;
        this.price = price;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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

    public int getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(int roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public long getAmountNight() {
        return amountNight;
    }

    public void setAmountNight(long amountNight) {
        this.amountNight = amountNight;
    }

    public int getAmountRoom() {
        return amountRoom;
    }

    public void setAmountRoom(int amountRoom) {
        this.amountRoom = amountRoom;
    }

}
