package com.jupiter.test.impl;

import com.jupiter.test.EmpDao;

public class EmpDaoImpl2 implements EmpDao {
    @Override
    public int addEmp() {
        System.out.println("addEmp for postgres successfully");
        return 0;
    }
}
