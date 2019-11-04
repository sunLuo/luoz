package org.luozhe.lzspring.mapper;

import org.luozhe.lzspring.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author luozhe
 * @since 2019-10-06
 */
public interface UserMapper extends BaseMapper<User> {
    void insertBatchI(List<User> list);
}
