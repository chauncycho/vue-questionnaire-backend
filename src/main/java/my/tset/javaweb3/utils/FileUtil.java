package my.tset.javaweb3.utils;

import java.io.*;

public class FileUtil {
    public static void upload(InputStream is, String path, String fileName){
        String filePath;
        if(path.charAt(path.length()-1) == '/'){
            filePath = path + fileName;
        }else{
            filePath = path + "/" + fileName;
        }

        File dict = new File(path);
        if (!dict.exists()){
            dict.mkdirs();
        }

        File file = new File(filePath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] bytes = new byte[10*1024];
            int length=0;
            while((length=is.read(bytes))!=-1){
                fos.write(bytes,0,length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
