package com.example.guidageparking;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


public class Etat extends AppCompatActivity {
    TextView textEtat;
    Button button_voirEtat;
    EditText editText_etat;
    String numeroPlace;
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat);

        Button button = findViewById(R.id.button_retourEtat);

        button.setOnClickListener(v -> {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        editText_etat = (EditText) findViewById(R.id.editText_etat);
        button_voirEtat = (Button) findViewById(R.id.button_voirEtat);
        textEtat = (TextView) findViewById(R.id.textEtat);

        button_voirEtat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numeroPlace = editText_etat.getText().toString();
                new MyTask().execute();
            }
        });


    }

    public class MyTask extends AsyncTask<Void, Void, Void>
            //<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        //(Void... voids)
        {
            URL url;
            String cible;
            try {
                cible = "http://btsirisinfo.free.fr/parking/getplace.php";
                if (!numeroPlace.isEmpty()) cible=cible+"?noPlace="+numeroPlace;
                url = new URL(cible);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String stringBuffer = bufferedReader.readLine();
                bufferedReader.close();
                result = stringBuffer;
            } catch (IOException e) {
                e.printStackTrace();
                result = e.toString();
                textEtat.setText(result);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            int etat = this.getPlaceParking(result);
            textEtat.setText("cette place :" + result);
            if (etat!=-1) {
                if (etat == 1) {
                    button_voirEtat.setBackgroundColor(Color.parseColor("green"));
                    textEtat.setText("La place " + numeroPlace + " est disponible ");
                }
                else {
                    button_voirEtat.setBackgroundColor(Color.parseColor("red"));
                    textEtat.setText("La place " + numeroPlace + " est indisponible ");
                }
            }else button_voirEtat.setBackgroundColor(Color.parseColor("blue"));
            super.onPostExecute(aVoid);
        }

        public int getPlaceParking(String result) {
            JSONObject laPlace;
            JSONArray tabPlace;
            int etat=0;
            try {
                tabPlace=new JSONArray(result);
                laPlace=new JSONObject(tabPlace.getString(0));
                if (laPlace.getString("libre").compareTo("1")==0){
                    etat=1;
                }
            } catch (JSONException e) {
                etat=-1;
                // e.printStackTrace();
            }
            return etat;
        }


    }
}
