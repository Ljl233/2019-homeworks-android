package com.mini.homeworks.MyAssign;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.mini.homeworks.AssignDetail.DetailActivity;
import com.mini.homeworks.MainActivity.TaskBean;
import com.mini.homeworks.MainActivity.TaskService;
import com.mini.homeworks.R;
import com.mini.homeworks.Utils.RetrofitWrapper;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAssign extends AppCompatActivity {

    private RecyclerView rv_myassign;
    private Toolbar tb_myassgin;
    List<TaskBean.AssignListBean> tasklist, tmptasklist;
    TaskBean.AssignListBean[] task;
    private MyDatabaseHelper dbHelper;
    private List<Overhead> overheads;
    private List<Delete> deletes;

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
        initRV();

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

    private void initRV() {

        final List<AssignBean> assignlist = new ArrayList<>();
        AssignBean[] assign;

        for( int i = 0 ; i < overheads.size() ; i++ ) {
            AssignBean tmp = new AssignBean();
            tmp.setSiteId(overheads.get(i).getSiteId());
            tmp.setBeginTime(overheads.get(i).getBeginTime());
            tmp.setEndTime(overheads.get(i).getEndTime());
            tmp.setAssignName(overheads.get(i).getAssignName());
            tmp.setStatus(overheads.get(i).getStatus());
            tmp.setDatatype(0);
            assignlist.add(tmp);
        }
        for( int i = 0 ; i < tasklist.size() ; i++ ) {
            AssignBean tmp = new AssignBean();
            tmp.setSiteId(overheads.get(i).getSiteId());
            tmp.setBeginTime(overheads.get(i).getBeginTime());
            tmp.setEndTime(overheads.get(i).getEndTime());
            tmp.setAssignName(overheads.get(i).getAssignName());
            tmp.setStatus(overheads.get(i).getStatus());
            tmp.setDatatype(1);
            assignlist.add(tmp);
        }
        assign = (AssignBean[]) assignlist.toArray();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_myassign.setLayoutManager(layoutManager);
        AssignAdapter courseAdapter = new AssignAdapter(assignlist);
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

    private void initData(int total) {
        SQLiteDatabase db = LitePal.getDatabase();
        overheads = LitePal.findAll(Overhead.class);
        deletes = LitePal.findAll(Delete.class);
        for ( int i = 0 ; i < total ; i++ ) {
            for ( int j = 0 ; j < deletes.size() ; j++ ) {
                if ( tasklist.get(i).getSiteId() == deletes.get(j).getSiteId() )
                    tasklist.remove(i);
            }
            for ( int j = 0 ; j < overheads.size() ; j++ ) {
                if (tasklist.get(i).getSiteId() == deletes.get(j).getSiteId() )
                    tasklist.remove(i);
            }
        }
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
