package com.example.medicinereminde;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class AddReminder extends AppCompatActivity {
    String c;
    EditText num;EditText nam;
    Button single;
    private String s = null;
    Button multiple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        num = findViewById(R.id.num);nam=findViewById(R.id.name);
        single = (Button)findViewById(R.id.remindMe);
       // multiple = (Button)findViewById(R.id.group);
        single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s="s";
                Intent intent = new Intent(AddReminder.this,AddSingle.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("People",s);
                intent.putExtra("num",num.getText().toString());
                intent.putExtra("name",nam.getText().toString());
                startActivity(intent);
                finish();
            }
        });
        /*multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s="g";
                Intent intent = new Intent(AddReminder.this,AddMultiple.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("People",s);
                startActivity(intent);
            }
        });*/
    }
}