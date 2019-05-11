package com.mini.homeworks.MyAssign;


import com.mini.homeworks.net.bean.AssignmentBean;
import com.mini.homeworks.MyAssign.Bean.mDelete;
import com.mini.homeworks.MyAssign.Bean.mNormal;
import com.mini.homeworks.MyAssign.Bean.mOverhead;

import org.litepal.LitePal;
import java.util.List;


class ManangeData {

    static void OverheadColorChange(AssignmentBean data , int color) {
        mOverhead tmp  = new mOverhead();
        tmp.setColor(color);
        tmp.updateAll("assignId = ?" , data.getAssignId() );
    }

    static void NormalColorChange(AssignmentBean data, int color ) {
        mNormal tmp = new mNormal();
        tmp.setColor(color);
        tmp.updateAll("assignId = ? " , data.getAssignId() );
    }

    static void PutOverhead(AssignmentBean data) {
        List<mOverhead> overhead = LitePal.findAll(mOverhead.class);
        List<mNormal> normal = LitePal.findAll(mNormal.class);

        mOverhead tmp = new mOverhead();
        tmp.setAssignId(data.getAssignId());
        tmp.setAssignName(data.getAssignName());
        tmp.setBeginTime(data.getBeginTime());
        tmp.setEndTime(data.getEndTime());
        tmp.setColor(data.getColor());
        tmp.setStatus(data.getStatus());
        tmp.setSiteId(data.getSiteId());
        overhead.add(tmp);
        tmp.save();  //增加顶置
        for( int i = 0 ; i < normal.size() ; i++ ) {
            if ( normal.get(i).getSiteId().equals( data.getAssignId() ) ) {
                LitePal.deleteAll(mNormal.class,"assignId = ?",data.getAssignId());//删除普通
                break;
            }
        }

    }

    static void Delete(AssignmentBean data) {
        List<mOverhead> overhead = LitePal.findAll(mOverhead.class);
        List<mNormal> normal = LitePal.findAll(mNormal.class);

        mDelete tmp = new mDelete();
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
                        LitePal.deleteAll(mOverhead.class,"siteId = ?",data.getAssignId());
                        break;
                    }
                }
                break;
            }
            case 1: { //删除普通item
                for( int i = 0 ; i < normal.size() ; i++ ) {
                    if ( normal.get(i).getSiteId().equals( data.getAssignId() ) ) {
                        LitePal.deleteAll(mNormal.class,"assignId = ?",data.getAssignId());
                        break;
                    }
                }
                break;
            }
        }
    }

    static void PutNormal(AssignmentBean data) {
        List<mOverhead> overhead = LitePal.findAll(mOverhead.class);
        List<mNormal> normal = LitePal.findAll(mNormal.class);

        mNormal tmp = new mNormal();
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
                LitePal.deleteAll(mOverhead.class,"assignId = ?",data.getAssignId());//删除顶置
                break;
            }
        }

    }






}
