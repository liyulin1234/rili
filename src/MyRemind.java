import java.io.File;
import sun.audio.AudioPlayer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class MyRemind extends JFrame implements Runnable{ //�ṩ��ȫ�̻߳���
    long time;     
    String video=null;
    String msg=null;
    static FileInputStream inputStream;
    public void run(){
        try{
    //����
            String strtime=JOptionPane.showInputDialog("����ʱ�䣺hh:mm");
            String t[]=strtime.split(":");
            int hh=Integer.parseInt(t[0]);
            int mm=Integer.parseInt(t[1]);
            
            msg=JOptionPane.showInputDialog("������ʾ��Ϣ��"); 
   //����         
            System.out.println("���ѳɹ�����"+hh+"��"+":"+mm+"��"+"��ʾ��ϢΪ"+msg+"������");
            hh-=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            mm-=Calendar.getInstance().get(Calendar.MINUTE);
            if(mm<0){
                  mm+=60;
                  hh--;
            }
            if(hh<0){
                  hh+=24;
            }
            time=((hh*60+mm)*60-Calendar.getInstance().get(Calendar.SECOND))*1000; 
   //�ȴ�
            Thread.sleep(time);  
            try{
                String video="text\\��������.wav";
                play(video);
            }catch(Exception e){
                   msg+="\n��Ƶ�ļ�����ʧ�ܣ�";}

            JOptionPane.showMessageDialog(new JFrame(), strtime+"\n"+msg);
             inputStream.close();
            }catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"��������ʧ�ܣ�ԭ��δ����");
         }}
    public static void play(String soundFile) throws IOException {
       inputStream = new FileInputStream(new File(soundFile));
       //���������޷������ĸ���  �ᱨ��  �������в��Ƽ����౨���Ϊ����
        AudioPlayer.player.start(inputStream);
}
            public static void main(String[] args) {
				new Thread(new MyRemind()).start();
			}
 }

