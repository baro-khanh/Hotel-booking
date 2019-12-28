/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.dtos;

import java.util.HashMap;

/**
 *
 * @author buido
 */
public class ShoppingCart {

    private String email;
    private HashMap<Integer, RoomDTO> cart;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<Integer, RoomDTO> getCart() {
        return cart;
    }

    public ShoppingCart(String email) {
        this.email = email;
        this.cart = new HashMap<>();
    }

    public void addToCart(RoomDTO dto) {
        if (this.cart.containsKey(dto.getRoomID())) {
            RoomDTO roomIncart = this.cart.get(dto.getRoomID());
            this.getCart().get(dto.getRoomID()).setAmountRoom(dto.getAmountRoom());
            this.getCart().get(dto.getRoomID()).setCheckin(dto.getCheckin());
            this.getCart().get(dto.getRoomID()).setCheckout(dto.getCheckout());
        }
        this.cart.put(dto.getRoomID(), dto);
    }

    public void removeRoom(int roomID) {
        if (this.cart.containsKey(roomID)) {
            this.cart.remove(roomID);
        }
    }

    public void updateQuantity(int roomID, int quantity) {
        if (this.cart.containsKey(roomID)) {
            this.cart.get(roomID).setAmountRoom(quantity);
        }
    }

    public float getTotal() {
        float total = 0;
        for (RoomDTO dto : this.cart.values()) {
            total += dto.getPrice() * dto.getAmountRoom() * dto.getAmountNight();
        }
        return total;
    }

}
