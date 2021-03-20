/*
Да се имплементира класа FileScanner која што ќе се однесува како thread. Во класата FileScanner се чуваат податоци за : - името на директориумот што треба да се скенира - статичка променлива counter што ќе брои колку нишки од класата FileScanner ќе се креираат Во класата FileScanner да се имплементираа статички методот што ќе печати информации за некоја датотека од следниот формат:

dir: lab1 - reshenija 100 (dir за директориуми, името на директориумот и број на фајлови)

file: spisok.pdf 29198 (file за обични фајлови, име на фајл и големина)

Дополнително да се преоптовари методот run() од класата Thread, така што ќе печати информации за директориумот за којшто е повикан. Доколку во директориумот има други под директориуми, да се креира нова нишка од тип FileScanner што ќе ги прави истите работи како и претходно за фајловите/директориумите што се наоѓаат во тие директориуми (рекурзивно).

На крај да се испечати вредноста на counter-от, односно колку вкупно нишки биле креирани.  Користете го следниот почетен код.
*/

import java.io.File;


public class FileScanner extends Thread  {

    private String FTS;
    private static Long counter = 0L;

    public FileScanner (String fileToScan) {
        this.FTS =fileToScan;
        synchronized(this) {
            counter++;
        }
    }

    public static void printInfo(File f)  {
        if(f.isDirectory()) {
            System.out.printf("dir: %s - solutions %d\n", f.getAbsolutePath(), getDirectorySize(f));
        } else if(f.isFile()) {
            System.out.printf("file: %s %d\n", f.getAbsolutePath(), f.length());
        } else {
            System.err.println("ERROR");
        }
    }

    public static long getDirectorySize(File f) {
        if(!f.isDirectory())
            return 0;

        long length = 0;

        for(File file : f.listFiles()) {
            if(file.isFile())
                length+=file.length();
            else if (file.isDirectory())
                length+=getDirectorySize(file);
        }

        return length;
    }

    public static Long getCounter () {
        return counter;
    }


    public void run() {

        File file = new File(FTS);

        File [] files = file.listFiles();

        for (File f : files) {

            if(!f.isDirectory()) printInfo(f);

            if(f.isDirectory()) {
                FileScanner newDirectoryThread = new FileScanner(f.getAbsolutePath());
                newDirectoryThread.start();

                try {
                    newDirectoryThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void main (String [] args) {
        String FILE_TO_SCAN = "C:\\Users\\jakim\\Desktop\\SI";

        FileScanner fileScanner = new FileScanner(FILE_TO_SCAN);

        fileScanner.start();

        try {
            fileScanner.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getCounter());

    }
}
