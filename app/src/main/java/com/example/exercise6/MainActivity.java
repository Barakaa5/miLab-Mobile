package com.example.exercise6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("onCreate");
        Button srcButton = findViewById(R.id.src_btn);
        srcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchStock(view);
            }
        });
    }

    public void fetchStock (final View view){
        final StockFetcher fetcher = new StockFetcher(view.getContext());
        final ProgressDialog progressDialog = new ProgressDialog(this);
        EditText nameEditText = (EditText) findViewById(R.id.stockName);
        String ticker = nameEditText.getText().toString();


        fetcher.dispatchRequest(new StockFetcher.StockResponseListener() {
            @Override
            public void onResponse(StockFetcher.StockResponse response) {

                if (response.isError) {
                    progressDialog.hide();
                    Toast.makeText(view.getContext(), "Error while fetching stock price", Toast.LENGTH_LONG);
                    System.out.println("Failed");
                    return;
                }
                else {
                    System.out.println("Success");
                }
                String priceTxt = "Price: " + response.price;
                ((TextView) MainActivity.this.findViewById(R.id.price)).setText(priceTxt);;

            }
        }, ticker);
    }
}