/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import java.time.LocalDate;
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
public class SearchHotelNameAction {

    private static final Logger LOGGER = Logger.getLogger(SearchHotelNameAction.class);

    private String search;
    private List<HotelDTO> listHotel;

    public SearchHotelNameAction() {
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<HotelDTO> getListHotel() {
        return listHotel;
    }

    public void setListHotel(List<HotelDTO> listHotel) {
        this.listHotel = listHotel;
    }

    public String execute() throws Exception {
        try {
            HotelDAO dao = new HotelDAO();
            String checkin = java.time.LocalDate.now() + "";
            String checkout = LocalDate.now().plusDays(1L) + "";
            listHotel = dao.searchByLikeName(search, checkin, checkout);
            if (listHotel.size() > 0) {
                listHotel = dao.searchTotalRoom(listHotel);
            }

            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("CHECKIN", checkin);
            request.setAttribute("CHECKOUT", checkout);
            request.setAttribute("AMOUNTROOM", 1);
        } catch (Exception e) {
            LOGGER.error("Error at SearchHotelNameAction: " + e.getMessage());
        }
        return "success";
    }

}
