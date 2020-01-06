package com.example.myapplication5.ui.dashboard;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication5.ChatActivity;
import com.example.myapplication5.CreateRoomActivity;
import com.example.myapplication5.R;
import com.example.myapplication5.stop_watch;

import java.util.Calendar;

public class DashboardFragment extends Fragment implements View.OnClickListener{

    Button btn_matchingstart;
    EditText  user_nickname;
    Spinner sp_subject;
    TextView deadlineView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_create_room, container, false);
        deadlineView =(TextView)rootView.findViewById(R.id.room_deadline);
        deadlineView.setOnClickListener(this);
        btn_matchingstart = (Button) rootView.findViewById(R.id.btn_matchingstart);
        user_nickname = (EditText) rootView.findViewById(R.id.user_nickname);
        sp_subject = (Spinner)rootView.findViewById(R.id.sp_subject);
        btn_matchingstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("userName", user_nickname.getText().toString());
                intent.putExtra("subject", sp_subject.getSelectedItem().toString());
                startActivity(intent);
            }
        });
        return rootView;
    }

    int year,month,day;
    public void onClick(View v){
        if(v == deadlineView){
            Calendar c= Calendar.getInstance();
            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                    new DatePickerDialog.OnDateSetListener(){
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                            if(monthOfYear == 0)
                            {
                                monthOfYear += 10;
                            }
                            deadlineView.setText(year+"년"+monthOfYear+"월"+dayOfMonth+"일");

                            Log.d("tag", year+"년"+monthOfYear+"월"+dayOfMonth+"일");
                        }
                    }, year, month, day);
            datePickerDialog.show();
        }
    }
}