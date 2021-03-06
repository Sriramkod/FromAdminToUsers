package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddMultiple extends AppCompatActivity {
    private static final int RESULT_PICK_CONTACT =1;
    private TextView phone;
    private Button select;
    String keyid;
    ArrayList<String> listData = new ArrayList<>();
DatabaseHelper databaseHelper;
    String phoneNo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_multiple);
        phone = findViewById (R.id.phone);
        select = findViewById (R.id.select);
        databaseHelper = new DatabaseHelper(this);
        phone.setText(" ");
        select.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                select.setVisibility(View.INVISIBLE);
                Intent in = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult (in, RESULT_PICK_CONTACT);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Toast.makeText(this, "Failed To pick contact", Toast.LENGTH_SHORT).show();
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;

        try {


            Uri uri = data.getData ();
            cursor = getContentResolver ().query (uri, null, null,null,null);
            cursor.moveToFirst ();
            int phoneIndex = cursor.getColumnIndex (ContactsContract.CommonDataKinds.Phone.NUMBER);
            String phoneName = String.valueOf(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNo = cursor.getString (phoneIndex);

            String contactName = null;

            // querying contact data store
            //Cursor cursor = getContentResolver().query(uriContact, null, null, null, null);

            if (cursor.moveToFirst()) {

                // DISPLAY_NAME = The display name for the contact.
                // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

                contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            }
            //phone.setText (phoneNo);
            phone.append(" "+contactName);

            listData.add(phoneNo);


        } catch (Exception e) {
            e.printStackTrace ();
        }

    }
    public void Submit(View view) {

            AddData(phoneNo);
            Intent intent = new Intent(AddMultiple.this, MainActivity.class);
            startActivity(intent);finish();
    }

    public void AddData(String src) {

        try {
            if (src != null) {
                boolean insertData = databaseHelper.addData(src);

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
public void toastMessage(String st){
    Toast.makeText(this, ""+st, Toast.LENGTH_SHORT).show();
}
}

