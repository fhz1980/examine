package com.ffait.examine;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.ffait.util.ImageBlur;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

import com.ffait.util.DownloadFromUrl;
import com.ffait.util.ParameterOperate;


public class FaceMainFrame {
	private static String userIdParam;
	private static String userNameParam;
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	//判断是否识别成功
	public static boolean flag_jump=true;
	static FaceService fs=new FaceService();
	public JFrame frame;
	public View view;
	static JLabel cameralable;
	static JLabel photolable;
	static JTextArea name;
	//static JTextArea idnum;
	static JTextArea sex;
	static JTextArea nation;
	static JTextArea birthday;
	static JTextArea address;
	static JTextArea org;
	static JTextArea validateTime;
	static JTextArea trainProject;
	static JButton submit;
	static JButton registerPhoto;
	static JLabel message;
	static int flag = 0;
	static BufferedImage photoregister= null;
	static BufferedImage showImg= null;
	static VideoCapture camera;
	public FaceMainFrame() {
		initialize();
	}
	//完善版本1.0
	private void initialize() {
		frame = new JFrame("AI学习考核系统");
		try {
			frame.setIconImage(ImageIO.read(new File("C:\\parameter\\nicola.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setBounds(0,0, 1024, 960);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		trainProject = new JTextArea("提示：在人脸识别成功后点击最下方“考核”按钮进行考核");
		trainProject.setLineWrap(true);
		trainProject.setForeground(new Color(177,44,37));
		trainProject.setFont(new Font("黑体", Font.PLAIN, 20) );
		trainProject.setEditable(false);					//模仿JLabel 禁止编辑文字
		trainProject.setBackground(new Color(238,238,238)); //设置背景色和 窗体的背景色一样
		trainProject.setLineWrap(true);        				//激活自动换行功能 
		trainProject.setWrapStyleWord(true);  				// 激活断行不断字功能
		trainProject.setBounds(20, 70, 630, 80);		
		
		name = new JTextArea("姓名：");
		name.setFont(new Font("黑体", Font.PLAIN, 30) );
		name.setEditable(false);//模仿JLabel 禁止编辑文字
		name.setBackground(new Color(238,238,238));//设置背景色和 窗体的背景色一样
		name.setBounds(20, 130, 600, 50);
		cameralable = new JLabel("");
		cameralable.setBounds(32, 300, 960, 540);
		
		photolable = new JLabel();
		photolable.setBounds(740, 30, 252, 252);
		//提示信息栏
		message = new JLabel("请将脸部置于中央进行人脸识别",JLabel.CENTER);
		message.setFont(new Font("黑体", Font.PLAIN, 30) );
		message.setForeground(new Color(37,102,68));
		message.setBounds(210, 200, 530, 50);
		
		submit = new JButton("开始学习");
		submit.setFont(new Font("黑体",Font.PLAIN,20));
		submit.setBackground(new Color(37,102,68));
		submit.setForeground(Color.WHITE);
		submit.setBounds(200, 860, 250, 60);
		submit.setVisible(false);
		
		registerPhoto = new JButton("开始考核");
		registerPhoto.setFont(new Font("黑体",Font.PLAIN,20));
		registerPhoto.setBackground(new Color(37,102,68));
		registerPhoto.setForeground(Color.WHITE);
		registerPhoto.setBounds(600, 860, 250, 60);
		registerPhoto.setVisible(false);
		
		frame.getContentPane().add(name);
		//frame.getContentPane().add(idnum);
		frame.getContentPane().add(trainProject);
		frame.getContentPane().add(cameralable);		
		frame.getContentPane().add(photolable);
		

		frame.getContentPane().add(submit);
		frame.getContentPane().add(message);
		frame.getContentPane().add(registerPhoto);
        int x = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-frame.getWidth())/2;
        int y = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-frame.getHeight())/2;
		frame.setLocation(x, 0);		

		//添加提交按键监听
		//跳转到学习页面
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userIdParam.equals("")||userNameParam.equals("")) {
					return;
				}
				//flag_jump=false;
				//关闭原来窗口
				//frame.dispose();
				 try {
					String browser=  ParameterOperate.extract("StudySystemCMD");
					Runtime.getRuntime().exec(browser);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        });
		//跳转到考核页面
		registerPhoto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userIdParam.equals("")||userNameParam.equals("")) {
					return;
				}
				flag_jump=false;
				//关闭原来窗口
				frame.setVisible(false);
				
