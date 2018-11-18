package com.nancy.recyclerwithviewmodel.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Observable;

public class UserList {

    @SerializedName("employees")
    @Expose
    private List<User> employees = null;

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }
}
