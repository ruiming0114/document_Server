package com.document.mapper;

import com.document.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface NoticeMapper {

    Notice getNoticeById(int noticeid);
    List<Notice> getNoticeByUser(int userid);
    List<Notice> getUnreadNoticeByUser(int userid);

    void addNotice(Map<String,Object> map);
    void updateNoticeStatus(Map<String,Object> map);
    void deleteNotice(int noticeid);
    void deleteNoticeByTeam(int teamid);

}
