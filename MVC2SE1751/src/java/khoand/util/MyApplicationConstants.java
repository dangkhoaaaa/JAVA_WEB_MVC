/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.util;

/**
 *
 * @author KHOA
 */
public class MyApplicationConstants {
    public class DispatchFeature{
       public static final String LOGIN_PAGE ="loginPage";
    public static final String LOGIN_CONTROLLER ="loginController";
    }
    
    public class LoginFeature{
      public static final String INVALID_PAGE = "invalidPage";
        public static final String SEARCH_ACTION = "searchPage";
        public static final String LOGOUT_PAGE ="logOutController";
    }
    public class SearchFeature{
     public static final String SEARCH_CONTROL = "searchController";
     public static final String DETELE_CONTROL = "deleteController";
    public static final String UPDATE_CONTROL = "updateController";
    }
    public class ShoppingFeature{
          public static final String SHOPPING_PAGE = "shoppingPagehtml";
        //public static final String STORE_CART_CONTROLLER = "shoppingController";
        public static final String ADD_ITEM_TO_CART_CONTROLLER = "addItemToCartController";
        public static final String VIEW_CART_PAGE = "viewCart";
        public static final String REMOVE_CART_CONTROLLER = "removeController";
       
    }
    
    public class StoreFeature{
         
        public static final String LOAD_VIEW_STORE = "loadViewOnlineShopping";
        public static final String ADD_ITEM_TO_STORE_CONTROLLER = "addToCartStore";
        public static final String VIEW_CART_PAGE_STORE = "shoppingPagejsp";
        public static final String REMOVE_CART_CONTROLLER = "removeToCartStore";

    }
    
    public class CreateAccountFeature{
            public static final String INSERT_ERROR_PAGE = "createAccountErrPage";
        public static final String CREATE_NEW_ACCOUNT_CONTROLLER = "createAccountController";
    }
    
}
