package com.example.afpa.dahouetregresults.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.afpa.dahouetregresults.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by afpa on 02/03/17.
 */

public class ResultAdapter extends ArrayAdapter<Result> {


    public ResultAdapter(Context context, List<Result> res) {
        super(context, 0, res);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data Avis for this position
        Result res = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.resultatitems, parent, false);
        }

        // Ajoute le resultat
        final TextView resPlaceTxt = (TextView) convertView.findViewById(R.id.resPlaceTxt);
        final TextView resNomVoiTxt = (TextView) convertView.findViewById(R.id.resNomVoiTxt);
        final TextView resNumVoiTxt = (TextView) convertView.findViewById(R.id.resNumVoiTxt);
        final TextView resSkiTxt = (TextView) convertView.findViewById(R.id.resSkiTxt);
        resPlaceTxt.setText(Integer.toString(res.getRes_place())); //Obligation de cast en string pour setText
        resNomVoiTxt.setText(res.getNom_voilier());
        resNumVoiTxt.setText(Integer.toString(res.getNum_voile()));
        resSkiTxt.setText(res.getSkipper());
        return convertView;
    }
}
