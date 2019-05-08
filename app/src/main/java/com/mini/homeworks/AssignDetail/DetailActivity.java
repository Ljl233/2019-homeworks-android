package com.mini.homeworks.AssignDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mini.homeworks.CourseAssign.AssignActivity;
import com.mini.homeworks.R;
import com.mini.homeworks.Utils.RetrofitWrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
任务详情界面
 */

public class DetailActivity extends AppCompatActivity {
    TextView tv_begin2, tv_ddl2, tv_state, tv_content, tv_pointNum, tv_studentNum, tv_submitContent,
            tv_feedback, tv_assignAttachmentNum, tv_assignAttachment_name, tv_assignName2,tv_submitAttachmentNum;

    String cookie, token, siteId, assignId;

    ListView listView;

    DetailAdapter detailAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        cookie = getIntent().getStringExtra("cookie");
        token = getIntent().getStringExtra("token");
        siteId = getIntent().getStringExtra("siteId");
        assignId = getIntent().getStringExtra("assignId");
        initView();
    }


    void initView() {
        Toolbar toolbar_detail = findViewById(R.id.toolbar_detail);

        listView=findViewById(R.id.listView);

        tv_begin2 = findViewById(R.id.tv_begin2);
        tv_ddl2 = findViewById(R.id.tv_ddl2);
        tv_state = findViewById(R.id.tv_state);
        tv_content = findViewById(R.id.tv_content);
        tv_pointNum = findViewById(R.id.tv_pointNum);
        tv_submitContent = findViewById(R.id.tv_submitContent);
        tv_feedback = findViewById(R.id.tv_feedback);
        tv_studentNum = findViewById(R.id.tv_studentNum);
        tv_assignName2 = findViewById(R.id.tv_assignName2);
        tv_submitAttachmentNum=findViewById(R.id.tv_submitAttachmentNum);


        setSupportActionBar(toolbar_detail);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        request();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void request() {
        DetailService detailService = RetrofitWrapper.getInstance().create(DetailService.class);
        Call<DetailBean> call = detailService.getDetailBean(siteId, assignId, cookie, token);
        call.enqueue(new Callback<DetailBean>() {
            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {
                if (response.isSuccessful()) {
                    DetailBean detail = response.body();
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
        tv_begin2.append(detail.getBeginTime());
        tv_ddl2.append(detail.getEndTime());
        tv_assignName2.append(detail.getCourseName());
        tv_assignName2.append(":");
        tv_assignName2.append(detail.getAssignName());


        if (detail.getStatus().equals("0")) tv_state.append("未提交");
        else if (detail.getStatus().equals("1")) tv_state.append("待批阅");
        else if (detail.getStatus().equals("2")) tv_state.append("已驳回");
        else tv_state.append("已批阅");

        tv_content.append(detail.getContent());
        tv_pointNum.append(detail.getPointNum());
        tv_studentNum.append(detail.getStudentNum());
        tv_submitContent.append(detail.getSubmitContent());
        tv_feedback.append(detail.getFeedback());
        tv_submitAttachmentNum.append(detail.getSubmitAttachmentNum());

        List<DetailBean.SubmitAttachmentBean> submitAttachmentBeanList = detail.getSubmitAttachment();
        detailAdapter = new DetailAdapter(this,submitAttachmentBeanList);
        listView.setAdapter(detailAdapter);
    }
}