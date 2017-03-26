package com.pchef.cc.personalchef;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by sarthak on 3/25/2017.
 */


public class RecAdapter  extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {

    Context context;
    List <Recipe> rList;



    public RecAdapter(List<Recipe> rList, Context context) {
        this.rList = rList;
        this.context=context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Recipe recipe = rList.get(position);
        holder.name.setText(recipe.getName());
        Glide.with(context).load(recipe.getImg()).into(holder.img);
        holder.rbar.setRating(recipe.getRating());

        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,RecipeDetails.class).putExtra("Recipe",recipe);
                holder.img.buildDrawingCache();
                holder.img.buildDrawingCache();
                /*Bitmap bmap = holder.img.getDrawingCache();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                intent.putExtra("byteArray",bmap);*/
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView img;
        public RatingBar rbar;
        public RelativeLayout rl;

        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            rbar=(RatingBar) view.findViewById(R.id.ratingBar);
            img=(ImageView) view.findViewById(R.id.imgView);
            rl=(RelativeLayout) view.findViewById(R.id.container);



        }
    }
}
