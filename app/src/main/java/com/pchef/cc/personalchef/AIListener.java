package com.pchef.cc.personalchef;

/**
 * Created by sarthak on 3/26/2017.
 */

import ai.api.model.AIError;
import ai.api.model.AIResponse;

import ai.api.model.AIError;
import ai.api.model.AIResponse;

/**
 * Created by sarthak on 3/26/2017.
 */

public interface AIListener {
    void onResult(AIResponse result); // here process response
    void onError(AIError error); // here process error
    void onAudioLevel(float level); // callback for sound level visualization
    void onListeningStarted(); // indicate start listening here
    void onListeningCanceled(); // indicate stop listening here
    void onListeningFinished(); // indicate stop listening here
}