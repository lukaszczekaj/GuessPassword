package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
	private static ServerSocket server_socket;
        
        public Server() throws IOException {
            System.out.println("Uruchamiam serwer");
            try
		{
			server_socket = new ServerSocket(12345);
		}
		catch (IOException ieo)
		{
                        System.out.println(ieo.getLocalizedMessage());
			System.exit(-1);
		}

		Thread watekLaczenia = new Thread(new Runnable()
		{
			@Override
			public void run()
			{
                                System.out.println("Serwer uruchomiony.");
				while (ServiceRequests.connections[1] == null)
				{
					try
					{
						Socket socket = server_socket.accept();
						ServiceRequests klient = new ServiceRequests(socket);
						klient.start();
						klient.doKlienta.println("WHO_YOU");
					}
					catch (IOException ieo)
					{
                                                System.out.println(ieo.getLocalizedMessage());
						System.out.println("Brak połączenia z klientem");
					}
				}
			}
		});
		watekLaczenia.start();
        }

}