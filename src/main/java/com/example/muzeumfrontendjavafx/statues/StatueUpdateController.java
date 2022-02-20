package com.example.muzeumfrontendjavafx.statues;

import com.example.muzeumfrontendjavafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class StatueUpdateController extends Controller {
    @FXML
    private Spinner<Integer> inputHeight;
    @FXML
    private Spinner<Integer> inputPrice;
    @FXML
    private TextField inputPerson;

    private Statues modositando;

    @FXML
    public void onModositButtonClick(ActionEvent actionEvent) {
        String person = inputPerson.getText().trim();
        int height = 0;
        int price = 0;
        if (person.isEmpty()){
            alert("Cím megadása kötelező");
            return;
        }
        try {
            height = inputHeight.getValue();
        } catch (NullPointerException ex){
            alert("A magasság megadása kötelező");
            return;
        } catch (Exception ex){
            alert("A magasság csak 1 és 1000 közötti szám lehet");
            return;
        }
        if (height < 1 || height > 1000) {
            alert("A magasság csak 1 és 1000 közötti szám lehet");
            return;
        }

        try {
            price = inputPrice.getValue();
        } catch (NullPointerException ex){
            alert("Az ár megadása kötelező");
            return;
        } catch (Exception ex){
            alert("Az ár csak 1 és 1000000 közötti szám lehet");
            return;
        }
        if (price < 1 || price > 1000000) {
            alert("Az ár csak 1 és 1000000 közötti szám lehet");
            return;
        }
        modositando.setPerson(person);
        modositando.setHeight(height);
        modositando.setPrice(price);

        try {
            Statues modositott= StatuesApi.statueModositas(modositando);
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

    public Statues getModositando() {
        return modositando;
    }

    public void setModositando(Statues modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    private void ertekekBeallitasa() {
        inputPerson.setText(modositando.getPerson());
        inputHeight.getValueFactory().setValue(modositando.getHeight());
        inputPrice.getValueFactory().setValue(modositando.getPrice());
    }
}
