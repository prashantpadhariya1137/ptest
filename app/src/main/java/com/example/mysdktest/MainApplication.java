package com.example.mysdktest;

import android.app.Application;


import android.app.Application;
import android.util.Log;

import com.example.sdk_fonimo.ToastBridgePackage;
import com.facebook.react.BuildConfig;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.facebook.react.ReactApplication;


import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
            return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new ToastBridgePackage()
            );
        }
        @Override
        protected String getJSMainModuleName() {
            return "index";
        }
    };

    @Override
    public ReactNativeHost getReactNativeHost() {

        Log.d("TAG", "ReactNativeHost configuration: " + mReactNativeHost);

        return mReactNativeHost;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SoLoader.init(this, /* native exopackage */ false);


        ReactInstanceManager reactInstanceManager = getReactNativeHost().getReactInstanceManager();

        // Check if the context is already initialized
        ReactContext reactContext = reactInstanceManager.getCurrentReactContext();

        Log.d("TAG", "onCreate: RNCONTEXT 0"+reactContext);


        if (reactContext instanceof ReactApplicationContext) {
            ReactApplicationContext reactApplicationContext = (ReactApplicationContext) reactContext;

            Log.d("TAG", "onCreate: RNCONTEXT "+reactApplicationContext);
            // Now you can use the reactApplicationContext
        }

    }
}
