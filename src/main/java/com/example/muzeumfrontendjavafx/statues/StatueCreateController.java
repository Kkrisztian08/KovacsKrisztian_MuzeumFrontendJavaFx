package com.example.muzeumfrontendjavafx.statues;

import com.example.muzeumfrontendjavafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

public class StatueCreateController extends Controller {
    @FXML
    private Spinner<Integer> inputHeight;
    @FXML
    private Spinner<Integer> inputPrice;
    @FXML
    private TextField inputPerson;

    @FXML
    public void onHozzadButtonClick(ActionEvent actionEvent) {
        String person = inputPerson.getText().trim();
        int height = 0;
        int price = 0;
        if (person.isEmpty()){
            alert("Cím megadása kötelező");
            return;
        }
        if (person.length()<5) {
            alert("A névnek 5 betünél hosszabbnak kell lennie");
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


        try {
            Statues ujStatute = new Statues(0,person,height,price);
            Statues letrehozott = StatuesApi.statueHozzaadasa(ujStatute);
            if (letrehozott != null){
                alert("Statute hozzáadása sikeres");
            } else {
                alert("Statute hozzáadása sikeretelen");
            }
        } catch (Exception e) {
            hibaKiir(e);
        }
    }
}
