package org.luozhe.lzspring.service;

import org.luozhe.lzspring.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author luozhe
 * @since 2019-10-06
 */
public interface IUserService extends IService<User> {

    void saveThread(List<User> list) throws InterruptedException, Exception;
}
