package it.unipd.tos.business;
import it.unipd.tos.business.TakeAwayBill;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;

import java.util.ArrayList;
import java.util.List;

public class Bill {

    private List<MenuItem> itemList;
    private User user;

    public  Bill(List<MenuItem> l, User u) {
        itemList = new ArrayList<MenuItem>(l);
        user = new User(u);
    }
    public static double getOrderPrice(List<MenuItem> itemsOrdered, User user) throws RestaurantBillException {
        double sum=0;
        for (MenuItem item : itemsOrdered) {
            sum+=item.getPrice();
        }
        return sum;
    }
}