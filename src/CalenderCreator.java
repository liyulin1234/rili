import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
public class CalenderCreator extends JFrame
{
Button days[]=new Button[49];
Choice Month=new Choice();
Choice Year=new Choice();
Label lmonth=new Label("MONTH");
Label lyear=new Label("Year");
Label ltext=new Label("YEAR UPTO");
Date date=new Date(); 
//����ʱ���ʽ���ڴ�����ʾ
SimpleDateFormat sdf1=new SimpleDateFormat("yyyy��MM��dd��HH:mm:ss");
Label riqi=new Label("������"+sdf1.format(date));
JButton beiwang=new JButton("����¼");
JButton rizhi=new JButton("��־");
JButton jihua=new JButton("�ƻ�");
JButton tixing=new JButton("����");
Panel p1,p2,p3,p4;
GregorianCalendar gc=new GregorianCalendar();
int totdays;
TextField textfield=new TextField(2);
public CalenderCreator()
{
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setTitle("����");
setSize(500,500);
setResizable(false);
setLocation(200,100);
riqi.setFont(new Font("����",Font.BOLD,15));
ltext.setFont(new Font("����",Font.BOLD,15));
lmonth.setFont(new Font("����",Font.BOLD,15));
lyear.setFont(new Font("����",Font.BOLD,15));
riqi.setFont(new Font("����",Font.BOLD,15));
p1=new Panel(new FlowLayout());
p2=new Panel(new GridLayout(7,7,10,10));
p3=new Panel(new FlowLayout());
p4=new Panel(new GridLayout(7,7,10,10));
//������ɫ  �ٶ���λ��
p1.setBackground(new Color(173,216,230));
p2.setBackground(new Color(255,182,193));
p4.setBackground(new Color(173,216,230));
add(p1,BorderLayout.NORTH);
add(p2);
add(p3,BorderLayout.SOUTH);
add(p4,BorderLayout.EAST);
p4.add(jihua);
p4.add(beiwang);
p4.add(rizhi);
p4.add(tixing);
p3.add(riqi);
p1.add(ltext);
p1.add(textfield);
p1.add(lmonth);
p1.add(Month);
Month.add("һ��");
Month.add("����");
Month.add("����");
Month.add("����");
Month.add("����");
Month.add("����");
Month.add("����");
Month.add("����");
Month.add("����");
Month.add("ʮ��");
Month.add("ʮһ");
Month.add("ʮ��");
Month.addItemListener(new myLis(this));
// int i1=myAction.newyear; //
// System.out.println("iiiiiiiiii is :::::::"+i1); //
textfield.addActionListener(new myAction(this)); //
p1.add(lyear);
p1.add(Year);
Year.add("2012");
Year.add("2013");
Year.add("2014");
Year.add("2015");
Year.add("2016");
Year.add("2017");
Year.add("2018");
Year.addItemListener(new myLis(this));
//������ڰ�ť��ʼ����
for(int i=0;i<49;i++)
{
days[i]=new Button("");
}
for(int c=0;c<49;c++)
{
p2.add(days[c]);
}
//��������Ϊ��ɫ
for(int i=6;i<49;i=i+7) {
	days[i].setForeground(Color.red);
}
//��������Ϊ��ɫ
for(int i=5;i<49;i=i+7) {
	days[i].setForeground(Color.green);
}
setVisible(true);
//�����������ܰ�ť�¼�����
beiwang.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		new DemoFrame();
	}
});
jihua.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		new Myplan();
	}
});
rizhi.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		new Mydaily();
	}
});
tixing.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
		new Thread(new MyRemind()).start();
	}
});
    init();//��ʼ������
}
//��ʼ������ Ŀ�Ŀ�����ʾ��ǰ����
public void init() {
	int selMonth=date.getMonth()+1;
	int selYear1=date.getYear();
	Month.select(date.getMonth());
	int a=date.getYear()+1900-2012;
	Year.select(a);
	int selYear = selYear1- 1900;
	Date d1 = new Date(selYear,selMonth,1);
	int day = d1.getDay();
	this.setVal(d1,day,selMonth-1,selYear);
}
//��������
void setYear(String mynewyear)
{
int h=Integer.parseInt(mynewyear);
for(int adder=2013;adder<=h;adder++)
{
Year.add(""+adder);
}
}
void setButtons(int myday,int mytotdays,int months,int years)
{
int count=7;
//��������һ�������������С
for(int i=0;i<7;i++) {
	days[i].setFont(new Font("����",Font.BOLD,15));
}
days[0].setLabel("����һ");
days[1].setLabel("���ڶ�");
days[2].setLabel("������");
days[3].setLabel("������");
days[4].setLabel("������");
days[5].setLabel("������");
days[6].setLabel("������");
//����ÿ���½���ǰ���м���
//����Ϊÿ���µ�һ�������
if(myday==0) {
	int blank= 7;
	int c=31-blank+2;
	int d=30-blank+2;
	int f=29-blank+2;
	int g=28-blank+2;
	  //System.out.println(months);
		if(months==0 || months==1 || months==3 || months==5 || months== 7 ||months==8 || months==10) {
			for( ;blank>1;blank--,count++) {
				days[count].setBackground(new Color(192,192,192));
				days[count].setFont(new Font("����",Font.BOLD,16));
	           days[count].setLabel(""+c);
	          c++;
			}
		}
		if(months==4 || months==6 || months==9 || months==11) {
			for( ;blank>1;blank--,count++) {
			days[count].setBackground(new Color(192,192,192));
			days[count].setFont(new Font("����",Font.BOLD,16));
			days[count].setLabel(""+d);
	        d++;
			}
		}
		if(gc.isLeapYear(years) && months==2) {
			for( ;blank>1;blank--,count++) {
				days[count].setBackground(new Color(192,192,192));
				days[count].setFont(new Font("����",Font.BOLD,16));
				days[count].setLabel(""+f);
		        f++;
				}
		}
		if(!gc.isLeapYear(years) && months==2) {
			for( ;blank>1;blank--,count++) {
				days[count].setBackground(new Color(192,192,192));
				days[count].setFont(new Font("����",Font.BOLD,16));
				days[count].setLabel(""+g);
		        g++;
				}
		}
}
//���ղ�Ϊÿ�µĵ�һ��
if (myday>0)
{
int blank= myday;
int c=31-blank+2;
int d=30-blank+2;
int f=29-blank+2;
int g=28-blank+2;
//for( ;blank>1;blank--,count++)
//{
	//days[count].setLabel("");
	//System.out.println(a);
     //System.out.println(months);
	if(months==0 || months==1 || months==3 || months==5 || months== 7 ||months==8 || months==10) {
		for( ;blank>1;blank--,count++) {
			days[count].setBackground(new Color(192,192,192));
			days[count].setFont(new Font("����",Font.BOLD,16));
            days[count].setLabel(""+c);
            c++;
		}
	}
	if(months==4 || months==6 || months==9 || months==11) {
		for( ;blank>1;blank--,count++) {
			days[count].setBackground(new Color(192,192,192));
		    days[count].setFont(new Font("����",Font.BOLD,16));
		    days[count].setLabel(""+d);
            d++;
		}
	}
	if(gc.isLeapYear(years) && months==2) {
		for( ;blank>1;blank--,count++) {
			days[count].setBackground(new Color(192,192,192));
			days[count].setFont(new Font("����",Font.BOLD,16));
			days[count].setLabel(""+f);
	        f++;
			}
	}
	if(!gc.isLeapYear(years) && months==2) {
		for( ;blank>1;blank--,count++) {
			days[count].setBackground(new Color(192,192,192));
			days[count].setFont(new Font("����",Font.BOLD,16));
			days[count].setLabel(""+g);
	        g++;
			}
	}
	
//}
}
//��ȡ��ǰ����
Date d1=new Date();
int k=d1.getDate();
//������������Ϊ��ɫ
for(int i=1;i<=mytotdays; i++,count++)
{
	days[count].setFont(new Font("����",Font.BOLD,18));
  days[count].setLabel(""+i);
if(i==k&&this.Month.getSelectedIndex()==date.getMonth()&&Integer.parseInt(this.Year.getSelectedItem())==date.getYear()+1900) {
	days[count].setBackground(new Color(0,191,255));
}
else {
	days[count].setBackground(Color.white);
}
}
//���ú����¸��µ����ڴ�1��ʼ
for(int j = 1;count < 49; j++,count++)
{
	days[count].setBackground(new Color(192,192,192));
	days[count].setFont(new Font("����",Font.BOLD,16));
    days[count].setLabel(""+j);
}
}
//�����·���������  ������2��������������
void setVal(Date date,int iday,int iselMonth,int iselYear)
{
gc.setTime(date);
if(iselMonth==0 || iselMonth==2 || iselMonth==4 || iselMonth==6 || iselMonth== 7 ||iselMonth==9 || iselMonth==11)
{
totdays=31;
setButtons(iday,totdays,iselMonth,iselYear);
}
if(iselMonth==3 || iselMonth==5 || iselMonth==8 || iselMonth==10)
{
totdays=30;
setButtons(iday,totdays,iselMonth,iselYear);
}
if(gc.isLeapYear(iselYear) && iselMonth==1)
{
totdays=29;
setButtons(iday,totdays,iselMonth,iselYear);
}
if( !gc.isLeapYear(iselYear) && iselMonth==1)
{
totdays=28;
setButtons(iday,totdays,iselMonth,iselYear);
}

}
static public void main(String args[])
{
CalenderCreator c=new CalenderCreator();
}
}
class myLis implements ItemListener
{
CalenderCreator calLis;
public myLis(CalenderCreator c)
{
calLis=c;
}
public void itemStateChanged(ItemEvent i)
{
	int selMonth=calLis.Month.getSelectedIndex();//��ȡλ��
	int selYear1=Integer.parseInt(calLis.Year.getSelectedItem());//Integer.parseInt����ת��
	int selYear = selYear1- 1900;
	//System.out.println(selMonth);
	Date d1 = new Date(selYear,selMonth,1);
	int day = d1.getDay();
	calLis.setVal(d1,day,selMonth,selYear);
}
}
//�������� 
class myAction implements ActionListener
{
CalenderCreator calAc;
int newyear;
public myAction(CalenderCreator ca)
{
calAc=ca;
}
public void actionPerformed(ActionEvent e)
{
String s=calAc.textfield.getText();
// newyear=Integer.parseInt(s);
System.out.println("Year upto::::::::"+s);
calAc.setYear(s);
TextField tf = (TextField)e.getSource();
tf.removeActionListener(this);
}
}
