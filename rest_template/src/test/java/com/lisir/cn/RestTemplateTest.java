package com.lisir.cn;

import com.alibaba.fastjson.JSON;
import com.lisir.cn.entity.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @Description: rest-template单元测试
 * @Auther: liSir
 * @Date: 2019/8/21 19:56
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RestTemplateTest {

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void testRest() {
        Company company = new Company();
        company.setId(1);
        company.setName("zhangsan");
        company.setAge(18);
        company.setAddress("上海市");
        company.setSalary(5000f);
        company.setJoinDate(new Date());
        String json = JSON.toJSONString(company);
        when(restTemplate.postForObject(anyString(), any(), any())).thenReturn(json);
    }
}
