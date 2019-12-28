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
import khanh.dtos.FeedBackDTO;

/**
 *
 * @author buido
 */
public class FeeadBackDAO {

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

    public FeeadBackDAO() {
    }

    public boolean insertFeedBack(FeedBackDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into tblFeedBack(HotelID, Email, Feedback) values(?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getHotelID());
            preStm.setString(2, dto.getEmail());
            preStm.setString(3, dto.getFeedback());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<FeedBackDTO> loadFeedBackByHotelID(int HotelID) throws Exception {
        List<FeedBackDTO> result = null;
        String email, feedback;
        FeedBackDTO dto = null;
        try {
            String sql = "Select Email, Feedback From tblFeedback Where HotelID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, HotelID);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                email = rs.getString("Email");
                feedback = rs.getString("Feedback");
                dto = new FeedBackDTO(HotelID, email, feedback);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

}
