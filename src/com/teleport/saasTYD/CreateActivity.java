package com.teleport.saasTYD;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.teleport.saasTYD.MainActivity.RequestTask;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
public class CreateActivity extends Activity {

	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_create);

        {          
            {
//////////////////////// ���������� ����� �������� �������� (����� ������������ ��� �������� �������� ������� � ����)
TelephonyManager t = (TelephonyManager)getSystemService(TELEPHONY_SERVICE); 
String simnumber = t.getLine1Number();
Log.d("SIM PHONE", simnumber + "");
EditText csp = (EditText) findViewById(R.id.editText3);
csp.setText(simnumber);

            }
          };
       
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
 	

// 		case R.id.menu_exit: {
// 			onDestroy();  
// 			break;
// 		}		
 		}
 		return super.onMenuItemSelected(featureId, item);
 	}   
    
//      @Override
//      public void onDestroy() {
//         super.onDestroy();
//         System.exit(0);
//      }
  
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
} 
	// ���� ������ ������ ���������
	public void onClickCreateInq(View v)
	{
		
		EditText cs = (EditText) findViewById(R.id.editText1);
		String �_sender = cs.getText().toString();  
	    Log.d("�_sender", �_sender);
		
		EditText csa = (EditText) findViewById(R.id.editText2);
		String �_sender_adress = csa.getText().toString();
		Log.d("�_sender_adress", �_sender_adress);
		
		EditText csp = (EditText) findViewById(R.id.editText3);
		String �_sender_phone = csp.getText().toString();
		Log.d("�_sender_phone", �_sender_phone);
		
		EditText cra = (EditText) findViewById(R.id.editText7);
		String �_receiver_adress = cra.getText().toString();
		Log.d("�_receiver_adress", �_receiver_adress);
		
		EditText cre = (EditText) findViewById(R.id.editText6);
		String �_receiver = cre.getText().toString();
		Log.d("�_receiver", �_receiver);
		
		EditText crp = (EditText) findViewById(R.id.editText8);
		String �_receiver_phone = crp.getText().toString();
	    Log.d("�_receiver_phone", �_receiver_phone);
		
		if(�_sender.matches("") || �_sender_adress.matches("") || �_sender_phone.matches("") || �_receiver_adress.matches("") || �_receiver.matches("") || �_receiver_phone.matches("")){

             // out of range
             Toast.makeText(this, R.string.enterfields, Toast.LENGTH_LONG).show();
		}else{
			
		Toast.makeText(getApplicationContext(), R.string.please_wait, Toast.LENGTH_LONG).show();
		
	//    Intent intent = new Intent(CreateActivity.this, MainActivity.class);
	//    startActivity(intent);
	    Toast.makeText(getApplicationContext(), R.string.dont_work, Toast.LENGTH_LONG).show();
	    
	    EditText cw = (EditText) findViewById(R.id.editText4);
	    String �_weight = cw.getText().toString();
	    Log.d("�_weight", �_weight);
	    Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
	    String gabaritChoose = mySpinner.getSelectedItem().toString();  
	    Log.d("�_gabarit", gabaritChoose);
	    EditText crem = (EditText) findViewById(R.id.editText9);
	    String �_receiver_email = crem.getText().toString();
	    Log.d("�_receiver_email", �_receiver_email);
	    EditText cdv = (EditText) findViewById(R.id.editText10);
	    String �_declarate_walue = cdv.getText().toString();
	    Log.d("�_declarate_walue", �_declarate_walue);
	    EditText ci = (EditText) findViewById(R.id.editText11);
	    String �_info = ci.getText().toString();
	    Log.d("�_info", �_info);
	    
	    String KEY = "API KEY";
	  //��� ��������� ���� ����� ����������, ��� ������� � ������ ��������� ���� ���� � ��� �� ���������� ������� wamp (:
//      new RequestTaskC().execute("http://saas.teleport-ds.com/api/getm/json/documents/" + KEY, KEY);
      Log.d("+URLCreateinq", "http://saas.teleport-ds.com/api/getm/json/documents/" + KEY);
	    
		}
		
	}
	
	

	
	// ���� ������ ������ ����������� ������
		public void onClickOkInquiry(View v)
		{
		}
		
	// ���� ������ ������ �������� ������
	public void onClickCancelInquiry(View v)
	{
		this.finish();
	}
	
} 
