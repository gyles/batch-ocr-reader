package com.think.ocr.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.think.ocr.domain.TextContent;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import javax.imageio.ImageIO;

@Service
@RequiredArgsConstructor
public class OcrService {

	public TextContent read(MultipartFile image) {
		TextContent barcode = new TextContent();
		barcode.setFilename(image.getOriginalFilename());
		barcode.setText(getText(image));

		return barcode;
	}

	public List<TextContent> readAll(List<MultipartFile> images) {
		return images.stream().map(this::read).collect(Collectors.toList());
	}

	private String getText(MultipartFile image) {
		String text;
		try {
			File libPath = LoadLibs.extractTessResources("linux-x86-64");
			System.setProperty("java.library.path", libPath.getPath());

			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(image.getBytes()));
			Tesseract tesseract = new Tesseract();
			tesseract.setDatapath("src/main/resources/tessdata");
			tesseract.setLanguage("eng");
			tesseract.setPageSegMode(1);
			tesseract.setOcrEngineMode(1);

			text = tesseract.doOCR(bufferedImage);
		} catch (IOException | TesseractException e) {
			text = "Unable to read code from binary source.";
		}

		return text;
	}

}
