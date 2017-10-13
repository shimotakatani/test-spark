package ru.test.spark.em;


import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * create time 12.10.2017
 *
 * @author nponosov
 */
public class LocalEntityMangerFactory {

    public DataSource dataSource() {
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();

        dataSource.setDatabaseName( "test" );
        dataSource.setUser( "test" );
        dataSource.setPassword("test");

        return dataSource;
    }


    public Properties hibernateProperties(){
        final Properties properties = new Properties();

        properties.put( "hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect" );
        properties.put( "hibernate.connection.driver_class", "org.postgresql.Driver" );
        properties.put( "hibernate.hbm2ddl.auto", "create-drop" );

        return properties;
    }


    public EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties ){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource( dataSource );
        em.setJpaVendorAdapter( new HibernateJpaVendorAdapter() );
        em.setJpaProperties( hibernateProperties );
        em.setPersistenceUnitName( "mytestdomain" );
        em.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        em.afterPropertiesSet();

        return em.getObject();
    }
}
