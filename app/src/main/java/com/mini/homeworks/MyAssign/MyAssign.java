package com.mini.homeworks.MyAssign;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mini.homeworks.MyAssign.ManangeData;
import com.mini.homeworks.AssignDetail.DetailActivity;
import com.mini.homeworks.MainActivity.TaskBean;
import com.mini.homeworks.MainActivity.TaskService;
import com.mini.homeworks.MyAssign.Bean.Assignment;
import com.mini.homeworks.MyAssign.Bean.Delete;
import com.mini.homeworks.MyAssign.Bean.Normal;
import com.mini.homeworks.MyAssign.Bean.Overhead;
import com.mini.homeworks.R;
import com.mini.homeworks.Utils.RetrofitWrapper;

import org.litepal.LitePal;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAssign extends AppCompatActivity {

    private RecyclerView rv_myassign;
    private Toolbar tb_myassgin;
    List<TaskBean.AssignListBean> tasklist;
    private List<Overhead> overheads;
    private List<Delete> deletes;
    private List<Normal> normal;
    private AssignAdapter courseAdapter;
    private List<Assignment> assignlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_assign);

        initView();
    }

    private void initView() {
        LitePal.initialize(this);
        rv_myassign = findViewById(R.id.rv_myassign);
        initToolbar();
        request_task();
    }

    private void initToolbar(){
        tb_myassgin = findViewById(R.id.tb_myassgin);
        setSupportActionBar(tb_myassgin);
        tb_myassgin.setNavigationIcon(R.mipmap.back_arrow);
        tb_myassgin.setTitle("我的任务");
        tb_myassgin.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                finish();
                return true;
            }
        });
    }

    private void initData(int total) {  //更新数据 每次任务只可能会增加而不会减少
        SQLiteDatabase db = LitePal.getDatabase();
        overheads = LitePal.findAll(Overhead.class);
        deletes = LitePal.findAll(Delete.class);
        normal = LitePal.findAll(Normal.class);
        for ( int i = 0 ; i < total ; i++ ) {
            boolean flag = false;
            for (int j = 0; j < deletes.size(); j++ ) {
                if (tasklist.get(i).getSiteId().equals(deletes.get(j).getSiteId())) {
                    flag = true;
                    i++;
                    break;
                }
            }
            for (int j = 0; j < overheads.size() && !flag; j++ ) {
                if (tasklist.get(i).getSiteId().equals(deletes.get(j).getSiteId())) {
                    flag = true;
                    i++;
                    break;
                }
            }
            for (int j = 0; j < normal.size() && !flag; j++ ) {
                if (tasklist.get(i).getSiteId().equals(normal.get(j).getSiteId())) {
                    flag = true;
                    i++;
                    break;
                }
            }
            if (!flag && i< total ) {
                Normal tmp = new Normal();   //刚刚增加的任务是普通的item
                tmp.setAssignName(tasklist.get(i).getAssignName());
                tmp.setBeginTime(tasklist.get(i).getBeginTime());
                tmp.setEndTime(tasklist.get(i).getEndTime());
                tmp.setSiteId(tasklist.get(i).getSiteId());
                tmp.setStatus(tasklist.get(i).getStatus());
                tmp.setAssignId(tasklist.get(i).getAssignId());
                long now = Instant.now().getEpochSecond();
                //未提交：0，待批阅：1，已驳回：2，已批阅：3
                if (tmp.getEndTime() > now && tmp.getBeginTime() <= now && ( tmp.getStatus() == 0 || tmp.getStatus() == 2 ) )
                    tmp.setColor( Color.parseColor("#039BE5") );
                else if ( tmp.getBeginTime() < now && ( tmp.getStatus() == 1 || tmp.getStatus() == 3 ) )
                    tmp.setColor( Color.parseColor("#3F51B5"));
                else if ( tmp.getEndTime() < now || tmp.getBeginTime() >= now )
                    tmp.setColor( Color.parseColor("#BCBCBC") );
                normal.add(tmp);
                tmp.save();
            }

        }


        for( int i = 0 ; i < overheads.size() ; i++ ) {
            Assignment tmp = new Assignment();
            tmp.setSiteId(overheads.get(i).getSiteId());
            tmp.setBeginTime(overheads.get(i).getBeginTime());
            tmp.setEndTime(overheads.get(i).getEndTime());
            tmp.setAssignName(overheads.get(i).getAssignName());
            tmp.setStatus(overheads.get(i).getStatus());
            tmp.setColor(overheads.get(i).getColor());
            tmp.setType(0);
            assignlist.add(tmp);
        }
        for( int i = 0 ; i < normal.size() ; i++ ) {
            Assignment tmp = new Assignment();
            tmp.setSiteId(normal.get(i).getSiteId());
            tmp.setBeginTime(normal.get(i).getBeginTime());
            tmp.setEndTime(normal.get(i).getEndTime());
            tmp.setAssignName(normal.get(i).getAssignName());
            tmp.setStatus(normal.get(i).getStatus());
            tmp.setColor(normal.get(i).getColor());
            tmp.setType(1);
            assignlist.add(tmp);
        }
        myassign_rv_show(assignlist);
    }


    public void myassign_rv_show( final List<Assignment> assignlist) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_myassign.setLayoutManager(layoutManager);
        courseAdapter = new AssignAdapter(assignlist);
        rv_myassign.setAdapter(courseAdapter);
        courseAdapter.setOnRecyclerViewItemClickListener(new AssignAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MyAssign.this, DetailActivity.class);
                intent.putExtra("siteId", assignlist.get(position - 1).getSiteId());
                startActivity(intent);
            }
        });
    }

    private void request_task() {
        TaskService taskService = RetrofitWrapper.getInstance().create(TaskService.class);
        Call<TaskBean> call = taskService.getTaskBean(getIntent().getStringExtra("cookie"), getIntent().getStringExtra("token"));
        call.enqueue(new Callback<TaskBean>() {
            @Override
            public void onResponse(Call<TaskBean> call, Response<TaskBean> response) {
                if (response.isSuccessful()) {
                    tasklist = response.body().getAssignList();
                    initData(response.body().getTotal());
                } else {

                }
            }

            @Override
            public void onFailure(Call<TaskBean> call, Throwable t) {
            }
        });

    }
}
