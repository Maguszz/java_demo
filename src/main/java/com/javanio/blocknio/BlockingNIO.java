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
 * @date 2020/11/14-10:36
 * 阻塞式的NIO 完成网络通信的三个核心
 * 1  Channel(通道) : 负责连接
 *      java.nio.channels.Channel接口:
 *       |-- SelectableChannel  //不能切换为非阻塞模式
 *          |--SocketChannel
 *          |--ServerSocketChannel
 *          |--DataGramChannel
 *          |--Pipe.SinkChannel
 *          |--Pipe.SourceChannel
 *          ....
 * 2 Buffer(缓冲区): 负责数据的存取
 * 3 Selector(选择器): 是SelectableChannel的多路复用器,用于监控SelectableChannel的使用状况
 */
public class BlockingNIO {
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
        //6 关闭资源
         socketChannel.close();
        fileChannel.close();
        serverSocketChannel.close();
    }
}
