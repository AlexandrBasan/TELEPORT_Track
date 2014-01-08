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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.teleport.saasTYD.MainActivity.RequestTask;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class CreateActivity extends Activity {
	// JSON Node names
    private static final String deliverycost = "error receiving data";
    private static final String inquiryid = "error receiving data";
    // ������ ��� �����������
    String oauth_consumer_key = "TTGY9XM8bSFtydVjGMyYWhVWamEPbHHU";
    String XCSRFTokenm = "XCSRFToken";
    String login = "alexandr.basan@gmail.com";
    String password = "Infinity8";
    // �������� �����
    String �_sender = "";
    String �_sender_adress = "";
    String �_sender_email = "";
    String �_sender_phone = "";
    String �_receiver = "";
    String �_receiver_adress = "";
    String �_receiver_phone = "";
    String �_weight = "";
    String �_gabarit = "";
    String �_receiver_email = "";
    String �_declarate_walue = "";
    String �_info = "";
    // ������� ��� ������� ��������
    private static ArrayList<HashMap<String, Object>> CreateInq;

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
		�_sender = cs.getText().toString();  
	    Log.d("�_sender", �_sender);
		
		EditText csa = (EditText) findViewById(R.id.editText2);
		�_sender_adress = csa.getText().toString();
		Log.d("�_sender_adress", �_sender_adress);
		
		EditText cse = (EditText) findViewById(R.id.editText5);
		�_sender_email = cse.getText().toString();
		Log.d("�_sender_email", �_sender_email);
		
		EditText csp = (EditText) findViewById(R.id.editText3);
		�_sender_phone = csp.getText().toString();
		Log.d("�_sender_phone", �_sender_phone);
		
		EditText cra = (EditText) findViewById(R.id.editText7);
		�_receiver_adress = cra.getText().toString();
		Log.d("�_receiver_adress", �_receiver_adress);
		
		EditText cre = (EditText) findViewById(R.id.editText6);
		�_receiver = cre.getText().toString();
		Log.d("�_receiver", �_receiver);
		
		EditText crp = (EditText) findViewById(R.id.editText8);
		�_receiver_phone = crp.getText().toString();
	    Log.d("�_receiver_phone", �_receiver_phone);
		
		if(�_sender.matches("") || �_sender_adress.matches("") || �_sender_phone.matches("") || �_receiver_adress.matches("") || �_receiver.matches("") || �_receiver_phone.matches("") || �_sender_email.matches("")){

             // out of range
             Toast.makeText(this, R.string.enterfields, Toast.LENGTH_LONG).show();
		}else{
			
		Toast.makeText(getApplicationContext(), R.string.please_wait, Toast.LENGTH_LONG).show();
		
	//    Intent intent = new Intent(CreateActivity.this, MainActivity.class);
	//    startActivity(intent);
	//    Toast.makeText(getApplicationContext(), R.string.dont_work, Toast.LENGTH_LONG).show();
	    
	    EditText cw = (EditText) findViewById(R.id.editText4);
	    �_weight = cw.getText().toString();
	    Log.d("�_weight", �_weight);
	    Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
	    �_gabarit = mySpinner.getSelectedItem().toString();  
	    Log.d("�_gabarit", �_gabarit);
	    EditText crem = (EditText) findViewById(R.id.editText9);
	    �_receiver_email = crem.getText().toString();
	    Log.d("�_receiver_email", �_receiver_email);
	    EditText cdv = (EditText) findViewById(R.id.editText10);
	    �_declarate_walue = cdv.getText().toString();
	    Log.d("�_declarate_walue", �_declarate_walue);
	    EditText ci = (EditText) findViewById(R.id.editText11);
	    �_info = ci.getText().toString();
	    Log.d("�_info", �_info);
	    

new RequestTaskXCSRFToken().execute("http://saas.teleport-ds.com/document/1.0/user/token.json?oauth_consumer_key=" + oauth_consumer_key, oauth_consumer_key);
Log.d("+URLtoken", "http://saas.teleport-ds.com/document/1.0/user/token.json?oauth_consumer_key=" + oauth_consumer_key);

		}
		
	}
