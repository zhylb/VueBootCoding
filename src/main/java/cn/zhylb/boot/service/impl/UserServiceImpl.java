package cn.zhylb.boot.service.impl;

import cn.zhylb.boot.entity.User;
import cn.zhylb.boot.mapper.UserMapper;
import cn.zhylb.boot.service.IUserService;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhylb
 * @since 2023-03-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Map<String, Object> login(User user) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, user.getUsername());
        User loginUser = this.getOne(wrapper);
        if (loginUser != null && passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
            // 说明登录成功
            Map<String, Object> map = new HashMap<>();

            String token = "user:" + UUID.randomUUID();

            map.put("token", token);

            //存入redis
            loginUser.setPassword(null);
            redisTemplate.opsForValue().set(token, loginUser, 30, TimeUnit.MINUTES);
            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> getInfoByToken(String token) {
        Object o = redisTemplate.opsForValue().get(token);
        // 在redis中查询出 token对应的值
        User user = JSON.parseObject(JSON.toJSONString(o), User.class);
        if (user != null) {
            Map<String, Object> map = new HashMap<>();

            List<String> list = this.getBaseMapper().getUserRoleNamesByUserId(user.getId());
            map.put("roles", list);
            map.put("name", user.getUsername());
            map.put("avatar", user.getAvatar());


            return map;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete(token);
    }

    @Override
    public boolean checkUserName(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        User loginUser = this.getOne(wrapper);
        return loginUser != null;
    }
}
