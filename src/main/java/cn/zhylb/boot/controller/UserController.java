package cn.zhylb.boot.controller;

import cn.zhylb.boot.entity.User;
import cn.zhylb.boot.service.IUserService;
import cn.zhylb.common.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhylb
 * @since 2023-03-14
 */
@RestController
@RequestMapping("/boot/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/all")
    public Result<List<User>> getAll() {
        return Result.success(userService.list());
    }


    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody User user) {
        Map<String,Object> map = userService.login(user);
        if (map != null) {
            return Result.success(map);
        }
        return Result.fail(20003,"用户名或密码错误");
    }


    @GetMapping("/info")
    public Result<Map<String, Object>> info(@RequestParam("token") String token) {
        Map<String, Object> map = userService.getInfoByToken(token);
        if (map != null) {
            return Result.success(map);
        }
        return Result.fail(20004,"登录异常，请重新登录");

    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token) {
        userService.logout(token);
        return Result.success("注销成功");
    }

    @GetMapping("/list")
    public Result<Map<String, Object>> getUserByCondition(
            @RequestParam(value = "username" ,required = false) String username,
            @RequestParam(value = "phone" ,required = false) String phone,
            @RequestParam("pageNo") Long pageNo,
            @RequestParam("pageSize") Long pageSize
    ) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(username), User::getUsername, username);
        wrapper.like(StringUtils.hasLength(phone), User::getPhone, phone);
        wrapper.orderByDesc(User::getId);

        Page<User> page = new Page<>(pageNo, pageSize);

        userService.page(page, wrapper);

        Map<String, Object> map = new HashMap<>();

        map.put("total", page.getTotal());
        map.put("rows", page.getRecords());
        return Result.success(map);
    }

    @PostMapping
    public Result<?> addUser(@RequestBody User user) {
        // 应该先检测用户名和密码是否存在
        boolean con = userService.checkUserName(user.getUsername());
        if (con) {
            // 存在的话，不应该创建成功
            return Result.fail("用户名已经存在");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 不存在的话，可以保存
        userService.save(user);
        return Result.success("添加用户成功");
    }

    @PutMapping
    public Result<?> update(@RequestBody User user){
        userService.updateById(user);
        return Result.success("修改用户成功");
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteUserById(@PathVariable("id") Integer id) {
        userService.removeById(id);
        return Result.success("删除成功了");
    }

}
