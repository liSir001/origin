package com.lisir.cn.controller;

import com.lisir.cn.service.ActModelService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("act/model")
public class ActModelController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private ActModelService actModelService;

    // 监听工作流的输出结果
    @PostConstruct
    public void init() {
        runtimeService.addEventListener(new ActivitiEventListener() {
            @Override
            public void onEvent(ActivitiEvent event) {
                // 流程变量值
                String value = (String) runtimeService.getVariable(event.getExecutionId(), "result");

                Map<String, Object> out = (Map<String, Object>) runtimeService.getVariable(event.getExecutionId(), "out");
                // TODO 输出结果存入工作流的out变量中
                out.put("result", value);
            }

            @Override
            public boolean isFailOnException() {
                return false;
            }
        }, ActivitiEventType.PROCESS_COMPLETED);
    }

    /**
     * 流程文件部署
     *
     * @param bpmnModel
     * @return
     */
    @PostMapping("deployTask")
    public String deployTask(@RequestBody String bpmnModel) {
        return actModelService.deployTask(bpmnModel);
    }

    /**
     * 启动流程
     *
     * @param map
     * @return
     */
    @PostMapping("startTask")
    public String startTask(@RequestBody Map<String, Object> map) {
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(map.get("id").toString()).singleResult();
//        Task task = taskService.createTaskQuery().processDefinitionId(processDefinition.getId()).singleResult();
        // resourceId
//        String taskDefinitionKey = taskService.createTaskQuery().taskId(task.getId()).singleResult().getTaskDefinitionKey();
//        Execution execution = runtimeService.createExecutionQuery().processDefinitionId(processDefinition.getId()).singleResult();
//        Map<String, Object> out = (Map<String, Object>)runtimeService.getVariable(execution.getId(), "out");
//        String value = (String) runtimeService.getVariableLocal(execution.getId(), "result");
        // 创建out用于存放结果数据
        Map<String, Object> out = new HashMap<>();
        map.put("out", out);
        String message = actModelService.startTask(map);
        // 返回输出结果
        System.out.println(out);
        return message;
    }

    /**
     * 流程实例状态查询
     *
     * @param processInstanceId
     */
    @PostMapping("state/{processInstanceId}")
    public void isInstanceEnd(@PathVariable("processInstanceId") String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (null != processInstance) {
            System.out.println("流程未结束");
        } else {
            System.out.println("流程已结束");
        }
    }
}
