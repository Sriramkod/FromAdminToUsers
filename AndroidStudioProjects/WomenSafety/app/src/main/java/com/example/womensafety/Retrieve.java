package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
public class Retrieve extends AppCompatActivity {
    LinearLayout container;
    int c=0;
    int idd,id_d;
    DatabaseHelper mDatabaseHelper;
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> source = new ArrayList<>();
    ArrayList<String> destination = new ArrayList<>();
    ArrayList<String> arrival = new ArrayList<>();
    ArrayList<String>  departure = new ArrayList<>();
    Cursor data3;Cursor data4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        container = (LinearLayout) findViewById(R.id.container);
        mDatabaseHelper = new DatabaseHelper(this);

        Cursor data = mDatabaseHelper.getData();
        Cursor data1 = mDatabaseHelper.getData();
        Cursor data2 = mDatabaseHelper.getData();
        data3 = mDatabaseHelper.getData();
        data4 = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            c++;
            //listData.add(data.getString(1));
        }

        String[] k = new String[c];
        int ind=0;
        while (data1.moveToNext()) {
            String src= data1.getString(1);
//            String dep = data1.getString(2);
            k[ind++]=src;
            //  Toast.makeText(this, ""+data.getString(2), Toast.LENGTH_SHORT).show();
            //listData.add(data.getString(1));
        }
        while (data2.moveToNext()) {
            id.add(data2.getString(0));
            source.add(data2.getString(1));

        }
        for(int i=0;i<k.length;i++){
            if(k[i]!=null)
                addItems(k[i]);
        }
    }
    public void addItems(String a) {
        LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.item_row, null);

        final TextView vv = (TextView) addView.findViewById(R.id.place);


        final Button del = (Button)addView.findViewById(R.id.delete);
        vv.setText(""+a.toString());

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = vv.getText().toString();
                //String[] a = str.split("-",2);
                while (data4.moveToNext()) {
                    if(data4.getString(1).equalsIgnoreCase(str)){
                        id_d=data4.getInt(0);
                        break;
                    }
//                    idd = data3.getInt(0);
//                    String src= data1.getString(1);
//                    String dep = data1.getString(2);
//                    k[ind++]=src+"-"+dep;
                    //  Toast.makeText(this, ""+data.getString(2), Toast.LENGTH_SHORT).show();
                    //listData.add(data.getString(1));
                }
                ((LinearLayout) addView.getParent()).removeView(addView);
  /*              String str = vv.getText().toString();
                String[] a = str.split("-",2);
*/
               /* int id = source.indexOf(a[0]);
                id++;*/
                Toast.makeText(Retrieve.this, ""+id_d, Toast.LENGTH_SHORT).show();
                mDatabaseHelper.deleteName(id_d);
            }
        });
        container.addView(addView);
    }
}