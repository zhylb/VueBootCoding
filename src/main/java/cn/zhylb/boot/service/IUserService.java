package cn.zhylb.boot.service;

import cn.zhylb.boot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhylb
 * @since 2023-03-14
 */
public interface IUserService extends IService<User> {

    Map<String, Object> login(User user);

    Map<String, Object> getInfoByToken(String token);

    void logout(String token);

    boolean checkUserName(String username);

}
