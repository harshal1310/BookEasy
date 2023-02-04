package com.harsh1310.bookeasily;

import android.content.Context;
import android.content.SharedPreferences;

public class Credentials {
    private static Context context;
    private static SharedPreferences sharedPreferences;
    private static Credentials instance;
    private  static String pref="TAS";
    public Credentials() {
        sharedPreferences=context.getSharedPreferences(pref,context.MODE_PRIVATE);
    }
    public  static Credentials getInstance(Context con)
    {

        context=con;
        if(instance==null)
        {
            instance =new Credentials();
        }

        return instance;
    }
    public void checkforlogin(String value)
    {
        sharedPreferences.edit().putString("login",value).apply();
    }
    public String getlogin()
    {
        String login=sharedPreferences.getString("login","");
        return login;
    }
    public void setid(String value)
    {
        sharedPreferences.edit().putString("userid",value).apply();
    }
    public String getuserid()
    {
        String getid=sharedPreferences.getString("userid","");
        return  getid;
    }
    public void setpincode(String pin)
    {
            sharedPreferences.edit().putString("Pincode",pin).apply();



    }
    public String getpin()
    {
        String getid=sharedPreferences.getString("Pincode","");
        return  getid;

    }
    public void setname(String name){
        sharedPreferences.edit().putString("Name",name).apply();
    }
    public String getName(){
        String getid=sharedPreferences.getString("Name","");
        return  getid;


    }
    public void setmail(String email){
        sharedPreferences.edit().putString("email",email).apply();
    }
    public String getmail(){
        String getid=sharedPreferences.getString("email","");
        return  getid;


    }
    public void setprofession(String name){
        sharedPreferences.edit().putString("prof",name).apply();
    }
    public String getprofession(){
        String getid=sharedPreferences.getString("prof","");
        return  getid;


    }
    public void setadhar(String name){
        sharedPreferences.edit().putString("adhar",name).apply();
    }
    public String getadhar(){
        String getid=sharedPreferences.getString("adhar","");
        return  getid;


    }

    public void setmobilenum(String name){
        sharedPreferences.edit().putString("mobilenum",name).apply();
    }
    public String getmobile(){
        String getid=sharedPreferences.getString("mobilenum","");
        return  getid;


    }
    public void setcity(String name){
        sharedPreferences.edit().putString("city",name).apply();
    }
    public String getcity(){
        String getid=sharedPreferences.getString("city","");
        return  getid;


    }


    public void setaltnum(String name){
        sharedPreferences.edit().putString("altnum",name).apply();
    }
    public String getaltnum(){
        String getid=sharedPreferences.getString("altnum","");
        return  getid;


    }

    public void setgender(String name){
        sharedPreferences.edit().putString("gender",name).apply();
    }
    public String getgender(){
        String getid=sharedPreferences.getString("gender","");
        return  getid;


    }

    public void setstate(String name){
        sharedPreferences.edit().putString("state",name).apply();
    }
    public String getstate(){
        String getid=sharedPreferences.getString("state","");
        return  getid;


    }

    public void setpass(String name){
        sharedPreferences.edit().putString("pass",name).apply();
    }
    public String getpass(){
        String getid=sharedPreferences.getString("pass","");
        return  getid;


    }

    public void setadres(String name){
        sharedPreferences.edit().putString("address",name).apply();
    }
    public String getadres(){
        String getid=sharedPreferences.getString("address","");
        return  getid;


    }
    public void setservicename(String name){
        sharedPreferences.edit().putString("servicename",name).apply();
    }
    public String getservicename(){
        String getid=sharedPreferences.getString("servicename","");
        return  getid;


    }
    public void checkforsignup(String val)
    {
        sharedPreferences.edit().putString("signup",val).apply();
    }
    public String getsignup()
    {
        return sharedPreferences.getString("signup","");
    }

    public void checkupdate(String val)
    {
        sharedPreferences.edit().putString("update",val).apply();
    }
    public String getupdate()
    {
        return  sharedPreferences.getString("update","");
    }
    public void settypeofuser(String val)
    {
        sharedPreferences.edit().putString("type",val).apply();
    }
    public String gettypeofuser()
    {
        return  sharedPreferences.getString("type","");
    }
    public void setagencyname(String val)
    {
        sharedPreferences.edit().putString("agencyname",val).apply();
    }
    public String getagencyname()
    {
        return  sharedPreferences.getString("agencyname","");
    }
    public void setwname(String val)
    {
        sharedPreferences.edit().putString("workname",val).apply();
    }
    public String getworkname()
    {
        return  sharedPreferences.getString("workname","");
    }


}


