package com.compressImage;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Iterator;

/**
 * @author Rubbck
 * @date 2020/11/11-17:36
 * 图片压缩,通过压缩图片质量,不改变图片尺寸
 */
public class ImageUtil {
    /**
     * 图片压缩,通过压缩图片质量,不改变图片尺寸
     *
     * @param imgByte 图片的byte数组
     * @param quality 图片质量(0-1)
     * @return byte[] 压缩后的图片
     */
    public static byte[] compressPicByQuality(byte[] imgByte, float quality) {
        byte[] imageBytes = null;
        ByteArrayInputStream inputStream = null;
        BufferedImage bufferedImage = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            inputStream = new ByteArrayInputStream(imgByte);
            bufferedImage = ImageIO.read(inputStream);
            //如果图片为空,返回空
            if (bufferedImage == null) {
                return null;
            }
            //得到指定的fomat图片的write的图片(迭代器)
            Iterator<ImageWriter> jpg = ImageIO.getImageWritersByFormatName("jpg");
            //得到writer
            ImageWriter imageWriter = jpg.next();
            //得到指定的writer的输出参数设置
            ImageWriteParam defaultWriteParam = imageWriter.getDefaultWriteParam();
            //设置可否压缩
            defaultWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            //设置压缩参数
            defaultWriteParam.setCompressionQuality(quality);
            defaultWriteParam.setProgressiveMode(ImageWriteParam.MODE_DISABLED);
            ColorModel rgBdefault = ColorModel.getRGBdefault();
            ////指定压缩的色彩模式
            defaultWriteParam.setDestinationType(new ImageTypeSpecifier(rgBdefault, rgBdefault.createCompatibleSampleModel(16, 16)));
            //开始打包文件
            IIOImage iioImage = new IIOImage(bufferedImage, null, null);
            imageWriter.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
            imageWriter.write(null, iioImage, defaultWriteParam);
            imageBytes = byteArrayOutputStream.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return imageBytes;
    }


