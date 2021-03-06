import client.gui.ElementCreateForm
import client.gui.FunctionCreateForm
import client.gui.WinApiHandbookReactor
import org.springframework.beans.factory.support.LookupOverride
import org.springframework.beans.factory.support.MethodOverrides

beans {
    reactor(WinApiHandbookReactor)

    functionCreateForm(FunctionCreateForm, reactor){
        bean -> bean.scope = 'prototype'
    }

    classCreateForm(ElementCreateForm, reactor) { bean ->
        bean.methodOverrides = new MethodOverrides()
        bean.methodOverrides.addOverride(new LookupOverride('functionCreateForm', 'functionCreateForm'))
    }
}