package com.mini.homeworks.MyAssign;

import android.database.sqlite.SQLiteDatabase;

import com.mini.homeworks.net.bean.AssignmentBean;
import com.mini.homeworks.MyAssign.Bean.Delete;
import com.mini.homeworks.MyAssign.Bean.Normal;
import com.mini.homeworks.MyAssign.Bean.Overhead;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;


class ManangeData {

    static void OverheadColorChange(AssignmentBean data , int color) {
        Overhead tmp  = new Overhead();
        tmp.setColor(color);
        tmp.updateAll("siteId = ?" , data.getSiteId() );
    }

    static void NormalColorChange(AssignmentBean data, int color ) {
        Normal tmp = new Normal();
        tmp.setColor(color);
        tmp.updateAll("siteId = ? " , data.getSiteId() );
    }

    static void PutOverhead(AssignmentBean data) {
        SQLiteDatabase db = LitePal.getDatabase();
        List<Overhead> overhead = LitePal.findAll(Overhead.class);
        List<Delete> delete = LitePal.findAll(Delete.class);
        List<Normal> normal = LitePal.findAll(Normal.class);
        List<AssignmentBean> assignmentBean = new ArrayList<>();

        Overhead tmp = new Overhead();
        tmp.setAssignId(data.getAssignId());
        tmp.setAssignName(data.getAssignName());
        tmp.setBeginTime(data.getBeginTime());
        tmp.setEndTime(data.getEndTime());
        tmp.setColor(data.getColor());
        tmp.setStatus(data.getStatus());
        tmp.setSiteId(data.getSiteId());
        overhead.add(tmp);
        tmp.save();  //增加顶置
        for ( int i = 0 ; i < overhead.size() ; i++ ) {
            AssignmentBean t = new AssignmentBean();
            t.setSiteId(overhead.get(i).getSiteId());
            t.setBeginTime(overhead.get(i).getBeginTime());
            t.setEndTime(overhead.get(i).getEndTime());
            t.setAssignName(overhead.get(i).getAssignName());
            t.setStatus(overhead.get(i).getStatus());
            t.setColor(overhead.get(i).getColor());
            t.setType(0);
            assignmentBean.add(t);
        }
        for( int i = 0 ; i < normal.size() ; i++ ) {
            if ( normal.get(i).getSiteId().equals( data.getAssignId() ) ) {
                LitePal.deleteAll(Normal.class,"siteId = ?",data.getSiteId());//删除普通
                break;
            }
            AssignmentBean t = new AssignmentBean();
            t.setSiteId(normal.get(i).getSiteId());
            t.setBeginTime(normal.get(i).getBeginTime());
            t.setEndTime(normal.get(i).getEndTime());
            t.setAssignName(normal.get(i).getAssignName());
            t.setStatus(normal.get(i).getStatus());
            t.setColor(normal.get(i).getColor());
            t.setType(1);
            assignmentBean.add(t);
        }

    }

