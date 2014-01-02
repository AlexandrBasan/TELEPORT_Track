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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.List;
public class MainActivity extends Activity {

	private InputStream is;
    TrackActivity url;
	public String trackid;
	public String output;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    //    this.getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);

        {          
            {
         
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
	// при клике на кнопку отследить
	public void onClickTrack(View v)
	{
		EditText edtrackID = (EditText) findViewById(R.id.editText1);
		if(edtrackID.getText().toString().length()<1){

             // out of range
             Toast.makeText(this, R.string.enterid, Toast.LENGTH_LONG).show();
		}else{
			
		Toast.makeText(getApplicationContext(), R.string.please_wait, Toast.LENGTH_LONG).show();
		// вызываем асинктаск передачи данных
	
    String ID = edtrackID.getText().toString();  
    Log.d("ID", ID);
    
    // сохраняем значение ID в SharedPreferences
    savePreferences("ID", edtrackID.getText().toString());
    Log.d("savePreferences", edtrackID.getText().toString());
    
		//тут указываем куда будем конектится, для примера я привел удаленных хост если у вас не получилось освоить wamp (:
        new RequestTask().execute("http://saas.teleport-ds.com/api/getm/json/documents/" + edtrackID.getText().toString(), ID);
        Log.d("+URL", "http://saas.teleport-ds.com/api/getm/json/documents/" + edtrackID.getText().toString() + ID);
		// запускаем нашу активити которая отображает данные
//				Intent launchNewIntent = new Intent(MainActivity.this,TrackActivity.class);
        
//				launchNewIntent.putExtra("edtrackID", edtrackID.getText().toString());
//				startActivityForResult(launchNewIntent, 0);

		}
			
	}
	 // сохраняем значение ID в SharedPreferences
	private void savePreferences(String key, String value) {
	SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	Editor editor = sharedPreferences.edit();
	editor.putString(key, value);
	editor.commit();}

	
	class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

                try {
                        //создаем запрос на сервер
                        DefaultHttpClient hc = new DefaultHttpClient();
                        ResponseHandler<String> res = new BasicResponseHandler();
                        //он у нас будет посылать post запрос
                        HttpPost postMethod = new HttpPost(params[0]);
                        //будем передавать два параметра
                        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                        //передаем параметры из наших текстбоксов
                        //лоигн
                        nameValuePairs.add(new BasicNameValuePair("ID", params[1]));
                        Log.d("params[1]", params[1] + "");
                        //собераем их вместе и посылаем на сервер
                        postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                        //получаем ответ от сервера
                        String response = hc.execute(postMethod, res);
                        Log.d("HTTP response", response + "");
                        //посылаем на вторую активность полученные параметры
                        Intent intent = new Intent(MainActivity.this, TrackActivity.class);
                        //то что куда мы будем передавать и что, putExtra(куда, что);
                        intent.putExtra(TrackActivity.JsonURL, response.toString());
                        // не может сконвертировать данные в обект жсон
                     // intent.putExtra(TrackActivity.SETID, params[1].toString());
                        
                                               
                        
                        startActivity(intent);
                } catch (Exception e) {
                        System.out.println("Exp=" + e);
                }
                return null;
        }

        @Override
        protected void onPostExecute(String result) {
  //      	url.pb.setVisibility(View.GONE);
                
                super.onPostExecute(result);
        }
        
        protected void onProgressUpdate(Integer... progress){
			url.pb.setProgress(progress[0]);
		}

        @Override
        protected void onPreExecute() {
 //       	url.pb.setVisibility(View.VISIBLE);
                super.onPreExecute();
        }
}
	
	public String getTrackID(){
		EditText fedtrackID = (EditText) findViewById(R.id.editText1);
		trackid = fedtrackID.getText().toString();
		Log.d("fTrackOrderID", trackid + "");
		return trackid;
	}
}

