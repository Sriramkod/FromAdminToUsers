package com.example.bybook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper_lr mDatabaseHelper;
    EditText et1,et2,et3,et4,et5,et6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mDatabaseHelper = new DatabaseHelper_lr(this);
        et1 = findViewById(R.id.name);
        et2 = findViewById(R.id.email);
        et3=findViewById(R.id.phone);
        et4=findViewById(R.id.pass);
    }

    public void AddData(String cid,String name,String email,String phone,String pass) {

        try {
            if (cid != null && name  != null && email  != null&& phone  != null && pass!=null) {
                boolean insertData = mDatabaseHelper.addData(cid,name,email,phone,pass);

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

    public void Register(View view) {
        AddData("CID_1",et1.getText().toString(),et2.getText().toString(),et3.getText().toString(),et4.getText().toString());
    }
}