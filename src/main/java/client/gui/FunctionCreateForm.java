package client.gui;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.WinApiFunction;
import model.WinApiFunctionRequirement;
import model.WinApiParameter;
import org.reactfx.EventSource;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static javafx.collections.FXCollections.observableArrayList;

public class FunctionCreateForm extends VBox {

    final EventSource<WinApiFunction> function;
    private final WinApiHandbookReactor reactor;
    ObservableList<Node> paramsForms;
    ObservableList<Node> requirementForms;
    TextField name;
    TextArea description;
    long id;
    long elementId;
    private Runnable o;
    private TextField returnType;
    private TextArea returnTypeDescription;
    private TextArea syntax;

    FunctionCreateForm(WinApiHandbookReactor reactor) {
        this.reactor = reactor;
        function = new EventSource<>();
        paramsForms = observableArrayList();
        createNameBlock();
        createDescriptionBlock();
        createReturnTypeBlock();
        createSyntaxBlock();
        createReturnTypeDefinitionBlock();
        createFunctionsParams();
        createFunctionRequirements();
        setMaxWidth(580);
        id = -1l;
        createIdBlock();
    }

    private void createFunctionRequirements() {
        VBox vBox = new VBox();
        Button addParam = new Button("addRequirement");
        addParam.setOnAction(this::addNewRequirement);

        requirementForms = vBox.getChildren();
        VBox vBox1 = new VBox(addParam, vBox);

        function
                .map(WinApiFunction::getRequirements)
                .map(f -> f.stream().map(RequirementForm::new).collect(toList()))
                .hook(list -> {
                    for (int i = 0; i < list.size(); i++) {
                        int num = i;
                        list.get(i).removeAction(() -> removeRequirement(num));
                    }
                })
                .subscribe(f -> vBox.getChildren().setAll(f));
        getChildren().add(vBox1);
    }

    private void createReturnTypeBlock() {
        Label label = new Label("Return type");
        returnType = new TextField();
        returnType.setPrefWidth(500);
        function
                .map(WinApiFunction::getReturnType)
                .feedTo(returnType.textProperty());
        VBox box = new VBox(label, returnType);
        box.setSpacing(10);
        getChildren().add(box);
    }

    private void createReturnTypeDefinitionBlock() {
        Label label = new Label("Return type definition");
        returnTypeDescription = new TextArea();
        returnTypeDescription.setWrapText(true);
        function
                .map(WinApiFunction::getReturnTypeDescription)
                .feedTo(returnTypeDescription.textProperty());
        VBox vBox = new VBox(label, returnTypeDescription);
        vBox.setSpacing(10);
        getChildren().add(vBox);
    }

    private void createSyntaxBlock() {
        Label label = new Label("Syntax");
        syntax = new TextArea();
        syntax.setPrefWidth(500);
        syntax.setWrapText(true);
        function
                .map(WinApiFunction::getSyntax)
                .feedTo(syntax.textProperty());
        VBox box = new VBox(label, syntax);
        box.setSpacing(10);
        getChildren().add(box);
    }

    private void createNameBlock() {
        Label label = new Label("Name");
        name = new TextField();
        name.setPrefWidth(400);
        function
                .map(WinApiFunction::getName)
                .feedTo(name.textProperty());


        Button remove = new Button("Remove");
        remove.setOnAction(e -> o.run());
        VBox box = new VBox(label, name);
        box.setSpacing(10);
        getChildren().addAll(remove, box);
    }

    private void createDescriptionBlock() {
        Label label = new Label("Description");
        description = new TextArea();
        description.setWrapText(true);
        description.setPrefWidth(500);
        function
                .map(WinApiFunction::getDescription)
                .feedTo(description.textProperty());

        VBox vBox = new VBox(label, description);
        getChildren().add(vBox);
    }

    private void createFunctionsParams() {
        VBox vBox = new VBox();
        Button addParam = new Button("addParam");
        addParam.setOnAction(this::addNewParam);

        paramsForms = vBox.getChildren();
        VBox vBox1 = new VBox(addParam, vBox);

        function
                .map(WinApiFunction::getParams)
                .map(f -> f.stream().map(ParamsForm::new).collect(toList()))
                .hook(list -> {
                    for (int i = 0; i < list.size(); i++) {
                        int num = i;
                        list.get(i).removeAction(() -> removeParam(num));
                    }
                })
                .subscribe(f -> vBox.getChildren().setAll(f));
        getChildren().add(vBox1);
    }

    private void removeRequirement(int number) {
        WinApiFunction function = getFunction();
        WinApiFunctionRequirement requirement = function.getRequirements().get(number);
        reactor.removeRequirement(requirement);
    }

    private void createIdBlock() {
        function
                .map(WinApiFunction::getId)
                .subscribe(l -> id = l);
        function
                .map(WinApiFunction::getElementId)
                .subscribe(l -> elementId = l);

    }

    void removeParam(int number) {
        WinApiFunction function = getFunction();
        WinApiParameter winApiParameter = function.getParams().get(number);
        reactor.removeParameter(winApiParameter);
    }

    void addNewParam(ActionEvent actionEvent) {
        WinApiFunction function = getFunction();
        function.getParams().add(new WinApiParameter());
        pushFunction(function);
    }

    public WinApiFunction getFunction() {
        List<WinApiParameter> collect = getParams();
        List<WinApiFunctionRequirement> requirements = getRequirements();
        return new WinApiFunction(id, elementId,
                name.getText(),
                description.getText(),
                syntax.getText(),
                collect,
                returnType.getText(),
                returnTypeDescription.getText(),
                requirements);
    }

    public void pushFunction(WinApiFunction function) {
        this.function.push(function);
    }

    List<WinApiParameter> getParams() {
        return paramsForms.stream()
                .map(ParamsForm.class::cast)
                .map(ParamsForm::getFirstDefinition)
                .collect(toList());
    }

    public List<WinApiFunctionRequirement> getRequirements() {
        return requirementForms.stream()
                .map(RequirementForm.class::cast)
                .map(RequirementForm::getRequirement)
                .collect(toList());
    }

    List<ParamsForm> getParamsForm() {
        return paramsForms
                .stream()
                .map(ParamsForm.class::cast)
                .collect(toList());
    }

    public void removeAction(Runnable o) {
        this.o = o;
    }

    public void addNewRequirement(ActionEvent actionEvent) {
        WinApiFunction function = getFunction();
        function.getRequirements().add(new WinApiFunctionRequirement());
        pushFunction(function);
    }
}
