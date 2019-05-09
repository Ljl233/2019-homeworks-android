package com.mini.homeworks.PersonalInformation;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mini.homeworks.Login.LoginActivity;
import com.mini.homeworks.R;
import com.mini.homeworks.net.RetrofitWrapper;
import com.mini.homeworks.net.Service.ChangeMailService;
import com.mini.homeworks.net.Service.InformationService;
import com.mini.homeworks.net.Service.SendVerifyCodeService;
import com.mini.homeworks.net.bean.ChangeMailBean;
import com.mini.homeworks.net.bean.ChangeMailPostData;
import com.mini.homeworks.net.bean.InformationBean;
import com.mini.homeworks.net.bean.SendVerifyCodeBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;


public class Information extends AppCompatActivity {

    private Toolbar tb_information;
    private EditText et_mailbox;
    private ImageView iv_clearmailbox;
    private TextView tv_name, tv_num;
    private String token;
    private String cookie;
    private String verifyCodeToken;
    private String newMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        initView();
    }

    private void initView() {
        tb_information = findViewById(R.id.tb_information);
        et_mailbox = findViewById(R.id.et_mailbox);
        Button btn_logoff = findViewById(R.id.btn_logoff);
        iv_clearmailbox = findViewById(R.id.iv_clearmailbox);
        tv_name = findViewById(R.id.tv_name);
        tv_num = findViewById(R.id.tv_num);
        btn_logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder Logoff = new AlertDialog.Builder(Information.this);
                Logoff.setTitle("确定退出当前账号？");
                Logoff.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Information.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                Logoff.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                Logoff.show();
            }
        });

        initToolbar();
        request_init();
        setmailbox();


    }

    private void initToolbar() {
        setSupportActionBar(tb_information);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tb_information.setNavigationIcon(R.drawable.back_arrow);
        tb_information.setTitle("个人信息");
        tb_information.setTitleTextColor(Color.rgb(255, 255, 255));
        tb_information.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void setmailbox() {
        et_mailbox.setClickable(true);
        iv_clearmailbox.setVisibility(View.GONE);
        et_mailbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_clearmailbox.setVisibility(View.VISIBLE);
                iv_clearmailbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        et_mailbox.setText("");
                    }
                });
                et_mailbox.setFocusableInTouchMode(true);
                et_mailbox.setFocusable(true);
                et_mailbox.requestFocus();
            }
        });

        et_mailbox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    newMail = et_mailbox.getText().toString();
                    request_sendVerifyCode();
                }
                return false;
            }
        });
    }

    private void showDialog_verify ( ){
        final EditText et = new EditText(Information.this);
        new AlertDialog.Builder(Information.this).setTitle("VerifyCode")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String input = et.getText().toString();
                        if (input.equals("")) {
                            Toast.makeText(getApplicationContext(), "内容不能为空！" + input, Toast.LENGTH_LONG).show();
                        }
                        else
                            request_changemail(newMail,input);
                    }
                })
                .show();
    }

    private void request_sendVerifyCode( ) {
        GetCookieAndToken();
        SendVerifyCodeService sendVerifyCodeService = RetrofitWrapper.getInstance().create(SendVerifyCodeService.class);
        Call<SendVerifyCodeBean> call = sendVerifyCodeService.Send(token,newMail);
        call.enqueue(new Callback<SendVerifyCodeBean>() {
            @Override
            public void onResponse(Call<SendVerifyCodeBean> call, Response<SendVerifyCodeBean> response) {
                verifyCodeToken = response.body().getVerifyCodeToken();
                showDialog_verify();
            }

            @Override
            public void onFailure(Call<SendVerifyCodeBean> call, Throwable t) {

            }
        });
    }

    private void request_init() {
        GetCookieAndToken();
        InformationService informationService = RetrofitWrapper.getInstance().create(InformationService.class);
        Call<InformationBean> call = informationService.getInformationBean(token);
        call.enqueue(new Callback<InformationBean>() {
            @Override
            public void onResponse(Call<InformationBean> call, Response<InformationBean> response) {
                if (response.isSuccessful()) {
                    tv_name.setText(response.body().getRealName());
                    tv_num.setText(response.body().getUserName());
                    et_mailbox.setText(response.body().getEmail());
                } else {
                    Toast.makeText(Information.this, "请求失败，请重试", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<InformationBean> call, Throwable t) {

            }
        });
    }

    private void request_changemail(final String newMail , String verifyCode) {
        GetCookieAndToken();
        ChangeMailService changeMailService = RetrofitWrapper.getInstance().create(ChangeMailService.class);
        Call<ChangeMailBean> call = changeMailService.getReturn(token,verifyCodeToken,new ChangeMailPostData(newMail,verifyCode));
        call.enqueue(new Callback<ChangeMailBean>() {
            @Override
            public void onResponse(Call<ChangeMailBean> call, Response<ChangeMailBean> response) {
                if ( response.isSuccessful() ){
                    Toast.makeText(Information.this, "修改成功", Toast.LENGTH_LONG).show();
                    et_mailbox.setText(newMail);
                } else {
                    Toast.makeText(Information.this, "请重试", Toast.LENGTH_LONG).show();
                    showDialog_verify();
                }
            }
            @Override
            public void onFailure(Call<ChangeMailBean> call, Throwable t) {
                Toast.makeText(Information.this, "请重试", Toast.LENGTH_LONG).show();
                showDialog_verify();
            }
        });
    }
    private void SaveCookie (String cookie) {
        SharedPreferences data = getSharedPreferences("CandT",MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putString("cookie",cookie);
        editor.apply();
    }

    private void GetCookieAndToken () {
        SharedPreferences data = getSharedPreferences("CandT",MODE_PRIVATE);
        cookie = data.getString("cookie",null);
        token = data.getString("token", null);
    }
}

