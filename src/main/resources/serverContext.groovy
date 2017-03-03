import org.apache.commons.dbcp.BasicDataSource
import org.springframework.orm.hibernate5.LocalSessionFactoryBean
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean

beans {

//    dataSourceBuilder(EmbeddedDatabaseBuilder) {
//        type = EmbeddedDatabaseType.H2
//        addScript("db/sql/create-db.sql")
//        addScript("db/sql/insert-data.sql")
//        build();
//    }
//
//
//    h2DataSource(dataSourceBuilder)
//
//    sessionFactory(LocalSessionFactoryBean) {
//        dataSource = h2DataSource
//
//        def properties = new Properties()
//        properties.setProperty("hibernate.dialect", H2Dialect.class.name)
//        properties.setProperty("hibernate.hbm2ddl.auto", "update")
//        properties.setProperty("hibernate.show_sql", "true")
//        hibernateProperties = properties
//    }

    dataSource(BasicDataSource) {
        driverClassName = "org.h2.Driver"
        url = "jdbc:h2:mem:grailsDB"
        username = "sa"
        password = ""
    }

//    sessionFactory(LocalSessionFactoryBean) {
//        dataSource = dataSource
//        hibernateProperties = ["hibernate.hbm2ddl.auto": "create-drop",
//                               "hibernate.show_sql"    : "true"]
//    }

    entityManagerFactory(LocalContainerEntityManagerFactoryBean){
        dataSource = dataSource
//        persistenceXmlLocation = "classpath:persistence.xml"
    }
}