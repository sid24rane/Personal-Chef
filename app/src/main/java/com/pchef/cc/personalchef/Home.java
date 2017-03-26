package com.pchef.cc.personalchef;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import ai.api.ui.AIDialog;

public class Home extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce= false;
    private EditText ip;
    //private FloatingActionButton add;
    private RecyclerView rv;
    Button go,add;
    private AIService aiService;

    private List<String> iList = new ArrayList<>();
    InputAdapter mAdapter;
    //EditText
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ip = (EditText) findViewById(R.id.input);
        add = (Button) findViewById(R.id.add);
        rv = (RecyclerView) findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());

        go = (Button) findViewById(R.id.go);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mAdapter = new InputAdapter(iList, this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aiService.startListening();

            }
        });


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iList.size() != 0) {
                    StringBuilder sb = new StringBuilder("");
                    for (int i = 0; i < iList.size(); i++) {
                        sb.append(iList.get(i));
                        sb.append("+");
                    }
                    String str = sb.toString();
                    Intent intent = new Intent(Home.this, MainActivity.class);
                    intent.putExtra("str", str);
                    startActivity(intent);
                } else {
                    Toast.makeText(Home.this, "Please Add Atleast One Ingredient", Toast.LENGTH_SHORT).show();
                }

            }
        });
        ip.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String i = ip.getText().toString().trim();
                if (!i.equals("")) {
                    if (!iList.contains(i)) {
                        iList.add(i);
                        mAdapter.notifyDataSetChanged();
                        rv.setAdapter(mAdapter);
                        ip.setText("");
                        ip.requestFocus();
                    }
                }
                return false;
            }
        });


        final AIConfiguration config = new AIConfiguration("54b8a1cfad6e4a7e870d33d8cc732a73",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        /*AIDialog aiDialog = new AIDialog(this, config);
        aiDialog.setResultsListener(new AIClass(Home.this));
        aiDialog.showAndListen();*/
        aiService = AIService.getService(this, config);
        aiService.setListener(new ai.api.AIListener() {
            @Override
            public void onResult(AIResponse response) {

                Result result = response.getResult();

                // Get parameters
                String parameterString = "";
                if (result.getParameters() != null && !result.getParameters().isEmpty()) {
                    for (final Map.Entry<String, JsonElement> entry : result.getParameters().entrySet()) {
                        parameterString += "(" + entry.getKey() + ", " + entry.getValue() + ") ";
                    }
                }

                // Show results in TextView.

                if(!parameterString.equals(""))
                {
                    String ip = result.getResolvedQuery();
                    iList.add(ip);
                    mAdapter.notifyDataSetChanged();
                    rv.setAdapter(mAdapter);
                }


                String s = "Query:" + result.getResolvedQuery() +
                        "\nAction: " + result.getAction()+
                        "\nparams" + parameterString;

                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                /*resultTextView.setText("Query:" + result.getResolvedQuery() +
                        "\nAction: " + result.getAction() +
                        "\nParameters: " + parameterString);*/
            }

            @Override
            public void onError(final AIError error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Home.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onAudioLevel(float level) {

            }

            @Override
            public void onListeningStarted() {
                Toast.makeText(Home.this, "Started", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onListeningCanceled() {

            }

            @Override
            public void onListeningFinished() {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_feedback) {
            startActivity(new Intent(Home.this,Feedback.class));
            return true;
        }
        if (id == R.id.action_about) {
            startActivity(new Intent(Home.this,AboutUs.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {



        if (doubleBackToExitPressedOnce) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }





}

