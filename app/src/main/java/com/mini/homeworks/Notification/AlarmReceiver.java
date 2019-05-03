package com.mini.homeworks.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mini.homeworks.MainActivity.CourseAndTaskActivity;
import com.mini.homeworks.R;

public class AlarmReceiver extends BroadcastReceiver {
    private NotificationManager notificationManager =null;
    private static final int NOTIFICATION_FLAG = 3;
    @Override
    public void onReceive(Context context, Intent intent) {
        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher_round);
        Intent intent1 = new Intent(context, CourseAndTaskActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent1,0);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(bitmap)
                .setTicker("云课堂作业提醒")
                .setContentTitle("云课堂作业提醒")
                .setContentText("您有作业即将截止，快来看一下吧！")
                .setContentIntent(pendingIntent)
                .setNumber(1)
                .getNotification();
        notification.flags|= Notification.FLAG_AUTO_CANCEL;
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_FLAG,notification);
        bitmap.recycle();

    }
}
