package example;

import example.ciphers.Cesar;
import java.io.*;
import java.net.Socket;

class Servers extends Thread {

    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    Servers(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }

    @Override
    public void run() {
        String word;
        Integer key;
        try {
            word = in.readLine();
            key = Integer.valueOf(in.readLine());
            try {
                Cesar cesar = new Cesar(word, key);
                out.write(cesar.cipher() + "\n");
                out.flush();
            } catch (IOException ignored) {
            }
            try {
                while (true) {
                    word = in.readLine();
                    if (word.equals("stop")) {
                        this.downService();
                        break;
                    }
                    for (Servers x : Server.serverList) {
                        x.send(word);
                    }
                }
            } catch (NullPointerException ignored) {
            }
        } catch (IOException e) {
            this.downService();
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
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
                for (Servers x : Server.serverList) {
                    if (x.equals(this)) {
                        x.interrupt();
                    }
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {
        }
    }
}
