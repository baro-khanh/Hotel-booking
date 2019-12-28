/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import khanh.dtos.DiscountDTO;
import khanh.dtos.OrderDTO;
import khanh.dtos.OrderDetailDTO;
import khanh.dtos.RoomDTO;
import khanh.dtos.ShoppingCart;
import khanh.models.OrderDAO;
import khanh.models.OrderDetailDAO;
import khanh.models.RoomDAO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author buido
 */
public class CheckEmptyRoomAction {

    private static final Logger LOGGER = Logger.getLogger(CheckEmptyRoomAction.class);

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";
    private static final String INVALID = "invalid";

    public CheckEmptyRoomAction() {
    }

    public String execute() {
        String url = ERROR;
        try {
            Map session = ActionContext.getContext().getSession();
            HttpServletRequest request = ServletActionContext.getRequest();
            ShoppingCart cart = (ShoppingCart) session.get("CART");
            RoomDAO daoRoom = new RoomDAO();
            //check exist room in cart
            String checkExist = daoRoom.checkCartRoomExist(cart);
            if (checkExist.length() == 0) {
                //ton tai het
                List<RoomDTO> room_hadOrder = new ArrayList<>();
                List<RoomDTO> room_noOrder = new ArrayList<>();
                //check empty
                String checkRoom = distributeListRoom(room_hadOrder, room_noOrder, cart);
                if (checkRoom.length() == 0) {
                    url = SUCCESS;
                } else {
                    request.setAttribute("INVALID", checkRoom + " is not enough quantity room");
                    url = INVALID;
                }
            } else {
                request.setAttribute("INVALID", checkExist + " is not available");
                url = INVALID;
            }
        } catch (Exception e) {
            LOGGER.error("ERROR AT CHECKEMPTYROOMACTION: " + e.getMessage());
        }
        return url;
    }


    public String distributeListRoom(List<RoomDTO> room_hadOrder, List<RoomDTO> room_noOrder, ShoppingCart cart) throws Exception {
        OrderDetailDAO dao = new OrderDetailDAO();
        int check, checkInList;
        room_hadOrder = new ArrayList<>();
        room_noOrder = new ArrayList<>();
        List<OrderDetailDTO> listRoomIDInOrder = dao.loadRoomIDinOrderDetail();
        //phan loai room
        for (RoomDTO value : cart.getCart().values()) {
            check = -1;
            for (int i = 0; i < listRoomIDInOrder.size(); i++) {
                if (value.getRoomID() == listRoomIDInOrder.get(i).getRoomID()) {
                    checkInList = -1;
                    for (RoomDTO roomDTO : room_hadOrder) {
                        if (roomDTO.getRoomID() == listRoomIDInOrder.get(i).getRoomID()) {
                            checkInList++;
                        }
                    }
                    if (checkInList < 0) {
                        room_hadOrder.add(value);
                        check++;
                    }
                }
            }
            if (check < 0) {
                room_noOrder.add(value);
            }
        }

        String checkRoom = "";
        //check phong trong
        //nhung room chua co order
        if (!room_noOrder.isEmpty()) {
            checkRoom = checkEmptyForRoomNoOrder(room_noOrder);
        }
        if (checkRoom.length() == 0) {
            //nhung room da co order trong ngay nay
            checkRoom = checkEmptyForRoomHadOrder(room_hadOrder);

        }
        return checkRoom;
    }

    public String checkEmptyForRoomNoOrder(List<RoomDTO> room_noOrder) throws Exception {
        String roomName = "";
        RoomDAO dao = new RoomDAO();
        for (RoomDTO roomDTO : room_noOrder) {
            roomName = dao.checkQuantityNoOrderRoom(roomDTO.getRoomID(), roomDTO.getAmountRoom());
            if (roomName.length() != 0) {
                break;
            }
        }
        return roomName;
    }

    public String checkEmptyForRoomHadOrder(List<RoomDTO> room_hadOrder) throws Exception {
        String check = "";
        OrderDetailDAO dao = new OrderDetailDAO();
        List<OrderDetailDTO> listRoomOrder = dao.loadRoomIDinOrderDetail();
        String checkIn, checkOut;
        Date inCart, outCart, inOrder, outOrder;
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (RoomDTO roomDTO : room_hadOrder) {
            for (OrderDetailDTO orderDetailDTO : listRoomOrder) {
                if (orderDetailDTO.getRoomID() == roomDTO.getRoomID()) {
                    //neu room nay duoc order roi
                    inCart = simpleDateFormat.parse(roomDTO.getCheckin());
                    outCart = simpleDateFormat.parse(roomDTO.getCheckout());
                    inOrder = simpleDateFormat.parse(orderDetailDTO.getCheckIn());
                    outOrder = simpleDateFormat.parse(orderDetailDTO.getCheckOut());
                    //xet thoi gian
                    if (inOrder.after(outCart) || inCart.after(outOrder)) {
                        //khong trung lich dat
                        //check nhu bth
                        check = checkAvailableRoom(roomDTO);
                    } else {
                        //so luong ban dau - so luong da dat trong khoang tgian do >= so luong muon dat
                        check = checkOrderRoom(roomDTO);
                    }
                    if (check.length() != 0) {
                        break;
                    }
                }
            }
        }
        return check;
    }

    public String checkAvailableRoom(RoomDTO cartDTO) throws Exception {
        String check = "";
        RoomDAO dao = new RoomDAO();
        check = dao.checkQuantityNoOrderRoom(cartDTO.getRoomID(), cartDTO.getAmountRoom());
        return check;
    }

    public String checkOrderRoom(RoomDTO cartDTO) throws Exception {
        String check = "";
        RoomDAO dao = new RoomDAO();
        OrderDetailDAO daoDetail = new OrderDetailDAO();
        int totalRoom = dao.getTotalRoom(cartDTO.getRoomID());
        int orderdRoom = daoDetail.getAmountRoomOrder(cartDTO.getRoomID(), cartDTO.getCheckin(), cartDTO.getCheckout());
        if ((totalRoom - orderdRoom) < cartDTO.getAmountRoom()) {
            check = cartDTO.getRoomName();
        }
        return check;
    }

}
