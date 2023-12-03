import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ViewProfile implements ActionListener {
    JFrame frame;
    JLabel backgroundImageLabel, employeeIdLabel;
    JTextField employeeIdTextField;
    JButton searchButton, cancelButton;

    ViewProfile(){
        frame = new JFrame("View Profile");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon backgroundImage = new ImageIcon(new ImageIcon("/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/details.jpg").getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT));
        backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setBounds(0, 0, 600, 400);
        frame.add(backgroundImageLabel);

        employeeIdLabel = new JLabel("Employee ID");
        employeeIdLabel.setBounds(50, 60, 150, 30);
        employeeIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
        employeeIdLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(employeeIdLabel);

        employeeIdTextField = new JTextField();
        employeeIdTextField.setBounds(250, 60, 180, 30);
        employeeIdTextField.setFont(new Font("Arial", Font.BOLD, 20));
        employeeIdTextField.setForeground(Color.BLACK);
        backgroundImageLabel.add(employeeIdTextField);

        searchButton = new JButton("Search");
        searchButton.setBounds(150, 150, 150, 30);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 20));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(Color.BLACK);
        searchButton.addActionListener(this);
        backgroundImageLabel.add(searchButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(350, 150, 150, 30);
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.addActionListener(this);
        backgroundImageLabel.add(cancelButton);



        frame.setLayout(null);
        frame.setLocation(400, 250);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == searchButton){
           frame.setVisible(false);
            new ViewProfileDetails(Integer.parseInt(employeeIdTextField.getText()));
             
        }
        else if(e.getSource() == cancelButton){
            frame.dispose();
            new Dashboard();
        }
    }

    public static void main(String[] args) {
        new ViewProfile();
    }

}
