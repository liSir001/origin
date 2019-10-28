package com.lisir.cn.service;

import java.util.Map;

public interface ActModelService {

    String deployTask(String bpmnModel);

    String startTask(Map<String, Object> map);
}