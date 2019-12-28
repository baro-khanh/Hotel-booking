/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import khanh.dtos.DiscountDTO;
import khanh.dtos.OrderDTO;
import khanh.dtos.ShoppingCart;
import khanh.models.OrderDAO;
import khanh.models.OrderDetailDAO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author buido
 */
public class OrderRoomAction {

    private static final Logger LOGGER = Logger.getLogger(OrderRoomAction.class);
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public OrderRoomAction() {
    }

    public String execute() {
        String url = ERROR;
        try {
            //insert order
            Map session = ActionContext.getContext().getSession();
            ShoppingCart cart = (ShoppingCart) session.get("CART");
            HttpServletRequest request = ServletActionContext.getRequest();
            String email = (String) session.get("USER");
            DiscountDTO discount = (DiscountDTO) session.get("DISCOUNT");
            float total = cart.getTotal();
            boolean insertOrder = insertOrder(email, discount, total);
            if (insertOrder) {
                OrderDAO daoOrder = new OrderDAO();
                int orderID = daoOrder.findPKOrder();
                OrderDetailDAO daoDetail = new OrderDetailDAO();
                boolean insertDetail = daoDetail.inserDetail(orderID, cart);
                if (insertDetail) {
                    cart.getCart().clear();
                    session.remove("CONFIRMCODE");
                    session.remove("DISCOUNT");
                    url = SUCCESS;
                } else {
                    request.setAttribute("ERROR", "Create new order detail failed");
                    url = ERROR;
                }
            } else {
                request.setAttribute("ERROR", "Create new order failed");
                url = ERROR;
            }
        } catch (Exception e) {
            LOGGER.error("Error at OrderRoomAction: " + e.getMessage());
        }
        return url;
    }

    public boolean insertOrder(String email, DiscountDTO dtoDiscount, float total) throws Exception {
        boolean check = false;
        String discount = "";
        OrderDAO daoOrder = new OrderDAO();
        String createDate = new Timestamp(System.currentTimeMillis()) + "";
        OrderDTO dtoOrder = new OrderDTO(email, discount, createDate, total);
        check = daoOrder.insertOrder(dtoOrder);
        if (check) {
            if (dtoDiscount != null) {
                discount = dtoDiscount.getDiscountCode();
                int decrease = 100 - dtoDiscount.getDecrease();
                check = daoOrder.updateDiscount(daoOrder.findPKOrder(), discount, total * decrease / 100);
            }
        }
        return check;
    }
}
