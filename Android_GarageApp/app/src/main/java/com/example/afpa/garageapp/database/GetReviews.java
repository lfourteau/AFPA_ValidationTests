package com.example.afpa.garageapp.database;

import android.os.AsyncTask;

import com.example.afpa.garageapp.MainActivityFragment;
import com.example.afpa.garageapp.ReviewsPage;
import com.example.afpa.garageapp.model.Avis;
import com.example.afpa.garageapp.model.Garage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by afpa on 14/02/17.
 */

public class GetReviews extends AsyncTask<String, Void, List<Avis>> {

    String globalnote;
    String noteNumber;


    //url de l'API
    private final String link = "http://10.105.49.35:8080/api/v1/garage/avis/";

    @Override
    protected List<Avis> doInBackground(String... params) {
        List<Avis> avisLst = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        HttpURLConnection urlConnection;


        try {
            URL url = new URL(link + params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "GarageApp");
            //Type de données acceptées
            urlConnection.setRequestProperty("Accept", "application/json");
            //Connection à l'url envoyée
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
                System.out.println("OK");

                //Reccupère la réponse
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                //Lis la réponse
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                //construit la réponse/"création" du JSON grâce au stringBuilder
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                //Si problème lors de la connection à l'url
            } else {
                System.out.println("PAS OK");
            }
            //Deconnection de l'url après construction/reccupération du JSON
            urlConnection.disconnect();

            //Reccupération de l'objet json transformé en string
            JSONObject jsonObject = new JSONObject(sb.toString());
            //Reccupération du tableau d'avis reçu dans l'objet JSON
            JSONArray jsonArray = jsonObject.getJSONArray("avis");
            //boucle pour parcourir le JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                //Reccupération de chaque objet JSON
                JSONObject json = jsonArray.getJSONObject(i);
                //Reccupération des données ontenues dans chaque objet JSON
                int avis_id = json.getInt("avis_id");
                int garage_id = json.getInt("garage_id");
                String avis = json.getString("avis");
                String postedDate = json.getString("parution_date");

                //Création d'un objet "garage"
                Avis a = new Avis(avis_id, garage_id, avis, postedDate);
                //Envoie de l'ogjet dans la list "garages"
                avisLst.add(a);
            }
            //Reccupération de la note moyenne du garage
            globalnote = jsonObject.getString("note");
            noteNumber = jsonObject.getString("noteNumber");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return avisLst;
    }

    public String getGlobalnote() {
        return globalnote;
    }

    public String getNoteNumber() {
        return noteNumber;
    }
}


