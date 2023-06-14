/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoand.registration;

import java.io.Serializable;

/**
 *
 * @author KHOA
 */
public class RegistrationCreateError implements Serializable{
    private String usernameLengthError;
    private String passwordLengthError;
    private String fullnameLengthError;
    private String confirmNotMatchedError;
    private String usernameisExit;
    private String passwordNull;

    public String getPasswordNull() {
        return passwordNull;
    }

    public void setPasswordNull(String passwordNull) {
        this.passwordNull = passwordNull;
    }

    public RegistrationCreateError() {
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    public String getConfirmNotMatchedError() {
        return confirmNotMatchedError;
    }

    public void setConfirmNotMatchedError(String confirmNotMatchedError) {
        this.confirmNotMatchedError = confirmNotMatchedError;
    }

    public String getUsernameisExit() {
        return usernameisExit;
    }

    public void setUsernameisExit(String usernameisExit) {
        this.usernameisExit = usernameisExit;
    }
    
}
