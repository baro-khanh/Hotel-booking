/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author buido
 */
public class LogOutAction {

    private static final Logger LOGGER = Logger.getLogger(LogOutAction.class);

    public LogOutAction() {
    }

    public String execute() {
        try {
            Map session = ActionContext.getContext().getSession();
            session.clear();
        } catch (Exception e) {
            LOGGER.error("Error at LogoutAction: " + e.getMessage());
        }
        return "success";
    }

}
