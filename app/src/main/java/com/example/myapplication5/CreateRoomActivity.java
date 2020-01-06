package com.example.myapplication5;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication5.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class CreateRoomActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText user_nickname;
    TextView deadlineView,locationTextView;
    Spinner sp_subject;
    EditText contentView;
    Button btn_matchingstart;
    String location;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    int timeStamp; // deadline


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);

        user_nickname = (EditText) findViewById(R.id.user_nickname);
        deadlineView = (TextView) findViewById(R.id.room_deadline);
        sp_subject = (Spinner) findViewById(R.id.sp_subject);
        btn_matchingstart = (Button) findViewById(R.id.btn_matchingstart);

        deadlineView.setOnClickListener(this);
        btn_matchingstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user_nickname.getText().toString().equals(""))
                    return;
                Intent intent = new Intent(CreateRoomActivity.this, ChatActivity.class);
                intent.putExtra("subject", sp_subject.getSelectedItem().toString());
                intent.putExtra("userName", user_nickname.getText().toString());
                startActivity(intent);
            }
        });

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Category,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_subject.setAdapter(adapter);
        sp_subject.setOnItemSelectedListener(this);
    }

    /* Spinner OnItemSelectedListener */
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        location = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback}
    }

    int year,month,day;
    public void onClick(View v){
        /*if(v == deadlineView){
            Calendar c= Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener(){
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                            deadlineView.setText(year+"년"+monthOfYear+"월"+dayOfMonth+"일");
                        }
                    }, year, month+1, day);
            datePickerDialog.show();
        }*/
    }


}
