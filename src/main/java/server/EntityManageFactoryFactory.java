package server;


import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

public class EntityManageFactoryFactory {
    final HibernateJpaVendorAdapter vendorAdapter;

    final DataSource dataSource;

    EntityManageFactoryFactory(HibernateJpaVendorAdapter vendorAdapter, DataSource dataSource) {
        this.vendorAdapter = vendorAdapter;
        this.dataSource = dataSource;
    }

    public EntityManagerFactory getObject() {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("model");
        factory.setDataSource(dataSource);
        factory.afterPropertiesSet();

        return factory.getObject();
    }

}

