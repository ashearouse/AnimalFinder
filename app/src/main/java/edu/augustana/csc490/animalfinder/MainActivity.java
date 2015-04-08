package edu.augustana.csc490.animalfinder;

import android.app.Activity;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static java.security.AccessController.getContext;


public class MainActivity extends Activity {
    ImageView mainImage;
    TextView foundBox;
    int trackX = 0;
    int trackY = 0;
    int x1, x2;
    int y1, y2;
    int diffX, diffY;
    int density;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //comment
        //lets test this!
        setImageListener();
        //setClickListener();
    }

    private void setImageListener(){
        mainImage = (ImageView) findViewById(R.id.mainImage);
        mainImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);


                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        Log.d("DEBUG_TAG", "Action was DOWN");
                        capturePress(event);
                        return true;
                    case (MotionEvent.ACTION_MOVE):
                        Log.d("DEBUG_TAG", "Action was MOVE");

                        return true;
                    case (MotionEvent.ACTION_UP):
                        Log.d("DEBUG_TAG", "Action was UP");
                        captureRelease(event);
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

    private void captureRelease(MotionEvent event){
        x2 = (int) event.getX();
        y2 = (int) event.getY();
        diffX = x1-x2;
        diffY = y1-y2;
        mainImage.scrollBy(x1-x2,y1-y2);
        trackX = trackX + diffX;
        trackY = trackY + diffY;
    }

    private void capturePress(MotionEvent event){
        x1 = (int) event.getX();
        y1 = (int) event.getY();
        DisplayMetrics metrics2 = getResources().getDisplayMetrics();
        density = metrics2.densityDpi;
        Toast toast = Toast.makeText(getApplicationContext(), "x: " + (x1 + trackX) + " y: " + (y1 + trackY),Toast.LENGTH_SHORT);
        toast.show();
        if((trackX + x1) / (density / 160) >= 725 && (trackX + x1) / (density / 160) <= 762 && (trackY + y1) / (density / 160) >= 325 && (trackY + y1) / (density / 160) <= 356){
            Toast toast2 = Toast.makeText(getApplicationContext(), "You won!", Toast.LENGTH_SHORT);
            toast2.show();
        }

    }



    private void setClickListener(){
        foundBox = (TextView) findViewById(R.id.foundPanda);
        foundBox.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Toast toast = Toast.makeText(getApplicationContext(), "You won!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
        );
    }

    private void showWin(){
        Toast toast = Toast.makeText(getApplicationContext(), "You won!", Toast.LENGTH_SHORT);
        toast.show();
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
