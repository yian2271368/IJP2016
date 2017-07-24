// IJP Assignment 1, Version 6.1.0, 05 Oct 2016
package ijp.controller;

import java.util.HashMap;

import ijp.Picture;
import ijp.service.Service;
import ijp.service.ServiceFromProperties;
import ijp.utils.Properties;
import ijp.view.View;
import ijp.view.ViewFromProperties;

/**
 * A template for implementing a controller for the PictureViewer application.
 * 
 * @author damon(qiuyu) tian
 * @version 
 */
public class MyController implements Controller {

	private View view;
	private Service service;
	private int selection;


	/**
	 * Start the controller.
	 */

	public void getnames(){
		String mountnames=Properties.get("MyController.subjects");
		String[] spiltednames=mountnames.split(",");
		
		for(int i=0;i<spiltednames.length;i++)
		{
			spiltednames[i] =spiltednames[i].trim();
			addSubject(spiltednames[i]);
		}
	}
	
	public void start() {

		view = new ViewFromProperties(this);
		service = new ServiceFromProperties();
		 
		getnames();
		view.start();
	}

	
	private HashMap<Integer,String> map = new HashMap<Integer,String>();
	
	public void addSubject(String mount){
		selection = view.addSelection(mount);
		map.put(selection, mount);
		};
		
		
		
		
	/**
	 * Handle the specified selection from the interface.
	 *
	 * @param selectionID the id of the selected item
	 */
	public void select(int selectionID) {
		
		Picture picture = new Picture();

		picture=service.getPicture(map.get(selectionID), selectionID);
		
			
		
		view.showPicture(picture);
	}
}
