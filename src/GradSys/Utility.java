package GradSys;
import java.awt.*;
import java.sql.*;
//这里封装了一些常用的类
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
	
	//连接数据库
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
	//关闭连接数据库
	static public void closeConnection(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//返回从数据库获取到的数据容器
	
	
	//方便空间的参数设置
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
