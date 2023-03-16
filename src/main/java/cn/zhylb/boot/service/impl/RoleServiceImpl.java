package cn.zhylb.boot.service.impl;

import cn.zhylb.boot.entity.Role;
import cn.zhylb.boot.mapper.RoleMapper;
import cn.zhylb.boot.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
