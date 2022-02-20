package com.example.muzeumfrontendjavafx.paintings;

import com.example.muzeumfrontendjavafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class PaintingUpdateController extends Controller {
    @FXML
    private Spinner<Integer> inputYear;
    @FXML
    private TextField inputTitle;
    private Paintings modositando;
    @FXML
    private CheckBox ondisplay;

    @FXML
    public void onModositButtonClick(ActionEvent actionEvent) {
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
            alert("Az év csak 1000 és 2022 közötti szám lehet");
            return;
        }

        modositando.setTitle(title);
        modositando.setYear(year);
        modositando.setOn_display(kiallitva);

        try {
            Paintings modositott=PaintingsApi.paintingModositas(modositando);
            if (modositott !=null){
                alertWait("Sikeres módosítás");
                this.stage.close();
            } else {
                alert("Sikertelen módosítás");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Paintings getModositando() {
        return modositando;
    }

    public void setModositando(Paintings modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    private void ertekekBeallitasa() {
        inputTitle.setText(modositando.getTitle());
        inputYear.getValueFactory().setValue(modositando.getYear());
        ondisplay.setSelected(modositando.getOn_display());
    }
}
