package com.app;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GmaDiariesApplication {
	
	@RequestMapping(value="/resource", produces = {"application/json", "image/jpeg"})
	public Map<String, Object> home(HttpServletRequest request) {
		
		
		//handle image reading, encoding, writing
		ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
		BufferedImage image = null;
		
		try {
			InputStream is;
			is = new BufferedInputStream(
		          new FileInputStream("src/main/resources/static/images/1941Postcard.jpg"));
			image = ImageIO.read(is);
			ImageIO.write(image, "jpeg", jpegOutputStream);			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {	
			e.printStackTrace();
		} catch (Exception e) {
			e.getMessage();
		}

		byte[] gmaImageByte = jpegOutputStream.toByteArray();
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("date", "12/12/1945");
		model.put("image", gmaImageByte);
		return model;
	}
	

	public static void main(String[] args) {
		SpringApplication.run(GmaDiariesApplication.class, args);
	}
}
