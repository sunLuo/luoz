package org.luozhe.lzspring.mythead;

import lombok.extern.slf4j.Slf4j;
import org.luozhe.lzspring.entity.User;
import org.luozhe.lzspring.mapper.UserMapper;
import org.luozhe.util.ApplicationContextProvider;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class MyThread implements Callable<Integer> {
    private static int count = 0;
    private List<User> list;
    private CountDownLatch begin;
    private CountDownLatch end;
    private String name;
    private final int id = count ++;

    public MyThread (List<User> list, CountDownLatch begin, CountDownLatch end,String name) {
        this.list = list;
        this.begin = begin;
        this.end = end;
        this.name=name;
    }
    public MyThread (List<User> list,String name) {
        this.list = list;
        this.name=name;
    }
    @Override
    public Integer call() throws Exception {
        try {
            ApplicationContextProvider.getBean(UserMapper.class).insertBatchI(list);
            log.info(Thread.currentThread().getName()+"已保存完待返回");
//            begin.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            end.countDown();
        }
        return id;
    }
}