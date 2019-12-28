/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionSupport;
import java.sql.Timestamp;
import khanh.dtos.AccountDTO;
import khanh.models.AccountDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author buido
 */
public class RegisterUserAction extends ActionSupport {

    private static final Logger LOGGER = Logger.getLogger(RegisterUserAction.class);

    private String emailID, fullname, phoneNo, password, confirm;

    public RegisterUserAction() {
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String execute() {
        String url = "error";
        try {
            boolean status = true;
            String createDate = new Timestamp(System.currentTimeMillis()) + "";
            AccountDTO dto = new AccountDTO(emailID, fullname, phoneNo, createDate, status, 2);
            dto.setPassword(password);
            AccountDAO dao = new AccountDAO();
            boolean check_insert = dao.insertUser(dto);
            if (check_insert) {
                url = "success";
            }
        } catch (Exception e) {
            LOGGER.error("Error at RegisterUserAction: " + e.getMessage());
        }
        return url;
    }

}
