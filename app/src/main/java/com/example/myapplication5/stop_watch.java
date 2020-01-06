package com.example.myapplication5;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.myapplication5.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.type.Date;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

//import static com.example.myapplication5.MainActivity.NOTIFICATION_CHANNEL_ID;

public class stop_watch extends AppCompatActivity {

    public static final String NOTIFICATION_CHANNEL_ID = "10001";

    private static final String TAG = "stop_watch";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabase;// ...
    private FirebaseAuth mAuth;


    java.util.Date todays = Calendar.getInstance().getTime();
    DateFormat todayfor = new SimpleDateFormat("yyyy-MM-dd");
    String today = todayfor.format(todays);
    SimpleDateFormat weekfor = new SimpleDateFormat("E");
    String week = weekfor.format(todays);



    private Button TimeRecord;
    private Handler handler = new Handler();
    public int count = 0;
    private boolean IsStartRecord = false;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            TimeRecord.setText(String.format("%02d:%02d:%02d", count / 3600, count % 3600 / 60, count % 60));
            ++count;
            if(count % 10 ==0) {
                NotificationSomethings("사진을 등록해 주세요","1시간이 지났습니다 사진을 채팅창에 등록해 주세요");
            }
            handler.postDelayed(runnable, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        this.setTitle("시간기록");
        setContentView(R.layout.activity_stop_watch);
        TimeRecord = findViewById(R.id.btn_record);
        TimeRecord.setText(String.format("%02d:%02d:%02d", count / 3600, count % 3600 / 60, count % 60));

        TimeRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsStartRecord == false){
                    handler.post(runnable);
                    IsStartRecord = true;
                }
                else{
                    handler.removeCallbacks(runnable);
                    TimeCountInput();
                    IsStartRecord = false;
                }
            }
        });

        //NotificationSomethings("제목","내용");
    }
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
    public void TimeCountInput() {
        String time = Integer.toString(count);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        timeCount newcount = new timeCount(time);
        mDatabase.child("StopWatch").child(user.getUid()).child(week).setValue(newcount);
    }

    public void NotificationSomethings(String title, String text) { //알람

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.putExtra("notificationId", 0); //전달할 값
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK) ;
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,  PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notifications_black_24dp)) //BitMap 이미지 요구
                .setContentTitle(title)
                .setContentText(text)
                // 더 많은 내용이라서 일부만 보여줘야 하는 경우 아래 주석을 제거하면 setContentText에 있는 문자열 대신 아래 문자열을 보여줌
                //.setStyle(new NotificationCompat.BigTextStyle().bigText("더 많은 내용을 보여줘야 하는 경우..."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent) // 사용자가 노티피케이션을 탭시 ResultActivity로 이동하도록 설정
                .setAutoCancel(true);

        //OREO API 26 이상에서는 채널 필요
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            builder.setSmallIcon(R.drawable.ic_launcher_foreground); //mipmap 사용시 Oreo 이상에서 시스템 UI 에러남
            CharSequence channelName  = "노티페케이션 채널";
            String description = "오레오 이상";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName , importance);
            channel.setDescription(description);

            // 노티피케이션 채널을 시스템에 등록
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);

        }else builder.setSmallIcon(R.mipmap.ic_launcher); // Oreo 이하에서 mipmap 사용하지 않으면 Couldn't create icon: StatusBarIcon 에러남

        assert notificationManager != null;
        notificationManager.notify(1234, builder.build()); // 고유숫자로 노티피케이션 동작시킴

    }

}