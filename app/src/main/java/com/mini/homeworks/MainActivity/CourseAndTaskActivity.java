package com.mini.homeworks.MainActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.mini.homeworks.net.RetrofitWrapper;
import com.mini.homeworks.net.Service.CoursesService;
import com.mini.homeworks.net.Service.TasksService;
import com.mini.homeworks.net.bean.CoursesBean;
import com.mini.homeworks.net.bean.TasksBean;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseAndTaskActivity extends AppCompatActivity implements OnClickListener {
    private Toolbar tb_head;
    private DrawerLayout dl_navigation;
    public Context context;
    private ViewPager vp_CourseAndTask;
    private LinearLayout ll_assignment, ll_information, ll_notice, ll_remind, ll_friends;
    private TextView tv_nav_name, tv_nav_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_and_task);
        context = this;
        initView();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_information: {
                Intent intent = new Intent(CourseAndTaskActivity.this, Information.class);
                startActivity(intent);
                break;
            }
            case R.id.ll_remind: {
                Intent intent = new Intent(CourseAndTaskActivity.this, NotificationActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.ll_assignment: {
                Intent intent = new Intent(CourseAndTaskActivity.this, MyAssign.class);
                startActivity(intent);
                break;
            }
            case R.id.ll_notice: {
                Intent intent = new Intent(CourseAndTaskActivity.this, MyNotice.class);
                startActivity(intent);
                break;
            }
            case R.id.ll_friends: {
                Intent intent = new Intent(CourseAndTaskActivity.this, FriendManagement.class);
                startActivity(intent);
                break;
            }
        }
    }
    private void initNav() {
        dl_navigation = findViewById(R.id.dl_navigation);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl_navigation, tb_head, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl_navigation.addDrawerListener(toggle);
        toggle.syncState();
        tv_nav_name = findViewById(R.id.tv_nav_name);
        tv_nav_username = findViewById(R.id.tv_nav_uesrname);
        tv_nav_name.setText(getIntent().getStringExtra("realName"));
        tv_nav_username.setText(getIntent().getStringExtra("userName"));
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
        TabLayout tl_CourseAndTask = findViewById(R.id.tl_CourseAndTask);
        tl_CourseAndTask.setTabGravity(TabLayout.GRAVITY_FILL);
        VPAdapter viewPagerAdapter = new VPAdapter(getSupportFragmentManager());
        vp_CourseAndTask.setAdapter(viewPagerAdapter);
        tl_CourseAndTask.setupWithViewPager(vp_CourseAndTask);//关联ViewPager
        tl_CourseAndTask.getTabAt(0).setText("课堂列表");
        tl_CourseAndTask.getTabAt(1).setText("任务列表");
    }

    private void initToolbar() {
        tb_head = findViewById(R.id.tb_head);
        tb_head.setTitle("HomeWorks");
        setSupportActionBar(tb_head);
        tb_head.setNavigationIcon(R.mipmap.menu_icon);
    }
    //加载menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search, menu);
        return true;
    }
    //menu点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(CourseAndTaskActivity.this, SearchActivity.class);
        startActivity(intent);
        return true;
    }
    private void initView() {
        initToolbar();
        initNav();
        initVP();
    }
    @Override
    public void onBackPressed() {
        if (dl_navigation.isDrawerOpen(GravityCompat.START)) {
            dl_navigation.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }
}