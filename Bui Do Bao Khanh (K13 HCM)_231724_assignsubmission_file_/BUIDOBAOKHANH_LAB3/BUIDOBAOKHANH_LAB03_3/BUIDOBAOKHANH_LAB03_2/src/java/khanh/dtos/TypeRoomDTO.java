/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanh.dtos;

/**
 *
 * @author buido
 */
public class TypeRoomDTO {

    private int typeID, tankage;
    private float price;
    private String typeName;

    public TypeRoomDTO(int typeID, int tankage, float price, String typeName) {
        this.typeID = typeID;
        this.tankage = tankage;
        this.price = price;
        this.typeName = typeName;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getTankage() {
        return tankage;
    }

    public void setTankage(int tankage) {
        this.tankage = tankage;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}
