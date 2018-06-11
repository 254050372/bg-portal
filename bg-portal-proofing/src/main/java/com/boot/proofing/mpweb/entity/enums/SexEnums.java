package com.boot.proofing.mpweb.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @description
 * @autor xbwu on 2018/5/25.
 */
public enum SexEnums implements IEnum {
    MAN(1, "男"),WOMAN(2, "女"),SECRET(3,"保密");

    private int value;
    private String desc;

    SexEnums(final int value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc(){
        return this.desc;
    }
}
