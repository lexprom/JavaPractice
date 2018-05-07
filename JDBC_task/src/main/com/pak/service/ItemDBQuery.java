package com.pak.service;

import com.pak.model.Item;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDBQuery
{
    private Connection connection;
    //JDBC driver name,database URL,database credentials
    private static final String JDBC_Driver = "org.h2.Driver";
    private static final String JDBC_URL = "jdbc:h2:file:~/h2/app_db;DB_CLOSE_ON_EXIT=FALSE";
    private static final String USER = "lex";
    private static final String PASS = "";

    public ItemDBQuery() throws IOException,SQLException,ClassNotFoundException
    {
        Class.forName(JDBC_Driver);
        connection = DriverManager.getConnection(JDBC_URL,USER,PASS);
        createTable();
    }

    public void createTable() throws SQLException {
        String query = "CREATE TABLE Item (" +
                "ID int NOT NULL," +
                "Title varchar(20) NOT NULL,"+
                "Price int NOT NULL," +
                "Description varchar(100)" +
                "PRIMARY KEY (ID));";
        createTableExecutor(query);
    }

    public void createTableExecutor(String query)throws SQLException
    {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }

    public List<Item> getItems()
    {
        List<Item> items = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(JDBC_URL,USER,PASS))
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Item");
            while (resultSet.next())
            {
                String title = resultSet.getString("Title");
                int price = resultSet.getInt("Price");
                String description = resultSet.getString("Description");

                items.add(new Item(title,price,description));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return items;
    }

    public void addItem(Item item) throws IOException, ClassNotFoundException
    {
        try(Connection connection = DriverManager.getConnection(JDBC_URL,USER,PASS))
        {
            Statement statement = connection.createStatement();
            String insertQuery = String.format
                    (
            "INSERT INTO Item VALUES('%d','%s','%d','%s');,",
                    item.getId(),
                    item.getTitle(),
                    item.getPrice(),
                    item.getDesc()
                    );
            statement.executeUpdate(insertQuery);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
