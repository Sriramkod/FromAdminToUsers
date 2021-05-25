package com.example.bybook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelper(this);
    }
    public void AddData(String src,String dest,String ar,String dept) {

        try {
            if (src != null && dest  != null && ar  != null&& dept  != null) {
                boolean insertData = mDatabaseHelper.addData(src,dest,ar,dept);

                if (insertData) {
                    toastMessage("Data Successfully Inserted!");
                } else {
                    toastMessage("Something went wrong");
                }
            }
        }
        catch (Exception e)
        {
            toastMessage(" "+e);
        }
    }
    public void toastMessage(String msg){
        Toast.makeText(this, " "+msg, Toast.LENGTH_SHORT).show();
    }

    public void Hello(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
      //  AddData("sriram","ok","ok","ok");
    }
    public void easy(View view) {
        Intent intent = new Intent(MainActivity.this, ListDataActivity.class);
        startActivity(intent);
    }
}