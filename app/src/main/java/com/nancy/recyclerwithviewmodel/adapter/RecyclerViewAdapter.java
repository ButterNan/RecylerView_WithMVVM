package com.nancy.recyclerwithviewmodel.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nancy.recyclerwithviewmodel.R;
import com.nancy.recyclerwithviewmodel.model.User;
import com.nancy.recyclerwithviewmodel.databinding.ItemRowBinding;
import com.nancy.recyclerwithviewmodel.viewmodel.UserViewModel;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    List<UserViewModel> user;
    private LayoutInflater layout;

    public RecyclerViewAdapter(List<UserViewModel> user) {
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layout==null)
        {
            layout = LayoutInflater.from(parent.getContext());
                    //.inflate(R.layout.item_row,parent,false);

        }
        ItemRowBinding binding = ItemRowBinding.inflate(layout,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserViewModel userModel = user.get(position);
        holder.bind(userModel);
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        private ItemRowBinding binding;
        public ViewHolder(ItemRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UserViewModel model)
        {
            this.binding.setViewmodel(model);
        }

        public ItemRowBinding getBinding()
        {
            return this.binding;
        }
    }
}
