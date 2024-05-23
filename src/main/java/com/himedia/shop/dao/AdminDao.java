package com.himedia.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.himedia.shop.dto.AdminVO;
import com.himedia.shop.dto.ProductVO;
import com.himedia.shop.util.Db;
import com.himedia.shop.util.Paging;

public class AdminDao {

	private AdminDao() {}
	private static AdminDao instance = new AdminDao();
	public static AdminDao getInstance() {return instance;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public AdminVO getAdmin(String adminid) {
		
		AdminVO avo = null;
		con = Db.getConnection();
		String sql = "select * from admins where adminid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, adminid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				avo = new AdminVO();
				avo.setAdminid(rs.getString("adminid"));
				avo.setPwd(rs.getString("pwd"));
				avo.setName(rs.getString("name"));
				avo.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Db.close(con, pstmt, rs);
		}
		
		return avo;
		
	}

	public ArrayList<ProductVO> adminProductList(Paging paging, String key) {
		ArrayList<ProductVO>list = new ArrayList<ProductVO>();
		String sql = "select*from product  "
				+ " where name like concat('%', ?, '%') "
				+ " order by pseq desc limit ?  offset ?";
		con = Db.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, paging.getDisplayRow());
			pstmt.setInt(3, paging.getStartNum()-1);
			rs = pstmt.executeQuery();
			while( rs.next() ) {
				ProductVO pvo = new ProductVO();
				pvo.setPseq( rs.getInt("pseq") );
				pvo.setIndate(rs.getTimestamp("indate"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice1( rs.getInt("price1") );
				pvo.setPrice2( rs.getInt("price2") );
				pvo.setPrice3( rs.getInt("price3") );
				pvo.setImage( rs.getString("image") );
				pvo.setUseyn( rs.getString("useyn") );
				pvo.setBestyn( rs.getString("bestyn") );
				list.add(pvo);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { 	Db.close(con, pstmt, rs);  }
		return list;
	}

	public int getAllcount(String tablename, String fieldname, String key) {
		int count=0;
		con=Db.getConnection();
		String sql = "select count(*) as cnt from " + tablename
				+" where " + fieldname + " like concat('%', ?, '%')";
		// 검색어가 '부츠' concat의 결과는 %부츠% ->부츠를 포함한 상품명 검색
		// 검색어가 '' concat의 결과 %% -> 모든 상품 결과를 검색
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt("cnt");
		} catch (SQLException e) {			
			e.printStackTrace();			
		} finally { 	Db.close(con, pstmt, rs);  }
		
		return count;
	}

	public void insertProduct(ProductVO pvo) {

		String sql = "insert into product( kind, name, price1, price2, price3, content, "
				+ " image, savefilename)  values( ? , ? , ? , ? , ? , ? , ? , ? )";
		con = Db.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getKind());
		    pstmt.setString(2, pvo.getContent());
		    pstmt.setInt(3, pvo.getPrice1());
		    pstmt.setInt(4, pvo.getPrice2());
		    pstmt.setInt(5, pvo.getPrice3());
		    pstmt.setString(6, pvo.getContent());
		    pstmt.setString(7, pvo.getImage());
		    pstmt.setString(8, pvo.getSavefilename());
		    pstmt.executeUpdate();  
		} catch (SQLException e) {e.printStackTrace();
		} finally {  Db.close(con, pstmt, rs);  }
		
	}
		
	}
	








