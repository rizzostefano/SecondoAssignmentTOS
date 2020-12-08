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

    public static double getOrderPrice(List<MenuItem> itemsOrdered,
        User user) throws RestaurantBillException {
        double sum=0;
        for (MenuItem item : itemsOrdered) {
            sum+=item.getPrice();
        }
        if (sum <= IMPORTO_MINIMO_SENZA_COMMISSIONE) {
            sum += 0.50;
        }
        return sum;
    }
}