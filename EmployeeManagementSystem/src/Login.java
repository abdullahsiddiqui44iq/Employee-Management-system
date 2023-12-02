import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Arrays;
import java.sql.PreparedStatement;

public class Login extends JFrame implements ActionListener{
    JFrame loginFrame;
    JLabel usernameLabel, passwordLabel;
    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton, cancelButton;

    Login() {
        loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 300);
        loginFrame.setLayout(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(50, 50, 100, 30);
        loginFrame.add(usernameLabel);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 100, 100, 30);
        loginFrame.add(passwordLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 30);
        loginFrame.add(usernameField);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 30);
        loginFrame.add(passwordField);

        ImageIcon loginIcon = new ImageIcon(new ImageIcon("/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/login.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        JLabel l3 = new JLabel(loginIcon);
        l3.setBounds(320, 50, 150, 150);
        loginFrame.add(l3);


        loginButton = new JButton("Login");
        loginButton.setBackground(Color.BLACK);
        loginButton.setBounds(50, 150, 100, 30);
        loginButton.addActionListener(this);
        loginButton.setForeground(Color.WHITE);
        loginFrame.add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setBounds(200, 150, 100, 30);
        cancelButton.addActionListener(this);
        cancelButton.setForeground(Color.WHITE);
        loginFrame.add(cancelButton);

        loginFrame.getContentPane();
        loginFrame.setVisible(true);
        loginFrame.setSize(500, 240);
        loginFrame.setLocation(450, 250);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            try{
                Connectionclass connection = new Connectionclass();
                String username = usernameField.getText();
                char[] passwordChars = passwordField.getPassword();
                String password = new String(passwordChars);
                Arrays.fill(passwordChars, '0');

                PreparedStatement ps = connection.conn.prepareStatement("SELECT * FROM login WHERE username = ? AND password = ?");
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if(rs.next()){ 
                    JOptionPane.showMessageDialog(loginFrame, "Login Successful");
                    loginFrame.setVisible(false);
                    // new Dashboard();
                }
                else{
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password");
                }
                
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
    
        } else if (e.getSource() == cancelButton) {
            loginFrame.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }

}
