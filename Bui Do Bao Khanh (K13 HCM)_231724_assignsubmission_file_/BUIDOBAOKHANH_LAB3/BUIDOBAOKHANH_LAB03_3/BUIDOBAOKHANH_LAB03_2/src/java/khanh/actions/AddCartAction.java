/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import khanh.dtos.RoomDTO;
import khanh.dtos.ShoppingCart;
import khanh.models.RoomDAO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author buido
 */
public class AddCartAction {
    
    private static final Logger LOGGER = Logger.getLogger(AddCartAction.class);
    
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private int roomID, amountRoom;
    private String checkin, checkout;
    
    public AddCartAction() {
    }
    
    public int getRoomID() {
        return roomID;
    }
    
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
    
    public int getAmountRoom() {
        return amountRoom;
    }
    
    public void setAmountRoom(int amountRoom) {
        this.amountRoom = amountRoom;
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
    
    public String execute() {
        String url = ERROR;
        try {
            RoomDAO daoRoom = new RoomDAO();
            RoomDTO dto = daoRoom.findRoom(roomID, checkin, checkout);
            if (dto != null) {
                long amountNight = daoRoom.countNight(checkin, checkout);
                dto.setAmountNight(amountNight);
                Map session = ActionContext.getContext().getSession();
                ShoppingCart cart = (ShoppingCart) session.get("CART");
                if (cart == null) {
                    String user = (String) session.get("USER");
                    cart = new ShoppingCart(user);
                }
                dto.setAmountRoom(amountRoom);
                cart.addToCart(dto);
                session.put("CART", cart);
                url = SUCCESS;
            } else {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("ERROR", "This room is full");
            }
        } catch (Exception e) {
            LOGGER.error("ERROR AT ADDCARTACTION: " + e.getMessage());
        }
        return url;
    }
    
}
