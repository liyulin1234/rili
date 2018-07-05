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
//设置时间格式并在窗口显示
SimpleDateFormat sdf1=new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
Label riqi=new Label("现在是"+sdf1.format(date));
JButton beiwang=new JButton("备忘录");
JButton rizhi=new JButton("日志");
JButton jihua=new JButton("计划");
JButton tixing=new JButton("提醒");
Panel p1,p2,p3,p4;
GregorianCalendar gc=new GregorianCalendar();
int totdays;
TextField textfield=new TextField(2);
public CalenderCreator()
{
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setTitle("日历");
setSize(500,500);
setResizable(false);
setLocation(200,100);
riqi.setFont(new Font("宋书",Font.BOLD,15));
ltext.setFont(new Font("宋书",Font.BOLD,15));
lmonth.setFont(new Font("宋书",Font.BOLD,15));
lyear.setFont(new Font("宋书",Font.BOLD,15));
riqi.setFont(new Font("宋书",Font.BOLD,15));
p1=new Panel(new FlowLayout());
p2=new Panel(new GridLayout(7,7,10,10));
p3=new Panel(new FlowLayout());
p4=new Panel(new GridLayout(7,7,10,10));
//设置颜色  百度其位置
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
Month.add("一月");
Month.add("二月");
Month.add("三月");
Month.add("四月");
Month.add("五月");
Month.add("六月");
Month.add("七月");
Month.add("八月");
Month.add("九月");
Month.add("十月");
Month.add("十一");
Month.add("十二");
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
//添加日期按钮初始化他
for(int i=0;i<49;i++)
{
days[i]=new Button("");
}
for(int c=0;c<49;c++)
{
p2.add(days[c]);
}
//设置周日为红色
for(int i=6;i<49;i=i+7) {
	days[i].setForeground(Color.red);
}
//设置周六为绿色
for(int i=5;i<49;i=i+7) {
	days[i].setForeground(Color.green);
}
setVisible(true);
//设置其他功能按钮事件监听
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
    init();//初始化函数
}
//初始化函数 目的可以显示当前日期
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
//设置年限
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
//设置星期一到星期五字体大小
for(int i=0;i<7;i++) {
	days[i].setFont(new Font("宋书",Font.BOLD,15));
}
days[0].setLabel("星期一");
days[1].setLabel("星期二");
days[2].setLabel("星期三");
days[3].setLabel("星期四");
days[4].setLabel("星期五");
days[5].setLabel("星期六");
days[6].setLabel("星期日");
//设置每个月界面前面有几天
//周日为每个月第一天就特殊
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
				days[count].setFont(new Font("宋体",Font.BOLD,16));
	           days[count].setLabel(""+c);
	          c++;
			}
		}
		if(months==4 || months==6 || months==9 || months==11) {
			for( ;blank>1;blank--,count++) {
			days[count].setBackground(new Color(192,192,192));
			days[count].setFont(new Font("宋体",Font.BOLD,16));
			days[count].setLabel(""+d);
	        d++;
			}
		}
		if(gc.isLeapYear(years) && months==2) {
			for( ;blank>1;blank--,count++) {
				days[count].setBackground(new Color(192,192,192));
				days[count].setFont(new Font("宋体",Font.BOLD,16));
				days[count].setLabel(""+f);
		        f++;
				}
		}
		if(!gc.isLeapYear(years) && months==2) {
			for( ;blank>1;blank--,count++) {
				days[count].setBackground(new Color(192,192,192));
				days[count].setFont(new Font("宋体",Font.BOLD,16));
				days[count].setLabel(""+g);
		        g++;
				}
		}
}
//周日不为每月的第一天
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
			days[count].setFont(new Font("宋体",Font.BOLD,16));
            days[count].setLabel(""+c);
            c++;
		}
	}
	if(months==4 || months==6 || months==9 || months==11) {
		for( ;blank>1;blank--,count++) {
			days[count].setBackground(new Color(192,192,192));
		    days[count].setFont(new Font("宋体",Font.BOLD,16));
		    days[count].setLabel(""+d);
            d++;
		}
	}
	if(gc.isLeapYear(years) && months==2) {
		for( ;blank>1;blank--,count++) {
			days[count].setBackground(new Color(192,192,192));
			days[count].setFont(new Font("宋体",Font.BOLD,16));
			days[count].setLabel(""+f);
	        f++;
			}
	}
	if(!gc.isLeapYear(years) && months==2) {
		for( ;blank>1;blank--,count++) {
			days[count].setBackground(new Color(192,192,192));
			days[count].setFont(new Font("宋体",Font.BOLD,16));
			days[count].setLabel(""+g);
	        g++;
			}
	}
	
//}
}
//获取当前的日
Date d1=new Date();
int k=d1.getDate();
//当年日期设置为蓝色
for(int i=1;i<=mytotdays; i++,count++)
{
	days[count].setFont(new Font("宋体",Font.BOLD,18));
  days[count].setLabel(""+i);
if(i==k&&this.Month.getSelectedIndex()==date.getMonth()&&Integer.parseInt(this.Year.getSelectedItem())==date.getYear()+1900) {
	days[count].setBackground(new Color(0,191,255));
}
else {
	days[count].setBackground(Color.white);
}
}
//设置后几天下个月的日期从1开始
for(int j = 1;count < 49; j++,count++)
{
	days[count].setBackground(new Color(192,192,192));
	days[count].setFont(new Font("宋体",Font.BOLD,16));
    days[count].setLabel(""+j);
}
}
//设置月份日期天数  ，闰年2月日期天数？？
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
	int selMonth=calLis.Month.getSelectedIndex();//获取位置
	int selYear1=Integer.parseInt(calLis.Year.getSelectedItem());//Integer.parseInt类型转化
	int selYear = selYear1- 1900;
	//System.out.println(selMonth);
	Date d1 = new Date(selYear,selMonth,1);
	int day = d1.getDay();
	calLis.setVal(d1,day,selMonth,selYear);
}
}
//设置年限 
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
