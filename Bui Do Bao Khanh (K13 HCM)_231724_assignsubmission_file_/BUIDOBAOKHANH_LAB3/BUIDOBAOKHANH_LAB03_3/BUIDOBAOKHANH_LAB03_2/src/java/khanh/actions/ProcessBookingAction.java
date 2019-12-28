/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import khanh.dtos.AccountDTO;
import khanh.models.AccountDAO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author buido
 */
public class ProcessBookingAction {

    private static final Logger LOGGER = Logger.getLogger(ProcessBookingAction.class);
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private AccountDTO user = null;

    public ProcessBookingAction() {
    }

    public AccountDTO getUser() {
        return user;
    }

    public void setUser(AccountDTO user) {
        this.user = user;
    }

    public String execute() {
        String url = ERROR;
        try {
            Map session = ActionContext.getContext().getSession();
            String email = (String) session.get("USER");
            AccountDAO dao = new AccountDAO();
            user = dao.findAccount(email);
            if (user != null) {
                url = SUCCESS;
            } else {
                HttpServletRequest request = ServletActionContext.getRequest();
                request.setAttribute("INVALID", "Your account is not valid");
            }
        } catch (Exception e) {
            LOGGER.error("ERROR AT PROCESSBOOKINGACTION: " + e.getMessage());
        }
        return url;
    }

}
