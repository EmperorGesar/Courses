package ca.uwo.proxies;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class LowQuantityProxy extends Proxy {

    private Proxy next;
    private static LowQuantityProxy instance = null;

    public static LowQuantityProxy getInstance(){

        if (instance == null) instance = new LowQuantityProxy(HighQuantityProxy.getInstance());
        return instance;

    }

    public LowQuantityProxy(Proxy next) {
        this.next = next;
    }

    @Override
    public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {

        int sum = 0;

        for (Map.Entry<String, Integer> entry : orderDetails.entrySet()){
            sum = sum + entry.getValue();
        }

        if (sum > 10) {

            this.next.placeOrder(orderDetails, buyer);

        } else {

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

    }

    @Override
    public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
        Facade facade = Facade.getInstance();
        facade.restock(restockDetails, supplier);
    }

}
