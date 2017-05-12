import client.gui.ClassCreateForm
import client.gui.FindClassesWidget
import client.gui.FunctionCreateForm
import client.gui.MainWindow
import client.gui.WinApiHandbookReactor
import common.service.impl.DummyHandbookService
import model.WinApiUserElement
import org.springframework.beans.factory.support.LookupOverride
import org.springframework.beans.factory.support.MethodOverrides

beans {
    handbookService(DummyHandbookService) { bean ->
        bean.initMethod = 'setup'
        files = ['methods/comboBox.json']
        topics = [1l: new WinApiUserElement(1l,"class1", "",[])]
    }

    reactor(WinApiHandbookReactor, handbookService)

    functionCreateForm(FunctionCreateForm, reactor){
        bean -> bean.scope = 'prototype'
    }

    classCreateForm(ClassCreateForm, reactor) { bean ->
        bean.methodOverrides = new MethodOverrides()
        bean.methodOverrides.addOverride(new LookupOverride('functionCreateForm', 'functionCreateForm'))
    }

    searchPanel(FindClassesWidget, reactor)

    mainWindow(MainWindow, reactor, classCreateForm, searchPanel)
}
