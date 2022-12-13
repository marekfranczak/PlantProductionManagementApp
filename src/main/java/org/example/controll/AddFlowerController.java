package org.example.controll;


import org.example.data.DataSource;

public class AddFlowerController {

    @FXML
    private TextField flowerNamePL;
    @FXML
    private TextField flowerNameLA;

    public void end(){
        DataSource.getInstance().addFlower(flowerNamePL.getText(), flowerNameLA.getText());
    }
}
