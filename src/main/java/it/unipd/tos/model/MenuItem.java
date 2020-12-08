////////////////////////////////////////////////////////////////////
// STEFANO RIZZO 1193464
////////////////////////////////////////////////////////////////////

package it.unipd.tos.model;

public class MenuItem {

    private enum items {GELATO, BUDINO, BIBITA};
    private items itemType;
    private String name;
    private double price;

    public MenuItem(String type, String itemName, double itemPrice) {
        itemType = items.valueOf(type);
        name = itemName;
        price = itemPrice;
    }

    public String getType() {
        return itemType.toString();
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }


}
