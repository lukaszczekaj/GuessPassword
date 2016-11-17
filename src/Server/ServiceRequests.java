package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceRequests extends Thread {

    static ServiceRequests connections[] = new ServiceRequests[2]; // tablica graczy
    int nr;
    static int counterConnections = 0;
    public Socket socket = null;
    public PrintWriter doKlienta = null;
    public BufferedReader odKlienta = null;

    public ServiceRequests(Socket s) throws IOException {
        socket = s;
        doKlienta = new PrintWriter(socket.getOutputStream(), true);
        odKlienta = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] == null) {
                connections[i] = this;
                nr = i;
                for (int j = 0; j < 4; j++) //	pionki[j] = i * 4 + j;
                {
                    break;
                }
            }
        }
        doKlienta.println("ID:" + nr);
    }

    public void doWszystkich(String komenda) {
        for (int i = 0; i < 2; i++) {
            if (connections[i] != null) {
                connections[i].doKlienta.println(komenda);
            }
        }
    }

    @Override
    public void run() {
        String komenda;
        while (true) {
            try {
                komenda = odKlienta.readLine();
                String kom[] = komenda.split(":");
                switch (kom[0]) {
                    case "MY_ID":
                        System.out.println("Do serwera dolaczyl: " + kom[1]);
                        counterConnections++;
                        break;
                }
            } catch (IOException ieo) {
                socket = null;
                odKlienta = null;
                doKlienta = null;
                break;
            }
        }
    }
}
