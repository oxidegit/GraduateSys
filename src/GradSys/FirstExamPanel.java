package GradSys;

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//初试成绩查询结果的显示面板
public class FirstExamPanel extends JPanel{
	String[] s = {"考号", "姓名","政治","数学", "外语", "专业课"};

	Vector columnName = new Vector();
	ResultSet rs;
	JTable jt = null;
	DefaultTableModel defaultMode;
	Statement sta = null;
	
	FirstExamPanel(){
		
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// 输入列名
		}
	}
	
	void refresh(){
		this.removeAll();
		
		defaultMode = new DefaultTableModel();
		defaultMode = new DefaultTableModel(columnName, 0);// 创建表格
		jt = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jt);
		
		this.add(js);
		
	}
	//从表中获取数据， 并从返回结果容器
	Vector getRowData(String number) throws ResultSetNullException{
		Vector row = new Vector();
		Vector rowChild = null;
		Utility.createConnection();
		sta = Utility.getSta();
		//row.clear();
		if (number != null) {
			String sql = "select * from first_exam_score where sno = '"+number+"'";
			try {
				rs = sta.executeQuery(sql);
				if (rs.next()) {
					// 将一个元组的数据放到容器里面
					rowChild = new Vector();
					rowChild.add(rs.getString(1));
					rowChild.add(rs.getString(2));
					for (int i = 3; i <= 6; i++) {
						rowChild.add(rs.getDouble(i));
					}
					row.add(rowChild);
				}
				else{
					throw new ResultSetNullException("未查到相关信息！");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Utility.closeConnection();//结束连接
		return row;
	}
	
	public static void main(String[] args){
		
		JFrame jf = new JFrame();
		FirstExamPanel panel = new FirstExamPanel();
		jf.add(panel);
		jf.setSize(400, 400);
		jf.setVisible(true);
	}
}