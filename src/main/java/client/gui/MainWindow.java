package client.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.WinApiUserElement;

import java.util.ArrayList;

public class MainWindow extends VBox {

    private final WinApiHandbookReactor reactor;
    private final ElementCreateForm elementCreateForm;
    private final FindClassesWidget findClassesWidget;
    TextField search;

    public MainWindow(WinApiHandbookReactor reactor, ElementCreateForm elementCreateForm, FindClassesWidget findClassesWidget) {

        this.reactor = reactor;
        this.elementCreateForm = elementCreateForm;
        this.findClassesWidget = findClassesWidget;
        reactor.listEventSource.subscribe(e -> changeToBrowseState(new ActionEvent()));
        reactor.refreshEventSource.subscribe(this::find);
        reactor.classEventSource.subscribe(e -> changeToEditorState(new ActionEvent()));
        elementCreateForm.managedProperty().bind(elementCreateForm.visibleProperty());
        findClassesWidget.managedProperty().bind(findClassesWidget.visibleProperty());
        reactor.getEditEventSource().subscribe(this::changeToEditorState);
        reactor.getFindEventSource().subscribe(this::changeToBrowseState);
        createSearchLine();
        reactor.getUpdateEventSource().subscribe(this::find);
        getChildren().addAll(elementCreateForm, findClassesWidget);
        changeToEditorState(new ActionEvent());
//        reactor.pushClass(new WinApiUserElement(1, "class", "key", "example",
//                asList(new WinApiFunction(1, "function", "key", "example",
//                        asList(new WinApiParameter(1, "type", "parameter"))))));
    }

    void changeToBrowseState(Event e) {
        elementCreateForm.setVisible(false);
        findClassesWidget.setVisible(true);
    }

    void changeToEditorState(Event e) {
        elementCreateForm.setVisible(true);
        findClassesWidget.setVisible(false);
    }

    private void createSearchLine() {
        search = new TextField();
        search.setPromptText("write class name");
        Button button = new Button("search");
        button.setOnAction(this::find);

        Button create = new Button("Create");
        create.setOnAction(this::createClass);
        Button refresh = new Button("Refresh");
        refresh.setOnAction(this::refresh);
        HBox hBox = new HBox(create, search, button, refresh);
        getChildren().add(hBox);

    }

    void refresh(ActionEvent e) {
        reactor.refreshEventSource.push(e);
    }

    private void createClass(ActionEvent actionEvent) {
        elementCreateForm.submit(new ActionEvent());
        reactor.pushClass(new WinApiUserElement(0, "", "", new ArrayList<>()));
    }

    void find(ActionEvent actionEvent) {
        reactor.search(search.getText());
    }
}
