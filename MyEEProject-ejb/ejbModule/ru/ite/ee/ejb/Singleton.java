package ru.ite.ee.ejb;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Schedules;


/**
 * Session Bean implementation class Singleton
 */
@javax.ejb.Singleton(mappedName = "singleton")
@LocalBean
public class Singleton implements ISingleton {

	int count = 0;
	
    /**
     * Default constructor. 
     */
    public Singleton() {
        System.out.println("+++Singleton " + Thread.currentThread());
    }
    
    void init() {
    	System.out.println("+++--->Singleton.init " + Thread.currentThread());
    }
    
    @Schedules({
    	@Schedule(hour = "*", minute = "*", second = "*/10", persistent = false)
    })
    public void logTime() {
    	System.out.println(new Date());
		
	}
    
    @Override
    public int incrementAndGet() {
    	// TODO Auto-generated method stub
    	return ++count;
    }
    
    @Override
    public String echo(String msg) {
    	return "ree:" + msg;
    }

}
