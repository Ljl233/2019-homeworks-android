package com.mini.homeworks.Login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.mini.homeworks.MainActivity.CourseAndTaskActivity;
import com.mini.homeworks.R;
import com.mini.homeworks.Utils.RetrofitWrapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btn_signin;
    private EditText et_password, et_userName;
    private CheckBox remember;
    private TextView forget;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn_signin = findViewById(R.id.btn_signin);
        et_userName = findViewById(R.id.et_userName);
        et_password = findViewById(R.id.et_password);
        forget = findViewById(R.id.forget);
        remember = findViewById(R.id.remember);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://spoc.ccnu.edu.cn/user/v1/getRetrievePassword?userName=")
                );
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember = sp.getBoolean("remember_password", false);
        if (isRemember) {
            String userName = sp.getString("userName", "");
            String password = sp.getString("password", "");
            et_userName.setText(userName);
            et_password.setText(password);
            remember.setChecked(true);
        }
    }

    private void request() {
        Intent intent = new Intent(LoginActivity.this, CourseAndTaskActivity.class);
        startActivity(intent);
//        CourseService courseService = RetrofitWrapper.getInstance().create(CourseService.class);
//        userName = et_userName.getText().toString();
//        password = et_password.getText().toString();
//        Call<CourseBean> call = courseService.getCourseBean(new LoginPostData(userName, password));

//        call.enqueue(new Callback<CourseBean>() {
//            @Override
//            public void onResponse(Call<CourseBean> call, Response<CourseBean> response) {
//                if (response.isSuccessful()) {
//                    SaveUserInfo();
//                    Intent intent = new Intent(LoginActivity.this, CourseAndTaskActivity.class);
//                    intent.putExtra("token", response.body().getToken());
//                    intent.putExtra("cookie", response.body().getCookie());
//                    startActivity(intent);
//                } else GetWrong();
//            }
//
//            @Override
//            public void onFailure(Call<CourseBean> call, Throwable t) {
//                GetWrong();
//            }
//        });
    }

    public void SaveUserInfo() {
        editor = sp.edit();
        if (remember.isChecked()) {
            String userName = et_userName.getText().toString();
            String password = et_password.getText().toString();
            editor.putBoolean("remember_password",true);
            editor.putString("userName", userName);
            editor.putString("password", password);
        } else {
            editor.clear();
        }
        editor.apply();
    }

    public void GetWrong() {
        AlertDialog.Builder Wrong = new AlertDialog.Builder(LoginActivity.this);
        Wrong.setTitle("登陆失败");
        Wrong.setMessage("学号或密码错误，请重新输入。");
        Wrong.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                et_userName.getText().clear();
                et_password.getText().clear();
            }
        });
        Wrong.show();
    }
}