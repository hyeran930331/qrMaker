package com.gd.qrmaker;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
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
    	/* ImageIO.read() 메서드로 이미지를 읽어들여서 BufferedImage 객체를 만든다.
		BufferedImage image = ImageIO.read(URL 객체)
		출처: https://bsceye.tistory.com/entry/이미지-크롤링-1-ImageIO-이용하기 [the 소소한]*/
    	BufferedImage qrImage= MatrixToImageWriter.toBufferedImage(matrix);
    	
    	//3.저장 
    	/*ImageIO.write() 메서드로 이미지를 저장한다.
		File file = new File(경로)
		ImageIO.write(읽어들인 이미지, 확장자, 저장할 위치)
		출처: https://bsceye.tistory.com/entry/이미지-크롤링-1-ImageIO-이용하기 [the 소소한]*/
    	String imageFileName = "\\myqr.png"; //이미지 이름 지정
    	ImageIO.write(qrImage, "png", new File(imageFileName));//버퍼에저장된이미지 , 타입, 파일경로 설정.
    	
    	String url = imageFileName;
    	return url;
	}

	public String getUserInfo(Info info) throws WriterException, IOException {
		log.debug("2. param 확인 : "+info);
		StringBuffer contents = new StringBuffer(); 
		contents.append(info.getUsername()); 
    	contents.append(",");
    	contents.append(info.getAge());
    	contents.append(",");
    	contents.append(info.getGender());
    	contents.append(",");
    	contents.append(info.getAddress());
    	contents.append(",");
    	contents.append(info.getGps());
    	//배열로 받아서, 포문을 쓸수 있을까는 잠시 뒤 생각해보기로 하자.
    	
    	//2.
    	QRCodeWriter qrWriter = new QRCodeWriter();
    	   	BitMatrix matrix = qrWriter.encode(contents.toString(), BarcodeFormat.QR_CODE, 200, 200);
    	//MatrixToImageConfig config = new MatrixToImageConfig(0xFFFFFFFF, 0xFF000000); 
    	BufferedImage qrImage= MatrixToImageWriter.toBufferedImage(matrix);
    	
    	//3.저장 
    	String imageFileName = contents+".png";
    	ImageIO.write(qrImage, "png", new File(imageFileName));
    	
    	String url = imageFileName;
    	return url;
	}
	
	/*
	 * 장치이름
	 * 장치의 QS
	 * 장치의 IP
	 * 장치의 GPS
	 */
	public String returnQRname(Info info) {
	      // 1. QR에 컨텐츠 추가
	      String username = info.getUsername();
	      int age = info.getAge();
	      String gender = info.getGender();
	      String address = info.getAddress();
	      String gps = info.getGps();
	      
	      StringBuffer contents = new StringBuffer(); // 데이터 추가를 위해 StringBuffer 타입 사용
	      contents.append(username);
	      contents.append(",");
	      contents.append(age);
	      contents.append(",");
	      contents.append(gender);
	      contents.append(",");
	      contents.append(address);
	      contents.append(",");
	      contents.append(gps);
	      
	      // 2. QR 생성
	      QRCodeWriter qrWriter = new QRCodeWriter();
	      BitMatrix matrix = null;
	      MatrixToImageConfig config = null;
	      BufferedImage qrImage = null;
	      try {
	         matrix = qrWriter.encode(contents.toString(), BarcodeFormat.QR_CODE, 200, 200);// (컨텐츠, qr종류, 넓이, 높이)
	         config = new MatrixToImageConfig(0xFFFFFFFF,0xFF000000);// 색상 설정파일(qr색상, 배경색상)
	         qrImage = MatrixToImageWriter.toBufferedImage(matrix, config);// matrix와 config를 이용하여 qr 이미지 생성
	         
	      } catch (WriterException e) {
	         e.printStackTrace();
	      } 
	      
	      // 3. qr 저장
	      String fileName = username+UUID.randomUUID()+".png";
	      try {
	         ImageIO.write(qrImage, "png", new File("D:\\stswork\\restapi\\src\\main\\webapp\\img\\"+fileName)); // 메모리안의 이미지, 확장자, 파일생성
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	      
	      // 4. qr 출력 ->web이면 jsp view에 출력 pc앱이면 swing frame android앱이면 activity
	      
	      return fileName;
	   }

}
