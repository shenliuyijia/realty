package com.kgc.git2018.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.git2018.mapper.RealMapper;
import com.kgc.git2018.pojo.Real;
import com.kgc.git2018.pojo.RealExample;
import com.kgc.git2018.service.RealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shkstart
 * @create 2020-11-25 17:37
 */
@Service
public class RealServiceImpl implements RealService {
    @Resource
    RealMapper realMapper;

    public PageInfo<Real> select(RealExample realExample, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum,pageSize);
        PageHelper.orderBy("id");
        List<Real> reals = realMapper.selectByExample(realExample);
        PageInfo<Real> pageInfo = new PageInfo<>(reals);
        return pageInfo;
    }
}
