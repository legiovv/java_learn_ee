package ru.ite.rmi;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ru.ite.ee.ejb.IEchoService;
import ru.ite.ee.ejb.ISingleton;
import ru.ite.ee.ejb.IStateBean;

public class EntryPoint {
	public static void main(String[] args) throws NamingException, JMSException {
		Context ctx = new InitialContext();
		
		Queue queue = (Queue)ctx.lookup("jms/QueueFromClientToServer");
		ConnectionFactory cf = (ConnectionFactory)ctx.lookup("jms/__defaultConnectionFactory");
		
		//newJms(queue, cf);
		
//		oldJms(queue, cf);
		
		singleton(ctx);
//		stateful(ctx);
//		stateles(ctx);
	}

	private static void singleton(Context ctx) throws NamingException {
		
		ISingleton singleton = (ISingleton)ctx.lookup("singleton");
		
		for(int i = 0; i < 100; i++) {
			new Thread(() -> {
				int res = singleton.incrementAndGet();
				System.out.println(res);
			}).start();
		}
	}
	
	private static void oldJms(Queue queue, ConnectionFactory cf) throws JMSException {
		Connection con = cf.createConnection();
		Session session = con.createSession();
		TextMessage tm = session.createTextMessage();
		
		tm.setText("Hello from client");
		
		session.createProducer(queue).send(tm);
	}

	private static void newJms(Queue queue, ConnectionFactory cf) {
		JMSContext jmsCtx = cf.createContext();
		jmsCtx.createProducer().send(queue, jmsCtx.createTextMessage("hello 22"));
	}
	private static void stateful(Context ctx) throws NamingException {
		IStateBean echoBean = (IStateBean) ctx.lookup("stateBean");
		
		//System.out.println(echoBean.getClass().getName());
		
		//String result = echoBean.echo("hello");
		//System.out.println(result);
		
		for(int i = 0; i < 100; i++) {
			new Thread(() -> {
				int res = echoBean.incrementAndGet();
				System.out.println(res);
			}).start();
		}
	}
	
	private static void stateles(Context ctx) throws NamingException {
		IEchoService echoBean = (IEchoService) ctx.lookup("stateBean");
		
		//System.out.println(echoBean.getClass().getName());
		
		//String result = echoBean.echo("hello");
		//System.out.println(result);
		
		for(int i = 0; i < 100; i++) {
			new Thread(() -> {
				int res = echoBean.incrementAndGet();
				System.out.println(res);
			}).start();
		}
	}
}