				if (view==null) {
					view=new View(FaceMainFrame.this,userIdParam,userNameParam);
					view.setVisible(true);
				}else {
					view.setUserId(userIdParam);
					view.setUserName(userNameParam);
					view.init();
					view.setVisible(true);
				}
			}
        });
		
		
	}
public static void start() {

	FaceMainFrame window = new FaceMainFrame();
	window.frame.setVisible(true);

	long pretime=System.currentTimeMillis();
//	VideoCapture camera = new VideoCapture(0);
	//System.out.println("111111111111111111");
	camera = new VideoCapture(0);
	//System.out.println("2222222222222222");
	if (!camera.isOpened()) {
		System.out.println("Camera Error");
	} else {
		Mat frame = new Mat();
		while (true) {
			camera.read(frame);
			BufferedImage bufferedImage = ImageBlur.gausssianBlur(frame);
            BufferedImage bi=fs.mat2BI(frame);
            //System.out.println("3333333333333");
            long currenttime=System.currentTimeMillis();
            if(currenttime-pretime>10000) {
            	new Thread(new Runnable() {
					@Override
					public void run() {
						//long a=System.currentTimeMillis();
						if(flag_jump){
							//System.out.println("44444444444444");
							if(message.getText() == null || message.getText() == "")
								message.setText("正在识别中...");
								
								String s = fs.judgeMember(bi);  
								
						
								if(null==s) {
									message.setText("没有检测到人脸");
								}else if("null".equals(s)) {
									message.setText("没有检测到人脸");
								}else if("noFace".equals(s)) {
									message.setText("没有检测到人脸");
								}else if ("noUser".equals(s)) {
									//当右上角无图时，添加图片及注册图片
//									if(photolable.getIcon() == null) {
//						                photoregister = bi;
//						                photolable.setIcon( new ImageIcon(bi.getScaledInstance(252,252,Image.SCALE_DEFAULT)));
//									}
//									submit.setVisible(true);
//									registerPhoto.setVisible(true);
									message.setText("身份验证不成功，请在入口处注册!");
								}// 验证通过
								else {
									submit.setVisible(true);
									registerPhoto.setVisible(true);
									//System.out.println(s);
									
									int f1 = s.indexOf('_');
							        int f2 = s.indexOf('_',f1+1);
							        int f3 = s.indexOf('_',f2+1);
							        int f4 = s.indexOf('_',f3+1);
							        int f5 = s.indexOf('_',f4+1);
							        
							        String userId = s.substring(0, f1);
							        String userCode = s.substring(f1+1,f2);
							        String userName = s.substring(f2+1,f3);
							        String roleId = s.substring(f3+1,f4);
							        String photoUrl = s.substring(f4+1,f5);
							        String projects = s.substring(f5+1);
							        userIdParam=userId;
							        userNameParam=userName;
							        message.setText("身份验证成功!");
//							    
							        BufferedImage photo = DownloadFromUrl.downloadBufferedImageFromUrl(ParameterOperate.extract("MainService")+photoUrl, "jpg");
							        
							        ImageIcon  i = new ImageIcon(photo);
									i.setImage(i.getImage().getScaledInstance(252,252,Image.SCALE_DEFAULT));//设置缩放
									photolable.setIcon(i);
									name.setText("姓名："+ userName);
									//idnum.setText("用户名："+userName);
									//trainProject.setText("培训项目：" + projects);

									
									try {
										//验证成功 五秒后清除信息
										Thread.sleep(5000);
										
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}finally{
										name.setText("姓名：");
										//idnum.setText("身份证后六位：");
										//trainProject.setText("培训项目：");
										message.setText("正在识别中...");
										submit.setVisible(false);
										registerPhoto.setVisible(false);
										photolable.setIcon(null);
										photoregister = null;
									}
									
									
								}
								
						}
					}
            		
            	}).start();
            	pretime=currenttime;
            }

			showImg = DownloadFromUrl.deepCopy(bi);
//			BufferedImage bufferedImage = ImageBlur.gausssianBlur(frame);
			ImageBlur.drawFace(bufferedImage,showImg.getSubimage(350,190,270,340));
            cameralable.setIcon(new ImageIcon(bufferedImage));

		}

	}
}
	
	public static void main(String[] args) {
      start();
		
	}


}