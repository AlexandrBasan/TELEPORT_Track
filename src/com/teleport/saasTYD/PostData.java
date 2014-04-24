package com.teleport.saasTYD;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;


class PostData extends AsyncTask<String, String, String>{
	
	TrackActivity ta;
	MainActivity ma;
	Context mContext;
	public String rfs;
    public PostData (Context context) {
        mContext = context;
    }
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			postData(params[0]);
			return null;
		}
		
		@Override
        protected void onPreExecute() {
                super.onPreExecute();
            	
        }
		protected void onPostExecute(String result){
			super.onPostExecute(result);
			Log.d("postexecuteresult", result + "");
			 Intent i = new Intent(mContext, TrackActivity.class);
			 i.putExtra(TrackActivity.JsonURL, rfs.toString());
			 Log.d("rfs", rfs + "");
//		     mContext.startActivity(i); // call using Context instance
			
			
	//		ta.pb.setVisibility(View.GONE);
			
		}
		protected void onProgressUpdate(Integer... progress){
			ta.pb.setProgress(progress[0]);
		}
 
		
		
		
		public void postData(String valueIWantToSend) {
			// Create a new HttpClient and Post Header
			HttpClient httpclient = new DefaultHttpClient();
			ResponseHandler<String> res = new BasicResponseHandler();
		//	HttpPost httppost = new HttpPost("http://saas.teleport-ds.com/trackorder/id=" + valueIWantToSend);
		//	HttpPost httppost = new HttpPost("http://teleport-manager.pp.ua/documents/" + valueIWantToSend + ".json");
			HttpPost httppost = new HttpPost("http://teleport-manager.pp.ua/entity_json/documents/" + valueIWantToSend);
		//	Log.d("+URL", "http://saas.teleport-ds.com/trackorder" + valueIWantToSend + "");
			Log.d("+URL", "http://teleport-manager.pp.ua/ru/documents/" + valueIWantToSend + ".json");
 
			try {
				// Add your data
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
				nameValuePairs.add(new BasicNameValuePair("ID", valueIWantToSend));
				Log.d("TrackOrderID from PostData", valueIWantToSend + "");
	//			nameValuePairs.add(new BasicNameValuePair("separator", "______________________________________"));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 
				// Execute HTTP Post Request
//				HttpResponse response = httpclient.execute(httppost);
//				Log.d("HTTP Response", response + "");
				//получаем ответ от сервера
				String responsefromserver = httpclient.execute(httppost, res);
				Log.d("HTTP responsefromserver", responsefromserver + "");
				
				rfs = responsefromserver;
 //
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// выводим эксепшн
				System.out.println("Exp=" + e);
			}
			
		}
 
	}