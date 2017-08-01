package GradSys;

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

//���Գɼ���ѯ�������ʾ���
public class FirstExamPanel extends JPanel{
	String[] s = {"����", "����","����","��ѧ", "����", "רҵ��"};

	Vector columnName = new Vector();
	ResultSet rs;
	JTable jt = null;
	DefaultTableModel defaultMode;
	Statement sta = null;
	
	FirstExamPanel(){
		
		for (int i = 0; i < s.length; i++) {
			columnName.add(s[i]);// ��������
		}
	}
	
	void refresh(){
		this.removeAll();
		
		defaultMode = new DefaultTableModel();
		defaultMode = new DefaultTableModel(columnName, 0);// �������
		jt = new JTable(defaultMode);
		JScrollPane js = new JScrollPane(jt);
		
		this.add(js);
		
	}
	//�ӱ��л�ȡ���ݣ� ���ӷ��ؽ������
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
					// ��һ��Ԫ������ݷŵ���������
					rowChild = new Vector();
					rowChild.add(rs.getString(1));
					rowChild.add(rs.getString(2));
					for (int i = 3; i <= 6; i++) {
						rowChild.add(rs.getDouble(i));
					}
					row.add(rowChild);
				}
				else{
					throw new ResultSetNullException("δ�鵽�����Ϣ��");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Utility.closeConnection();//��������
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