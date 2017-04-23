package ru.ite.ee.ejb;

import javax.ejb.Remote;

@Remote
public interface IStateBean {
	int incrementAndGet();
}
