package cn.zhylb.boot.mapper;

import cn.zhylb.boot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhylb
 * @since 2023-03-14
 */
public interface UserMapper extends BaseMapper<User> {


    List<String> getUserRoleNamesByUserId(Integer userId);

}
