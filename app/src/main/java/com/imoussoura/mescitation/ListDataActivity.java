package com.imoussoura.mescitation;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        mListView=(ListView) findViewById(R.id.listView);
        databaseHelper=new DatabaseHelper(this);
        
        populateListView();
    }

    private void populateListView() {
        //get the data and append to a list
        Cursor data=databaseHelper.getData();
        ArrayList<String> listData=new ArrayList<>();
        while (data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        ListAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);
    }
}