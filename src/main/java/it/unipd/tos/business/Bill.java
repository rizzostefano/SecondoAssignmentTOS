////////////////////////////////////////////////////////////////////
// STEFANO RIZZO 1193464
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.util.List;


public class Bill {

    public static final double
            IMPORTO_MINIMO_SENZA_COMMISSIONE = 10.00;

    public static double getLeastExpensiveIceCreamPrice(List<MenuItem> l) {
        double min=10000000;
        for (MenuItem i: l) {
            if (min > i.getPrice() && i.getType().equals("GELATO")) {
                min=i.getPrice();
            }
        }
        return min;
    }

    public static double getOrderPrice(List<MenuItem> itemsOrdered,
        User user) throws RestaurantBillException {
        double sum=0;
        int iceCreamCounter=0;
        for (MenuItem item : itemsOrdered) {
            sum+=item.getPrice();
            if (item.getType().equals("GELATO")) {
                iceCreamCounter+=1;
            }
        }
        if (sum <= IMPORTO_MINIMO_SENZA_COMMISSIONE) {
            sum += 0.50;
        }
        double discount=0;
        if (iceCreamCounter >5) {
            discount=getLeastExpensiveIceCreamPrice(itemsOrdered)*0.5;
        }
        return sum-discount;
    }
}