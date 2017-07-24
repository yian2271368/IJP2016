// IJP Assignment 1, Version 6.1.0, 05 Oct 2016
package ijp.test;

import static org.junit.Assert.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import org.junit.Test;

import ijp.Picture;
import ijp.proxy.BrokenCacheProxy;
import ijp.proxy.CacheProxy;
import ijp.service.Service;

/**
 * A template for testing a cache proxy for the PictureViewer application.
 * 
 * @author YOUR NAME HERE
 * @version YOUR VERSION HERE
 */
public class MyCacheProxyTest implements Service {
	
	/**
	 * Test that requests for the same subject and index return the same image.
	 */
	@Test
	public void equalityTest() {

		Service proxy = new CacheProxy(this);
		Picture firstPicture = proxy.getPicture("equalityTest",2);
		Picture secondPicture = proxy.getPicture("equalityTest",2);
		assertTrue(
				"different picture returned for same subject (and index)",
				firstPicture.equals(secondPicture));
	}
	
	@Test
	public void indexTestA(){
		Service proxy = new BrokenCacheProxy(this);
		Picture picture1=proxy.getPicture("indextest", 2);
		
		assertTrue("wrong index", picture1.index()==2);
	}
	
	@Test
	public void PicturetestB(){
		Service proxy = new BrokenCacheProxy(this);
		Picture picture = proxy.getPicture("test",1);
		//System.out.println(picture.description()+picture.index()+picture.subject());
		assertFalse(
				"No service error", 
				picture.source() == "NoService" 
				);
		
		
		
		
		//System.out.println("dsadsadsadsa" + picture.source());
	}
	
	

	/**
	 * Return a picture from the simulated service.
	 * This service simply returns an empty picture every time that it called.
	 * Note that a <em>different</em> object is returned each time, even if the
	 * subject and index are the same.
	 *
	 * @param subject the requested subject
	 * @param index the index of the picture within all pictures for the requested topic
	 *
	 * @return the picture
	 */
	@Override
	public Picture getPicture(String subject, int index) {
		return new Picture((BufferedImage)null, subject ,serviceName(), index);
	}
	
	/**
	 * Return a textual name to identify the simulated service.
	 *
	 * @return the name of the service ("cacheProxyTest")
	 */
	@Override
	public String serviceName() {
		return "MyCacheProxyTest";
	}
}
