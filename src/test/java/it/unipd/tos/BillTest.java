////////////////////////////////////////////////////////////////////
// STEFANO RIZZO 1193464
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.unipd.tos.business.Bill;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import org.junit.*;

public class BillTest {
    private static final User
    //utente maggiorenne
    U1 = new User("Paolo", "Lampredotto",
            LocalDate.of(1973, 5, 17)),
    //utente minorenne
    U2 = new User("Joaquin", "Merdovich",
            LocalDate.of(2007, 9, 5));

    private static final MenuItem
    m1 = new MenuItem("BUDINO", "Pinguino", 5.00),
    m2 = new MenuItem("BUDINO", "Biancaneve", 7.50),
    m3 = new MenuItem("GELATO", "Coppa Nafta", 3.00),
    m4 = new MenuItem("GELATO", "Banana Split", 6.50),
    m5 = new MenuItem("BIBITA", "Coca Cola", 2.50),
    m6 = new MenuItem("BIBITA", "Fanta", 2.00);

    @Test
    public void getOrderPrice_ValidValues_NoDiscount_NoCommission() {
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        l1.add(m4);
        l1.add(m6);
        try {
            assertEquals(13.50, Bill.getOrderPrice(l1, U1),
                    0.01);
        }catch (RestaurantBillException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderPrice_ValidValues_NoDiscountWithCommission()
    {
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m3);
        l1.add(m4);
        try {
            assertEquals(10.00, Bill.getOrderPrice(l1, U1),
                    0.01);
        }catch (RestaurantBillException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderPrice_ValidValues_WithDiscountNoCommission()
    {
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m3);
        l1.add(m4);
        l1.add(m3);
        l1.add(m4);
        l1.add(m3);
        l1.add(m4);
        try {
            assertEquals(27.0, Bill.getOrderPrice(l1, U1),
                    0.01);
        }catch (RestaurantBillException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getOrderdPrice_ValidValues_WithDiscountWithCommission()
    {
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        l1.add(m6);
        try {
            assertEquals(7.50, Bill.getOrderPrice(l1, U1),
                    0.01);
        }catch (RestaurantBillException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLeastExpensiveIceCreamPrice_ValidValues(){
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m2);
        l1.add(m3);
        l1.add(m4);
        l1.add(m5);
        assertEquals(3.00,
                Bill.getLeastExpensiveIceCreamPrice(l1), 0.01);
    }
}
