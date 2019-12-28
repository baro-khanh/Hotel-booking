/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import javax.servlet.http.HttpServletRequest;
import khanh.dtos.FeedBackDTO;
import khanh.models.FeeadBackDAO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author buido
 */
public class InsertFeedBackAction {

    private static final Logger LOGGER = Logger.getLogger(InsertFeedBackAction.class);

    private int hotelID;
    private String email, feedback;

    public InsertFeedBackAction() {
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String execute() {
        String url = "error";
        try {
            FeedBackDTO dto = new FeedBackDTO(hotelID, email, feedback);
            FeeadBackDAO dao = new FeeadBackDAO();
            boolean check = dao.insertFeedBack(dto);
            HttpServletRequest request = ServletActionContext.getRequest();
            if (check) {
                request.setAttribute("SUCCESS", "Thanks for your comment");
                url = "success";
            } else {
                request.setAttribute("ERROR", "Feedback failed");
            }
        } catch (Exception e) {
            LOGGER.error("Error at InsertFeedbackAction: " + e.getMessage());
        }
        return url;
    }

}
