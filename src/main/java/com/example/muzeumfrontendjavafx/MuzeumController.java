package com.example.muzeumfrontendjavafx;

import com.example.muzeumfrontendjavafx.paintings.PaintingsApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.List;

import static com.example.muzeumfrontendjavafx.Controller.ujAblak;

public class MuzeumController extends Controller {


    @FXML
    public void onPaintingsClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("paintings/painting-view.fxml", "Festmények",
                    320, 400);
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onStatuesClick(ActionEvent actionEvent) {
        try {
            Controller hozzadas = ujAblak("statues/statue-view.fxml", "Szobrok",
                    320, 400);
            hozzadas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }
}