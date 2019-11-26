package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class OrderTableDAO {
	public Vector listOrderTable() {
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("select num, office, tradename, company,");
			sb.append(" sal, amount, (sal*amount) exprice ");
			sb.append(" from ordertable");
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("num"));
				row.add(rs.getString("office"));
				row.add(rs.getString("tradename"));
				row.add(rs.getString("company"));
				row.add(rs.getInt("sal"));
				row.add(rs.getInt("amount"));
				row.add(rs.getInt("exprice"));
				items.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return items;
	}
	
	public int insertOrderTable(OrderTableDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("insert into ordertable");
			sb.append(" (num, office, tradename, company,");
			sb.append(" sal, amount) values(?,?,?,?,?,?)");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getNum());
			pstmt.setString(2, dto.getOffice());
			pstmt.setString(3, dto.getTradename());
			pstmt.setString(4, dto.getCompany());
			pstmt.setInt(5, dto.getSal());
			pstmt.setInt(6, dto.getAmount());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public int updateOrderTable(OrderTableDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("update ordertable ");
			sb.append(" set office=?, tradename=?, company=?, ");
			sb.append(" sal=?, amount=? where num=?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt = conn.prepareStatement(sb.toString());	
			pstmt.setString(1, dto.getOffice());
			pstmt.setString(2, dto.getTradename());
			pstmt.setString(3, dto.getCompany());
			pstmt.setInt(4, dto.getSal());
			pstmt.setInt(5, dto.getAmount());
			pstmt.setString(6, dto.getNum());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public int deleteOrderTable(String num) {
		int result = 0;
		Connection conn=null;
		PreparedStatement pstmt = null;
		try {
			conn=DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("delete from ordertable where num=?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, num);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null) conn.close();
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	public Vector searchOrderTable(String num) {
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("select num, office, tradename, ");
			sb.append(" company, sal, amount, (sal*amount) exprice ");
			sb.append(" from ordertable where num like ?");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, "%"+num+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("num"));
				row.add(rs.getString("office"));
				row.add(rs.getString("tradename"));
				row.add(rs.getString("company"));
				row.add(rs.getInt("sal"));
				row.add(rs.getInt("amount"));
				row.add(rs.getInt("exprice"));
				items.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return items;
	}
}
