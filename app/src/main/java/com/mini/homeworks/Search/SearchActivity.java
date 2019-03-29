package com.mini.homeworks.Search;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.mini.homeworks.R;
import com.mini.homeworks.Utils.RetrofitWrapper;

import java.lang.reflect.Method;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    SearchView mSearchView;
    SearchView.SearchAutoComplete mSearchAutoComplete;
    String cookie, token;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);

        toolbar = findViewById(R.id.toolbar_search);
        listView = findViewById(R.id.lv_search);
        setSupportActionBar(toolbar);


        initNavigationIcon();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);

        // 找到searchView
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        mSearchAutoComplete = (SearchView.SearchAutoComplete) mSearchView.findViewById(R.id.search_src_text);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchCourse(s);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    private void initNavigationIcon() {
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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    finish();
                }
            }
        });
    }

    private void searchCourse(String s) {

        SearchService searchService = RetrofitWrapper.getInstance().create(SearchService.class);
        Call<SearchBean> call = searchService.getSearchBean(cookie, token, s);
        call.enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                SearchBean searchBean = response.body();
                initBean(searchBean);
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {

            }
        });






    }
    private void initBean(SearchBean searchBean){

    }
}
