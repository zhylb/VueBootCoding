package cn.zhylb.boot.service.impl;

import cn.zhylb.boot.entity.UserRole;
import cn.zhylb.boot.mapper.UserRoleMapper;
import cn.zhylb.boot.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhylb
 * @since 2023-03-14
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
