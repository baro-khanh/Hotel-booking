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
import khanh.dtos.AreaDTO;

/**
 *
 * @author buido
 */
public class AreaDAO {

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

    public AreaDAO() {
    }

    public List<AreaDTO> loadArea() throws Exception {
        List<AreaDTO> listArea = null;
        int areaID;
        String areaName;
        AreaDTO dto = null;
        try {
            String sql = "Select AreaID, AreaName From tblArea";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            listArea = new ArrayList<>();
            while (rs.next()) {
                areaID = rs.getInt("AreaID");
                areaName = rs.getString("AreaName");
                dto = new AreaDTO(areaID, areaName);
                listArea.add(dto);
            }
        } finally {
            closeConnection();
        }
        return listArea;
    }
}
