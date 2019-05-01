package com.mini.homeworks.MyAssign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;

import com.mini.homeworks.MyAssign.Bean.Assignment;
import com.mini.homeworks.R;

import java.lang.reflect.Field;


class ShowPopupMenu {

    private static OperationListener listener;
    static int presentcolor;

    //监听菜单操作的接口
    public interface OperationListener{
        void onOperationListener(int type,int color);
        /*
        * type：
        * 0：顶置变色（color = 变化的颜色，若无变化color = -1）
        * 1：普通变色（color = 变化的颜色，若无变化color = -1）
        * 2：删除顶置（color = -1）
        * 3：删除普通（color = -1）
        * 4：顶置（color = -1）
        * 5：取消顶置（color = -1）
         */
    }

    /*设置监听菜单操作的方法
     * @param listener 接口要在Adapter里被实现 */
    public void setOperationListener(OperationListener listener) {
        this.listener = listener;
    }


    @SuppressLint("RestrictedApi")
    static void showPopupMenu(final View view, final Assignment data, final Context mContext) {


        //监听要先找到Dialog
        final ColorChooseDialog colorChooseDialog = new ColorChooseDialog();

        //接收选择到的颜色
        colorChooseDialog.setOnColorListener(new ColorChooseDialog.ColorListener() {
            @Override
            public void onColorListener(int color) {
                Log.d("选择了："," "+color);
                //颜色选择完成之后开始进行下一步操作
                presentcolor = color;
            }
        });


        // 这里的view代表popupMenu需要依附的view
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        // 获取布局文件
        if ( data.getType() == 0 )
            popupMenu.getMenuInflater().inflate(R.menu.myassign_menu_overhead, popupMenu.getMenu());
        else
            popupMenu.getMenuInflater().inflate(R.menu.myassign_menu_usual,popupMenu.getMenu());
        popupMenu.show();
        // 通过上面这几行代码，就可以把控件显示出来了
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ImageView iv_menu = view.findViewById(R.id.iv_menu);
                switch (item.getItemId()) {
                    case R.id.delete_overhead: { // 2：删除顶置（color = -1）
                        ManangeData.Delete(data);
                        listener.onOperationListener(2,-1);
                        iv_menu.setImageResource(R.drawable.pull_down);
                        break;
                    }
                    case R.id.delete_usual: { // 3：删除普通（color = -1）
                        ManangeData.Delete(data);
                        listener.onOperationListener(3,-1);
                        iv_menu.setImageResource(R.drawable.pull_down);
                        break;
                    }
                    case R.id.overhead_usual: { // 4：顶置（color = -1）
                        ManangeData.PutOverhead(data);
                        listener.onOperationListener(4,-1);
                        iv_menu.setImageResource(R.drawable.pull_down);
                        break;
                    }
                    case R.id.changecolor_usual: { // 1：普通变色（color = 变化的颜色，若无变化color = -1）
                        //进行颜色选择
                        colorChooseDialog.showColorChooseDialog(data.getColor(),mContext);
                        if ( data.getColor() == presentcolor )
                            listener.onOperationListener(1,-1);
                        else {
                            ManangeData.NormalColorChange(data,presentcolor);
                            listener.onOperationListener(1,presentcolor);
                        }
                        iv_menu.setImageResource(R.drawable.pull_down);
                        break;
                    }
                    case R.id.changecolor_overhead: { // 0：顶置变色（color = 变化的颜色，若无变化color = -1）
                        //进行颜色选择
                        colorChooseDialog.showColorChooseDialog(data.getColor(),mContext);
                        if ( data.getColor() == presentcolor )
                            listener.onOperationListener(0,-1);
                        else{
                            ManangeData.OverheadColorChange(data,presentcolor);
                            listener.onOperationListener(0,presentcolor);
                        }
                        iv_menu.setImageResource(R.drawable.pull_down);
                        break;
                    }
                    case R.id.overhead_overhead: {  // 5：取消顶置（color = -1）
                        ManangeData.PutNormal(data);
                        listener.onOperationListener(5,-1);
                        iv_menu.setImageResource(R.drawable.pull_down);
                        break;
                    }
                    default: {
                        iv_menu.setImageResource(R.drawable.pull_down);
                        break;
                    }
                }
                // 控件每一个item的点击事件
                return true;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                ImageView iv_menu = view.findViewById(R.id.iv_menu);
                iv_menu.setImageResource(R.drawable.pull_down);
                // 控件消失时的事件
            }
        });
        //通过反射获取MenuPopupHelper实例，然后设置setForceShowIcon为true
        try {
            Field mFieldPopup = popupMenu.getClass().getDeclaredField("mPopup");
            mFieldPopup.setAccessible(true);
            MenuPopupHelper mPopup = (MenuPopupHelper) mFieldPopup.get(popupMenu);
            mPopup.setForceShowIcon(true);
        } catch (Exception e) {

        }
        popupMenu.show();
    }

}
