package com.example.demo.utils.common;

import org.activiti.engine.impl.cfg.IdGenerator;

import java.util.UUID;

/**
 * Created by gaozhiy on 2017/5/19 0019.
 */
public class UUIDGenerator implements IdGenerator {
    @Override
    public String getNextId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
