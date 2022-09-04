package com.ffait.util;
import com.ffait.examine.FaceService;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Auther: NF
 * @Date: 2022/09/04/19:18
 * @Description:
 */
public class ImageBlur {
    static FaceService fs=new FaceService();

    public static void blur(Mat src) {
        //克隆，数据,大小，类型等同于输入图像
        Mat image = src.clone();
        Imgproc.blur(src, image, new Size(100,100),new Point(1,1));
        HighGui.imshow("均值模糊(降噪)", image);
        HighGui.waitKey();
    }

    public static BufferedImage gausssianBlur(Mat src) {
        //返回一个恒等指定大小和类型矩阵,数据全部有0填充。
        Mat image = Mat.eye(src.size(),src.type());
        Imgproc.GaussianBlur(src, image, new Size(11,11), 7,7);
        return fs.mat2BI(image);
    }

    //虚化图片后将人脸在画上去
    public static BufferedImage drawFace(BufferedImage image,BufferedImage face){
        Graphics g = image.getGraphics();

        g.drawImage(face,350,190,270,340,null);

        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(5.0f));
        g2.drawOval(350, 190, 270, 340);

        return image;
    }

    public static void mediaBlur(Mat src) {
        //返回一个恒等指定大小和类型矩阵,数据全部有0填充。
        Mat image = Mat.zeros(src.size(),src.type());
        Imgproc.medianBlur(src, image, 9);
        HighGui.imshow("中值滤波(降噪)", image);
        HighGui.waitKey();
    }

    public static void bilateralFilter(Mat src) {
        //返回一个恒等指定大小和类型矩阵,数据全部有BGR分别由100填充。
        Mat image = Mat.ones(src.size(),src.type());
        Imgproc.bilateralFilter(src, image, 2, 200, 5);
        HighGui.imshow("双边滤波(降噪)", image);
        HighGui.waitKey();
    }

}

