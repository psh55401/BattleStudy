package com.example.myapplication5.ui.shop;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication5.R;
import com.example.myapplication5.ResetPasswordActivity;
import com.example.myapplication5.SignUpActivity;
import com.example.myapplication5.ui.send.SendViewModel;


public class shopfragment extends Fragment {
    private ImageView IV;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_shop, container, false);

        IV = rootView.findViewById(R.id.image_item);
        rootView.findViewById(R.id.btn_cu).setOnClickListener(onClickListener);
        rootView.findViewById(R.id.btn_seven).setOnClickListener(onClickListener);
        rootView.findViewById(R.id.btn_gs).setOnClickListener(onClickListener);
        return rootView;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_gs:
                    IV.setImageResource(R.drawable.gsitem);
                    break;
                case R.id.btn_cu:
                    IV.setImageResource(R.drawable.cuitem);
                    break;
                case R.id.btn_seven:
                    IV.setImageResource(R.drawable.sevenitem);
                    break;
            }
        }
    };
}

