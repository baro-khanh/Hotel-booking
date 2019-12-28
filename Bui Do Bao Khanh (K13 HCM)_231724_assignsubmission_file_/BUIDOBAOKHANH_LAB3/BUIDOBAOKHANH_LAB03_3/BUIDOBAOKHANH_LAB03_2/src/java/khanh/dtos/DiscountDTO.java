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
public class DiscountDTO {

    private String discountCode, startDate, endDate, des;
    private boolean status;
    private int decrease;

    public DiscountDTO(String discountCode, String startDate, String endDate, String des, int decrease) {
        this.discountCode = discountCode;
        this.startDate = startDate;
        this.endDate = endDate;
        this.des = des;
        this.decrease = decrease;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getDecrease() {
        return decrease;
    }

    public void setDecrease(int decrease) {
        this.decrease = decrease;
    }

}
