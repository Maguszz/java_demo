package com.compressFiles;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author Rubbck
 * @date 2020/9/15-11:23
 * 文件压缩
 */
public class ZipUtil {
    public static void main(String[] args) throws Exception {
        //ZipUtil.ZipFileBuffer();
       // ZipUtil.ZipFileBufferWithBufferStream();
        ZipUtil.ZipFileBufferWithChannel();
    }
    private static String  ZIP_FILE = "C:/Users/admin/Desktop/test/6.zip";
    private static String  JPG_FILE = "C:/Users/admin/Desktop/test/6.jpg";
    private static String  FILE_NAME = "myJpg";
    private static Long  FILE_SIZE = 2048l;
    public static void ZipFileBuffer() throws IOException {
        File  zipFile = new File(ZIP_FILE);
        ZipOutputStream zipOutputStream = null;
        InputStream input = null;

        try {
            //压缩文件输出名称
             zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
             long startTime =  System.currentTimeMillis();
                for (int i = 1; i <10 ; i++) {
                    input = new FileInputStream(JPG_FILE);
                    //压缩的每个文件名称
                    zipOutputStream.putNextEntry(new ZipEntry(FILE_NAME+i));
                    int temp =0;
                    while((temp=input.read()) != -1){
                        //压缩文件写出
                        zipOutputStream.write(temp);
                    }
                }
            long endTime =  System.currentTimeMillis();
            System.out.println(endTime-startTime); //19447 大约20秒
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  finally {
            if(input!=null){
                input.close();
            }
            if(zipOutputStream!=null){
                zipOutputStream.close();
            }
        }
    }

    public static void ZipFileBufferWithBufferStream() throws IOException {
        File  zipFile = new File(ZIP_FILE);
        ZipOutputStream zipOutputStream = null;
        BufferedInputStream bufferedInputStream =null;
        BufferedOutputStream bufferedOutputStream = null;


        try {
            long startTime =  System.currentTimeMillis();
            //压缩文件输出名称
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            bufferedOutputStream = new BufferedOutputStream(zipOutputStream);

            for (int i = 1; i <10 ; i++) {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(JPG_FILE));
                //压缩的每个文件名称
                zipOutputStream.putNextEntry(new ZipEntry(FILE_NAME+i));
                int temp =0;
                while((temp=bufferedInputStream.read()) != -1){
                    //压缩文件写出
                    bufferedOutputStream.write(temp);
                }
            }
            long endTime =  System.currentTimeMillis();
            System.out.println(endTime-startTime); //418毫秒
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  finally {
            if(bufferedInputStream!=null){
                bufferedInputStream.close();
            }
            if(bufferedOutputStream!=null){
                bufferedOutputStream.close();
            }
            if(zipOutputStream!=null){
                zipOutputStream.close();
            }
        }
    }

    public static void ZipFileBufferWithChannel() throws IOException {
        File  zipFile = new File(ZIP_FILE);
        ZipOutputStream zipOutputStream =null;
        WritableByteChannel writableByteChannel = null;
        long startTime =  System.currentTimeMillis();
        try {
             zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            //使用管道
            writableByteChannel = Channels.newChannel(zipOutputStream);
            for (int i = 1; i <10 ; i++) {
                    //压缩的每个文件名称
                    FileChannel fileChannel = new  FileInputStream(JPG_FILE).getChannel();
                    zipOutputStream.putNextEntry(new ZipEntry(FILE_NAME+i));
                    fileChannel.transferTo(0,FILE_SIZE,writableByteChannel);
                }
            long endTime =  System.currentTimeMillis();
            System.out.println(endTime-startTime); //29毫秒
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(zipOutputStream!=null){
                zipOutputStream.close();
            }
        }
    }

    public static void ZipFileBUfferWithMap() throws IOException {
        File  zipFile = new File(ZIP_FILE);
        ZipOutputStream zipOutputStream =null;
        WritableByteChannel writableByteChannel = null;
        long startTime =  System.currentTimeMillis();
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            //使用管道
            writableByteChannel = Channels.newChannel(zipOutputStream);
            for (int i = 1; i <10 ; i++) {
                //压缩的每个文件名称
                zipOutputStream.putNextEntry(new ZipEntry(FILE_NAME+i));
                MappedByteBuffer mappedByteBuffer = new RandomAccessFile(JPG_FILE,"r").getChannel().map(FileChannel.MapMode.READ_ONLY,0,FILE_SIZE);
                writableByteChannel.write(mappedByteBuffer);
            }
            long endTime =  System.currentTimeMillis();
            System.out.println(endTime-startTime); //29毫秒
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(zipOutputStream!=null){
                zipOutputStream.close();
            }
        }
    }
    public static void ZipFileWithPipe() throws IOException {
        File  zipFile = new File(ZIP_FILE);
        ZipOutputStream zipOutputStream =null;
        WritableByteChannel writableByteChannel = null;
        long startTime =  System.currentTimeMillis();
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            //使用管道
            writableByteChannel = Channels.newChannel(zipOutputStream);
            for (int i = 1; i <10 ; i++) {
                //压缩的每个文件名称
                zipOutputStream.putNextEntry(new ZipEntry(FILE_NAME+i));
                MappedByteBuffer mappedByteBuffer = new RandomAccessFile(JPG_FILE,"r").getChannel().map(FileChannel.MapMode.READ_ONLY,0,FILE_SIZE);
                writableByteChannel.write(mappedByteBuffer);
            }
            long endTime =  System.currentTimeMillis();
            System.out.println(endTime-startTime); //29毫秒
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(zipOutputStream!=null){
                zipOutputStream.close();
            }
        }
    }
}
