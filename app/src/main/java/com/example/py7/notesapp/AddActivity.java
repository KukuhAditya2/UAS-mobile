package com.example.py7.notesapp;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.awt.Button;
import java.awt.Checkbox;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.SpinnerModel;
import javax.swing.plaf.basic.BasicBorders;

public class AddActivity extends AppCompatActivity {

    DBHelper helper;
    EditText TxTitle, TxDetail;
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        helper = new DBHelper(this);

        id = getIntent().getLongExtra(DBHelper.row_id, 0);

        nama_lapangan = (EditText)findViewById(R.id.nama_lapangan);
        jenis_lapangan = (RadioGroup)findViewById(R.id.jenis_lapangan);
        lokasi_lapangan = (SpinnerModel)findViewById(R.id.lokasi_lapangan);
        tersedia_lapangan = (Checkbox)findViewById(R.id.tersedia_lapangan_container);
        btnInsert = (Button)findViewById(R.id.button_insert)
        btnView = (Button)findViewById(R.id.button_view)

    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.save_add:
                String title = TxTitle.getText().toString().trim();
                String detail = TxDetail.getText().toString().trim();

                //Get Date
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDate = new SimpleDateFormat("MMM dd, yyyy");
                String created = simpleDate.format(calendar.getTime());

                ContentValues values = new ContentValues();
                values.put(DBHelper.row_title, title);
                values.put(DBHelper.row_note, detail);
                values.put(DBHelper.row_created, created);

                //Create Condition if Title and Detail is empty
                if (title.equals("") && detail.equals("")){
                    Toast.makeText(AddActivity.this, "Nothing to save", Toast.LENGTH_SHORT).show();
                }else{
                    helper.insertData(values);
                    Toast.makeText(AddActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
