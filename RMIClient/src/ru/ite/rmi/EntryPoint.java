package ru.ite.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ru.ite.ee.ejb.IEchoService;

public class EntryPoint {
	public static void main(String[] args) throws NamingException {
		Context ctx = new InitialContext();
		
		stateles(ctx);
	}
	
	private static void stateles(Context ctx) throws NamingException {
		IEchoService echoBean = (IEchoService) ctx.lookup("ki");
		
		System.out.println(echoBean.getClass().getName());
		
		String result = echoBean.echo("hello");
		System.out.println(result);
		
		for(int i = 0; i < 100; i++) {
			new Thread(() -> {
				int res = echoBean.incrementAndGet();
				System.out.println(res);
			}).start();
		}
	}
}
