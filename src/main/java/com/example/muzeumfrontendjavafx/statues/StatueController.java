package com.example.muzeumfrontendjavafx.statues;

import com.example.muzeumfrontendjavafx.Controller;
import com.example.muzeumfrontendjavafx.paintings.PaintingUpdateController;
import com.example.muzeumfrontendjavafx.paintings.Paintings;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;

public class StatueController extends Controller {
    @FXML
    private TableColumn<Statues, String > personCol;
    @FXML
    private TableView<Statues> statueTable;
    @FXML
    private TableColumn<Statues, Integer> heightCol;
    @FXML
    private TableColumn<Statues, Integer> priceCol;
    @FXML
    private Button szoborModosit;
    @FXML
    private Button szobortorol;

    public void initialize(){
        personCol.setCellValueFactory(new PropertyValueFactory<>("person"));
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        statueListaFeltolt();
    }

    @FXML
    public void onStatueUpdate(ActionEvent actionEvent) {
        int selectedIndex = statueTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A módosításhoz előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Statues modositando = statueTable.getSelectionModel().getSelectedItem();
        try {
            StatueUpdateController modositas = (StatueUpdateController) ujAblak("statues/statueupdate-view.fxml",
                    "Szobor módosítása", 320, 400);
            modositas.setModositando(modositando);
            modositas.getStage().setOnHiding(event -> statueTable.refresh());
            modositas.getStage().show();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onStatueCreate(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("statues/statuecreate-view.fxml", "Szobor hozzáadása",
                    320, 400);
            hozzadas.getStage().setOnCloseRequest(event -> statueListaFeltolt());
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onStatueDelete(ActionEvent actionEvent) {
        int selectedIndex = statueTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1){
            alert("A törléshez előbb válasszon ki egy elemet a táblázatból");
            return;
        }
        Statues torlendoStatue = statueTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztos hogy törölni szeretné az alábbi szobrot: "+torlendoStatue.getPerson())){
            return;
        }
        try {
            boolean sikeres=StatuesApi.statueTorlese(torlendoStatue.getId());
            alert(sikeres? "Sikeres törlés": "Sikertelen törlés");
            statueListaFeltolt();
        } catch (IOException e) {
            hibaKiir(e);
        }
    }

    private void statueListaFeltolt(){
        szoborModosit.setDisable(true);
        szobortorol.setDisable(true);
        try {
            List<Statues> statueList = StatuesApi.getStatues();
            statueTable.getItems().clear();
            for(Statues statue: statueList){
                statueTable.getItems().add(statue);
            }
        } catch (IOException e) {
            hibaKiir(e);
        }
    }
    @Deprecated
    public void onSzoborSelected(Event event) {
        int selectedIndex = statueTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            szoborModosit.setDisable(false);
            szobortorol.setDisable(false);
        }
    }
}
