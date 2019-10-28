package com.lisir.cn.service.impl;

import com.lisir.cn.service.ActModelService;
import com.lisir.cn.service.OutputService;
import com.lisir.cn.util.ActivityUtils;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.validation.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ActModelServiceImpl implements ActModelService {

    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;

    @Override
    public String deployTask(String bpmnModel) {
        // 解析流程文件
        BpmnModel model = ActivityUtils.convertToBpmnModel(bpmnModel);
        // 校验流程文件有效性
        List<ValidationError> validate = ActivityUtils.validateBpmnModel(model);
        // 流程文件校验失败
        if (validate.size() > 0) {
            throw new RuntimeException("流程部署异常: " + validate);
        }
        // 部署BPMNModel模型文件
        Deployment deploy = repositoryService.createDeployment().name("活动流程").addBpmnModel("test3.bpmn", model).deploy();
        return "流程部署ID: " + deploy.getId();
    }

    @Override
    public String startTask(Map<String, Object> map) {
        map.put("OutputService", new OutputService());
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(map.get("id").toString()).singleResult();
        // 启动流程实例
        runtimeService.startProcessInstanceById(processDefinition.getId(), map);
        return "流程启动成功!";
    }

}
