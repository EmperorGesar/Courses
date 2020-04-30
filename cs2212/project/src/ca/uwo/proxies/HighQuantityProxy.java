package ca.uwo.proxies;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class HighQuantityProxy extends Proxy {

    private static HighQuantityProxy instance = null;

    public static HighQuantityProxy getInstance(){

        if (instance == null) instance = new HighQuantityProxy();
        return instance;

    }

    public HighQuantityProxy() {
    }

    @Override
    public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {

        BufferedReader br;
        boolean authenticated = false;

        try {
            br = new BufferedReader(new FileReader(new File("buyer_file")));
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineTokens = line.split("\t");
                if (buyer.getUserName().equals(lineTokens[1]) && buyer.getPassword().equals(lineTokens[2])) authenticated = true;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (authenticated){

            Facade facade = Facade.getInstance();
            facade.placeOrder(orderDetails, buyer);

        } else {
            System.out.println("Access denied.");
        }

    }

    @Override
    public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
        Facade facade = Facade.getInstance();
        facade.restock(restockDetails, supplier);
    }

}
