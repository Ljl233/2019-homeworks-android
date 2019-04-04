package com.mini.homeworks.Information;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mini.homeworks.R;
import com.mini.homeworks.Utils.RetrofitWrapper;

import retrofit2.Call;

public class InformationActivity extends AppCompatActivity {
    private TextView tv_infor_name;
    private TextView tv_infor_id;
    private TextView tv_infor_mail;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_view);

        init();
        request();
    }

    private void init() {
        tv_infor_id = findViewById(R.id.tv_infor_id);
        tv_infor_mail = findViewById(R.id.tv_infor_mail);
        tv_infor_name = findViewById(R.id.tv_infor_name);

    }

    private void request() {
        InformationService informationService = RetrofitWrapper.getInstance().create(InformationService.class);
        Call<InformationBean> call = informationService.getInformationBean(token);
    }


}
