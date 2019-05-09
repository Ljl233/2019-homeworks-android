package com.mini.homeworks.MainActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mini.homeworks.CourseAssign.AssignActivity;
import com.mini.homeworks.R;
import com.mini.homeworks.net.RetrofitWrapper;
import com.mini.homeworks.net.Service.CoursesService;
import com.mini.homeworks.net.bean.CoursesBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class CourseFragment extends Fragment {

    private List<Course> CourseList = new ArrayList<>();
    private RecyclerView rv_course;
    private List<CoursesBean.CourseListBean> courselist;
    private String cookie;
    private String token;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.course_view,container,false);
        request_course();
        return view;
    }

    private void initCourse(int total) {
        rv_course = view.findViewById(R.id.rv_course);
        int[] images = {R.drawable.books, R.drawable.caculator, R.drawable.glasses, R.drawable.notebook, R.drawable.pen};
        int t = 0;
        int i = 0;
        while (i < total) {
            CourseList.add(new Course(courselist.get(i).getCourseName(), images[t], courselist.get(i).getSiteId()));
            i++;
            t++;
            if (t == 5) t = 0;
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv_course.setLayoutManager(layoutManager);
        CourseAdapter courseAdapter = new CourseAdapter(CourseList);
        rv_course.setAdapter(courseAdapter);
        courseAdapter.setOnRecyclerViewItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), AssignActivity.class);
                intent.putExtra("siteId", CourseList.get(position).getSiteId());
                startActivity(intent);
            }
        });
    }

    private void request_course() {
        GetCookieAndToken();
        CoursesService coursesService = RetrofitWrapper.getInstance().create(CoursesService.class);
        Call<CoursesBean> call = coursesService.getCoursesBean(cookie, token);
        call.enqueue(new Callback<CoursesBean>() {
            @Override
            public void onResponse(Call<CoursesBean> call, Response<CoursesBean> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    courselist = response.body().getCourseList();
                    cookie = response.body().getCookie();
                    SaveCookie(cookie);
                    initCourse(response.body().getTotal());
                } else
                    Toast.makeText(getContext(),"加载失败，请重试",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<CoursesBean> call, Throwable t) {
                Toast.makeText(getContext(),"请检查网络连接",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SaveCookie (String cookie) {
        SharedPreferences data = getContext().getSharedPreferences("CandT",MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("cookie",cookie);
        editor.apply();
    }

    private void GetCookieAndToken () {
        SharedPreferences data = getContext().getSharedPreferences("CandT",MODE_PRIVATE);
        cookie = data.getString("cookie",null);
        token = data.getString("token", null);
    }

}