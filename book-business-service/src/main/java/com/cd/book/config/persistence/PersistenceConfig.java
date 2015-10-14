package com.cd.book.config.persistence;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Mayank.
 * 
 */

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.cd.book.dao" })
public class PersistenceConfig {

    @Autowired
    private Environment env;

    @Primary
    @Bean
    public DataSource dataSource() {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName(env.getProperty("database.driver"));
	dataSource.setUrl(env.getProperty("database.url"));
	dataSource.setUsername(env.getProperty("database.username"));
	dataSource.setPassword(env.getProperty("database.password"));
	System.out.println(" dataSource  is "+dataSource);
	return dataSource;
    }
    
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.cd.book.entity");
        factory.setDataSource(dataSource());
        factory.setJpaPropertyMap(jpaPropertyMap());
        factory.afterPropertiesSet();
        System.out.println(" I am here");
        return factory.getObject();
    }
    
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory){
    	JpaTransactionManager jpaTransactionManager=new JpaTransactionManager();
    	jpaTransactionManager.setDataSource(dataSource());
    	jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
    	return jpaTransactionManager;
    }
    public Map<String, String> jpaPropertyMap() {
		Map<String, String> map = new HashMap<String, String>();
		
		/*map.put("hibernate.connection.provider_class","org.hibernate.connection.C3P0ConnectionProvider");
		map.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		map.put("hibernate.c3p0.min_size", "5");
		map.put("hibernate.c3p0.max_size", "20");
		map.put("hibernate.c3p0.timeout", "360000");*/
		map.put("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
		//map.put("hibernate.connection.url", "jdbc:mysql://localhost/nemo");
		/*map.put("hibernate.connection.username", "root");
		map.put("hibernate.connection.password", "");
		map.put("hibernate.c3p0.max_statements", "30");*/
		
		map.put("hibernate.hbm2ddl.auto", "create");
		map.put("hibernate.format_sql", "true");
		System.out.println("map is "+map);
		return map;
	}
}

