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
public class MyRemind extends JFrame implements Runnable{ //提供安全线程机制
    long time;     
    String video=null;
    String msg=null;
    static FileInputStream inputStream;
    public void run(){
        try{
    //设置
            String strtime=JOptionPane.showInputDialog("输入时间：hh:mm");
            String t[]=strtime.split(":");
            int hh=Integer.parseInt(t[0]);
            int mm=Integer.parseInt(t[1]);
            
            msg=JOptionPane.showInputDialog("闹钟提示信息："); 
   //计算         
            System.out.println("我已成功设置"+hh+"点"+":"+mm+"分"+"提示信息为"+msg+"的闹钟");
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
   //等待
            Thread.sleep(time);  
            try{
                String video="text\\清明雨上.wav";
                play(video);
            }catch(Exception e){
                   msg+="\n音频文件加载失败！";}

            JOptionPane.showMessageDialog(new JFrame(), strtime+"\n"+msg);
             inputStream.close();
            }catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"闹钟任务失败，原因未明！");
         }}
    public static void play(String soundFile) throws IOException {
       inputStream = new FileInputStream(new File(soundFile));
       //出现问题无法载入哪个类  会报错  在设置中不推荐的类报错改为警告
        AudioPlayer.player.start(inputStream);
}
            public static void main(String[] args) {
				new Thread(new MyRemind()).start();
			}
 }

