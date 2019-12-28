/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import khanh.dtos.HotelDTO;
import khanh.models.HotelDAO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author buido
 */
public class SearchHotelAction {

    private static final Logger LOGGER = Logger.getLogger(SearchHotelAction.class);
    private static final String SUCCESS = "success";
    private int areaID, amount;
    private String checkin, checkout;
    private List<HotelDTO> listHotel;

    public SearchHotelAction() {
    }

    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public List<HotelDTO> getListHotel() {
        return listHotel;
    }

    public void setListHotel(List<HotelDTO> listHotel) {
        this.listHotel = listHotel;
    }

    public String execute() {
        try {
            HotelDAO dao = new HotelDAO();
            listHotel = dao.searchHotelByRequire(areaID, amount, checkin, checkout);
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("CHECKIN", checkin);
            request.setAttribute("CHECKOUT", checkout);
            request.setAttribute("AMOUNTROOM", amount);
        } catch (Exception e) {
            LOGGER.error("Error at SearchHotelAction: " + e.getMessage());
        }
        return "success";
    }

}
