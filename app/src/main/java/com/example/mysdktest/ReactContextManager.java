package com.example.mysdktest;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.ReactContext;

public class ReactContextManager {
    private static ReactContextManager instance;
    private ReactInstanceManager reactInstanceManager;

    private ReactContextManager(ReactInstanceManager reactInstanceManager) {
        this.reactInstanceManager = reactInstanceManager;
    }

    public static synchronized ReactContextManager getInstance(ReactInstanceManager reactInstanceManager) {
        if (instance == null) {
            instance = new ReactContextManager(reactInstanceManager);
        }
        return instance;
    }

    public ReactContext getReactContext() {
        return reactInstanceManager.getCurrentReactContext();
    }

    public void executeWhenReady(Runnable runnable) {
        ReactContext reactContext = getReactContext();
        if (reactContext != null) {
            runnable.run();
        } else {
            // Add a listener to wait for the ReactContext to be initialized
            reactInstanceManager.addReactInstanceEventListener(new ReactInstanceManager.ReactInstanceEventListener() {
                @Override
                public void onReactContextInitialized(ReactContext context) {
                    reactInstanceManager.removeReactInstanceEventListener(this);
                    runnable.run();
                }
            });

            // Ensure React context creation is started if it hasn't already
            if (!reactInstanceManager.hasStartedCreatingInitialContext()) {
                reactInstanceManager.createReactContextInBackground();
            }
        }
    }
}

