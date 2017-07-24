package ijp.proxy;

import java.util.HashMap;

import ijp.Picture;
import ijp.service.Service;
import ijp.service.ServiceFromProperties;

public class MyCacheProxy implements Service{
	
	private Service baseService = null;
	
	HashMap<String,Picture> map = new HashMap<String,Picture>();
	
	public MyCacheProxy(Service baseService) {

		this.baseService = baseService;

		}
	
	public MyCacheProxy() {
		baseService = new ServiceFromProperties("CacheProxy.base_service");
		}
	
	@Override
	public Picture getPicture(String subject,int index) {

		String key=subject+String.valueOf(index);  

		Picture picture=new Picture();

		if (map.get(key)==null){

		picture= baseService.getPicture(subject,index);

		map.put(key, picture);

		}   

		else{

		picture= map.get(key);

		}

		return picture;

		} 

		public String serviceName() {

		return baseService.serviceName();

		}

		}