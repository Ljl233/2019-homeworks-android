package com.mini.homeworks.MyAssign;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.mini.homeworks.AssignDetail.DetailActivity;
import com.mini.homeworks.MyAssign.Bean.mDelete;
import com.mini.homeworks.net.bean.TasksBean;
import com.mini.homeworks.net.Service.TasksService;
import com.mini.homeworks.net.bean.AssignmentBean;
import com.mini.homeworks.MyAssign.Bean.mNormal;
import com.mini.homeworks.MyAssign.Bean.mOverhead;
import com.mini.homeworks.R;
import com.mini.homeworks.net.RetrofitWrapper;

import org.litepal.LitePal;

import java.lang.reflect.Field;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAssignActivity extends AppCompatActivity {

    private RecyclerView rv_myassign;
    private List<TasksBean.AssignListBean> tasklist = new ArrayList<>();
    private List<AssignmentBean> assignlist = new ArrayList<>();
    private String cookie;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_assign);
        //verifyStoragePermissions();//请求文件读写权限
        initView();
        LitePal.deleteAll(mOverhead.class);
        LitePal.deleteAll(mNormal.class);
        LitePal.deleteAll(mDelete.class);
    }

    private void initView() {
        LitePal.initialize(this);
        rv_myassign = findViewById(R.id.rv_myassign);
        initToolbar();
        request_task();

    }

    private void initToolbar(){
        Toolbar tb_myassgin = findViewById(R.id.tb_myassgin);
        setSupportActionBar(tb_myassgin);
        tb_myassgin.setNavigationIcon(R.mipmap.back_arrow);
        tb_myassgin.setTitle("我的任务");
        tb_myassgin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void verifyStoragePermissions() {
    // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.

            ActivityCompat.requestPermissions(MyAssignActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if ( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    initView();
                    Toast.makeText(MyAssignActivity.this,"获取权限成功",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,"权限被拒绝了",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            default:
                break;
        }
        return;
    }

    private void initData( ) {  //更新数据 每次任务只可能会增加而不会减少

        List<mOverhead> mOverheads = LitePal.findAll(mOverhead.class);
        List<mDelete> deletes = LitePal.findAll(mDelete.class);
        List<mNormal> mNormal = LitePal.findAll(mNormal.class);


        for ( int i = 0 ; i < tasklist.size() ; i++ ) {
            boolean flag = false;
            if ( deletes.size() != 0 ) {
                for (int j = 0; j < deletes.size(); j++ ) {
                    if (tasklist.get(i).getAssignId().equals(deletes.get(j).getAssignId())) {
                        flag = true;
                        break;
                    }
                }
            }
            if ( mOverheads.size() != 0 && !flag ) {
                for (int j = 0; j < mOverheads.size(); j++ ) {
                    if (tasklist.get(i).getAssignId().equals(mOverheads.get(j).getAssignId())) {
                        flag = true;
                        break;
                    }
                }
            }
            if ( mNormal.size() != 0 && !flag ) {
                for (int j = 0; j < mNormal.size() ; j++ ) {
                    if (tasklist.get(i).getAssignId().equals(mNormal.get(j).getAssignId())) {
                        flag = true;
                        break;
                    }
                }
            }
            if ( !flag ) {
                mNormal tmp = new mNormal();   //刚刚增加的任务是普通的item
                tmp.setAssignName(tasklist.get(i).getAssignName());
                tmp.setBeginTime(tasklist.get(i).getBeginTime());
                tmp.setEndTime(tasklist.get(i).getEndTime());
                tmp.setSiteId(tasklist.get(i).getSiteId());
                tmp.setStatus(tasklist.get(i).getStatus());
                tmp.setAssignId(tasklist.get(i).getAssignId());
                long now = Instant.now().getEpochSecond()*1000;
                //未提交：0，待批阅：1，已驳回：2，已批阅：3
                if ( tmp.getEndTime() > now && tmp.getBeginTime() <= now && ( tmp.getStatus() == 0 || tmp.getStatus() == 2 ) )
                    tmp.setColor( Color.parseColor("#039BE5") );
                else if ( tmp.getStatus() == 1 || tmp.getStatus() == 3 )
                    tmp.setColor( Color.parseColor("#3F51B5"));
                else if ( tmp.getEndTime() < now && ( tmp.getStatus() == 0 || tmp.getStatus() == 2 ) )
                    tmp.setColor( Color.parseColor("#BCBCBC") );
                mNormal.add(tmp);
                tmp.save();
            }
        }

        for(int i = 0; i < mOverheads.size() ; i++ ) {
            AssignmentBean tmp = new AssignmentBean();
            tmp.setSiteId(mOverheads.get(i).getSiteId());
            tmp.setBeginTime(mOverheads.get(i).getBeginTime());
            tmp.setEndTime(mOverheads.get(i).getEndTime());
            tmp.setAssignName(mOverheads.get(i).getAssignName());
            tmp.setStatus(mOverheads.get(i).getStatus());
            tmp.setColor(mOverheads.get(i).getColor());
            tmp.setAssignId(mOverheads.get(i).getAssignId());
            tmp.setType(0);
            assignlist.add(tmp);
        }
        for(int i = 0; i < mNormal.size() ; i++ ) {
            AssignmentBean tmp = new AssignmentBean();
            tmp.setSiteId(mNormal.get(i).getSiteId());
            tmp.setBeginTime(mNormal.get(i).getBeginTime());
            tmp.setEndTime(mNormal.get(i).getEndTime());
            tmp.setAssignName(mNormal.get(i).getAssignName());
            tmp.setStatus(mNormal.get(i).getStatus());
            tmp.setColor(mNormal.get(i).getColor());
            tmp.setAssignId(mNormal.get(i).getAssignId());
            tmp.setType(1);
            assignlist.add(tmp);
        }
        myassign_rv_show();
    }

    public void myassign_rv_show () {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_myassign.setLayoutManager(layoutManager);
        MyAssignAdapter myAssignAdapter = new MyAssignAdapter(assignlist);
        rv_myassign.setAdapter(myAssignAdapter);
        //Activity需要结果
        myAssignAdapter.setOnUpdateListener(new MyAssignAdapter.UpdateListener(){
            @Override
            public void onUpdateListener() {
                initData();
                rv_myassign.getAdapter().notifyDataSetChanged();
            }
        });
        myAssignAdapter.setOnRecyclerViewItemClickListener(new MyAssignAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MyAssignActivity.this, DetailActivity.class);
                intent.putExtra("siteId", assignlist.get(position).getSiteId());
                intent.putExtra("assignId",assignlist.get(position).getAssignId());
                startActivity(intent);
            }
        });
    }

    private void request_task() {
        GetCookieAndToken();
        TasksService tasksService = RetrofitWrapper.getInstance().create(TasksService.class);
        Call<TasksBean> call = tasksService.getTaskBean(cookie,token);
        call.enqueue(new Callback<TasksBean>() {
            @Override
            public void onResponse(Call<TasksBean> call, Response<TasksBean> response) {
                if (response.isSuccessful()) {
                    tasklist.addAll(response.body().getAssignList());
                    cookie = response.body().getCookie();
                    SaveCookie(cookie);
                    initData();
                } else
                    Toast.makeText(MyAssignActivity.this, "加载失败，请重试", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<TasksBean> call, Throwable t) {
                Toast.makeText(MyAssignActivity.this,"请检查网络连接", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SaveCookie (String cookie) {
        SharedPreferences data = getSharedPreferences("CandT",MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("cookie",cookie);
        editor.apply();
    }

    private void GetCookieAndToken () {
        SharedPreferences data = getSharedPreferences("CandT",MODE_PRIVATE);
        cookie = data.getString("cookie",null);
        token = data.getString("token", null);
    }
}
