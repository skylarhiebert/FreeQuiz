/**
 * 
 */
package org.freequiz.www.dao.hibernate;

import static org.junit.Assert.*;

import java.util.List;

import org.freequiz.www.dao.AbstractDAOFactory;
import org.freequiz.www.dao.QuestionDAO;
import org.freequiz.www.dao.SubjectDAO;
import org.freequiz.www.dao.TopicDAO;
import org.freequiz.www.model.Question;
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
public class HibernateQuestionDAOTest {
	private static Configuration config;
	private static SubjectDAO subjectDAO;
	private static TopicDAO topicDAO;
	private static QuestionDAO questionDAO;
	
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
		topicDAO = AbstractDAOFactory.getFactory().getTopicDAO();
		questionDAO = AbstractDAOFactory.getFactory().getQuestionDAO();
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
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateQuestionDAO#findByExample(org.freequiz.www.model.Question, java.lang.String[])}.
	 */
	@Test
	public final void testFindByExample() {
		Question testQuestion = new Question();
		testQuestion.setQuestionText("testFindByExample Question");
		testQuestion.setAnswerText("test Answer");
		testQuestion.setGradeLevel("1");
		testQuestion.setDifficulty(new Long(1));
		doSaveQuestion(testQuestion);
		
		Question confirmQuestion = new Question();
		confirmQuestion.setQuestionText("testFindByExample Question");
		questionDAO.beginTransaction();
		List<Question> questions = questionDAO.findByExample(confirmQuestion, null);
		questionDAO.commitTransaction();
		assertTrue("Found at least one record matching " + confirmQuestion, questions.size() > 0);
		
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateQuestionDAO#findRangeByExample(int, int, org.freequiz.www.model.Question, java.lang.String[])}.
	 */
	@Test
	public final void testFindRangeByExample() {
		Question testQuestion1 = new Question();
		testQuestion1.setQuestionText("testFindRangeByExample Question");
		testQuestion1.setAnswerText("test Answer");
		testQuestion1.setGradeLevel("1");
		testQuestion1.setDifficulty(new Long(1));
		doSaveQuestion(testQuestion1);
		Question testQuestion2 = new Question();
		testQuestion2.setQuestionText("testFindRangeByExample Question");
		testQuestion2.setAnswerText("test Answer");
		testQuestion2.setGradeLevel("1");
		testQuestion2.setDifficulty(new Long(1));
		doSaveQuestion(testQuestion2);
		
		int range = 2;
		questionDAO.beginTransaction();
		Question confirmQuestion = new Question();
		confirmQuestion.setQuestionText("testFindRangeByExample Question");
		List<Question> questions = questionDAO.findRangeByExample(0, range, confirmQuestion, null);
		assertEquals("Expected number of questions found", range, questions.size());
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateQuestionDAO#getCountByExample(org.freequiz.www.model.Question, java.lang.String[])}.
	 */
	@Test
	public final void testGetCountByExample() {
		Question testQuestion = new Question();
		testQuestion.setQuestionText("testGetCountByExample Question");
		testQuestion.setAnswerText("test Answer");
		testQuestion.setGradeLevel("1");
		testQuestion.setDifficulty(new Long(1));
		doSaveQuestion(testQuestion);
		
		int count = 0;
		questionDAO.beginTransaction();
		Question confirmQuestion = new Question();
		confirmQuestion.setQuestionText("testGetCountByExample Question");
		count = questionDAO.getCountByExample(confirmQuestion, null);
		assertTrue("Found at least one record", count > 0);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateQuestionDAO#findByExampleWithSubject(org.freequiz.www.model.Question, org.freequiz.www.model.Subject, java.lang.String[])}.
	 */
	@Test
	public final void testFindByExampleWithSubject() {
		Question testQuestion1 = new Question();
		testQuestion1.setQuestionText("testFindByExampleWithSubject Question");
		testQuestion1.setAnswerText("test Answer");
		testQuestion1.setGradeLevel("1");
		testQuestion1.setDifficulty(new Long(1));
		Topic topic = new Topic("Test Topic");
		Subject subject = new Subject("findByExampleWithSubject");
		topic.setSubject(subject);
		testQuestion1.setTopic(topic);
		doSaveQuestion(testQuestion1);
		Question testQuestion2 = new Question();
		testQuestion2.setQuestionText("testFindByExampleWithSubject Question");
		testQuestion2.setAnswerText("test Answer");
		testQuestion2.setGradeLevel("1");
		testQuestion2.setDifficulty(new Long(1));
		testQuestion2.setTopic(topic);
		doSaveQuestion(testQuestion2);
		assertTrue(subject.getSubjectid() != null);
		
		questionDAO.beginTransaction();
		Question confirmQuestion = new Question();
		confirmQuestion.setQuestionText("testFindByExampleWithSubject Question");
		List<Question> questions = questionDAO.findByExampleWithSubject(confirmQuestion, subject, null);
		assertEquals("Expected number of questions found", 2, questions.size());
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateQuestionDAO#findRangeByExampleWithSubject(int, int, org.freequiz.www.model.Question, org.freequiz.www.model.Subject, java.lang.String[])}.
	 */
	@Test
	public final void testFindRangeByExampleWithSubject() {
		Question testQuestion1 = new Question();
		testQuestion1.setQuestionText("testFindRangeByExampleWithSubject Question");
		testQuestion1.setAnswerText("test Answer");
		testQuestion1.setGradeLevel("1");
		testQuestion1.setDifficulty(new Long(1));
		Topic topic = new Topic("Test Topic");
		Subject subject = new Subject("getCountSubject");
		topic.setSubject(subject);
		testQuestion1.setTopic(topic);
		doSaveQuestion(testQuestion1);
		Question testQuestion2 = new Question();
		testQuestion2.setQuestionText("testFindRangeByExampleWithSubject Question");
		testQuestion2.setAnswerText("test Answer");
		testQuestion2.setGradeLevel("1");
		testQuestion2.setDifficulty(new Long(1));
		testQuestion2.setTopic(topic);
		doSaveQuestion(testQuestion2);
		
		int range = 2;
		questionDAO.beginTransaction();
		Question confirmQuestion = new Question();
		confirmQuestion.setQuestionText("testFindRangeByExampleWithSubject Question");
		List<Question> questions = questionDAO.findRangeByExampleWithSubject(0, range, confirmQuestion, subject, null);
		assertEquals("Expected number of questions found", range, questions.size());
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateQuestionDAO#getCount(org.freequiz.www.model.Topic)}.
	 */
	@Test
	public final void testGetCountTopic() {
		Question testQuestion = new Question();
		testQuestion.setQuestionText("testGetCountByTopic Question");
		testQuestion.setAnswerText("test Answer");
		testQuestion.setGradeLevel("1");
		testQuestion.setDifficulty(new Long(1));
		Topic topic = new Topic("getCountTopic");
		Subject subject = new Subject("getCountSubject");
		topic.setSubject(subject);
		testQuestion.setTopic(topic);
		doSaveQuestion(testQuestion);
		
		int count = 0;
		questionDAO.beginTransaction();
		count = questionDAO.getCount(subject);
		assertTrue("Found expected number of records", count == 1);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateQuestionDAO#getCount(org.freequiz.www.model.Subject)}.
	 */
	@Test
	public final void testGetCountSubject() {
		Question testQuestion = new Question();
		testQuestion.setQuestionText("testGetCountBySubject Question");
		testQuestion.setAnswerText("test Answer");
		testQuestion.setGradeLevel("1");
		testQuestion.setDifficulty(new Long(1));
		Topic topic = new Topic("Test Topic");
		Subject subject = new Subject("getCountSubject");
		topic.setSubject(subject);
		testQuestion.setTopic(topic);
		doSaveQuestion(testQuestion);
		
		int count = 0;
		questionDAO.beginTransaction();
		count = questionDAO.getCount(subject);
		assertTrue("Found expected number of records", count == 1);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateQuestionDAO#getCountByExampleWithSubject(org.freequiz.www.model.Question, org.freequiz.www.model.Subject, java.lang.String[])}.
	 */
	@Test
	public final void testGetCountByExampleWithSubject() {
		Question testQuestion = new Question();
		testQuestion.setQuestionText("testFindByExampleWithSubject Question");
		testQuestion.setAnswerText("test Answer");
		testQuestion.setGradeLevel("1");
		testQuestion.setDifficulty(new Long(1));
		Topic topic = new Topic("Test Topic");
		Subject subject = new Subject("findByExampleWithSubject");
		topic.setSubject(subject);
		testQuestion.setTopic(topic);
		doSaveQuestion(testQuestion);
		
		int count = 0;
		questionDAO.beginTransaction();
		Question confirmQuestion = new Question();
		confirmQuestion.setQuestionText("testFindByExampleWithSubject Question");
		count = questionDAO.getCountByExampleWithSubject(confirmQuestion, subject, null);
		assertTrue("Found expected number of records", count == 1);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateQuestionDAO#findBySubject(org.freequiz.www.model.Subject)}.
	 */
	@Test
	public final void testFindBySubject() {
		Question testQuestion = new Question();
		testQuestion.setQuestionText("testFindBySubject Question");
		testQuestion.setAnswerText("test Answer");
		testQuestion.setGradeLevel("1");
		testQuestion.setDifficulty(new Long(1));
		Topic topic = new Topic("Test Topic");
		Subject subject = new Subject("findBySubject");
		topic.setSubject(subject);
		testQuestion.setTopic(topic);
		doSaveQuestion(testQuestion);
		
		questionDAO.beginTransaction();
		List<Question> questions = questionDAO.findBySubject(subject);
		assertTrue("Found at least one record with Subject " + subject, questions.size() > 0);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.HibernateQuestionDAO#findByTopic(org.freequiz.www.model.Topic)}.
	 */
	@Test
	public final void testFindByTopic() {
		Question testQuestion = new Question();
		testQuestion.setQuestionText("testFindByTopic Question");
		testQuestion.setAnswerText("test Answer");
		testQuestion.setGradeLevel("1");
		testQuestion.setDifficulty(new Long(1));
		Topic topic = new Topic("testFindByTopic");
		Subject subject = new Subject("findByTopicSubject");
		topic.setSubject(subject);
		testQuestion.setTopic(topic);
		doSaveQuestion(testQuestion);
		
		questionDAO.beginTransaction();
		List<Question> questions = questionDAO.findByTopic(topic);
		assertTrue("Found at least one record with Topic " + topic, questions.size() > 0);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#findAll()}.
	 */
	@Test
	public final void testFindAll() {
		Question testQuestion = new Question();
		testQuestion.setQuestionText("testFindAll Question");
		testQuestion.setAnswerText("test Answer");
		testQuestion.setGradeLevel("1");
		testQuestion.setDifficulty(new Long(1));
		doSaveQuestion(testQuestion);
		
		questionDAO.beginTransaction();
		List<Question> questions = questionDAO.findAll();
		assertTrue("Found at least one record", questions.size() > 0);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#findRange(int, int)}.
	 */
	@Test
	public final void testFindRange() {
		Question testQuestion1 = new Question();
		testQuestion1.setQuestionText("testFindRange Question");
		testQuestion1.setAnswerText("test Answer");
		testQuestion1.setGradeLevel("1");
		testQuestion1.setDifficulty(new Long(1));
		doSaveQuestion(testQuestion1);
		Question testQuestion2 = new Question();
		testQuestion2.setQuestionText("testFindRange Question");
		testQuestion2.setAnswerText("test Answer");
		testQuestion2.setGradeLevel("1");
		testQuestion2.setDifficulty(new Long(1));
		doSaveQuestion(testQuestion2);
		
		int range = 2;
		questionDAO.beginTransaction();
		List<Question> questions = questionDAO.findRange(0, range);
		assertEquals("Expected number of records found", range, questions.size());
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#getCount()}.
	 */
	@Test
	public final void testGetCount() {
		Question testQuestion1 = new Question();
		testQuestion1.setQuestionText("testGetCount Question");
		testQuestion1.setAnswerText("test Answer");
		testQuestion1.setGradeLevel("1");
		testQuestion1.setDifficulty(new Long(1));
		doSaveQuestion(testQuestion1);
		Question testQuestion2 = new Question();
		testQuestion2.setQuestionText("testGetCount Question");
		testQuestion2.setAnswerText("test Answer");
		testQuestion2.setGradeLevel("1");
		testQuestion2.setDifficulty(new Long(1));
		doSaveQuestion(testQuestion2);
		
		int count = 0;
		questionDAO.beginTransaction();
		count = questionDAO.getCount();
		questionDAO.commitTransaction();
		
		assertTrue("Found at least 2 records", count >= 2);
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#save(java.lang.Object)}.
	 */
	@Test
	public final void testSave() {
		Question testQuestion = new Question();
		testQuestion.setQuestionText("testSave Question");
		testQuestion.setAnswerText("testSave Answer");
		testQuestion.setGradeLevel("1");
		testQuestion.setDifficulty(new Long(1));
		doSaveQuestion(testQuestion);

		assertNotNull("Successfully saved " + testQuestion, testQuestion.getQuestionid());
	}

	/**
	 * Test method for {@link org.freequiz.www.dao.hibernate.GenericHibernateDAO#delete(java.lang.Object)}.
	 */
	@Test
	public final void testDelete() {
		int beforeDelete = 0, afterDelete = 0;
		Question testQuestion = new Question();
		testQuestion.setQuestionText("testDelete Question");
		testQuestion.setAnswerText("testSave Answer");
		testQuestion.setGradeLevel("1");
		testQuestion.setDifficulty(new Long(1));
		doSaveQuestion(testQuestion);
		questionDAO.beginTransaction();
		beforeDelete = questionDAO.getCount();
		assertTrue("No topics to delete", beforeDelete > 0);
	
		questionDAO.delete(questionDAO.findByPrimaryKey(testQuestion.getQuestionid()));
		afterDelete = questionDAO.getCount();
		questionDAO.commitTransaction();
		
		assertTrue("Successfully deleted element in database", beforeDelete > afterDelete);
	}
	
	private void doSaveQuestion(Question question) {
		questionDAO.beginTransaction();
		if(question.getTopic() != null && question.getTopic().getTopicid() == null) {
			if(question.getTopic().getSubject() != null && question.getTopic().getSubject().getSubjectid() == null)
				subjectDAO.save(question.getTopic().getSubject());
			topicDAO.save(question.getTopic());
		}
		questionDAO.save(question);
		questionDAO.commitTransaction();
	}

}
