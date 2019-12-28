/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;

/**
 *
 * @author buido
 */
public class CreateFeedBackAction {

    private int hotelID;
    private String hotelName, email;

    public CreateFeedBackAction() {
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        email = (String) session.get("USER");
        return "success";
    }

}
