package com.example.afpa.dahouetregresults.database;

import android.os.AsyncTask;

import com.example.afpa.dahouetregresults.model.Regate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by afpa on 02/03/17.
 */

public class GetRegateById extends AsyncTask<String, Void, Regate> {
    //url de l'API
    private final String link = "http://10.105.49.67:8080/api/v1/regate/";

    @Override
    protected Regate doInBackground(String... params) {
        Regate reg = new Regate();

        StringBuilder sb = new StringBuilder();
        HttpURLConnection urlConnection;

        try {
            URL url = new URL(link + params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(10000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "dahouetRegResults");
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


            //Reccupération du tableau d'avis reçu dans l'objet JSON
            JSONArray jsonArray = new JSONArray(sb.toString());
            //boucle pour parcourir le JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                //Reccupération de chaque objet JSON
                JSONObject json = jsonArray.getJSONObject(i);
                //Reccupération des données ontenues dans chaque objet JSON
                int reg_id = json.getInt("reg_id");
                String reg_libelle = json.getString("reg_libelle");
                //Reccupération date de la régate
                String reg_date_string = json.getString("reg_date");
                SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
                Date reg_Date = sdf.parse(reg_date_string);
                double reg_distance = json.getDouble("reg_distance");
                String cha_nom = json.getString("cha_nom");

                //Création de objet "regate"
                reg = new Regate(reg_id, reg_libelle, reg_Date, reg_distance, cha_nom);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return reg;
    }
}
