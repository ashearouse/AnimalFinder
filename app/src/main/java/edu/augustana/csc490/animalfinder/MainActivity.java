package edu.augustana.csc490.animalfinder;

import android.app.Activity;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends Activity {
    ImageView mainImage;
    int x1, x2;
    int y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //comment
        //lets test this!
        mainImage = (ImageView) findViewById(R.id.mainImage);
        mainImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);

                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        Log.d("DEBUG_TAG", "Action was DOWN");
                        x1 = (int) event.getX();
                        y1 = (int) event.getY();
                        return true;
                    case (MotionEvent.ACTION_MOVE):
                        Log.d("DEBUG_TAG", "Action was MOVE");
                        x2 = (int) event.getX();
                        y2 = (int) event.getY();
                        mainImage.scrollBy( x1-x2,y1-y2);
                        return true;
                    case (MotionEvent.ACTION_UP):
                        Log.d("DEBUG_TAG", "Action was UP");
                        return true;
                    case (MotionEvent.ACTION_CANCEL):
                        Log.d("DEBUG_TAG", "Action was CANCEL");
                        return true;
                    case (MotionEvent.ACTION_OUTSIDE):
                        Log.d("DEBUG_TAG", "Movement occurred outside bounds " +
                                "of current screen element");
                        return true;
                    default:
                        return onTouchEvent(event);
                }
            }
        });
    }

    private View.OnDragListener imageDragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            mainImage.scrollBy(20,20);
            return true;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
