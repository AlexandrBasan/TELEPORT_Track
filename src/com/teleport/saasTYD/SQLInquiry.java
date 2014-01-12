// http://www.androidhive.info/2012/07/android-gps-location-manager-tutorial/
// http://www.vogella.com/tutorials/AndroidSQLite/article.html

package com.teleport.saasTYD;
public class SQLInquiry {
     
    //private variables
    int _id;
    String _inquiry_id;
    String _inquiry_time;
     
    // Empty constructor
    public SQLInquiry(){
         
    }
    // constructor
    public SQLInquiry(int id, String inquiry_id, String _inquiry_time){
        this._id = id;
        this._inquiry_id = inquiry_id;
        this._inquiry_time = _inquiry_time;
    }
     
    // constructor
    public SQLInquiry(String inquiry_id, String _inquiry_time){
        this._inquiry_id = inquiry_id;
        this._inquiry_time = _inquiry_time;
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
}