package com.base64;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @author Rubbck
 * @date 2020/9/27-15:06
 */
public class Test {
    static InputStream in = null;

    public static void main(String[] args) throws IOException {
        try {

            in = new FileInputStream("C:\\Users\\admin\\Desktop\\test\\7.png");
            int a = in.available();
            //668字节
            byte[] data = new byte[in.available()];
            System.out.println(a);
            int read = in.read(data);
            System.out.println(read);
            System.out.println(Arrays.toString(data));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            in.close();
        }

    }
}
