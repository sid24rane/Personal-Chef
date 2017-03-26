package com.pchef.cc.personalchef;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.view.View.GONE;

public class RecipeDetails extends AppCompatActivity {
    Toolbar mToolbar;
    private TextView desc,url;
    private LinearLayout l_desc;
    List<String> iList = new ArrayList<String>();
    private RecyclerView rv,rv2;
    private IngAdapter mAdapter,vAdapter;
    private ImageView imv;
    List<String> nuts = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        Intent intent = getIntent();

        final Recipe recipe = (Recipe) getIntent().getParcelableExtra("Recipe");
        rv=(RecyclerView) findViewById(R.id.inRecyclerview);
        rv2=(RecyclerView) findViewById(R.id.vitaminsRecyclerview);
        imv=(ImageView) findViewById(R.id.main_backdrop);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv2.setLayoutManager(new LinearLayoutManager(this));
        rv2.setItemAnimator(new DefaultItemAnimator());



        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv2.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new IngAdapter(iList,this);
        Glide.with(this).load(recipe.getImg()).into(imv);
        /*Bitmap b = BitmapFactory.decodeByteArray(
                getIntent().getByteArrayExtra("byteArray"),0,getIntent().getByteArrayExtra("byteArray").length);
        imv.setImageBitmap(b);*/


        desc=(TextView) findViewById(R.id.desc);
        url =(TextView) findViewById(R.id.url);
        l_desc=(LinearLayout)findViewById(R.id.layout_desc);


        try {
            Nutrition nutrition = recipe.getNutrition();

            if (!nutrition.getCarbohydrateContent().equals("abc")) {
                String s = "Carbohydrate Content:" + nutrition.getCarbohydrateContent();
                nuts.add(s);
            }
            if (!nutrition.getCholesterolContent().equals("")) {
                String s = "Cholesterol Content:" + nutrition.getCholesterolContent();
                nuts.add(s);
            }
            if (!nutrition.getFatContent().equals("")) {
                String s = "Fat Content:" + nutrition.getFatContent();
                nuts.add(s);
            }
            if (!nutrition.getFiberContent().equals("")) {
                String s = "Fiber Content:" + nutrition.getFiberContent();
                nuts.add(s);
            }
            if (!nutrition.getProteinContent().equals("")) {
                String s = "Protien Content:" + nutrition.getProteinContent();
                nuts.add(s);
            }
            if (!nutrition.getSugarContent().equals("")) {
                String s = "Sugar Content:" + nutrition.getSugarContent();
                nuts.add(s);
            }

            vAdapter = new IngAdapter(nuts, this);

        }
        catch(Exception e)
        {
            //

        }






        String arr[]=recipe.getIngridient();

        for(int i=0;i<arr.length;i++)
        {
            iList.add(arr[i]);
        }

        rv.setAdapter(mAdapter);
        rv2.setAdapter(vAdapter);
        String d=(recipe.getDesc());
        desc.setText(d);

        if(d==null)
        {
            l_desc.setVisibility(GONE);
        }

        url.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(recipe.getUrl());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
        getSupportActionBar().setTitle(recipe.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}
