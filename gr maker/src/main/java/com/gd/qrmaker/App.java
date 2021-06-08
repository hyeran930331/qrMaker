package com.gd.qrmaker;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame; // 창띄우기위해서
import javax.swing.JLabel;

import org.slf4j.Logger; //로그 출력
import org.slf4j.LoggerFactory; //로그 출력

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Hello world!
 *
 */
public class App extends JFrame
{
	static Logger log = LoggerFactory.getLogger(App.class); //로그 출력
    public static void main( String[] args ) throws WriterException, IOException
    {
        //1.
    	QRService qrService = new QRService();
    	String userName = qrService.getUserName();
    	
    	StringBuffer contents = new StringBuffer();
    	contents.append(userName);
    	//contents.append(",");
    	//2.
    	QRCodeWriter qrWriter = new QRCodeWriter();
    	//BitMatrix martix = qrwiter.encode(컨텐츠, QR종류, 폭/넓이. 높이) 모양과 커
    	BitMatrix matrix = qrWriter.encode(contents.toString(), BarcodeFormat.QR_CODE, 200, 200);
    	//16진수 색 :0x FF FFFFFF 배경색
    	MatrixToImageConfig config = new MatrixToImageConfig(0xFFFFFFFF, 0xFF000000); 
    	//두개의 설정 매개변수를 이용하여 이미지 생성
    	BufferedImage qrImage= MatrixToImageWriter.toBufferedImage(matrix,config);
    	
    	//3.저장 
    	//ImageIO.write(qrImage, userName, null);
    	String imageFileName = "myqr.png";
    	ImageIO.write(qrImage, "png", new File("myqr.png"));
    	
    	//4.
    	App app = new App();
        app.setTitle("QR");
        app.setLayout(new FlowLayout());

    	ImageIcon icon = new ImageIcon(imageFileName);
    	JLabel imageLabel = new JLabel(icon);
    	app.add(imageLabel);
    	
    	app.setSize(300,300);
    	app.setVisible(true);
    	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
