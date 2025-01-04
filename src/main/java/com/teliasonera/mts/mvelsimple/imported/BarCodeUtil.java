package com.teliasonera.mts.mvelsimple.imported;

import org.apache.commons.lang.StringUtils;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.Code128Constants;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * @author Lars Lie <lars.lie@netcom-gsm.no>
 */
public final class BarCodeUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BarCodeUtil.class);
    public static final float HEIGHT_BASIC = 6f;
    public static final float HEIGHT_WITH_TEXT = 9.7f;
    public static final float MODULE_WIDTH = 0.25f;
    public static final String MIME = "image/png";
    public static final int RESOLUTION = 300;
    public static final String POSITION_NONE = "none";

    private BarCodeUtil() {
    }

    /**
     * Generates barcode for defined {@code code}, allowing the result to be rotated and allowing the {@code code}
     * being generated above or below the generated barcode.
     *
     * @param code the actual barcode value
     * @param rotation by how many degrees to rotate the result; allowed values are 0, 90, 180, 270, -90, -180 or -270;
     *                 negative values rotate the image clockwise, positive values counter-clockwise
     * @param textPosition controls position of the text; allowed values are "none", "top" and "bottom"; blank string
     *                     is interpreted as "none"
     *
     * @return barcode image in PNG format, encoded into Base64
     *
     * @throws IllegalArgumentException in case value of {@code rotation} or {@code textPosition} is not supported
     * */
    public static String generateBarCode(String code, int rotation, String textPosition) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }

        HumanReadablePlacement msgPosition = StringUtils.isBlank(textPosition)
                ? HumanReadablePlacement.HRP_NONE
                : HumanReadablePlacement.byName(textPosition);

        // the actual barcode area has reduced height when human-readable text is included in the generated output
        // this difference needs to be taken into account to avoid generating barcodes that are hard to scan
        float height = HumanReadablePlacement.HRP_NONE.equals(msgPosition) ? HEIGHT_BASIC : HEIGHT_WITH_TEXT;

        //Create the barcode bean
        Code128Bean bean = new Code128Bean();
        bean.setMsgPosition(msgPosition);
        bean.setHeight(height);
        bean.setModuleWidth(MODULE_WIDTH);
        bean.setCodeset(Code128Constants.CODESET_B);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        //Set up the canvas provider for monochrome PNG output
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(bos, MIME, RESOLUTION, BufferedImage.TYPE_BYTE_GRAY, false, rotation);

        //Generate the barcode
        bean.generateBarcode(canvas, code);

        //Signal end of generation
        try {
            canvas.finish();
        } catch (IOException e) {
            LOGGER.error("Error generating barcode image.", e);
            return null;
        }
        return Base64.getEncoder().encodeToString(bos.toByteArray());
    }

    /**
     * Convenient method for generating a simple barcode. This is functionally equivalent to calling
     * {@link BarCodeUtil#generateBarCode(String, int, String)} with {@code rotation} set to zero and
     * {@code textPosition} set to "none".
     *
     * @param code the actual barcode value
     *
     * @return barcode image in PNG format (not rotated, without human-readable text), encoded into Base64
     * */
    public static String generateBarCode(String code) {
        return generateBarCode(code, 0, POSITION_NONE);
    }
}
