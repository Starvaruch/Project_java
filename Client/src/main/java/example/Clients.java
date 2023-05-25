package example;

import java.io.*;
import java.net.Socket;

class Clients {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    private BufferedReader inputUser;
    private String addr;
    private int port;
    private String word;
    private int key;
    private final Object lock = new Object();

    Clients(String addr, int port) {
        this.addr = addr;
        this.port = port;
        try {
            this.socket = new Socket(addr, port);
        } catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            inputUser = new BufferedReader(new InputStreamReader(System.in));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.pressWord();
            new ReadMsg().start();
            this.pressKey();
        } catch (IOException e) {
            Clients.this.downService();
        }
    }

    private void pressWord() {
        System.out.print("Press your word: ");
        try {
            word = inputUser.readLine();
            out.write(word + "\n");
            out.flush();
        } catch (IOException ignored) {
        }
    }

    private void pressKey() {
        System.out.print("Press your key: ");
        try {
            synchronized (lock) {
                key = Integer.parseInt(inputUser.readLine());
            }
            out.write(Integer.toString(key));
            out.write('\n');
            out.flush();
        } catch (IOException ignored) {
        }
    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {
        }
    }

    private final class ReadMsg extends Thread {

        private ReadMsg() {
        }

        @Override
        public void run() {
            String str;
            try {
                while (true) {
                    str = in.readLine();
                    System.out.println("Encrypted text: " + str);
                    Clients.this.downService();
                    break;
                }
            } catch (IOException e) {
                Clients.this.downService();
            }
        }
    }
}
