/**
 * 
 */
package org.freequiz.www.dao.hibernate;

import static org.junit.Assert.*;

import java.util.List;

import org.freequiz.www.dao.AbstractDAOFactory;
import org.freequiz.www.dao.SubjectDAO;
import org.freequiz.www.dao.TopicDAO;
import org.freequiz.www.model.Subject;
import org.freequiz.www.model.Topic;

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
public class HibernateTopicDAOTest {
	private static Configuration config;
	private static TopicDAO topicDAO;
	private static SubjectDAO subjectDAO;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		config = new Configuration();
		config.addAnnotatedClass(Subject.class);
		config.addAnnotatedClass(Topic.class);
		config.setProperty("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
		config.setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
		config.setProperty("hibernate.connection.url", "jdbc:derby:/fqdbTest;create=true");
		config.configure();
		new SchemaExport(config).create(true, true);
		
		topicDAO = AbstractDAOFactory.getFactory().getTopicDAO();
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
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#save(java.lang.Object)}.
	 */
	@Test
	public final void testSave() {
		Topic testTopic = new Topic("testSave Topic");
		testTopic.setSubject(new Subject("Test Subject"));
		doSaveTopic(testTopic);

		assertNotNull("Successfully saved " + testTopic, testTopic.getTopicid());
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateTopicDAO#findByPrimaryKey(java.io.Serializable)}.
	 */
	@Test
	public final void testFindByPrimaryKey() {
		Topic testTopic = new Topic("testFindByPrimaryKey Topic");
		testTopic.setSubject(new Subject("Test Subject"));
		doSaveTopic(testTopic);
		topicDAO.beginTransaction();
		Topic findTopic = topicDAO.findByPrimaryKey(testTopic.getTopicid());
		topicDAO.commitTransaction();
		assertNotNull("Found topic with primary key " + testTopic.getTopicid(), findTopic);
	}
	
	/** 
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateTopicDAO#findByExample()}.
	 */
	@Test
	public final void testFindByExample() {
		Topic testTopic = new Topic("testFindByExample Topic");
		testTopic.setSubject(new Subject("Test Subject"));
		doSaveTopic(testTopic);
		topicDAO.beginTransaction();
		Topic confirmTopic = new Topic("testFindByExample Topic");
		List<Topic> findTopicList = topicDAO.findByExample(confirmTopic, null);
		topicDAO.commitTransaction();
		assertTrue("Found at least one record matching " + testTopic, findTopicList.size() > 0);
	}
	
	@Test
	public final void testFindRangeByExample() {
		Subject testSubject = new Subject("Test Subject");
		Topic testTopic1 = new Topic("testFindRangeByExample Topic");
		testTopic1.setSubject(testSubject);
		doSaveTopic(testTopic1);
		Topic testTopic2 = new Topic("testFindByRangeExample Topic");
		testTopic2.setSubject(testTopic1.getSubject());
		doSaveTopic(testTopic2);
		
		topicDAO.beginTransaction();
		Topic confirmTopic = new Topic();
		int range = 2;
		confirmTopic.setSubject(testSubject);
		List<Topic> topics = topicDAO.findRangeByExample(0, range, confirmTopic, null);
		assertEquals("Expected number of topics found", topics.size(), range);
	}
	
	@Test
	public final void testFindAll() {
		Topic testTopic = new Topic("testFindAll Topic");
		testTopic.setSubject(new Subject("Test Subject"));
		doSaveTopic(testTopic);
		
		topicDAO.beginTransaction();
		List<Topic> findAllTopic = topicDAO.findAll();
		topicDAO.commitTransaction();
		
		assertTrue("Found at least one record with findAll()", findAllTopic.size() > 0);
	}
	
	@Test
	public final void testGetCount() {
		Topic testTopic = new Topic("testGetCount Topic");
		testTopic.setSubject(new Subject("Test Subject"));
		doSaveTopic(testTopic);
		
		int count = 0;
		topicDAO.beginTransaction();
		count = topicDAO.getCount();
		topicDAO.commitTransaction();
		assertTrue("Count contains at least one record", count > 0);
	}
	
	@Test 
	public final void testGetCountByExample() {
		Topic testTopic = new Topic("testGetCountByExample Topic");
		testTopic.setSubject(new Subject("Test Subject"));
		doSaveTopic(testTopic);
		
		int count = 0;
		topicDAO.beginTransaction();
		count = topicDAO.getCountByExample(testTopic, null);
		topicDAO.commitTransaction();
		assertTrue("Count contains at least one record", count > 0);
	}
	
	@Test 
	public final void testGetCountBySubject() {
		Topic testTopic = new Topic("testGetCount(Subject) Topic");
		testTopic.setSubject(new Subject("Test Subject"));
		doSaveTopic(testTopic);
		
		int count = 0;
		topicDAO.beginTransaction();
		count = topicDAO.getCount(testTopic.getSubject());
		topicDAO.commitTransaction();
		assertTrue("Count contains at least one record", count > 0);
	}
	
	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#delete(java.lang.Object)}.
	 */
	@Test
	public final void testDelete() {
		int beforeDelete = 0, afterDelete = 0;
		Topic topic1 = new Topic("testDelete Topic 1");
		topic1.setSubject(new Subject("Test Subject"));
		Topic topic2 = new Topic("testDelete Topic 2");
		topic2.setSubject(new Subject("Test Subject"));
		doSaveTopic(topic1);
		doSaveTopic(topic2);
		
		topicDAO.beginTransaction();
		beforeDelete = topicDAO.getCount();
		assertTrue("No topics to delete", beforeDelete > 0);
	
		topicDAO.delete(topicDAO.findByPrimaryKey(topic1.getTopicid()));
		afterDelete = topicDAO.getCount();
		topicDAO.commitTransaction();
		
		assertTrue("Successfully deleted first element in database", beforeDelete > afterDelete);
	}
	
	private void doSaveTopic(Topic topic) {
		topicDAO.beginTransaction();
		if(topic.getSubject().getSubjectid() == null)			
			subjectDAO.save(topic.getSubject());

		topicDAO.save(topic);
		topicDAO.commitTransaction();
	}
}
