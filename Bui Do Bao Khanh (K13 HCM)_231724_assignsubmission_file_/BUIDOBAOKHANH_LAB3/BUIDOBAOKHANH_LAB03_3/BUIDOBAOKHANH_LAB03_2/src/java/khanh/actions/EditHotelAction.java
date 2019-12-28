/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import khanh.dtos.FeedBackDTO;
import khanh.dtos.HotelDTO;
import khanh.dtos.RoomDTO;
import khanh.dtos.TypeRoomDTO;
import khanh.models.FeeadBackDAO;
import khanh.models.HotelDAO;
import khanh.models.RoomDAO;
import khanh.models.TypeRoomDAO;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author buido
 */
public class EditHotelAction {

    private static final Logger LOGGER = Logger.getLogger(EditHotelAction.class);

    private static final String ERROR = "error";
    private static final String SUCCESS = "success";
    private int hotelID, amountRoom, typeRoomID;
    private HotelDTO hotelDTO = null;
    private Map<Integer, String> listTypeRoom;
    private String checkin, checkout;
    private List<RoomDTO> listRoom;
    private List<FeedBackDTO> listFeedback;

    public EditHotelAction() {
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }

    public Map<Integer, String> getListTypeRoom() {
        return listTypeRoom;
    }

    public void setListTypeRoom(Map<Integer, String> listTypeRoom) {
        this.listTypeRoom = listTypeRoom;
    }

    public int getAmountRoom() {
        return amountRoom;
    }

    public void setAmountRoom(int amountRoom) {
        this.amountRoom = amountRoom;
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

    public List<RoomDTO> getListRoom() {
        return listRoom;
    }

    public void setListRoom(List<RoomDTO> listRoom) {
        this.listRoom = listRoom;
    }

    public int getTypeRoomID() {
        return typeRoomID;
    }

    public void setTypeRoomID(int typeRoomID) {
        this.typeRoomID = typeRoomID;
    }

    public List<FeedBackDTO> getListFeedback() {
        return listFeedback;
    }

    public void setListFeedback(List<FeedBackDTO> listFeedback) {
        this.listFeedback = listFeedback;
    }

    public String execute() {
        String url = ERROR;
        try {
            HotelDAO dao = new HotelDAO();
            hotelDTO = dao.findByPK(hotelID);
            HttpServletRequest request = ServletActionContext.getRequest();
            if (hotelDTO != null) {
                findTypeRoom();
                loadRoomByHotelID();
                loadFeedback();
                url = SUCCESS;
                request.setAttribute("CHECKIN", checkin);
                request.setAttribute("CHECKOUT", checkout);
                request.setAttribute("AMOUNTROOM", amountRoom);
            }
        } catch (Exception e) {
            LOGGER.error("ERROR AT EDITHOTELACTION: " + e.getMessage());
        }
        return url;
    }

    public void findTypeRoom() throws Exception {
        listTypeRoom = new HashMap<>();
        TypeRoomDAO dao = new TypeRoomDAO();
        List<TypeRoomDTO> result = dao.loadTypeRoomByHotel(hotelID, amountRoom);
        for (TypeRoomDTO typeRoomDTO : result) {
            listTypeRoom.put(typeRoomDTO.getTypeID(), typeRoomDTO.getTypeName() + " - Tankage: " + typeRoomDTO.getTankage());
        }
        if (typeRoomID == 0) {
            typeRoomID = result.get(0).getTypeID();
        }
    }

    public void loadRoomByHotelID() throws Exception {
        RoomDAO dao = new RoomDAO();
        listRoom = dao.findListRoomByHotel(hotelID, amountRoom, typeRoomID, checkin, checkout);
    }

    public void loadFeedback() throws Exception {
        FeeadBackDAO dao = new FeeadBackDAO();
        listFeedback = dao.loadFeedBackByHotelID(hotelID);
    }

}
