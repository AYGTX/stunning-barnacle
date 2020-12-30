package com.gtx.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

// the name of the db table is users
@Entity(name = "users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 8951994251593378324L;


    @Id
    @GeneratedValue

    private long id;

    //required
    @Column(nullable = false)
    private String userId;
    //required and max length of varchar will be 25

    @Column(nullable = false, length = 25)
    private String firstName;

    @Column(nullable = false, length = 25)
    private String lastName;

    @Column(nullable = false, length = 180, unique = true)
    private String email;

    private String encyptedPassword;

    private String getEmailVerificationToken;

    @Column(nullable = false)
    private boolean emailVerificationStatus = false;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncyptedPassword() {
        return encyptedPassword;
    }

    public void setEncyptedPassword(String encyptedPassword) {
        this.encyptedPassword = encyptedPassword;
    }

    public String getGetEmailVerificationToken() {
        return getEmailVerificationToken;
    }

    public void setGetEmailVerificationToken(String getEmailVerificationToken) {
        this.getEmailVerificationToken = getEmailVerificationToken;
    }

    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }
}
