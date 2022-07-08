package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.dao.ClientDao;
import org.example.dao.OrderDao;
import org.example.models.Client;
import org.example.models.Order;

public class TestTableRelations {
    public static void main(String[] args) {

        ClientDao clientDao = new ClientDao();
        OrderDao orderDao = new OrderDao();

        clientDao.deleteById(1L);


    }
        
}
