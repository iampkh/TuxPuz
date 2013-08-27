package com.pkh.tuxpuz;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FacebookPage extends Activity {

	/** Called when the activity is first created. */
	
	WebView webView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.facebook_page);
	    webView = (WebView) findViewById(R.id.fb_webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
		
		 webView.setWebViewClient(new HelloWebViewClient());
		 
		webView.loadUrl("https://www.facebook/Tuxpuz.com");
		
		findViewById(R.id.fb_backbtn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				webView.goBack();
				
			}
		});
		
		findViewById(R.id.fb_homebtn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	    // TODO Auto-generated method stub
	}
	private class HelloWebViewClient extends WebViewClient {
		  @Override
		  public boolean shouldOverrideUrlLoading(WebView view, String url) {
		   view.loadUrl(url);
		   view.canGoBack();
		   view.canGoForward();
		   return true;
		  }
		 }

}
