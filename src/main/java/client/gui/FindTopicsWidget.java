package client.gui;

import javafx.scene.layout.VBox;


public class FindTopicsWidget extends VBox {
//    @Setter
//    WinApiHandbookService service;
//    Collection<WinApiClass> topics;
//
//    @Setter
//    String findMessage;
//    @Setter
//    String closeMessage;
//    @Setter
//    ObjectProperty<WinApiClass> currentTopic;
//
//    TextField textField;
//    ListView<WinApiClass> listView;
//
//    public void setup() {
//        topics = service.findTopics("");
//        listView = new ListView<>();
//        listView.getItems().addAll(topics);
//        listView.setCellFactory(Cell::new);
//        getChildren().addAll(createControls(), listView);
//        setMinWidth(100);
//        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> currentTopic.setValue(newValue));
//    }
//
//    private Pane createControls() {
//        textField = new TextField();
//        Button find = new Button(findMessage);
//        find.setOnAction(this::find);
//        Button close = new Button(closeMessage);
//        close.setOnAction(this::close);
//        return new HBox(find, textField, close);
//    }
//
//    private void find(Event e) {
//        String keyword = textField.getText();
//        List<WinApiClass> topics = service.findTopics(keyword);
//        listView.setItems(FXCollections.observableList(topics));
//    }
//
//    private void close(Event e) {
//        setVisible(false);
//    }
//
//    public void open() {
//        setVisible(true);
//    }
//
//    private static class Cell extends ListCell<WinApiClass> {
//        public Cell(ListView<WinApiClass> view) {
//            super();
//        }
//
//        @Override
//        protected void updateItem(WinApiClass item, boolean empty) {
//            super.updateItem(item, empty);
//            if (!empty) setText(item.getHeader());
//            else setText("");
//        }
//    }
}
