package com.lisir.cn.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.validation.ProcessValidator;
import org.activiti.validation.ProcessValidatorFactory;
import org.activiti.validation.ValidationError;
import java.io.IOException;
import java.util.List;

public class ActivityUtils {

    /**
     * BpmnModel流程文件校验
     *
     * @param bpmnModel
     * @return
     */
    public static List<ValidationError> validateBpmnModel(BpmnModel bpmnModel) {
        ProcessValidatorFactory processValidatorFactory = new ProcessValidatorFactory();
        ProcessValidator processValidator = processValidatorFactory.createDefaultProcessValidator();
        return processValidator.validate(bpmnModel);
    }

    /**
     * xml解析为bpmnModel
     *
     * @param bpmnJson
     * @return
     */
    public static BpmnModel convertToBpmnModel(String bpmnJson) {
        JsonNode jsonNode = null;
        try {
            jsonNode = new ObjectMapper().readTree(bpmnJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BpmnJsonConverter().convertToBpmnModel(jsonNode);
    }

    /**
     * bpmnModel转化为xml
     *
     * @param model
     * @return
     */
    public static String convertToXML(BpmnModel model) {
        BpmnXMLConverter bpmnXMLConverter = new BpmnXMLConverter();
        byte[] convertToXML = bpmnXMLConverter.convertToXML(model);
        return new String(convertToXML);
    }

}
