package com.makotojava.learn.hellospringboot;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;


public class MysqlUtil {

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/weather";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "";

    public static void insertOrUpdateOrdelete() {
        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.prepareStatement("insert into weather_ther values(?,?,?,?,?,?)");
            stmt.setString(1,"1");
            stmt.setString(2, "2017-10-10");
            stmt.setString(3, "1");
            stmt.setString(4,"1");
            stmt.setString(5,"1");
            stmt.setInt(6,1);
            int i= stmt.executeUpdate();
            if(i>0){
                System.out.println("更新成功");
            }

            // 展开结果集数据库

            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }

    public static ArrayList<HashMap<String,Object>> query(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<HashMap<String,Object>> hashMaps = new ArrayList<>();
        try{
            // 注册 JDBC 驱动
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对...");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            // 展开结果集数据库
            while(rs.next()){
                HashMap<String,Object> hashMap = new HashMap<>();
                // 通过字段检索
                String no  = rs.getString("no");
                Date cloud_time = rs.getDate("cloud_time");
                String direction = rs.getString("direction");
                String airQuality = rs.getString("airQuality");
                String cloudState = rs.getString("cloudState");
                int temp = rs.getInt("temp");
                hashMap.put("no",no);
                hashMap.put("cloud_time",cloud_time);
                hashMap.put("direction",direction);
                hashMap.put("airQuality",airQuality);
                hashMap.put("cloudState",cloudState);
                hashMap.put("temp",temp);
                hashMaps.add(hashMap);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        return hashMaps;
    }
}
