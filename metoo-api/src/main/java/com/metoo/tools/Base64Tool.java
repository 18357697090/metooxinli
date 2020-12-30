package com.metoo.tools;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Base64Tool implements Serializable {
    public static BufferedImage base64String2BufferedImage(String base64string) {
        BufferedImage image = null;
        try {
            InputStream stream = BaseToInputStream(base64string);
            image = ImageIO.read(stream);
        } catch (IOException e) {
            System.out.println(e+"3111133");
        }
        return image;
    }

    public static String imageToBase64(BufferedImage bufferedImage) {
        Base64 encoder = new Base64();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "jpg", baos);
        } catch (IOException e) {
            System.out.println(e+"332223");
        }
        return new String(encoder.encode((baos.toByteArray())));
    }

    private static InputStream BaseToInputStream(String base64string){
        ByteArrayInputStream stream = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64string);
            stream = new ByteArrayInputStream(bytes1);
        } catch (Exception e) {
            System.out.println(e+"333");
        }
        return stream;
    }

    public static String resizeImageTo40K(String base64Img) {
        try {
            BufferedImage src = base64String2BufferedImage(base64Img);
            BufferedImage output = Thumbnails.of(src).size(src.getWidth()/3, src.getHeight()/3).asBufferedImage();
            String base64 = imageToBase64(output);
            if (base64.length() - base64.length() / 8 * 2 > 40000) {
                output = Thumbnails.of(output).scale(1/(base64.length()/40000)).asBufferedImage();
                base64 = imageToBase64(output);
            }
            return base64;
        } catch (Exception e) {
            System.out.println(4444);
            return base64Img;
        }
    }


}
