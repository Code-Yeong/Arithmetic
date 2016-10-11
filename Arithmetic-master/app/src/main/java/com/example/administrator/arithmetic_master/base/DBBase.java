package com.example.administrator.arithmetic_master.base;

import android.content.Entity;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */
public interface DBBase <T>{
    /*
     * 数据库基类接口
     * */

    /**
     *根据用户id获取用户信息
     * @param userid 用户id
     * @return T 用户对象
     * */
    public T getUser(long userid);

    /**
     * 根据用户名获取用户信息
     * @param username 用户名
     * @return T 返回用户对象
     * */
    public T getUser(String username);

    /**
     * 根据用户名和用户密码获取用户信息
     * @param username,password 用户名,用户密码
     * @return T 返回用户对象
     * */
    public T getUser(String username,String password);

    /**
     * 用户注册，保存用户信息
     * @param username,password 用户名,用户密码
     * @return null
     * */
    public void saveUser(String username,String password);

    /**
     * 获取全部用户
     * @param
     * @return List<T> 返回用户集合
     * */
    public List<T> getUsers();

    /**
     * 获取排行榜记录集合
     * @param
     * @return List<T></T> 返回排行榜记录集合
     * */
    public List<T> rankList();

    /**
     * 根据用户名获取排行榜记录集合
     * @param
     * @return List<T></T> 返回排行榜记录集合
     * */
    public List<T> rankList(String username);

    /**
     * 保存答题信息
     * @param entity 记录保存对象
     * @return null
     * */
    public void saveData(Entity entity);

    /**
     * 根据用户名获取用户答题记录
     * @param username
     * @return List<T> 题集
     * */
    public List<T> getDatas(String username);
 }
