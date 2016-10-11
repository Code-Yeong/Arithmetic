package com.example.administrator.arithmetic_master.baseimpl;

import android.content.Entity;

import com.example.administrator.arithmetic_master.base.DBBase;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */
public class DBBaseImpl implements DBBase{
    @Override
    public Object getUser(long userid) {
        return null;
    }

    @Override
    public Object getUser(String username) {
        return null;
    }

    @Override
    public Object getUser(String username, String password) {
        return null;
    }

    @Override
    public void saveUser(String username, String password) {

    }

    @Override
    public List getUsers() {
        return null;
    }

    @Override
    public List rankList() {
        return null;
    }

    @Override
    public List rankList(String username) {
        return null;
    }

    @Override
    public void saveData(Entity entity) {

    }

    @Override
    public List getDatas(String username) {
        return null;
    }
}
