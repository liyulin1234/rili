import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
//�ô��ڼ̳���JFrame. ʵ����ActionListener�ӿ�
public class DemoFrame extends JFrame implements ActionListener {
	JTextArea jta;//�ı���
	JButton jbRead,jbWrite;
	public DemoFrame() {
		jta = new JTextArea();
		JScrollPane jsp=new JScrollPane(jta);
		add(jsp);
		jsp.setForeground(Color.black);
		jta.setFont(new Font("����",Font.BOLD,18));
	    jbRead = new JButton("�ҵı���¼");
		JButton tixing=new JButton("����");
		jbRead.addActionListener(this);
		jbWrite = new JButton("����");
		jbWrite.addActionListener(this);
		JPanel jp = new JPanel();
		jp.add(jbRead);
		jp.add(jbWrite);
		jp.add(tixing);
		add(jp, BorderLayout.SOUTH);
		setTitle("�ҵı���¼");// ���ڱ���
		setSize(500,500);// ���ڴ�С
		setLocation(100,100);// ���ھ���
		Date date=new Date(); 
		//����ʱ���ʽ���ڴ�����ʾ
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy��MM��dd�� HH:mm:ss");
		Label riqi=new Label("�����Ǳ���ʱ��:"+sdf1.format(date));
		Panel p1=new Panel(new FlowLayout());
		p1.setBackground(Color.green);
		p1.setFont(new Font("����",Font.BOLD,18));
		add(p1,BorderLayout.NORTH);
		p1.add(riqi);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ͨ��������д���,����������½ǵĹر�ʱ���������
		setVisible(true);
		tixing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new MyRemind()).start();
			}
		});
	}
	// main����
	public static void main(String[] args) {
		new DemoFrame();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton) e.getSource();
		if (jb == jbRead) {
			getFilePath();// �����ļ�
			jta.setText(readFile(filePath));// ��ȡ�ļ�����ʾ
		} else if (jb == jbWrite) {
			if (filePath != null) {
				String str = jta.getText();
				writeFile(str, filePath);//д���ļ�
			}else {
				JOptionPane.showMessageDialog(null, "�ȵ���ҵı���¼");
			}
		}
		
	}
	// �ļ�·��
	private String filePath;
	
	//  �����õ��ļ�·��
	private void getFilePath() {
		filePath = "text\\demo.txt";
	}
	//��ȡ�ļ��õ��ı�����
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
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return str;
	}
	// ������д���ļ�
	private void writeFile(String str, String fp) {
		OutputStream out=null;
		try {
			out=new FileOutputStream(fp);
			byte[] flush=str.getBytes();
			out.write(flush, 0, flush.length);
			out.flush();
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}finally {
			if(null!=out)
				try {
					out.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
		}
		JOptionPane.showMessageDialog(null, "����ɹ�");
	}
}


