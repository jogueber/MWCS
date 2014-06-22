package org.jojo.mwcs.exercise3;

import static org.junit.Assert.*;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;

import Aufgabe1.ClientAufgabea;
import Aufgabe1.QuoterImpl;
import Aufgabe1.ServerImpl;

public class TestAufgabe1 {
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
		String args[] = { "1050", "localhost" };
		server.main(args);
		ClientAufgabea client = new ClientAufgabea();
		
		client.main(args);
		assertTrue(client.getResult1() == client.getResult2());
		assertTrue(testPrice == client.getResult1());
	}

}
