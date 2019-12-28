/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import khanh.db.MyConnection;
import khanh.dtos.OrderDetailDTO;
import khanh.dtos.RoomDTO;
import khanh.dtos.ShoppingCart;

/**
 *
 * @author buido
 */
public class OrderDetailDAO {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public OrderDetailDAO() {
    }

    public boolean inserDetail(int orderID, ShoppingCart cart) throws Exception {
        boolean check = true;
        try {
            String sql = "Insert into tblOrderDetail( OrderID, RoomID, Quantity, CheckIn, CheckOut, Status, HotelID, SubPrice) values(?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            conn.setAutoCommit(false);
            preStm = conn.prepareStatement(sql);
            for (RoomDTO dto : cart.getCart().values()) {
                preStm.setInt(1, orderID);
                preStm.setInt(2, dto.getRoomID());
                preStm.setInt(3, dto.getAmountRoom());
                preStm.setString(4, dto.getCheckin());
                preStm.setString(5, dto.getCheckout());
                preStm.setInt(6, 1);
                preStm.setInt(7, dto.getHotelID());
                preStm.setFloat(8, dto.getPrice() * dto.getAmountNight() * dto.getAmountRoom());
                preStm.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
            check = false;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<OrderDetailDTO> loadRoomIDinOrderDetail() throws Exception {
        List<OrderDetailDTO> listRoom = null;
        int roomID, hotelID, orderID, quantity;
        String checkIn, checkOut;
        float subPrice;
        OrderDetailDTO dto = null;
        try {
            String sql = "Select RoomID, tblOrderDetail.CheckIn, tblOrderDetail.CheckOut, tblOrderDetail.HotelID, tblOrderDetail.OrderID, tblOrderDetail.Quantity, tblOrderDetail.SubPrice"
                    + " From tblOrderDetail"
                    + " Where Status = 1"
                    + " Group by tblOrderDetail.RoomID, tblOrderDetail.CheckIn, tblOrderDetail.CheckOut, tblOrderDetail.HotelID, tblOrderDetail.OrderID, tblOrderDetail.Quantity, tblOrderDetail.SubPrice";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            listRoom = new ArrayList<>();
            while (rs.next()) {
                roomID = rs.getInt("RoomID");
                checkIn = rs.getString("CheckIn");
                checkOut = rs.getString("CheckOut");
                hotelID = rs.getInt("HotelID");
                orderID = rs.getInt("OrderID");
                quantity = rs.getInt("Quantity");
                subPrice = rs.getFloat("SubPrice");
                dto = new OrderDetailDTO(orderID, roomID, quantity, hotelID, checkIn, checkOut, subPrice);
                listRoom.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listRoom;
    }

    public int getAmountRoomOrder(int roomID, String checkin, String checkout) throws Exception {
        int amountOrder = -1;
        try {
            String sql = "Select SUM(tblOrderDetail.Quantity) as AmountRoom"
                    + " From tblOrderDetail"
                    + " Where tblOrderDetail.OrderDetailID in("
                    + " Select tblOrderDetail.OrderDetailID"
                    + " From tblOrderDetail"
                    + " Where tblOrderDetail.RoomID = ?"
                    + " EXCEPT"
                    + " Select tblOrderDetail.OrderDetailID"
                    + " From tblOrderDetail"
                    + " Where(tblOrderDetail.CheckOut < (?)) or (tblOrderDetail.CheckIn > (?)) )";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, roomID);
            preStm.setString(2, checkin);
            preStm.setString(3, checkout);
            rs = preStm.executeQuery();
            if (rs.next()) {
                amountOrder = rs.getInt("AmountRoom");
            }
        } finally {
            closeConnection();
        }
        return amountOrder;
    }

    public List<OrderDetailDTO> loadOrderDetailByOrderID(int orderID) throws Exception {
        List<OrderDetailDTO> listRoom = null;
        int roomID, hotelID, quantity, detailID;
        String checkIn, checkOut, hotelName;
        float subPrice;
        int status;
        OrderDetailDTO dto = null;
        try {
            String sql = "Select tblOrderDetail.RoomID, tblOrderDetail.Quantity, tblOrderDetail.CheckIn, tblOrderDetail.CheckOut, tblOrderDetail.OrderDetailID, tblOrderDetail.SubPrice, tblOrderDetail.Status, tblHotel.HotelName, tblOrderDetail.HotelID"
                    + " From tblOrderDetail, tblHotel"
                    + " Where tblOrderDetail.OrderID = ? and tblHotel.HotelID = tblOrderDetail.HotelID and tblOrderDetail.Status = 1";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, orderID);
            rs = preStm.executeQuery();
            listRoom = new ArrayList<>();
            while (rs.next()) {
                roomID = rs.getInt("RoomID");
                checkIn = rs.getString("CheckIn");
                checkOut = rs.getString("CheckOut");
                quantity = rs.getInt("Quantity");
                subPrice = rs.getFloat("SubPrice");
                detailID = rs.getInt("OrderDetailID");
                status = rs.getInt("Status");
                hotelName = rs.getString("HotelName");
                hotelID = rs.getInt("HotelID");
                dto = new OrderDetailDTO(detailID, roomID, quantity, status, checkIn, checkOut, hotelName, subPrice);
                dto.setOrderID(orderID);
                dto.setHotelID(hotelID);
                listRoom.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listRoom;
    }

    public boolean updateStatus(int orderDetailID) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblOrderDetail set Status = 3 Where OrderDetailID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, orderDetailID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
