import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
  public static void main(String[] args) {
      int port = 4221;

      try (ServerSocket serverSocket = new ServerSocket(port)) {
          System.out.println("Server started on port " + port);

          // Continuously accept incoming connections
          while (true) {
              try {
                  Socket clientSocket = serverSocket.accept();

                  // Get input/output streams
                  BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                  BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                  // Read the request (not needed to process at this stage, but read it to complete handshake)
                  String line;
                  while ((line = in.readLine()) != null && !line.isEmpty()) {
                      // Just print the request line for debugging if needed
                      System.out.println("Received: " + line);
                  }

                  // Send HTTP 200 OK response
                  out.write("HTTP/1.1 200 OK\r\n\r\n");
                  out.flush();

                  // Close connection
                  clientSocket.close();
              } catch (IOException e) {
                  System.err.println("Error handling client: " + e.getMessage());
              }
          }

      } catch (IOException e) {
          System.err.println("Could not start server: " + e.getMessage());
      }
  }
}
