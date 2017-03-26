package com.pchef.cc.personalchef;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sarthak on 3/25/2017.
 */

//Ingredients Adapter

public class IngAdapter extends RecyclerView.Adapter<IngAdapter.MyViewHolder> {

    Context context;
    List<String> iList;

    public IngAdapter(List<String> heroList, Context context) {
        this.iList = heroList;
        this.context=context;
    }


    @Override
    public IngAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.input_element, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IngAdapter.MyViewHolder holder, final int position) {
        String item = iList.get(position);
        holder.name.setText(item);

    }

    @Override
    public int getItemCount() {
        return iList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);


        }
    }
}

