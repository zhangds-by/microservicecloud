package com.zhangds.service.impl;

import com.zhangds.entities.Dept;
import com.zhangds.service.DeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by zhangds
 * 2019-11-08 11:19
 **/
@Service
public class DeptServiceImpl implements DeptService {
    @Override
    public boolean add(Dept dept) {
        return false;
    }

    @Override
    public Dept get(Long id) {
        return null;
    }

    @Override
    public List<Dept> list() {
        return null;
    }
}
