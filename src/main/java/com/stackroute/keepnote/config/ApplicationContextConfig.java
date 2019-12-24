package com.stackroute.keepnote.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
/*This class will contain the application-context for the application. 
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the 
 *                  class can be used by the Spring IoC container as a source of 
 *                  bean definitions
 * @EnableTransactionManagement - Enables Spring's annotation-driven transaction management capability.
 *                  
 * */import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stackroute.keepnote.model.Note;

import javax.sql.DataSource;

import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement

@ComponentScan(basePackages = { "com.stackroute.keepnote.dao" })

public class ApplicationContextConfig {

	/*
	 * Define the bean for DataSource. In our application, we are using MySQL as the
	 * dataSource. To create the DataSource bean, we need to know: 1. Driver class
	 * name 2. Database URL 3. UserName 4. Password
	 */
	@Autowired
	private Environment env;
	
		
		@Bean
		public DataSource getDatasource() {
			BasicDataSource dataSource = new BasicDataSource();
//			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//			dataSource.setUrl("jdbc:mysql://localhost:3306/JDBCTable?verifyServerCertificate=false&useSSL=false&requireSSL=false");
//			dataSource.setUsername("root");
//			dataSource.setPassword("Root@123");
			dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
			  dataSource.setUrl("jdbc:mysql://" + System.getenv("MYSQL_HOST") + ":3306/" +
			  System.getenv("MYSQL_DATABASE")
			  +"?verifyServerCertificate=false&useSSL=false&requireSSL=false");
			  dataSource.setUsername(System.getenv("MYSQL_USER"));
			  dataSource.setPassword(System.getenv("MYSQL_PASSWORD"));

			return dataSource;
	}

	/*
	 * Use this configuration while submitting solution in hobbes.
	 * 
	 */

	/*
	 * Define the bean for SessionFactory. Hibernate SessionFactory is the factory
	 * class through which we get sessions and perform database operations.
	 */
		@Bean
		@Autowired
		public LocalSessionFactoryBean getSessionFactory(DataSource dataSource) throws IOException {
			LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
			sessionFactory.setDataSource(dataSource);

			Properties hibernateProperties = new Properties();
			hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
			hibernateProperties.put("hibernate.show_sql", "true");
			hibernateProperties.put("hibernate.format_sql", "true");
			hibernateProperties.put("hibernate.hbm2ddl.auto", "update"); // create ,update, validate,none
			sessionFactory.setHibernateProperties(hibernateProperties);
			sessionFactory.setAnnotatedClasses(Note.class);
			sessionFactory.afterPropertiesSet();
			return sessionFactory;
		}

	/*
	 * Define the bean for Transaction Manager. HibernateTransactionManager handles
	 * transaction in Spring. The application that uses single hibernate session
	 * factory for database transaction has good choice to use
	 * HibernateTransactionManager. HibernateTransactionManager can work with plain
	 * JDBC too. HibernateTransactionManager allows bulk update and bulk insert and
	 * ensures data integrity.
	 */
		@Bean
		@Autowired
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
			HibernateTransactionManager transactionManager = new HibernateTransactionManager();
			transactionManager.setSessionFactory(sessionFactory);
			return transactionManager;
		}
}
