/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import khanh.models.AccountDAO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author buido
 */
public class LoginAction extends ActionSupport {

    private static final Logger LOGGER = Logger.getLogger(LoginAction.class);

    private String emailID, password;

    public LoginAction() {
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String execute() {
        String url = "error";
        try {
            AccountDAO dao = new AccountDAO();
            int checkRole = dao.checkLogin(emailID, password);
            HttpServletRequest request = ServletActionContext.getRequest();
            if (checkRole < 0) {
                request.setAttribute("INVALID", "Email or Password is wrong");
            } else {
                Map session = ActionContext.getContext().getSession();
                session.put("USER", emailID);
                switch (checkRole) {
                    case 1:
                        session.put("ROLE", "admin");
                        url = "success";
                        break;
                    case 2:
                        session.put("ROLE", "customer");
                        url = "success";
                        break;
                    default:
                        request.setAttribute("INVALID", "Your role is no support");
                        break;
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error at LoginAction: " + e.getMessage());
        }
        return url;
    }

}
