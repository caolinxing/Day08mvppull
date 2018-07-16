package com.example.day08mvppull.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class SwapInputStreamToString {
    public static String SwapInputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = -1;
        byte[] b = new byte[1024];
        while ((len = inputStream.read(b))!=-1){
            outputStream.write(b,0,len);
        }
        return  new String(outputStream.toByteArray());
    }
}
