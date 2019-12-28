/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import khanh.dtos.OrderDTO;
import khanh.models.OrderDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author buido
 */
public class SearchOrderByDateAction {

    private static final Logger LOGGER = Logger.getLogger(SearchOrderByDateAction.class);

    private String searchDate;
    private List<OrderDTO> listOrder;

    public SearchOrderByDateAction() {
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public List<OrderDTO> getListOrder() {
        return listOrder;
    }

    public void setListOrder(List<OrderDTO> listOrder) {
        this.listOrder = listOrder;
    }

    public String execute() {
        try {
            if (searchDate == null) {
                searchDate = new Timestamp(System.currentTimeMillis()) + "";
            }
            OrderDAO dao = new OrderDAO();
            Map session = ActionContext.getContext().getSession();
            String email = (String) session.get("USER");
            listOrder = dao.searchOrderByDate(searchDate, email);
        } catch (Exception e) {
            LOGGER.error("Error at SearchOrderAction: " + e.getMessage());
        }
        return "success";
    }

}
