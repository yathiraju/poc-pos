package com.pos.util;

import java.awt.Font;
import java.awt.image.BufferedImage;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

/**
 * @author yathi
 *
 */
public class BarbecueBarcodeGenerator {

	private static final Font BARCODE_TEXT_FONT = new Font("pos", 16, 16);

	public static BufferedImage generateEAN13BarcodeImage(String barcodeText) throws Exception {
		Barcode barcode = BarcodeFactory.createEAN13(barcodeText);
		barcode.setFont(BARCODE_TEXT_FONT);
		return BarcodeImageHandler.getImage(barcode);
	}

}
