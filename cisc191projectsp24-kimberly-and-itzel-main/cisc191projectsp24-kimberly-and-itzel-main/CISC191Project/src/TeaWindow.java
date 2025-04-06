package Tea;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TeaWindow extends JFrame implements ActionListener {

    private String sizeSelected = "Small";
    private String teaSelected = "tea";
    private ArrayList<String> toppingsSelected = new ArrayList<>();
    private int sugarLevelSelected = 100;
    private int iceLevelSelected = 100;
    private double sizePrice = 5.00;
    private double toppingPrice = 0.00;
    private double totalPrice = 5.00;
    private JLabel bobaCup;
    private JLabel iceLabel;
    private Timer timer;

    public TeaWindow() {

        // create main panels
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel();

        // Set layout of top panel
        topPanel.setLayout(new FlowLayout());

        //create panels for options and set the layout for each
        JPanel sizePanel = new JPanel(new BorderLayout());
        sizePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JPanel teaPanel = new JPanel(new BorderLayout());
        teaPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JPanel sugarPanel = new JPanel(new BorderLayout());
        sugarPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JPanel icePanel = new JPanel(new BorderLayout());
        icePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        JPanel toppingPanel = new JPanel(new BorderLayout());
        toppingPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        // //add a label and an image to the main panel
        JPanel cupPanel = new JPanel(new GridBagLayout());
        bobaCup = new JLabel(new ImageIcon("Resources2/boba_cup.jpg"));
        cupPanel.add(bobaCup);
        mainPanel.add(cupPanel, BorderLayout.CENTER);

        // need to create the panel to hold the available toppings images
        JPanel toppingsDisplayPanel = new JPanel(new GridLayout(5, 1));
        toppingsDisplayPanel.setPreferredSize(new Dimension(200, 800));
        toppingsDisplayPanel.setBorder(BorderFactory.createTitledBorder("Available Toppings"));

        JLabel bobaImage = createToppingImageLabel("Resources2/boba.jpg");
        JLabel lycheeImage = createToppingImageLabel("Resources2/lychee.jpg");
        JLabel cremeImage = createToppingImageLabel("Resources2/creme.jpg");
        JLabel cheeseImage = createToppingImageLabel("Resources2/cheese.jpg");

        toppingsDisplayPanel.add(new JLabel("Golden Boba", SwingConstants.CENTER));
        toppingsDisplayPanel.add(bobaImage);
        toppingsDisplayPanel.add(new JLabel("Lychee Jelly", SwingConstants.CENTER));
        toppingsDisplayPanel.add(lycheeImage);
        toppingsDisplayPanel.add(new JLabel("Creme Brulee", SwingConstants.CENTER));
        toppingsDisplayPanel.add(cremeImage);
        toppingsDisplayPanel.add(new JLabel("Cheese Foam", SwingConstants.CENTER));
        toppingsDisplayPanel.add(cheeseImage);

        mainPanel.add(toppingsDisplayPanel, BorderLayout.LINE_END);

        // have to make the panel to hold the ice images
        JPanel icePanelContainer = new JPanel(new BorderLayout());
        iceLabel = new JLabel();
        icePanelContainer.add(iceLabel, BorderLayout.CENTER);
        icePanelContainer.setBorder(BorderFactory.createTitledBorder("Ice Level"));

        mainPanel.add(icePanelContainer, BorderLayout.LINE_START);

        // this is for making buttons for sizes
        JPanel sizeButtons = new JPanel(); // Panel to hold size buttons
        ButtonGroup sizeGroup = new ButtonGroup();
        String[] sizes = {"Small", "Medium", "Large"};

        // gotta add buttons to panel and button group
        for (String size : sizes) {
            JRadioButton sizeButton = new JRadioButton(size);
            sizeGroup.add(sizeButton);
            sizeButton.addActionListener(e -> {
                JRadioButton selectedButton = (JRadioButton) e.getSource();
                sizeSelected = selectedButton.getText();

                switch (sizeSelected) {
                    case "Small":
                        bobaCup.setIcon(new ImageIcon("Resources2/smallCup.jpg"));
                        sizePrice = 5.00;
                        break;
                    case "Medium":
                        bobaCup.setIcon(new ImageIcon("Resources2/medCup.jpg"));
                        sizePrice = 6.00;
                        break;
                    case "Large":
                        bobaCup.setIcon(new ImageIcon("Resources2/largeCup.jpg"));
                        sizePrice = 7.00;
                        break;
                }
                iceLabel.setIcon(null); // this will reset ice label when changing size
                calculateTotalPrice();
            });
            sizeButtons.add(sizeButton);
        }

        // this will make buttons for tea options
        JPanel teaButtons = new JPanel(); // Panel to hold tea buttons
        ButtonGroup teaGroup = new ButtonGroup();
        String[] teas = {"Matcha Tea", "Oolong Tea", "Black Tea", "Jasmine Tea", "Thai Tea"};

        // this will add buttons to panel and button group
        for (String tea : teas) {
            JRadioButton teaButton = new JRadioButton(tea);
            teaGroup.add(teaButton);
            teaButton.addActionListener(e -> {
                JRadioButton selectedButton = (JRadioButton) e.getSource();
                teaSelected = selectedButton.getText();
                switch (teaSelected) {
                    case "Matcha Tea":
                        if (sizeSelected.equals("Small")) bobaCup.setIcon(new ImageIcon("Resources2/smallMatcha.jpg"));
                        else if (sizeSelected.equals("Medium")) bobaCup.setIcon(new ImageIcon("Resources2/medMatcha.jpg"));
                        else if (sizeSelected.equals("Large")) bobaCup.setIcon(new ImageIcon("Resources2/larMatcha.jpg"));
                        break;
                    case "Oolong Tea":
                        if (sizeSelected.equals("Small")) bobaCup.setIcon(new ImageIcon("Resources2/smallOolong.jpg"));
                        else if (sizeSelected.equals("Medium")) bobaCup.setIcon(new ImageIcon("Resources2/medOolong.jpg"));
                        else if (sizeSelected.equals("Large")) bobaCup.setIcon(new ImageIcon("Resources2/larOolong.jpg"));
                        break;
                    case "Black Tea":
                        if (sizeSelected.equals("Small")) bobaCup.setIcon(new ImageIcon("Resources2/smallBlack.jpg"));
                        else if (sizeSelected.equals("Medium")) bobaCup.setIcon(new ImageIcon("Resources2/medBlack.jpg"));
                        else if (sizeSelected.equals("Large")) bobaCup.setIcon(new ImageIcon("Resources2/larBlack.jpg"));
                        break;
                    case "Jasmine Tea":
                        if (sizeSelected.equals("Small")) bobaCup.setIcon(new ImageIcon("Resources2/smallJas.jpg"));
                        else if (sizeSelected.equals("Medium")) bobaCup.setIcon(new ImageIcon("Resources2/medJas.jpg"));
                        else if (sizeSelected.equals("Large")) bobaCup.setIcon(new ImageIcon("Resources2/larJas.jpg"));
                        break;
                    case "Thai Tea":
                        if (sizeSelected.equals("Small")) bobaCup.setIcon(new ImageIcon("Resources2/smallThai.jpg"));
                        else if (sizeSelected.equals("Medium")) bobaCup.setIcon(new ImageIcon("Resources2/medThai.jpg"));
                        else if (sizeSelected.equals("Large")) bobaCup.setIcon(new ImageIcon("Resources2/larThai.jpg"));
                        break;
                }
                iceLabel.setIcon(null); // this is gonna reset ice label when changing tea
            });
            teaButtons.add(teaButton);
        }

        // this is gonna create buttons for sugar percentages
        JPanel sugarButtons = new JPanel(); // Panel to hold sugar buttons
        ButtonGroup sugarGroup = new ButtonGroup();
        String[] sugarLevels = {"100%", "75%", "50%", "25%"};

        // add buttons to panel and button group
        for (String sugarLevel : sugarLevels) {
            JRadioButton sugarButton = new JRadioButton(sugarLevel);
            sugarGroup.add(sugarButton);
            sugarButton.addActionListener(e -> {
                JRadioButton selectedButton = (JRadioButton) e.getSource();
                String sugarText = selectedButton.getText();
                sugarLevelSelected = Integer.parseInt(sugarText.replace("%", ""));
            });
            sugarButtons.add(sugarButton);
        }

        // this will being going to create buttons for ice level
        JPanel iceButtons = new JPanel();
        ButtonGroup iceGroup = new ButtonGroup(); // this is a panel to hold ice buttons
        JRadioButton iceButton1 = new JRadioButton("Regular ice");
        JRadioButton iceButton2 = new JRadioButton("Half ice");

        iceGroup.add(iceButton1);
        iceGroup.add(iceButton2);

        iceButton1.addActionListener(e -> {
            JRadioButton selectedButton = (JRadioButton) e.getSource();
            iceLevelSelected = selectedButton.getText().equals("Regular ice") ? 100 : 50;
            updateIceLabel();
        });

        iceButton2.addActionListener(e -> {
            JRadioButton selectedButton = (JRadioButton) e.getSource();
            iceLevelSelected = selectedButton.getText().equals("Half ice") ? 50 : 100;
            updateIceLabel();
        });

        iceButtons.add(iceButton1);
        iceButtons.add(iceButton2);

        // makes buttons for toppings
        JPanel toppingsButtons = new JPanel(); // the panel to hold toppings buttons
        String[] toppings = {"Golden Boba", "Lychee Jelly", "Creme Brulee", "Cheese Foam"};

        for (String topping : toppings) {
            JRadioButton toppingsButton = new JRadioButton(topping);
            toppingsButton.addActionListener(e -> {
                JRadioButton selectedButton = (JRadioButton) e.getSource();
                String toppingSelected = selectedButton.getText();

                if (toppingsSelected.contains(toppingSelected)) {
                    toppingsSelected.remove(toppingSelected);
                    toppingPrice -= 0.50;
                } else {
                    toppingsSelected.add(toppingSelected);
                    toppingPrice += 0.50;
                }
                calculateTotalPrice();
            });
            toppingsButtons.add(toppingsButton);
        }

        // makes the labels for options
        JLabel sizeLabel = new JLabel("Pick your size");
        sizeLabel.setHorizontalAlignment(JLabel.CENTER);
        sizeLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel teaLabel = new JLabel("Pick your choice of tea");
        teaLabel.setHorizontalAlignment(JLabel.CENTER);
        teaLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel sugarLabel = new JLabel("Choose your sweetness level");
        sugarLabel.setHorizontalAlignment(JLabel.CENTER);
        sugarLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel iceLabelHeader = new JLabel("Choose your ice level");
        iceLabelHeader.setHorizontalAlignment(JLabel.CENTER);
        iceLabelHeader.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel toppingLabel = new JLabel("Choose your toppings");
        toppingLabel.setHorizontalAlignment(JLabel.CENTER);
        toppingLabel.setBorder(BorderFactory.createLineBorder(Color.black));

        // Add the widgets to the corresponding panels
        sizePanel.add(sizeLabel, BorderLayout.NORTH);
        sizePanel.add(sizeButtons, BorderLayout.CENTER);

        teaPanel.add(teaLabel, BorderLayout.NORTH);
        teaPanel.add(teaButtons, BorderLayout.CENTER);

        sugarPanel.add(sugarLabel, BorderLayout.NORTH);
        sugarPanel.add(sugarButtons, BorderLayout.CENTER);

        icePanel.add(iceLabelHeader, BorderLayout.NORTH);
        icePanel.add(iceButtons, BorderLayout.CENTER);

        toppingPanel.add(toppingLabel, BorderLayout.NORTH);
        toppingPanel.add(toppingsButtons, BorderLayout.CENTER);

        // gonna add options panels to top panels
        topPanel.add(sizePanel);
        topPanel.add(teaPanel);
        topPanel.add(sugarPanel);
        topPanel.add(icePanel);
        topPanel.add(toppingPanel);

        // will add cup image to main panel
        mainPanel.add(cupPanel, BorderLayout.CENTER);
        mainPanel.setBackground(Color.white);

        displayWelcomeScreen();

        // add the panels to the frame
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);

        // add Finish button
        JButton finishButton = new JButton("FINISHED");
        finishButton.addActionListener(this);
        finishButton.setPreferredSize(new Dimension(100, 50));
        cupPanel.add(finishButton, new GridBagConstraints());

        // add the panels to the frame
        this.add(mainPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Boba Oasis Cafe");
        this.pack();
        this.setVisible(true);
    }

    private JLabel createToppingImageLabel(String imagePath) {
        ImageIcon icon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(icon);
        label.setPreferredSize(new Dimension(150, 150));
        return label;
    }

    private void displayWelcomeScreen() {
        final JWindow welcome = new JWindow(this);
        welcome.pack();
        welcome.setSize(1800, 1200);
        welcome.setVisible(true);

        JPanel panel = new JPanel();
        welcome.add(panel);
        JLabel teaShop = new JLabel(new ImageIcon("Resources2/Welcome.jpg"));
        teaShop.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(teaShop);
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));

        JProgressBar progress = new JProgressBar(0, 100);
        progress.setForeground(Color.PINK);
        welcome.add(BorderLayout.PAGE_END, progress);
        welcome.revalidate();

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = progress.getValue();
                if (x == 100) {
                    welcome.dispose();
                    TeaWindow.this.setVisible(true);
                    timer.stop();
                } else {
                    progress.setValue(x + 4);
                }
            }
        });

        timer.start();
    }

    private void calculateTotalPrice() {
        totalPrice = sizePrice + toppingPrice;
    }

    private void updateIceLabel() {
        if (iceLevelSelected == 100) {
            iceLabel.setIcon(new ImageIcon(new ImageIcon("Resources2/regIce.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } else if (iceLevelSelected == 50) {
            iceLabel.setIcon(new ImageIcon(new ImageIcon("Resources2/halfIce.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        } else {
            iceLabel.setIcon(null);
        }
    }

    public void setSize(String size) {
        sizeSelected = size;
    }

    public String getSize1() {
        return sizeSelected;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("FINISHED")) {
            new Receipt(sizeSelected, teaSelected, toppingsSelected.toArray(new String[0]), sugarLevelSelected, iceLevelSelected, sizePrice, toppingPrice, totalPrice);
        } else {
            JOptionPane.showMessageDialog(clickedButton, "Going to the " + clickedButton.getText());
        }
    }

    public static void main(String[] args) {
        new TeaWindow();
    }
}
