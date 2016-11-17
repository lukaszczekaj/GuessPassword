package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import Server.Server;

@SuppressWarnings("unused")
public class Client {

    private static final long serialVersionUID = 1L;

    int globalID;
    String ID = "aaa";

    Socket socket;
    public PrintWriter doSerwera = null;
    public BufferedReader odSerwera = null;

    Thread watek = new Thread(new Runnable() {
        String komenda;

        @Override
        public void run() {
            while (true) {
                String kom[];
                try {
                    komenda = odSerwera.readLine();
                    System.out.println(komenda);
                    kom = komenda.split(":");
                    switch (kom[0]) {
                        case "WHO_YOU":
                            doSerwera.println("MY_ID:" + ID);
                            break;
                        case "ID":
                            globalID = Byte.parseByte(kom[1]);
                            System.out.println(globalID);
                            break;
                    }
                } catch (IOException ieo) {
                    System.out.println(ieo.getMessage());
                }
            }
        }
    });

    public Client() throws IOException {
        socket = null;
        System.out.println("Uruchamianie modulu klienta");
        try {
            socket = new Socket("127.0.0.1", 12345);
            System.out.println("Polaczenie nawiazane");
            doSerwera = new PrintWriter(socket.getOutputStream(), true);
            odSerwera = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            watek.start();
        } catch (NullPointerException e) {
            System.out.println("Null Pointer");
        } catch (UnknownHostException ex) {
            System.out.println("Nie można nawiązać połączenia z serwerem.");
            //   JOptionPane.showMessageDialog(null, "Wyjatek");
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage() + " - widocznie brak serwera.");
            new Server();
        }
    }

    public static void main(String[] args) throws IOException {
        new Client();
    }
}
