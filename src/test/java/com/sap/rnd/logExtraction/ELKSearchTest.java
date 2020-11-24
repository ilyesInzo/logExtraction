package com.sap.rnd.logExtraction;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;

import com.sap.rnd.logExtraction.exception.RequestProcessException;
import com.sap.rnd.logExtraction.pojo.KibanaResponse;

/**
 * Unit test for ELKSearchTest.
 */
public class ELKSearchTest 
{
	
	ELKSearch elkSearch ;
	
	public ELKSearchTest() {
		elkSearch = new ELKSearch();
	}
	
	@Test
	public void testKibanaInfo() {
		

		try {
			KibanaResponse kibanaResponse = elkSearch.getLogs(null,LocalDateTime.now().minusDays(1), LocalDateTime.now(), 0);
			assertNotNull(kibanaResponse);
		} catch (RequestProcessException e) {
			e.printStackTrace();
		}
		
	}
	

	
}
