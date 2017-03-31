package client.gui;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.WinApiClass;
import model.WinApiFunction;
import org.reactfx.EventSource;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


public abstract class ClassCreateForm extends VBox {

    final WinApiHandbookReactor reactor;
    final EventSource<WinApiClass> winApiClass;
    VBox functionForms;
    TextField description;
    TextField name;

    ClassCreateForm(WinApiHandbookReactor reactor) {
        this.reactor = reactor;
        winApiClass = new EventSource<>();
        reactor.getClassEventSource().feedTo(winApiClass);
        createNameBlock();
        createDescriptionBlock();
        createFunctionsFields();
        createSaveButton();
    }

    void createNameBlock() {
        Label label = new Label("Name");
        name = new TextField();
        winApiClass
                .map(WinApiClass::getName)
                .feedTo(name.textProperty());


        VBox vBox = new VBox(label, name);
        getChildren().add(vBox);
    }

    void createDescriptionBlock() {
        Label label = new Label("Description");
        description = new TextField();
        winApiClass
                .map(WinApiClass::getDescription)
                .feedTo(description.textProperty());

        VBox vBox = new VBox(label, description);
        getChildren().add(vBox);
    }

    void createFunctionsFields() {
        functionForms = new VBox();
        Button addFunction = new Button("addFunction");
        addFunction.setOnAction(this::addNewFunction);

        VBox vBox = new VBox(addFunction, functionForms);
        winApiClass.
                map(WinApiClass::getFunctions)
                .map(f -> f.stream().map(this::functionCreateForm).collect(toList()))
                .subscribe(functionForms.getChildren()::setAll);
        getChildren().add(vBox);
    }

    void createSaveButton() {
        Button create = new Button("Save");
        create.setOnAction(this::submit);
        Button back = new Button("Back");
        back.setOnAction(this::back);
        getChildren().addAll(create, back);
    }

    void back(ActionEvent actionEvent) {
        reactor.getFindEventSource().push(new ActionEvent());
    }

    void addNewFunction(ActionEvent e) {
        WinApiClass winApiClass = getWinApiClass();
        winApiClass.getFunctions().add(
                new WinApiFunction(0l, "", "", "", new ArrayList<>()));
        pushClass(winApiClass);
    }

    WinApiClass getWinApiClass() {
        return new WinApiClass(
                null,
                name.getText(),
                "",
                description.getText(),
                getClassFunctions());
    }

    void pushClass(WinApiClass winApiClass) {
        this.winApiClass.push(winApiClass);
    }

    List<WinApiFunction> getClassFunctions() {
        return functionForms.getChildren()
                .stream()
                .map(FunctionCreateForm.class::cast)
                .map(FunctionCreateForm::getFunction)
                .collect(toList());
    }

    void submit(ActionEvent e) {
        reactor.save(getWinApiClass());
    }

    FunctionCreateForm functionCreateForm(WinApiFunction function) {
        FunctionCreateForm functionCreateForm = functionCreateForm();
        functionCreateForm.pushFunction(function);
        return functionCreateForm;
    }

    abstract FunctionCreateForm functionCreateForm();
}
