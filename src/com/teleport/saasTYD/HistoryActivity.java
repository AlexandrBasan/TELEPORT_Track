package com.teleport.saasTYD;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class HistoryActivity extends Activity {

	private static ArrayList<HashMap<String, Object>> myBooks;
	
	public ListView listView;
	private static final String IDs = "IDs";
	private static final String Times = "Times";
	private static final String costs = "costs";
	private static final String recs = "recs";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_history);

        {          
            {
            	listView = (ListView) findViewById(R.id.list);
            	
            	// ArrayList для listview
            	myBooks = new ArrayList<HashMap<String, Object>>();
            	HashMap<String, Object> hm;
            	hm = new HashMap<String, Object>();
            	
//////////////////////////////////////////////////////////////////////////////////////////////////////////
            	   DatabaseHandler db = new DatabaseHandler(this);
                   
                   /**
                    * CRUD Operations
                    * */
                   // Inserting Contacts
              //     Log.d("Insert: ", "Inserting .."); 
                   // работает нормально, чтобы не захламлять базу отключено
              //     db.addContact(new SQLInquiry("Ravi2", "9100000000"));        

                    
                   // Reading all contacts
                   Log.d("Reading: ", "Reading all contacts.."); 
                   List<SQLInquiry> contacts = db.getAllContacts(); 
                   
                   for (SQLInquiry cn : contacts) {
                   //    String log = "Id: "+cn.getID()+" ,Name: " + cn.getInquiryID() + " ,Phone: " + cn.getInquiryTime();
                	   String log = "Id: "+ cn.getID() + " ,inquiryidforuser from SQL: " + cn.getInquiryID() + " ,inquirytime from SQL: " + cn.getInquiryTime() + " ,getInquirycost from SQL: " + cn.getinquiry_cost();
                           // Writing Contacts to log
                	   Log.d("Name: ", log);
                	   
                	   hm.put("IDs", cn.getInquiryID());
                	   Log.d("IDS: ", cn.getInquiryID());
                	   hm.put("Times", cn.getInquiryTime());
                	   Log.d("Times: ", cn.getInquiryTime());
                	   hm.put("costs", cn.getinquiry_cost());
                	   Log.d("costs: ", cn.getinquiry_cost());
                	   hm.put("recs", cn.getreceiver_fio());
                	   Log.d("recs: ", cn.getreceiver_fio());
                	   
                	   myBooks.add(hm);

                   Log.d("inquiryidforuser_SQL: ", cn.getInquiryID() + "");
                   Log.d("inquirytime_SQL: ", cn.getInquiryTime() + "");
                   Log.d("Inquirycost_SQL: ", cn.getinquiry_cost() + "");
                   Log.d("sender_adress_SQL: ", cn.getsender_adress() + "");
                   Log.d("receiver_fio_SQL: ", cn.getreceiver_fio() + "");
                   Log.d("receiver_adress_SQL: ", cn.getreceiver_adress() + "");
                   Log.d("receiver_phone_SQL: ", cn.getreceiver_phone() + "");

               }


                  SimpleAdapter adapter = new SimpleAdapter(HistoryActivity.this, myBooks, R.layout.historylist,
                          new String[] {  
       //         		  DatabaseHandler.KEY_inquiry_id,
                		  IDs,
                		  Times,
                		  costs,
                		  recs
                		  
       //     			  DatabaseHandler.KEY_inquiry_time,
       //     			  DatabaseHandler.KEY_inquiry_cost,
       //     			  DatabaseHandler.KEY_receiver_fio
            			  },
            			  new int[] { 
                		R.id.inquiryids,
          			    R.id.inquirytimes,
          			    R.id.inquirycosts,
          			    R.id.receiverfios
        			     });
 //                          
                           //выводим в листвбю
                           listView.setAdapter(adapter);
                           listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                	
//////////////////////////////////////////////////////////////////////////////////////////////////////////
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
    
    // обработка событий нажатия меню
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
	
	
} 
