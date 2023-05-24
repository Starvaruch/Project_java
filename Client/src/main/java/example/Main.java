package example;

import example.ciphers.Cesar;
import java.util.Scanner;

public final class Main {

    private Main() { }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String txt = input.nextLine();
        int key = input.nextInt();
        Cesar cesar = new Cesar(txt, key);
        cesar.print();
        input.close();
    }
}
