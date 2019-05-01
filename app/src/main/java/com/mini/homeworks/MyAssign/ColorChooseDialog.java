package com.mini.homeworks.MyAssign;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mini.homeworks.R;

import java.util.HashMap;
import java.util.Map;

public class ColorChooseDialog extends DialogFragment {

    private ColorListener listener;

    //监听颜色选择的接口
    public interface ColorListener {
        //颜色选择完成后，进行事件操作的方法
        void onColorListener(int color);
    }

    /*设置监听颜色选择的方法
    * @param listener 接口要在ShowPopoupMenu被实现*/
    public void setOnColorListener(ColorListener listener) {
        this.listener = listener;
    }

    private int color;

    //具体颜色选择的逻辑方法
    void showColorChooseDialog ( int originalcolor, Context context ) {

        color = originalcolor;

        View colorchoose_dialog = LayoutInflater.from(context).inflate(R.layout.myassign_colorchange_dialog,null);
        TextView tv_check = colorchoose_dialog.findViewById(R.id.tv_myassign_dialog_check);
        TextView tv_cancel = colorchoose_dialog.findViewById(R.id.tv_myassign_dialog_cancel);
        final ImageView iv_myassign_dialog_933da9 = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_933da9);
        final ImageView iv_myassign_dialog_3f51b5 = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_3f51b5);
        final ImageView iv_myassign_dialog_4cb684 = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_4cb684);
        final ImageView iv_myassign_dialog_4db6ac = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_4db6ac);
        final ImageView iv_myassign_dialog_039be5 = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_039be5);
        final ImageView iv_myassign_dialog_8490c8 = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_8490c8);
        final ImageView iv_myassign_dialog_bcbcbc = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_bcbcbc);
        final ImageView iv_myassign_dialog_cd3278 = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_cd3278);
        final ImageView iv_myassign_dialog_db4437 = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_db4437);
        final ImageView iv_myassign_dialog_e59089 = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_e59089);
        final ImageView iv_myassign_dialog_f6bf26 = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_f6bf26);
        final ImageView iv_myassign_dialog_f37047 = colorchoose_dialog.findViewById(R.id.iv_myassign_dialog_f37047);

        SparseIntArray circle_color = new SparseIntArray();
        //Map<Integer,Integer> circle_color = new HashMap<Integer, Integer>();
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#933DA9") );
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#3F51B5") );
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#039BE5") );
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#F37047") );
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#4DB6AC") );
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#E59089") );
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#DB4437") );
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#BCBCBC") );
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#8490C8") );
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#F6BF26") );
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#CD3278") );
        circle_color.put( R.drawable.color_933da9, Color.parseColor("#4CB684") );

        SparseIntArray color_circle = new SparseIntArray();
        color_circle.put( Color.parseColor("#933DA9"), R.drawable.color_933da9 );
        color_circle.put( Color.parseColor("#3F51B5"), R.drawable.color_933da9 );
        color_circle.put( Color.parseColor("#039BE5"), R.drawable.color_933da9 );
        color_circle.put( Color.parseColor("#F37047"), R.drawable.color_933da9 );
        color_circle.put( Color.parseColor("#4DB6AC"), R.drawable.color_933da9 );
        color_circle.put( Color.parseColor("#E59089"), R.drawable.color_933da9 );
        color_circle.put( Color.parseColor("#DB4437"), R.drawable.color_933da9 );
        color_circle.put( Color.parseColor("#BCBCBC"), R.drawable.color_933da9 );
        color_circle.put( Color.parseColor("#8490C8"), R.drawable.color_933da9 );
        color_circle.put( Color.parseColor("#F6BF26"), R.drawable.color_933da9 );
        color_circle.put( Color.parseColor("#CD3278"), R.drawable.color_933da9 );
        color_circle.put( Color.parseColor("#4CB684"), R.drawable.color_933da9 );

        Map<Integer, ImageView> color_view = new HashMap<>();
        color_view.put( Color.parseColor("#933DA9"), iv_myassign_dialog_933da9 );
        color_view.put( Color.parseColor("#3F51B5"), iv_myassign_dialog_3f51b5 );
        color_view.put( Color.parseColor("#039BE5"), iv_myassign_dialog_039be5 );
        color_view.put( Color.parseColor("#F37047"), iv_myassign_dialog_f37047 );
        color_view.put( Color.parseColor("#4DB6AC"), iv_myassign_dialog_4db6ac );
        color_view.put( Color.parseColor("#E59089"), iv_myassign_dialog_e59089 );
        color_view.put( Color.parseColor("#DB4437"), iv_myassign_dialog_db4437 );
        color_view.put( Color.parseColor("#BCBCBC"), iv_myassign_dialog_bcbcbc );
        color_view.put( Color.parseColor("#8490C8"), iv_myassign_dialog_8490c8 );
        color_view.put( Color.parseColor("#F6BF26"), iv_myassign_dialog_f6bf26 );
        color_view.put( Color.parseColor("#CD3278"), iv_myassign_dialog_cd3278 );
        color_view.put( Color.parseColor("#4CB684"), iv_myassign_dialog_4cb684 );

        ImageView t = color_view.get(originalcolor);
        final Resources[] r = {getResources()};
        Drawable[] layers = new Drawable[2];
        layers[0] = r[0].getDrawable(R.drawable.tick);
        layers[1] = r[0].getDrawable(color_circle.get(originalcolor));
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        t.setImageDrawable(layerDrawable);


        final AlertDialog.Builder layoutDialog = new AlertDialog.Builder(context);
        layoutDialog.setView(colorchoose_dialog);

        iv_myassign_dialog_3f51b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("3F51B5");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_3f51b5);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_3f51b5.setImageDrawable(layerDrawable);
                iv_myassign_dialog_4cb684.setImageResource(R.drawable.color_4cb684);
                iv_myassign_dialog_4db6ac.setImageResource(R.drawable.color_4db6ac);
                iv_myassign_dialog_039be5.setImageResource(R.drawable.color_039be5);
                iv_myassign_dialog_933da9.setImageResource(R.drawable.color_933da9);
                iv_myassign_dialog_f37047.setImageResource(R.drawable.color_f37047);
                iv_myassign_dialog_f6bf26.setImageResource(R.drawable.color_f6bf26);
                iv_myassign_dialog_e59089.setImageResource(R.drawable.color_e59089);
                iv_myassign_dialog_db4437.setImageResource(R.drawable.color_db4437);
                iv_myassign_dialog_cd3278.setImageResource(R.drawable.color_cd3278);
                iv_myassign_dialog_bcbcbc.setImageResource(R.drawable.color_bcbcbc);
                iv_myassign_dialog_8490c8.setImageResource(R.drawable.color_8490c8);
            }
        });

        iv_myassign_dialog_4cb684.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("4CB684");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_4cb684);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_4cb684.setImageDrawable(layerDrawable);
                iv_myassign_dialog_3f51b5.setImageResource(R.drawable.color_3f51b5);
                iv_myassign_dialog_4db6ac.setImageResource(R.drawable.color_4db6ac);
                iv_myassign_dialog_039be5.setImageResource(R.drawable.color_039be5);
                iv_myassign_dialog_933da9.setImageResource(R.drawable.color_933da9);
                iv_myassign_dialog_f37047.setImageResource(R.drawable.color_f37047);
                iv_myassign_dialog_f6bf26.setImageResource(R.drawable.color_f6bf26);
                iv_myassign_dialog_e59089.setImageResource(R.drawable.color_e59089);
                iv_myassign_dialog_db4437.setImageResource(R.drawable.color_db4437);
                iv_myassign_dialog_cd3278.setImageResource(R.drawable.color_cd3278);
                iv_myassign_dialog_bcbcbc.setImageResource(R.drawable.color_bcbcbc);
                iv_myassign_dialog_8490c8.setImageResource(R.drawable.color_8490c8);
            }
        });

        iv_myassign_dialog_4db6ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("4DB6AC");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_4db6ac);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_4db6ac.setImageDrawable(layerDrawable);
                iv_myassign_dialog_3f51b5.setImageResource(R.drawable.color_3f51b5);
                iv_myassign_dialog_4cb684.setImageResource(R.drawable.color_4cb684);
                iv_myassign_dialog_039be5.setImageResource(R.drawable.color_039be5);
                iv_myassign_dialog_933da9.setImageResource(R.drawable.color_933da9);
                iv_myassign_dialog_f37047.setImageResource(R.drawable.color_f37047);
                iv_myassign_dialog_f6bf26.setImageResource(R.drawable.color_f6bf26);
                iv_myassign_dialog_e59089.setImageResource(R.drawable.color_e59089);
                iv_myassign_dialog_db4437.setImageResource(R.drawable.color_db4437);
                iv_myassign_dialog_cd3278.setImageResource(R.drawable.color_cd3278);
                iv_myassign_dialog_bcbcbc.setImageResource(R.drawable.color_bcbcbc);
                iv_myassign_dialog_8490c8.setImageResource(R.drawable.color_8490c8);
            }
        });

        iv_myassign_dialog_039be5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("039BE5");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_039be5);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_039be5.setImageDrawable(layerDrawable);
                iv_myassign_dialog_3f51b5.setImageResource(R.drawable.color_3f51b5);
                iv_myassign_dialog_4cb684.setImageResource(R.drawable.color_4cb684);
                iv_myassign_dialog_4db6ac.setImageResource(R.drawable.color_4db6ac);
                iv_myassign_dialog_933da9.setImageResource(R.drawable.color_933da9);
                iv_myassign_dialog_f37047.setImageResource(R.drawable.color_f37047);
                iv_myassign_dialog_f6bf26.setImageResource(R.drawable.color_f6bf26);
                iv_myassign_dialog_e59089.setImageResource(R.drawable.color_e59089);
                iv_myassign_dialog_db4437.setImageResource(R.drawable.color_db4437);
                iv_myassign_dialog_cd3278.setImageResource(R.drawable.color_cd3278);
                iv_myassign_dialog_bcbcbc.setImageResource(R.drawable.color_bcbcbc);
                iv_myassign_dialog_8490c8.setImageResource(R.drawable.color_8490c8);
            }
        });

        iv_myassign_dialog_933da9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("933DA9");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_933da9);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_933da9.setImageDrawable(layerDrawable);
                iv_myassign_dialog_3f51b5.setImageResource(R.drawable.color_3f51b5);
                iv_myassign_dialog_4cb684.setImageResource(R.drawable.color_4cb684);
                iv_myassign_dialog_4db6ac.setImageResource(R.drawable.color_4db6ac);
                iv_myassign_dialog_039be5.setImageResource(R.drawable.color_039be5);
                iv_myassign_dialog_f37047.setImageResource(R.drawable.color_f37047);
                iv_myassign_dialog_f6bf26.setImageResource(R.drawable.color_f6bf26);
                iv_myassign_dialog_e59089.setImageResource(R.drawable.color_e59089);
                iv_myassign_dialog_db4437.setImageResource(R.drawable.color_db4437);
                iv_myassign_dialog_cd3278.setImageResource(R.drawable.color_cd3278);
                iv_myassign_dialog_bcbcbc.setImageResource(R.drawable.color_bcbcbc);
                iv_myassign_dialog_8490c8.setImageResource(R.drawable.color_8490c8);
            }
        });

        iv_myassign_dialog_8490c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("8490C8");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_8490c8);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_8490c8.setImageDrawable(layerDrawable);
                iv_myassign_dialog_3f51b5.setImageResource(R.drawable.color_3f51b5);
                iv_myassign_dialog_4cb684.setImageResource(R.drawable.color_4cb684);
                iv_myassign_dialog_4db6ac.setImageResource(R.drawable.color_4db6ac);
                iv_myassign_dialog_039be5.setImageResource(R.drawable.color_039be5);
                iv_myassign_dialog_f37047.setImageResource(R.drawable.color_f37047);
                iv_myassign_dialog_f6bf26.setImageResource(R.drawable.color_f6bf26);
                iv_myassign_dialog_e59089.setImageResource(R.drawable.color_e59089);
                iv_myassign_dialog_db4437.setImageResource(R.drawable.color_db4437);
                iv_myassign_dialog_cd3278.setImageResource(R.drawable.color_cd3278);
                iv_myassign_dialog_bcbcbc.setImageResource(R.drawable.color_bcbcbc);
                iv_myassign_dialog_933da9.setImageResource(R.drawable.color_933da9);
            }
        });

        iv_myassign_dialog_bcbcbc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("BCBCBC");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_bcbcbc);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_bcbcbc.setImageDrawable(layerDrawable);
                iv_myassign_dialog_3f51b5.setImageResource(R.drawable.color_3f51b5);
                iv_myassign_dialog_4cb684.setImageResource(R.drawable.color_4cb684);
                iv_myassign_dialog_4db6ac.setImageResource(R.drawable.color_4db6ac);
                iv_myassign_dialog_039be5.setImageResource(R.drawable.color_039be5);
                iv_myassign_dialog_f37047.setImageResource(R.drawable.color_f37047);
                iv_myassign_dialog_f6bf26.setImageResource(R.drawable.color_f6bf26);
                iv_myassign_dialog_e59089.setImageResource(R.drawable.color_e59089);
                iv_myassign_dialog_db4437.setImageResource(R.drawable.color_db4437);
                iv_myassign_dialog_cd3278.setImageResource(R.drawable.color_cd3278);
                iv_myassign_dialog_8490c8.setImageResource(R.drawable.color_8490c8);
                iv_myassign_dialog_933da9.setImageResource(R.drawable.color_933da9);
            }
        });

        iv_myassign_dialog_cd3278.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("CD3278");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_cd3278);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_cd3278.setImageDrawable(layerDrawable);
                iv_myassign_dialog_3f51b5.setImageResource(R.drawable.color_3f51b5);
                iv_myassign_dialog_4cb684.setImageResource(R.drawable.color_4cb684);
                iv_myassign_dialog_4db6ac.setImageResource(R.drawable.color_4db6ac);
                iv_myassign_dialog_039be5.setImageResource(R.drawable.color_039be5);
                iv_myassign_dialog_f37047.setImageResource(R.drawable.color_f37047);
                iv_myassign_dialog_f6bf26.setImageResource(R.drawable.color_f6bf26);
                iv_myassign_dialog_e59089.setImageResource(R.drawable.color_e59089);
                iv_myassign_dialog_db4437.setImageResource(R.drawable.color_db4437);
                iv_myassign_dialog_bcbcbc.setImageResource(R.drawable.color_bcbcbc);
                iv_myassign_dialog_8490c8.setImageResource(R.drawable.color_8490c8);
                iv_myassign_dialog_933da9.setImageResource(R.drawable.color_933da9);
            }
        });

        iv_myassign_dialog_db4437.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("DB4437");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_db4437);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_db4437.setImageDrawable(layerDrawable);
                iv_myassign_dialog_3f51b5.setImageResource(R.drawable.color_3f51b5);
                iv_myassign_dialog_4cb684.setImageResource(R.drawable.color_4cb684);
                iv_myassign_dialog_4db6ac.setImageResource(R.drawable.color_4db6ac);
                iv_myassign_dialog_039be5.setImageResource(R.drawable.color_039be5);
                iv_myassign_dialog_f37047.setImageResource(R.drawable.color_f37047);
                iv_myassign_dialog_f6bf26.setImageResource(R.drawable.color_f6bf26);
                iv_myassign_dialog_e59089.setImageResource(R.drawable.color_e59089);
                iv_myassign_dialog_cd3278.setImageResource(R.drawable.color_cd3278);
                iv_myassign_dialog_bcbcbc.setImageResource(R.drawable.color_bcbcbc);
                iv_myassign_dialog_8490c8.setImageResource(R.drawable.color_8490c8);
                iv_myassign_dialog_933da9.setImageResource(R.drawable.color_933da9);
            }
        });

        iv_myassign_dialog_e59089.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("E59089");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_e59089);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_e59089.setImageDrawable(layerDrawable);
                iv_myassign_dialog_3f51b5.setImageResource(R.drawable.color_3f51b5);
                iv_myassign_dialog_4cb684.setImageResource(R.drawable.color_4cb684);
                iv_myassign_dialog_4db6ac.setImageResource(R.drawable.color_4db6ac);
                iv_myassign_dialog_039be5.setImageResource(R.drawable.color_039be5);
                iv_myassign_dialog_f37047.setImageResource(R.drawable.color_f37047);
                iv_myassign_dialog_f6bf26.setImageResource(R.drawable.color_f6bf26);
                iv_myassign_dialog_db4437.setImageResource(R.drawable.color_db4437);
                iv_myassign_dialog_cd3278.setImageResource(R.drawable.color_cd3278);
                iv_myassign_dialog_bcbcbc.setImageResource(R.drawable.color_bcbcbc);
                iv_myassign_dialog_8490c8.setImageResource(R.drawable.color_8490c8);
                iv_myassign_dialog_933da9.setImageResource(R.drawable.color_933da9);
            }
        });

        iv_myassign_dialog_f6bf26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("F6BF26");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_f6bf26);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_f6bf26.setImageDrawable(layerDrawable);
                iv_myassign_dialog_3f51b5.setImageResource(R.drawable.color_3f51b5);
                iv_myassign_dialog_4cb684.setImageResource(R.drawable.color_4cb684);
                iv_myassign_dialog_4db6ac.setImageResource(R.drawable.color_4db6ac);
                iv_myassign_dialog_039be5.setImageResource(R.drawable.color_039be5);
                iv_myassign_dialog_f37047.setImageResource(R.drawable.color_f37047);
                iv_myassign_dialog_e59089.setImageResource(R.drawable.color_e59089);
                iv_myassign_dialog_db4437.setImageResource(R.drawable.color_db4437);
                iv_myassign_dialog_cd3278.setImageResource(R.drawable.color_cd3278);
                iv_myassign_dialog_bcbcbc.setImageResource(R.drawable.color_bcbcbc);
                iv_myassign_dialog_8490c8.setImageResource(R.drawable.color_8490c8);
                iv_myassign_dialog_933da9.setImageResource(R.drawable.color_933da9);
            }
        });

        iv_myassign_dialog_f37047.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = Color.parseColor("F37047");
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(R.drawable.tick);
                layers[1] = r.getDrawable(R.drawable.color_f37047);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                iv_myassign_dialog_f37047.setImageDrawable(layerDrawable);
                iv_myassign_dialog_3f51b5.setImageResource(R.drawable.color_3f51b5);
                iv_myassign_dialog_4cb684.setImageResource(R.drawable.color_4cb684);
                iv_myassign_dialog_4db6ac.setImageResource(R.drawable.color_4db6ac);
                iv_myassign_dialog_039be5.setImageResource(R.drawable.color_039be5);
                iv_myassign_dialog_f6bf26.setImageResource(R.drawable.color_f6bf26);
                iv_myassign_dialog_e59089.setImageResource(R.drawable.color_e59089);
                iv_myassign_dialog_db4437.setImageResource(R.drawable.color_db4437);
                iv_myassign_dialog_cd3278.setImageResource(R.drawable.color_cd3278);
                iv_myassign_dialog_bcbcbc.setImageResource(R.drawable.color_bcbcbc);
                iv_myassign_dialog_8490c8.setImageResource(R.drawable.color_8490c8);
                iv_myassign_dialog_933da9.setImageResource(R.drawable.color_933da9);
            }
        });

        tv_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onColorListener(color); //通知下一步已经选择好的颜色
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        dialog.dismiss();
                    }
                });
            }
        });

        layoutDialog.create().show();




    }

}
