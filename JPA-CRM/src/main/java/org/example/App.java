package org.example;

import java.util.ArrayList;
import java.util.List;

import org.example.dao.ClientDao;
import org.example.models.Client;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args )
    {


        // Test listClients method
        ClientDao clientDao = new ClientDao();
        List<Client> listClients = new ArrayList<>();


        
        System.out.println("\n\nTesting listClients() method");


        listClients = clientDao.listClients();

        for (Client client : listClients) {
            System.out.println("Client " + client.getId() + ": " + client);
        }

        
        System.out.println("\n\nTesting listClients() finished.");

        // Test client create method

        System.out.println("\n\nTesting create() method");
        Client clientToCreate = new Client();
        
        clientToCreate.setCompanyName("ClientCreate");
        clientToCreate.setFirstName("ClientCreate");
        clientToCreate.setLastName("ClientCreate");
        clientToCreate.setAddress("ClientCreate");
        clientToCreate.setCity("ClientCreate");
        clientToCreate.setCountry("ClientCreate");
        clientToCreate.setEmail("ClientCreate");
        clientToCreate.setPhone("01234");
        clientToCreate.setState(0);
        clientToCreate.setZipCode("ClientCreate");

        clientDao.create(clientToCreate);

        System.out.println("\n\nTesting create() finished");

        // Test client findById method

        
        System.out.println("\n\nTesting findById() method");

        Long idToLookup = clientToCreate.getId();

        try {
            Client clientFound = clientDao.findById(idToLookup);
            System.out.println(clientFound);
        } catch (Exception e){
            System.out.println("findById(" + idToLookup + ") did not work");
        }

        System.out.println("\n\nTesting findById() finished");

        // Test delete client method

        
        System.out.println("\n\nTesting deleteById() method");

        clientDao.deleteById(idToLookup);

        try{
            clientDao.findById(idToLookup);
        } catch (Exception e){
            System.out.println("Client was not found " + e);
        }
        
        System.out.println("\n\nTesting deleteById() finished");
        
        // Test update method

        System.out.println("\n\nTesting update() method");

        Client client = clientDao.findById(2L);

        client.setAddress("My new address");
        clientDao.update(client);

        client = clientDao.findById(client.getId());

        System.out.println(client);

        System.out.println("\n\nTesting update() finished");

    }
}
