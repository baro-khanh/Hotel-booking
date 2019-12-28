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
import khanh.dtos.TypeRoomDTO;

/**
 *
 * @author buido
 */
public class TypeRoomDAO {

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

    public TypeRoomDAO() {
    }

    public List<TypeRoomDTO> loadTypeRoomByHotel(int hotelID, int amountRoom) throws Exception {
        List<TypeRoomDTO> result = null;
        int typeID, tankage;
        float price;
        String typeName;
        TypeRoomDTO dto = null;
        try {
            String sql = "Select tblTypeRoom.TypeRoomID, tblTypeRoom.TypeName, tblTypeRoom.Tankage, tblTypeRoom.Price"
                    + " From tblTypeRoom, tblRoom, tblHotel"
                    + " Where tblHotel.HotelID = ? and tblRoom.Status=1 and tblRoom.RoomQuantity >= ? and tblRoom.HotelID = tblHotel.HotelID and tblRoom.TypeRoomID = tblTypeRoom.TypeRoomID"
                    + " Group by tblTypeRoom.TypeRoomID, tblTypeRoom.TypeName, tblTypeRoom.Tankage, tblTypeRoom.Price";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, hotelID);
            preStm.setInt(2, amountRoom);
            result = new ArrayList<>();
            rs = preStm.executeQuery();
            while (rs.next()) {
                typeID = rs.getInt("TypeRoomID");
                tankage = rs.getInt("Tankage");
                price = rs.getFloat("Price");
                typeName = rs.getString("TypeName");
                dto = new TypeRoomDTO(typeID, tankage, price, typeName);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
