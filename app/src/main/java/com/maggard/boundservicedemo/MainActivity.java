package com.maggard.boundservicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//import the MyService java file and method you need to access
import com.maggard.boundservicedemo.MyService.MyLocalBinder;

public class MainActivity extends AppCompatActivity {

    MyService myService;      //get reference to the service class
    boolean isBound = false;  //is the client bound or not?

    /*whenever you bind a service you need an action class
    what is it that you want to happen when you bind and exit?*/
    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {      //what happens when you connect

            MyLocalBinder binder = (MyLocalBinder) service;   // makes the connections
            myService = binder.getService();  //get service to the class
            isBound = true; //the client is now bound to the service so the isBound Boolean is true
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {  //what do you want to happen when you disconnect

            isBound = false; //breaks the bind if the service is disconnected
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ComponentName i = startService(new Intent(this,MyService.class));
        bindService(new Intent(this,MyService.class),myConnection, Context.BIND_AUTO_CREATE);
    }



    public void showTime(View view) {
        String currentTime = myService.getCurrentTime();   //create string variable for time
        TextView timeTextView = (TextView) findViewById(R.id.timeTextView);   //get the timeText resource
        timeTextView.setText(currentTime);   //set the timeTextView to the current time
    }
}
