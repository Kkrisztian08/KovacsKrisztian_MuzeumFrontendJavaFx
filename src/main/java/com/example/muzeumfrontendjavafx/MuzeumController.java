package com.example.muzeumfrontendjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MuzeumController extends Controller {


    @FXML
    public void onPaintingsClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("paintings/painting-view.fxml", "Festmények",
                    750, 550);
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onStatuesClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("statues/statue-view.fxml", "Szobrok",
                    750, 550);
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }
}