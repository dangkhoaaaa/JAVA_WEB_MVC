/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.tblProduct;

import khoand.registration.*;
import java.io.Serializable;

/**
 *
 * @author KHOA
 */
public class TblProductDTO implements Serializable{
    private int sku;
    private String name;
    private String discription;
    private int quantity;
    private float price;
    private boolean status;

    public TblProductDTO() {
    }

    public TblProductDTO(int sku, String name, String discription, int quantity, float price, boolean status) {
        this.sku = sku;
        this.name = name;
        this.discription = discription;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
    }

    public TblProductDTO(String name, String discription, int price) {
        
        this.name = name;
        this.discription = discription;
        
        this.price = price;
       
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
  
     
   
    
}
