import client.gui.*
import common.service.impl.DummyHandbookService
import org.springframework.beans.factory.support.LookupOverride
import org.springframework.beans.factory.support.MethodOverrides

beans {
    handbookService(DummyHandbookService) { bean ->
        bean.initMethod = 'setup'
        files = []
        topics = new HashMap<>()
    }

    reactor(WinApiHandbookReactor, handbookService)

    errorHandler(ErrorHandler, reactor)

    functionCreateForm(FunctionCreateForm, reactor) {
        bean -> bean.scope = 'prototype'
    }

    classCreateForm(ElementCreateForm, reactor) { bean ->
        bean.methodOverrides = new MethodOverrides()
        bean.methodOverrides.addOverride(new LookupOverride('functionCreateForm', 'functionCreateForm'))
    }

    searchPanel(FindClassesWidget, reactor)

    mainWindow(MainWindow, reactor, classCreateForm, searchPanel)
}
