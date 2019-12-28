/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import khanh.dtos.ShoppingCart;
import org.apache.log4j.Logger;

/**
 *
 * @author buido
 */
public class DeleteCartAction {

    private static final Logger LOGGER = Logger.getLogger(DeleteCartAction.class);

    private int roomID;

    public DeleteCartAction() {
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String execute() {
        try {
            Map session = ActionContext.getContext().getSession();
            ShoppingCart cart = (ShoppingCart) session.get("CART");
            cart.removeRoom(roomID);
            session.put("CART", cart);
        } catch (Exception e) {
            LOGGER.error("DeleteCartAction: " + e.getMessage());
        }
        return "success";
    }

}
