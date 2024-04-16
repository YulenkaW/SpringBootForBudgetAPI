package com.example.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PasswordResetToken {
    @Id
    private String id;
    private String userId;
        private String token;
    private Date expirationDate;
    

    public PasswordResetToken() {
    }


    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
        public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId; 
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public Date getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
    
    public boolean isExpired() {
        return new Date().after(this.expirationDate);
    }

    
}
