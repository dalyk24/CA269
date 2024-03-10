import java.util.HashMap;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class Item
 * Represents Food Items
 * has name, weight, price
 */
class Item {
    private final String name;
    private final double price;

    /**
     * Creates an Item
     * @param name of item
     * @param price of item
     */
    Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }

    public String toString() {
        return this.name + " " + this.price;
    }
}

class Basket {
    private final Stack<Item> basket;

    Basket() {
        this.basket = new Stack<>();
    }

    public void addItem(Item item) {
        this.basket.push(item);
    }

    public Item removeItem() {
        if(this.basket.size() == 0) {
            return null;
        }
        else {
            Item item = this.basket.pop();
            return item;
        }
    }

    public String toString() {
        return "basket:" + this.basket.toString();
    }
}

class Checkout {
    private final Queue<Item> checkout;

    Checkout(Basket basket) {
        this.checkout = new LinkedList<>();
        Item temp;
    
        while((temp = basket.removeItem()) != null) {
            addItem(temp);
        }
    }

    public void addItem(Item item) {
        // TODO - add item to the checkout queue
        this.checkout.add(item);

    }

    public Item removeItem() {
        // TODO - remove item from the checkout queue
        // don't forget to check if its empty first!
        return this.checkout.poll();
    }

    public String toString() {
        return "checkout:" + this.checkout.toString();
    }
}

class Bill {
    private final Map<String,Integer> basket;
    private double price;

    Bill(Checkout checkout) {
        // TODO - initialise Map, set price, bill items from checkout
        // first, create the Map for the basket - just like Queue,
        // you must select an implementation such as LinkedHashMap
        // second, our starting price is zero - there are no items yet
        // third, iterate through the checkout items -
        // take an item out of the checkout, and bill it
        this.basket = new LinkedHashMap<>();
        this.price = 0;
        Item temp;

        while((temp = checkout.removeItem()) != null) {
            billItem(temp);
        }
    }

    public void billItem(Item item) {
        // TODO - put item in map, keep count of same items being billed
        // Note that the Map is from key String to value Integer
        // Items have names as Strings and count of items is an integer
        // first, check if the Item already exists in the Map -
        // the Item name is what we use as the key
        // If it exists - we need to increment its counter by 1
        // If not - we need to create a new key with the item name and value 1
        // second, add the item's price to the bill total price
        String itemName = item.getName();
        int tempInt;

        if(this.basket.containsKey(itemName)) {
            tempInt = this.basket.get(itemName);
            tempInt += 1;
            this.basket.put(itemName, tempInt);
        }
        else {
            this.basket.put(itemName, 1);
        }

        this.price += item.getPrice();
    }

    public double getTotal() {
        return this.price;
    }

    public String toString() {
        String output = "";
        for(String item: this.basket.keySet()) {
            output += item + " (" + this.basket.get(item) + "nos)\n";
        }
        return output + "total: " + this.price;
    }
}

class ShopInventory2 {
    public static void main(String[] args) {
        Basket basket = new Basket();
        loadBasket(basket);
        //System.out.println(basket); // for DEBUG
        Checkout checkout = new Checkout(basket);
        //System.out.println(checkout); // for DEBUG
        Bill bill = new Bill(checkout);
        System.out.println(bill);
    }

    static void loadBasket(Basket basket) {
        basket.addItem(new Item("Twinings Earl Grey Tea", 4.50));
        basket.addItem(new Item("Folans Orange Marmalade", 4.00));
        basket.addItem(new Item("Free-range Eggs", 3.35));
        basket.addItem(new Item("Brennan's Bread", 2.15));
        basket.addItem(new Item("Ferrero Rocher", 7.00));
        basket.addItem(new Item("Ferrero Rocher", 7.00));
    }
}