package svlt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qbq.utils.DBUtity;

public class AcctManager {

	Connection con = null;

	public AcctManager() {

	}

	/**
	 * 查询全部
	 * 
	 * @return
	 */
	public List<Acct> acctQueryAll() {
		List<Acct> accts = new ArrayList<Acct>();
		try {
			con = DBUtity.openConnection();
			String sql = "select * from acct";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs == null)
				return accts;
			while (rs.next()) {
				String acctNo = rs.getString("acct_no");
				String acctName = rs.getString("acct_name");
				double balance = rs.getDouble("balance");
				String acctStatus = rs.getString("acct_status");
				Acct acct = new Acct(acctNo, acctName, balance, acctStatus);
				accts.add(acct);
			}
			if (ps != null) {
				ps.close();
			}
			DBUtity.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accts;
	}

	/**
	 * 精确查询
	 * 
	 * @param acct_no
	 * @return
	 */
	public List<Acct> selectAcctByno(Acct acct) {
		List<Acct> accts = new ArrayList<Acct>();
		try {
			con = DBUtity.openConnection();
			String sql = "select * from acct";
			String cdt = " where 1=1 ";
			// acctNo条件
			if (!acct.getAcctNo().equals(""))
				cdt += " and acct_no = '" + acct.getAcctNo() + "'";
			// acctName条件
			if (!acct.getAcctName().equals(""))
				cdt += " and acct_name = '" + acct.getAcctName() + "'";
			// 余额balance
			if (acct.getBalance() > 0.00000001)
				cdt += " and balance = " + acct.getBalance();
			// 账户状态
			if (!acct.getAcctStatus().equals(""))
				cdt += " and acct_status = '" + acct.getAcctStatus() + "'";
			sql += cdt;
			System.out.println("sql语句:" + sql);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs == null)
				return accts;
			while (rs.next()) {
				String acctNo = rs.getString("acct_no");
				String acctName = rs.getString("acct_name");
				double balance = rs.getDouble("balance");
				String acctStatus = rs.getString("acct_status");
				Acct acctnum = new Acct(acctNo, acctName, balance, acctStatus);
				accts.add(acctnum);
			}
			if (ps != null) {
				ps.close();
			}
			DBUtity.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accts;

	}

	/**
	 * 
	 * @param a
	 * @return
	 */
	public List<Acct> queryHazy(Acct acct) {
		List<Acct> accts = new ArrayList<Acct>();
		try {
			con = DBUtity.openConnection();
			String sql = "select * from acct";
			String cdt = " where 1=1 ";
			// acctNo条件
			if (!acct.getAcctNo().equals(""))
				cdt += " and acct_no like '%" + acct.getAcctNo() + "%'";
			// acctName条件
			if (!acct.getAcctName().equals(""))
				cdt += " and acct_name like '%" + acct.getAcctName() + "%'";
			sql += cdt;
			System.out.println("sql语句:" + sql);
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs == null)
				return accts;
			while (rs.next()) {
				String acctNo = rs.getString("acct_no");
				String acctName = rs.getString("acct_name");
				double balance = rs.getDouble("balance");
				String acctStatus = rs.getString("acct_status");
				Acct acct_num = new Acct(acctNo, acctName, balance, acctStatus);
				accts.add(acct_num);
			}
			if (ps != null) {
				ps.close();
			}
			DBUtity.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accts;

	}

	/**
	 * 添加
	 * 
	 * @param acct
	 * @return int
	 * @throws SQLException
	 */
	public int addAcct(Acct acct) throws SQLException {

		con = DBUtity.openConnection();
		String sql = "insert into acct values (?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, acct.getAcctNo());
		ps.setString(2, acct.getAcctName());
		ps.setString(3, acct.getAcctStatus());
		ps.setString(4, "2017-06-20");
		ps.setDouble(5, acct.getBalance());
		System.out.println("sql语句:" + sql);
		System.out.println(acct.toString());
		int rs = ps.executeUpdate();
		System.out.println("sql ------------");
		if (rs != 0) {
			return rs;
		}
		if (ps != null) {
			ps.close();
		}
		DBUtity.closeConnection(con);
		return rs;
	}
	/**
	 * 删除 
	 * @param acct
	 * @return
	 * @throws SQLException
	 */
	public int addDelete(Acct acct) throws SQLException {
		con = DBUtity.openConnection();
		String sql = "delete from acct where acct_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, acct.getAcctNo());

		System.out.println("sql语句:" + sql);
		System.out.println(acct.toString());
		int rs = ps.executeUpdate();
		System.out.println("sql ------------");
		if (rs != 0) {
			return rs;
		}
		if (ps != null) {
			ps.close();
		}
		DBUtity.closeConnection(con);
		return rs;
	}
	/**
	 * 修改
	 * @param acct
	 * @return
	 * @throws SQLException
	 */
	public int updateAcct(Acct acct) throws SQLException {

		con = DBUtity.openConnection();
		String sql = "update acct set acct_name=?,acct_status=?,balance=? where acct_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, acct.getAcctName());
		ps.setString(2, acct.getAcctStatus());
		ps.setDouble(3, acct.getBalance());
		ps.setString(4, acct.getAcctNo());
		System.out.println("sql语句:" + sql);
		System.out.println(acct.toString());
		int rs = ps.executeUpdate();
		System.out.println("sql ------------");
		if (rs != 0) {
			return rs;
		}
		if (ps != null) {
			ps.close();
		}
		DBUtity.closeConnection(con);
		return rs;
	}

}
