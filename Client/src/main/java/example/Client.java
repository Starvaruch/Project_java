package example;

public final class Client {

    private static String ipAddr = "localhost";
    private static int port = 8080;

    private Client() {
    }

    public static void main(String[] args) {
        new Clients(ipAddr, port);
    }
}
