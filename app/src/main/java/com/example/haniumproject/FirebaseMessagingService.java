package com.example.haniumproject;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.PowerManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {//파이어베이스 메시지를 받기위한 클래스정의

    private static final String TAG="FirebaseMsgService";
    private String msg,title,color;//푸시알림시 수신타이틀,메시지


    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {//메시지가 도착하였을때 호출되는 메소드
        super.onMessageReceived(remoteMessage);
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE );
        @SuppressLint("InvalidWakeLockTag")
        PowerManager.WakeLock wakeLock = pm.newWakeLock( PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG" );
        wakeLock.acquire(3000);//화면 꺼짐상태에서도 메시지를 받기위함

        System.out.println("ㅎㅎ");

        title=remoteMessage.getData().get("title");//String변수에 푸시 타이틀수신값 저장
        msg=remoteMessage.getData().get("body");//String변수에 푸시 메시지 수신값 저장


        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //pendingIntent를 이용하여 푸시 메시지를 상단에 표시
        PendingIntent contentIntent=PendingIntent.getActivity(this,0,new Intent(this,MainActivity.class),0);
        String channelId="Channel ID";
        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(this,channelId).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setVibrate(new long[]{1,1000});

        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)//SDK버전에 따라 CHANNEL 설정
        {
            System.out.println("gggg");
            String channelName="Channel Name";
            NotificationChannel channel=new NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(0,mBuilder.build());

        mBuilder.setContentIntent(contentIntent);
    }
}