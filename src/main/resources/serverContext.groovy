import common.service.WinApiHibernateHandbookService
import org.apache.commons.dbcp.BasicDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import server.soap.HandbookSoapPublisher
import server.soap.HandbookSoapServiceImpl

import server.thrift.InitializeBase
import server.thrift.TWinApiHandbookHandler
import server.thrift.ThriftServer

beans {

    xmlns([jpa: 'http://www.springframework.org/schema/data/jpa'])
    jpa.'repositories'('base-package': 'common.service')

    initDatabase(InitializeBase) { bean ->
        bean.initMethod = 'setup'
        content = ['methods/winApiClasses.json']
        repository = ref("winApiClassRepository")
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

    winApiHandbookHibernateService(WinApiHibernateHandbookService,
            ref("winApiFunctionRepository"),
            ref("winApiClassRepository")
    )

    handbookThriftHandler(TWinApiHandbookHandler, winApiHandbookHibernateService)

    thriftServer(ThriftServer){
        handler = handbookThriftHandler
        port = 9090
    }
//    handbookSoupService(HandbookSoapServiceImpl){
//        repository = ref("winApiClassRepository")
//    }

//    soupPublisher(HandbookSoapPublisher){
//        url = "http://localhost:1986/wss/hello?wsdl"
//        service = handbookSoupService
//    }
}