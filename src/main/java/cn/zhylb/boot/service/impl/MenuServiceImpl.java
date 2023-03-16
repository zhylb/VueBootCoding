package cn.zhylb.boot.service.impl;

import cn.zhylb.boot.entity.Menu;
import cn.zhylb.boot.mapper.MenuMapper;
import cn.zhylb.boot.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
