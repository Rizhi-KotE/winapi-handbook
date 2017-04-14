import common.service.impl.WinApiHandbookServiceJdbc
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import server.thrift.TWinApiHandbookHandler
import server.thrift.ThriftServer

beans {

//    dataSource(BasicDataSource) {
//        driverClassName = "org.h2.Driver"
//        url = "jdbc:h2:mem:grailsDB"
//        username = "sa"
//        password = ""
//    }

    dataSource(DriverManagerDataSource) {
        driverClassName = "com.mysql.jdbc.Driver"
        url = "jdbc:mysql://localhost:3306/winapi_handbook?useUnicode=true&characterEncoding=utf8"
        username = "root"
        password = "1"
    }


    winApiHandbookHibernateService(WinApiHandbookServiceJdbc,
            dataSource)

//    initDatabase(InitializeBase) { bean ->
//        bean.initMethod = 'setup'
//        content = ['methods/winApiClasses.json']
//        repository = winApiHandbookHibernateService
//    }

    handbookThriftHandler(TWinApiHandbookHandler, winApiHandbookHibernateService)

    thriftServer(ThriftServer) {
        handler = handbookThriftHandler
        port = 9090
    }

//    soupPublisher(HandbookSoapPublisher){
//        url = "http://localhost:1986/wss/hello?wsdl"
//        service = handbookSoupService
//    }
}