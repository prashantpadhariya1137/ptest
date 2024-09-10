package com.example.mysdktest.ui.theme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.mysdktest.MyReactNativeModule;
import com.example.mysdktest.R;

import com.example.mysdktest.ReactContextManager;
import com.example.sdk_fonimo.ToastBridgePackage;
import com.example.sdk_fonimo.ToastRNative;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceEventListener;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;



public class MainActivityNew extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        ReactApplication reactApplication = (ReactApplication) getApplication();
        ReactNativeHost reactNativeHost = reactApplication.getReactNativeHost();
        ReactInstanceManager reactInstanceManager = reactNativeHost.getReactInstanceManager();

        Log.d("TAG", "GETCONTEXT before:1 "+reactInstanceManager);

        ReactInstanceManager reactInstanceManagernew = ((ReactApplication) getApplication()).getReactNativeHost().getReactInstanceManager();
        ReactApplicationContext reactContext = (ReactApplicationContext) reactInstanceManager.getCurrentReactContext();

        ReactContextManager contextManager = ReactContextManager.getInstance(reactInstanceManager);

        Log.d("TAG", "GETCONTEXT onCreate 3: "+reactContext);

//        MyNative myNative = new MyNative(reactContext);
//        myNative.callJavaScriptFunction("showToast","Toast from fonimo react-method");

        Log.d("TAG", "onCreate RNCONTEXT 1: "+reactContext);
       new ToastRNative(reactContext);

      ToastRNative.sendEvent("showToast","Toast from fonimo react-method");




    }



    // CallSipConnection method with username,password,host parameters

    public void callSipConnection(String username,String password,String host){

        ReactApplication reactApplication = (ReactApplication) getApplication();
        ReactNativeHost reactNativeHost = reactApplication.getReactNativeHost();
        ReactInstanceManager reactInstanceManager = reactNativeHost.getReactInstanceManager();

        ReactApplicationContext reactContext = (ReactApplicationContext) reactInstanceManager.getCurrentReactContext();

        WritableMap params = Arguments.createMap();
        params.putString("param1", username);
        params.putString("param2", password);
        params.putString("param3", host);

//        MyNative myNative = new MyNative(reactContext);
//        myNative.callJavaScriptFunction("CallSipConnection",params);

    }


    // Intiate outgoing call from this method and pass number in parameter

    public void callInitiate(String number){

        ReactApplication reactApplication = (ReactApplication) getApplication();
        ReactNativeHost reactNativeHost = reactApplication.getReactNativeHost();
        ReactInstanceManager reactInstanceManager = reactNativeHost.getReactInstanceManager();

        ReactApplicationContext reactContext = (ReactApplicationContext) reactInstanceManager.getCurrentReactContext();

        WritableMap params = Arguments.createMap();
        params.putString("param1", number);

//        MyNative myNative = new MyNative(reactContext);
//        myNative.callJavaScriptFunction("callInitiate",params);

    }



}
