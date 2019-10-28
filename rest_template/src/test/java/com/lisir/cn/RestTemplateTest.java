package com.lisir.cn;

import com.lisir.cn.entity.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: TODO
 * @Auther: liSir
 * @Date: 2019/8/21 19:56
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RestTemplateTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testRest() {
        Company company = this.restTemplate.getForObject("http://localhost:10003/pgsql/company/1", Company.class);
        System.out.println("company = " + company);
    }
}
