package org.example.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.example.jpa.EntityManagerSingleton;
import org.example.models.Client;
import org.hibernate.annotations.SourceType;

public class ClientDao {

    public static EntityManager entityManager = EntityManagerSingleton.getEntityManager();


    // List all clients
    public static List<Client> listClients() {

        Query findAllQuery = entityManager.createQuery("select c from Client c");

        return findAllQuery.getResultList();
    }

    // Find unique client by id
    public static Client findById(Long id) {

        return entityManager.find(Client.class, id);
    }

    // Update a client 
    public static void update(Client client) {

        Client originalClient = findById(client.getId());

        originalClient.setAddress(client.getAddress());
        originalClient.setCity(client.getCity());
        originalClient.setCountry(client.getCountry());
        originalClient.setEmail(client.getEmail());
        originalClient.setFirstName(client.getFirstName());
        originalClient.setLastName(client.getLastName());
        originalClient.setPhone(client.getPhone());
        originalClient.setState(client.getState());
        originalClient.setZipCode(client.getZipCode());

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.merge(originalClient);
        tx.commit();

    }

    // delete a client by id
    public static void deleteById(Long id){

        Query deleteByIdQuery = entityManager.createQuery("delete from Client c where c.id=:id")
        .setParameter("id", id);
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        int result = deleteByIdQuery.executeUpdate();
        if (result == 1){
            System.out.println("Succesfully deleted client " + id);
        }
        tx.commit();
        entityManager.clear(); // used for clearing cache so that can no longer find with id 

    }

    // Create a client
    public static void create(Client client){

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(client);
        tx.commit();

    }


}
