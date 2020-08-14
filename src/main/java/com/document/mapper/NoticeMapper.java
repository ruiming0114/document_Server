package com.document.mapper;

import com.document.pojo.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface NoticeMapper {

    Notice getNoticeById(int noticeid);
}
