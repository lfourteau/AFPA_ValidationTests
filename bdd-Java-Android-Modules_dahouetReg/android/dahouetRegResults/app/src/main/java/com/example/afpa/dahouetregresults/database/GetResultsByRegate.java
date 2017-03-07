package com.example.afpa.dahouetregresults.database;

import android.os.AsyncTask;

import com.example.afpa.dahouetregresults.model.Regate;
import com.example.afpa.dahouetregresults.model.Result;

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

public class GetResultsByRegate extends AsyncTask<String, Void, List<Result>> {
    //url de l'API
    private final String link = "http://10.105.49.67:8080/api/v1/result/regate/";

    @Override
    protected List<Result> doInBackground(String... params) {
        List<Result> resLst = new ArrayList<>();

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
                String reg_libelle = json.getString("reg_libelle");
                int res_place = json.getInt("res_points");
                String nom_voilier = json.getString("voi_nom");
                int num_voile = json.getInt("voi_num_voile");
                String skipper_prenom = json.getString("per_prenom");
                String skipper_nom = json.getString("per_nom");
                String skipper = skipper_prenom + " " + skipper_nom;
                //Création de objet "result"
                Result res = new Result(reg_libelle, res_place, nom_voilier, num_voile, skipper);
                resLst.add(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resLst;
    }
}
