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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.teleport.saasTYD.MainActivity.RequestTask;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CreateActivity extends Activity {
	// JSON Node names
    String deliverycost = "error receiving data";
    String inquiryid = "error receiving data";
    String inquiryidforuser = "error receiving data";
    String viewinquiry = "error receiving data";
    // Validation phone
//    String regexStr9phone = "^[0-9]{9,9}$";
//    String regexStr12phone = "^[0-9]{12,12}$";
    // ������ ��� �����������
    String oauth_consumer_key = "NW8onh2tZvadKKXRrkUVfa7aY44erczL";
    String XCSRFTokenm = "XCSRFToken";
    String XCSRFTokenmifcancelsave = "";
    String login = "apimob@teleport-ds.com";
    String password = "apimob@teleport-ds.comTELEPORT";
    String sessidm = "sessid";
    String session_namem = "session_name";
    // �������� �����
    String �_sender = "";
    String �_sender_adress = "";
    static String �_sender_email = "";
    String �_sender_phone = "";
    String �_receiver = "";
    String �_receiver_adress = "";
    String �_receiver_phone = "";
    String �_weight = "";
    String �_gabarit = "";
    String �_receiver_email = "";
    String �_declarate_walue = "";
    String �_info = "";
    String field_summary_price_prodzd = "0";
    String field_select_the_rate_km_kg = "";
    String type = "appdelivery";
    String field_avtostatus = "������ �������� ���������";
    
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
		// ��������� ������������ sender e-mail 
		isEmailValid(�_sender_email);
		
			
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
	    
	    EditText cw = (EditText) findViewById(R.id.editText4);
	    �_weight = cw.getText().toString();
	    Log.d("�_weight", �_weight);
	    
	    // ���� email ����������� ���������� ��������� ��� ���������
	    if(isEmailValid(�_sender_email)) {
			Log.d("�_sender_email_true", �_sender_email);// something
			// ��������� ����� ������� ���������
			if(csp.getText().length() < 9 || csp.getText().length() > 9 || crp.getText().length() < 12 || crp.getText().length() > 12){
				// TEST - ������ ����� ������� �������� ���������
		//		if(csp.getText().length() < 9){ //5 char min
				Toast.makeText(getApplicationContext(), R.string.phonevalidation, Toast.LENGTH_LONG).show();
				Log.d("c_sender_phone_validation_fail", "<9" + ">9");
				Log.d("c_receiver_phone_validation_fail", "<12" + ">12");// something//Show error
			}
			else{
			    //Do match
			
		
		if(�_sender.matches("") || �_sender_adress.matches("") || �_sender_phone.matches("") || �_receiver_adress.matches("") || �_receiver.matches("") || �_receiver_phone.matches("") || �_sender_email.matches("")  || �_weight.matches("")){

             // out of range
             Toast.makeText(this, R.string.enterfields, Toast.LENGTH_LONG).show();
		}else{
			
		Toast.makeText(getApplicationContext(), R.string.please_wait, Toast.LENGTH_LONG).show();
		
	//    Intent intent = new Intent(CreateActivity.this, MainActivity.class);
	//    startActivity(intent);
	//    Toast.makeText(getApplicationContext(), R.string.dont_work, Toast.LENGTH_LONG).show();
	    
	    
	    Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
	    �_gabarit = mySpinner.getSelectedItem().toString();  
	    Log.d("�_gabarit", �_gabarit);
	    Spinner tarifSpinner = (Spinner) findViewById(R.id.spinner2);
	    field_select_the_rate_km_kg = tarifSpinner.getSelectedItem().toString();  
	    Log.d("field_select_the_rate_km_kg", field_select_the_rate_km_kg);
	    EditText crem = (EditText) findViewById(R.id.editText9);
	    �_receiver_email = crem.getText().toString();
	    Log.d("�_receiver_email", �_receiver_email);
	    EditText cdv = (EditText) findViewById(R.id.editText10);
	    �_declarate_walue = cdv.getText().toString();
	    Log.d("�_declarate_walue", �_declarate_walue);
	    EditText ci = (EditText) findViewById(R.id.editText11);
	    �_info = ci.getText().toString();
	    Log.d("�_info", �_info);
// 1 step Login
	    new RequestTaskLogin().execute("http://saas.teleport-ds.com/document/1.0/user/login.json?oauth_consumer_key=" + oauth_consumer_key, oauth_consumer_key, XCSRFTokenm, login, password);
    	Log.d("+URLLogin", "http://saas.teleport-ds.com/document/1.0/user/login.json?oauth_consumer_key=" + oauth_consumer_key);



		} }
		
	    } else {
			Log.d("�_sender_email_false", �_sender_email);//something else
			Toast.makeText(getApplicationContext(), R.string.senderemailvalidation, Toast.LENGTH_LONG).show();
		}
		
	}
	    
