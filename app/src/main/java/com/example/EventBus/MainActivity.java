package com.example.EventBus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().postSticky(new MyEvent("Hey event subscriber!"));

    }

    public void onStart() {
        super.onStart();
        Log.e(TAG , "onStart" );
        EventBus.getDefault().register(this);
    }

    public void onStop(){
        super.onStop();
        Log.e(TAG , "onStop" );
        EventBus.getDefault().unregister(this);

    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MyEvent event) {
        Log.e(TAG , "Get Event" );
        Toast.makeText(this, "Hey, you got myEvent" + event.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
