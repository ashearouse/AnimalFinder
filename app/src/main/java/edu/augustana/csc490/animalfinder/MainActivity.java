package edu.augustana.csc490.animalfinder;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

//acknowledgements
/*
Thanks to Brittney Cox of the EDGE Center for the fox, firefly, and panda graphics
The deer graphics were modified by myself, but the original file came from the
Augustana Web Guild's Art Explosion clipart collection
 */


public class MainActivity extends ActionBarActivity {

    Button easy;
    Button medium;
    Button hard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        easy = (Button) findViewById(R.id.easyButton);
        medium = (Button) findViewById(R.id.mediumButton);
        hard = (Button) findViewById(R.id.hardButton);
        setListeners();
    }

    private void setListeners() {
        easy.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               Intent easyIntent = new Intent(getBaseContext(), EasyPanda.class);
               startActivity(easyIntent);
           }
        });

        medium.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent mediumIntent = new Intent(getBaseContext(), MediumPanda.class);
                startActivity(mediumIntent);
            }
        });

        hard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent hardIntent = new Intent(getBaseContext(), HardPanda.class);
                startActivity(hardIntent);
            }
        });
    }


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
