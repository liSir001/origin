package com.lisir.cn.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
/*
 * indexName索引名称 理解为数据库名 限定小写
 * type 理解为数据库的表名称
 * shards = 5 默认分区数
 * replicas = 1 每个分区默认的备份数
 */
@Document(indexName = "requestlogindex", type = "requestlog")
public class RequestLog {

    @Id  //Id注解Elasticsearch里相应于该列就是主键，查询时可以使用主键查询
    @Field(type = FieldType.Keyword)
    private Long id;

    private String orderNo;

    private String userId;

    @Field(type = FieldType.Keyword)
    private String userName;

    private String createTime;
}