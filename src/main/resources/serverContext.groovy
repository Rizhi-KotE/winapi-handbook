import org.apache.commons.dbcp.BasicDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import thrift.HandbookThriftProcessor
import thrift.HibernateHandbookThriftService

beans {

    xmlns([jpa: 'http://www.springframework.org/schema/data/jpa'])
    jpa.'repositories'('base-package': 'thrift')

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

    thriftProcessor(HandbookThriftProcessor) { bean ->
        bean.initMethod = 'setup'
        port = 9090
        handler = handbookThriftHandler
    }
}