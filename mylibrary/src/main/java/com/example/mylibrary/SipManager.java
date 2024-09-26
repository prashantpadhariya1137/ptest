package com.example.mylibrary;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class SipManager {
    private WebView webView;
    private SipResultListener listener;
    private Context context;

    // Constructor to initialize the WebView and Context
    public SipManager(Context context, WebView webView) {
        this.context = context;
        this.webView = webView;
        setupWebView();
    }

    // Set the listener to receive the result
    public void setSipResultListener(SipResultListener listener) {
        this.listener = listener;
    }

    // Setup the WebView
    private void setupWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new JSInterface(), "AndroidFunction");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        // Load your HTML or JS content
        webView.loadUrl("file:///android_asset/index.html");
    }

    // Method to register SIP
    public void sipRegister(String uname, String pass, String domain) {
        if (domain.isEmpty()) {
            Toast.makeText(context, "Please enter domain", Toast.LENGTH_SHORT).show();
        } else if (uname.isEmpty()) {
            Toast.makeText(context, "Please enter username", Toast.LENGTH_SHORT).show();
        } else if (pass.isEmpty()) {
            Toast.makeText(context, "Please enter password", Toast.LENGTH_SHORT).show();
        } else {
            webView.evaluateJavascript("makeSipCall('" + uname + "', '" + pass + "', '" + domain + "')", null);
        }
    }

    // Inner class for JavaScript interface
    private class JSInterface {
        @JavascriptInterface
        public void handleResultFromJS(String result) {
            if (listener != null) {
                listener.onSipResult(result);  // Send the result back via listener
            }
        }
    }
}