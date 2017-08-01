package GradSys;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


//初试分数线查询
public class QueryFristScoreLine extends JPanel{

	String[] s = {"门类代号", "专业门类","单科（=100）","单科（>100）"};
	Vector columnName = new Vector();
	ResultSet rs;
	JTable jt = null;
	DefaultTableModel defaultMode = new DefaultTableModel();
	Statement sta = null;
	
	QueryFristScoreLine(){
		//建立表头容器
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// 输入列名
		}
		
		defaultMode = new DefaultTableModel(getRowData(), columnName);// 创建表格
		jt = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jt);
		
		//this.setVisible(false);
		this.add(js);
	}
	
	// 从表中获取数据， 并从返回结果容器
	Vector getRowData() {
		Vector row = new Vector();
		Vector rowChild = null;
		Utility.createConnection();
		sta = Utility.getSta();

		String sql = "select * from first_exam_line";
		try {
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				// 将一个元组的数据放到容器里面
				rowChild = new Vector();
				rowChild.add(rs.getString(1));
				rowChild.add(rs.getString(2));
				for (int i = 3; i <= 4; i++) {
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
		JFrame jf = new JFrame();
		QueryFristScoreLine panel = new QueryFristScoreLine();
		jf.add(panel);
		jf.setSize(600, 400);
		jf.setVisible(true);
	}
}
