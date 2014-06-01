package org.jojo.mwcs.exercise3;

import static org.junit.Assert.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import aufgabe2.ClientAufgabeB;
import Aufgabe1.ClientAufgabea;
import Aufgabe1.QuoterImpl;
import Aufgabe1.ServerImpl;

public class TestAufgabe2 {
	QuoterImpl testquo;
	String testName;
	String testIsn;
	double testPrice;

	@Before
	public void setUp() throws Exception {
		testPrice = RandomUtils.nextDouble(0, 100);
		testIsn = RandomStringUtils.random(5);
		testName = RandomStringUtils.random(8);
		testquo = new QuoterImpl();
		testquo.putStock(testIsn, testPrice);
		testquo.putStock(testName, testPrice);
	}

	@Test
	public void test() {
		ServerImpl server = new ServerImpl();
		server.setQuo(testquo);
		String args[] = { "ORBInitialPort 1050", "ORBInitialHost localhost"};
		server.main(args);
		String[] args2={ "ORBInitialPort 1050", "ORBInitialHost localhost" ,testIsn,testName};
		ClientAufgabeB client=new ClientAufgabeB();
		client.main(args2);
		assertTrue(client.getTestResult1()==client.getTestResult2());
		assertTrue(client.getTestResult1()==testPrice);
	}

}
