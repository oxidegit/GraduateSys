package GradSys;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//设计主窗口界面
public class MainFrame extends JFrame{
	static final int WIDTH = 700;
	static final int HEIGHT = 700;
	CardLayout card = new CardLayout();
	
	JMenuBar menuBar = new JMenuBar();//菜单条
	JMenu menu_student = new JMenu("考生入口");
	JMenu menu_manager = new JMenu("管理员登录");
	JMenu menu_firstExam = new JMenu("初试查询");
	JMenu menu_secondExam = new JMenu("复试查询");
	
	JMenuItem menu_login = new JMenuItem("账号登录");
	JMenuItem menu_enroll = new JMenuItem("考试报名");
	JMenuItem menu_firstLine = new JMenuItem("国家线");
	JMenuItem menu_firstScore = new JMenuItem("初试分数");
	JMenuItem menu_secondScore = new JMenuItem("复试分数");
	JMenuItem menu_secondLine = new JMenuItem("复试线");
	//面板组件
	mainPage mainpage = new mainPage();
	ManagerPanel managerPanel = new ManagerPanel();
	Login login = new Login(this, "管理员登录", true);//登录窗口
	QuerySecLine secondLine = new QuerySecLine();
	QuerySecScore secondPanel = new QuerySecScore();
	JPanel contPanel = new JPanel();
	EnrollPane enrollPane = new EnrollPane();
	FisrtExamDialog firstExamDia = new FisrtExamDialog((JFrame)this, false);
	QueryFristScoreLine qureryPanel = new QueryFristScoreLine();//查询国家线的panel
	//测试窗口
	public static void main(String[] args){
		new MainFrame("研究生信息管理系统");
	}
	//主窗口界面设计
	MainFrame(String frameName){
		super(frameName);
		super.setJMenuBar(menuBar);
		super.setContentPane(contPanel);//设置主内容面板
		setListener();//为组件设置监听器
		
		menu_firstExam.add(menu_firstLine);
		menu_firstExam.add(menu_firstScore);
		menu_secondExam.add(menu_secondLine);
		menu_secondExam.add(menu_secondScore);
		menu_student.add(menu_enroll);
		menu_student.add(menu_firstExam);
		menu_student.add(menu_secondExam);
		menu_manager.add(menu_login);

		this.menuBar.add(menu_student);
		this.menuBar.add(menu_manager);
		//设置卡片布局
		this.setLayout(card);
		contPanel.add(mainpage,"mainpage");
		contPanel.add("managerPanel", managerPanel);
		contPanel.add("enrollPane", enrollPane);
		contPanel.add("qureryPanel",qureryPanel);
		contPanel.add("secondPanel", secondPanel);
		contPanel.add("secondLine", secondLine);
		//contPanel.setVisible(false);
		
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(450, 50);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	void setListener(){
		//注册界面
		menu_enroll.addActionListener(new ActionListener()
		{	
			public void actionPerformed(ActionEvent e) {
				enrollPane.refresh();
				contPanel.setVisible(true);
				card.show(contPanel, "enrollPane");
			}
			
		});
		//查询系统
		menu_firstScore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				firstExamDia.refersh();
				firstExamDia.setVisible(true);
				//firstExamDia.firstExamPanel.setVisible(true);
			}
		});
		
		//国家线
		menu_firstLine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(contPanel, "qureryPanel");
				contPanel.setVisible(true);
			}		
		});	
		
		//复试分数
		menu_secondScore.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				secondPanel.refresh();
				card.show(contPanel, "secondPanel");
				contPanel.setVisible(true);
			}		
		});	
		
		//查询复试分数线
		menu_secondLine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				secondLine.refresh();
				card.show(contPanel, "secondLine");
				contPanel.setVisible(true);	
			}		
		});
		
		//管理员登录
		menu_login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				login.refersh();
				login.setVisible(true);
				if (login.getState()==true){
					System.out.print("ceshi");
					card.show(contPanel, "managerPanel");
					contPanel.setVisible(true);
					System.out.print("mainframes");
				}
			}		
		});
	}
}
