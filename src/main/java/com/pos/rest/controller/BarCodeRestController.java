package com.pos.rest.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pos.util.BarbecueBarcodeGenerator;

/**
 * @author yathi
 *
 */
@RestController
@RequestMapping("/barcodes")
public class BarCodeRestController {

	@GetMapping(value = "/barbecue/ean13/{barcode}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] barbecueEAN13Barcode(@PathVariable("barcode") String barcode) throws Exception {
		BufferedImage bufferedImage = BarbecueBarcodeGenerator.generateEAN13BarcodeImage(barcode);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( bufferedImage, "png", baos );
		baos.flush();
		return baos.toByteArray();
	}

}
