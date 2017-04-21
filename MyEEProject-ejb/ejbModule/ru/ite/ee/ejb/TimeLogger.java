package ru.ite.ee.ejb;

import java.lang.reflect.Method;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Priority(2000)
public class TimeLogger {

	public TimeLogger() {
		// TODO Auto-generated constructor stub
	}

	@AroundInvoke
	public Object logTime(InvocationContext ic) throws Exception {
		long ts = System.nanoTime();
		Object result = ic.proceed();
		Method method = ic.getMethod();
		Object[] args = ic.getParameters();
		LogTime annotation = method.getAnnotation(LogTime.class);
		
		System.out.println("" +ic.getTarget().getClass().getSimpleName() + ""
				+ " " + method.getName() 
				+ "() ---> ["
				+ result
				+ "] done in " 
				+ (System.nanoTime() - ts) /1000/1000d + "ms"
		);
		
		return result;
	}

}
