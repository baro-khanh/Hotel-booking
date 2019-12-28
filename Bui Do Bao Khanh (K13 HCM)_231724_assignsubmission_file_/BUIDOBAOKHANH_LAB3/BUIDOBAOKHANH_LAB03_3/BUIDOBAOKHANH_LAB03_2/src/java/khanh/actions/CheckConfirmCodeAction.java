/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author buido
 */
public class CheckConfirmCodeAction {

    private static final Logger LOGGER = Logger.getLogger(CheckConfirmCodeAction.class);
    private String confirmcode;

    public CheckConfirmCodeAction() {
    }

    public String getConfirmcode() {
        return confirmcode;
    }

    public void setConfirmcode(String confirmcode) {
        this.confirmcode = confirmcode;
    }

    public String execute() throws Exception {
        String url = "error";
        try {
            Map session = ActionContext.getContext().getSession();
            String confirmcode_sent = (String) session.get("CONFIRMCODE");
            if (confirmcode_sent.equals(confirmcode)) {
                url = "success";
            }
        } catch (Exception e) {
            LOGGER.error("Error at CheckConfirmAction: " + e.getMessage());
        }
        if (url.equals("error")) {
            HttpServletRequest requets = ServletActionContext.getRequest();
            requets.setAttribute("ERROR", "Your confirm code is wrong");
        }
        return url;
    }

}
