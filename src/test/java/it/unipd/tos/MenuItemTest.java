  
////////////////////////////////////////////////////////////////////
// STEFANO RIZZO 1193464
////////////////////////////////////////////////////////////////////
package it.unipd.tos;

import static org.junit.Assert.assertEquals;
import it.unipd.tos.model.MenuItem;
import org.junit.*;

public class MenuItemTest {

    private static final MenuItem
    MI1 = new MenuItem("GELATO", "Coppa Nafta", 2.00);

    @Test
    public void testGetType() {
        assertEquals("GELATO", MI1.getType());
    }

    @Test
    public  void testGetName() {
        assertEquals("Coppa Nafta", MI1.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(2.00, MI1.getPrice(), 0.0000001);
    }

}
