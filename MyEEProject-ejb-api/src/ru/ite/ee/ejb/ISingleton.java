package ru.ite.ee.ejb;

import javax.ejb.Remote;

@Remote
public interface ISingleton {
	int incrementAndGet();

	String echo(String msg);
}
