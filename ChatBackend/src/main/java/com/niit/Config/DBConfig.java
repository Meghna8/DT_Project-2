package com.niit.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.DAO.BlogDAO;
import com.niit.DAO.BlogDAOImpl;
import com.niit.DAO.ForumDAO;
import com.niit.DAO.ForumDAOImpl;
import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Forum;
import com.niit.model.ForumComment;

@Configuration
@EnableTransactionManagement

public class DBConfig {
	@Bean
	public DataSource getH2DataSource()
	{   
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/LetsChat");
		dataSource.setUsername("deepika");
		dataSource.setPassword("deepika");
		
		System.out.println("---Data Source Created---");
		return dataSource;
	}
	
	@Bean
	public SessionFactory sessionFactory() 
	{
		LocalSessionFactoryBuilder factoryBuilder = new LocalSessionFactoryBuilder(getH2DataSource());
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		hibernateProperties.setProperty("hibernate.use_sql_commnets", "true");
		factoryBuilder.addProperties(hibernateProperties);
		
		factoryBuilder.addAnnotatedClass(Blog.class);
		factoryBuilder.addAnnotatedClass(BlogComment.class);
		
		System.out.println("Creating SessionFactory Bean");
		return factoryBuilder.buildSessionFactory();

	}
	
	@Bean(name="blogDAO")
	public BlogDAO getBlogDAO()
	{
		System.out.println("----blog DAO Implementation---");
		return new BlogDAOImpl();
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("---Transaction Manager----");
		return new HibernateTransactionManager(sessionFactory);
	}

}
