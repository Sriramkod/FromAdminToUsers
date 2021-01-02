package com.example.medicinereminde;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
public class AddSingle extends AppCompatActivity {
    int global;
    DatabaseHelper mDatabaseHelper;
    LinearLayout container;
    ListView listview;
    Button addButton;
    EditText GetValue;
    String medname;
    Button speakButton;
    private int notificationId =1;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    String[] ListElements = new String[] {};
    String s = null;
    String[] time;
    TextView tv;
String c;
    ArrayList<String> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_single);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        tv = findViewById(R.id.newk);
        container = (LinearLayout) findViewById(R.id.container);
        mDatabaseHelper = new DatabaseHelper(this);
        //addButton = findViewById(R.id.button);
        //GetValue = findViewById(R.id.editext);
      //  addButton = findViewById(R.id.button);



        Intent i = getIntent();
        medname=i.getStringExtra("name");
        s = i.getStringExtra("People");
c=i.getStringExtra("num");
global=Integer.parseInt(c);
        listData = i.getStringArrayListExtra("list");
String k[]=new String[Integer.parseInt(c)];
        time = new String[Integer.parseInt(c)];
for(int p=0;p<Integer.parseInt(c);p++)
{
    k[p]=String.valueOf(p);
time[p]="Reminder"+String.valueOf(p+1);
}
for(int ikk=0;ikk<Integer.parseInt(c);ikk++) {

    LayoutInflater layoutInflater =
            (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    final View addView = layoutInflater.inflate(R.layout.item_row, null);

    final TextView vv = (TextView) addView.findViewById(R.id.place);

    final Button edit = (Button)addView.findViewById(R.id.edit);
    edit.setId(Integer.parseInt(k[ikk]));
    //final Button del = (Button)addView.findViewById(R.id.delete);
    vv.setText(time[ikk]);
edit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        int selectedHour,selectedMinute;
        final int[] hourk = new int[1];
        final int[] minutek=new int[1];
        Toast.makeText(AddSingle.this, ""+edit.getId(), Toast.LENGTH_SHORT).show();
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(AddSingle.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
int idk = edit.getId();
idk++;
                vv.setText( "Reminder"+idk+" is set at:"+selectedHour + ":" + selectedMinute+" for "+medname+" medicine");
                AddData(vv.getText().toString());
                Intent intent = new Intent(AddSingle.this,Alaram.class);
                intent.putExtra("NotifiactionId",notificationId);
                intent.putExtra("todo",vv.getText().toString());
                intent.putExtra("People",s);
                intent.putStringArrayListExtra("list",listData);
                final int _id = (int) System.currentTimeMillis();
                PendingIntent alaramIntent = PendingIntent.getBroadcast(AddSingle.this, _id, intent,PendingIntent.FLAG_ONE_SHOT);
                AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);

                Calendar startTime  = Calendar.getInstance();

                startTime.set(Calendar.HOUR_OF_DAY,selectedHour);
                startTime.set(Calendar.MINUTE,selectedMinute);
                startTime.set(Calendar.SECOND,0);
                long alaramstartTime = startTime.getTimeInMillis();
                alarm.set(AlarmManager.RTC_WAKEUP,alaramstartTime,alaramIntent);
                //Toast.makeText(this,"Done!",Toast.LENGTH_SHORT).show();
                int id = edit.getId();
                id++;
                Toast.makeText(AddSingle.this, "Reminder "+id+ "has been set", Toast.LENGTH_SHORT).show();
edit.setVisibility(View.GONE);global--;if(global==0){tv.setVisibility(View.VISIBLE);}
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");

        mTimePicker.show();

    }
});
    container.addView(addView);
}
        listData = i.getStringArrayListExtra("list");
       /* addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int c=0;
                String newEntry = GetValue.getText().toString();
                if (GetValue.length() != 0) {
                    //AddData(newEntry);

      //              Pend(c);
                    Intent intent = new Intent(AddSingle.this,MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    // GetValue.setText("");
                } else {
                    toastMessage("You must put something in the text field!");
                }
               /* Pend(c);
                Intent intent = new Intent(AddSingle.this,MainActivity.class);
                startActivity(intent);*/
           // }*/
        //});

    }
    /*public void Pend(int request)
    {

        EditText editText = findViewById(R.id.editext);
        toastMessage("Hello World");
        AddData(editText.getText().toString());
        TimePicker timePicker = findViewById(R.id.timePicker);
        Intent intent = new Intent(AddSingle.this,Alaram.class);
        intent.putExtra("NotifiactionId",notificationId);
        intent.putExtra("todo",editText.getText().toString());
        intent.putExtra("People",s);
        intent.putStringArrayListExtra("list",listData);



        final int _id = (int) System.currentTimeMillis();
        PendingIntent alaramIntent = PendingIntent.getBroadcast(this, _id, intent,PendingIntent.FLAG_ONE_SHOT);

        //PendingIntent alaramIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);
        String title = editText.getText().toString();
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        Calendar startTime  = Calendar.getInstance();

        startTime.set(Calendar.HOUR_OF_DAY,hour);
        startTime.set(Calendar.MINUTE,minute);
        startTime.set(Calendar.SECOND,0);
        long alaramstartTime = startTime.getTimeInMillis();
        alarm.set(AlarmManager.RTC_WAKEUP,alaramstartTime,alaramIntent);
        Toast.makeText(this,"Done!",Toast.LENGTH_SHORT).show();

    }*/

    public void AddData(String newEntry) {

        try {
            if (newEntry != null) {
                boolean insertData = mDatabaseHelper.addData(newEntry);

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

    public void Button(View view) {
        askSpeechInput();
    }
    private void askSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Tell your title");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    // Receiving speech input

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    GetValue.setText(result.get(0));
                }
                break;
            }

        }
    }

    public void New(View view) {
        Intent intent = new Intent(AddSingle.this,AddReminder.class);
        startActivity(intent);
    }
  /*  public void addItems() {
        LayoutInflater layoutInflater =
                (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View addView = layoutInflater.inflate(R.layout.item_row, null);

        final TextView vv = (TextView) addView.findViewById(R.id.place);

        final Button edit = (Button)addView.findViewById(R.id.edit);
       // final Button del = (Button)addView.findViewById(R.id.delete);
        final TimePicker tp = (TimePicker)addView.findViewById(R.id.timePicker);
        //vv.setText(""+a.toString());

        container.addView(addView);
    }*/
}