    static void Delete(AssignmentBean data) {
        SQLiteDatabase db = LitePal.getDatabase();
        List<Overhead> overhead = LitePal.findAll(Overhead.class);
        List<Delete> delete = LitePal.findAll(Delete.class);
        List<Normal> normal = LitePal.findAll(Normal.class);
        List<AssignmentBean> assignmentBean = new ArrayList<>();

        Delete tmp = new Delete();
        tmp.setAssignId(data.getAssignId());
        tmp.setAssignName(data.getAssignName());
        tmp.setBeginTime(data.getBeginTime());
        tmp.setEndTime(data.getEndTime());
        tmp.setColor(data.getColor());
        tmp.setSiteId(data.getSiteId());
        tmp.setStatus(data.getStatus());
        tmp.save();    //增加删除item
        switch ( data.getType() ) {
            case 0: {   //删除顶置item
                for ( int i = 0 ; i < overhead.size() ; i++ ) {
                    if ( overhead.get(i).getSiteId().equals( data.getAssignId() ) ) {
                        LitePal.deleteAll(Overhead.class,"siteId = ?",data.getSiteId());
                        break;
                    }
                    AssignmentBean t = new AssignmentBean();
                    t.setSiteId(overhead.get(i).getSiteId());
                    t.setBeginTime(overhead.get(i).getBeginTime());
                    t.setEndTime(overhead.get(i).getEndTime());
                    t.setAssignName(overhead.get(i).getAssignName());
                    t.setStatus(overhead.get(i).getStatus());
                    t.setColor(overhead.get(i).getColor());
                    t.setType(0);
                    assignmentBean.add(t);
                }
                for( int i = 0 ; i < normal.size() ; i++ ) {
                    AssignmentBean t = new AssignmentBean();
                    t.setSiteId(normal.get(i).getSiteId());
                    t.setBeginTime(normal.get(i).getBeginTime());
                    t.setEndTime(normal.get(i).getEndTime());
                    t.setAssignName(normal.get(i).getAssignName());
                    t.setStatus(normal.get(i).getStatus());
                    t.setColor(normal.get(i).getColor());
                    t.setType(1);
                    assignmentBean.add(t);
                }
                break;
            }
            case 1: { //删除普通item
                for ( int i = 0 ; i < overhead.size() ; i++ ) {
                    AssignmentBean t = new AssignmentBean();
                    t.setSiteId(overhead.get(i).getSiteId());
                    t.setBeginTime(overhead.get(i).getBeginTime());
                    t.setEndTime(overhead.get(i).getEndTime());
                    t.setAssignName(overhead.get(i).getAssignName());
                    t.setStatus(overhead.get(i).getStatus());
                    t.setColor(overhead.get(i).getColor());
                    t.setType(0);
                    assignmentBean.add(t);
                }
                for( int i = 0 ; i < normal.size() ; i++ ) {
                    if ( normal.get(i).getSiteId().equals( data.getAssignId() ) ) {
                        LitePal.deleteAll(Normal.class,"siteId = ?",data.getSiteId());
                        break;
                    }
                    AssignmentBean t = new AssignmentBean();
                    t.setSiteId(normal.get(i).getSiteId());
                    t.setBeginTime(normal.get(i).getBeginTime());
                    t.setEndTime(normal.get(i).getEndTime());
                    t.setAssignName(normal.get(i).getAssignName());
                    t.setStatus(normal.get(i).getStatus());
                    t.setColor(normal.get(i).getColor());
                    t.setType(1);
                    assignmentBean.add(t);
                }
                break;
            }
        }
    }

    static void PutNormal(AssignmentBean data) {
        SQLiteDatabase db = LitePal.getDatabase();
        List<Overhead> overhead = LitePal.findAll(Overhead.class);
        List<Delete> delete = LitePal.findAll(Delete.class);
        List<Normal> normal = LitePal.findAll(Normal.class);
        List<AssignmentBean> assignmentBean = new ArrayList<>();

        Normal tmp = new Normal();
        tmp.setAssignId(data.getAssignId());
        tmp.setAssignName(data.getAssignName());
        tmp.setBeginTime(data.getBeginTime());
        tmp.setEndTime(data.getEndTime());
        tmp.setColor(data.getColor());
        tmp.setStatus(data.getStatus());
        tmp.setSiteId(data.getSiteId());
        normal.add(tmp);
        tmp.save();  //增加普通
        for ( int i = 0 ; i < overhead.size() ; i++ ) {
            if ( overhead.get(i).getSiteId().equals( data.getAssignId() ) ) {
                LitePal.deleteAll(Overhead.class,"siteId = ?",data.getSiteId());//删除顶置
                break;
            }
            AssignmentBean t = new AssignmentBean();
            t.setSiteId(overhead.get(i).getSiteId());
            t.setBeginTime(overhead.get(i).getBeginTime());
            t.setEndTime(overhead.get(i).getEndTime());
            t.setAssignName(overhead.get(i).getAssignName());
            t.setStatus(overhead.get(i).getStatus());
            t.setColor(overhead.get(i).getColor());
            t.setType(0);
            assignmentBean.add(t);
        }
        for( int i = 0 ; i < normal.size() ; i++ ) {
            AssignmentBean t = new AssignmentBean();
            t.setSiteId(normal.get(i).getSiteId());
            t.setBeginTime(normal.get(i).getBeginTime());
            t.setEndTime(normal.get(i).getEndTime());
            t.setAssignName(normal.get(i).getAssignName());
            t.setStatus(normal.get(i).getStatus());
            t.setColor(normal.get(i).getColor());
            t.setType(1);
            assignmentBean.add(t);
        }
    }






}
