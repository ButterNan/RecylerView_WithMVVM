package com.nancy.recyclerwithviewmodel.viewmodel;

import com.nancy.recyclerwithviewmodel.model.Flower;

public class UserViewModel {

    public String name;
    public String category;

    public UserViewModel(Flower model) {
        this.name = model.getName();
        this.category = model.getCategory();

    }

    public UserViewModel() {
    }
}
