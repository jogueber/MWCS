package server;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Random;

import com.chaosinmotion.asn1.AsnFatalException;
import com.chaosinmotion.asn1.BerInputStream;
import com.chaosinmotion.asn1.BerOutputStream;
import com.turkcelltech.jac.PrintableString;

import server.communication.PDU;
import server.communication.Request;
import server.communication.Response;
import server.communication.Verification;


/**
 * Implementation of a simple server.
 * 
 */
public class Server {
	// counter for unique numbering of clients
	private static int idCounter = 1;
	
	final static int OK = 0;
	final static int FAIL = 1;
	
	private static enum Option {ENC, DEC, NONE}; 
	private static Option opt = Option.NONE; 

	/**
	 * Initializes the server and accepts incoming connections.
	 * 
	 * @param args
	 *            no arguments required
	 */
	public static void main(String[] args) {
		ServerSocket socket = null;
		if(args.length == 1) {
			if(args[0].equals("-enc")) {
				opt = Option.ENC;
			} else if(args[0].equals("-dec")) {
				opt = Option.DEC;
			}			
		}
		System.out.println("Server started.\n"
				+ " Note: output labeled with <= and input with =>\n"
				+ "-----------------------------------------------");
		try {
			socket = new ServerSocket(8080);
			while (true) {
				Socket sock = socket.accept();
				Runnable handler = new ClientHandler(sock);
				new Thread(handler).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Generates and returns an unique client id.
	 * 
	 * @return unique client id
	 */
	private static synchronized int createClientID() {
		return idCounter++;
	}

	/**
	 * A handler for a client being connected to the server.
	 * 
	 */
	private static class ClientHandler implements Runnable {

		/** the socket */
		private Socket sock;
		/** unique id of client */
		private int id;

		/**
		 * Creates a new client handler for the specified socket.
		 * 
		 * @param sock
		 *            the socket
		 */
		private ClientHandler(Socket sock) {
			this.sock = sock;
		}

		@Override
		public void run() {
			id = createClientID();
			try {
				// read data from client
				int buffSize = sock.getReceiveBufferSize();
				byte[] buff = new byte[buffSize];
				int numBytes = sock.getInputStream().read(buff);
				
				// input and output streams for ans.1
				ByteArrayInputStream inputStream = new ByteArrayInputStream(buff);
				BerInputStream in = new BerInputStream(inputStream);
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();		
				BerOutputStream out = new BerOutputStream(outStream);
				
				// get request
				PDU request = new PDU("request");
				request.decode(in);
				if(request.getCurrentChoice() instanceof Request) {
					// message is request
					long value = ( ( Request) request.getCurrentChoice()).value.getValue();
					System.out.println("<= client{" + id + "}: " + value);
					switch (opt) {
					case DEC:
						printPDU(request);
						break;
					case ENC:
						printHex(buff, numBytes);
						break;
					default:
						break;
					}
					// create answer
					PDU answer = new PDU("response");
					answer.response.value.setValue(value+1);
					String randomString = randomString();
					answer.response.randStr.setValue(randomString);
					answer.encode(out);
					// printHex(outStream.toByteArray());
					buff = outStream.toByteArray();
					sock.getOutputStream().write(buff);
					System.out.println("=> client{" + id + "}: "+ answer.response.value.getValue() + " and "+answer.response.randStr.getValue());
					switch (opt) {
					case DEC:
						printPDU(answer);
						break;
					case ENC:
						printHex(buff, buff.length);
						break;
					default:
						break;
					}
					// wait for response
					numBytes = sock.getInputStream().read(buff);
					inputStream = new ByteArrayInputStream(buff);
					in = new BerInputStream(inputStream);
					PDU response = new PDU("response");
					response.decode(in);
					if(response.getCurrentChoice() instanceof Response) {
						String swapped = ((Response) response.getCurrentChoice()).randStr.getValue();
						System.out.println("<= client{" + id + "}: "+swapped.toString());
						switch (opt) {
						case DEC:
							printPDU(response);
							break;
						case ENC:
							printHex(buff, numBytes);
							break;
						default:
							break;
						}
						// check if answer is correct
						StringBuilder sbSwap = new StringBuilder();
						for(char c : swapped.toCharArray()) {
							if (Character.isLowerCase(c)) {
								sbSwap.append(Character.toUpperCase(c));
							} else if (Character.isUpperCase(c)) {
								sbSwap.append(Character.toLowerCase(c));
							} else {
								sbSwap.append(c);
							}
						}
						
						PDU verification = new PDU("verification");
						
						// send FAIL or OK 
						outStream = new ByteArrayOutputStream();		
						out = new BerOutputStream(outStream);
						if(sbSwap.toString().equals(randomString)) {
							verification.verification.result.setValue(OK);
						} else {
							verification.verification.result.setValue(FAIL);
						}
						verification.encode(out);
						buff = outStream.toByteArray();
						sock.getOutputStream().write(buff);
						if(verification.verification.result.getValue() == OK) {
							System.out.println("=> client{" + id + "}: OK");
						} else if (verification.verification.result.getValue() == FAIL) {
							System.out.println("=> client{" + id + "}: FAIL");
						}
						
						switch (opt) {
						case DEC:
							printPDU(verification);
							break;
						case ENC:
							printHex(buff, buff.length);
							break;
						default:
							break;
						}
						sock.getOutputStream().flush();
					} else {
						System.out.println("Received unexpected message, expected response from client{"+id+"}.");	
					}
				} else {
					System.out.println("Received unexpected message, expected request from client{"+id+"}.");
				}
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * Generates a random string matching the regular expression
	 * [a-zA-Z0-9]{10,20}
	 * 
	 * @return random string
	 */
	private static String randomString() {
		String alphabet = new String(
				"0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
		int n = alphabet.length();
		StringBuffer result = new StringBuffer();
		Random r = new Random();
		int length = r.nextInt(11) + 10;
		for (int i = 0; i < length; i++) {
			result.append(alphabet.charAt(r.nextInt(n)));
		}
		return result.toString();
	}

	/**
	 * Prints hex values of given array. 
	 * 
	 * @param coded the byte array containing the BER coded data
	 * @param length number of bytes to print
	 */
	public static void printHex(byte[] coded, int length) 
	{
		System.out.print("   [enc: ");
	    String hexDigits = "0123456789ABCDEF";
	    for (int i=0; i<length; i++) {
	      int c = coded[i];
	      if (c < 0) c += 256;
	      int hex1 = c & 0xF;
	      int hex2 = c >> 4;
	      System.out.print(hexDigits.substring(hex2,hex2+1));
	      System.out.print(hexDigits.substring(hex1,hex1+1) + " ");
	    }
	    System.out.println("]");
	  }
	
	/**
	 * Prints the specified PDU in human readable format. 
	 * 
	 * @param pdu the PDU to print
	 */
	public static void printPDU(PDU pdu) {
		try {
			if(pdu.getCurrentChoice() instanceof Request) {
				System.out.println("   [dec: PDU ::= request : { value "+pdu.request.value.getValue()+" }]");
			} else if (pdu.getCurrentChoice() instanceof Response) {
				System.out.println("   [dec: PDU ::= response : { value "+pdu.response.value.getValue()+" , randStr \""+pdu.response.randStr.getValue()+"\" }]");
			} else if (pdu.getCurrentChoice() instanceof Verification) {
				System.out.println("   [dec: PDU ::= verification : { result "+pdu.verification.result.getValue()+" }]");
			}
		} catch (AsnFatalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
