package com.mini.homeworks.Notification;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface NotificationService {
    //弹窗提醒
    @GET("/api/notice/getAssignments/")
    Call<NotificationBean.AssignmentsGet> getAssignments(@Header("cookie") String cookie,
                                                         @Header("token") String token);

    //邮件提醒启用状态更改
    @PUT("/api/mail/isSend/modify/")
    Call<NotificationBean.MailIsSendModify> getIsSend(
            @Header("token") String token);

    //添加时间节点
    @POST("/api/mail/noticeTime/add/")
    Call<NotificationBean.NoticeTimeAdd> getNoticeTimeAdd(@Field("noticeTime") int noticeTime,
                                                          @Header("token") String token);

    //获取全部时间节点及邮箱通知设置
    @PUT("/api/mail/noticeConfig/get/")
    Call<NotificationBean.NoticeConfigGet> getNoticeConfig(
            @Header("token") String token);

    //修改时间节点
    @PUT("/api/mail/noticeTime/{noticeTimeId}/modify/")
    Call<NotificationBean.ModifyChange> getModifyChange(@Path("noticeTimeId") String noticeTimeId,
                                                        @Field("noticeTime") int noticeTime,
                                                        @Header("token") String token);

    //改变时间节点启用状态
    @PUT("/api/mail/noticeTime/{noticeTimeId}/changeStatus/")
    Call<NotificationBean.ChangesStatusBean> getChangesStatusBean(@Path("noticeTimeId") String noticeTimeId,

                                                                  @Header("token") String token);

    //移除时间节点
    @DELETE("/api/mail/noticeTime/delete/")
    Call<NotificationBean.NoticeTimeDeleteBean> getNoticeTimeDeleteBean(@Field("noticeTimeId") String noticeTimeId,
                                                                        @Header("token") String token);
}
