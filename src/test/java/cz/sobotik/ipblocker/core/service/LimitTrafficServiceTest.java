package cz.sobotik.ipblocker.core.service;

import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.sobotik.ipblocker.Application;
import cz.sobotik.ipblocker.IntegrationTest;
import cz.sobotik.ipblocker.api.service.LimitTrafficService;
import cz.sobotik.ipblocker.core.SystemException;
import cz.sobotik.ipblocker.core.util.CsvLineReader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Basic test to check whether {@link Application} is usable as expected.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@IntegrationTest
public class LimitTrafficServiceTest {

	@Autowired
	private LimitTrafficService limitTrafficService;

	@Autowired
	private CsvLineReader csvLineReader;


	@PostConstruct
	public void initTests() throws Exception {
		//csvLineReader.slpitAndMapLine("\"34614272\",\"34614527\",\"CZ\",\"Czech Republic\",\"Praha, Hlavni mesto\",\"Prague\",\"50.088040\",\"14.420760\"");
	}

	@Test(expected = SystemException.class)
	public void ipv6AddressesNotSupported() throws Exception {
		InetAddress ipAddress = InetAddress.getByName("0:0:0:0:0:0:0:1");
		boolean isvalid = limitTrafficService.isValid(ipAddress);
	}

	@Test
	public void addressIsValid() throws Exception {
		InetAddress ipAddress = InetAddress.getByName("2.16.44.1");
		boolean isvalid = limitTrafficService.isValid(ipAddress);
		assertTrue(isvalid);
	}

	@Test
	public void cityAddressLimitExceeded() throws Exception {
		InetAddress ipAddress = InetAddress.getByName("2.16.44.1");
		boolean isvalid = true;
		int cityLimit = 10;
		while (cityLimit > 0) {
			isvalid = limitTrafficService.isValid(ipAddress);
			cityLimit--;
		}

		assertFalse(isvalid);
	}

	@Test
	public void regionAddressLimitExceeded() throws Exception {
		// FIXME limits has to be configurable (ie from configuration file)
		// after that we can test it here
		// or test it on bigger portion of data
	}

	@Test
	public void countryAddressLimitExeeded() throws Exception {
		// FIXME limits has to be configurable (ie from configuration file)
		// after that we can test it here
		// or test it on bigger portion of data
	}

	@Test
	public void traficLimittTimeUNLock() throws Exception {
		// FIXME duration of time lock has to be configurable after that we can write test to unlock limit
	}

}
