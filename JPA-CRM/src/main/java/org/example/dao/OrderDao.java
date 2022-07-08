package org.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.example.jpa.EntityManagerSingleton;
import org.example.models.Client;
import org.example.models.Order;

public class OrderDao {

    public static EntityManager entityManager = EntityManagerSingleton.getEntityManager();

    
    // List all Orders
    public static List<Order> listOrders() {

        Query findAllQuery = entityManager.createQuery("select o from Order o");
        return findAllQuery.getResultList();
    }

    // Find unique Order by id
    public static Order findById(Long id) {

        return entityManager.find(Order.class, id);
    }

    // Update a Order 
    public static void update(Order order) {

        Order originalOrder = findById(order.getId());

        originalOrder.setClient(order.getClient());
        originalOrder.setDesignation(order.getDesignation());
        originalOrder.setNbDays(order.getNbDays());
        originalOrder.setState(order.getState());
        originalOrder.setTypePresta(order.getTypePresta());
        originalOrder.setUnitPrice(order.getUnitPrice());

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(originalOrder);
        tx.commit();

    }

    // delete a Order by id
    public static void deleteById(Long id){

        Query deleteByIdQuery = entityManager.createQuery("delete from Order o where o.id=:id")
        .setParameter("id", id);
        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();
        int result = deleteByIdQuery.executeUpdate();
        if (result == 1){
            System.out.println("Succesfully deleted Order " + id);
        }
        tx.commit();
        entityManager.clear(); // used for clearing cache so that can no longer find with id 

    }

    // Create a Order
    public static void create(Order order){

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(order);
        tx.commit();

    }






}