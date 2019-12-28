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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import khanh.db.MyConnection;
import khanh.dtos.RoomDTO;
import khanh.dtos.ShoppingCart;

/**
 *
 * @author buido
 */
public class RoomDAO {

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

    public RoomDAO() {
    }

    public List<RoomDTO> findListRoomByHotel(int hotelID, int amountRoom, int roomTypeID, String checkIn, String checkOut) throws Exception {
        List<RoomDTO> listRoom = null;
        int roomID, quantity;
        String des, roomName, photo;
        float price;
        RoomDTO dto = null;
        try {
            String sql = "Select  tblRoom.Photo, tblRoom.RoomName, tblRoom.RoomQuantity, tblTypeRoom.Price, tblRoom.RoomID, tblRoom.Description"
                    + " From tblRoom, tblHotel, tblTypeRoom"
                    + " Where tblHotel.HotelID=? and tblTypeRoom.TypeRoomID=? and tblRoom.TypeRoomID=tblTypeRoom.TypeRoomID and tblRoom.HotelID=tblHotel.HotelID"
                    + " EXCEPT "
                    + "select tblRoom.Photo, tblRoom.RoomName, tblRoom.RoomQuantity, tblTypeRoom.Price, tblRoom.RoomID, tblRoom.Description"
                    + " FROM tblRoom, tblOrderDetail, tblHotel, tblTypeRoom"
                    + " where tblHotel.HotelID=? and tblOrderDetail.HotelID =tblHotel.HotelID and tblTypeRoom.TypeRoomID = ? AND tblOrderDetail.RoomID = tblRoom.RoomID and  ((tblOrderDetail.CheckOut between (?) and (?) or tblOrderDetail.CheckIn between (?) and (?)) or(tblOrderDetail.CheckIn < (?) and tblOrderDetail.CheckOut > (?)))"
                    + " GROUP BY tblRoom.Photo, tblRoom.RoomName, tblRoom.RoomQuantity, tblTypeRoom.Price, tblRoom.RoomID, tblRoom.Description"
                    + " HAVING SUM(tblRoom.RoomQuantity) - SUM(tblOrderDetail.Quantity) < ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, hotelID);
            preStm.setInt(2, roomTypeID);
            preStm.setInt(3, hotelID);
            preStm.setInt(4, roomTypeID);
            preStm.setString(5, checkIn);
            preStm.setString(6, checkOut);
            preStm.setString(7, checkIn);
            preStm.setString(8, checkOut);
            preStm.setString(9, checkIn);
            preStm.setString(10, checkOut);
            preStm.setInt(11, amountRoom);
            rs = preStm.executeQuery();
            listRoom = new ArrayList<>();
            while (rs.next()) {
                des = rs.getString("Description");
                photo = rs.getString("Photo");
                roomID = rs.getInt("RoomID");
                roomName = rs.getString("RoomName");
                quantity = rs.getInt("RoomQuantity");
                price = rs.getFloat("Price");
                dto = new RoomDTO(roomID, quantity, roomTypeID, hotelID, des, roomName, photo, price);
                listRoom.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listRoom;
    }

    public RoomDTO findRoom(int roomID, String checkIn, String checkOut) throws Exception {
        int quantity, hotelID;
        String roomName, photo, hotelName;
        float price;
        RoomDTO dto = null;
        try {
            String sql = "Select tblRoom.RoomName, tblRoom.Photo, tblRoom.RoomQuantity,tblHotel.HotelID, tblHotel.HotelName, tblTypeRoom.Price"
                    + " From tblRoom, tblTypeRoom, tblHotel "
                    + "Where tblRoom.RoomID = ? and tblTypeRoom.TypeRoomID = tblRoom.TypeRoomID and tblHotel.HotelID = tblRoom.HotelID";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, roomID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                photo = rs.getString("Photo");
                roomName = rs.getString("RoomName");
                quantity = rs.getInt("RoomQuantity");
                price = rs.getFloat("Price");
                hotelID = rs.getInt("HotelID");
                hotelName = rs.getString("HotelName");
                dto = new RoomDTO(roomID, quantity, roomName, photo, checkIn, checkOut, price);
                dto.setHotelID(hotelID);
                dto.setHotelName(hotelName);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public long countNight(String checkIn, String checkOut) throws ParseException {
        Date date1 = null;
        Date date2 = null;
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date1 = simpleDateFormat.parse(checkIn);
        date2 = simpleDateFormat.parse(checkOut);

        long getDiff = date2.getTime() - date1.getTime();
        long night = TimeUnit.MILLISECONDS.toDays(getDiff);
        return night;
    }

    public List<RoomDTO> loadAllRoom() throws Exception {
        List<RoomDTO> listRoom = null;
        int roomID, quantity;
        String roomName;
        RoomDTO dto = null;
        try {
            String sql = "Select tblRoom.RoomID, tblRoom.RoomName, tblRoom.RoomQuantity"
                    + " From tblRoom"
                    + " Where tblRoom.Status = 1 ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            listRoom = new ArrayList<>();
            while (rs.next()) {
                roomID = rs.getInt("RoomID");
                roomName = rs.getString("RoomName");
                quantity = rs.getInt("RoomQuantity");
                dto = new RoomDTO(roomID, quantity, roomName);
                listRoom.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listRoom;
    }

    public int checkExit(int roomID) throws Exception {
        int checkExits = -1;
        try {
            String sql = "Select tblRoom.RoomName From tblRoom Where tblRoom.RoomID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, roomID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                checkExits++;
            }
        } finally {
            closeConnection();
        }
        return checkExits;
    }

    public String checkCartRoomExist(ShoppingCart cart) throws Exception {
        String check = "";
        int checkExist;
        for (RoomDTO dto : cart.getCart().values()) {
            checkExist = checkExit(dto.getRoomID());
            if (checkExist < 0) {
                check = dto.getRoomName();
                break;
            }
        }
        return check;
    }

    public String checkQuantityNoOrderRoom(int roomID, int amountRoom) throws Exception {
        String roomName = "";
        try {
            String sql = "select tblRoom.RoomName"
                    + " from tblRoom"
                    + " where tblRoom.RoomID = ?"
                    + " group by tblRoom.RoomName, tblRoom.RoomQuantity"
                    + " having (tblRoom.RoomQuantity - ? < 0)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, roomID);
            preStm.setInt(2, amountRoom);
            rs = preStm.executeQuery();
            if (rs.next()) {
                roomName = rs.getString("RoomName");
                
            }
        } finally {
            closeConnection();
        }
        return roomName;
    }
    
    public int getTotalRoom(int roomID) throws Exception{
        int totalRoom = -1;
        try {
            String sql = "Select tblRoom.RoomQuantity From tblRoom Where RoomID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, roomID);
            rs = preStm.executeQuery();
            if(rs.next()){
                totalRoom = rs.getInt("RoomQuantity");
            }
        } finally {
            closeConnection();
        }
        return totalRoom;
    }

}
