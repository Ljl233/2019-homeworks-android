package com.mini.homeworks.Login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.mini.homeworks.MainActivity.CourseAndTaskActivity;
import com.mini.homeworks.R;
import com.mini.homeworks.net.RetrofitWrapper;
import com.mini.homeworks.net.Service.LoginService;
import com.mini.homeworks.net.bean.LoginBean;
import com.mini.homeworks.net.bean.LoginPostData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText et_password, et_userName;
    private CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button btn_signin = findViewById(R.id.btn_signin);
        et_userName = findViewById(R.id.et_userName);
        et_password = findViewById(R.id.et_password);
        TextView forget = findViewById(R.id.forget);
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
        getUserInfo();
    }

    private void request() {
        LoginService loginService = RetrofitWrapper.getInstance().create(LoginService.class);
        final String username = et_userName.getText().toString();
        final String password = et_password.getText().toString();
        Call<LoginBean> call = loginService.getCourseBean(new LoginPostData(username, password));
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                if (response.isSuccessful()) {
                    if ( remember.isChecked() )
                        saveUserInfo(username,password);
                    else
                        clearUserInfo();
                    SaveCookieAndToken(response.body().getCookie(), response.body().getToken());
                    Intent intent = new Intent(LoginActivity.this, CourseAndTaskActivity.class);
                    startActivity(intent);
                } else {
                    GetWrong();
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                NetWrong();
            }
        });
    }
    public void GetWrong() {
        AlertDialog.Builder Wrong = new AlertDialog.Builder(LoginActivity.this);
        Wrong.setTitle("登录失败");
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
    private void NetWrong() {
        AlertDialog.Builder Wrong = new AlertDialog.Builder(LoginActivity.this);
        Wrong.setTitle("登录失败");
        Wrong.setMessage("请检查网络连接");
        Wrong.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        Wrong.show();
    }
    //保存用户信息
    private void saveUserInfo(String username, String password){
        SharedPreferences userInfo = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        //得到Editor后，写入需要保存的数据
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();//提交修改
    }
    //读取用户信息
    private void getUserInfo() {
        SharedPreferences userInfo = getSharedPreferences("user", MODE_PRIVATE);
        String username = userInfo.getString("username", null);//读取username
        String password = userInfo.getString("password", null);//读取password
        if ( username != null) {
            et_password.setText(password);
            et_userName.setText(username);
            remember.setChecked(true);
        } else remember.setChecked(false);
    }
    //清空数据
    private void clearUserInfo(){
        SharedPreferences userInfo = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = userInfo.edit();//获取Editor
        editor.clear();
        editor.apply();
    }
    private void SaveCookieAndToken (String cookie, String token) {
        SharedPreferences data = getSharedPreferences("CandT",MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("cookie",cookie);
        editor.putString("token",token);
        editor.apply();
    }

}