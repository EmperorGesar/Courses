package ca.uwo.proxies;

import ca.uwo.client.Buyer;
import ca.uwo.client.Supplier;
import ca.uwo.frontend.Facade;

import java.util.Map;

public class SupplierProxy extends Proxy {

    private Proxy next;
    private static SupplierProxy instance = null;

    public static SupplierProxy getInstance(){

        if (instance == null) instance = new SupplierProxy(LowQuantityProxy.getInstance());
        return instance;

    }

    public SupplierProxy(Proxy next) {
        this.next = next;
    }

    @Override
    public void placeOrder(Map<String, Integer> orderDetails, Buyer buyer) {
        this.next.placeOrder(orderDetails, buyer);
    }

    @Override
    public void restock(Map<String, Integer> restockDetails, Supplier supplier) {
        Facade facade = Facade.getInstance();
        facade.restock(restockDetails, supplier);
    }

}
