package GradSys;

import java.awt.BorderLayout;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//��ʾͨ����ѧ��
public class passStudent extends JPanel{
	String[] s = { "����", "����", "����", "��ѧ", "Ӣ��", "רҵ��" };
	Vector columnName = new Vector();
	ResultSet rs;
	JTable jt = null;
	DefaultTableModel defaultMode;// �������
	Statement sta = null;

	passStudent() {
		// ����������
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// ��������
		}
		// �������
		defaultMode = new DefaultTableModel(getRowData(), columnName);
		jt = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jt);
		this.setLayout(new BorderLayout());
		this.add(js);
	}
	// �ӱ��л�ȡ���ݣ� ���ӷ��ؽ������
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
					// ��һ��Ԫ������ݷŵ���������
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
			Utility.closeConnection();// ��������
			return row;
		}
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new passStudent());
		f.setVisible(true);
	}
}
