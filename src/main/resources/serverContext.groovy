import common.service.impl.InitBase
import common.service.impl.WinApiHandbookServiceJdbcWrapper
import org.springframework.jdbc.datasource.DriverManagerDataSource

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


    winApiHandbookService(WinApiHandbookServiceJdbcWrapper,
            dataSource)

    initDatabase(InitBase, winApiHandbookService, ['methods/comboBox.json'])

}