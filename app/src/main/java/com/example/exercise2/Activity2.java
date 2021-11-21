package com.example.exercise2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

public class Activity2 extends AppCompatActivity {

    String[] stark_names = {"Jon Snow", "Robb Stark", "Catelyn Stark", "Ned Stark", "Sansa Stark", "Arya Stark", "Bran Stark", "Rickon Stark" };
    int[] stark_images = {R.drawable.jon_snow, R.drawable.robb_stark, R.drawable.arya_stark, R.drawable.ned_stark, R.drawable.sansa_stark, R.drawable.arya_stark, R.drawable.bran_stark,
            R.drawable.rickon_stark };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.list1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListAdapter(this,stark_names, stark_images));
    }
}