package GradSys;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


//���Է����߲�ѯ
public class QueryFristScoreLine extends JPanel{

	String[] s = {"�������", "רҵ����","���ƣ�=100��","���ƣ�>100��"};
	Vector columnName = new Vector();
	ResultSet rs;
	JTable jt = null;
	DefaultTableModel defaultMode = new DefaultTableModel();
	Statement sta = null;
	
	QueryFristScoreLine(){
		//������ͷ����
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// ��������
		}
		
		defaultMode = new DefaultTableModel(getRowData(), columnName);// �������
		jt = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jt);
		
		//this.setVisible(false);
		this.add(js);
	}
	
	// �ӱ��л�ȡ���ݣ� ���ӷ��ؽ������
	Vector getRowData() {
		Vector row = new Vector();
		Vector rowChild = null;
		Utility.createConnection();
		sta = Utility.getSta();

		String sql = "select * from first_exam_line";
		try {
			rs = sta.executeQuery(sql);
			while (rs.next()) {
				// ��һ��Ԫ������ݷŵ���������
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

		Utility.closeConnection();// ��������
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
