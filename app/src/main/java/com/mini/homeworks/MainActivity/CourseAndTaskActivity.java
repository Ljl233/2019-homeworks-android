package com.mini.homeworks.MainActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.mini.homeworks.AssignDetail.DetailActivity;
import com.mini.homeworks.CourseAssign.AssignActivity;
import com.mini.homeworks.FriendManagement.FriendManagement;
import com.mini.homeworks.MyAssign.MyAssign;
import com.mini.homeworks.MyNotice.MyNotice;
import com.mini.homeworks.Notification.NotificationActivity;
import com.mini.homeworks.PersonalInformation.Information;
import com.mini.homeworks.R;
import com.mini.homeworks.Search.SearchActivity;
import com.mini.homeworks.Utils.RetrofitWrapper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseAndTaskActivity extends AppCompatActivity implements OnClickListener {

    private Toolbar tb_head;
    private TabLayout tl_CourseAndTask;
    private ViewPager vp_CourseAndTask;
    private DrawerLayout dl_navigation;
    private List<Course> CourseList = new ArrayList<>();
    private RecyclerView rv_course, rv_task;
    private LinearLayout ll_information, ll_notice, ll_remind, ll_friends, ll_assignment;
    private Spinner spinner_collation;
    private Button btn_all, btn_completed, btn_processing, btn_overdue;
    List<CoursesBean.CourseListBean> courselist;
    List<TaskBean.AssignListBean> tasklist, tmptasklist;
    TaskBean.AssignListBean[] task;
    private String cookie = getIntent().getStringExtra("cookie");
    private String token = getIntent().getStringExtra("token");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_and_task);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_information :{
                Intent intent = new Intent(CourseAndTaskActivity.this, Information.class);
                intent.putExtra("cookie",cookie);
                intent.putExtra("token",token);
                startActivity(intent);
                break;
            }
            case R.id.ll_remind :{
                Intent intent = new Intent(CourseAndTaskActivity.this, NotificationActivity.class);
                intent.putExtra("cookie",cookie);
                intent.putExtra("token",token);startActivity(intent);
                break;
            }
            case R.id.ll_assignment :{
                Intent intent = new Intent(CourseAndTaskActivity.this, MyAssign.class);
                intent.putExtra("cookie",cookie);
                intent.putExtra("token",token);startActivity(intent);
                break;
            }
            case R.id.ll_notice :{
                Intent intent = new Intent(CourseAndTaskActivity.this, MyNotice.class);
                intent.putExtra("cookie",cookie);
                intent.putExtra("token",token);startActivity(intent);
                break;
            }
            case R.id.ll_friends :{
                Intent intent = new Intent(CourseAndTaskActivity.this, FriendManagement.class);
                intent.putExtra("cookie",cookie);
                intent.putExtra("token",token);startActivity(intent);
                break;
            }
            case R.id.btn_all : {
                btn_all.setTextColor(Color.argb((float) 1,255,255,255));
                btn_all.setBackgroundColor(Color.argb((float) 1,77, 182, 172));
                btn_overdue.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_overdue.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                btn_processing.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_processing.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                btn_completed.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_completed.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                tmptasklist = tasklist;
                break;
            }
            case R.id.btn_processing : {
                btn_processing.setTextColor(Color.argb((float) 1,255,255,255));
                btn_processing.setBackgroundColor(Color.argb((float) 1,77, 182, 172));
                btn_overdue.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_overdue.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                btn_all.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_all.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                btn_completed.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_completed.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                selectprocessing();
                break;
            }
            case R.id.btn_completed : {
                btn_completed.setTextColor(Color.argb((float) 1,255,255,255));
                btn_completed.setBackgroundColor(Color.argb((float) 1,77, 182, 172));
                btn_overdue.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_overdue.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                btn_processing.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_processing.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                btn_all.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_all.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                selectcompeleted();
                break;
            }
            case R.id.btn_overdue : {
                btn_overdue.setTextColor(Color.argb((float) 1,255,255,255));
                btn_overdue.setBackgroundColor(Color.argb((float) 1,77, 182, 172));
                btn_all.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_all.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                btn_processing.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_processing.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                btn_completed.setTextColor(Color.argb((float) 0.26,0,0,0));
                btn_completed.setBackgroundColor(Color.argb((float) 0.12,0,0,0));
                selectoverdue();
                break;
            }
        }
    }

    private void initNav() {
        dl_navigation = findViewById(R.id.dl_navigation);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl_navigation, tb_head, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl_navigation.addDrawerListener(toggle);
        toggle.syncState();

        ll_assignment = findViewById(R.id.ll_assignment);
        ll_information = findViewById(R.id.ll_information);
        ll_notice = findViewById(R.id.ll_notice);
        ll_remind = findViewById(R.id.ll_remind);
        ll_friends = findViewById(R.id.ll_friends);

        ll_information.setOnClickListener(this);
        ll_notice.setOnClickListener(this);
        ll_remind.setOnClickListener(this);
        ll_assignment.setOnClickListener(this);
        ll_friends.setOnClickListener(this);
    }

    private void initVP() {

        vp_CourseAndTask = findViewById(R.id.vp_CourseAndTask);
        tl_CourseAndTask = findViewById(R.id.tl_CourseAndTask);
        rv_course = findViewById(R.id.rv_course);
        rv_task = findViewById(R.id.rv_task);

        tl_CourseAndTask.addTab(tl_CourseAndTask.newTab().setText("课堂列表"));
        tl_CourseAndTask.addTab(tl_CourseAndTask.newTab().setText("任务列表"));
        tl_CourseAndTask.setTabGravity(TabLayout.GRAVITY_FILL);

        VPAdapter viewPagerAdapter = new VPAdapter(getSupportFragmentManager(),tl_CourseAndTask.getTabCount());
        vp_CourseAndTask.setAdapter(viewPagerAdapter);
        tl_CourseAndTask.setupWithViewPager(vp_CourseAndTask);//关联ViewPager

    }

    private void initToolbar() {
        tb_head = findViewById(R.id.tb_head);
        setSupportActionBar(tb_head);
        tb_head.setNavigationIcon(R.mipmap.menu_icon);
        tb_head.setTitle("HomeWorks");
        tb_head.inflateMenu(R.menu.search);
        tb_head.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(CourseAndTaskActivity.this, SearchActivity.class);
                intent.putExtra("cookie",cookie);
                intent.putExtra("token",token);
                startActivity(intent);
                return true;
            }
        });
    }

    private void initCourse(int total) {
        int[] images = {R.drawable.books, R.drawable.caculator, R.drawable.glasses, R.drawable.notebook, R.drawable.pen};
        int t = 0;
        int i = 0;
        while (i < total) {
            CourseList.add(new Course(courselist.get(i).getCourseName(), images[t], courselist.get(i).getSiteId()));
            i++;
            t++;
            if (t == 5) t = 0;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_course.setLayoutManager(layoutManager);
        CourseAdapter courseAdapter = new CourseAdapter(CourseList);
        rv_course.setAdapter(courseAdapter);

        courseAdapter.setOnRecyclerViewItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(CourseAndTaskActivity.this, AssignActivity.class);
                intent.putExtra("siteId", CourseList.get(position - 1).getSiteId());
                intent.putExtra("cookie",cookie);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });
    }


    private void initTask(int total) {
        btn_overdue = findViewById(R.id.btn_overdue);
        btn_processing = findViewById(R.id.btn_processing);
        btn_all = findViewById(R.id.btn_all);
        btn_completed = findViewById(R.id.btn_completed);
        spinner_collation = findViewById(R.id.spinner_collation);

        btn_all.setOnClickListener(this);
        btn_overdue.setOnClickListener(this);
        btn_processing.setOnClickListener(this);
        btn_completed.setOnClickListener(this);

        task = new TaskBean.AssignListBean[tmptasklist.size()];
        tmptasklist.toArray(task);
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_collation, R.layout.task_view_spinner_text_item);
        spinnerAdapter.setDropDownViewResource(R.layout.task_view_spinner_dropdown_item);
        spinner_collation.setAdapter(spinnerAdapter);
        spinner_collation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (id == 0)
                    beginorder();
                else
                    endorder();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_task.setLayoutManager(layoutManager);
        TaskAdapter taskAdapter = new TaskAdapter(tmptasklist);
        rv_task.setAdapter(taskAdapter);

        taskAdapter.setOnRecyclerViewItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(CourseAndTaskActivity.this, DetailActivity.class);
                intent.putExtra("siteId", tmptasklist.get(position - 1).getSiteId());
                intent.putExtra("assignId",tmptasklist.get(position - 1 ).getAssignId());
                intent.putExtra("cookie",cookie);
                intent.putExtra("token",token);
                startActivity(intent);
            }
        });

    }
    private void beginorder() {
        for (int i = 0 ; i < tmptasklist.size() ; i++ ) {
            for (int j = i ; j < tmptasklist.size() ; j++ ) {
                if ( task[i].getBeginTime() < task[j].getBeginTime() ) {
                    TaskBean.AssignListBean t = task[i];
                    task[i] = task[j];
                    task[j] = t;
                }
            }
        }
    }
    private void endorder () {
        for (int i = 0 ; i < tmptasklist.size() ; i++ ) {
            for (int j = i ; j < tmptasklist.size() ; j++ ) {
                if ( task[i].getEndTime() < task[j].getEndTime() ) {
                    TaskBean.AssignListBean t = task[i];
                    task[i] = task[j];
                    task[j] = t;
                }
            }
        }
    }

    private void selectprocessing() {
        tmptasklist = tasklist;
        int l = tasklist.size();
        long now = Instant.now().getEpochSecond();
        for (int i = 0 ; i < l ; i++ ) {
            if ( tmptasklist.get(i).getEndTime() <= now ||
                    tmptasklist.get(i).getBeginTime() >= now ||
                    ( tmptasklist.get(i).getStatus() != 0 && tmptasklist.get(i).getStatus() != 2 ) )
                tmptasklist.remove(i);
        }
    }

    private void selectcompeleted() {
        tmptasklist = tasklist;
        int l = tasklist.size();
        long now = Instant.now().getEpochSecond();
        for (int i = 0 ; i < l ; i++ ) {
            if ( tmptasklist.get(i).getBeginTime() >= now ||
                    (tmptasklist.get(i).getStatus() != 1 && tmptasklist.get(i).getStatus() != 3 ) )
                tmptasklist.remove(i);
        }
    }

    private void selectoverdue() {
        tmptasklist = tasklist;
        int l = tasklist.size();
        long now = Instant.now().getEpochSecond();
        for (int i = 0 ; i < l ; i++ ) {
            if ( tmptasklist.get(i).getEndTime() >= now && tmptasklist.get(i).getBeginTime() < now )
                tmptasklist.remove(i);
        }
    }


    private void request_task() {
        TaskService taskService = RetrofitWrapper.getInstance().create(TaskService.class);
        Call<TaskBean> call = taskService.getTaskBean(cookie, token);
        call.enqueue(new Callback<TaskBean>() {
            @Override
            public void onResponse(Call<TaskBean> call, Response<TaskBean> response) {
                if (response.isSuccessful()) {
                    tasklist = response.body().getAssignList();
                    cookie = response.body().getCookie();
                    initTask(response.body().getTotal());
                } else {
                    Toast.makeText(CourseAndTaskActivity.this, "请求失败，请重试", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<TaskBean> call, Throwable t) {
            }
        });
    }

    private void request_course() {
        CoursesService coursesService = RetrofitWrapper.getInstance().create(CoursesService.class);
        Call<CoursesBean> call = coursesService.getCoursesBean(cookie, token);
        call.enqueue(new Callback<CoursesBean>() {
            @Override
            public void onResponse(Call<CoursesBean> call, Response<CoursesBean> response) {
                if (response.isSuccessful()) {
                    courselist = response.body().getCourseList();
                    cookie = response.body().getCookie();
                    initCourse(response.body().getTotal());
                } else {
                    Toast.makeText(CourseAndTaskActivity.this, "请求失败，请重试", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CoursesBean> call, Throwable t) {

            }
        });
    }

    private void initView() {
        initNav();
        initVP();
        initToolbar();
        request_course();
        request_task();
    }

    @Override
    public void onBackPressed() {
        if (dl_navigation.isDrawerOpen(GravityCompat.START)) {
            dl_navigation.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}