package com.javanio.blocknio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Rubbck
 * @time  2020/11/15-1:06
 * 有返回结果的阻塞式NIO的
 */
public class BlockingNIOWithResult {
    //客户端
    @Test
    public void client() throws IOException {
        //1 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        //图片服务
        FileChannel fileChannel = FileChannel.open(Paths.get("D:/eos/project/fileUpload/empResource/3/esignImg/a03be6996d4ea431016d5d1fa99d2da9_crop.jpg"), StandardOpenOption.READ);

        //2 分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //3 读取本地文件,并且发送到服务器
        while(fileChannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        socketChannel.shutdownOutput();
        //接收服务端的反馈
        int len  = 0;
        while((len = socketChannel.read(byteBuffer))!=-1){
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(),0,len));
            byteBuffer.clear();
        }
        //4 关闭通道
        fileChannel.close();
        socketChannel.close();

    }
    //服务端
    @Test
    public void server() throws IOException {
        //1 获取通达
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        FileChannel fileChannel = FileChannel.open(Paths.get("D:/eos/project/fileUpload/empResource/3/esignImg/34.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        //2 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9898));
        //3 获取客户连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        //4 缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //5 接收客户端保存到本地
        while(socketChannel.read(byteBuffer)!=-1){
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        //发送反馈给客户端
        byteBuffer.put("服务端接收成功".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        //6 关闭资源
        socketChannel.close();
        fileChannel.close();
        serverSocketChannel.close();
    }
}
