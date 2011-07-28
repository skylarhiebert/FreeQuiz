/**
 * 
 */
package org.freequiz.www.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Skylar Hiebert
 *
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failure. " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static void initializeHibernate() {} // Causes Hibernate sessionFactory to be loaded
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public static void closeSession() {
		sessionFactory.getCurrentSession().close();
	}
	
	public static Session beginTransaction() {
		Session hibernateSession = HibernateUtil.getSession();
		hibernateSession.beginTransaction();
		return hibernateSession;
	}
	
	public static void commitTransaction() {
		HibernateUtil.getSession().getTransaction().commit();
	}
	
	public static void rollbackTransaction() {
		HibernateUtil.getSession().getTransaction().rollback();
	}
	
	public static void shutdown() {
		getSessionFactory().close();
	}
}
