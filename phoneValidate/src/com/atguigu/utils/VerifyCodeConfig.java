package com.atguigu.utils;

public interface VerifyCodeConfig {
	
    public static String PHONE_PREFIX = "phoneno:";  //前缀
	
    public static String CODE_SUFFIX = ":code";    //验证码key后缀 
    
    public static String COUNT_SUFFIX = ":count";    //计数器key后缀
    
    public static int CODE_LEN = 6;   //随机码长度
    
    public static int CODE_TIMEOUT = 120;//随机码有效时间

    public static int COUNT_TIMES_1DAY = 3;  //单日最多发送次数
    
    public static int SECONDS_PER_DAY = 60*60*24; //单日秒数
    
    public static String HOST = "192.168.67.140"; //主机地址
    
    public static int PORT = 6379; //端口号
}

