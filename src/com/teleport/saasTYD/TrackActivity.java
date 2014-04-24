package com.teleport.saasTYD;

// http://dajver.blogspot.com/2013/02/json.html
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class TrackActivity extends Activity {
	public ProgressBar pb;
	public static String JsonURL;
	public static String SETID;
    private static ArrayList<HashMap<String, Object>> myBooks;
	// JSON Node names
    private static final String systemstatus = "system status";
    private static final String deliverystatus = "delivery status";
    private static final String statustime = "status time";
 // ��� ������������� �� � views � � TextView
    String ss = "...";
    String ds = "...";
    String time = "...";
    public ListView listView;
    MainActivity ma;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track);

		 // ����������� � ��������� �������� ID ������ � ����
		pb=(ProgressBar)findViewById(R.id.progressBar1);
	//	pb.setVisibility(View.VISIBLE);
	//	pb.setVisibility(View.GONE);
		

		pb.setVisibility(View.VISIBLE);
       	Log.e("pb visibility", "VISIBLE");
		
		listView = (ListView) findViewById(R.id.list);
		// ��������� � �������������
//		new Trackasynk().execute();
		myBooks = new ArrayList<HashMap<String, Object>>();
        //��������� �������� ������� �� ��������� � manActivity
        Bundle extras = getIntent().getExtras();
        //���������� � ��� ������ ��� ��������
        String json = extras.getString(JsonURL);
 //       Log.d("json", json + "");
       
        //�������� � ����� ��������
        JSONURL(json);
        

       
		TextView txtInfo = (TextView)findViewById(R.id.TextView04);
		String edtrackID = ("" + R.string.orderid);
		edtrackID = getIntent().getExtras().getString("edtrackID");
	    txtInfo.setText(edtrackID);
	    
	    loadSavedPreferences();

	  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.track, menu);
		return true;
	}
    
	
    /** @param result */
	     public void JSONURL(String result) {

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
                            hm.put(systemstatus, urls.getJSONObject(i).getString("system status").toString());
                            Log.d("systemstatus", urls.getJSONObject(i).getString("system status").toString() + "");
                            hm.put(deliverystatus, urls.getJSONObject(i).getString("delivery status").toString());
                            Log.d("deliverystatus", urls.getJSONObject(i).getString("delivery status").toString() + "");
                            hm.put(statustime, urls.getJSONObject(i).getString("status time").toString());
                            Log.d("statustime", urls.getJSONObject(i).getString("status time").toString() + "");
                            myBooks.add(hm);
                            //������ ��������� ���������� ��������� � ��� �������
                            SimpleAdapter adapter = new SimpleAdapter(TrackActivity.this, myBooks, R.layout.list,
                                            new String[] { systemstatus, deliverystatus, statustime, }, new int[] { R.id.text1, R.id.text2, R.id.text3,});
                            //������� � �������
                            listView.setAdapter(adapter);
                            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                       
                            pb.setVisibility(View.GONE);
                          	Log.e("pb visibility", "GONE");
                    }
            } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
            }
    } 
	
	
	


    public void SETID (String result) {

    	TextView txtInfo = (TextView)findViewById(R.id.TextView04);
		String edtrackID = ("" + R.string.orderid);
		edtrackID = getIntent().getExtras().getString("edtrackID");
	    txtInfo.setText(edtrackID);
}

    private void loadSavedPreferences() {
    	SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    	String name = sharedPreferences.getString("ID", "inquiryID");
    	TextView txtInfo = (TextView)findViewById(R.id.TextView04);
    	 txtInfo.setText(name);
    	 Log.e("ID in TrackActivity",name);}

	
    // ��������� ������� ������� ����
 	@Override
 	public boolean onMenuItemSelected(int featureId, MenuItem item) {
 		switch (item.getItemId()) {
 		case R.id.menu_about: {
 			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://teleport-ds.com/"));
 			startActivity(browserIntent);
 			break;
 		}


 		case R.id.menu_exit: {
 			onDestroy();  
 			break;
 		}		
 		}
 		return super.onMenuItemSelected(featureId, item);
 	}  
 	
	class Trackasynk extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

                return null;
        }

        @Override
        protected void onPostExecute(String result) {
        
          // // ��� ������������� �� � views � � TextView
    //        TextView sso = (TextView) findViewById(R.id.textView8);
    //        TextView dso = (TextView) findViewById(R.id.textView9);
    //        TextView timeo = (TextView) findViewById(R.id.textView10);
    //        sso.setText(ss);
    //        dso.setText(ds);
    //        timeo.setText(time);
        	// ���������� ����
    //    	TextView ss = (TextView) findViewById(R.id.textView8);
    //		ss.setVisibility(View.VISIBLE);
    //		TextView ds = (TextView) findViewById(R.id.textView9);
    //		ds.setVisibility(View.VISIBLE);
    //		TextView time = (TextView) findViewById(R.id.textView10);
    //		time.setVisibility(View.VISIBLE);
//      	pb.setVisibility(View.GONE);
//      	Log.e("pb visibility", "GONE");
    		
                super.onPostExecute(result);
        }
        
        protected void onProgressUpdate(Integer... progress){
        //	pb.setProgress(progress[0]);
		}

        @Override
        protected void onPreExecute() {
 //      	pb.setVisibility(View.VISIBLE);
 //      	Log.e("pb visibility", "VISIBLE");
                super.onPreExecute();
        }
}
	// ��� ������������� �� � views � � TextView
	/** public void JSONURL(String result) {

        try {
                //������� �������� json �������� � ������ ��� ������ - result
                JSONObject json = new JSONObject(result);

                ss = (String) json.get("system status");
            	Log.d("ss", (String) json.get("system status") + "");
            	ds = (String) json.get("delivery status");
            	Log.d("ds", (String) json.get("delivery status") + "");
            	time = (String) json.get("status time");
            	Log.d("time", (String) json.get("status time") + "");
                //	String XCSRFToken = (String) json.get("token");
                //	Log.d("XCSRFToken", (String) json.get("token") + "");
              
        } catch (JSONException e) {
                Log.e("log_tag", "Error parsing data " + e.toString());
        }
} */
 	
 	
}
