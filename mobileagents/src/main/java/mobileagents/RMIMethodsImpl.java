package mobileagents;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.google.common.reflect.Invokable;

public class RMIMethodsImpl implements RMIMethods {

	protected RMIMethodsImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Container invokeCompile(CompiledContainer container) {
		try {
			DateTime start = DateTime.now();
			container.setResult(new ArrayList<>());
			Method[] t1 = container.getA().getMethods();
			for (Method met : t1) {
				String name = met.getName();
				Object[] b = container.getMethodsToInvoke().get(name);
				Object result;
				if (Modifier.isStatic(met.getModifiers())
						&& met.getReturnType().equals(Void.TYPE)) {
					met.invoke(null, b);
					continue;
				}
				if (Modifier.isStatic(met.getModifiers())) {
					result = met.invoke(null, b);
					container.getResult().add(result);
				} else {
					result = met.invoke(container.getInstance(), b);
					container.getResult().add(result);
				}
			}
			DateTime end = DateTime.now();
			org.joda.time.Duration dur = new org.joda.time.Duration(start, end);
			container.setBilling(dur.getStandardSeconds());
			return container;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Container invokeRaw(SourceContainer container) {
		try {
			DateTime start = DateTime.now();
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager fileManager = compiler
					.getStandardFileManager(null, Locale.getDefault(),
							StandardCharsets.UTF_8);
			compiler.getTask(null, null, null, container.getArgs(), null,
					fileManager.getJavaFileObjects(container.getSources()))
					.call();
			String method;
			Class t;
			try {
				t = Class.forName(container.getClassName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

			Method[] methods = t.getMethods();

			for (Method m : methods) {
				String name = m.getName();
				Object[] b = container.getMethodsToInvoke().get(name);
				Object result;
				if (Modifier.isStatic(m.getModifiers())
						&& m.getReturnType().equals(Void.TYPE)) {
					m.invoke(null, b);
					continue;
				}
				if (Modifier.isStatic(m.getModifiers())) {
					result = m.invoke(null, b);
					container.getResult().add(result);
				} else {
					result = m.invoke(container.getInstance(), b);
					container.getResult().add(result);
				}
			}
			DateTime end = DateTime.now();
			org.joda.time.Duration dur = new org.joda.time.Duration(start, end);
			container.setBilling(dur.getStandardSeconds());
			return container;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Container invokeThis(CompiledContainer con) {
		try {
			DateTime start = DateTime.now();
			con.setResult(new ArrayList<>());
			Method[] t1 = con.getA().getMethods();
			for (Method met : t1) {
				Annotation[] anno = met.getDeclaredAnnotations();
				Object result;
				if (Modifier.isStatic(met.getModifiers()) && anno.length > 0) {
					{
						for (Annotation a : anno) {
							Class<Annotation> type = (Class<Annotation>) a
									.annotationType();
							if (type.getName().equals("InvokeThis")) {
								Object[] b = con.getMethodsToInvoke().get(
										met.getName());
								Object result1 = met.invoke(null, b);
								con.getResult().add(result1);
							}
						}
					}
				}
			}
			DateTime end = DateTime.now();
			org.joda.time.Duration dur = new org.joda.time.Duration(start, end);
			con.setBilling(dur.getStandardSeconds());
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
