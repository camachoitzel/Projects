package Tea;

import java.awt.GridLayout; 
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JPanel; 

public class Receipt {

    private JFrame frame; // need to create a JFrame object for each of the things
    private String sizeSelected; 
    private String teaSelected; 
    private String[] toppings; 
    private int sugarLevel; 
    private int iceLevel; 
    private double sizePrice; 
    private double toppingPrice; 
    private double totalPrice; 

    // constructor for Receipt class, takes in several parameters
    public Receipt(String sizeSelected, String teaSelected, String[] toppings, int sugarLevel, int iceLevel, double sizePrice, double toppingPrice, double totalPrice) {
        this.sizeSelected = sizeSelected; 
        this.teaSelected = teaSelected; 
        this.toppings = toppings; 
        this.sugarLevel = sugarLevel; 
        this.iceLevel = iceLevel; 
        this.sizePrice = sizePrice; 
        this.toppingPrice = toppingPrice; 
        this.totalPrice = totalPrice; 

        frame = new JFrame("Receipt"); // create a new JFrame with the title "Receipt"
        frame.setSize(300, 400); // set the size of the frame
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // close the frame when user clicks the close button
        frame.setLayout(new GridLayout(0, 1)); // use GridLayout with 0 rows and 1 column

        displayReceipt(); //  have to call the displayReceipt method to show the receipt
    }

    // method to display the receipt
    public void displayReceipt() {
        JPanel receiptPanel = new JPanel(); // create a JPanel for the receipt
        receiptPanel.setLayout(new GridLayout(0, 1)); // set the layout of the panel

        receiptPanel.add(new JLabel("------- Receipt -------")); // add a label with the title
        receiptPanel.add(new JLabel("Your order has been received!")); // add a label for order confirmation
        receiptPanel.add(new JLabel("We will see you soon at the Boba Oaisis Cafe!")); // add a label for the cafe message
        receiptPanel.add(new JLabel("Cup Size: " + sizeSelected + " - $" + String.format("%.2f", sizePrice))); // add a label for the cup size and price
        receiptPanel.add(new JLabel("Tea Selected: " + teaSelected)); // add a label for the tea selected

        if (toppings.length > 0) { // check if there are any toppings
            for (String topping : toppings) { // loop through each topping
                receiptPanel.add(new JLabel("Topping: " + topping + " - $0.50")); // add a label for each topping
            }
        } else {
            receiptPanel.add(new JLabel("Toppings: None")); // add a label if no toppings were selected
        }

        receiptPanel.add(new JLabel("Sugar Level: " + sugarLevel + "%")); // add a label for the sugar level
        receiptPanel.add(new JLabel("Ice Level: " + iceLevel + "%")); // add a label for the ice level
        receiptPanel.add(new JLabel(" ")); // add an empty label for spacing
        receiptPanel.add(new JLabel("Total Price: $" + String.format("%.2f", totalPrice))); // add a label for the total price
        receiptPanel.add(new JLabel("Thank you for your valued business <3")); // add a thank you message

        frame.add(receiptPanel); // add the panel to the frame
        frame.setVisible(true); 
    }
}

