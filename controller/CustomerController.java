package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Customer;
import model.CustomerModel;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Optional;

public class CustomerController {
    public TextField txtId;
    public TextField txtSalary;
    public TextField txtName;
    public TextField txtAddress;

    public void btnCancelOnAction(ActionEvent actionEvent) {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtSalary.setText("");
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer customer = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                Double.parseDouble(txtSalary.getText()
                ));
        try {
            Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to add this customer?", ButtonType.YES, ButtonType.NO).showAndWait();

            if (buttonType.get() == ButtonType.YES) {
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?)");
                pstm.setObject(1, customer.getId());
                pstm.setObject(2, customer.getName());
                pstm.setObject(3, customer.getAddress());
                pstm.setObject(4, customer.getSalary());

                if (pstm.executeUpdate() > 0) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Added !").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something went wrong !").show();
                }
                txtId.setText("");
                txtName.setText("");
                txtAddress.setText("");
                txtSalary.setText("");
            }

        } catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }
    public void btnSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
      Customer customer= CustomerModel.SearchCustomer(txtId.getText());
      if(customer!=null){
          txtName.setText(customer.getName());
          txtAddress.setText(customer.getAddress());
          txtSalary.setText(customer.getSalary()+"");
      }else {
          new Alert(Alert.AlertType.INFORMATION, "Customer Not Found !").show();
      }
    }
    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Customer customer=new Customer(txtId.getText(),txtName.getText(),txtAddress.getText(),Double.parseDouble(txtSalary.getText()));
        boolean isUpdated=CustomerModel.updateCustomer(customer);
        if (isUpdated){
            new Alert(Alert.AlertType.INFORMATION, "Update Success !").show();
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Update Failed !").show();
        }
    }
    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        boolean isDeleted=CustomerModel.deleteCustomer(txtId.getText());
        if (isDeleted){
            new Alert(Alert.AlertType.INFORMATION, "Deleted Success !").show();
            txtId.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtSalary.setText("");
        }
        else {
            new Alert(Alert.AlertType.INFORMATION, "Delete Failed !").show();
        }
    }
}











