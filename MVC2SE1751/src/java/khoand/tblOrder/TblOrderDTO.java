/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.tblOrder;

import khoand.registration.*;
import java.sql.Date;

/**
 *
 * @author KHOA
 */
public class TblOrderDTO {
    private String id;
    private String name;
    private String address;
    private Date date;
    private float total;

    public TblOrderDTO() {
    }

    public TblOrderDTO(String id, String name, String address, Date date, float total) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.date = date;
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
}
