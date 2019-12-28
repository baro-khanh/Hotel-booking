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
import khanh.dtos.OrderDTO;

/**
 *
 * @author buido
 */
public class OrderDAO {

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

    public OrderDAO() {
    }

    public boolean insertOrder(OrderDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into tblOrder(Email, CreateDate, Total) values(?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getEmail());
            preStm.setString(2, dto.getCreateDate());
            preStm.setFloat(3, dto.getTotal());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int findPKOrder() throws Exception {
        int orderID = -1;
        try {
            String sql = "Select MAX(tblOrder.OrderID) as LastID  From tblOrder";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                orderID = rs.getInt("LastID");
            }
        } finally {
            closeConnection();
        }
        return orderID;
    }

    public boolean updateDiscount(int orderID, String discount, float total) throws Exception {
        boolean check = false;
        try {
            String sql = "Update tblOrder set DiscountCode = ?, Total = ? Where OrderID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, discount);
            preStm.setFloat(2, total);
            preStm.setInt(3, orderID);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<OrderDTO> searchOrderByDate(String date, String email) throws Exception {
        List<OrderDTO> result = null;
        int orderID;
        String discount, createDate;
        float total;
        OrderDTO dto = null;
        try {
            String sql = "Select tblOrder.OrderID, tblOrder.DiscountCode, tblOrder.Total, tblOrder.CreateDate"
                    + " From tblOrder"
                    + " Where tblOrder.CreateDate = ? and Email = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, date);
            preStm.setString(2, email);
            result = new ArrayList<>();
            rs = preStm.executeQuery();
            while (rs.next()) {
                orderID = rs.getInt("OrderID");
                discount = rs.getString("DiscountCode");
                createDate = rs.getString("CreateDate");
                total = rs.getFloat("Total");
                dto = new OrderDTO(email, discount, createDate, total);
                dto.setOrderID(orderID);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
