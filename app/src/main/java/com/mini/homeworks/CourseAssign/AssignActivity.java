package com.mini.homeworks.CourseAssign;

import android.content.DialogInterface;
import android.content.Intent;
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

import com.mini.homeworks.AssignDetail.DetailActivity;
import com.mini.homeworks.R;
import com.mini.homeworks.net.RetrofitWrapper;
import com.mini.homeworks.net.Service.CourseAssignListService;
import com.mini.homeworks.net.bean.CourseAssignBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignActivity extends AppCompatActivity {
    private List<CourseAssignBean.DataBean> mAssignList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AssignAdapter assignAdapter;
    Intent i;
    String cookie;
    String token;
    String siteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_main);
        i = this.getIntent();
        cookie = i.getStringExtra("cookie");
        token = i.getStringExtra("token");
        siteId = i.getStringExtra("siteId");

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
        CourseAssignListService courseAssignListService = RetrofitWrapper.getInstance().create(CourseAssignListService.class);
        Call<CourseAssignBean> call = courseAssignListService.getTasksBean(siteId, cookie, token);
        call.enqueue(new Callback<CourseAssignBean>() {
            @Override
            public void onResponse(Call<CourseAssignBean> call, Response<CourseAssignBean> response) {
                if (response.isSuccessful()) {
                    mAssignList = response.body().getData();
                    cookie = response.body().getCookie();
                }
            }

            @Override
            public void onFailure(Call<CourseAssignBean> call, Throwable t) {

                Log.d("loading failure","loading failure");
                AlertDialog.Builder Wrong = new AlertDialog.Builder(AssignActivity.this);
                //      Wrong.setTitle("加载失败");
                Wrong.setMessage("加载失败");
                Wrong.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //   et_userName.getText().clear();
                        // et_password.getText().clear();
                    }
                });
                Wrong.show();
            }
        });
    }

}