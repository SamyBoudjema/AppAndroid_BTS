package com.example.guidageparking;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Plan extends AppCompatActivity {
    String result;

    ArrayList<Button>listeBoutons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);
        Button button = findViewById(R.id.buttonRetour);

        button.setOnClickListener(v -> {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
        listeBoutons=new ArrayList<>();
        //for (int i=0;i<100;i++)
        {
            listeBoutons.add(findViewById(R.id.button));
            listeBoutons.add(findViewById(R.id.button2));
            listeBoutons.add(findViewById(R.id.button3));
            listeBoutons.add(findViewById(R.id.button4));
            listeBoutons.add(findViewById(R.id.button5));
            listeBoutons.add(findViewById(R.id.button6));
            listeBoutons.add(findViewById(R.id.button7));
            listeBoutons.add(findViewById(R.id.button8));
            listeBoutons.add(findViewById(R.id.button9));
            listeBoutons.add(findViewById(R.id.button10));
            listeBoutons.add(findViewById(R.id.button11));
            listeBoutons.add(findViewById(R.id.button12));
            listeBoutons.add(findViewById(R.id.button13));
            listeBoutons.add(findViewById(R.id.button14));
            listeBoutons.add(findViewById(R.id.button15));
            listeBoutons.add(findViewById(R.id.button16));
            listeBoutons.add(findViewById(R.id.button17));
            listeBoutons.add(findViewById(R.id.button18));
            listeBoutons.add(findViewById(R.id.button19));
            listeBoutons.add(findViewById(R.id.button20));
            listeBoutons.add(findViewById(R.id.button21));
            listeBoutons.add(findViewById(R.id.button22));
            listeBoutons.add(findViewById(R.id.button23));
            listeBoutons.add(findViewById(R.id.button24));
            listeBoutons.add(findViewById(R.id.button25));
            listeBoutons.add(findViewById(R.id.button26));
            listeBoutons.add(findViewById(R.id.button27));
            listeBoutons.add(findViewById(R.id.button28));
            listeBoutons.add(findViewById(R.id.button29));
            listeBoutons.add(findViewById(R.id.button30));
            listeBoutons.add(findViewById(R.id.button31));
            listeBoutons.add(findViewById(R.id.button32));
            listeBoutons.add(findViewById(R.id.button33));
            listeBoutons.add(findViewById(R.id.button34));
            listeBoutons.add(findViewById(R.id.button35));
            listeBoutons.add(findViewById(R.id.button36));
            listeBoutons.add(findViewById(R.id.button37));
            listeBoutons.add(findViewById(R.id.button38));
            listeBoutons.add(findViewById(R.id.button39));
            listeBoutons.add(findViewById(R.id.button40));
            listeBoutons.add(findViewById(R.id.button41));
            listeBoutons.add(findViewById(R.id.button42));
            listeBoutons.add(findViewById(R.id.button43));
            listeBoutons.add(findViewById(R.id.button44));
            listeBoutons.add(findViewById(R.id.button45));
            listeBoutons.add(findViewById(R.id.button46));
            listeBoutons.add(findViewById(R.id.button47));
            listeBoutons.add(findViewById(R.id.button48));
            listeBoutons.add(findViewById(R.id.button49));
            listeBoutons.add(findViewById(R.id.button50));
            listeBoutons.add(findViewById(R.id.button51));
            listeBoutons.add(findViewById(R.id.button52));
            listeBoutons.add(findViewById(R.id.button53));
            listeBoutons.add(findViewById(R.id.button54));
            listeBoutons.add(findViewById(R.id.button55));
            listeBoutons.add(findViewById(R.id.button56));
            listeBoutons.add(findViewById(R.id.button57));
            listeBoutons.add(findViewById(R.id.button58));
            listeBoutons.add(findViewById(R.id.button59));
            listeBoutons.add(findViewById(R.id.button60));
        }

        new Plan.MyTask().execute();
    }
//------------------------------------------------------------------------------------
    private class MyTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids)
        {
            URL url;
            String cible;

            try {
                cible = "http://btsirisinfo.free.fr/parking/getparking.php";
                url = new URL(cible);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String stringBuffer = bufferedReader.readLine();
                bufferedReader.close();
                result = stringBuffer;
            } catch (IOException e) {
                e.printStackTrace();
                result = e.toString();

            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
//------------------------------------------------------------------
        @Override
        protected void onPostExecute(Void aVoid) {
            //this.getPlaceParking(result);
            this.publishProgress( );
            super.onPostExecute(aVoid);
            new Plan.MyTask().execute();

        }
//------------------------------------------------------------------
        @Override
        protected void onProgressUpdate(Void... values) {
            this.getPlaceParking(result);
            super.onProgressUpdate(values);
        }
//--------------------------------------------------------------------------
        public <PlaceParking> ArrayList<PlaceParking> getPlaceParking(String result) {

            ArrayList<PlaceParking> places = new ArrayList<PlaceParking>();
            try {
                // On récupère le JSON complet
                JSONArray tabJson = new JSONArray(result);
                // Pour tous les objets on récupère les infos
                for (int i = 0; i < tabJson.length(); i++) {
                    // On récupère un objet JSON du tableau
                    JSONObject laPlace = new JSONObject(tabJson.getString(i));
                    if (laPlace.getString("libre").compareTo("1")==0)
                        listeBoutons.get(i).setBackgroundColor(Color.parseColor("green"));
                    else  listeBoutons.get(i).setBackgroundColor(Color.parseColor("red"));
                }
            } catch (Exception ex) {

            }
            return places;
        }
    }
}