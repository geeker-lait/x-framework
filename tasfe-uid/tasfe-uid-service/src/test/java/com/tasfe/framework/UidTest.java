package com.tasfe.framework;

import com.tasfe.framework.uid.service.impls.JedisIdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:
 *
 * @author liat.zhang@gmail.com
 * @tel 15801818092
 * @create 2017-11-20 17:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:tasfe-framework-redis-configure.xml"})
public class UidTest {

    @Autowired
    private JedisIdGenerator jedisIdGenerator;

    @Test
    public void testGeneratorId() throws Exception {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 3000; i++) {
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    while (true)
                        try {
                            System.out.println(jedisIdGenerator.generatorId("aaa"));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            });
        }
        System.in.read();
    }
}