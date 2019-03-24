package com.mini.homeworks.CourseAssign;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.mini.homeworks.Login.CourseBean;
import com.mini.homeworks.MainActivity.CourseAndTaskActivity;
import com.mini.homeworks.R;
import com.mini.homeworks.Utils.RetrofitWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignActivity extends AppCompatActivity {
    private List<TasksBean.DataBean> mAssignList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AssignAdapter assignAdapter;

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
                Intent intent = new Intent(AssignActivity.this, CourseAndTaskActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.task_recycle_view);

        setSupportActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        request();

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL, false));
        assignAdapter = new AssignAdapter(mAssignList);
        recyclerView.setAdapter(assignAdapter);

        assignAdapter.setOnItemClickListener(new AssignAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }


    private void request() {
        AssignListService assignListService = RetrofitWrapper.getInstance().create(AssignListService.class);
        String siteId = getIntent().getStringExtra("siteId");
        Call<TasksBean> call = assignListService.getTasksBean(siteId, new CourseBean().getCookie(), new CourseBean().getToken());
        call.enqueue(new Callback<TasksBean>() {
            @Override
            public void onResponse(Call<TasksBean> call, Response<TasksBean> response) {
                if (response.isSuccessful()) {
                    mAssignList.add((TasksBean.DataBean) response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<TasksBean> call, Throwable t) {

            }
        });
    }

}