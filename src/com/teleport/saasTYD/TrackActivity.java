package com.teleport.saasTYD;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
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
	private static ArrayList<HashMap<String, Object>> myBooks;
	// JSON Node names
	private static final String Status = "Status";
    private static final String StatusTime = "StatusTime";
    public ListView listView;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_track);
		MainActivity ma;
		
		
		listView = (ListView) findViewById(R.id.list);
		
        myBooks = new ArrayList<HashMap<String, Object>>();
        //принимаем параметр который мы послылали в manActivity
        Bundle extras = getIntent().getExtras();
        //превращаем в тип стринг для парсинга
        String json = extras.getString(JsonURL);
        Log.d("json", json + "");
        //передаем в метод парсинга
        JSONURL(json);
        
		pb=(ProgressBar)findViewById(R.id.progressBar1);
		pb.setVisibility(View.VISIBLE);
		TextView txtInfo = (TextView)findViewById(R.id.TextView04);
		String edtrackID = ("" + R.string.orderid);
		edtrackID = getIntent().getExtras().getString("edtrackID");
	    txtInfo.setText(edtrackID);
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
                    //создали читателя json объектов и отдали ему строку - result
                    JSONObject json = new JSONObject(result);
                    //дальше находим вход в наш json им является ключевое слово data
                    JSONArray urls = json.getJSONArray("data");
                    //проходим циклом по всем нашим параметрам
                    for (int i = 0; i < urls.length(); i++) {
                            HashMap<String, Object> hm;
                            hm = new HashMap<String, Object>();
                            //читаем что в себе хранит параметр firstname
                            hm.put(Status, urls.getJSONObject(i).getString("Status").toString());
                            //читаем что в себе хранит параметр lastname
                            hm.put(StatusTime, urls.getJSONObject(i).getString("StatusTime").toString());
                            myBooks.add(hm);
                            //дальше добавляем полученные параметры в наш адаптер
                            SimpleAdapter adapter = new SimpleAdapter(TrackActivity.this, myBooks, R.layout.list, new String[] { Status, StatusTime }, new int[] { R.id.text1, R.id.text2 });
                            //выводим в листвбю
                           listView.setAdapter(adapter);
                           listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                           // скрываем прогрессбар
                           pb.setVisibility(View.GONE);
                    }
            } catch (JSONException e) {
                    Log.e("log_tag", "Error parsing data " + e.toString());
            }
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


 		case R.id.menu_exit: {
 			onDestroy();  
 			break;
 		}		
 		}
 		return super.onMenuItemSelected(featureId, item);
 	}  
}
