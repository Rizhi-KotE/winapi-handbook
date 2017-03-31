package client.gui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.layout.VBox;

public class MainWindow extends VBox {

    private final WinApiHandbookReactor reactor;
    private final ClassCreateForm classCreateForm;
    private final FindClassesWidget findClassesWidget;

    public MainWindow(WinApiHandbookReactor reactor, ClassCreateForm classCreateForm, FindClassesWidget findClassesWidget){

        this.reactor = reactor;
        this.classCreateForm = classCreateForm;
        this.findClassesWidget = findClassesWidget;
        classCreateForm.managedProperty().bind(classCreateForm.visibleProperty());
        findClassesWidget.managedProperty().bind(findClassesWidget.visibleProperty());
        reactor.getEditEventSource().subscribe(this::changeToEditorState);
        reactor.getFindEventSource().subscribe(this::changeToBrowseState);
        changeToBrowseState(new ActionEvent());
        getChildren().addAll(classCreateForm, findClassesWidget);
    }

    void changeToBrowseState(Event e) {
        classCreateForm.setVisible(false);
        findClassesWidget.setVisible(true);
    }

    void changeToEditorState(Event e) {
        classCreateForm.setVisible(true);
        findClassesWidget.setVisible(false);
    }
}
