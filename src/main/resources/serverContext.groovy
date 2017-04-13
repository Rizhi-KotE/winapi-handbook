import common.service.WinApiHibernateHandbookService
import org.apache.commons.dbcp.BasicDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import server.EntityManageFactoryFactory
import server.thrift.InitializeBase
import server.thrift.TWinApiHandbookHandler
import server.thrift.ThriftServer

beans {

    xmlns([jpa: 'http://www.springframework.org/schema/data/jpa'])
    jpa.'repositories'('base-package': 'common.service')

//    dataSource(BasicDataSource) {
//        driverClassName = "org.h2.Driver"
//        url = "jdbc:h2:mem:grailsDB"
//        username = "sa"
//        password = ""
//    }

    dataSource(BasicDataSource) {
        driverClassName = "com.mysql.jdbc.Driver"
        url = "jdbc:mysql://localhost:3306/winapi_handbook?useUnicode=true&characterEncoding=utf8"
        username = "root"
        password = "1"
    }


    vendorAdapter(HibernateJpaVendorAdapter) {
        generateDdl = true
    }

    entityManagerFactoryFactory(EntityManageFactoryFactory, vendorAdapter, dataSource)

    entityManagerFactory(entityManagerFactoryFactory: 'getObject')

    transactionManager(JpaTransactionManager) {
        entityManagerFactory = entityManagerFactory
    }

    winApiHandbookHibernateService(WinApiHibernateHandbookService,
            ref("winApiFunctionRepository"),
            ref("winApiClassRepository"),
            ref("winApiParameterRepository")
    )

    initDatabase(InitializeBase) { bean ->
        bean.initMethod = 'setup'
        content = ['methods/winApiClasses.json']
        repository = winApiHandbookHibernateService
    }

    handbookThriftHandler(TWinApiHandbookHandler, winApiHandbookHibernateService)

    thriftServer(ThriftServer) {
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