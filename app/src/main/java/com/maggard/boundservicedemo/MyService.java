package com.maggard.boundservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale; // we are in the USA and need to specify

public class MyService extends Service {
    //create an object, and that object will be responsible for binding the client to the service
    private final IBinder myBinder = new MyLocalBinder();

    public MyService(){

    }

    //will return the myBinder
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public String getCurrentTime(){
        SimpleDateFormat df = new SimpleDateFormat("MM:mm:ss",Locale.US);
        return (df.format(new Date()));
    }

    //a class that binds the client to the service, when you create a binder you extend from Binder
    //this class will get the service
    public class MyLocalBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

}
