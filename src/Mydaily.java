import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.*;
import javax.swing.text.BadLocationException;
//该窗口继承自JFrame. 实现了ActionListener接口
public class Mydaily extends JFrame implements ActionListener {
	JTextArea jta;//文本域
	JButton jbRead, jbWrite,jbCha;//按钮
	//jtf可以拉大小
	JTextField jtf;
	public Mydaily() {
		jta = new JTextArea();
		JScrollPane jsp=new JScrollPane(jta);
		add(jsp);
		jta.setFont(new Font("宋体",Font.BOLD,18));
		jbRead = new JButton("我的日志");
		jbRead.addActionListener(this);
		jbWrite = new JButton("保存");
		jbWrite.addActionListener(this);
		jtf=new JTextField();
		jtf.setColumns(10);
		jbCha=new JButton("查找");
		jbCha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String msg=jtf.getText();
				if(msg.equals("")) {
					JOptionPane.showMessageDialog(null, "输入不能为空");
				}
				int count=0;
				BufferedReader br=null;
				try {
					br=new BufferedReader(new FileReader(getFilePath()));  //字符流
					String str=null;
					while(null!=(str=br.readLine())) {
						
						if(str.contains(msg)) {
							int selectionStart=jta.getLineStartOffset(count);
							int selectionEnd=jta.getLineStartOffset(count);
							//System.out.println(selectionStart+"========="+selectionEnd);
							//跳转到哪里
							jta.setSelectionStart(selectionStart);
							jta.setSelectionEnd(selectionEnd);
							break;
						}
						count++;
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (BadLocationException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		
		JPanel jp = new JPanel();
		jp.add(jbRead);
		jp.add(jbWrite);
		jp.add(jtf);
		jp.add(jbCha);		
		add(jp, BorderLayout.SOUTH);
		setTitle("我的计划");// 窗口标题
		setSize(500,500);// 窗口大小
		setLocation(100,100);// 窗口居中
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点击窗口右下角的关闭时会结束程序
		setVisible(true);
	}
	// main方法
	public static void main(String[] args) {
		new Mydaily();
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
	
	// 搜索得到文件路径
	private String getFilePath() {
		filePath = "text\\mydaily.txt";
		return filePath;
	}
	// 读取文件得到文本内容
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