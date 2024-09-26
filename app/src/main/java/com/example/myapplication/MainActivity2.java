package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mylibrary.SipManager;

public class MainActivity2 extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        EditText username, password,domain;

        webView = findViewById(R.id.webView);
        Button button = findViewById(R.id.button);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        domain = findViewById(R.id.domain);



        // Enable JavaScript in the WebView
        webView.getSettings().setJavaScriptEnabled(true);

        // Set WebViewClient to handle page loading
        webView.setWebViewClient(new WebViewClient());

        // Set WebChromeClient to handle JavaScript dialogs, titles, etc.
        webView.setWebChromeClient(new WebChromeClient());

        // Add JavaScript interface to handle callbacks from JS
        webView.addJavascriptInterface(new JSInterface(), "AndroidFunction");

        // Load the HTML page with the JavaScript function
        webView.loadUrl("file:///android_asset/index.html");


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);  // Allow access to file URLs
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);  // Allow cross-origin requests for WebView

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());


        SipManager sipManager = new SipManager(getApplicationContext(), webView);

        sipManager.setSipResultListener(result -> {
            // Handle the SIP result
            Log.d("SIP Result 2", result);
            Toast.makeText(getApplicationContext(),"SIP Result"+result,Toast.LENGTH_LONG).show();
        });


        // Call a JavaScript function
        webView.evaluateJavascript("sumTwoNumbers(5, 10)", null);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String num1 = number1.getText().toString();
//                String num2 = number2.getText().toString();
//
//                // Make sure the inputs are not empty
//                if (!num1.isEmpty() && !num2.isEmpty()) {
//                    // Call the sumTwoNumbers JavaScript function with the user inputs
//                    webView.evaluateJavascript("sumTwoNumbers(" + num1 + ", " + num2 + ")", null);
//                } else {
//                    Toast.makeText(MainActivity2.this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
//                }
                String domainstr = domain.getText().toString();
                String usernamestr = username.getText().toString();
                String passwordstr = password.getText().toString();

                sipManager.sipRegister(usernamestr,passwordstr,domainstr);

//                sipRegister(usernamestr,passwordstr,domainstr);
                // Call the sumTwoNumbers JavaScript function with arguments on button click
            }
        });

    }

    public void sipRegister(String uname,String pass,String domain){

        if (domain.isEmpty()) {
            // Call the JavaScript function with the entered SIP URI
            Toast.makeText(MainActivity2.this, "Please enter domain", Toast.LENGTH_SHORT).show();

        } else if (uname.isEmpty()) {
            // Call the JavaScript function with the entered SIP URI
            Toast.makeText(MainActivity2.this, "Please enter username", Toast.LENGTH_SHORT).show();
        }else if (pass.isEmpty()) {
            // Call the JavaScript function with the entered SIP URI
            Toast.makeText(MainActivity2.this, "Please enter password", Toast.LENGTH_SHORT).show();
        }else {
            webView.evaluateJavascript("makeSipCall('" + uname + "', '" + pass + "', '" + domain + "')", null);
        }
    }



    public class JSInterface {
        @JavascriptInterface
        public void handleResultFromJS(String result) {

            Log.d("TAG", "handleResultFromJS: "+result.toString());
            // Handle the result from the JavaScript function
            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Result from JS: " + result, Toast.LENGTH_LONG).show());
        }
    }
}