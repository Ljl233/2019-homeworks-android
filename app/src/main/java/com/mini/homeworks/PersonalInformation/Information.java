package com.mini.homeworks.PersonalInformation;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.strictmode.IntentReceiverLeakedViolation;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.mini.homeworks.net.bean.SendVerifyCodePostData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;


public class Information extends AppCompatActivity {

    private Toolbar tb_information;
    private EditText et_mailbox;
    private ImageView iv_clearmailbox;
    private TextView tv_name, tv_num;
    private Button btn_logoff;
    private String token;
    private String cookie;
    private String verifyCodeToken;
    private String newMail;
    private LinearLayout ll_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        initView();
    }

    private void initView() {
        tb_information = findViewById(R.id.tb_information);
        et_mailbox = findViewById(R.id.et_mailbox);
        btn_logoff = findViewById(R.id.btn_logoff);
        iv_clearmailbox = findViewById(R.id.iv_clearmailbox);
        tv_name = findViewById(R.id.tv_name);
        tv_num = findViewById(R.id.tv_num);
        ll_info = findViewById(R.id.ll_info);
        btn_logoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(Information.this)
                        .setTitle("确定退出当前账号？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Information.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });

        ll_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm;
                imm = (InputMethodManager)v.getContext().getSystemService(v.getContext().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                iv_clearmailbox.setVisibility(View.INVISIBLE);
            }
        });
        setmailbox();
        initToolbar();
        request_init();
    }


    private void initToolbar() {
        setSupportActionBar(tb_information);
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

    private void showDialog_checkmail() {
        new AlertDialog.Builder(Information.this)
                .setTitle("将会向此邮箱发送验证码，请确认邮箱填写正确。")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        newMail = et_mailbox.getText().toString();
                        request_sendVerifyCode();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }

    private void setmailbox() {
        et_mailbox.setClickable(true);
        iv_clearmailbox.setVisibility(View.INVISIBLE);
        et_mailbox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    showDialog_checkmail();
                }
                return false;
            }
        });
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
    }

    private void showDialog_verify ( ) {
        final EditText et = new EditText(Information.this);
        new AlertDialog.Builder(Information.this).setTitle("请输入验证码：")
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String input = et.getText().toString();
                        if (input.equals("")) {
                            Toast.makeText(getApplicationContext(), "内容不能为空！" + input, Toast.LENGTH_LONG).show();
                            showDialog_verify();
                        }
                        else {
                            int verify = 0;
                            int time = 1;
                            int l = input.length();
                            for ( int i = l - 1 ; i >= 0 ; i-- ) {
                                verify += ( input.charAt(i)-'0' ) * time;
                                time *= 10;
                            }
                            request_changemail(newMail, verify);
                        }
                    }
                })
                .show();
    }

    private void request_sendVerifyCode( ) {
        GetCookieAndToken();
        SendVerifyCodeService sendVerifyCodeService = RetrofitWrapper.getInstance().create(SendVerifyCodeService.class);
        Call<SendVerifyCodeBean> call = sendVerifyCodeService.Send(token,new SendVerifyCodePostData(newMail));
        call.enqueue(new Callback<SendVerifyCodeBean>() {
            @Override
            public void onResponse(Call<SendVerifyCodeBean> call, Response<SendVerifyCodeBean> response) {
                if ( response.isSuccessful() ) {
                    verifyCodeToken = response.body().getVerifyCodeToken();
                    showDialog_verify();
                } else
                    Toast.makeText(Information.this,"加载失败，请重试", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<SendVerifyCodeBean> call, Throwable t) {
                Toast.makeText(Information.this,"请检查网络连接", Toast.LENGTH_LONG).show();
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
                    Toast.makeText(Information.this, "加载失败，请重试", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<InformationBean> call, Throwable t) {
                Toast.makeText(Information.this, "请检查网络连接",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void request_changemail(final String newMail , int verifyCode) {
        GetCookieAndToken();
        ChangeMailService changeMailService = RetrofitWrapper.getInstance().create(ChangeMailService.class);
        Call<ChangeMailBean> call = changeMailService.getReturn(token,verifyCodeToken,new ChangeMailPostData(newMail,verifyCode));
        call.enqueue(new Callback<ChangeMailBean>() {
            @Override
            public void onResponse(Call<ChangeMailBean> call, Response<ChangeMailBean> response) {
                if ( response.isSuccessful() ) {
                    Toast.makeText(Information.this, "修改成功", Toast.LENGTH_LONG).show();
                    et_mailbox.setText(newMail);
                    et_mailbox.clearFocus();//取消焦点
                    ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(Information.this.getCurrentFocus().getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);//关闭输入法
                } else {
                    Toast.makeText(Information.this, "请重试", Toast.LENGTH_LONG).show();
                    showDialog_verify();
                }
            }
            @Override
            public void onFailure(Call<ChangeMailBean> call, Throwable t) {
                Toast.makeText(Information.this, "请检查网络连接", Toast.LENGTH_LONG).show();
                showDialog_verify();
            }
        });
    }
    private void GetCookieAndToken () {
        SharedPreferences data = getSharedPreferences("CandT",MODE_PRIVATE);
        cookie = data.getString("cookie",null);
        token = data.getString("token", null);
    }
}

