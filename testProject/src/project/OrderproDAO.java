package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class OrderproDAO {
	public Vector listOrderpro() {
		Vector items = new Vector();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("select o2.tradename, o2.amount, (o2.amount-o1.amount) total");
			sb.append(" from ordertable o1, orderpro o2 ");
			sb.append(" where o2.tradename=o1.tradename ");
			sb.append(" group by o2.tradename, o2.amount, o1.amount ");
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Vector row = new Vector();
				row.add(rs.getString("tradename"));
				row.add(rs.getInt("amount"));
				row.add(rs.getInt("total"));
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
	
	
	public int insertOrderpro(OrderproDTO dto) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.oraConn();
			StringBuilder sb = new StringBuilder();
			sb.append("insert into orderpro values ( ?, ?, 0 )");
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getTradename());
			pstmt.setInt(2, dto.getAmount());
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
}
