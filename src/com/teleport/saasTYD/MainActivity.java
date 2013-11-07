package com.teleport.saasTYD;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	//���������� �������� ��� ���
	private WebView mWebView;
	// �������� �����������
	private ProgressBar pb;
	
	MainActivity ma;
	TrackActivity ta;
	public String trackid;
	public String output;

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        
             
        mWebView = (WebView) findViewById(R.id.webview);  	  
        mWebView.setWebViewClient(new WebViewClient());      
        
        // �������� ��������� JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // ��������� ������
        mWebView.getSettings().setSavePassword(true);
        // ��������� ������ ���� ��� ����� ������
        mWebView.getSettings().setSaveFormData(true);
        // ������������� Zoom control
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
     // ��������� cookie
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
    	// ��������� �������� ��������
        mWebView.loadUrl("http://saas.teleport-ds.com/"); 
        
     // ��������� �������� ���� �������� ��������
        final Activity activity = this;
        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
        mWebView.setWebChromeClient(new WebChromeClient()
        {
            public void onProgressChanged(WebView view, int progress)
            {
              activity.setTitle(R.string.loading);
              activity.setProgress(progress * 100);

              if(progress == 100)
            	  activity.setTitle(R.string.app_name);
            }
          });
        // ��������� ������
        mWebView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
             Toast.makeText(getApplicationContext(), "Error: " + description+ " " + failingUrl, Toast.LENGTH_LONG).show();
            }
        });        
                
    }

    public void onClickTeleportwww(View v){
    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://teleport-ds.com/"));
			startActivity(browserIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    // ��������� ������� ������� ����
 	@Override
 	public boolean onMenuItemSelected(int featureId, MenuItem item) {
 		switch (item.getItemId()) {
 		case R.id.menu_about: {
 			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://teleport-ds.com/"));
 			startActivity(browserIntent);
 			break;
 		}
 		case R.id.menu_update_page: {
 			mWebView.reload();
 			break;
 		}

 		case R.id.menu_exit: {
 			onDestroy();  
 			break;
 		}		
 		}
 		return super.onMenuItemSelected(featureId, item);
 	}   
    
    
	public void onPageStarted (WebView view, String url, Bitmap favicon) {
        // ��������� �������� �������� � ������ �������� ��������
        mWebView.getSettings().setLoadsImagesAutomatically(false);
      }
      
      public void onPageFinished (WebView view, String url) {
    	// �������� �������� �������� � ����� �������� ��������
    	  mWebView.getSettings().setLoadsImagesAutomatically(true);
      }   
    
    //��������� ������� ������� ������ ����� 31.05 ���� ������ ����� �������� ������
  	@Override
  	public boolean onKeyDown(int keyCode, KeyEvent event) {
  		if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
  			mWebView.goBack();
  			return true;
  		}
  		return super.onKeyDown(keyCode, event);
  	}
      
      
      @Override
      public void onDestroy() {
         super.onDestroy();
//         System.exit(0);
      }
  
      // Displays an error if the app is unable to load content.
      private void showErrorPage() {
          setContentView(R.layout.activity_main);

          // The specified network connection is not available. Displays error message.
          WebView myWebView = (WebView) findViewById(R.id.webview);
          myWebView.loadData(getResources().getString(R.string.connection_error),
                  "text/html", null);
      }


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
} 
	// ��� ����� �� ������ ���������
	public void onClickTrack(View v)
	{
		EditText edtrackID = (EditText) findViewById(R.id.editText1);
		if(edtrackID.getText().toString().length()<1){

             // out of range
             Toast.makeText(this, R.string.enterid, Toast.LENGTH_LONG).show();
		}else{
			
		Toast.makeText(getApplicationContext(), R.string.please_wait, Toast.LENGTH_LONG).show();
//		new PostData().execute(edtrackID.getText().toString());
//		 new PostData(getBaseContext()).execute();
		// �������� ������� ������������ �������� ������ ������
		getTrackID();
		// �������� ��������� �������� ������
		new PostData(getBaseContext()).execute(edtrackID.getText().toString());
		// ��������� ���� �������� ������� ���������� ������
				Intent launchNewIntent = new Intent(MainActivity.this,TrackActivity.class);
				launchNewIntent.putExtra("edtrackID", edtrackID.getText().toString());
				
				startActivityForResult(launchNewIntent, 0);
				
				
                
              
              
//		new PostData(ta, ma, getBaseContext()).execute();
		}
			
	}
	
	public String getTrackID(){
		EditText fedtrackID = (EditText) findViewById(R.id.editText1);
		trackid = fedtrackID.getText().toString();
		Log.d("fTrackOrderID", trackid + "");
		return trackid;
	}
}

