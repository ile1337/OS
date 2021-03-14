import java.io.*;

public class Lab1_zad6 {
   /* public static String readTextFile(String path)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
        String line = null;
        StringBuilder sb= new StringBuilder();
        while ((line=br.readLine())!=null){
            sb.append(line).append("\n");
        }
        br.close();
        return sb.toString();
    }*/
    public static void TextFileCopy(String from,String to) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(from));
        BufferedWriter bw = new BufferedWriter(new FileWriter(to));

        String line=null;

        while ((line = br.readLine()) != null)
        {
            Integer ctr=0;
            if(line.contains("\n")){
                ctr++;
                System.out.println(ctr);
            }
            if(line.contains("0")||line.contains("1")||line.contains("2")||line.contains("3")||line.contains("4")||line.contains("5")||line.contains("6")||line.contains("7")||line.contains("8")||line.contains("9")){
                bw.write(line + "\n");
                continue;
            }
            if(ctr == 9){
                br.close();
                bw.close();
                System.out.println("here");
                break;
            }

        }
        br.close();
        bw.close();

    }
    public static void main(String[] args) throws IOException {
        File file1 = new File("D:\\Data\\OS\\tmp\\folder1\\folder2\\text1.txt");
        File file2 = new File("D:\\Data\\OS\\tmp\\folder1\\folder2\\text2.txt");
        TextFileCopy(file1.getAbsolutePath(),file2.getAbsolutePath());
    }
}
