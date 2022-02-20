package com.example.muzeumfrontendjavafx.paintings;

import com.example.muzeumfrontendjavafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class PaintingCreateController extends Controller {

    @FXML
    private Spinner<Integer> inputYear;
    @FXML
    private TextField inputTitle;
    @FXML
    private CheckBox ondisplay;

    @FXML
    public void onHozzadButtonClick(ActionEvent actionEvent) {
        String title = inputTitle.getText().trim();
        int year = 0;
        boolean kiallitva = ondisplay.isSelected();
        if (title.isEmpty()){
            alert("Cím megadása kötelező");
            return;
        }
        try {
            year = inputYear.getValue();
        } catch (NullPointerException ex){
            alert("Az év megadása kötelező");
            return;
        } catch (Exception ex){
            alert("Az év csak 1000 és 2022 közötti szám lehet");
            return;
        }
        if (year < 1000 || year > 2022) {
            alert("A hossz csak 1000 és 2022 közötti szám lehet");
            return;
        }

        try {
            Paintings ujPainting = new Paintings(0,title,year,kiallitva);
            Paintings letrehozott = PaintingsApi.paintingHozzaadasa(ujPainting);
            if (letrehozott != null){
                alert("Painting hozzáadása sikeres");
            } else {
                alert("Painting hozzáadása sikeretelen");
            }
        } catch (Exception e) {
            hibaKiir(e);
        }
    }
}
