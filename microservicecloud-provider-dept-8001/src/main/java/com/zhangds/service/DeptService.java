package com.zhangds.service;

import com.zhangds.entities.Dept;

import java.util.List;

/**
 * Create by zhangds
 * 2019-11-08 11:19
 **/
public interface DeptService {
    public boolean add(Dept dept);

    public Dept get(Long id);

    public List<Dept> list();

}
