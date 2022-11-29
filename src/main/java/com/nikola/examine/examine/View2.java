package com.nikola.examine.examine;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.nikola.examine.util.ParameterOperate;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class View2 extends JFrame {
    JLabel lblNewLabel_12;
    JLabel lblNewLabel_question;
    JLabel lblNewLabel_1;
    JLabel lblNewLabel_2;
    JButton lblNewLabel_A;
    JButton lblNewLabel_B;
    JButton lblNewLabel_C;
    JButton lblNewLabel_D;
    JButton btnNewButton_1;
    JButton btnNewButton_2;
    JButton btnNewButton_3;
    JButton btnNewButton_4;
    JButton btnNewButton_5;
    JButton btnNewButton_6;
    JButton btnNewButton_7;
    JButton btnNewButton_8;
    JButton btnNewButton_9;
    JButton btnNewButton_10;
    FaceMainFrame fframe;
    private String userId;
    private String userName;
    private JPanel contentPane;
    // 参数
    private String examId = "";
    // Key:Value questionSort:answer
    private Map<String, String> answerMap = new HashMap<String, String>();
    private Map<String, String> questionIdMap = new HashMap<String, String>();

    private int flag_start = 0;
    // 题目序号
    private int sort = 0;
    // 倒计时
    private int time;
    private Timer timer = new Timer();
    // 当前选中的选项
    private String selectString = "";

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    //设置view2窗体
                    View2 frame = new View2(new FaceMainFrame(), "1047", "007");
                    //窗体可见
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public View2(FaceMainFrame ffame,String userId, String userName) {
        this.fframe=ffame;
        this.userId = userId;
        this.userName = userName;
        //设置窗体
        setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1024, 768);
        setResizable(false);
        //面板容器
        contentPane = new JPanel();
        contentPane.setForeground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        //创建panel_1面板容器，并设置panel_1面板容器
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(3,80,144));
        panel_1.setBounds(0, 0, 1023, 66);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        //设置文本域
        lblNewLabel_1 = new JLabel(ParameterOperate.extract("CurrentProName"));
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 25));
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setBounds(10, 0, 150, 60);
        panel_1.add(lblNewLabel_1);

        lblNewLabel_12 = new JLabel("考生:"+this.userName,JLabel.CENTER);
        lblNewLabel_12.setFont(new Font("宋体", Font.PLAIN, 25));
        lblNewLabel_12.setForeground(Color.WHITE);
        lblNewLabel_12.setBounds(210, 0, 358, 60);
        panel_1.add(lblNewLabel_12);

        lblNewLabel_2 = new JLabel("未开始");
        lblNewLabel_2.setForeground(Color.white);
        lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(625, 2, 150, 60);
        panel_1.add(lblNewLabel_2);


        //创建按钮，并设置按钮
        JButton btnNewButton_start = new JButton("开始");
        btnNewButton_start.setBackground(new Color(241,81,37));

        btnNewButton_start.setForeground(Color.white);
        btnNewButton_start.setBounds(785, 10, 100, 40);
        panel_1.add(btnNewButton_start);
        btnNewButton_start.setFont(new Font("宋体", Font.PLAIN, 25));

        JButton btnNewButton_submit = new JButton("提交");
        btnNewButton_submit.setBackground(new Color(241,81,37));
        btnNewButton_submit.setForeground(Color.white);
        btnNewButton_submit.setBounds(900, 10, 100, 40);
        panel_1.add(btnNewButton_submit);
        btnNewButton_submit.setFont(new Font("宋体", Font.PLAIN, 25));

        //创建问题的面板容器
        JPanel panel_question = new JPanel();
        panel_question.setBackground(Color.WHITE);
        panel_question.setBounds(0, 65, 1010, 196);
        contentPane.add(panel_question);
        panel_question.setLayout(null);

        //创建问题文本域，并设置问题文本域
        lblNewLabel_question = new JLabel("");
        lblNewLabel_question.setFont(new Font("宋体", Font.PLAIN, 30));
        lblNewLabel_question.setVerticalAlignment(JLabel.BOTTOM);
        lblNewLabel_question.setBounds(0, 0, 1010, 196);
        lblNewLabel_question.setVerticalTextPosition(JLabel.BOTTOM);
        panel_question.add(lblNewLabel_question);

        //创建回答的面板容器
        JPanel panel_answer = new JPanel();

        //设置回答的面板容器
        panel_answer.setBackground(Color.WHITE);
        panel_answer.setBounds(0, 262, 1010, 469);
        contentPane.add(panel_answer);
        panel_answer.setLayout(null);

        //创建回答文本域，并设置回答文本域
        JLabel lblNewLabel_answer = new JLabel("");
        lblNewLabel_answer.setVerticalAlignment(JLabel.TOP);
        lblNewLabel_answer.setFont(new Font("宋体", Font.PLAIN, 30));
        lblNewLabel_answer.setBounds(0, 0, 1010, 258);
        panel_answer.add(lblNewLabel_answer);

        //获取屏幕大小，并设置居中
        int x = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-getWidth())/2;
        int y = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-getHeight())/2;
        setLocation(x, 0);

        //创建panel_3面板容器，并设置panel_3面板容器
        JPanel panel_3 = new JPanel();
        panel_3.setBounds(0, 257, 1010, 222);
        panel_answer.add(panel_3);
        panel_3.setBackground(new Color(180,180,180));
        panel_3.setLayout(null);

        //创建按钮，并设置按钮
        JButton btnNewButton_next = new JButton("下一题");
        btnNewButton_next.setFont(new Font("宋体", Font.PLAIN, 30));
        btnNewButton_next.setBounds(771, 149, 161, 50);

        panel_3.add(btnNewButton_next);

        btnNewButton_1 = new JButton("1");
        btnNewButton_1.setBackground(Color.LIGHT_GRAY);
        btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 30));
        btnNewButton_1.setBounds(10, 134, 65, 65);
        panel_3.add(btnNewButton_1);

        btnNewButton_2 = new JButton("2");
        btnNewButton_2.setBackground(Color.LIGHT_GRAY);
        btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 30));
        btnNewButton_2.setBounds(85, 134, 65, 65);
        panel_3.add(btnNewButton_2);

        btnNewButton_3 = new JButton("3");
        btnNewButton_3.setBackground(Color.LIGHT_GRAY);
        btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 30));
        btnNewButton_3.setBounds(160, 134, 65, 65);
        panel_3.add(btnNewButton_3);

        btnNewButton_4 = new JButton("4");
        btnNewButton_4.setBackground(Color.LIGHT_GRAY);
        btnNewButton_4.setFont(new Font("宋体", Font.PLAIN, 30));
        btnNewButton_4.setBounds(235, 134, 65, 65);
        panel_3.add(btnNewButton_4);

        btnNewButton_5 = new JButton("5");
        btnNewButton_5.setBackground(Color.LIGHT_GRAY);
        btnNewButton_5.setFont(new Font("宋体", Font.PLAIN, 30));
        btnNewButton_5.setBounds(310, 134, 65, 65);
        panel_3.add(btnNewButton_5);

        btnNewButton_6 = new JButton("6");
        btnNewButton_6.setBackground(Color.LIGHT_GRAY);
        btnNewButton_6.setFont(new Font("宋体", Font.PLAIN, 30));
        btnNewButton_6.setBounds(385, 134, 65, 65);
        panel_3.add(btnNewButton_6);

        btnNewButton_7 = new JButton("7");
        btnNewButton_7.setBackground(Color.LIGHT_GRAY);
        btnNewButton_7.setFont(new Font("宋体", Font.PLAIN, 30));
        btnNewButton_7.setBounds(460, 134, 65, 65);
        panel_3.add(btnNewButton_7);

        btnNewButton_8 = new JButton("8");
        btnNewButton_8.setBackground(Color.LIGHT_GRAY);
        btnNewButton_8.setFont(new Font("宋体", Font.PLAIN, 30));
        btnNewButton_8.setBounds(534, 134, 65, 65);
        panel_3.add(btnNewButton_8);

        btnNewButton_9 = new JButton("9");
        btnNewButton_9.setBackground(Color.LIGHT_GRAY);
        btnNewButton_9.setFont(new Font("宋体", Font.PLAIN, 30));
        btnNewButton_9.setBounds(609, 137, 65, 65);
        panel_3.add(btnNewButton_9);

        btnNewButton_10 = new JButton("10");
        btnNewButton_10.setBackground(Color.LIGHT_GRAY);
        btnNewButton_10.setFont(new Font("宋体", Font.PLAIN, 30));
        btnNewButton_10.setBounds(684, 137, 65, 65);
        panel_3.add(btnNewButton_10);



        JButton btnNewButton_previous = new JButton("上一题");
        btnNewButton_previous.setFont(new Font("宋体", Font.PLAIN, 30));

        btnNewButton_previous.setBounds(771, 35, 161, 50);
        panel_3.add(btnNewButton_previous);

        lblNewLabel_A = new JButton("A");
        lblNewLabel_A.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_A.setOpaque(true);
        lblNewLabel_A.setFont(new Font("宋体", Font.PLAIN, 60));
        lblNewLabel_A.setBounds(10, 24, 120, 100);
        panel_3.add(lblNewLabel_A);

        lblNewLabel_B = new JButton("B");
        lblNewLabel_B.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_B.setOpaque(true);
        lblNewLabel_B.setFont(new Font("宋体", Font.PLAIN, 60));
        lblNewLabel_B.setBounds(160, 24, 120, 100);
        panel_3.add(lblNewLabel_B);

        lblNewLabel_C = new JButton("C");
        lblNewLabel_C.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_C.setOpaque(true);
        lblNewLabel_C.setFont(new Font("宋体", Font.PLAIN, 60));
        lblNewLabel_C.setBounds(310, 24, 120, 100);
        panel_3.add(lblNewLabel_C);

        lblNewLabel_D = new JButton("D");
        lblNewLabel_D.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_D.setOpaque(true);
        lblNewLabel_D.setFont(new Font("宋体", Font.PLAIN, 60));
        lblNewLabel_D.setBounds(460, 24, 120, 100);
        panel_3.add(lblNewLabel_D);

        registerListeners(lblNewLabel_1, lblNewLabel_2, lblNewLabel_question, lblNewLabel_answer, btnNewButton_next,
                btnNewButton_start, btnNewButton_submit, btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4,
                btnNewButton_5, btnNewButton_6, btnNewButton_7, btnNewButton_8, btnNewButton_9, btnNewButton_10,
                btnNewButton_previous, lblNewLabel_A, lblNewLabel_B, lblNewLabel_C, lblNewLabel_D);

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void init() {
        lblNewLabel_12.setText("考生:"+userName);
        lblNewLabel_question.setText("");
        flag_start = 0;
        lblNewLabel_1.setText(ParameterOperate.extract("currentproname"));
        lblNewLabel_2.setText("剩时：");
        lblNewLabel_A.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_B.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_C.setBackground(Color.LIGHT_GRAY);
        lblNewLabel_D.setBackground(Color.LIGHT_GRAY);
        btnNewButton_1.setBackground(Color.LIGHT_GRAY);
        btnNewButton_2.setBackground(Color.LIGHT_GRAY);
        btnNewButton_3.setBackground(Color.LIGHT_GRAY);
        btnNewButton_4.setBackground(Color.LIGHT_GRAY);
        btnNewButton_5.setBackground(Color.LIGHT_GRAY);
        btnNewButton_6.setBackground(Color.LIGHT_GRAY);
        btnNewButton_7.setBackground(Color.LIGHT_GRAY);
        btnNewButton_8.setBackground(Color.LIGHT_GRAY);
        btnNewButton_9.setBackground(Color.LIGHT_GRAY);
        btnNewButton_10.setBackground(Color.LIGHT_GRAY);
    }

    public void registerListeners(JLabel lblNewLabel_1, JLabel lblNewLabel_2, JLabel lblNewLabel_question,
                                  JLabel lblNewLabel_answer, JButton btnNewButton_next, JButton btnNewButton_start,
                                  JButton btnNewButton_submit, JButton btnNewButton_1, JButton btnNewButton_2, JButton btnNewButton_3,
                                  JButton btnNewButton_4, JButton btnNewButton_5, JButton btnNewButton_6, JButton btnNewButton_7,
                                  JButton btnNewButton_8, JButton btnNewButton_9, JButton btnNewButton_10, JButton btnNewButton_previous,
                                  JButton lblNewLabel_A, JButton lblNewLabel_B, JButton lblNewLabel_C, JButton lblNewLabel_D) {

        // 开始答题事件
        StartListener startListener = new StartListener(lblNewLabel_1, lblNewLabel_2, lblNewLabel_answer,
                lblNewLabel_question, btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4, btnNewButton_5,
                btnNewButton_6, btnNewButton_7, btnNewButton_8, btnNewButton_9, btnNewButton_10,btnNewButton_submit);
        btnNewButton_start.addActionListener(startListener);

        // 跳转事件
        AnswerQuestionListener answerQuestionListener = new AnswerQuestionListener(lblNewLabel_answer,
                lblNewLabel_question, btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4, btnNewButton_5,
                btnNewButton_6, btnNewButton_7, btnNewButton_8, btnNewButton_9, btnNewButton_10, lblNewLabel_A,
                lblNewLabel_B, lblNewLabel_C, lblNewLabel_D,btnNewButton_submit);
        btnNewButton_1.addActionListener(answerQuestionListener);
        btnNewButton_2.addActionListener(answerQuestionListener);
        btnNewButton_3.addActionListener(answerQuestionListener);
        btnNewButton_4.addActionListener(answerQuestionListener);
        btnNewButton_5.addActionListener(answerQuestionListener);
        btnNewButton_6.addActionListener(answerQuestionListener);
        btnNewButton_7.addActionListener(answerQuestionListener);
        btnNewButton_8.addActionListener(answerQuestionListener);
        btnNewButton_9.addActionListener(answerQuestionListener);
        btnNewButton_10.addActionListener(answerQuestionListener);
        btnNewButton_next.addActionListener(answerQuestionListener);
        btnNewButton_previous.addActionListener(answerQuestionListener);

        // 提交事件
        SubmitListener submitListener = new SubmitListener(lblNewLabel_1, lblNewLabel_2, lblNewLabel_answer,
                lblNewLabel_question, lblNewLabel_A, lblNewLabel_B, lblNewLabel_C, lblNewLabel_D);
        btnNewButton_submit.addActionListener(submitListener);

        SelectJLabelListener selectJLabelListener = new SelectJLabelListener(lblNewLabel_A, lblNewLabel_B,
                lblNewLabel_C, lblNewLabel_D);
        lblNewLabel_A.addActionListener(selectJLabelListener);
        lblNewLabel_B.addActionListener(selectJLabelListener);
        lblNewLabel_C.addActionListener(selectJLabelListener);
        lblNewLabel_D.addActionListener(selectJLabelListener);
    }

    // 选择事件
    class SelectJLabelListener implements ActionListener {
        private JButton lblNewLabel_A, lblNewLabel_B, lblNewLabel_C, lblNewLabel_D;

        public SelectJLabelListener(JButton lblNewLabel_A, JButton lblNewLabel_B, JButton lblNewLabel_C,
                                    JButton lblNewLabel_D) {
            super();
            this.lblNewLabel_A = lblNewLabel_A;
            this.lblNewLabel_B = lblNewLabel_B;
            this.lblNewLabel_C = lblNewLabel_C;
            this.lblNewLabel_D = lblNewLabel_D;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (flag_start != 1) {
                JOptionPane.showMessageDialog(null, "先点击开始考试！");
                return;
            }
            String str = ((JButton) e.getSource()).getText();
            if (str.equals("A")) {
                lblNewLabel_A.setBackground(new Color(37,102,68));
                lblNewLabel_B.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_C.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_D.setBackground(Color.LIGHT_GRAY);
                selectString = "A";
            } else if (str.equals("B")) {
                lblNewLabel_A.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_B.setBackground(new Color(37,102,68));
                lblNewLabel_C.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_D.setBackground(Color.LIGHT_GRAY);
                selectString = "B";
            } else if (str.equals("C")) {
                lblNewLabel_A.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_B.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_C.setBackground(new Color(37,102,68));
                lblNewLabel_D.setBackground(Color.LIGHT_GRAY);
                selectString = "C";
            } else if (str.equals("D")) {
                lblNewLabel_A.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_B.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_C.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_D.setBackground(new Color(37,102,68));
                selectString = "D";
            }
            answerMap.put(Integer.toString(sort), selectString);
        }

    }

    // 跳转事件
    class AnswerQuestionListener implements ActionListener {
        private JLabel lblNewLabel_answer, lblNewLabel_question;
        private JButton btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4, btnNewButton_5, btnNewButton_6,
                btnNewButton_7, btnNewButton_8, btnNewButton_9, btnNewButton_10;
        private JButton lblNewLabel_A, lblNewLabel_B, lblNewLabel_C, lblNewLabel_D;
        private JButton btnNewButton_submit;


        public AnswerQuestionListener(JLabel lblNewLabel_answer, JLabel lblNewLabel_question, JButton btnNewButton_1,
                                      JButton btnNewButton_2, JButton btnNewButton_3, JButton btnNewButton_4, JButton btnNewButton_5,
                                      JButton btnNewButton_6, JButton btnNewButton_7, JButton btnNewButton_8, JButton btnNewButton_9,
                                      JButton btnNewButton_10, JButton lblNewLabel_A, JButton lblNewLabel_B, JButton lblNewLabel_C,
                                      JButton lblNewLabel_D, JButton btnNewButton_submit) {
            super();
            this.lblNewLabel_answer = lblNewLabel_answer;
            this.lblNewLabel_question = lblNewLabel_question;
            this.btnNewButton_1 = btnNewButton_1;
            this.btnNewButton_2 = btnNewButton_2;
            this.btnNewButton_3 = btnNewButton_3;
            this.btnNewButton_4 = btnNewButton_4;
            this.btnNewButton_5 = btnNewButton_5;
            this.btnNewButton_6 = btnNewButton_6;
            this.btnNewButton_7 = btnNewButton_7;
            this.btnNewButton_8 = btnNewButton_8;
            this.btnNewButton_9 = btnNewButton_9;
            this.btnNewButton_10 = btnNewButton_10;
            this.lblNewLabel_A = lblNewLabel_A;
            this.lblNewLabel_B = lblNewLabel_B;
            this.lblNewLabel_C = lblNewLabel_C;
            this.lblNewLabel_D = lblNewLabel_D;
            this.btnNewButton_submit = btnNewButton_submit;
        }



        @Override
        public void actionPerformed(ActionEvent e) {
            // 10道题目
            if (flag_start != 1) {
                JOptionPane.showMessageDialog(null, "先点击开始考试！");
                return;
            }
            // 获取当前选项
            String selectABCD = answerMap.get(Integer.toString(sort));
            //System.out.println(selectABCD);

            // 跳转的操作
            String questionSort = ((JButton) e.getSource()).getText();
            String jumpSort = questionSort;
            ExamData examData = new ExamData();
            // 下一题事件或者跳转事件
            if (questionSort.equals("下一题")) {
                if (Integer.valueOf(sort) == 10) {
                    JOptionPane.showMessageDialog(null, "已到最后一题！");
                    //提交操作
                    btnNewButton_submit.doClick();
                    return;
                }
                // 获取下一题序号
                String nextSort = Integer.toString(sort + 1);
                jumpSort = nextSort;
                examData = getNext(getParam(nextSort, questionIdMap.get(Integer.toString(sort)), selectABCD));
            } else if (questionSort.equals("上一题")) {
                if (Integer.valueOf(sort) == 1) {
                    JOptionPane.showMessageDialog(null, "已是第一题！");
                    return;
                }
                // 获取上一题序号
                String previousSort = Integer.toString(sort - 1);
                jumpSort = previousSort;
                examData = getNext(getParam(previousSort, questionIdMap.get(Integer.toString(sort)), selectABCD));
            } else {
                // 获取当前题目位置
                String nowSort = Integer.toString(sort);
                examData = getNext(getParam(questionSort, questionIdMap.get(nowSort), selectABCD));
            }
            // 改变按钮颜色
            changeButton(btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4, btnNewButton_5, btnNewButton_6,
                    btnNewButton_7, btnNewButton_8, btnNewButton_9, btnNewButton_10);
            // 通用部分
            try {
                AnswerSetText(lblNewLabel_answer, examData.getQuestion().getOptions());
                JlabelSetText(lblNewLabel_question, examData.getQuestion().getSubject());

            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            // 回显选项
            if (answerMap.containsKey(jumpSort)) {
                String selectLocalString = answerMap.get(jumpSort);
                if (selectLocalString.equals("A")) {
                    lblNewLabel_A.setBackground(new Color(37,102,68));
                    lblNewLabel_B.setBackground(Color.LIGHT_GRAY);
                    lblNewLabel_C.setBackground(Color.LIGHT_GRAY);
                    lblNewLabel_D.setBackground(Color.LIGHT_GRAY);
                } else if (selectLocalString.equals("B")) {
                    lblNewLabel_B.setBackground(new Color(37,102,68));
                    lblNewLabel_A.setBackground(Color.LIGHT_GRAY);
                    lblNewLabel_C.setBackground(Color.LIGHT_GRAY);
                    lblNewLabel_D.setBackground(Color.LIGHT_GRAY);
                } else if (selectLocalString.equals("C")) {
                    lblNewLabel_C.setBackground(new Color(37,102,68));
                    lblNewLabel_B.setBackground(Color.LIGHT_GRAY);
                    lblNewLabel_A.setBackground(Color.LIGHT_GRAY);
                    lblNewLabel_D.setBackground(Color.LIGHT_GRAY);
                } else if (selectLocalString.equals("D")) {
                    lblNewLabel_D.setBackground(new Color(37,102,68));
                    lblNewLabel_B.setBackground(Color.LIGHT_GRAY);
                    lblNewLabel_C.setBackground(Color.LIGHT_GRAY);
                    lblNewLabel_A.setBackground(Color.LIGHT_GRAY);
                }
            } else {
                lblNewLabel_A.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_B.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_C.setBackground(Color.LIGHT_GRAY);
                lblNewLabel_D.setBackground(Color.LIGHT_GRAY);
            }
        }

    }

    // 提交事件
    class SubmitListener implements ActionListener {
        private JLabel lblNewLabel_1, lblNewLabel_2, lblNewLabel_answer, lblNewLabel_question;
        private JButton lblNewLabel_A, lblNewLabel_B, lblNewLabel_C, lblNewLabel_D;

        public SubmitListener(JLabel lblNewLabel_1, JLabel lblNewLabel_2, JLabel lblNewLabel_answer,
                              JLabel lblNewLabel_question, JButton lblNewLabel_A, JButton lblNewLabel_B, JButton lblNewLabel_C,
                              JButton lblNewLabel_D) {
            super();
            this.lblNewLabel_1 = lblNewLabel_1;
            this.lblNewLabel_2 = lblNewLabel_2;
            this.lblNewLabel_answer = lblNewLabel_answer;
            this.lblNewLabel_question = lblNewLabel_question;
            this.lblNewLabel_A = lblNewLabel_A;
            this.lblNewLabel_B = lblNewLabel_B;
            this.lblNewLabel_C = lblNewLabel_C;
            this.lblNewLabel_D = lblNewLabel_D;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (flag_start != 1) {
                JOptionPane.showMessageDialog(null, "请先点击开始考试！");
                return;
            }
            //提交最后一次答题结果

            // 获取当前选项
            String selectABCD = answerMap.get(Integer.toString(sort));

            String nowSort = Integer.toString(sort);

            getNext(getParam(nowSort, questionIdMap.get(nowSort), selectABCD));


            // 销毁timer
            timer.cancel();
            timer = new Timer();

            flag_start = 0;
            lblNewLabel_1.setText("未开始");
            lblNewLabel_2.setText("剩时：");
            sort = 0;
            String submitString = getSubmit(getParam("0", "0", "0"));
            // 重置全局变量数据
            examId = "";
            answerMap = new HashMap<String, String>();
            questionIdMap = new HashMap<String, String>();

            lblNewLabel_A.setBackground(Color.LIGHT_GRAY);
            lblNewLabel_B.setBackground(Color.LIGHT_GRAY);
            lblNewLabel_C.setBackground(Color.LIGHT_GRAY);
            lblNewLabel_D.setBackground(Color.LIGHT_GRAY);

            lblNewLabel_question.setText("你的分数是:" + submitString.substring(11) + "分");
            lblNewLabel_answer.setText("");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                        fframe.frame.setVisible(true);
                        fframe.flag_jump=true;
                        View2.this.setVisible(false);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }}).start();


        }
    }

    // 开始答题事件
    class StartListener implements ActionListener {
        private JLabel lblNewLabel_1, lblNewLabel_2, lblNewLabel_answer, lblNewLabel_question;
        private JButton btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4, btnNewButton_5, btnNewButton_6,
                btnNewButton_7, btnNewButton_8, btnNewButton_9, btnNewButton_10;
        private JButton btnNewButton_submit;

        public StartListener(JLabel lblNewLabel_1, JLabel lblNewLabel_2, JLabel lblNewLabel_answer,
                             JLabel lblNewLabel_question, JButton btnNewButton_1, JButton btnNewButton_2, JButton btnNewButton_3,
                             JButton btnNewButton_4, JButton btnNewButton_5, JButton btnNewButton_6, JButton btnNewButton_7,
                             JButton btnNewButton_8, JButton btnNewButton_9, JButton btnNewButton_10, JButton btnNewButton_submit) {
            super();
            this.lblNewLabel_1 = lblNewLabel_1;
            this.lblNewLabel_2 = lblNewLabel_2;
            this.lblNewLabel_answer = lblNewLabel_answer;
            this.lblNewLabel_question = lblNewLabel_question;
            this.btnNewButton_1 = btnNewButton_1;
            this.btnNewButton_2 = btnNewButton_2;
            this.btnNewButton_3 = btnNewButton_3;
            this.btnNewButton_4 = btnNewButton_4;
            this.btnNewButton_5 = btnNewButton_5;
            this.btnNewButton_6 = btnNewButton_6;
            this.btnNewButton_7 = btnNewButton_7;
            this.btnNewButton_8 = btnNewButton_8;
            this.btnNewButton_9 = btnNewButton_9;
            this.btnNewButton_10 = btnNewButton_10;
            this.btnNewButton_submit = btnNewButton_submit;
        }



        @Override
        public void actionPerformed(ActionEvent e) {

            if (flag_start != 0) {
                return;
            }
            // 刷新时间
            time = 30 * 60 + 1;
//			time=10;
            timer.cancel();
            timer = new Timer();
            timerStart(1000, 1, lblNewLabel_2,btnNewButton_submit);
            // 第二次点击开始按钮无效
            flag_start = 1;
            lblNewLabel_1.setText("开始答题");
            ExamData examData = getStart(getParam("1", "0", "0"));
            AnswerSetText(lblNewLabel_answer, examData.getQuestion().getOptions());

            // 改变颜色
            changeButton(btnNewButton_1, btnNewButton_2, btnNewButton_3, btnNewButton_4, btnNewButton_5, btnNewButton_6,
                    btnNewButton_7, btnNewButton_8, btnNewButton_9, btnNewButton_10);

            try {
                JlabelSetText(lblNewLabel_question, examData.getQuestion().getSubject());

            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    // 调用http服务
    public ExamData getStart(Map<String, String> map) {
        Examination e = new Examination();
        String jsonString = e.doGet(map);
        ExamData examData = Examination.JsonToObject(jsonString, ExamData.class);

        // 全局变量赋值
        questionIdMap.put(examData.getQuestion().getQuestionSort(),
                Integer.toString(examData.getQuestion().getQuestionId()));
        examId = examData.getExamId().toString();
        return examData;
    }

    // 获取下一题
    public ExamData getNext(Map<String, String> map) {
        Examination e = new Examination();
        String jsonString = e.nextGet(map);
        ExamData examData = Examination.JsonToObject(jsonString, ExamData.class);

        questionIdMap.put(examData.getQuestion().getQuestionSort(),
                Integer.toString(examData.getQuestion().getQuestionId()));
        return examData;
    }

    // 提交
    public String getSubmit(Map<String, String> map) {
        Examination e = new Examination();
        String submitString = e.submitGet(map);
        return submitString;
    }

    // 获取参数
    public Map<String, String> getParam(String questionSort, String questionId, String answer) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userId", userId);
        map.put("userID", userId);
        map.put("userName", userName);
        map.put("customId", ParameterOperate.extract("CurrentCustomID"));
        map.put("customName", ParameterOperate.extract("CurrentProName"));

        // 可变参数
        map.put("questionSort", questionSort);
        map.put("questionId", questionId);
        map.put("answer", answer);

        // 每一次调用参数更新当前题目位置
        sort = Integer.valueOf(questionSort);

        if (examId != null) {
            map.put("examId", examId);
        }
        return map;
    }

    // 选项换行
    public void AnswerSetText(JLabel Label, String options) {
        StringBuilder builder = new StringBuilder("<html>");
        builder.append(options);
        builder.append("</html>");
        Label.setText(builder.toString());
    }

    // 题目换行
    public void JlabelSetText(JLabel Label, String longString) throws InterruptedException {
        longString = "(第" + sort + "/10题)" + longString;
        StringBuilder builder = new StringBuilder("<html>");
        char[] chars = longString.toCharArray();
        int start = 0;
        int len = 0;
        while (start + len < longString.length()) {
            while (true) {
                len = len + 1;
                if (start + len > longString.length())
                    break;
            }
            builder.append(chars, start, len - 1).append("<br/>");
            start = start + len - 1;
            len = 0;
        }
        builder.append(chars, start, longString.length() - start);
        builder.append("</html>");
        Label.setText(builder.toString());
    }

    // 已完成题目按钮颜色改变
    public void changeButton(JButton btnNewButton_1, JButton btnNewButton_2, JButton btnNewButton_3,
                             JButton btnNewButton_4, JButton btnNewButton_5, JButton btnNewButton_6, JButton btnNewButton_7,
                             JButton btnNewButton_8, JButton btnNewButton_9, JButton btnNewButton_10) {
        if (!answerMap.isEmpty()) {
            becomeGRAY();
            // 遍历key(questionSort)
            for (String key : answerMap.keySet()) {
                switch (Integer.valueOf(key)) {
                    case 1:
                        btnNewButton_1.setBackground(new Color(37,102,68));
                        break;
                    case 2:
                        btnNewButton_2.setBackground(new Color(37,102,68));
                        break;
                    case 3:
                        btnNewButton_3.setBackground(new Color(37,102,68));
                        break;
                    case 4:
                        btnNewButton_4.setBackground(new Color(37,102,68));
                        break;
                    case 5:
                        btnNewButton_5.setBackground(new Color(37,102,68));
                        break;
                    case 6:
                        btnNewButton_6.setBackground(new Color(37,102,68));
                        break;
                    case 7:
                        btnNewButton_7.setBackground(new Color(37,102,68));
                        break;
                    case 8:
                        btnNewButton_8.setBackground(new Color(37,102,68));
                        break;
                    case 9:
                        btnNewButton_9.setBackground(new Color(37,102,68));
                        break;
                    case 10:
                        btnNewButton_10.setBackground(new Color(37,102,68));
                        break;
                    default:
                        break;
                }
            }
            becomeCYAN();
        } else {
            becomeGRAY();
            //当前题号颜色变为黄色
            becomeCYAN();
        }

    }
    public void becomeGRAY() {
        btnNewButton_1.setBackground(Color.LIGHT_GRAY);
        btnNewButton_2.setBackground(Color.LIGHT_GRAY);
        btnNewButton_3.setBackground(Color.LIGHT_GRAY);
        btnNewButton_4.setBackground(Color.LIGHT_GRAY);
        btnNewButton_5.setBackground(Color.LIGHT_GRAY);
        btnNewButton_6.setBackground(Color.LIGHT_GRAY);
        btnNewButton_7.setBackground(Color.LIGHT_GRAY);
        btnNewButton_8.setBackground(Color.LIGHT_GRAY);
        btnNewButton_9.setBackground(Color.LIGHT_GRAY);
        btnNewButton_10.setBackground(Color.LIGHT_GRAY);
    }

    public void becomeCYAN() {
        switch(sort) {
            case 1:
                btnNewButton_1.setBackground(Color.CYAN);
                break;
            case 2:
                btnNewButton_2.setBackground(Color.CYAN);
                break;
            case 3:
                btnNewButton_3.setBackground(Color.CYAN);
                break;
            case 4:
                btnNewButton_4.setBackground(Color.CYAN);
                break;
            case 5:
                btnNewButton_5.setBackground(Color.CYAN);
                break;
            case 6:
                btnNewButton_6.setBackground(Color.CYAN);
                break;
            case 7:
                btnNewButton_7.setBackground(Color.CYAN);
                break;
            case 8:
                btnNewButton_8.setBackground(Color.CYAN);
                break;
            case 9:
                btnNewButton_9.setBackground(Color.CYAN);
                break;
            case 10:
                btnNewButton_10.setBackground(Color.CYAN);
                break;
            default:
                break;
        }
    }
    // 倒计时
    public void timerStart(int period, int type, JLabel label_count,JButton btnNewButton_submit) {

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                time--;
                label_count.setText("剩时：" + String.valueOf(time / 60) + "分" + String.valueOf(time % 60) + "秒");
                if (time == 0) {
                    timer.cancel();
                    //时间结束调用提交事件
                    btnNewButton_submit.doClick();
                }
            }
        }, 0, period);
    }
}

