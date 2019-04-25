package com.mini.homeworks.MyAssign;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.mini.homeworks.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShowColorChangeDialog extends AppCompatActivity implements AdapterView.OnItemClickListener {



    public interface GetColor {
        public int getcolor(int id);
    }
    int[] color = { R.drawable.color_933da9, R.drawable.color_3f51b5, R.drawable.color_0388c9,
            R.drawable.color_f37047, R.drawable.color_4db6ac, R.drawable.color_e59089,
            R.drawable.color_db4437, R.drawable.color_bcbcbc, R.drawable.color_8490c8,
            R.drawable.color_f6bf26, R.drawable.color_cd3278 };

    public void ShowColorChangeDialog (Context context , final GetColor getcolor, int lastcolorid) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myassign_colorchange_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.myassign_colorchange_dialog_title);
        builder.setView(view);
        builder.create();

        TextView check , cancel;
        GridView colors;
        colors = view.findViewById(R.id.gv_colors);
        check = view.findViewById(R.id.tv_myassign_changecolor_check);
        cancel = view.findViewById(R.id.tv_myassign_changecolor_cancel);

        ArrayList<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < color.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            if (lastcolorid == color[i]) {
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(color[i]);
                layers[1] = r.getDrawable(R.drawable.tick);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                map.put("image",layers);
            }else
                map.put("image", color[i]);
            arrayList.add(map);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(context, arrayList,
                R.layout.myassgin_colorchange_dialog_gridview_item, new java.lang.String[] { "image" },
                new int[] { R.id.iv_myassgin_dialog_color });
        colors.setAdapter(simpleAdapter);
        colors.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView item = (ImageView) parent.getItemAtPosition(position);
                Resources r = getResources();
                Drawable[] layers = new Drawable[2];
                layers[0] = r.getDrawable(color[position-1]);
                layers[1] = r.getDrawable(R.drawable.tick);
                LayerDrawable layerDrawable = new LayerDrawable(layers);
                item.setImageDrawable(layerDrawable);
                getcolor.getcolor(color[position-1]);
            }
        });


        check.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick (View v) {

            }
        });

        cancel.setOnClickListener(new android.view.View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        builder.show();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ImageView item = (ImageView) parent.getItemAtPosition(position);
        Resources r = getResources();
        Drawable[] layers = new Drawable[2];
        layers[0] = r.getDrawable(item.getImageAlpha());
        layers[1] = r.getDrawable(R.drawable.tick);
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        item.setImageDrawable(layerDrawable);
    }
}