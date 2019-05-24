import java.io.*;
import java.nio.charset.*;
import java.net.*;

class CCServer {
	public static void main(String args[]) throws Exception {
		if (args.length != 1) {
			System.out.println("usage: java CCServer port");
			System.exit(-1);
		}
		int port = Integer.parseInt(args[0]);

		ServerSocket ssock = new ServerSocket(port);
		System.out.println("listening on port " + port);
		while (true) {
			try {
				Socket csock = ssock.accept();
				System.out.println("Accepted connections: " + csock);

				ConnectingGraph graph = new ConnectingGraph();
				System.out.println("Connecting graph has been initialized.");

				BufferedReader reader = new BufferedReader(new InputStreamReader(csock.getInputStream(), StandardCharsets.UTF_8));

				PrintWriter writer = new PrintWriter(csock.getOutputStream(), true);

				String line = null;
				while ((line = reader.readLine()) != null) {
					// System.out.println(line);
					// process line to integers
					String[] nodes = line.split("\\s");
					int node_0 = Integer.parseInt(nodes[0]);
					int node_1 = Integer.parseInt(nodes[1]);
					System.out.println(node_0);

					graph.connect(node_0, node_1);
				}


				// String line = reader.readLine();
				// System.out.println("Read data: " + line);

				// writer.println(line.toUpperCase());
				// csock.close();


				/*
				 * YOUR CODE GOES HERE - accept connection from server socket - read requests
				 * from connection repeatedly - for each request, compute an output and send a
				 * response - each message has a 4-byte header followed by a payload - the
				 * header is the length of the payload (signed, two's complement, big-endian) -
				 * the payload is a string (UTF-8, big-endian)
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
