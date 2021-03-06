package edu.augustana.csc490.animalfinder;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

//acknowledgements
/*
Thanks to Brittney Cox of the EDGE Center for the fox, firefly, and panda graphics
The deer graphics were modified by myself, but the original file came from the
Augustana Web Guild's Art Explosion clipart collection
 */


public class EasyPanda extends ActionBarActivity {
    ImageView mainImage;
    int trackX = 0;
    int trackY = 0;
    int x1, x2;
    int y1, y2;
    int diffX, diffY;
    int density;
    int locationPixelsX, locationPixelsY;
    Chronometer clock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_panda);
        clock = (Chronometer) findViewById(R.id.chronometer1);
        clock.setBase(SystemClock.elapsedRealtime());
        clock.start();
        //comment
        //lets test this!
        setImageListener();
    }

    private void setImageListener(){
        mainImage = (ImageView) findViewById(R.id.mainImage);
        mainImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = MotionEventCompat.getActionMasked(event);
                switch (action) {
                    case (MotionEvent.ACTION_DOWN):
                        capturePress(event);
                        return true;
                    case (MotionEvent.ACTION_UP):
                        Log.d("DEBUG_TAG", "Action was UP");
                        captureRelease(event);
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
        trackX = trackX + diffX;
        trackY = trackY + diffY;
        mainImage.scrollBy(x1-x2,y1-y2);
    }

    private void capturePress(MotionEvent event){
        x1 = (int) event.getX();
        y1 = (int) event.getY();
        DisplayMetrics metrics2 = getResources().getDisplayMetrics();
        density = metrics2.densityDpi;
        locationPixelsX = (trackX + x1) / (density / 160);
        locationPixelsY = (trackY + y1) / (density / 160);
        if(locationPixelsX >= 794 && locationPixelsX <= 831 && locationPixelsY >= 336 && locationPixelsY <= 355){
            clock.stop();
            long time = getTime();
            showWin(time);
        }
    }

    private long getTime(){
        long elapsedMillis = SystemClock.elapsedRealtime() - clock.getBase();
        return elapsedMillis / 1000;
    }

    private void showWin(long time){
        Toast toast2 = Toast.makeText(getApplicationContext(), "You won in " + time + " seconds!", Toast.LENGTH_SHORT);
        toast2.show();
        mainMenu();
    }

    private void mainMenu(){
        Intent menuIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(menuIntent);
    }

    private void restartClock(){
        Intent easyIntent = new Intent(getBaseContext(), EasyPanda.class);
        startActivity(easyIntent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_easy_panda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch(id){
            case R.id.restart_clock:
                restartClock();
                return true;
            case R.id.main_menu:
                mainMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
