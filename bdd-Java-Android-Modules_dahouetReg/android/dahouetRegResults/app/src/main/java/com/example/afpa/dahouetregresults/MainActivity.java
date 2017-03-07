package com.example.afpa.dahouetregresults;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.afpa.dahouetregresults.database.GetRegateById;
import com.example.afpa.dahouetregresults.database.GetRegates;
import com.example.afpa.dahouetregresults.model.Regate;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        AdapterView.OnItemSelectedListener {
    Spinner regSpinner;
    TextView regListTxt;
    TextView regTitleTxt;
    TextView regChaNomTxt;
    TextView regDateTxt;
    TextView regLongTxt;
    private List<Regate> regatesList;
    Button getResultsBtn;
    Regate reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regSpinner = (Spinner) findViewById(R.id.regSpinner);
        regListTxt = (TextView) findViewById(R.id.regListTxt);

        regatesList = new ArrayList();

        regSpinner.setOnItemSelectedListener(this);

        GetRegates data = new GetRegates();
        data.execute();
        try {
            //Rempli la liste garages avec ce qui est reçu
            regatesList = data.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        populateSpinner();
        getResultsBtn = (Button) findViewById(R.id.getResultsBtn);
        getResultsBtn.setOnClickListener(MainActivity.this);


    }

    private void populateSpinner() {

        List<String> labels = new ArrayList();

        regListTxt.setText("Veuillez selectionner votre régate");

        for (int i = 0; i < regatesList.size(); i++) {
            labels.add(regatesList.get(i).getReg_libelle());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        regSpinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        int reg_id = new BigDecimal(regSpinner.getSelectedItemId()).intValueExact() + 1;
        GetRegateById data = new GetRegateById();
        data.execute(Integer.toString(reg_id));
        reg = null;
        try {
            reg = data.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        regTitleTxt = (TextView) findViewById(R.id.regTitleTxt);
        regDateTxt = (TextView) findViewById(R.id.regDateTxt);
        regLongTxt = (TextView) findViewById(R.id.regLongTxt);
        regChaNomTxt = (TextView) findViewById(R.id.regChaNomTxt);


        regTitleTxt.setText(reg.getReg_libelle());
        regChaNomTxt.setText("Challenge " + reg.getCha_nom());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String regDate = sdf.format(reg.getReg_date());
        regDateTxt.setText("Date : " + regDate);
        regLongTxt.setText("Longueur (en miles) : " + String.valueOf(reg.getReg_distance()));

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View v) {
        Intent resultPage = new Intent(this, ResultActivity.class);
//        System.out.println(reg.getReg_id());
        String reg_id = Integer.toString(reg.getReg_id());
        resultPage.putExtra("reg_id", reg_id);
        String res_name = reg.getReg_libelle();
        resultPage.putExtra("reg_name", res_name);
        startActivity(resultPage);

    }

}
