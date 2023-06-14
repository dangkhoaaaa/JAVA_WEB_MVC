/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import khoand.tblProduct.TblProductDTO;

/**
 *
 * @author KHOA
 */
public class CartObjectForStore implements Serializable{
     private Map<String, Integer> items;
    //phan biet cai gio, ngan chua do, va do
    public Map<String, Integer> getItems() {
        return items;
    }
    
    public boolean addItemToCart(String id, int quantily){
        boolean result = false;
     
      //1. check item's item is existed -//1 check dâta validation
      if(id==null){
         return result;
      }
      if(id.trim().isEmpty()){
         return result;
      
      }
   
      if(quantily<= 0){
         return result;
      }
      //2 when item is existed, checking existed items - (check ngan chua do)
      if(this.items == null){
      this.items = new HashMap<>();
      }
      //3 WWhen items has existed, checking exxited id
      //int quantily=1;
      if(this.items.containsKey(id)){
           int curentQuantiyu = this.items.get(id);
           
          quantily = curentQuantiyu+quantily;
      }
      //4 update cart items
      this.items.put(id, quantily);
      result = true;
      return result;
    }
    
    public boolean removeItemFromCart(String id, int quantily){
        boolean result = false;
    //1. check item's item is existed -//1 check dâta validation
      if(id==null){
         return result;
      }
      if(id.trim().isEmpty()){
         return result;
      
      }
   
      if(quantily<= 0){
         return result;
      }
    //2 check items
    if(this.items==null){
       return result;
    }
    
    //3 check item
    if(!this.items.containsKey(id)){
        return result;
    
    }//item has not existed
    //4. decrease quantity
    int curentQuantiyu = this.items.get(id);
    if(curentQuantiyu>=quantily){
    quantily = curentQuantiyu-quantily;
    }// current larger than quantity
    
    //5. update cart items
    if(quantily==0){
     this.items.remove(id);
       if(this.items.isEmpty()){
         this.items = null;
       }
    } else{
    this.items.put(id, quantily);
    
    }
    result = true;
    return result;
    }
}
