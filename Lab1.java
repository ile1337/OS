import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Lab1 {
    public static final String filePath="D:\\Data\\OS\\tmp";
    public static void main(String[] args) throws IOException {
        File f=new File(filePath);
        if (!f.isDirectory()){
            throw new FileNotFoundException();
        }
        File[] files=f.listFiles();
        File max =files[0];
        for (File file: files) {
            if(file.isDirectory()){
                continue;
            }
            if(file.isFile()){
                if(max.length()<file.length()){
                    max=file;
                }
            }
        }
        System.out.println(max.getAbsolutePath());
    }
}
