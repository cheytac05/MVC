package com.golab.edgewrite_mvc.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.wearable.view.DismissOverlayView;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import com.golab.edgewrite_mvc.R;

public class MainActivity extends Activity {

    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
            @Override
            public void onLayoutInflated(WatchViewStub stub) {
                mTextView = (TextView) stub.findViewById(R.id.text);
            }
        });
    }



    /**
     * タッチイベントを処理する関数．
     * タッチイベントの種類によってSWITCH文で場合分けを行う．
     * (onTouchEventは上手くいかなかった)
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        TextView tx = (TextView) findViewById(R.id.text01);
        tx.setText("Running");

        // Log.d("TouchEvent", "X : " + event.getX() + ", Y : " + event.getY());


        // タッチ動作によって動作を場合分け //
        switch( event.getAction() ){

            case MotionEvent.ACTION_DOWN:
                Log.d("DownEvent", "down");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d("MoveEvent", "move");
                break;

            case MotionEvent.ACTION_UP:
                Log.d("UpEvent", "up");
                break;



        }

        return true;
    }

}
