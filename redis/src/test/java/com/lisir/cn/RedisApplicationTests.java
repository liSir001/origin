package com.lisir.cn;

import com.lisir.cn.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class RedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * string类型
     */
    @Test
    public void testRedisForString() {
        // 插入数据
        redisTemplate.opsForValue().set("people:name", "wangwu");
        // 指定生命周期 1min
        
        redisTemplate.opsForValue().set("people:age", "24", 2, TimeUnit.MINUTES);
        // 获取数据
        System.out.println(redisTemplate.opsForValue().get("people:name"));

        User user = new User();
        user.setUsername("xiaoqiang");
        user.setAge(19);
        user.setSex("男");
        redisTemplate.opsForValue().set("u",user);

        User u = (User)redisTemplate.opsForValue().get("u");
        System.out.println("u = " + u);
    }

    /**
     * hash类型
     */
    @Test
    public void testRedisForHash() {
        // 存值
        redisTemplate.opsForHash().put("car", "price", "120000");
        // 取值
        System.out.println(redisTemplate.opsForHash().get("car", "price"));

        Map<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        redisTemplate.opsForHash().putAll("test", map);

        // 确定hashkey是否存在
        System.out.println("key是否存在 : " + redisTemplate.opsForHash().hasKey("test", "k1"));
        System.out.println(redisTemplate.opsForHash().get("test", "k2"));

        // 删除key对应的hash的hashkey及其value
        redisTemplate.opsForHash().delete("car", "price");

        // 获取key对应的hash表的大小
        System.out.println(redisTemplate.opsForHash().size("test"));

        // 获取key对应的hash表的所有键值对
        System.out.println(redisTemplate.opsForHash().entries("test"));

        // 获取key对应的hash表的所有key
        System.out.println(redisTemplate.opsForHash().keys("test"));

        // 获取key对应的hash表的所有value
        System.out.println(redisTemplate.opsForHash().values("test"));

    }

    /**
     * list类型
     */
    @Test
    public void testRedisForList() {
        // 从左边插入，即插入到列表头部
        redisTemplate.opsForList().leftPush("list", "HuaWei");
        redisTemplate.opsForList().leftPush("list", "XiaoMi");
        redisTemplate.opsForList().leftPush("list", "vivo");
        redisTemplate.opsForList().leftPush("list", "oppo");

        // 获取制定下标对应的值 index,从0开始
        System.out.println(redisTemplate.opsForList().index("list", 1));

        // 查询列表长度
        System.out.println(redisTemplate.opsForList().size("list"));

        // 指定位置重新设置指定值
        redisTemplate.opsForList().set("list", 0, "酷派");

        // 弹出最左边元素
        redisTemplate.opsForList().leftPop("list");

        // 弹出最右边元素
        redisTemplate.opsForList().rightPop("list");

        // 从左边插入一个数组
        String[] books = new String[]{"think in java", "MySQL从入门到精通"};
        redisTemplate.opsForList().leftPushAll("book:list", books);

        // 插入集合
        List<String> list = new ArrayList<>();
        list.add("西游记");
        list.add("水浒传");
        list.add("红楼梦");
        list.add("三国演义");
        redisTemplate.opsForList().leftPushAll("cctv:list", list);
    }

    /**
     * set类型
     */
    @Test
    public void testRedisForSet() {
        // 集合中添加元素
        stringRedisTemplate.opsForSet().add("sports", "basketball", "football", "badminton");

        // 从集合中删除指定元素
        stringRedisTemplate.opsForSet().remove("sports","badminton");

        // 获得集合大小
        System.out.println(stringRedisTemplate.opsForSet().size("sports"));

        // 判断集合中是否存在某元素
        System.out.println(stringRedisTemplate.opsForSet().isMember("sports", "football"));

        // 获取所有成员
        System.out.println(stringRedisTemplate.opsForSet().members("sports"));
    }

    /**
     * zset类型
     */
    @Test
    public void testRedisForZSet() {
        // 先集合中插入元素
        stringRedisTemplate.opsForZSet().add("score:list", "a1", 29.1);

        // 获得指定元素的分数
        System.out.println(stringRedisTemplate.opsForZSet().score("score:list", "a1"));

        // 从集合中删除指定元素
        stringRedisTemplate.opsForZSet().remove("score:list", "a1");

        System.out.println(stringRedisTemplate.opsForZSet().range("score:list", 0, -1));
    }
}
