package GradSys;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//��������ڽ���
public class MainFrame extends JFrame{
	static final int WIDTH = 700;
	static final int HEIGHT = 700;
	CardLayout card = new CardLayout();
	
	JMenuBar menuBar = new JMenuBar();//�˵���
	JMenu menu_student = new JMenu("�������");
	JMenu menu_manager = new JMenu("����Ա��¼");
	JMenu menu_firstExam = new JMenu("���Բ�ѯ");
	JMenu menu_secondExam = new JMenu("���Բ�ѯ");
	
	JMenuItem menu_login = new JMenuItem("�˺ŵ�¼");
	JMenuItem menu_enroll = new JMenuItem("���Ա���");
	JMenuItem menu_firstLine = new JMenuItem("������");
	JMenuItem menu_firstScore = new JMenuItem("���Է���");
	JMenuItem menu_secondScore = new JMenuItem("���Է���");
	JMenuItem menu_secondLine = new JMenuItem("������");
	//������
	mainPage mainpage = new mainPage();
	ManagerPanel managerPanel = new ManagerPanel();
	Login login = new Login(this, "����Ա��¼", true);//��¼����
	QuerySecLine secondLine = new QuerySecLine();
	QuerySecScore secondPanel = new QuerySecScore();
	JPanel contPanel = new JPanel();
	EnrollPane enrollPane = new EnrollPane();
	FisrtExamDialog firstExamDia = new FisrtExamDialog((JFrame)this, false);
	QueryFristScoreLine qureryPanel = new QueryFristScoreLine();//��ѯ�����ߵ�panel
	//���Դ���
	public static void main(String[] args){
		new MainFrame("�о�����Ϣ����ϵͳ");
	}
	//�����ڽ������
	MainFrame(String frameName){
		super(frameName);
		super.setJMenuBar(menuBar);
		super.setContentPane(contPanel);//�������������
		setListener();//Ϊ������ü�����
		
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
		//���ÿ�Ƭ����
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
		//ע�����
		menu_enroll.addActionListener(new ActionListener()
		{	
			public void actionPerformed(ActionEvent e) {
				enrollPane.refresh();
				contPanel.setVisible(true);
				card.show(contPanel, "enrollPane");
			}
			
		});
		//��ѯϵͳ
		menu_firstScore.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				firstExamDia.refersh();
				firstExamDia.setVisible(true);
				//firstExamDia.firstExamPanel.setVisible(true);
			}
		});
		
		//������
		menu_firstLine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(contPanel, "qureryPanel");
				contPanel.setVisible(true);
			}		
		});	
		
		//���Է���
		menu_secondScore.addActionListener(new ActionListener(){
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				secondPanel.refresh();
				card.show(contPanel, "secondPanel");
				contPanel.setVisible(true);
			}		
		});	
		
		//��ѯ���Է�����
		menu_secondLine.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				secondLine.refresh();
				card.show(contPanel, "secondLine");
				contPanel.setVisible(true);	
			}		
		});
		
		//����Ա��¼
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
