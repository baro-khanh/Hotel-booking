/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author buido
 */
public class CreateConfirmCodeAction {

    private static final Logger LOGGER = Logger.getLogger(CreateConfirmCodeAction.class);
    private static final String HOST_NAME = "smtp.gmail.com";
    private static final int SSL_PORT = 465;
    private static final int TSL_POST = 587;
    private static final String APP_PASSWORD = "khanhse130392";
    private static final String APP_EMAIL = "khanhbdbse130392@fpt.edu.vn";
    private String confirmCode = "";

    public CreateConfirmCodeAction() {
    }

    public String getConfirmCode() {
        return confirmCode;
    }

    public void setConfirmCode(String confirmCode) {
        this.confirmCode = confirmCode;
    }

    public String execute() {
        String url = "error";
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", HOST_NAME);
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.port", TSL_POST);

            // get Session
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
                }
            });

            Map sessionApp = ActionContext.getContext().getSession();
            String recieve_email = (String) sessionApp.get("USER");
            createCode();
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recieve_email));
            message.setSubject("Confirm code for order room");
            message.setText("Your code is: " + confirmCode);

            // send message
            Transport.send(message);
            url = "success";
            sessionApp.put("CONFIRMCODE", confirmCode);
        } catch (Exception e) {
            LOGGER.error("Error at CreateConfirmAction: " + e.getMessage());
        }
        if (url.equals("error")) {
            request.setAttribute("ERROR", "Some problem send confirm code failed");
        }
        return url;
    }

    public void createCode() {
        Random rd = new Random();
        int number;
        for (int i = 0; i < 6; i++) {
            number = rd.nextInt(10);
            confirmCode = confirmCode + number;
        }
    }

}
