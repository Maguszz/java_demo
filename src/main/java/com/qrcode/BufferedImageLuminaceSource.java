package com.qrcode;

import com.google.zxing.LuminanceSource;

/**
 * @author Rubbck
 * @date 2020/10/22-10:43
 */
public class BufferedImageLuminaceSource  extends LuminanceSource {
    protected BufferedImageLuminaceSource(int width, int height) {
        super(width, height);
    }

    @Override
    public byte[] getRow(int i, byte[] bytes) {
        return new byte[0];
    }

    @Override
    public byte[] getMatrix() {
        return new byte[0];
    }
}
