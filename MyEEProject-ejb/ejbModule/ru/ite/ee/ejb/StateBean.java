package ru.ite.ee.ejb;

import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class StateBean
 */
@Stateful(mappedName = "stateBean")
@LocalBean
public class StateBean implements IStateBean {

	int count = 0;
	
	public int incrementAndGet() {
		return ++count;
	}
	
	@PostActivate
	@PrePassivate
	@Remove
	public void remove() {
		
	}
}
