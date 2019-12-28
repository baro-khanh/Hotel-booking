/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import khanh.dtos.ShoppingCart;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author buido
 */
public class UpdateCartAction {

    private static final Logger LOGGER = Logger.getLogger(UpdateCartAction.class);
    private static final String ERROR = "error";
    private static final String SUCCESS = "success";
    private String action;
    private int roomID, quantity;

    public UpdateCartAction() {
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
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

    public String execute() {
        String url = ERROR;
        try {
            if (action.equals("Update")) {
                updateCart();
                url = SUCCESS;
            } else {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("ERROR", "Action no support");
            }
        } catch (Exception e) {
            LOGGER.error("Error at UpdateCartAction: " + e.getMessage());
        }
        return url;
    }

    public void updateCart() {
        Map session = ActionContext.getContext().getSession();
        ShoppingCart cart = (ShoppingCart) session.get("CART");
        cart.updateQuantity(roomID, quantity);
        session.put("CART", cart);
    }

}
