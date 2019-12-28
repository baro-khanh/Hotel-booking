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
import khanh.db.MyConnection;
import khanh.dtos.AccountDTO;

/**
 *
 * @author buido
 */
public class AccountDAO {

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

    public AccountDAO() {
    }

    public int checkLogin(String email, String password) throws Exception {
        int check = -1;
        try {
            String sql = "Select RoleID From tblAccount Where Email = ? and Password = ? and Status = 'true'";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                check = rs.getInt("RoleID");
            }
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean insertUser(AccountDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert into tblAccount(Email, Phone, Fullname, Status, CreateDate, Password, RoleID) values(?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getEmail());
            preStm.setString(2, dto.getPhone());
            preStm.setString(3, dto.getFullname());
            preStm.setBoolean(4, dto.isStatus());
            preStm.setString(5, dto.getCreateDate());
            preStm.setString(6, dto.getPassword());
            preStm.setInt(7, dto.getRoleID());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public AccountDTO findAccount(String email) throws Exception {
        AccountDTO dto = null;
        String fullname, phone, createDate;
        int roleID;
        try {
            String sql = "Select tblAccount.CreateDate, tblAccount.Fullname, tblAccount.Phone, tblAccount.RoleID"
                    + " From tblAccount"
                    + " Where tblAccount.Status = 1 and tblAccount.RoleID != 1 and tblAccount.Email = ?";
            //admin khong co quyen mua
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            rs = preStm.executeQuery();
            if (rs.next()) {
                fullname = rs.getString("Fullname");
                phone = rs.getString("Phone");
                createDate = rs.getString("CreateDate");
                roleID = rs.getInt("RoleID");
                dto = new AccountDTO(email, fullname, phone, createDate, true, roleID);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

}
