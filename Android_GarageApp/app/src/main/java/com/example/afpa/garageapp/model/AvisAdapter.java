package com.example.afpa.garageapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.afpa.garageapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by afpa on 14/02/17.
 */

public class AvisAdapter extends ArrayAdapter<Avis> {


    public AvisAdapter(Context context, List<Avis> avis) {
        super(context, 0, avis);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data Avis for this position
        Avis avis = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.avisitems, parent, false);
        }

        // Ajoute l'avis
        final TextView comment = (TextView) convertView.findViewById(R.id.avisItemTxt);
        comment.setText(avis.getAvis());

        //Ajoute la date (SimpleDateFormat sert à caster le String reçu en date sans h/min/sec)
        final TextView postedDate = (TextView) convertView.findViewById(R.id.postedDateTxt);
        String test = avis.getPostedDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date postedDateTmp = sdf.parse(test);
            postedDate.setText("le " + sdf.format(postedDateTmp));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
