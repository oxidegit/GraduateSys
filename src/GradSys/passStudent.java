package GradSys;

import java.awt.BorderLayout;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//显示通过的学生
public class passStudent extends JPanel{
	String[] s = { "考号", "姓名", "政治", "数学", "英语", "专业课" };
	Vector columnName = new Vector();
	ResultSet rs;
	JTable jt = null;
	DefaultTableModel defaultMode;// 创建表格
	Statement sta = null;

	passStudent() {
		// 构造列容器
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// 输入列名
		}
		// 创建表格
		defaultMode = new DefaultTableModel(getRowData(), columnName);
		jt = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jt);
		this.setLayout(new BorderLayout());
		this.add(js);
	}
	// 从表中获取数据， 并从返回结果容器
		Vector getRowData() {
			Vector row = new Vector();
			Vector rowChild = null;
			Utility.createConnection();
			sta = Utility.getSta();
			// row.clear();

			String sql = "select * from pass_student";
			try {
				rs = sta.executeQuery(sql);
				while (rs.next()) {
					// 将一个元组的数据放到容器里面
					rowChild = new Vector();
					rowChild.add(rs.getString(1));
					rowChild.add(rs.getString(2));
					for (int i = 3; i <= 7; i++) {
						rowChild.add(rs.getDouble(i));
					}
					row.add(rowChild);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			Utility.closeConnection();// 结束连接
			return row;
		}
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new passStudent());
		f.setVisible(true);
	}
}
