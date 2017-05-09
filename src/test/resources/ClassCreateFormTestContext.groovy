import client.gui.*
import common.service.impl.DummyHandbookService
import org.springframework.beans.factory.support.LookupOverride
import org.springframework.beans.factory.support.MethodOverrides

beans {
    handbookService(DummyHandbookService) { bean ->
        bean.initMethod = 'setup'
        files = new HashMap<>()
        topics = new HashMap<>()
    }

    reactor(WinApiHandbookReactor, handbookService)

    functionCreateForm(FunctionCreateForm, reactor) {
        bean -> bean.scope = 'prototype'
    }

    classCreateForm(ClassCreateForm, reactor) { bean ->
        bean.methodOverrides = new MethodOverrides()
        bean.methodOverrides.addOverride(new LookupOverride('functionCreateForm', 'functionCreateForm'))
    }

    searchPanel(FindClassesWidget, reactor)

    mainWindow(MainWindow, reactor, classCreateForm, searchPanel)
}
