/*
Извршете го примерот од TwoThreads. Потоа, модифицирајте ја програмата така што ќе користите само една класа за нитки,
 ThreadClassNumbersLetters. Во конструкторот на класата ќе се предаде листа која соодветната инстанца треба да ја отпечати.
  Нитката не треба да ја наследува класата Thread. Однесувањето на новата програма треба да биде исто како на оригиналната,
   односно повторно треба да имате две нитки кои ќе го извршуваат посебно методот run(): едната нитка ќе ги печати првите пет броеви,
    потоа другата ќе ги печати првите пет букви од англиската азбука,
*/

public class TwoThreads {

    public static class ThreadClassNumbersLetters implements Runnable{
        String[] list;

        ThreadClassNumbersLetters(String... lista){
            this.list = lista;
        }
        @Override
        public void run() {
            for(String string : list){
                System.out.println(string);
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadAlphabet = new Thread(new ThreadClassNumbersLetters("A","B","C","D","E"));
        Thread threadNumberrs = new Thread(new ThreadClassNumbersLetters("0","1","2","3","4"));
        threadNumberrs.start();
        threadAlphabet.start();

    }

}
/*
public class TwoThreads {

    public static void main(String[] args) throws InterruptedException {
        ThreadClassLetters letters = new ThreadClassLetters();
        ThreadClassNumbers numbers = new ThreadClassNumbers();
        numbers.start();
        numbers.join();
        letters.start();
        letters.join();

    }

}

class ThreadClassNumbers extends Thread {

    @Override
    public void run() {
        for(int i = 0; i<5;i++) System.out.println(i);
    }
}


class ThreadClassLetters extends Thread {

    @Override
    public void run() {
        for(int i = 0; i<5;i++) System.out.println((char)(i + 65));
    }
}
*/




