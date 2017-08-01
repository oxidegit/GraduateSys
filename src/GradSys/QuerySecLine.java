package GradSys;

import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//查询复试分数线
public class QuerySecLine extends JPanel {
	String[] s = { "专业代码", "专业名称", "复试线" };
	Vector columnName = new Vector();
	ResultSet rs;
	JTable jt = null;
	DefaultTableModel defaultMode;// 创建表格
	Statement sta = null;

	QuerySecLine() {
		// 构造列容器
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// 输入列名
		}

	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new QuerySecLine());
		f.setVisible(true);
	}

	// 刷新
	void refresh() {
		// 创建表格
		this.removeAll();
		defaultMode = new DefaultTableModel(getRowData(), columnName);
		jt = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jt);
		this.add(js);
	}

	// 从表中获取数据， 并从返回结果容器
	Vector getRowData() {
		Vector row = new Vector();
		Vector rowChild = null;
		Utility.createConnection();
		sta = Utility.getSta();
		// row.clear();

		String sql = "select * from school_major_info";
		try {
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				// 将一个元组的数据放到容器里面
				rowChild = new Vector();
				rowChild.add(rs.getString(1));
				rowChild.add(rs.getString(2));

				rowChild.add(rs.getDouble(6));

				row.add(rowChild);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Utility.closeConnection();// 结束连接
		return row;
	}
}
