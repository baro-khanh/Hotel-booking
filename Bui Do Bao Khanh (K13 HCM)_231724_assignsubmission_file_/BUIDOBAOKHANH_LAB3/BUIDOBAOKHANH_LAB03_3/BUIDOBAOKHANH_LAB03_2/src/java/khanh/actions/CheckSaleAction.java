/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.Map;
import khanh.dtos.DiscountDTO;
import khanh.models.DiscountDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author buido
 */
public class CheckSaleAction {

    private static final Logger LOGGER = Logger.getLogger(CheckSaleAction.class);

    private String saleCode, mess;
    private DiscountDTO discount = null;

    public CheckSaleAction() {
    }

    public String getSaleCode() {
        return saleCode;
    }

    public void setSaleCode(String saleCode) {
        this.saleCode = saleCode;
    }

    public DiscountDTO getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountDTO discount) {
        this.discount = discount;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    public String execute() {
        try {
            DiscountDAO dao = new DiscountDAO();
            discount = dao.findDiscount(saleCode);
            Map session = ActionContext.getContext().getSession();
            session.remove("DISCOUNT");
            if (discount != null) {
                session.put("DISCOUNT", discount);
                mess = "Discount available";
            } else {
                mess = "Discount is unavailable";
            }
        } catch (Exception e) {
            LOGGER.error("ERROR AT CHECKSALEACTION: " + e.getMessage());
        }
        return "success";
    }

}
