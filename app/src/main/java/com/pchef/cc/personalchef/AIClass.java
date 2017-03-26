package com.pchef.cc.personalchef;

import android.content.Context;
import android.widget.Toast;

import ai.api.model.AIError;
import ai.api.model.AIResponse;
import ai.api.ui.AIDialog;

/**
 * Created by sarthak on 3/26/2017.
 */

public class AIClass implements AIDialog.AIDialogListener {

    Context context;

    public AIClass(Context context)
    {
        this.context=context;
    }

    @Override
    public void onResult(AIResponse result) {
        Toast.makeText(context,"Here",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(AIError error) {

    }

    @Override
    public void onCancelled() {

    }

}
