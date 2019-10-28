package com.lisir.cn;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.lisir.cn.entity.User;
import com.lisir.cn.service.UserServiceImpl;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@RunWith(JUnit4.class)
@SpringBootTest
public class EasyExcelApplicationTest {

    private final static UserServiceImpl USER_SERVICE_IMPL = new UserServiceImpl();

    @Test
    @Ignore
    public void contextLoads(){
        // 文件输出路径
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("E:\\Temp\\test.xlsx");
            ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);
            Sheet s = new Sheet(1, 0, User.class);
            s.setSheetName("用户表");
            writer.write(USER_SERVICE_IMPL.list(), s);
            writer.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}