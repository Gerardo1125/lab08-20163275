package controller;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
public final class PMF2 {
	private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	private PMF2() {}
	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}
} 
