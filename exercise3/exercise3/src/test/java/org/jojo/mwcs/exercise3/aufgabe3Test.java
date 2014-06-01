package org.jojo.mwcs.exercise3;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

import callback.Client_Handler.Stock;
import aufgabe3.Client;
import aufgabe3.Server;
import aufgabe3.ServerRegisterImpl;
import junit.framework.TestCase;

public class aufgabe3Test extends TestCase {
	String testName;
	ServerRegisterImpl clientStock;
	Stock testStock;

	@Before
	public void setup() {
		double testPrice = RandomUtils.nextDouble(0, 100);
		String testIsn = RandomStringUtils.random(5);
		testName = RandomStringUtils.random(8);
		testStock = new Stock();
		testStock.name = testName;
		testStock.price = testPrice;
		testStock.isn = testIsn;
		clientStock = new ServerRegisterImpl();
	}

	protected static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testAufgabe3() throws InvalidName, AdapterInactive {
		Server serv = new Server();
		serv.setRegist(clientStock);
		clientStock.addStock(testStock);
		Client client = new Client();
		String args[] = { "ORBInitialPort 1050", "ORBInitialHost localhost" };
		serv.main(args);
		String args2[] = { "ORBInitialPort 1050", "ORBInitialHost localhost",
				testName };
		client.main(args2);
		clientStock.refresh(testStock);
		assertEquals(client.getImp().getRecived().name, testName);
	}

}
