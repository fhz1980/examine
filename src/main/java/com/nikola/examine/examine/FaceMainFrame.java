package com.nikola.examine.examine;


import java.awt.Color;
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
import javax.swing.JButton;

import com.nikola.examine.util.DownloadFromUrl;
import com.nikola.examine.util.ImageBlur;
import com.nikola.examine.util.ParameterOperate;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
		//窗体标题
		frame = new JFrame("AI学习考核系统");
		try {
			//图标资源
			frame.setIconImage(ImageIO.read(new File("./res/img/nicola.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//窗体大小
		frame.setBounds(0,0, 1024, 960);
		//设置窗体"close"操作后，窗体"exit"退出
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//设置布局
		frame.getContentPane().setLayout(null);
		//窗体大小不可变
		frame.setResizable(false);

		//提示
		trainProject = new JTextArea("提示：在人脸识别成功后点击最下方“考核”按钮进行考核");
		//设置文本换行
		trainProject.setLineWrap(true);
		//设置字体颜色
		trainProject.setForeground(new Color(177,44,37));
		//设置字体
		trainProject.setFont(new Font("黑体", Font.PLAIN, 20) );
		trainProject.setEditable(false);					//模仿JLabel 禁止编辑文字
		trainProject.setBackground(new Color(238,238,238)); //设置背景色和 窗体的背景色一样
		trainProject.setLineWrap(true);        				//激活自动换行功能 
		trainProject.setWrapStyleWord(true);  				// 激活断行不断字功能
		//设置文本域的位置和文本域的大小
		trainProject.setBounds(20, 70, 630, 80);		

		//姓名
		name = new JTextArea("姓名：");
		//设置文字
		name.setFont(new Font("黑体", Font.PLAIN, 30) );
		name.setEditable(false);//模仿JLabel 禁止编辑文字
		name.setBackground(new Color(238,238,238));//设置背景色和 窗体的背景色一样
		//设置文本域的位置和文本域的大小
		name.setBounds(20, 130, 600, 50);
		//拍照
		cameralable = new JLabel("");
		cameralable.setBounds(32, 300, 960, 540);

		//图片展示
		photolable = new JLabel();
		photolable.setBounds(740, 30, 252, 252);
		//提示信息栏
		message = new JLabel("请将脸部置于中央进行人脸识别",JLabel.CENTER);
		message.setFont(new Font("黑体", Font.PLAIN, 30) );
		message.setForeground(new Color(37,102,68));
		message.setBounds(210, 200, 530, 50);

		//设置按钮
		submit = new JButton("开始学习");
		submit.setFont(new Font("黑体",Font.PLAIN,20));
		submit.setBackground(new Color(37,102,68));
		submit.setForeground(Color.WHITE);
		submit.setBounds(200, 860, 250, 60);
		submit.setVisible(false);

		//设置按钮
		registerPhoto = new JButton("开始考核");
		registerPhoto.setFont(new Font("黑体",Font.PLAIN,20));
		registerPhoto.setBackground(new Color(37,102,68));
		registerPhoto.setForeground(Color.WHITE);
		registerPhoto.setBounds(600, 860, 250, 60);
		registerPhoto.setVisible(false);

		//添加文本域:名字
		frame.getContentPane().add(name);
		//frame.getContentPane().add(idnum);
		//添加提示
		frame.getContentPane().add(trainProject);
		//添加拍照
		frame.getContentPane().add(cameralable);
		//添加图片展示
		frame.getContentPane().add(photolable);
		

		//添加开始学习按钮
		frame.getContentPane().add(submit);
		//添加提示信息栏
		frame.getContentPane().add(message);
		//添加开始考核按钮
		frame.getContentPane().add(registerPhoto);
		//设置窗口位置，位于屏幕居中
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

	//创建一个主窗口
	FaceMainFrame window = new FaceMainFrame();
	//设置窗口可见
	window.frame.setVisible(true);

	//获取系统时间
	long pretime=System.currentTimeMillis();
//	VideoCapture camera = new VideoCapture(0);
	//System.out.println("111111111111111111");
	//创建拍照组件
	camera = new VideoCapture(0);
	//System.out.println("2222222222222222");
	if (!camera.isOpened()) {
		System.out.println("Camera Error");
	} else {
		//用于存储图片
		Mat frame = new Mat();
		while (true) {
			//定义一个视频流
			camera.read(frame);
			//采用高斯模糊
			BufferedImage bufferedImage = ImageBlur.gausssianBlur(frame);
            BufferedImage bi=fs.mat2BI(frame);
            //System.out.println("3333333333333");
			//获取系统时间，时间单位为毫秒
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

									//获取用户信息
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
									//下载照片
							        BufferedImage photo = DownloadFromUrl.downloadBufferedImageFromUrl(ParameterOperate.extract("MainService")+photoUrl, "jpg");

									//设置用户照片
							        ImageIcon  i = new ImageIcon(photo);
									i.setImage(i.getImage().getScaledInstance(252,252,Image.SCALE_DEFAULT));//设置缩放
									photolable.setIcon(i);
									//设置用户名
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
										//设置组件不可见
										submit.setVisible(false);
										registerPhoto.setVisible(false);
										//设置按钮默认图标为空
										photolable.setIcon(null);
										photoregister = null;
									}
									
									
								}
								
						}
					}
            		
            	}).start();
            	pretime=currenttime;
            }

			//展示图片
			showImg = DownloadFromUrl.deepCopy(bi);
//			BufferedImage bufferedImage = ImageBlur.gausssianBlur(frame);
			//设置图片的位置和大小
			ImageBlur.drawFace(bufferedImage,showImg.getSubimage(350,190,270,340));
            cameralable.setIcon(new ImageIcon(bufferedImage));

		}

	}
}
	
	public static void main(String[] args) {
      start();
		
	}


}