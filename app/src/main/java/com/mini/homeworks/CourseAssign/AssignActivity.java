package com.mini.homeworks.CourseAssign;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mini.homeworks.AssignDetail.DetailActivity;
import com.mini.homeworks.R;
import com.mini.homeworks.net.RetrofitWrapper;
import com.mini.homeworks.net.Service.CourseAssignListService;
import com.mini.homeworks.net.bean.CourseAssignBean;
import com.mini.homeworks.net.bean.CoursesBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignActivity extends AppCompatActivity {
    private List<CourseAssignBean.DataBean> mAssignList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AssignAdapter assignAdapter;
    String cookie;
    String token;
    String siteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_main);
        init();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.task_recycle_view);

        setSupportActionBar(toolbar);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);

        request();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        assignAdapter = new AssignAdapter(mAssignList);
        recyclerView.setAdapter(assignAdapter);

        assignAdapter.setOnItemClickListener(new AssignAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(AssignActivity.this, DetailActivity.class);
                intent.putExtra("siteId", siteId);
                intent.putExtra("assignId", mAssignList.get(position).getAssignId());
                intent.putExtra("cookie", cookie);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
    }


    private void request() {
        GetCookieAndToken();
        siteId = getIntent().getStringExtra("siteId");
        CourseAssignListService courseAssignListService = RetrofitWrapper.getInstance().create(CourseAssignListService.class);
        Call<CourseAssignBean> call = courseAssignListService.getTasksBean(siteId, cookie, token);
        call.enqueue(new Callback<CourseAssignBean>() {
            @Override
            public void onResponse(Call<CourseAssignBean> call, Response<CourseAssignBean> response) {
                if (response.isSuccessful()) {
                    mAssignList = response.body().getData();
                    cookie = response.body().getCookie();
                    SaveCookie(cookie);
                } else
                    Toast.makeText(AssignActivity.this, "加载失败，请重试", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CourseAssignBean> call, Throwable t) {
                Toast.makeText(AssignActivity.this, "请检查网络连接", Toast.LENGTH_SHORT).show();
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