package com.mini.homeworks.MyAssign;

import android.content.Context;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.mini.homeworks.R;

public class ShowPopupMenu {
    public static void showPopupMenu(View view, int type, Context mContext) {
        // 这里的view代表popupMenu需要依附的view
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        // 获取布局文件
        if ( type == 0 )
            popupMenu.getMenuInflater().inflate(R.menu.myassign_menu_overhead, popupMenu.getMenu());
        else
            popupMenu.getMenuInflater().inflate(R.menu.myassign_menu_usual,popupMenu.getMenu());
        popupMenu.show();
        // 通过上面这几行代码，就可以把控件显示出来了
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.delete_usual: {
                        break;
                    }
                    case R.id.overhead_usual: {
                        break;
                    }
                    case R.id.changecolor_usual: {

                        break;
                    }
                    default:
                        break;
                }
                // 控件每一个item的点击事件
                return true;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                // 控件消失时的事件
            }
        });

    }
}
