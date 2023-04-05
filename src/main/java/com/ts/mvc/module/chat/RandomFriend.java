//package com.ts.mvc.module.chat;
//import java.sql.*;
//import java.util.*;
//
//public class RandomFriend {
//
//    private static final String JDBC_URL = "";
//    private static final String DB_USER = "ts";
//    private static final String DB_PASSWORD = "123qwe!@#QWE";
//
//    public static void main(String[] args) {
//
//        // MySQL 연결
//        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
//
//            // 랜덤하게 사용자 ID 선택
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT user_id FROM user ORDER BY RAND() LIMIT 1");
//            rs.next();
//            int userId = rs.getInt(1);
//
//            // 선택한 사용자의 친구 중 랜덤하게 추천
//            PreparedStatement pstmt = conn.prepareStatement("SELECT friend_id FROM friends WHERE user_id = ? ORDER BY RAND() LIMIT 1");
//            pstmt.setInt(1, userId);
//            rs = pstmt.executeQuery();
//            rs.next();
//            int friendId = rs.getInt(1);
//
//            // 추천된 친구 정보 출력
//            pstmt = conn.prepareStatement("SELECT * FROM user WHERE user_id = ?");
//            pstmt.setInt(1, friendId);
//            rs = pstmt.executeQuery();
//            rs.next();
//            String friendName = rs.getString("name");
//            System.out.println("추천된 친구: " + friendName);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}