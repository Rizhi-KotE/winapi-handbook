import org.apache.commons.dbcp.BasicDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import server.soap.HandbookSoapPublisher
import server.soap.HandbookSoapServiceImpl

import server.thrift.InitializeBase
import server.thrift.ThriftServer

beans {

    xmlns([jpa: 'http://www.springframework.org/schema/data/jpa'])
    jpa.'repositories'('base-package': 'common.server')

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

    thriftServer(ThriftServer){
        handler = handbookThriftHandler
        port = 9090
    }

    handbookSoupService(HandbookSoapServiceImpl){
        repository = ref("topicRepository")
    }

    soupPublisher(HandbookSoapPublisher){
        url = "http://localhost:1986/wss/hello?wsdl"
        service = handbookSoupService
    }
}