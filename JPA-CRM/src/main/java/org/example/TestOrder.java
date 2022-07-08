package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.dao.ClientDao;
import org.example.dao.OrderDao;
import org.example.models.Client;
import org.example.models.Order;

public class TestOrder {

    public static void main(String[] args) {


        ClientDao clientDao = new ClientDao();
        OrderDao orderDao = new OrderDao();

        
        System.out.println("\n\n *******TESTING ORDER DAO******* \n\n");

        System.out.println("\n\nTesting listOrders() method.");

        List<Order> listOrders = new ArrayList<>();
        listOrders = orderDao.listOrders();

        for (Order order : listOrders) {
            System.out.println("Order " + order.getId() + ": " + order);
        }

        System.out.println("\n\nTesting listOrders() finished.");

        // Test order create method

        System.out.println("\n\nTesting create() method");

        Order orderToCreate = new Order();
        Client clientTemp = clientDao.findById(1L);

        orderToCreate.setClient(clientTemp);
        orderToCreate.setDesignation("newOrderLol");
        orderToCreate.setNbDays(5);
        orderToCreate.setState(0);
        orderToCreate.setTypePresta("newOrderLol");
        orderToCreate.setUnitPrice(150f);

        orderDao.create(orderToCreate);

        System.out.println("\n\nTesting create() finished");

        // Test order findById method

        System.out.println("\n\nTesting findById() method");

        Long idToLookup = orderToCreate.getId();

        try {
            Order orderFound = orderDao.findById(idToLookup);
            System.out.println(orderFound);
        } catch (Exception e) {
            System.out.println("findById(" + idToLookup + ") did not work");
        }

        System.out.println("\n\nTesting findById() finished");

        // Test delete order method

        System.out.println("\n\nTesting deleteById() method");

        orderDao.deleteById(1L);

        try {
            orderDao.findById(idToLookup);
        } catch (Exception e) {
            System.out.println("Order was not found " + e);
        }

        System.out.println("\n\nTesting deleteById() finished");

        // Test update method

        System.out.println("\n\nTesting update() method");

        Order order = orderDao.findById(2L);

        order.setDesignation("My new designation");
        orderDao.update(order);

        order = orderDao.findById(order.getId());

        System.out.println(order);

        System.out.println("\n\nTesting update() finished");

    }
}
