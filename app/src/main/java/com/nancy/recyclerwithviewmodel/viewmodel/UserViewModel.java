package com.nancy.recyclerwithviewmodel.viewmodel;

import com.nancy.recyclerwithviewmodel.model.User;

public class UserViewModel {

    public String name;
    public String title;

    public UserViewModel(User model) {
        this.name = model.getName();
        this.title = model.getTitle();

    }

    public UserViewModel() {
    }
}
