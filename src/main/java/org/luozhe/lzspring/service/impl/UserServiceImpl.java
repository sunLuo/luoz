package org.luozhe.lzspring.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.luozhe.lzspring.mythead.MyThread;
import org.luozhe.lzspring.entity.User;
import org.luozhe.lzspring.mapper.UserMapper;
import org.luozhe.lzspring.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author luozhe
 * @since 2019-10-06
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public void saveThread(List<User> list) throws Exception {
        int count = 1000;                   //一个线程处理10000条数据
        int listSize = list.size();        //数据集合大小
        int runSize = (listSize / count) + 1;  //开启的线程数
        List<User> newlist = null;       //存放每个线程的执行数据
        ExecutorService executor = Executors.newFixedThreadPool(8);
        //创建两个个计数器
        /*CountDownLatch begin = new CountDownLatch(8);
        CountDownLatch end = new CountDownLatch(runSize);*/
        List<Future<Integer>> futures = new ArrayList<Future<Integer>>();
        //循环创建线程
        for (int i = 0; i < runSize; i++) {
            if ((i + 1) == runSize) {
                int startIndex = (i * count);
                int endIndex = list.size();
                newlist = list.subList(startIndex, endIndex);
            } else {
                int startIndex = (i * count);
                int endIndex = (i + 1) * count;
                newlist = list.subList(startIndex, endIndex);
            }
            if (newlist != null&&newlist.size()>0) {
                MyThread mythead = new MyThread(newlist, "LZTestThread-"+i);
                futures.add(executor.submit(mythead));
                /*userMapper.insertBatchI(newlist);*/
            }
        }
        /*begin.countDown();
        end.await();*/
        for (Future<Integer> future : futures) {
            Integer id = future.get();
            log.info("主线程收集的到了 \t" + id + "\t线程的返回值");
        }/**/

        executor.shutdown();
    }

}
