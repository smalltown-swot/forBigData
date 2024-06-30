package cn.zzuer.demo.rest;

import lombok.Data;

@Data
public class NoFinalString {
    private static final String now = "/NOW_FOR_TEST";
    public static final String WHERE = now;
    public static final String DATABASE_TABLE = now;

}
