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
import khanh.dtos.HotelDTO;

/**
 *
 * @author buido
 */
public class HotelDAO {

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

    public HotelDAO() {
    }

    public List<HotelDTO> searchByLikeName(String search, String checkin, String checkout) throws Exception {
        List<HotelDTO> result = null;
        HotelDTO dto = null;
        int hotelID, areaID;
        String hotelName, address, phone, photo, des;
        try {
            String sql = " Select tblHotel.HotelID,tblHotel.HotelID, HotelName, Address, Phone, tblHotel.Photo, AreaID, tblHotel.Description"
                    + " From tblHotel, tblRoom"
                    + " Where tblHotel.Status=1 and tblHotel.HotelName LIKE ? and tblHotel.HotelID = tblRoom.HotelID and tblRoom.RoomID in("
                    + " Select tblRoom.RoomID"
                    + " FROM tblRoom, tblOrderDetail, tblHotel"
                    + " Where tblHotel.HotelID = tblRoom.HotelID AND tblOrderDetail.RoomID = tblRoom.RoomID and ((tblOrderDetail.CheckOut between (?) and (?) or tblOrderDetail.CheckIn between (?) and (?)) or(tblOrderDetail.CheckIn < (?) and tblOrderDetail.CheckOut > (?)))"
                    + " GROUP BY tblRoom.RoomID"
                    + " HAVING SUM(tblRoom.RoomQuantity) - SUM(tblOrderDetail.Quantity) > 1"
                    + " UNION("
                    + " Select tblRoom.RoomID"
                    + " FROM tblRoom, tblHotel"
                    + " Where tblHotel.HotelID = tblRoom.HotelID"
                    + " GROUP BY tblRoom.RoomID"
                    + " EXCEPT"
                    + " SELECT tblOrderDetail.RoomID"
                    + " FROM tblOrderDetail, tblRoom, tblHotel"
                    + " WHERE tblOrderDetail.RoomID = tblRoom.RoomID AND tblOrderDetail.HotelID = tblHotel.HotelID and ((tblOrderDetail.CheckOut between (?) and (?) or tblOrderDetail.CheckIn between (?) and (?)) or(tblOrderDetail.CheckIn < (?) and tblOrderDetail.CheckOut > (?)))))"
                    + " Group by tblHotel.HotelID,tblHotel.HotelID, HotelName, Address, Phone, tblHotel.Photo, AreaID, tblHotel.Description";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            preStm.setString(2, checkin);
            preStm.setString(3, checkout);
            preStm.setString(4, checkin);
            preStm.setString(5, checkout);
            preStm.setString(6, checkin);
            preStm.setString(7, checkout);
            preStm.setString(8, checkin);
            preStm.setString(9, checkout);
            preStm.setString(10, checkin);
            preStm.setString(11, checkout);
            preStm.setString(12, checkin);
            preStm.setString(13, checkout);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                hotelID = rs.getInt("HotelID");
                hotelName = rs.getString("HotelName");
                address = rs.getString("Address");
                phone = rs.getString("Phone");
                photo = rs.getString("Photo");
                areaID = rs.getInt("AreaID");
                des = rs.getString("Description");
                dto = new HotelDTO(hotelID, areaID, hotelName, address, phone, photo, des);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<HotelDTO> searchTotalRoom(List<HotelDTO> listHotel) throws Exception {
        int total;
        try {
            String sql = "select SUM(tblRoom.RoomQuantity) as TotalRoom from tblRoom where tblRoom.HotelID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            for (HotelDTO hotelDTO : listHotel) {
                preStm.setInt(1, hotelDTO.getHotelID());
                rs = preStm.executeQuery();
                if (rs.next()) {
                    total = rs.getInt("TotalRoom");
                    hotelDTO.setTotalRoom(total);
                }
            }
        } finally {
            closeConnection();
        }
        return listHotel;
    }

    public List<HotelDTO> searchHotelByRequire(int areaID, int amount, String checkIn, String checkOut) throws Exception {
        List<HotelDTO> listHotel = null;
        HotelDTO dto = null;
        int hotelID;
        String hotelName, address, phone, photo, des;
        try {
            String sql = "( Select tblHotel.HotelID,tblHotel.HotelID, HotelName, Address, Phone, tblHotel.Photo, tblHotel.AreaID, tblHotel.Description"
                    + " From tblHotel, tblRoom, tblArea"
                    + " Where tblHotel.Status=1 and tblArea.AreaID = ? and tblArea.AreaID = tblHotel.AreaID and tblHotel.HotelID = tblRoom.HotelID and tblRoom.RoomID in("
                    + " Select tblRoom.RoomID"
                    + " FROM tblRoom, tblOrderDetail, tblHotel"
                    + " Where tblHotel.HotelID = tblRoom.HotelID AND tblOrderDetail.RoomID = tblRoom.RoomID and ((tblOrderDetail.CheckOut between (?) and (?) or tblOrderDetail.CheckIn between (?) and (?)) or(tblOrderDetail.CheckIn < (?) and tblOrderDetail.CheckOut > (?)))"
                    + " GROUP BY tblRoom.RoomID"
                    + " HAVING SUM(tblRoom.RoomQuantity) - SUM(tblOrderDetail.Quantity) > ?"
                    + " UNION("
                    + " Select tblRoom.RoomID"
                    + " FROM tblRoom, tblHotel"
                    + " Where tblHotel.HotelID = tblRoom.HotelID"
                    + " GROUP BY tblRoom.RoomID"
                    + " EXCEPT"
                    + " SELECT tblOrderDetail.RoomID"
                    + " FROM tblOrderDetail, tblRoom, tblHotel"
                    + " WHERE tblOrderDetail.RoomID = tblRoom.RoomID AND tblOrderDetail.HotelID = tblHotel.HotelID  and  ((tblOrderDetail.CheckOut between (?) and (?) or tblOrderDetail.CheckIn between (?) and (?)) or(tblOrderDetail.CheckIn < (?) and tblOrderDetail.CheckOut > (?)))))"
                    + " group by tblHotel.HotelID,tblHotel.HotelID, HotelName, Address, Phone, tblHotel.Photo,tblHotel.AreaID, tblHotel.Description)"
                    + " UNION ("
                    + " Select tblHotel.HotelID,tblHotel.HotelID, HotelName, Address, Phone, tblHotel.Photo, tblHotel.AreaID, tblHotel.Description"
                    + " From tblHotel, tblArea Where tblHotel.Status=1 and tblArea.AreaID = ? and tblArea.AreaID = tblHotel.AreaID"
                    + " EXCEPT"
                    + " select  tblHotel.HotelID,tblHotel.HotelID, HotelName, Address, Phone, tblHotel.Photo, tblHotel.AreaID, tblHotel.Description"
                    + " from tblHotel, tblOrderDetail"
                    + " where tblHotel.HotelID = tblOrderDetail.HotelID and ((tblOrderDetail.CheckOut between (?) and (?) or tblOrderDetail.CheckIn between (?) and (?)) or(tblOrderDetail.CheckIn < (?) and tblOrderDetail.CheckOut > (?)))"
                    + " group by tblHotel.HotelID,tblHotel.HotelID, HotelName, Address, Phone, tblHotel.Photo, tblHotel.AreaID, tblHotel.Description )";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, areaID);
            preStm.setString(2, checkIn);
            preStm.setString(3, checkOut);
            preStm.setString(4, checkIn);
            preStm.setString(5, checkOut);
            preStm.setString(6, checkIn);
            preStm.setString(7, checkOut);
            preStm.setInt(8, amount);
            preStm.setString(9, checkIn);
            preStm.setString(10, checkOut);
            preStm.setString(11, checkIn);
            preStm.setString(12, checkOut);
            preStm.setString(13, checkIn);
            preStm.setString(14, checkOut);
            preStm.setInt(15, areaID);
            preStm.setString(16, checkIn);
            preStm.setString(17, checkOut);
            preStm.setString(18, checkIn);
            preStm.setString(19, checkOut);
            preStm.setString(20, checkIn);
            preStm.setString(21, checkOut);
            rs = preStm.executeQuery();
            listHotel = new ArrayList<>();
            while (rs.next()) {
                hotelID = rs.getInt("HotelID");
                hotelName = rs.getString("HotelName");
                address = rs.getString("Address");
                phone = rs.getString("Phone");
                photo = rs.getString("Photo");
                areaID = rs.getInt("AreaID");
                des = rs.getString("Description");
                dto = new HotelDTO(hotelID, areaID, hotelName, address, phone, photo, des);
                listHotel.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listHotel;
    }

    public HotelDTO findByPK(int hotelID) throws Exception {
        HotelDTO dto = null;
        int areaID, total;
        String hotelName, address, phone, photo, des, createDate;
        try {
            String sql = "select HotelName, Address, Phone, tblHotel.Photo, AreaID, tblHotel.Description, SUM(tblRoom.RoomQuantity) as total"
                    + " from tblHotel, tblRoom"
                    + " where tblHotel.HotelID = ? and tblHotel.Status = 1 and tblHotel.HotelID = tblRoom.HotelID and tblRoom.RoomQuantity >0"
                    + " group by tblHotel.HotelID, HotelName, Address, Phone, tblHotel.Photo, AreaID, tblHotel.Status, tblHotel.Description, CreateDate";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, hotelID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                hotelName = rs.getString("HotelName");
                address = rs.getString("Address");
                phone = rs.getString("Phone");
                photo = rs.getString("Photo");
                areaID = rs.getInt("AreaID");
                des = rs.getString("Description");
                total = rs.getInt("total");
                dto = new HotelDTO(hotelID, areaID, hotelName, address, phone, photo, des);
                dto.setTotalRoom(total);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

}
