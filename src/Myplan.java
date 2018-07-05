import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.*;
//该窗口继承自JFrame. 实现了ActionListener接口
public class Myplan extends JFrame implements ActionListener {
	JTextArea jta;//文本域
	JButton jbRead, jbWrite;//按钮
	public Myplan() {
		jta = new JTextArea();
		add(jta);
		jta.setFont(new Font("宋体",Font.BOLD,18));
		jbRead = new JButton("我的计划");
		jbRead.addActionListener(this);
		jbWrite = new JButton("保存");
		jbWrite.addActionListener(this);
		JPanel jp = new JPanel();
		jp.add(jbRead);
		jp.add(jbWrite);
		add(jp, BorderLayout.SOUTH);
		setTitle("我的计划");// 窗口标题
		setSize(500,500);// 窗口大小
		setLocation(100,100);// 窗口居中
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 通常添加这行代码,点击窗口右下角的关闭时会结束程序
		setVisible(true);
	}
	// main方法
	public static void main(String[] args) {
		new Myplan();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton) e.getSource();
		if (jb == jbRead) {
			getFilePath();// 搜索文件
			jta.setText(readFile(filePath));// 读取文件并显示
		} else if (jb == jbWrite) {
			if (filePath != null) {
				String str = jta.getText();
				writeFile(str, filePath);//写入文件
			}else {
				JOptionPane.showMessageDialog(null, "先点击我的计划");
			}
		}
	}
	// 文件路径
	private String filePath;
	
	//搜索得到文件路径
	private void getFilePath() {
		filePath = "text\\myplan.txt";
	}
	//读取文件得到文本内容
	private String readFile(String fp) {
		InputStream in=null;
		String str=new String();
		try {
			in=new FileInputStream(fp);
			byte[] bytes=new byte[1024];
			int len=0;
			while((len=in.read(bytes))!=-1) {
				String s=new String(bytes,0,len);
                   str=str+s;
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return str;
	}
	// 把内容写入文件
	private void writeFile(String str, String fp) {
		OutputStream out=null;
		try {
			out=new FileOutputStream(fp);
			byte[] flush=str.getBytes();
			out.write(flush, 0, flush.length);
			out.flush();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			if(null!=out)
				try {
					out.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		}
		JOptionPane.showMessageDialog(null, "保存成功");
	}
}