/////////////////////////////////////////////////// 1 step //////////////////////////////////////////////////////////
	// ��������� �������� ������
		class RequestTaskXCSRFToken extends AsyncTask<String, String, String> {

	        @Override
	        protected String doInBackground(String... params) {

	                try {
	                        //������� ������ �� ������
	                        DefaultHttpClient hc = new DefaultHttpClient();
	                        ResponseHandler<String> res = new BasicResponseHandler();
	                        //�� � ��� ����� �������� post ������
	                        HttpPost postMethod = new HttpPost(params[0]);
	                        //����� ���������� 14 ����������
	                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(14);
	                        //�������� ��������� �� ����� ����������� � �������
	                        nameValuePairs.add(new BasicNameValuePair("oauth_consumer_key", params[1]));
	                        Log.d("oauth_consumer_key", params[1] + "");
	                      //�������� �� ������ � �������� �� ������
	                        postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	                        //�������� ����� �� �������
	                        String responsetoken = hc.execute(postMethod, res);
	                        Log.d("responsetoken", responsetoken + "");
	                        
	                        // ������������ �������� ������� ���������
	                    	CreateInq = new ArrayList<HashMap<String, Object>>();
	                    	//�������� � ����� ��������
	                    	JSONURLtoken(responsetoken);

	                        
	                } catch (Exception e) {
	                        System.out.println("Exp=" + e);
	                        
	                }
	                return null;
	        }

	        @Override
	        protected void onPostExecute(String result) {
	        	// �������� step 2
	        	new RequestTaskLogin().execute("http://saas.teleport-ds.com/document/1.0/user/login.json?oauth_consumer_key=" + oauth_consumer_key, oauth_consumer_key, XCSRFTokenm, login, password);
	        	Log.d("+URLLogin", "http://saas.teleport-ds.com/document/1.0/user/login.json?oauth_consumer_key=" + oauth_consumer_key);
	                super.onPostExecute(result);
	        }
	        
	        protected void onProgressUpdate(Integer... progress){  }
				

	        @Override
	        protected void onPreExecute() {
	                super.onPreExecute();
	        }
	}
		 /** 1 step @param result */
		public void JSONURLtoken(String result) {

	        try {
	                //������� �������� json �������� � ������ ��� ������ - result
	                JSONObject json = new JSONObject(result);

	                	XCSRFTokenm = (String) json.get("token");
	                	Log.d("XCSRFTokenm", (String) json.get("token") + "");
	                	
	              
	        } catch (JSONException e) {
	                Log.e("log_tag", "Error parsing data " + e.toString());
	        }
	}
