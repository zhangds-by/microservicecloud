package com.zhangds.dao;

import com.zhangds.entities.Dept;

import java.util.List;

/**
 * Create by zhangds
 * 2019-11-08 11:28
 **/
public interface DeptDao {

    public boolean addDept(Dept dept);

    public Dept findById(Long id);

    public List<Dept> findAll();
}
