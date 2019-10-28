package com.lisir.cn.controller;

import com.alibaba.fastjson.JSON;
import com.lisir.cn.entity.Dictionary;
import com.lisir.cn.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@Api(tags = "字典接口")
@RestController
@RequestMapping("/api/dict")
@Slf4j
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 新增
     *
     * @param dictionary
     * @return
     */
    @ApiOperation(value = "新增字典", produces = APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "dictionary", value = "字典对象", required = true, paramType = "dody", dataType = "Dictionary")
    @PostMapping(path = "createDict", produces = APPLICATION_JSON_UTF8_VALUE)
    public Dictionary createDict(@RequestBody Dictionary dictionary) {
        log.info("Create dictionary start, request:{}", JSON.toJSONString(dictionary));
        Dictionary dict = dictionaryService.createDict(dictionary);
        log.info("Create dictionary end, response:{}", JSON.toJSONString(dict));
        return dict;
    }
    
    /**
     * 查询所有
     *
     * @return
     */
    @ApiOperation(value = "查询所有字典", produces = APPLICATION_JSON_UTF8_VALUE)
    @GetMapping(path = "list", produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Dictionary> list() {
        return dictionaryService.list();

    }

    /**
     * 查询字典详情
     *
     * @param dictId 字典id
     * @return
     */
    @ApiOperation(value = "查询字典详情", produces = APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "dictId", value = "主键id", required = true, paramType = "path", dataType = "Long")
    @GetMapping(path = "{dictId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public Dictionary queryDictionary(@PathVariable("dictId") Long dictId) {
        log.info("Query dictionary detail start, dictId:{}", dictId);
        Dictionary dictionary = dictionaryService.queryDictById(dictId);
        log.info("Query dictionary detail end, dictId:{}, response:{}", dictId, JSON.toJSONString(dictionary));
        return dictionary;
    }

    /**
     * 分页查询
     *
     * @return
     */
    @ApiOperation(value = "分页查询字典", produces = APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码，从0开始", defaultValue = "0", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页大小", defaultValue = "20", dataType = "Integer", paramType = "query")
    })
    @GetMapping(path = "page-query", produces = APPLICATION_JSON_UTF8_VALUE)
    public Page<Dictionary> pageQueryDictionary(@PageableDefault(value = 20, sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable) {
        return dictionaryService.pageQueryDictionary(pageable);
    }

    /**
     * 修改
     *
     * @param dictionary
     * @return
     */
    @ApiOperation(value = "更新字典", produces = APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "dictionary", value = "字典对象", required = true, paramType = "body", dataType = "Dictionary")
    @PutMapping(path = "updateDict", produces = APPLICATION_JSON_UTF8_VALUE)
    @Transactional
    public Dictionary updateDict(@RequestBody Dictionary dictionary) {
        log.info("Update Dictionary start, request:{}", JSON.toJSONString(dictionary));
        Dictionary dict = dictionaryService.updateDict(dictionary);
        log.info("Update Dictionary end, response:{}", JSON.toJSONString(dict));
        return dict;
    }

    /**
     * 删除
     *
     * @param dictId 字典id
     * @return
     */
    @ApiOperation(value = "删除字典", produces = APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(name = "dictId", value = "主键id", required = true, paramType = "path", dataType = "Long")
    @DeleteMapping(path = "{dictId}", produces = APPLICATION_JSON_UTF8_VALUE)
    public void deleteDict(@PathVariable("dictId") Long dictId) {
        log.info("Delete dictionary start, dictId:{}", dictId);
        dictionaryService.deleteById(dictId);
        log.info("Delete dictionary end, dictId:{}", dictId);
    }
}
