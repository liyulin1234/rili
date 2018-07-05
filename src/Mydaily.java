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
//�ô��ڼ̳���JFrame. ʵ����ActionListener�ӿ�
public class Mydaily extends JFrame implements ActionListener {
	JTextArea jta;//�ı���
	JButton jbRead, jbWrite,jbCha;//��ť
	//jtf��������С
	JTextField jtf;
	public Mydaily() {
		jta = new JTextArea();
		JScrollPane jsp=new JScrollPane(jta);
		add(jsp);
		jta.setFont(new Font("����",Font.BOLD,18));
		jbRead = new JButton("�ҵ���־");
		jbRead.addActionListener(this);
		jbWrite = new JButton("����");
		jbWrite.addActionListener(this);
		jtf=new JTextField();
		jtf.setColumns(10);
		jbCha=new JButton("����");
		jbCha.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String msg=jtf.getText();
				if(msg.equals("")) {
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
				}
				int count=0;
				BufferedReader br=null;
				try {
					br=new BufferedReader(new FileReader(getFilePath()));  //�ַ���
					String str=null;
					while(null!=(str=br.readLine())) {
						
						if(str.contains(msg)) {
							int selectionStart=jta.getLineStartOffset(count);
							int selectionEnd=jta.getLineStartOffset(count);
							//System.out.println(selectionStart+"========="+selectionEnd);
							//��ת������
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
					// TODO �Զ����ɵ� catch ��
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
		setTitle("�ҵļƻ�");// ���ڱ���
		setSize(500,500);// ���ڴ�С
		setLocation(100,100);// ���ھ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ����������½ǵĹر�ʱ���������
		setVisible(true);
	}
	// main����
	public static void main(String[] args) {
		new Mydaily();
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
				JOptionPane.showMessageDialog(null, "�ȵ���ҵļƻ�");
			}
		}
	}
	// �ļ�·��
	private String filePath;
	
	// �����õ��ļ�·��
	private String getFilePath() {
		filePath = "text\\mydaily.txt";
		return filePath;
	}
	// ��ȡ�ļ��õ��ı�����
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