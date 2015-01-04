package tyrant;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;


public class TyrantTest {
	TyrantMap tyrant ;
	
	@Test public void getRetrivesWhatWasPut() throws IOException{
		byte[] key = new byte[]{'k','y','e'};
		byte[] value = new byte[]{'v','a','l','u','e'}; 
		tyrant = new TyrantMap();
		tyrant.open();
		tyrant.put(key,value);
		assertArrayEquals(value, tyrant.get(key));
		tyrant.close();
	}
	
	@Before public void connect() throws IOException{
		tyrant = new TyrantMap();
		tyrant.open();
	}
	
	@After public void disconnect() throws IOException{
		tyrant.close();
	}
}
