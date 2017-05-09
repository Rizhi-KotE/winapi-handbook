import common.service.impl.WinApiHandbookServiceJdbcWrapper
import org.springframework.jdbc.datasource.DriverManagerDataSource

beans {

//    dataSource(DriverManagerDataSource) {
//        driverClassName = "org.h2.Driver"
//        url = "jdbc:h2:mem:grailsDB"
//        username = "sa"
//        password = ""
//    }

    dataSource(DriverManagerDataSource) {
        driverClassName = "com.mysql.jdbc.Driver"
        url = "jdbc:mysql://localhost:3306/winapi_handbook_test?useUnicode=true&characterEncoding=utf8"
        username = "root"
        password = "1"
    }


//    winApiHandbookService(WinApiHandbookServiceJdbcWrapper,
//            dataSource)
//
//    initDatabase(InitializeBase) { bean ->
//        bean.initMethod = 'setup'
//        content = ['methods/winApiClasses.json']
//        repository = winApiHandbookService
//    }

}