    public  static byte[] fileToBytes(File  file) throws IOException {
        //File file = new File("C:\\Users\\admin\\Desktop\\my\\a03ea00875438ad8017543edc9bd122f_crop2.jpg");
        FileInputStream inputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = null;
        try {

            BufferedImage read = ImageIO.read(inputStream);
            boolean jpg = ImageIO.write(read, "jpg", byteArrayOutputStream);
            bytes = byteArrayOutputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            byteArrayOutputStream.close();
        }
        return bytes;
    }
    public  static void ByteToFile(byte[] bytes,File file) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        try {
            BufferedImage read = ImageIO.read(inputStream);
            //File file =new File("C:\\Users\\admin\\Desktop\\my\\a03ea00875438ad8017543edc9bd122f_crop3.jpg");
            ImageIO.write(read,"jpg",file);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
    }
    public static void copyByFileChannel(File srcFile,File desFile) throws IOException
    {
        FileChannel srcChannel = new FileInputStream(srcFile).getChannel();
        FileChannel desChannel = new FileOutputStream(desFile).getChannel();
        srcChannel.transferTo(0,srcChannel.size(),desChannel);
        srcChannel.close();
        desChannel.close();
    }
    public static void main(String[] args) throws Exception {
        //byte[] bytes = fileToBytes();
        //byte[] pic = compressPicByQuality(bytes, (float) 0.6);
        //ByteToFile(pic);
      //readFile("D:\\eos\\project\\fileUpload\\empResource");
        //File srcFile  = new  File("D:\\eos\\project\\fileUpload\\empResource\\");
        //int i = srcFile.getAbsolutePath().indexOf("headImg");
        //System.out.println(i);

        readFile("D:\\eos\\project\\fileUpload\\empResource");
        //readFileTest("D:\\eos\\project\\fileUpload\\empResource\\10");
        //copyByFileChannel(new File("D:\\eos\\project\\fileUpload\\empResource\\1\\esignImg\\4028b2f06a0e39ac016a126a36925de9.jpg"),new File("D:\\eos\\project\\fileUpload\\empResource\\1\\esignImg\\4028b2f06a0e39ac016a126a36925de9_20201112.jpg"));
        //transformFile(new File("D:\\eos\\project\\fileUpload\\empResource\\1\\esignImg\\4028b2f06a0e39ac016a126a36925de9.jpg"));
        //System.out.println((14>10)&&(14<30));System.out.println(10/3.0);

    }
    public static void  transformFile(File srcFile,double d,float f) throws Exception {

        //String absolutePath = srcFile.getAbsolutePath();
        //String copyName = name.substring(0,name.indexOf(".")) + "_20201112"+name.substring(name.indexOf("."));
        //String copyPath = absolutePath.substring(0,absolutePath.lastIndexOf("\\"));
        //String copyabsolutePath = copyPath + "\\"+ copyName;
        //File copyfile = new File(copyabsolutePath);
        try {
            ////先去复制文件
            //copyByFileChannel(srcFile,copyfile);
            ////源文件转换为数组
            //byte[] bytes = fileToBytes(srcFile);
            ////数组压缩
            //byte[] pic = compressPicByQuality(bytes, (float) 0.1);
            ////输出数组
            //ByteToFile(pic,copyfile);
            Thumbnails.of(srcFile.getAbsolutePath())
                    .scale(d)
                    .outputQuality(f)
                    .toFile(srcFile.getAbsolutePath());
            System.out.println("压缩后大小"+(srcFile.length()/1024.0));
        } catch (Exception e) {
            throw new Exception();
        }

    }
    public static void readFile(String filepath) throws Exception {
        File file = new File(filepath);

        if(!file.isDirectory()&&(file.getAbsolutePath().indexOf("esignImg")>-1)&&(file.getName().endsWith(".jpg")||file.getName().endsWith(".bmp")||file.getName().endsWith(".png")||file.getName().endsWith(".jpeg"))){
            System.out.print("文件  -->");
            System.out.print("AbsolutePathpath-->"+ file.getAbsolutePath()+"-->");
            System.out.println("大小"+(file.length()/1024));
            IsCompress(file);
        }else if (file.isDirectory()){
            File[] listFiles = file.listFiles();
            for (File listFile : listFiles) {
                File readfile = new File(filepath + "\\" + listFile.getName());
                if(!readfile.isDirectory()&&(file.getAbsolutePath().indexOf("esignImg")>-1)&&(readfile.getName().endsWith(".jpg")||readfile.getName().endsWith(".bmp")||readfile.getName().endsWith(".png")||readfile.getName().endsWith(".jpeg"))){
                    System.out.print("文件  -->");
                    System.out.print("AbsolutePathpath"+ readfile.getAbsolutePath()+"-->");
                    System.out.println("大小"+(readfile.length()/1024));
                    IsCompress(readfile);
                }else if (readfile.isDirectory()){
                    //System.out.println("文件夹 -->"+ readfile.getAbsolutePath());
                    readFile(readfile.getAbsolutePath());
                }
            }
        }
    }

    private static void IsCompress(File file) throws Exception {
        //if (((file.length() / 1024) >= 10) && ((file.length() / 1024) < 30)) {
        //    transformFile(file, 0.8d, 0.4f);
        //} else if ((file.length() / 1024) >= 30) {
        //    transformFile(file, 0.5d, 0.2f);
        //}else if(((file.length() / 1024) > 2) && ((file.length() / 1024) < 10)){
        //    transformFile(file, 0.8d, 0.4f);
        //}
        double d  =file.length() / 1024.0;
        if(d >= 2) {
            System.out.println("压缩前大小"+d);
            transformFile(file, 0.8d, 1f);
        }
    }
//
    public static void readFileTest(String filepath){
        File file = new File(filepath);
        File[] files = file.listFiles();
        for (File file1 : files) {
            System.out.println(file + "\\" + file1.getName());
        }
    }


}

