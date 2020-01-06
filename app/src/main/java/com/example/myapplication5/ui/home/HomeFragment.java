package com.example.myapplication5.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.myapplication5.CreateRoomActivity;
import com.example.myapplication5.R;
import com.example.myapplication5.stop_watch;

public class HomeFragment extends Fragment {
    private Button btn_start1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_show_stopwhatch_background, container, false);
        btn_start1 = rootView.findViewById(R.id.btn_start1);
        btn_start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), stop_watch.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}