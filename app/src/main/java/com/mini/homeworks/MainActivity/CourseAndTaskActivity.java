package com.mini.homeworks.MainActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.design.internal.NavigationMenuPresenter;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.mini.homeworks.Login.LoginActivity;
import com.mini.homeworks.R;
import com.mini.homeworks.Search.SearchActivity;
import com.mini.homeworks.Utils.RetrofitWrapper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseAndTaskActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar tb_head;
    private TabLayout tl_CourseAndTask;
    private ViewPager vp_CourseAndTask;
    private DrawerLayout dl_navigation;
    private List<Course> CourseList = new ArrayList<>();
    private RecyclerView rv_course;
    private RecyclerView rv_task;
    private LinearLayout ly_course_view, ly_task_view, ly_course_and_task_content;
    private RecyclerView rv_task_button;
    private Spinner spinner_collation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_and_task);
        initView();
    }

    private void initCourse(List<CoursesBean.CourseListBean> courselist , int total) {
        int[] images = {R.drawable.books,R.drawable.caculator,R.drawable.glasses,R.drawable.notebook,R.drawable.pen};
        int t = 0;
        int i = 0;
        while (i<total) {
            CourseList.add(new Course(courselist.get(i).getCourseName(),images[t],courselist.get(i).getSiteId()));
            i++; t++;
            if(t==5) t = 0;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_course.setLayoutManager(layoutManager);
        CourseAdapter courseAdapter = new CourseAdapter(CourseList);
        rv_course.setAdapter(courseAdapter);

        courseAdapter.setOnRecyclerViewItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(CourseAndTaskActivity.this, LoginActivity.class);
                intent.putExtra("siteId",CourseList.get(position-1).getSiteId());
                startActivity(intent);
            }
        });
    }


    private void initTask(List<TaskBean.AssignListBean> tasklist, int total) {
        // 在这里两个layout自已定义效果,不用系统自带.通过加载xml文件配置的数据源
        ArrayAdapter spinnerAdapter = ArrayAdapter.createFromResource(this,R.array.array_collation, R.layout.task_view_spinner_text_item);
        spinnerAdapter.setDropDownViewResource(R.layout.task_view_spinner_dropdown_item);
        spinner_collation.setAdapter(spinnerAdapter);

        spinner_collation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (id==0) {

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        rv_task_button.setLayoutManager(layoutManager);

        List<String> btn_str = null;
        btn_str.add("全部");
        btn_str.add("进行中");
        btn_str.add("已完成");
        btn_str.add("已逾期");

        TaskButtonAdapter taskButtonAdapter = new TaskButtonAdapter(btn_str);
        rv_task_button.setAdapter(taskButtonAdapter);

        /*
        taskButtonAdapter.setOnRecyclerViewItemClickListener(new TaskButtonAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                TaskButtonAdapter.ViewHolder viewHolder = holder;
                String str = mTaskButtonList.get(position);
                holder.btn_task_view.setText(str);
            }
        });
        */
    }

    private void request_task() {
        TaskService taskService = RetrofitWrapper.getInstance().create(TaskService.class);
        Call<TaskBean> call = taskService.getTaskBean(getIntent().getStringExtra("cookie"),getIntent().getStringExtra("token"));
        call.enqueue(new Callback<TaskBean>() {
            @Override
            public void onResponse(Call<TaskBean> call, Response<TaskBean> response) {
                if (response.isSuccessful()) {
                    initTask(response.body().getAssignList(),response.body().getTotal());
                }else{
                    Toast.makeText(CourseAndTaskActivity.this, "请求失败，请重试",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<TaskBean> call, Throwable t) {

            }
        });

    }

    private void request_course() {
        CoursesService coursesService = RetrofitWrapper.getInstance().create(CoursesService.class);
        Call<CoursesBean> call = coursesService.getCoursesBean(getIntent().getStringExtra("cookie"),getIntent().getStringExtra("token"));
        call.enqueue(new Callback<CoursesBean>() {
            @Override
            public void onResponse(Call<CoursesBean> call, Response<CoursesBean> response) {
                if (response.isSuccessful()) {
                    initCourse(response.body().getCourseList(),response.body().getTotal());
                }else{
                    Toast.makeText(CourseAndTaskActivity.this, "请求失败，请重试",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<CoursesBean> call, Throwable t) {

            }
        });
    }

    private void initView () {

        ly_course_and_task_content = findViewById(R.id.ly_course_and_task_content);
        ly_course_view = findViewById(R.id.ly_course_view);
        ly_task_view = findViewById(R.id.ly_task_view);

        tb_head = findViewById(R.id.tb_head);
        dl_navigation = findViewById(R.id.dl_navigation);
        vp_CourseAndTask = findViewById(R.id.vp_CourseAndTask);
        tl_CourseAndTask = findViewById(R.id.tl_CourseAndTask);

        spinner_collation = findViewById(R.id.spinner_collation);

        setSupportActionBar(tb_head);

        tb_head.setNavigationIcon(R.mipmap.menu_icon);
        tb_head.setTitle("HomeWorks");
        tb_head.inflateMenu(R.menu.search);
        tb_head.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(CourseAndTaskActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            }
        });

        List<View> viewList = new ArrayList<>();
        List<String> titleList = new ArrayList<>();

        titleList.add("课堂列表");
        titleList.add("任务列表");

        LayoutInflater inflater = LayoutInflater.from(this);
        View tl1 = inflater.inflate(R.layout.course_view, null);
        View tl2 = inflater.inflate(R.layout.task_view, null);

        rv_course = findViewById(R.id.rv_course);
        rv_task = findViewById(R.id.rv_task);


        viewList.add(tl1);
        viewList.add(tl2);

        VPAdapter viewPagerAdapter = new VPAdapter(titleList,viewList);
        vp_CourseAndTask.setAdapter(viewPagerAdapter);

        tl_CourseAndTask.setupWithViewPager(vp_CourseAndTask);

        request_course();
        request_task();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl_navigation, tb_head, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        dl_navigation.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);//显示menu图标颜色


        //将RGB转换为int
        int rgb = 202 + 69 * 256 + 137 * 256 * 256;
        int b = rgb / (256 * 256);
        int g = (rgb - b * 256 * 256) / 256;
        int r = (rgb - b * 256 * 256 - g * 256);

        setNavigationMenuLineStyle(navigationView, Color.rgb(r,g,b) ,1);
    }



    public static void setNavigationMenuLineStyle(NavigationView navigationView, @ColorInt final int color, final int height){  //设置menu分割线。
        try {
            Field fieldByPressenter = navigationView.getClass().getDeclaredField("mPresenter");
            fieldByPressenter.setAccessible(true);
            NavigationMenuPresenter menuPresenter = (NavigationMenuPresenter) fieldByPressenter.get(navigationView);
            Field fieldByMenuView = menuPresenter.getClass().getDeclaredField("mMenuView");
            fieldByMenuView.setAccessible(true);
            final NavigationMenuView mMenuView = (NavigationMenuView) fieldByMenuView.get(menuPresenter);
            mMenuView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                @Override
                public void onChildViewAttachedToWindow(View view) {
                    RecyclerView.ViewHolder viewHolder = mMenuView.getChildViewHolder(view);
                    if (viewHolder != null && "SeparatorViewHolder".equals(viewHolder.getClass().getSimpleName()) && viewHolder.itemView != null) {
                        if (viewHolder.itemView instanceof FrameLayout) {
                            FrameLayout frameLayout = (FrameLayout) viewHolder.itemView;
                            View line = frameLayout.getChildAt(0);
                            line.setBackgroundColor(color);
                            line.getLayoutParams().height = height;
                            line.setLayoutParams(line.getLayoutParams());
                        }
                    }
                }

                @Override
                public void onChildViewDetachedFromWindow(View view) {

                }
            });

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onBackPressed() {
        if (dl_navigation.isDrawerOpen(GravityCompat.START)) {
            dl_navigation.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation_menu view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_assignment) {

        } else if (id == R.id.nav_friends) {

        } else if (id == R.id.nav_information) {

        } else if (id == R.id.nav_remind) {

        } else if (id == R.id.nav_notice) {

        }

        dl_navigation = findViewById(R.id.dl_navigation);
        dl_navigation.closeDrawer(GravityCompat.START);
        return true;
    }
}