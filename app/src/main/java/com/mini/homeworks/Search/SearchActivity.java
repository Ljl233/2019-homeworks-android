package com.mini.homeworks.Search;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mini.homeworks.R;
import com.mini.homeworks.net.RetrofitWrapper;
import com.mini.homeworks.net.Service.SearchService;
import com.mini.homeworks.net.bean.SearchBean;

import java.lang.reflect.Method;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

import static android.view.View.INVISIBLE;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SearchView mSearchView;
    private SearchView.SearchAutoComplete mSearchAutoComplete;
    private String cookie, token;
    private RecyclerView recyclerView;

    private Context context;

    //初始化数据库变量：历史记录
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    //初始化 删除搜索记录
    private TextView tv_clear;
    private ImageView iv_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);

        //实例化toolbar
        toolbar = findViewById(R.id.toolbar_search);
        recyclerView = findViewById(R.id.ry_search);
        setSupportActionBar(toolbar);
        //返回键 当搜索框显示时， 按下ToolBar的返回按钮关闭搜索框，否则关闭当前界面
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSearchAutoComplete.isShown()) {
                    try {
                        mSearchAutoComplete.setText("");
                        //利用反射调用收起SearchView的onCloseClicked()方法
                        Method method = mSearchAutoComplete.getClass().getDeclaredMethod("onCloseClicked");
                        method.setAccessible(true);
                        method.invoke(mSearchView);
                        tv_clear.setVisibility(View.VISIBLE);
                        iv_clear.setVisibility(View.VISIBLE);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
            }
        });

        //实例化 删除搜索记录 设置不可见
        tv_clear = findViewById(R.id.tv_clear);
        iv_clear = findViewById(R.id.iv_clear);
        tv_clear.setVisibility(INVISIBLE);
        iv_clear.setVisibility(INVISIBLE);

        //实例化SQLiteOpenHelper子类对象
        helper = new RecordSQLiteOpenHelper(context);




        //设置 删除搜索记录 监听器
        tv_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清除数据库
                deleteData();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
//        MenuItem searchItem = menu.findItem(R.id.action_search);
        // 通过MenuItem得到searchView
        mSearchView = (SearchView) searchItem.getActionView();
        // 通过Id得到搜索框界面(SearchView.SearchAutoComplete)
        mSearchAutoComplete = mSearchView.findViewById(R.id.search_src_text);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                searchCourse(s);
                return false;
            }
        });
        //设置键盘监听事件,监听键盘上的搜索键
        mSearchView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    searchCourse(mSearchView.getQuery().toString());
                    //点击搜索键后，对该字段在数据库是否存在进行检查
                    boolean hasData = hasData(mSearchView.getQuery().toString());
                    //若存在，则不保存；若 不存在则保存
                    if (!hasData) {
                        insertData(mSearchView.getQuery().toString().trim());
                        //显示。。。。。。
                    }
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }



    //网络请求
    private void searchCourse(final String s) {

        //设置清除历史按钮不可见
        tv_clear.setVisibility(INVISIBLE);
        iv_clear.setVisibility(INVISIBLE);
        GetCookieAndToken();
        SearchService searchService = RetrofitWrapper.getInstance().create(SearchService.class);
        Call<SearchBean> call = searchService.getSearchBean(cookie, token, s);
        call.enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                cookie = response.body().getCookie();
                SaveCookie(cookie);
                SearchBean searchBean = response.body();
                SearchAdapter searchAdapter = new SearchAdapter(searchBean, s);
                recyclerView.setAdapter(searchAdapter);

            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "请求失败，请重试", Toast.LENGTH_LONG).show();
            }
        });
    }


    //检查数据库中是否已经有该搜索记录
    private boolean hasData(String tempName) {
        //从数据库中Record表里找到name=tempName的Id
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();

    }

    //插入数据到数据库
    private void insertData(String tempName) {
        db = helper.getWritableDatabase();
        db.execSQL("insert into records(name) values('" + tempName + "')");
        db.close();
    }

    //清除数据库
    private void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
        tv_clear.setVisibility(INVISIBLE);
        iv_clear.setVisibility(INVISIBLE);
    }

    //查询数据，显示到listView列表上
    private void query(String temp){
        //1.模糊搜索
        Cursor cursor=helper.getReadableDatabase().rawQuery(
                "select id as _id,name from rcords where name like '%"+ temp+"%' order by id desc ",null
        );
        //2.创建adapter适配器对象，装入结果
        // ArrayAdapter adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,)
    }


    //    private void search(SearchBean searchBean, String key) {
//
//        SearchAdapter searchAdapter = new SearchAdapter(searchBean, key);
//        recyclerView.setAdapter(searchAdapter);
//
//    }
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
