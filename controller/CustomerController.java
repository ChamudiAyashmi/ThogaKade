package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerController {
    public TextField txtId;
    public TextField txtSalary;
    public TextField txtName;
    public TextField txtAddress;

    public void btnCancelOnAction(ActionEvent actionEvent) {

    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        Double salary = Double.parseDouble(txtSalary.getText());
        Customer customer = new Customer(id, name, address, salary);

        String SQL = "Insert Into Customer Values(?,?,?,?)";



        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);
        pstm.setObject(1, customer.getId());
        pstm.setObject(2, customer.getName());
        pstm.setObject(3, customer.getAddress());
        pstm.setObject(4, customer.getSalary());
        System.out.println("hello java fx");
        int num = pstm.executeUpdate();
        if (num > 0) {
            System.out.println("Added Success");
        }else{
            System.out.println("Failed");
        }
       // return
    }
}











