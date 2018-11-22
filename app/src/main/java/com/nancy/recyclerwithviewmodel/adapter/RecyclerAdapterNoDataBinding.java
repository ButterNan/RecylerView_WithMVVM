package com.nancy.recyclerwithviewmodel.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nancy.recyclerwithviewmodel.R;
import com.nancy.recyclerwithviewmodel.databinding.ItemRowBinding;
import com.nancy.recyclerwithviewmodel.model.Flower;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterNoDataBinding extends RecyclerView.Adapter<RecyclerAdapterNoDataBinding.Holder> {

    private  LayoutInflater mInflater;
    private List<Flower> mFlowerList;
    private FlowerClickListener mListener;

    public RecyclerAdapterNoDataBinding(FlowerClickListener listener, LayoutInflater inflater) {
        mListener = listener;
        mInflater = inflater;
        mFlowerList = new ArrayList<>();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(mInflater.inflate(R.layout.item_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Flower currFlower = mFlowerList.get(position);

        holder.mName.setText(currFlower.getName());
        holder.mPrice.setText(String.format("$%.2f", currFlower.getPrice()));

    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }

    public void addFlowers(List<Flower> flowerResponses) {
        mFlowerList.addAll(flowerResponses);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView mPhoto;
        private TextView mName, mPrice;

        public Holder(View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.name);
            mPrice = (TextView) itemView.findViewById(R.id.time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getLayoutPosition(), mFlowerList.get(getAdapterPosition()).getName());
        }
    }
    public interface FlowerClickListener {

        void onClick(int position, String name);
    }

}

