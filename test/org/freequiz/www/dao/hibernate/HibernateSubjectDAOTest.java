/**
 * 
 */
package org.freequiz.www.dao.hibernate;

import static org.junit.Assert.*;

import java.util.List;

import org.freequiz.www.dao.AbstractDAOFactory;
import org.freequiz.www.dao.SubjectDAO;
import org.freequiz.www.model.Subject;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Skylar Hiebert
 *
 */
public class HibernateSubjectDAOTest {
	private static Configuration config;
	private static SubjectDAO subjectDAO;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		config = new Configuration();
		config.addAnnotatedClass(Subject.class);
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
		config.setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
		config.setProperty("hibernate.connection.url", "jdbc:derby:/fqdbTest;create=true");
		config.configure();
		new SchemaExport(config).create(true, true);
		
		subjectDAO = AbstractDAOFactory.getFactory().getSubjectDAO();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		new SchemaExport(config).drop(true, true);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#findByPrimaryKey(java.io.Serializable)}.
	 */
	@Test
	public final void testFindByPrimaryKey() {
		Subject testSubject = new Subject("testFindByPrimaryKey Subject");
		doSaveSubject(testSubject);
		subjectDAO.beginTransaction();
		Subject findSubject = subjectDAO.findByPrimaryKey(testSubject.getSubjectid());
		subjectDAO.commitTransaction();
		assertNotNull("Found subject with primary key " + testSubject.getSubjectid(), findSubject);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#findAll()}.
	 */
	@Test
	public final void testFindAll() {
		Subject testSubject = new Subject("testGetCount Subject");
		doSaveSubject(testSubject);
		subjectDAO.beginTransaction();
		List<Subject> findAllSubject = subjectDAO.findAll();
		subjectDAO.commitTransaction();
		System.out.println("End findAll");
		assertTrue("Found at least one record with findAll()", findAllSubject.size() > 0);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#findRange(int, int)}.
	 */
	@Test
	public final void testFindRange() {
		Subject testSubject1 = new Subject("testFindRange Subject");
		doSaveSubject(testSubject1);
		Subject testSubject2 = new Subject("testFindRange Subject");
		doSaveSubject(testSubject2);
		
		subjectDAO.beginTransaction();
		int range = 2;
		List<Subject> subjects = subjectDAO.findRange(0, range);
		assertEquals("Expected number of subjects found", subjects.size(), range);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#findRangeByExample(int, int, java.lang.Object, java.lang.String[])}.
	 */
	@Test
	public final void testFindRangeByExample() {
		Subject testSubject1 = new Subject("testFindRangeByExample Subject");
		doSaveSubject(testSubject1);
		Subject testSubject2 = new Subject("testFindRangeByExample Subject");
		doSaveSubject(testSubject2);
		
		int range = 2;
		subjectDAO.beginTransaction();
		Subject confirmSubject = new Subject("testFindRangeByExample Subject");
		List<Subject> subjects = subjectDAO.findRangeByExample(0, range, confirmSubject, null);
		assertEquals("Expected number of subjects found", subjects.size(), range);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#findByExample(java.lang.Object, java.lang.String[])}.
	 */
	@Test
	public final void testFindByExample() {
		Subject testSubject = new Subject("testFindByExample Subject");
		doSaveSubject(testSubject);
		
		subjectDAO.beginTransaction();
		Subject confirmSubject = new Subject("testFindByExample Subject");
		List<Subject> findSubjectList = subjectDAO.findByExample(confirmSubject, null);
		subjectDAO.commitTransaction();
		assertTrue("Found at least one record matching " + testSubject, findSubjectList.size() > 0);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#getCount()}.
	 */
	@Test
	public final void testGetCount() {
		Subject testSubject = new Subject("testGetCount Subject");
		doSaveSubject(testSubject);
		
		int count = 0;
		subjectDAO.beginTransaction();
		count = subjectDAO.getCount();
		subjectDAO.commitTransaction();
		assertTrue("Count contains at least one record", count > 0);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#getCountByExample(java.lang.Object, java.lang.String[])}.
	 */
	@Test
	public final void testGetCountByExample() {
		Subject testSubject = new Subject("testGetCountByExample Subject");
		doSaveSubject(testSubject);
		
		int count = 0;
		subjectDAO.beginTransaction();
		count = subjectDAO.getCountByExample(testSubject, null);
		subjectDAO.commitTransaction();
		assertTrue("Count contains at least one record", count > 0);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#save(java.lang.Object)}.
	 */
	@Test
	public final void testSave() {
		Subject testSubject = new Subject("testSave Subject");
		doSaveSubject(testSubject);

		assertNotNull("Successfully saved " + testSubject, testSubject.getSubjectid());
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#delete(java.lang.Object)}.
	 */
	@Test
	public final void testDelete() {
		int beforeDelete = 0, afterDelete = 0;
		Subject Subject1 = new Subject("testDelete Subject 1");
		Subject Subject2 = new Subject("testDelete Subject 2");
		doSaveSubject(Subject1);
		doSaveSubject(Subject2);
		subjectDAO.beginTransaction();
		beforeDelete = subjectDAO.findAll().size();
		assertTrue("No Subjects to delete", beforeDelete > 0);
	
		// Delete first

		subjectDAO.delete(subjectDAO.findByPrimaryKey(Subject1.getSubjectid()));
		afterDelete = subjectDAO.findAll().size();
		subjectDAO.commitTransaction();
		
		assertTrue("Successfully deleted first element in database", beforeDelete > afterDelete);
	}

	private void doSaveSubject(Subject subject) {
		subjectDAO.beginTransaction();
		subjectDAO.save(subject);
		subjectDAO.commitTransaction();
	}
	
}
