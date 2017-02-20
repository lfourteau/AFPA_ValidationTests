package com.example.afpa.garageapp.database;

import android.os.AsyncTask;

import com.example.afpa.garageapp.model.Garage;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by afpa on 09/02/17.
 */
// Class permettant de se connecter à l'API créée via SLIM pour réccupérer la liste de tous les garages
public class FindGarages extends AsyncTask<String, Void, List<Garage>> {
    //url de l'API
    private final String link = "http://10.105.49.35:8080/api/v1/garage";

    @Override
    protected List<Garage> doInBackground(String... params) {
        List<Garage> garages = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        HttpURLConnection urlConnection;


        try {
            URL url = new URL(link);
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

            //Reccupération du tableau JSON transformé en string
            JSONArray jsonArray = new JSONArray(sb.toString());

            //boucle pour parcourir le JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                //Reccupération de chaque objet JSON
                JSONObject json = jsonArray.getJSONObject(i);
                //Reccupération des données ontenues dans chaque objet JSON
                int id = json.getInt("garage_id");
                String nom = json.getString("nom");
                Double latitude = json.getDouble("latitude");
                Double longitude = json.getDouble("longitude");
                String concessionnaire = json.getString("concessionnaire");
                //Création d'un objet "garage"
                Garage g = new Garage(id, nom, latitude, longitude, concessionnaire);
                //Envoie de l'ogjet dans la list "garages"
                garages.add(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return garages;
    }
}


