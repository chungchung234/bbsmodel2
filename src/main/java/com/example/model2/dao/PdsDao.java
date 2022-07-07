package com.example.model2.dao;

import com.example.model2.db.DBClose;
import com.example.model2.db.DBConnection;
import com.example.model2.dto.PdsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.model2.db.DBConnection.getConnection;

public class PdsDao {

    private static PdsDao dao = new PdsDao();

    private PdsDao() {

    }

    public static PdsDao getInstance() {
        return dao;
    }

    public List<PdsDto> getPdsList(){
        String sql = " SELECT seq, id, title, content, filename, newfilename, readcount, downcount, regdate" +
                " from pds";

        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        List<PdsDto> list = new ArrayList<PdsDto>();

        try {
            conn = getConnection();
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                int i = 1;

                PdsDto dto = new PdsDto(
                        rs.getInt(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                        rs.getString(i++),
                rs.getInt(i++),
                        rs.getInt(i++),
                        rs.getString(i++));
                list.add(dto);

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(conn, psmt, rs);
        }
        return list;
    }

    public boolean wriitePds(PdsDto dto){

        String sql = " INSERT INTO pds( id, title, content, filename, newfilename, readcount, downcount, regdate ) VALUES (" +
                " ?,?,?,?,?, 0 ,0,new()))  ";

        Connection conn = null;
        PreparedStatement psmt = null;

        int count = 0;

        try {
            conn = DBConnection.getConnection();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, dto.getTitle());
            psmt.setString(2, dto.getContent());
            psmt.setString(3, dto.getFilename());
            psmt.setString(4, dto.getFilename());
            psmt.setString(5, dto.getNewfilename());
            count = psmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBClose.close(conn, psmt, null);
        }
        return count>0?true:false;
    }

}
