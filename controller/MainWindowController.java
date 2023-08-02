package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
    public void btnSearchCustOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SearchCustomer-form.fxml"))));
        stage.show();
    }

    public void btnAddCustOnActon(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Addcustomer-form.fxml"))));
        stage.show();
    }

    public void btnUpdateCustOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/UpdateCustomer-form.fxml"))));
        stage.show();
    }

    public void btnDeleteCustOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/DeleteCustomer-form.fxml"))));
        stage.show();
    }

    public void btnViewCustOnAction(ActionEvent actionEvent) {
    }
}
