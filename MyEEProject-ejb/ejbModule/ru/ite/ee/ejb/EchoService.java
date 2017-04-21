package ru.ite.ee.ejb;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Inject;
import javax.inject.Named;


@Stateless(mappedName = "ki")
@LocalBean
public class EchoService implements IEchoService {

	@Inject
	TestService testService;

	@Inject
	@Named
	double rnd;
	
	int count = 0;
	
	@PostConstruct
	public void init() {
	}
	
	/* (non-Javadoc)
	 * @see ru.ite.ee.ejb.IEchoService#incrementAndGet()
	 */
	@Override
	public int incrementAndGet(){
		System.out.println("----" + Thread.currentThread().getName());
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		return ++count;
	}
	
	/* (non-Javadoc)
	 * @see ru.ite.ee.ejb.IEchoService#echo(java.lang.String)
	 */
	@Override
	public String echo(String msg) {
		testService.test();
		System.out.println(rnd);
		
		//new TypeLiteral<List<Map<String, Date>>>() {};
		
		System.out.println("---->" + testService.getClass().getName());
		return "re:" + msg;
	}
	
	
	
}
