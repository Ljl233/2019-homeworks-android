package com.mini.homeworks.Notification;

        import android.app.AlarmManager;
        import android.app.PendingIntent;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;

        import java.util.Date;

public class AlarmTimer {
    //设置周期性闹钟
    public static void setRepeatingAlarmTimer(Context context, long firstTimer, long cycTimer,
                                              String action, int AlarmManagerType){
        Intent myIntent = new Intent();
        myIntent.setAction(action);
        PendingIntent sender = PendingIntent.getBroadcast(context,0,myIntent,0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManagerType,firstTimer,cycTimer,sender);
    }

    //设置定时闹钟
    public static void setAlarmTimer(Context context, long cycTime, String action, int AlarmManagerType, Date date){
        Intent myIntent = new Intent();
        //传递定时日期
        myIntent.putExtra("date",date);
        myIntent.setAction(action);
        //给每个闹钟设置不同ID防止覆盖
        PendingIntent sender = PendingIntent.getBroadcast(context,1,myIntent,0);
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManagerType,cycTime,sender);
    }
    //取消闹钟
    public static void cancelAlarmTimer(Context context, String action){
        Intent myIntent = new Intent();
        myIntent.setAction(action);
        PendingIntent sender = PendingIntent.getBroadcast(context,0,myIntent,0);
        AlarmManager alarmManager=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }


}
