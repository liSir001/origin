package com.lisir.cn.dao;

import com.lisir.cn.entity.RequestLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface RequestLogRepository extends ElasticsearchRepository<RequestLog, Long> {
}