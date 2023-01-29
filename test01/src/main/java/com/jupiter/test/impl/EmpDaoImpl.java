package com.jupiter.test.impl;

import com.jupiter.test.EmpDao;

public class EmpDaoImpl implements EmpDao {
    @Override
    public int addEmp() {
        System.out.println("addEmp succeed");
        return 0;
    }
}
