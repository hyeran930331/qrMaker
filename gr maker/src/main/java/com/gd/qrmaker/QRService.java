package com.gd.qrmaker;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class QRService extends JFrame  {
	static Logger log = LoggerFactory.getLogger(App.class); // log 출력
	//사용자 이름(고정)service
	public String getUserName() throws WriterException, IOException{
		log.debug("2. param없음"); //콘트롤러에서 넘어온값 확인
		String userName = "Hyeran"; //없어서 기본값설정
		//contents라는 StringBuffer객체를 생성한다. (삽입에 데이터 낭비가 없는 데이터형)
		StringBuffer contents = new StringBuffer(); 
    	contents.append(userName); //앞서 설정한 userName를 넣는다.
    	//contents.append(","); // 여러개면 구분자 ,를 넣는다.
    	
    	//2.생성
    	//QRCodewriter 큐알코드를 작성하는 Object를 qrWriter를 객체로 생성한다.
    	QRCodeWriter qrWriter = new QRCodeWriter(); //This object renders a QR Code as a BitMatrix 2D array of greyscale values
    	
  
    	//BitMatrix martix = qrwiter.encode(컨텐츠, QR종류, 폭/넓이. 높이)를 설정한다.
    	BitMatrix matrix = qrWriter.encode(contents.toString(), BarcodeFormat.QR_CODE, 300, 300);
    	//16진수 색 :0x FF FFFFFF 배경색
    	//MatrixToImageConfig config = new MatrixToImageConfig(0xFFFFFFFF, 0xFF000000); 
    	//설정 매개변수 matrix를 이용하여 이미지 생성하는 MatrixToImageWriter.toBufferedImage를 실행하여 bufferedImage에 넣어둔다.
    	BufferedImage qrImage= MatrixToImageWriter.toBufferedImage(matrix);
    	
    	//3.저장 
    	//
    	String imageFileName = "myqr.png"; //이미지 이름 지정
    	ImageIO.write(qrImage, "png", new File(imageFileName));//버퍼에저장된이미지 , 타입, 파일이름 설정.
    	
    	//4.
    	App app = new App(); // Jframe을 상속받은 객체 생성
        app.setTitle("QR");// 제목 qr 설정
        app.setLayout(new FlowLayout());// layout 설정
        
        ImageIcon icon = new ImageIcon(imageFileName);// 이미지 아이콘을 imageFileName이용하여 설정
        JLabel imageLabel = new JLabel(icon);// label에도 icon정보 지정
        app.add(imageLabel);// app에 더한다.
        
        app.setSize(400, 400);// 실행할 app 사이즈의 크기 지정
        app.setVisible(true);// 우리 눈으로 볼 수 있게 설정
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// x버튼을 누르면 프로그램이 종료 되도록 지정
    	
    	String url;
    	return url;
	}

	public String getUserInfo(Info info) throws WriterException, IOException {
		log.debug("2. param 확인 : "+info);
		StringBuffer contents = new StringBuffer(); 
		contents.append(info.getName()); 
    	contents.append(",");
    	contents.append(info.getPhone());
    	contents.append(",");
    	contents.append(info.getGPS());
    	contents.append(",");
    	contents.append(info.getIP());
    	//배열로 받아서, 포문을 쓸수 있을까는 잠시 뒤 생각해보기로 하자.
    	
    	//2.
    	QRCodeWriter qrWriter = new QRCodeWriter();
    	//BitMatrix martix = qrwiter.encode(컨텐츠, QR종류, 폭/넓이. 높이) 모양과 커
    	BitMatrix matrix = qrWriter.encode(contents.toString(), BarcodeFormat.QR_CODE, 200, 200);
    	//16진수 색 :0x FF FFFFFF 배경색
    	//MatrixToImageConfig config = new MatrixToImageConfig(0xFFFFFFFF, 0xFF000000); 
    	//두개의 설정 매개변수를 이용하여 이미지 생성
    	BufferedImage qrImage= MatrixToImageWriter.toBufferedImage(matrix);
    	
    	//3.저장 
    	//ImageIO.write(qrImage, userName, null);
    	String imageFileName = "info.png";
    	ImageIO.write(qrImage, "png", new File(imageFileName));
    	
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
    	
    	String url;
    	return url;
	}
	
	/*
	 * 장치이름
	 * 장치의 QS
	 * 장치의 IP
	 * 장치의 GPS
	 */
}
