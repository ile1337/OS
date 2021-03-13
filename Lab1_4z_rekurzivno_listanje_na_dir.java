import java.io.File;
import java.io.IOException;

public class Lab1_4z_rekurzivno_listanje_na_dir {
    public static void listFile(File file) throws IOException {
           if(!file.exists()){
                return;
            }
        System.out.println(file.getAbsolutePath());
            if(file.isDirectory()){
                File [] files = file.listFiles();
                for(File f:files){
                    listFile(f);
                }
                File file1=new File(file.getName()+".txt");

                file1.createNewFile();

            }
    }
    public static void main(String[] args) throws IOException {
        File f=new File("D:\\Data\\OS\\tmp");
        listFile(f);
    }
}