/////////////////////////////////////////////////// 1 step //////////////////////////////////////////////////////////	
/////////////////////////////////////////////////// 2 step //////////////////////////////////////////////////////////
		// ��������� �������� ������
				class RequestTaskLogin extends AsyncTask<String, String, String> {

			        @Override
			        protected String doInBackground(String... params) {

			                try {
			                        //������� ������ �� ������
			                        DefaultHttpClient hc = new DefaultHttpClient();
			                        ResponseHandler<String> res = new BasicResponseHandler();
			                        //�� � ��� ����� �������� post ������
			                        HttpPost postMethod = new HttpPost(params[0]);
			                        postMethod.setHeader("X-CSRF-Token:"+ XCSRFTokenm,"application/json" );
			                        Log.d("setHeader X-CSRF-Token ni", "X-CSRF-Token:"+ XCSRFTokenm + "");
			                        //����� ���������� 14 ����������
			                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(14);
			                        //�������� ��������� �� ����� ����������� � �������
			                        nameValuePairs.add(new BasicNameValuePair("oauth_consumer_key", params[1]));
			                        Log.d("oauth_consumer_key", params[1] + "");
			                        nameValuePairs.add(new BasicNameValuePair("X-CSRF-Token", params[2]));
			                        Log.d("X-CSRF-Token login", params[2] + "");
			                        nameValuePairs.add(new BasicNameValuePair("username", params[3]));
			                        Log.d("username login", params[3] + "");
			                        nameValuePairs.add(new BasicNameValuePair("password", params[4]));
			                        Log.d("password login", params[4] + "");
			                      //�������� �� ������ � �������� �� ������
			                        postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			                        //�������� ����� �� �������
			                        String responselogin = hc.execute(postMethod, res);
			                        Log.d("responselogin", responselogin + "");
			                        
			                        // ������������ �������� ������� ���������
			                    //	CreateInq = new ArrayList<HashMap<String, Object>>();
			                    	//�������� � ����� ��������
			                   // 	JSONURLLogin(responselogin);

			                        
			                } catch (Exception e) {
			                        System.out.println("Exp=" + e);
			                        
			                }
			                return null;
			        }

			        @Override
			        protected void onPostExecute(String result) {
			        	// �������� step 4
			        	// 4 ��� ��������� ���� ����� ����������, ��� ������� � ������ ��������� ���� ���� � ��� �� ���������� ������� wamp (:
			          new RequestTaskC().execute("http://saas.teleport-ds.com/document/1.0/ni?oauth_consumer_key=" + oauth_consumer_key, oauth_consumer_key, XCSRFTokenm, login, password, �_sender, �_sender_adress, �_sender_email, �_sender_phone, �_receiver_adress, �_receiver, �_receiver_phone, �_weight, �_gabarit, �_receiver_email, �_declarate_walue, �_info);
			          Log.d("+URLCreateinq", "http://saas.teleport-ds.com/document/1.0/ni?oauth_consumer_key=" + oauth_consumer_key);
			                super.onPostExecute(result);
			        }
			        
			        protected void onProgressUpdate(Integer... progress){  }
						

			        @Override
			        protected void onPreExecute() {
			                super.onPreExecute();
			        }
			}
				 /** 2 step @param result */
				public void JSONURLLogin(String result) {

			        try {
			                //������� �������� json �������� � ������ ��� ������ - result
			                JSONObject json = new JSONObject(result);

			                //	String XCSRFToken = (String) json.get("token");
			                //	Log.d("XCSRFToken", (String) json.get("token") + "");
			              
			        } catch (JSONException e) {
			                Log.e("log_tag", "Error parsing data " + e.toString());
			        }
			}
		
		
