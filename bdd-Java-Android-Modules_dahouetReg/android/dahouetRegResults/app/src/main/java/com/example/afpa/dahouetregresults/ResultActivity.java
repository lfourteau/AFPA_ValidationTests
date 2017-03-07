package com.example.afpa.dahouetregresults;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.afpa.dahouetregresults.database.GetRegates;
import com.example.afpa.dahouetregresults.database.GetResultsByRegate;
import com.example.afpa.dahouetregresults.model.Regate;
import com.example.afpa.dahouetregresults.model.Result;
import com.example.afpa.dahouetregresults.model.ResultAdapter;

import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by afpa on 02/03/17.
 */

public class ResultActivity extends AppCompatActivity {

    List<Result> resLst;
    String reg_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_page);
        final ListView resultsLst = (ListView) findViewById(R.id.regResultsLst);
        final TextView resRegTitleTxt = (TextView) findViewById(R.id.resRegTitleTxt);

        Bundle extras = getIntent().getExtras();
        reg_id = extras.getString("reg_id");

        GetResultsByRegate data = new GetResultsByRegate();
        data.execute(reg_id);
        try {
            //Rempli la liste garages avec ce qui est reçu
            resLst = data.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        resRegTitleTxt.setText(extras.getString("reg_name"));
        ResultAdapter adapter = new ResultAdapter(this, resLst);
        resultsLst.setAdapter(adapter);
        System.out.println("test");

    }
}
