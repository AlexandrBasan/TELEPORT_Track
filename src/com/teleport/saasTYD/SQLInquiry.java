// http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
// http://www.vogella.com/tutorials/AndroidSQLite/article.html

package com.teleport.saasTYD;
public class SQLInquiry {
     
	private String comment;
    //private variables
    int _id;
    String _inquiry_id;
    String _inquiry_time;
    String _inquiry_cost;
    String _sender_adress;
    String _receiver_fio;
    String _receiver_adress;
    String _receiver_phone;
     
    // Empty constructor
    public SQLInquiry(){
         
    }
    // constructor
    public SQLInquiry(int id, String inquiry_id, String _inquiry_time, String _inquiry_cost, String _sender_adress, String _receiver_fio, String _receiver_adress, String _receiver_phone){
        this._id = id;
        this._inquiry_id = inquiry_id;
        this._inquiry_time = _inquiry_time;
        this._inquiry_cost = _inquiry_cost;
        this._sender_adress = _sender_adress;
        this._receiver_fio = _receiver_fio;
        this._receiver_adress = _receiver_adress;
        this._receiver_phone = _receiver_phone;
    }
     
    // constructor
    public SQLInquiry(String inquiry_id, String _inquiry_time, String _inquiry_cost, String _sender_adress, String _receiver_fio, String _receiver_adress, String _receiver_phone){
        this._inquiry_id = inquiry_id;
        this._inquiry_time = _inquiry_time;
        this._inquiry_cost = _inquiry_cost;
        this._sender_adress = _sender_adress;
        this._receiver_fio = _receiver_fio;
        this._receiver_adress = _receiver_adress;
        this._receiver_phone = _receiver_phone;
    }
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting inquiry_id
    public String getInquiryID(){
        return this._inquiry_id;
    }
     
    // setting inquiry_id
    public void setInquiryID(String inquiry_id){
        this._inquiry_id = inquiry_id;
    }
     
    // getting inquiry_time
    public String getInquiryTime(){
        return this._inquiry_time;
    }
     
    // setting inquiry_time
    public void setInquiryTime(String inquiry_time){
        this._inquiry_time = inquiry_time;
    }
    
    // getting inquiry_time
    public String getinquiry_cost(){
        return this._inquiry_cost;
    }
     
    // setting inquiry_time
    public void setinquiry_cost(String inquiry_cost){
        this._inquiry_cost = inquiry_cost;
    }
    
    // getting inquiry_time
    public String getsender_adress(){
        return this._sender_adress;
    }
     
    // setting inquiry_time
    public void setsender_adress(String sender_adress){
        this._sender_adress = sender_adress;
    }
    
    // getting inquiry_time
    public String getreceiver_fio(){
        return this._receiver_fio;
    }
     
    // setting inquiry_time
    public void setreceiver_fio(String receiver_fio){
        this._receiver_fio = receiver_fio;
    }
    
    // getting inquiry_time
    public String getreceiver_adress(){
        return this._receiver_adress;
    }
     
    // setting inquiry_time
    public void setreceiver_adress(String receiver_adress){
        this._receiver_adress = receiver_adress;
    }
    
    // getting inquiry_time
    public String getreceiver_phone(){
        return this._receiver_phone;
    }
     
    // setting inquiry_time
    public void setreceiver_phone(String receiver_phone){
        this._receiver_phone = receiver_phone;
    }
    
 // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
      return comment;
    }
}