/////////////////////////////////////////////////// 2 step //////////////////////////////////////////////////////////
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
	                        postMethod.addHeader("Cookie ", session_namem + "=" + sessidm );
	                        Log.d("setHeader session", "Cookie :"+ session_namem + "=" + sessidm + "");
	                        //����� ���������� 14 ����������
	                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(14);
	                        //�������� ��������� �� ����� ����������� � �������
	                   //     nameValuePairs.add(new BasicNameValuePair("oauth_consumer_key", params[1]));
	                   //     Log.d("oauth_consumer_key", params[1] + "");
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
	        	// 4 step NI
	        	new RequestTaskC().execute("http://saas.teleport-ds.com/document/1.0/ni?oauth_consumer_key=" + oauth_consumer_key, oauth_consumer_key, XCSRFTokenm, login, password, �_sender, �_sender_adress, �_sender_email, �_sender_phone, �_receiver_adress, �_receiver, �_receiver_phone, �_weight, �_gabarit, �_receiver_email, �_declarate_walue, �_info, field_summary_price_prodzd, field_select_the_rate_km_kg, type);
		        Log.d("+URLCreateinq", "http://saas.teleport-ds.com/document/1.0/ni?oauth_consumer_key=" + oauth_consumer_key);
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
/////////////////////////////////////////////////// 2 step //////////////////////////////////////////////////////////	
/////////////////////////////////////////////////// 1 step //////////////////////////////////////////////////////////
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
			                   //     postMethod.setHeader("X-CSRF-Token ", XCSRFTokenm);
			                   //     Log.d("setHeader X-CSRF-Token login", "X-CSRF-Token:"+ XCSRFTokenm + "");
			                        //����� ���������� 14 ����������
			                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(14);
			                        //�������� ��������� �� ����� ����������� � �������
			                   //     nameValuePairs.add(new BasicNameValuePair("oauth_consumer_key", params[1]));
			                   //     Log.d("oauth_consumer_key", params[1] + "");
			                   //     nameValuePairs.add(new BasicNameValuePair("X-CSRF-Token", params[2]));
			                   //     Log.d("X-CSRF-Token login", params[2] + "");
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
			                    	JSONURLLogin(responselogin);

			                        
			                } catch (Exception e) {
			                        System.out.println("Exp=" + e);
			                        
			                }
			                return null;
			        }

			        @Override
			        protected void onPostExecute(String result) {
			        	// 2 step Token
			        	new RequestTaskXCSRFToken().execute("http://saas.teleport-ds.com/document/1.0/user/token.json?oauth_consumer_key=" + oauth_consumer_key, oauth_consumer_key);
			        	Log.d("+URLtoken", "http://saas.teleport-ds.com/document/1.0/user/token.json?oauth_consumer_key=" + oauth_consumer_key);
			        	
			          
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

			                sessidm = (String) json.get("sessid");
		                	Log.d("sessidm", (String) json.get("sessid") + "");
		                	session_namem = (String) json.get("session_name");
		                	Log.d("session_namem", (String) json.get("session_name") + "");
			                //	String XCSRFToken = (String) json.get("token");
			                //	Log.d("XCSRFToken", (String) json.get("token") + "");
			              
			        } catch (JSONException e) {
			                Log.e("log_tag", "Error parsing data " + e.toString());
			        }
			}
		
		
