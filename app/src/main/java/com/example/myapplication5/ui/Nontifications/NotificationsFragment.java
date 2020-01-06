package com.example.myapplication5.ui.Nontifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication5.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class NotificationsFragment extends Fragment {

    BarChart barChart;
    ArrayList<String> dates;
    Random random;
    ArrayList<BarEntry> barEntries;

    private NotificationsViewModel notificationsViewModel;

    public void createRandonBarGraph(String Date1, String Date2){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/DD");
        try {
            Date date1 = simpleDateFormat.parse(Date1);
            Date date2 = simpleDateFormat.parse(Date2);

            Calendar mDate1 = Calendar.getInstance();
            Calendar mDate2 = Calendar.getInstance();
            mDate1.clear();
            mDate2.clear();

            mDate1.setTime(date1);
            mDate2.setTime(date2);

            dates = new ArrayList<>();
            dates = getList(mDate1, mDate2);

            barEntries = new ArrayList<>();
            float max = 0f;
            float value = 0f;
            for(int j = 0; j<dates.size(); j++){
                max = 100f;
                value = random.nextFloat()*max;
                barEntries.add(new BarEntry(value,j));
            }

        } catch (ParseException e){
            e.printStackTrace();
        }

    }

    public ArrayList<String> getList(Calendar startDate, Calendar endDate) {
        ArrayList<String> list = new ArrayList<>();
        while(startDate.compareTo(endDate)<=0){
            list.add(getDate(startDate));
            startDate.add(Calendar.DAY_OF_MONTH,1);
        }
        return list;
    }

    public String getDate(Calendar cld){
        String curDate = cld.get(Calendar.YEAR) + "/" + (cld.get(Calendar.MONTH) + 1) + "/"
                + cld.get(Calendar.DAY_OF_MONTH);
        try{
            Date date = new SimpleDateFormat("yyyy/mm/dd").parse(curDate);
            curDate = new SimpleDateFormat("yyy/MM/dd").format(date);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return curDate;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notification, container, false);
        final BarChart barChart = root.findViewById(R.id.bargraph);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //BarEntry(순서, 값)
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                barEntries.add(new BarEntry(0,5));
                barEntries.add(new BarEntry(1,0));
                barEntries.add(new BarEntry(2,12));
                barEntries.add(new BarEntry(3,8));
                barEntries.add(new BarEntry(4,9));
                barEntries.add(new BarEntry(5,3));
                barEntries.add(new BarEntry(6,3));
                BarDataSet barDataSet = new BarDataSet(barEntries,"Time");

                //이름 삽입
                final String[] quarters = new String[] { "월", "화", "수", "목", "금", "토", "일" };
                ValueFormatter formatter = new ValueFormatter() {
                    @Override
                    public String getAxisLabel(float value, AxisBase axis) {
                        return quarters[(int) value];
                    }
                };
                XAxis xAxis = barChart.getXAxis();
                xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setTextSize(14f);
                xAxis.setValueFormatter(formatter);
                YAxis leftAxis = barChart.getAxisLeft();
                leftAxis.setAxisMinimum(0f);
                YAxis right = barChart.getAxisRight();
                right.setDrawLabels(false); // no axis labels


                BarData theData = new BarData(barDataSet);
                barChart.setData(theData);
                barChart.setTouchEnabled(true);
                barChart.setDragEnabled(true);
                barChart.setScaleEnabled(true);
            }
        });
        return root;
    }
}