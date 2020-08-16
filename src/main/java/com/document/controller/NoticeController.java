package com.document.controller;

import com.document.pojo.JsonResult;
import com.document.pojo.Notice;
import com.document.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @PutMapping("/readNotice")
    public JsonResult<Object> updateNoticeStatus(@RequestParam("noticeid") int noticeid){
        noticeService.updateNoticeStatus(noticeid,1);
        return new JsonResult<>("0","通知已读");
    }

    @DeleteMapping("/deleteNotice")
    public JsonResult<Object> deleteNotice(@RequestParam("noticeid") int noticeid){
        Notice notice = noticeService.getNoticeById(noticeid);
        if (notice.getType()==1&&notice.getStatus()<2){
            return new JsonResult<>("1","请同意或拒绝邀请后再删除此条通知");
        }
        noticeService.deleteNotice(noticeid);
        return new JsonResult<>("0","删除成功");
    }

    @GetMapping("/getReadNoticeByUser")
    public JsonResult<Object> getReadNoticeByUser(@RequestParam("userid") int userid){
        Map<String,Object> map = new HashMap<>();
        map.put("readnoticelist",noticeService.getReadNoticeByUser(userid));
        return new JsonResult<>(map);
    }

    @GetMapping("/getUnreadNoticeByUser")
    public JsonResult<Object> getUnreadNoticeByUser(@RequestParam("userid") int userid){
        Map<String,Object> map = new HashMap<>();
        map.put("unreadnoticelist",noticeService.getUnreadNoticeByUser(userid));
        return new JsonResult<>(map);
    }
}
