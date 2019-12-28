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
import java.sql.Timestamp;
import khanh.db.MyConnection;
import khanh.dtos.DiscountDTO;

/**
 *
 * @author buido
 */
public class DiscountDAO {

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

    public DiscountDAO() {
    }
    
    

    public DiscountDTO findDiscount(String discountCode) throws Exception {
        DiscountDTO dto = null;
        String startDate, endDate, des;
        String getDate = new Timestamp(System.currentTimeMillis()) + "";
        int decrease;
        try {
            String sql = "Select tblDiscount.DiscountCode, tblDiscount.Decrease, Description, tblDiscount.EndDate, tblDiscount.StartDate"
                    + " From tblDiscount"
                    + " Where ? between tblDiscount.StartDate and tblDiscount.EndDate and tblDiscount.Status = 1 and tblDiscount.DiscountCode = ?";
            //những discoun còn trong tgian áp dụng
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, getDate);
            preStm.setString(2, discountCode);
            rs = preStm.executeQuery();
            if (rs.next()) {
                startDate = rs.getString("StartDate");
                endDate = rs.getString("EndDate");
                des = rs.getString("Description");
                decrease = rs.getInt("Decrease");
                dto = new DiscountDTO(discountCode, startDate, endDate, des, decrease);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
