package com.kgc.git2018.service;

import com.github.pagehelper.PageInfo;
import com.kgc.git2018.pojo.Real;
import com.kgc.git2018.pojo.RealExample;

/**
 * @author shkstart
 * @create 2020-11-25 17:37
 */
public interface RealService {
    public PageInfo<Real> select(RealExample realExample, Integer pageNum, Integer pageSize);
}
