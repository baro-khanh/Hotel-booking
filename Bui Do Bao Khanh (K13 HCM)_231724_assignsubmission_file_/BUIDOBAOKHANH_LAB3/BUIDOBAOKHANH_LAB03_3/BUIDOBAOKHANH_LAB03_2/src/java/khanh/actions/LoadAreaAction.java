/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.actions;

import com.opensymphony.xwork2.ActionContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import khanh.dtos.AreaDTO;
import khanh.models.AreaDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author buido
 */
public class LoadAreaAction {

    private static final Logger LOGGER = Logger.getLogger(LoadAreaAction.class);

    private static final String SUCCESS = "success";
    private Map<Integer, String> listArea;

    public LoadAreaAction() {
    }

    public Map<Integer, String> getListArea() {
        return listArea;
    }

    public void setListArea(Map<Integer, String> listArea) {
        this.listArea = listArea;
    }

    public String execute() {
        try {
            AreaDAO daoArea = new AreaDAO();
            Map session = ActionContext.getContext().getSession();
            listArea = new HashMap<Integer, String>();
            List<AreaDTO> result = daoArea.loadArea();
            for (AreaDTO areaDTO : result) {
                listArea.put(areaDTO.getAreaID(), areaDTO.getAreaName());
            }
            session.put("LISTAREA", listArea);
        } catch (Exception e) {
            LOGGER.error("ERROR AT LOADAREAACTION: "+e.getMessage());
        }
        return SUCCESS;
    }

}
