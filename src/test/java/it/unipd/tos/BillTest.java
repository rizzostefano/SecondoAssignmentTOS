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
    U1 = new User("Paolo", "Lampredotto", LocalDate.of(1973, 5, 17)),
    //utente minorenne
    U2 = new User("Joaquin", "Merdovich", LocalDate.of(2007, 9, 5));

    private static final MenuItem
    m1 = new MenuItem("BUDINO", "Pinguino", 5.00),
    m2 = new MenuItem("BUDINO", "Biancaneve", 7.50),
    m3 = new MenuItem("GELATO", "Coppa Nafta", 3.00),
    m4 = new MenuItem("GELATO", "Banana Split", 6.30),
    m5 = new MenuItem("BIBITA", "Coca Cola", 2.50),
    m6 = new MenuItem("BIBITA", "Fanta", 2.00);

    @Test
    public void getOrderPrice_ValidValues_NoDiscount() {
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        l1.add(m6);
        try {
            assertEquals(7.00, Bill.getOrderPrice(l1, U1), 0.01);
        }catch (RestaurantBillException e) {
            e.printStackTrace();
        }

    }
}
