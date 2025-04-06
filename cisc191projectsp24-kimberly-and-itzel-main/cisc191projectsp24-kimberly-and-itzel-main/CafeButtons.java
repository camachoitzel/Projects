package Tea;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CafeButtons implements ActionListener{

    private JFrame frame;
    
    
//	private JFrame backFrame;
//	private ImageIcon backIcon;
//	private JLabel myLabel;
	
	public CafeButtons() {
    	
    	
   	
//    	backIcon = new ImageIcon(this.getClass().getResource("IMG_2904.jpg"));
//    	myLabel = new JLabel(backIcon);
//    	myLabel.setSize(1000,450);	
//    	backFrame.add(myLabel);
    	
    	
    	
    	
    	frame = new JFrame("Cafe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1800, 1000);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        
        
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        
        
        
        ImageIcon backgroundImage = createImageIcon("Resources2/boba_cup.jpg"); //needs a new method, its at the bottom
        if (backgroundImage != null) {
            JLabel backgroundLabel = new JLabel(backgroundImage);
            backgroundLabel.setBounds(0, 0, 1800, 1000);
            panel.add(backgroundLabel);
        } 
        else 
        {
            System.err.println("Error loading background image.");
        }
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0,0,1800,1000); //this is going to take over the whole background
        panel.add(backgroundLabel);
        
        
        
       
        Container container = frame.getContentPane();
        container.setLayout(null);
        container.add(panel);
       
       
        JButton btn1 = new JButton("Hi there welcome to the Boba Oasis Cafe! Please choose from the following.");
        btn1.setBounds(500, 100, 700, 400); //this is x,y, width, and height
        container.add(btn1);
        btn1.addActionListener(this);

        JButton btn2 = new JButton("MENU");
        btn2.setBounds(750, 550, 200, 80);
        container.add(btn2);
        btn2.addActionListener(this);

        JButton btn3 = new JButton("ORDER");
        btn3.setBounds(750, 630, 200, 80);
        container.add(btn3);
        btn3.addActionListener(this);
        
        JButton btn4 = new JButton("ABOUT US");
        btn4.setBounds(750, 710, 200, 80);
        container.add(btn4);
        btn4.addActionListener(this);

        
        
        btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 ImageIcon backgroundImage = createImageIcon("Resources2/boba_cup.jpg"); //needs a new method, its at the bottom
			        if (backgroundImage != null) {
			            JLabel backgroundLabel = new JLabel(backgroundImage);
			            backgroundLabel.setBounds(0, 0, 1800, 1000);
			            panel.add(backgroundLabel);
			        } 
			        else 
			        {
			            System.err.println("Error loading background image.");
			        }
			        JLabel backgroundLabel = new JLabel(backgroundImage);
			        backgroundLabel.setBounds(0,0,1800,1000); //this is going take over the whole background
			        panel.add(backgroundLabel);
			}
        	
        });
	
        
        
        
     
        frame.getContentPane().add(panel);
        
        frame.setVisible(true);
    }

    private ImageIcon createImageIcon(String path) {
		
		java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
   
		return null;
        }
	}

	public static void main(String args[]) {
        new CafeButtons();
    }

	@Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equals("ORDER")) {
            // now the order will show the menu
            Tea teaWindow = new Tea();
        } else {
            JOptionPane.showMessageDialog(clickedButton, "Going to the " + clickedButton.getText());
        }
    
    }
}
