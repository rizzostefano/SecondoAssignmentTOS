////////////////////////////////////////////////////////////////////
// STEFANO RIZZO 1193464
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
public class Bill {
    private final LocalTime time;
    private final List<MenuItem> items;
    private final User user;
    private boolean luck;

    private static int free=10;


    public Bill(LocalTime t, List<MenuItem> i, User u) {
        time= LocalTime.of(t.getHour(), t.getMinute(), t.getSecond());
        items = new LinkedList<>(i);
        user = new User(u);
    }
    public void setLuck(boolean b) {
        luck=b;
    }

    public static boolean isTime(LocalTime t) {
        return t.isAfter(LocalTime.of(18, 00)) &&
                t.isBefore(LocalTime.of(19, 00));
    }

    public boolean checkBill() {
        return isTime(time) && user.isUnderage() && luck;
    }

    public double getLeastExpensiveIceCreamPrice() {
        double min=10000000;
        for (MenuItem i: items) {
            if (min > i.getPrice() && i.getType().equals("GELATO")) {
                min=i.getPrice();
            }
        }
        return min;
    }

    public double getOrderPrice() throws RestaurantBillException {
        if (items.size()>30) {
            throw new RestaurantBillException("Too Many Item");
        }
        double sum=0;
        int iceCreamCounter=0;
        for (MenuItem item : items) {
            sum+=item.getPrice();
            if (item.getType().equals("GELATO")) {
                iceCreamCounter+=1;
            }
        }
        if (sum <= 10.00) {
            sum += 0.50;
        }
        double discount=0;
        if (iceCreamCounter >5) {
            discount=getLeastExpensiveIceCreamPrice()*0.5;
        }
        sum-=discount;
        if (sum >=50) {
            sum-=sum*0.10;
        }
        if (free>0 &&
        checkBill()) {
            sum = 0;
            free-=1;
        }
        return sum;
    }
}