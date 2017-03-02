package gui;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lombok.Setter;
import service.HandbookService;
import service.Topic;

import java.util.Collection;
import java.util.List;

public class FindTopicsWidget extends VBox {
    @Setter
    HandbookService service;
    Collection<Topic> topics;

    @Setter
    String findMessage;
    @Setter
    String closeMessage;
    TextField textField;
    ListView<Topic> listView;

    public void setup() {
        topics = service.findTopics("");
        listView = new ListView<>();
        listView.getItems().addAll(topics);
        listView.setCellFactory(Cell::new);
        getChildren().addAll(createControls(), listView);
        setMinWidth(100);
    }

    private Pane createControls() {
        textField = new TextField();
        Button find = new Button(findMessage);
        find.setOnAction(this::find);
        Button close = new Button(closeMessage);
        close.setOnAction(this::close);
        return new HBox(find, textField, close);
    }

    private void find(Event e) {
        String keyword = textField.getText();
        List<Topic> topics = service.findTopics(keyword);
        listView.setItems(FXCollections.observableList(topics));
    }

    private void close(Event e) {
        setVisible(false);
    }

    public void open() {
        setVisible(true);
    }

    public void bindSelectionListener(ChangeListener<Topic> listener) {
        listView.getSelectionModel().selectedItemProperty().addListener(listener);
    }

    private static class Cell extends ListCell<Topic> {
        public Cell(ListView<Topic> view) {
            super();
        }

        @Override
        protected void updateItem(Topic item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) setText(item.getHeader());
            else setText("");
        }
    }
}
