package GradSys;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class StudentInfo {
	String sno;
	String name;
	String sex;
	String age;
	String politics;
	String isCurrent;
	String background;
	String source;
	String major;
	String type;
	
	void insertTable(Vector v) throws SQLException,NumberFormatException, InputNullException{
		sno = (String)v.elementAt(0);
		name = (String)v.elementAt(1);
		sex = (String)v.elementAt(2);
		age = (String)v.elementAt(3);
		politics = (String)v.elementAt(4);
		isCurrent = (String)v.elementAt(5);
		background = (String)v.elementAt(6);
		source = (String)v.elementAt(7);
		major = (String)v.elementAt(8);
		type = (String)v.elementAt(9);
		
		if (sno.equals("") || name.equals("") || sex.equals("") || age.equals("")
				|| politics.equals("") || isCurrent.equals("") || background.equals("") || source.equals("")
				|| major.equals("")|| type.equals("")){
			throw new InputNullException("选项不能为空");
		}
		System.out.print("no execute");
		int a = Integer.parseInt(age);
		Utility.createConnection();
		Statement sta = Utility.getSta();
		String sql = "insert into enroll_student values"
		+"('"+sno+"','"+name+"','"+sex+"','"+age+"','"+politics+"','"+isCurrent+"','"+background+"','"+source+"','"+major+"','"+type+"')";
		
		sta.executeUpdate(sql);
		Utility.closeConnection();
	}
}

