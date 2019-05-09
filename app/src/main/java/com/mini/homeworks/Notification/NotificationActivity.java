package com.mini.homeworks.Notification;

import android.app.AlarmManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;

import com.kyleduo.switchbutton.SwitchButton;
import com.mini.homeworks.MySQLiteOpenHelper;
import com.mini.homeworks.R;
import com.mini.homeworks.net.RetrofitWrapper;
import com.mini.homeworks.net.Service.NotificationService;
import com.mini.homeworks.net.bean.NotificationBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends AppCompatActivity {

    private LinearLayout ll_add;
    private AlertDialog alertDialog;
    public SQLiteDatabase notification_database;
    private RecyclerView recyclerView;
    private SwitchButton switchButton_allow;
    private SwitchButton switchButton_mail;
    public String token, cookie;
    public NotificationAdapter adapter;
    final NotificationService notificationService = RetrofitWrapper.getInstance().create(NotificationService.class);
    public static final int NOTIFICATION_ID = 1;
    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        token = getIntent().getStringExtra("token");
        cookie = getIntent().getStringExtra("cookie");

        //初始化控件
        initView();
        //设置监听事件
        ll_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomizeDialog();
            }
        });

        switchButton_allow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        switchButton_mail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                //邮件提醒启用状态更改
                Call<NotificationBean.MailIsSendModify> mailIsSendModifyCall = notificationService.getIsSend(token);
                mailIsSendModifyCall.enqueue(new Callback<NotificationBean.MailIsSendModify>() {
                    @Override
                    public void onResponse(Call<NotificationBean.MailIsSendModify> call, Response<NotificationBean.MailIsSendModify> response) {
                        switchButton_mail.setChecked(isChecked);
                    }

                    @Override
                    public void onFailure(Call<NotificationBean.MailIsSendModify> call, Throwable t) {

                    }
                });
            }
        });
        updateRecyclerView();


        setNotification();
    }

    private void setNotification() {


        Call<NotificationBean.AssignmentsGet> assignmentsGetCall = notificationService.getAssignments(cookie, token);
        assignmentsGetCall.enqueue(new Callback<NotificationBean.AssignmentsGet>() {
            @Override
            public void onResponse(Call<NotificationBean.AssignmentsGet> call, Response<NotificationBean.AssignmentsGet> response) {
                assert response.body() != null;
                List<NotificationBean.AssignmentsGet.DataBean> dataBeans = response.body().getData();
                int num = dataBeans.size();
                int[] endTimer = new int[num];
                for (int i = 0; i < num; i++) {
                    endTimer[i] = dataBeans.get(i).getEndTime();
                }
                Arrays.sort(endTimer);
                // 时间戳转化为Date
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH;mm;ss");
                Long time = new Long(445555555);
                String d = format.format(time);
                try {
                    Date date = format.parse(d);

                            AlarmTimer.setAlarmTimer(context, getTimeNodes(), "TIMER_ACTION", AlarmManager.ELAPSED_REALTIME, date);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<NotificationBean.AssignmentsGet> call, Throwable t) {

            }

        });

    }

    //初始化控件
    public void initView() {
        Toolbar toolbar_notification = findViewById(R.id.toolbar_notification);
        toolbar_notification.setTitle("消息提醒");
        setSupportActionBar(toolbar_notification);
        ll_add = findViewById(R.id.ll_add);


        //创建SQLiteOpenHelper
        MySQLiteOpenHelper dbhelper = new MySQLiteOpenHelper(this, "allow");
        //创建数据库；
        notification_database = dbhelper.getWritableDatabase();

        recyclerView = findViewById(R.id.rv_notification);
        recyclerView.setClickable(true);

        switchButton_allow = findViewById(R.id.switch_allow);
        switchButton_mail = findViewById(R.id.switch_allow_mail);

        //检查switchButton_allow isCheck？
        if (isDatabaseEmpty()) {
            switchButton_mail.setChecked(false);
            switchButton_allow.setChecked(false);
            ContentValues values = new ContentValues();
            values.put("switch_allow", 0);
            values.put("switchBotton_mail", 1);
            notification_database.insert("notification", null, values);

        } else {
            Cursor cursor = notification_database.query("notification", new String[]{"switch_allow",
                    "name"}, "id=?", new String[]{"1"}, null, null, null);


        }

    }

    //自定义dialog
    private void showCustomizeDialog() {

        final AlertDialog.Builder customizeDialog = new AlertDialog.Builder(NotificationActivity.this);
        final View dialogView = LayoutInflater.from(NotificationActivity.this).inflate(R.layout.notification_dialog, null);
        //设置标题
        customizeDialog.setTitle(null);
        //绑定View对象
        customizeDialog.setView(dialogView);
        //设置按钮
        customizeDialog.setPositiveButton("取消",
                //OnClickListener
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        alertDialog.dismiss();


                    }
                });
        customizeDialog.setNegativeButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //获取EditView中输入的内容
                        EditText editText_day = dialogView.findViewById(R.id.ed_dialog_day);
                        EditText editText_hour = dialogView.findViewById(R.id.ed_dialog_hour);
                        int hour = Integer.valueOf(editText_hour.toString()) + Integer.valueOf(editText_day.toString()) * 24;
                        //添加事件节点
                        Call<NotificationBean.NoticeTimeAdd> noticeTimeAddCall = notificationService.getNoticeTimeAdd(hour, token);
                        noticeTimeAddCall.enqueue(new Callback<NotificationBean.NoticeTimeAdd>() {
                            @Override
                            public void onResponse(Call<NotificationBean.NoticeTimeAdd> call, Response<NotificationBean.NoticeTimeAdd> response) {
                                //刷新RecycleView
                                updateRecyclerView();
                            }

                            @Override
                            public void onFailure(Call<NotificationBean.NoticeTimeAdd> call, Throwable t) {

                            }
                        });
                    }
                });
        customizeDialog.show();

    }

    //刷新RecyclerView
    private void updateRecyclerView() {

        Call<NotificationBean.NoticeConfigGet> noticeConfigGetCall = notificationService.getNoticeConfig(token);
        noticeConfigGetCall.enqueue(new Callback<NotificationBean.NoticeConfigGet>() {
            @Override
            public void onResponse(@NonNull Call<NotificationBean.NoticeConfigGet> call, Response<NotificationBean.NoticeConfigGet> response) {
                assert response.body() != null;
                adapter = new NotificationAdapter(response.body());
                //设置监听事件
                adapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        showPopMenu(view, position);
                    }
                });
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<NotificationBean.NoticeConfigGet> call, Throwable t) {

            }
        });

    }

    public boolean isDatabaseEmpty() {
        boolean flag;
        String sql = "select count(*) from time";
        Cursor cursor = notification_database.rawQuery(sql, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if (count == 0) {
            flag = true;
        } else {
            flag = false;
        }
        cursor.close();
        return flag;
    }

    public void showPopMenu(View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.longclick_delete, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                adapter.removeItem(position);
                deleteNoticeItem(position);
                return false;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {

            }
        });
        popupMenu.show();
    }

    //移除时间节点
    public void deleteNoticeItem(int position) {
        Call<NotificationBean.NoticeTimeDeleteBean> beanCall = notificationService.getNoticeTimeDeleteBean(String.valueOf(position), token);
        beanCall.enqueue(new Callback<NotificationBean.NoticeTimeDeleteBean>() {
            @Override
            public void onResponse(Call<NotificationBean.NoticeTimeDeleteBean> call, Response<NotificationBean.NoticeTimeDeleteBean> response) {

            }

            @Override
            public void onFailure(Call<NotificationBean.NoticeTimeDeleteBean> call, Throwable t) {

            }
        });
    }

    public int getTimeNodes() {
        final int[][] noticeTime = new int[1][1];
        Call<NotificationBean.NoticeConfigGet> configGetCall = notificationService.getNoticeConfig(token);
        configGetCall.enqueue(new Callback<NotificationBean.NoticeConfigGet>() {
            @Override
            public void onResponse(@NonNull Call<NotificationBean.NoticeConfigGet> call, @NonNull Response<NotificationBean.NoticeConfigGet> response) {
                assert response.body() != null;
                List<NotificationBean.NoticeConfigGet.NoticeTimeListBean> listBeans = response.body().getNoticeTimeList();
                noticeTime[0] = new int[listBeans.size()];
                for (int i = 0; i < listBeans.size(); i++) {
                    noticeTime[0][i]=listBeans.get(i).getNoticeTime();
                }
                Arrays.sort(noticeTime[0]);
            }

            @Override
            public void onFailure(Call<NotificationBean.NoticeConfigGet> call, Throwable t) {

            }
        });
        return noticeTime[0][0];
    }


}


