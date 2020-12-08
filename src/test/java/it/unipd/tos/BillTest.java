////////////////////////////////////////////////////////////////////
// STEFANO RIZZO 1193464
////////////////////////////////////////////////////////////////////

package it.unipd.tos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

import it.unipd.tos.business.Bill;
import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import org.junit.*;

import static org.junit.Assert.*;

public class BillTest {
    private static User U1, U2;
    private static LocalTime AFTERNOON;
    private static LocalTime EVENING;
    private static MenuItem m1, m2, m3, m4, m5, m6;

    @Before
    public void setUsers() {
        U1 = new User("Paolo", "Rossi", LocalDate.of(1973, 5, 17));
        U2 = new User("Giovanni", "Bianchi", LocalDate.of(2007, 9, 5));
    }

    @Before
    public void setTimes() {
        AFTERNOON = LocalTime.of(14, 53, 25);
        EVENING = LocalTime.of(18, 33, 25);
    }

    @Before
    public void setMenuItems() {
        m1 = new MenuItem("BUDINO", "Pinguino", 5.00);
        m2 = new MenuItem("BUDINO", "Biancaneve", 7.50);
        m3 = new MenuItem("GELATO", "Coppa Nafta", 3.00);
        m4 = new MenuItem("GELATO", "Banana Split", 6.50);
        m5 = new MenuItem("BIBITA", "Coca Cola", 2.50);
        m6 = new MenuItem("BIBITA", "Fanta", 2.00);
    }



    @Test
    public void isTime_AfternoonBill_NoDiscount () {
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        assertFalse(Bill.isTime(AFTERNOON));
    }

    @Test
    public void isTime_AfternoonBill_WithDiscount () {
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        assertTrue(Bill.isTime(EVENING));
    }

    @Test
    //controlla ci siano le condizioni (orario ed età) per applicare lo sconto
    public void checkBill_OrderFrom18to19_WithLuck() {
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        Bill b1 = new Bill(EVENING, l1, U1);
        Bill b2 = new Bill(EVENING, l1, U2);
        b1.setLuck(true);
        b2.setLuck(true);
        assertFalse(b1.checkBill());
        assertTrue(b2.checkBill());
    }

    @Test
    public void checkBill_OrderFrom18to19_NoLuck() {
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        Bill b1 = new Bill(EVENING, l1, U1);
        Bill b2 = new Bill(EVENING, l1, U2);
        b1.setLuck(false);
        b2.setLuck(false);
        assertFalse(b1.checkBill());
        assertFalse(b2.checkBill());
    }

    @Test
    public void checkBill_OtherTime_WithLuck() {
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        Bill b1 = new Bill(AFTERNOON, l1, U1);
        Bill b2 = new Bill(AFTERNOON, l1, U2);
        b1.setLuck(true);
        b2.setLuck(true);
        assertFalse(b1.checkBill());
        assertFalse(b2.checkBill());
    }
    @Test
    public void checkBill_OtherTime_NoLuck() {
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        Bill b1 = new Bill(AFTERNOON, l1, U1);
        Bill b2 = new Bill(AFTERNOON, l1, U2);
        b1.setLuck(false);
        b2.setLuck(false);
        assertFalse(b1.checkBill());
        assertFalse(b2.checkBill());
    }

    @Test
    public void getLeastExpensiveIceCreamPrice_AllIceCream_LowestPriceFound(){
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m2);
        l1.add(m3);
        l1.add(m4);
        l1.add(m5);
        assertEquals(3.00, new Bill(AFTERNOON, l1, U1)
                .getLeastExpensiveIceCreamPrice(), 0.01);
    }

    @Test
    public void getOrderPrice_ValidValues_NoDiscountCalculated_WithCommission() throws RestaurantBillException{
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        assertEquals(5.50, new Bill(AFTERNOON, l1, U1).getOrderPrice(), 0.01);
    }

    @Test
    public void getOrderPrice_ValidValues_NoDiscountCalculated_NoCommission() throws RestaurantBillException{
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        l1.add(m1);
        l1.add(m6);
        assertEquals(12, new Bill(AFTERNOON, l1, U1).getOrderPrice(), 0.01);
    }

    @Test
    public void getOrderPrice_ValidValues_MoreThanFiveIceCreamDiscountCalculated() throws RestaurantBillException{
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m3);
        l1.add(m3);
        l1.add(m3);
        l1.add(m3);
        l1.add(m3);
        l1.add(m3);
        assertEquals(16.5, new Bill(AFTERNOON, l1, U1).getOrderPrice(), 0.01);
    }

    @Test
    public void getOrderPrice_ValidValues_LessThanFiveIceCreamDiscountCalculated() throws RestaurantBillException{
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m3);
        l1.add(m3);
        l1.add(m3);
        l1.add(m2);
        l1.add(m2);
        l1.add(m2);
        l1.add(m2);
        l1.add(m2);
        l1.add(m2);
        l1.add(m2);
        assertEquals(55.35, new Bill(AFTERNOON, l1, U1).getOrderPrice(), 0.01);
    }

    @Test
    public void getOrderPrice_ValidValues_MoreThan50euros_DiscountCalculated() throws RestaurantBillException{
        List<MenuItem> l1 = new LinkedList<>();
        for (int i=0; i<11; i++) {
            l1.add(m1);
        }
        assertEquals(49.5, new Bill(AFTERNOON, l1, U1).getOrderPrice(), 0.01);
    }

    @Test
    public void getOrderPrice_invalidValues() {
        List<MenuItem> l1 = new LinkedList<>();
        for (int i = 0; i < 50; i++) {
            l1.add(m1);
        }
        try {
            new Bill(AFTERNOON, l1, U1).getOrderPrice();
        } catch (RestaurantBillException e) {
            assertTrue((e.getMessage()).equals("Too Many Item"));
        }
    }

    @Test
    public void getOrderPrice_ValidValues_UnderageFreeBill() throws RestaurantBillException{
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        Bill b1 = new Bill(EVENING, l1, U2);
        b1.setLuck(true);
        assertEquals(0, b1.getOrderPrice(), 0.01);
    }

    @Test
    public void getOrderPrice_ValidValues_AdultFreeBill() throws RestaurantBillException{
        List<MenuItem> l1 = new LinkedList<>();
        l1.add(m1);
        Bill b1 = new Bill(EVENING, l1, U1);
        b1.setLuck(true);
        assertEquals(5.5, b1.getOrderPrice(), 0.01);
    }
}
