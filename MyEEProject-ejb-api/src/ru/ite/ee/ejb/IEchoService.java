package ru.ite.ee.ejb;

import javax.ejb.Remote;

@Remote
public interface IEchoService {

	int incrementAndGet();

	String echo(String msg);

}