/////////////////////////////////////////////////// 2 step //////////////////////////////////////////////////////////
/////////////////////////////////////////////////// 4 step //////////////////////////////////////////////////////////	
	// ��������� �������� ������
	class RequestTaskC extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

                try {
                        //������� ������ �� ������
                        DefaultHttpClient hc = new DefaultHttpClient();
                        ResponseHandler<String> res = new BasicResponseHandler();
                        //�� � ��� ����� �������� post ������
                        HttpPost postMethod = new HttpPost(params[0]);
                        postMethod.setHeader("X-CSRF-Token:"+ XCSRFTokenm,"application/json" );
                        Log.d("setHeader X-CSRF-Token ni", "X-CSRF-Token:"+ XCSRFTokenm + "");
                        //����� ���������� 14 ����������
                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(14);
                        //�������� ��������� �� ����� ����������� � �������
                   //     nameValuePairs.add(new BasicNameValuePair("oauth_consumer_key", params[1]));
                  //      Log.d("params[1]", params[1] + "");
                  //      nameValuePairs.add(new BasicNameValuePair("X-CSRF-Token", params[2]));
                  //      Log.d("X-CSRF-Token_params[2]", params[2] + "");
                    //    nameValuePairs.add(new BasicNameValuePair("username", params[3]));
                    //    Log.d("username_params[3]", params[3] + "");
                    //    nameValuePairs.add(new BasicNameValuePair("password", params[4]));
                    //    Log.d("password_params[4]", params[4] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_sender_phone", params[5]));
                        Log.d("�_sender_params[5]", params[5] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_sender_email", params[6]));
                        Log.d("�_sender_adress_params[6]", params[6] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_receiver", params[7]));
                        Log.d("�_sender_email_params[7]", params[7] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_receiver_adress", params[8]));
                        Log.d("�_sender_phone_params[8]", params[8] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_receiver_phone", params[9]));
                        Log.d("�_receiver_adress_params[9]", params[9] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_weight", params[10]));
                        Log.d("�_receiver_params[10]", params[10] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_gabarit", params[11]));
                        Log.d("�_receiver_phone_params[11]", params[11] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_receiver_email", params[12]));
                        Log.d("�_weight_params[12]", params[12] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_declarate_walue", params[13]));
                        Log.d("�_gabarit_params[13]", params[13] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_info", params[14]));
                        Log.d("�_receiver_email_params[14]", params[14] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_info", params[15]));
                        Log.d("�_declarate_walue_params[15]", params[15] + "");
                        nameValuePairs.add(new BasicNameValuePair("�_info", params[16]));
                        Log.d("�_info_params[16]", params[16] + "");
                        //�������� �� ������ � �������� �� ������
                        postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        //�������� ����� �� �������
                        String response = hc.execute(postMethod, res);
                        Log.d("HTTP response", response + "");
                                               
                       
                        // ������������ �������� ������� ���������
                    	CreateInq = new ArrayList<HashMap<String, Object>>();
                    	//�������� � ����� ��������
                        JSONURLc(response);
                        
                } catch (Exception e) {
                        System.out.println("Exp=" + e);
                        
                }
                return null;
        }

        @Override
        protected void onPostExecute(String result) {
	
        	
        	//������ �������� ����� ��������� �������� � ID ������
            TextView cost = (TextView) findViewById(R.id.textView13);
            TextView id = (TextView) findViewById(R.id.textView15);
            cost.setText(deliverycost);
            id.setText(inquiryid);
                
                super.onPostExecute(result);
        }
        
        protected void onProgressUpdate(Integer... progress){  }
			

        @Override
        protected void onPreExecute() {
 //       	url.pb.setVisibility(View.VISIBLE);
                super.onPreExecute();
        }
}
	
	
	 /** @param result */
	public void JSONURLc(String result) {

        try {
                //������� �������� json �������� � ������ ��� ������ - result
                JSONObject json = new JSONObject(result);
                //������ ������� ���� � ��� json �� �������� �������� ����� data
                JSONArray urls = json.getJSONArray("documents");
                //�������� ������ �� ���� ����� ����������
                for (int i = 0; i < urls.length(); i++) {
                        HashMap<String, Object> hm;
                        hm = new HashMap<String, Object>();
                        //������ ��� � ���� ������ �������� firstname
                        hm.put(deliverycost, urls.getJSONObject(i).getString("DELIVERY COST").toString());
                        Log.d("deliverycost", urls.getJSONObject(i).getString("DELIVERY COST").toString() + "");
                        hm.put(inquiryid, urls.getJSONObject(i).getString("INQUIRY ID").toString());
                        Log.d("inquiryid", urls.getJSONObject(i).getString("INQUIRY ID").toString() + "");
                        
                        CreateInq.add(hm);
                        
                   
                      
                }
        } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
        }
}
	
/////////////////////////////////////////////////// 4 step //////////////////////////////////////////////////////////	
	
	
	// ���� ������ ������ ����������� ������
			public void onClickOkInquiry(View v)
			{
			}
			
		// ���� ������ ������ �������� ������
		public void onClickCancelInquiry(View v)
		{
			this.finish();
		}
		
		public void onStop () {
			//do your stuff here
			super.onStop(); 
			}
} 
