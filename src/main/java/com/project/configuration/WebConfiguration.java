package com.project.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.project.*")
@PropertySource(value = "classpath:db.properties")
@EnableTransactionManagement
public class WebConfiguration {
	@Bean
	public static PropertyPlaceholderConfigurer placeholderConfigurer() {
		return new PropertyPlaceholderConfigurer();
	}
	
	@Autowired
	private Environment evm;
	
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("classpath:tiles.xml");
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		TilesViewResolver viewResolver = new TilesViewResolver();
		return viewResolver;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(evm.getProperty("db.driverClass"));
		dataSource.setUrl(evm.getProperty("db.url"));
		dataSource.setUsername(evm.getProperty("db.username"));
		dataSource.setPassword(evm.getProperty("db.password"));
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean session = new LocalSessionFactoryBean();
		session.setDataSource(this.dataSource());
		session.setPackagesToScan("com.project.entity");
		Properties properties = new Properties();
		properties.put("hibernate.dialect", evm.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", evm.getProperty("hibernate.show_sql"));
		session.setHibernateProperties(properties);
		return session;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager manager = new HibernateTransactionManager();
		manager.setSessionFactory(sessionFactory);
		return manager;
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry register) {
//		register.addResourceHandler("/static/**").addResourceLocations("/resource/");
//	}
}
