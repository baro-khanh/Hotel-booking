/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import java.util.List;
import khanh.dtos.OrderDetailDTO;
import khanh.models.OrderDetailDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author buido
 */
public class SearchDetailOrderAction {

    private static final Logger LOGGER = Logger.getLogger(SearchDetailOrderAction.class);

    private int action;
    private List<OrderDetailDTO> listDetail;

    public SearchDetailOrderAction() {
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public List<OrderDetailDTO> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<OrderDetailDTO> listDetail) {
        this.listDetail = listDetail;
    }

    public String execute() {
        try {
            OrderDetailDAO dao = new OrderDetailDAO();
            listDetail = dao.loadOrderDetailByOrderID(action);
        } catch (Exception e) {
            LOGGER.error("Error at SearchDetailOrder: " + e.getMessage());
        }
        return "success";
    }

}
