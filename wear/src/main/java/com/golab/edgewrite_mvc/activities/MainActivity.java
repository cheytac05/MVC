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
import com.golab.edgewrite_mvc.models.EdgeWrite;

public class MainActivity extends Activity {

    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        edgeWrite = EdgeWrite.getInstance();

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



        // Log.d("TouchEvent", "X : " + event.getX() + ", Y : " + event.getY());


        // タッチ動作によって動作を場合分け //
        switch( event.getAction() ){

            // case 画面をタップした場合
            case MotionEvent.ACTION_DOWN:
                Log.d("DownEvent", "down");

                edgeWrite.setFirstEdge(event.getX(), event.getY());

                break;

            case MotionEvent.ACTION_MOVE:
                Log.d("MoveEvent", "move");

                edgeWrite.setEdge(event.getX(), event.getY());

                break;

            case MotionEvent.ACTION_UP:
                Log.d("UpEvent", "up");

                for(int i = 0; i < edgeWrite.getOrderSize(); i++) {

                    String str = String.valueOf(edgeWrite.getEdge(i));
                    
                    if(!str.equals(0))
                        tx.append(str);

                }

                break;



        }

        return true;
    }

    private EdgeWrite edgeWrite;

}
