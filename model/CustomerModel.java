package model;

import db.DBConnection;

import java.sql.*;

public class CustomerModel {
    public static Customer SearchCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection= DBConnection.getInstance().getConnection();
        Statement stm=connection.createStatement();
        String SQL="Select * From Customer where id='"+id+"'";
        ResultSet rst=stm.executeQuery(SQL);
        return rst.next() ? new Customer(id,rst.getString("name"),rst.getString("address"),rst.getDouble("salary")):null;
    }
    public static boolean updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {
        String SQL = "Update Customer set name=?,address=?,salary=? where id=?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(SQL);

        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getSalary());
        pstm.setObject(4, customer.getId());
        return pstm.executeUpdate() > 0;
    }
    public static boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Customer where id='"+id+"'")>0;
    }
}
