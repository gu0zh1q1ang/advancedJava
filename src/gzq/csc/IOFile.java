package gzq.csc;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class IOFile {

    public static void main(String[] args) throws IOException {
        generateLargeFile();
    }
    private static void readFile()  throws IOException {
        File textFile = new File("F:\\1.txt");
        FileInputStream fileInputStream = new FileInputStream(textFile);

        byte[] buf = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int realLen = 0;
        while((realLen = fileInputStream.read(buf))!=-1) {
//            System.out.println(new String(buf, StandardCharsets.UTF_8));
            baos.write(buf,0,realLen);
        }
        baos.close();
        fileInputStream.close();

        System.out.println(baos.toString(StandardCharsets.UTF_8.name()));

    }

    private static void generateLargeFile() throws IOException{
//        File output = new File("F:\\2.txt");
//        FileOutputStream fileOutputStream = new FileOutputStream(output);
        LocalDateTime localDateTime = LocalDateTime.now().plusHours(1L).withMinute(0).withSecond(0).withNano(0);
        System.out.println(localDateTime);
    }
}
