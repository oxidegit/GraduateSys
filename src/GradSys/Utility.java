package GradSys;
import java.awt.*;
import java.sql.*;
//�����װ��һЩ���õ���
public class Utility{
	//
	static Statement sta = null;
	static Connection con = null;
	public static Connection getCon() {
		return con;
	}
	public static Statement getSta() {
		return sta;
	}
	
	//�������ݿ�
	static public Connection createConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gradsys", "root", "mysql");
			sta = con.createStatement();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	//�ر��������ݿ�
	static public void closeConnection(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//���ش����ݿ��ȡ������������
	
	
	//����ռ�Ĳ�������
	static public GridBagConstraints createConstrainAnchor(GridBagConstraints constraints, int x,int y,int w,int h,  int a){
		
		constraints.gridx=x;
	    constraints.gridy=y;
	    constraints.gridwidth=w;
	    constraints.gridheight=h;
	    constraints.anchor = a;
		
		return constraints;
	}
	static public GridBagConstraints createConstrain(GridBagConstraints constraints, int x,int y,int w,int h){
		
		constraints.gridx=x;
	    constraints.gridy=y;
	    constraints.gridwidth=w;
	    constraints.gridheight=h;
	    constraints.anchor = GridBagConstraints.CENTER;
		
		return constraints;
	}
	static public GridBagConstraints createConstrainInset(GridBagConstraints constraints, int x,int y,int w,int h, int a, Insets inset){
		
		constraints.gridx=x;
	    constraints.gridy=y;
	    constraints.gridwidth=w;
	    constraints.gridheight=h;
	    constraints.anchor = a;
	    constraints.insets = inset;	
		return constraints;
	}
}
