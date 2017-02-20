package com.example.afpa.garageapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.afpa.garageapp.database.FindGarages;
import com.example.afpa.garageapp.database.GetReviews;
import com.example.afpa.garageapp.database.SendComment;
import com.example.afpa.garageapp.database.SendNote;
import com.example.afpa.garageapp.model.Avis;
import com.example.afpa.garageapp.model.AvisAdapter;

import org.json.JSONObject;

import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by afpa on 13/02/17.
 */

public class ReviewsPage extends AppCompatActivity {

    private List<Avis> avisLst;
    String garage_id;
    String globalNote;
    String noteNumber;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviews);
        //Réccupère les champs de la vue
        final TextView garageNameTtl = (TextView) findViewById(R.id.garageNameTtl);
        final TextView noteNumberTxt = (TextView) findViewById(R.id.noteNumberTxt);
        final ListView avisListing = (ListView) findViewById(R.id.posReviewsLst);
        final RatingBar globalNoteRtBar = (RatingBar) findViewById(R.id.reviewsRatingBar);
        final RatingBar giveNoteRatingBar = (RatingBar) findViewById(R.id.giveNoteRatingBar);
        final EditText postCommentEdtTxt = (EditText) findViewById(R.id.postCommentEdtTxt);

        // Reccupère les infos reçues depuis le mainActivityFragment
        Bundle extras = getIntent().getExtras();


        //Titre de la page
        garageNameTtl.setText(extras.getString("garage_name"));


        //Reccupère la liste des avisitems selon l'id du garage (passé en paramètre du execute et reccupéré dans params dans la class GetReviews
        garage_id = extras.getString("garage_id");
        GetReviews avis = new GetReviews();
        avis.execute(garage_id);
        try {
            avisLst = avis.get();
            globalNote = avis.getGlobalnote();
            noteNumber = avis.getNoteNumber();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(avisLst.size());
        AvisAdapter adapter = new AvisAdapter(this, avisLst);
        avisListing.setAdapter(adapter);


        //Reccupère la ratingBar indicateur et la rempli en fonction de la moyenne
        globalNoteRtBar.setRating(Float.parseFloat(globalNote));
        //change la couleur des étoiles en jaune
        LayerDrawable stars = (LayerDrawable) globalNoteRtBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);
        //Ajoute le nbr de note au champs text
        noteNumberTxt.setText("(" + noteNumber + ")");

        //Ecoute sur la ratingBar
        giveNoteRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                //Reccupère le nbr d'étoiles selectionnées
                float note = ratingBar.getRating();
                //Reccupère le context actuel
                Context context = getApplicationContext();
                //Envoie la note en BDD
                SendNote sendNote = new SendNote(context, note, Integer.parseInt(garage_id));
                sendNote.execute();
            }
        });
        //Ecoute sur la validation du champ editText
        postCommentEdtTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    String avis = postCommentEdtTxt.getText().toString();
                    Context context = getApplicationContext();
                    SendComment sendComment = new SendComment(context, Integer.parseInt(garage_id), avis);
                    sendComment.execute();
                    return true;
                }
                return false;
            }
        });
    }
}


