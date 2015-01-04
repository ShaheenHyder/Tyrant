package tyrant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

class TyrantMap{
	private static final int OPERATION_PREFIX = 0xC8; 
	private static final int PUT_OPERATION = 0x10;
	private static final int GET_OPERATION = 0x30;
	private Socket socket;
	private DataOutputStream writer;
	private DataInputStream reader;
	
	public void put(byte[] key,byte[] value) throws IOException{
		writer.write(OPERATION_PREFIX);
		writer.write(PUT_OPERATION);
		writer.writeInt(key.length);
		writer.writeInt(value.length);
		writer.write(key);
		writer.write(value);
		int status = reader.read();
		if(status!=0)
			throw new RuntimeException();
	}
	public byte[] get(byte[] key) throws IOException {
		writer.write(OPERATION_PREFIX);
		writer.write(GET_OPERATION);
		writer.writeInt(key.length);
		writer.write(key);
		int status = reader.read();
		if(status!=0)
			throw new RuntimeException();
		int length = reader.readInt();
		byte[] result = new byte[length];
		reader.read(result);//TODO read longer values
		return result;
	}
	public void open() throws IOException{
		socket = new  Socket("localhost",8081);
		writer = new DataOutputStream(socket.getOutputStream());
		reader = new DataInputStream(socket.getInputStream());
	}
	public void close() throws IOException{
		socket.close();
	}
}

