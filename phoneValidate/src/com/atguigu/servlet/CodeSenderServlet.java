package com.atguigu.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.utils.VerifyCodeConfig;
import com.sun.java_cup.internal.runtime.virtual_parse_stack;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class VerifiCodeServlet
 */
public class CodeSenderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CodeSenderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jedis jedis=new Jedis("192.168.153.128",6379);

        String phoneNo = request.getParameter("phone_no");
        if (phoneNo==null){
            return;
        }

        //每个手机号每天三次
        String countKey =VerifyCodeConfig.PHONE_PREFIX+phoneNo+VerifyCodeConfig.COUNT_SUFFIX;
        //获取redis中手机验证码次数
        String countStr =jedis.get(countKey);
        if (countStr==null){
            jedis.setex(countKey,VerifyCodeConfig.SECONDS_PER_DAY,"1");
        }else {
            int count = Integer.parseInt(countStr);
            if (count<3){
                jedis.incr(countKey);
            }else {
                response.getWriter().write("limit");
                jedis.close();
                return;
            }
        }

        //生成验证码
        String code = genCode(6);
        System.out.println(code);

        //2分钟有效
        //phoneno:110:code
        String codeKey =VerifyCodeConfig.PHONE_PREFIX+phoneNo+VerifyCodeConfig.CODE_SUFFIX;

        jedis.setex(codeKey,VerifyCodeConfig.CODE_TIMEOUT,code);
        response.getWriter().write("true");
        jedis.close();

    }


    private String genCode(int len) {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }

}
