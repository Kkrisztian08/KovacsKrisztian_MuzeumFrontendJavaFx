package com.example.muzeumfrontendjavafx.paintings;

import com.example.muzeumfrontendjavafx.Controller;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class PaintingController extends Controller {
    @FXML
    private TableColumn<Paintings, String> titleCol;
    @FXML
    private TableColumn<Paintings, Integer> yearCol;
    @FXML
    private TableColumn<Paintings, Integer> ondisplayCol;
    @FXML
    private TableView<Paintings> paintingTable;
    @FXML
    private Button festmenytorol;
    @FXML
    private Button festmenyModosit;

    public void initialize(){
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        ondisplayCol.setCellValueFactory(new PropertyValueFactory<>("on_display"));
        paintingListaFeltolt();
    }

    @FXML
    public void onPaintingUpdate(ActionEvent actionEvent) {
        int selectedIndex = paintingTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A módosításhoz előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Paintings modositando = paintingTable.getSelectionModel().getSelectedItem();
        try {
            PaintingUpdateController modositas = (PaintingUpdateController) ujAblak("paintings/paintingupdate-view.fxml",
                    "Film módosítása", 320, 400);
            modositas.setModositando(modositando);
            modositas.getStage().setOnHiding(event -> paintingTable.refresh());
            modositas.getStage().show();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onPaintingDelete(ActionEvent actionEvent) {
        int selectedIndex = paintingTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A törléshez előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Paintings torlendoPainting = paintingTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos hogy törölni szeretné az alábbi festményt: "+torlendoPainting.getTitle())){
            return;
        }
        try {
            boolean sikeres=PaintingsApi.paintingTorlese(torlendoPainting.getId());
            alert(sikeres? "Sikeres törlés": "Sikertelen törlés");
            paintingListaFeltolt();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onPaintingCreate(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("paintings/statuecreate-view.fxml", "Festmény hozzáadása",
                    320, 400);
            hozzadas.getStage().setOnCloseRequest(event -> paintingListaFeltolt());
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    private void paintingListaFeltolt(){
        festmenyModosit.setDisable(true);
        festmenytorol.setDisable(true);
        try {
            List<Paintings> paintingList = PaintingsApi.getPaintings();
            paintingTable.getItems().clear();
            for(Paintings painting: paintingList){
                paintingTable.getItems().add(painting);
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onFestmenySelected(Event event) {
        int selectedIndex = paintingTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            festmenytorol.setDisable(false);
            festmenyModosit.setDisable(false);
        }
    }
}
