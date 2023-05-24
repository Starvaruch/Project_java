package example.ciphers;

import example.Encoder;

public class Cesar implements Encoder {
    private final String txt;
    private final int key;

    public Cesar(String txt, int key) {
        this.txt = txt;
        this.key = key;
    }

    @Override
    public String cipher() {
        StringBuilder txtcipher = new StringBuilder(txt.length());
        char ans;
        for (int i = 0; i < txt.length(); ++i) {
            System.out.println(i);
            char txti = txt.charAt(i);
            if (Character.isUpperCase(txti)) {
                ans = (char) (((int) txti + key - 65) % 26 + 65);
            } else if (Character.isLowerCase(txti)) {
                ans = (char) (((int) txti + key - 97) % 26 + 97);
            } else {
                return "Not word, use only letters";
            }
            txtcipher.append(ans);
        }
        return txtcipher.toString();
    }

    public void print() {
        System.out.println(cipher());
    }
}

