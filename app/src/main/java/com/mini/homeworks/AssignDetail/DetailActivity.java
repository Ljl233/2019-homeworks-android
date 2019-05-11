package com.mini.homeworks.AssignDetail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mini.homeworks.R;
import com.mini.homeworks.Utils.GetDate;
import com.mini.homeworks.net.RetrofitWrapper;
import com.mini.homeworks.net.Service.DetailService;
import com.mini.homeworks.net.bean.DetailBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
任务详情界面
 */

public class DetailActivity extends AppCompatActivity {
    TextView tv_begin2, tv_ddl2, tv_state, tv_content, tv_pointNum, tv_studentNum, tv_submitContent,
            tv_feedback, tv_assignName2, tv_submitAttachmentNum;

    String cookie, token, siteId, assignId;

    ListView listView;

    DetailAdapter detailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
    }


    void initView() {
        Toolbar toolbar_detail = findViewById(R.id.toolbar_detail);

        listView = findViewById(R.id.listView);

        tv_begin2 = findViewById(R.id.tv_begin2);
        tv_ddl2 = findViewById(R.id.tv_ddl2);
        tv_state = findViewById(R.id.tv_state);
        tv_content = findViewById(R.id.tv_content);
        tv_pointNum = findViewById(R.id.tv_pointNum);
        tv_submitContent = findViewById(R.id.tv_submitContent);
        tv_feedback = findViewById(R.id.tv_feedback);
        tv_studentNum = findViewById(R.id.tv_studentNum);
        tv_assignName2 = findViewById(R.id.tv_assignName2);
        tv_submitAttachmentNum = findViewById(R.id.tv_submitAttachmentNum);

        setSupportActionBar(toolbar_detail);
        toolbar_detail.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        request();
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void request() {
        GetCookieAndToken();
        siteId = getIntent().getStringExtra("siteId");
        assignId = getIntent().getStringExtra("assignId");
        DetailService detailService = RetrofitWrapper.getInstance().create(DetailService.class);
        Call<DetailBean> call = detailService.getDetailBean(siteId, assignId, cookie, token);
        call.enqueue(new Callback<DetailBean>() {
            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {
                if (response.isSuccessful()) {
                    DetailBean detail = response.body();
                    cookie = response.body().getCookie();
                    SaveCookie(cookie);
                    initDetail(detail);
                } else {
                    Toast.makeText(DetailActivity.this, "请求失败，请重试", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DetailBean> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "404", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void initDetail(DetailBean detail) {

        String begintime = GetDate.TimeStampToDate("" + detail.getBeginTime(), "yyyy-MM-dd HH:mm:ss").substring(0, 10);
        String endtime = GetDate.TimeStampToDate("" + detail.getEndTime(), "yyyy-MM-dd HH:mm:ss").substring(0, 10);
        tv_ddl2.append(endtime + " " + GetDate.DateToWeek(endtime));
        tv_begin2.append(begintime + " " + GetDate.DateToWeek(begintime));
        tv_assignName2.append(detail.getCourseName());
        tv_assignName2.append(":");
        tv_assignName2.append(detail.getAssignName());
        Log.d("tv_assignName2", tv_assignName2.toString());

        if (detail.getStatus() == 0) tv_state.append("未提交");
        else if (detail.getStatus() == 1) tv_state.append("待批阅");
        else if (detail.getStatus() == 2) tv_state.append("已驳回");
        else tv_state.append("已批阅");

        tv_submitAttachmentNum.append(detail.getSubmitAttachmentNum() + "");
        tv_pointNum.append(detail.getPointNum() + "");
        tv_studentNum.append(detail.getStudentNum() + "");
        if (detail.getContent() != null)
            tv_content.append(detail.getContent());
        else tv_content.append("无");
        if (detail.getSubmitContent() != null)
            tv_submitContent.append(detail.getSubmitContent());
        else tv_submitContent.append("无");
        if (detail.getFeedback() != null)
            tv_feedback.append(detail.getFeedback());
        else tv_feedback.append("无");

        List<DetailBean.SubmitAttachmentBean> submitAttachmentBeanList = detail.getSubmitAttachment();
        detailAdapter = new DetailAdapter(this, submitAttachmentBeanList);
        listView.setAdapter(detailAdapter);
    }

    private void SaveCookie(String cookie) {
        SharedPreferences data = getSharedPreferences("CandT", MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("cookie", cookie);
        editor.apply();
    }

    private void GetCookieAndToken() {
        SharedPreferences data = getSharedPreferences("CandT", MODE_PRIVATE);
        cookie = data.getString("cookie", null);
        token = data.getString("token", null);
    }
}