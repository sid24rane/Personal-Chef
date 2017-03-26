package com.pchef.cc.personalchef;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by sarthak on 3/25/2017.
 */

public class InputAdapter  extends RecyclerView.Adapter<InputAdapter.MyViewHolder> {

    Context context;
    List<String> iList;



    public InputAdapter(List<String> heroList, Context context) {
        this.iList = heroList;
        this.context=context;
    }


    @Override
    public InputAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.input_element, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InputAdapter.MyViewHolder holder, final int position) {
        String item = iList.get(position);
        holder.name.setText(item);

        holder.name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                String iname = iList.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                builder.setTitle("Confirm");
                builder.setMessage("Do You wish to Delete "+iname+"?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        iList.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();



                return false;
            }
        });

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

