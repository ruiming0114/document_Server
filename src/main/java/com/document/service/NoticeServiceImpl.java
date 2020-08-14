package com.document.service;

import com.document.mapper.NoticeMapper;
import com.document.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    @Override
    public Notice getNoticeById(int noticeid) {
        return noticeMapper.getNoticeById(noticeid);
    }
}
