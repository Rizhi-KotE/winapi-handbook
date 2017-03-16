import org.apache.commons.dbcp.BasicDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import server.thrift.HandbookTServletFactory

import server.thrift.HibernateHandbookThriftService
import server.thrift.InitializeBase

beans {

    xmlns([jpa: 'http://www.springframework.org/schema/data/jpa'])
    jpa.'repositories'('base-package': 'server.thrift')

    initDatabase(InitializeBase) { bean ->
        bean.initMethod = 'setup'
        content = [1l: 'methods/CompareFileTime.html']
        repository = ref("topicRepository")
    }

    dataSource(BasicDataSource) {
        driverClassName = "org.h2.Driver"
        url = "jdbc:h2:mem:grailsDB"
        username = "sa"
        password = ""
    }

    entityManagerFactory(LocalContainerEntityManagerFactoryBean) {
        dataSource = dataSource
    }

    transactionManager(JpaTransactionManager) {
        entityManagerFactory = entityManagerFactory
    }

    handbookThriftHandler(HibernateHandbookThriftService) {
        repository = ref("topicRepository")
    }

    TServletFactory(HandbookTServletFactory){
        handler = handbookThriftHandler
    }
}