/////////////////////////////////////////////////// 1 step //////////////////////////////////////////////////////////
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
                  //      postMethod.setHeader("X-CSRF-Token:"+ XCSRFTokenm, "application/json" );
                       postMethod.setHeader("X-CSRF-Token",XCSRFTokenm );
                        Log.d("setHeader X-CSRF-Token ni", "X-CSRF-Token: "+ XCSRFTokenm + "");
                        // �� ��������� ��� ����
                   //     postMethod.setHeader("Cookie: "+ session_namem + "=" + sessidm, "application/json" );
                        postMethod.addHeader("Cookie ", session_namem + "=" + sessidm );
                        Log.d("setHeader Cookie", "Cookie :"+ session_namem + "=" + sessidm + "");
                        // ����� ���������� 14 ����������
                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(14);
                        //�������� ��������� �� ����� ����������� � �������
                 //       nameValuePairs.add(new BasicNameValuePair("oauth_consumer_key", params[1]));
                 //       Log.d("params[1]", params[1] + "");
                //        nameValuePairs.add(new BasicNameValuePair("X-CSRF-Token", params[2]));
                //        Log.d("X-CSRF-Token_params[2]", params[2] + "");
                //        nameValuePairs.add(new BasicNameValuePair("username", params[3]));
                //        Log.d("username_params[3]", params[3] + "");
                //        nameValuePairs.add(new BasicNameValuePair("password", params[4]));
                //        Log.d("password_params[4]", params[4] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_sender[und][0][value]", params[5]));
                        Log.d("�_sender_params[5]", params[5] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_sender_adress[und][0][value]", params[6]));
                        Log.d("�_sender_adress_params[6]", params[6] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_sender_email[und][0][value]", params[7]));
                        Log.d("�_sender_email_params[7]", params[7] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_sender_phone[und][0][value]", params[8]));
                        Log.d("�_sender_phone_params[8]", params[8] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_address_dos_zd[und][0][value]", params[9]));
                        Log.d("�_receiver_adress_params[9]", params[9] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_fio_pol_pos_zd[und][0][value]", params[10]));
                        Log.d("�_receiver_params[10]", params[10] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_phone_pol_zd[und][0][number]", params[11]));
                        Log.d("�_receiver_phone_params[11]", params[11] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_total_weight_zd[und][0][value]", params[12]));
                        Log.d("�_weight_params[12]", params[12] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_clear_parcels_zd[und][0][value]", params[13]));
                        Log.d("�_gabarit_params[13]", params[13] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_e_mail_client[und][0][value]", params[14]));
                        Log.d("�_receiver_email_params[14]", params[14] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_declared_value[und][0][value]", params[15]));
                        Log.d("�_declarate_walue_params[15]", params[15] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_prim_shop[und][0][value]", params[16]));
                        Log.d("�_info_params[16]", params[16] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_summary_price_prodzd[und][0][value]", params[17]));
                        Log.d("field_summary_price_prodzd_params[17]", params[17] + "");
                        nameValuePairs.add(new BasicNameValuePair("field_select_the_rate_km_kg[und][0][value]", params[18]));
                        Log.d("field_select_the_rate_km_kg_params[18]", params[18] + "");
                        nameValuePairs.add(new BasicNameValuePair("type", params[19]));
                        Log.d("type", params[19] + "");
                        //�������� �� ������ � �������� �� ������
                        postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
                        //�������� ����� �� �������
                        String responseni = hc.execute(postMethod, res);
                        Log.d("HTTP response", responseni + "");
                                               
                       
                        // ������������ �������� ������� ���������
                    //	CreateInq = new ArrayList<HashMap<String, Object>>();
                    	//�������� � ����� ��������
                        JSONURLc(responseni);
                        
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
            id.setText(inquiryidforuser);
                
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
                // ���������� ����������� ID ������
                inquiryid = (String) json.get("id");              	
            	Log.d("inquiryid", (String) json.get("id") + "");
            	// �������� ���������� � ������� Integer ������� ����� �������������� ������ � String
            	String deliverydos = String.valueOf(json.get("deliverydos"));              	
            	Log.d("deliverydos", String.valueOf(json.get("deliverydos")) + "");
            	deliverycost = String.valueOf(json.get("cost_delivery"));              	
            	Log.d("deliverycost", String.valueOf(json.get("cost_delivery")) + "");
            	inquiryidforuser = (String) json.get("id_zd");              	
            	Log.d("inquiryidforuser", (String) json.get("id_zd") + "");
            	viewinquiry = String.valueOf(json.get("view_uri"));
            	Log.d("viewinquiry", (String) json.get("id_zd") + "");

            	
        } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
        }
}
/////////////////////////////////////////////////// 4 step //////////////////////////////////////////////////////////	
	
/////////////////////////////////////////////////// Inquiry cancel step //////////////////////////////////////////////////////////	
// ��������� �������� ������
class RequestTaskCancelInq extends AsyncTask<String, String, String> {

@Override
protected String doInBackground(String... params) {

try {
//������� ������ �� ������
DefaultHttpClient hc = new DefaultHttpClient();
ResponseHandler<String> res = new BasicResponseHandler();
//�� � ��� ����� �������� post ������
HttpPut postMethod = new HttpPut(params[0]);
postMethod.setHeader("X-CSRF-Token",XCSRFTokenm );
Log.d("setHeader X-CSRF-Token cancel inquiry", "X-CSRF-Token: "+ XCSRFTokenm + "");
postMethod.addHeader("Cookie ", session_namem + "=" + sessidm );
Log.d("setHeader Cookie", "Cookie :"+ session_namem + "=" + sessidm + "");
// ����� ���������� 14 ����������
List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(14);
nameValuePairs.add(new BasicNameValuePair("field_avtostatus[und][0][value]", params[1]));
Log.d("field_avtostatus[und][0][value]_params[1]", params[1] + "");
//�������� �� ������ � �������� �� ������
postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
//�������� ����� �� �������
String responsenicancel = hc.execute(postMethod, res);
Log.d("HTTP responsenicancel", responsenicancel + "");
// ������������ �������� ������� ���������
//	CreateInq = new ArrayList<HashMap<String, Object>>();
//�������� � ����� ��������
//JSONURLc(responsenicancel);

} catch (Exception e) {
System.out.println("Exp=" + e);

}
return null;
}

@Override
protected void onPostExecute(String result) {

	
super.onPostExecute(result);
}

protected void onProgressUpdate(Integer... progress){  }


@Override
protected void onPreExecute() {
//       	url.pb.setVisibility(View.VISIBLE);
super.onPreExecute();
}
}
/////////////////////////////////////////////////// Inquiry cancel step //////////////////////////////////////////////////////////	
	/**
	 * method is used for checking valid email id format.
	 * 
	 * @param email
	 * @return boolean true for valid false for invalid
	 */
	public static boolean isEmailValid(String email) {
	    boolean isValid = false;
	    
	    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence inputStr = email;
	    
	    Log.d("email", �_sender_email + "");
	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);
	    if (matcher.matches()) {
	        isValid = true;
	    }
	    return isValid;
	}
	
	// ���� ������ ������ ����������� ������
			public void onClickOkInquiry(View v)
			{
				if(deliverycost.matches("error receiving data") || inquiryid.matches("error receiving data")) {
					
					Toast.makeText(getApplicationContext(), R.string.toastproblems, Toast.LENGTH_LONG).show();
					 Log.d("toastproblems", "toastproblems");
				 } else {
						
					 Toast.makeText(this, R.string.inquiryoktoast, Toast.LENGTH_LONG).show();
					 Log.d("inquiryoktoast", "inquiryoktoast");
					}
				// ���� ������������ ����� OK �� ��������� �������� ���������� X-SCRF-Token � �������� ��� ����� ��� ������ ������ �� ����������
				XCSRFTokenmifcancelsave = XCSRFTokenm;
				Log.d("XCSRFTokenmifcancelsave", XCSRFTokenmifcancelsave);
				XCSRFTokenm = "inquiry confirmed";
				Log.d("XCSRFTokenm confirmed", XCSRFTokenm);
			}
			
		// ���� ������ ������ �������� ������
		public void onClickCancelInquiry(View v)
		{
			if(XCSRFTokenm.matches("inquiry confirmed")) {
//				���� ������������ ����� ������� ������ ����� ������������� ������ ���������� �������� ��������� ����� �� �����
				XCSRFTokenm = XCSRFTokenmifcancelsave;
				Log.d("XCSRFTokenm cancel after ok", XCSRFTokenm);
				Toast.makeText(this, R.string.inquirycanceltoast, Toast.LENGTH_LONG).show();
				this.finish();
				
			 } else {
					
				 this.finish();
				}

		}
		
		public void onStop () {
			// �������� ������
			new RequestTaskCancelInq().execute("http://saas.teleport-ds.com/document/1.0/ni/" + inquiryid + ".json?oauth_consumer_key=" + oauth_consumer_key, field_avtostatus);
	        Log.d("+URLRequestTaskCancelInq", "http://saas.teleport-ds.com/document/1.0/ni/" + inquiryid + ".json?oauth_consumer_key=" + oauth_consumer_key);
	       
	        if(XCSRFTokenm.matches("inquiry confirmed")) {
	        	Toast.makeText(this, R.string.inquiryoktoast, Toast.LENGTH_LONG).show();
				 Log.d("inquiryoktoast", "inquiryoktoast");
				
			 } else {
				 Toast.makeText(this, R.string.inquirycanceltoast, Toast.LENGTH_LONG).show();
				}
			//do your stuff here
			super.onStop(); 
			}
} 
