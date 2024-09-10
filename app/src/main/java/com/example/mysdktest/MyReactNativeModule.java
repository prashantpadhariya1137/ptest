package com.example.mysdktest;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

public class MyReactNativeModule extends ReactContextBaseJavaModule {

    public MyReactNativeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        Log.d("MyReactNativeModule", "ReactApplicationContext in constructor: " + reactContext);
    }

    @Override
    public String getName() {
        return "MyReactNativeModule";
    }

    public void someMethod() {
        ReactApplicationContext context = getReactApplicationContext();
        Log.d("MyReactNativeModule", "ReactApplicationContext in someMethod: " + context);
        // Use the context as needed
    }
}
