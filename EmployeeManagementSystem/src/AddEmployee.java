import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.time.LocalDate;



import javax.swing.*;

import com.toedter.calendar.JDateChooser;

public class AddEmployee implements ActionListener {
    JFrame frame;
    JLabel mainHeading, nameLabel, ageLabel, addressLabel, phoneLabel, emailLabel, designationLabel, departmentLabel, employeeIdLabel, fatherNameLabel, dobLabel;
    JTextField nameField, ageField, addressField, phoneField, emailField, designationField, employeeIdTextField, fatherNamTextField;
    JComboBox departmentComboBox;
    JDateChooser dobField;
    String[] departments = {"HR", "IT", "Finance", "Marketing", "Sales", "Management"};

    JButton submitButton, cancelButton;
    AddEmployee() {
        frame = new JFrame("Add Employee");
        frame.setBackground(Color.WHITE);
        frame.setLayout(null);
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon backgroundImage = new ImageIcon(new ImageIcon("/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/18940.jpg").getImage().getScaledInstance(900, 600, Image.SCALE_DEFAULT));
        JLabel l1 = new JLabel(backgroundImage);
        l1.setBounds(0, 0, 900, 600);
        frame.add(l1);

        mainHeading = new JLabel("New Employee Details");
        mainHeading.setBounds(300, 30, 500, 50);
        mainHeading.setFont(new Font("Arial", Font.BOLD, 30));
        mainHeading.setForeground(Color.BLACK   );
        l1.add(mainHeading);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 150, 150, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(Color.BLACK);
        l1.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(200, 150, 150, 30);
        nameField.setFont(new Font("Arial", Font.BOLD, 20));
        nameField.setForeground(Color.BLACK);
        l1.add(nameField);

        ageLabel = new JLabel("Age");
        ageLabel.setBounds(50, 200, 150, 30);
        ageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        ageLabel.setForeground(Color.BLACK);
        l1.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(200, 200, 150, 30);
        ageField.setFont(new Font("Arial", Font.BOLD, 20));
        ageField.setForeground(Color.BLACK);
        l1.add(ageField);

        addressLabel = new JLabel("Address");
        addressLabel.setBounds(50, 250, 150, 30);
        addressLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addressLabel.setForeground(Color.BLACK);
        l1.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(200, 250, 150, 30);
        addressField.setFont(new Font("Arial", Font.BOLD, 20));
        addressField.setForeground(Color.BLACK);
        l1.add(addressField);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 300, 150, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(Color.BLACK);
        l1.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(200, 300, 150, 30);
        emailField.setFont(new Font("Arial", Font.BOLD, 20));
        emailField.setForeground(Color.BLACK);
        l1.add(emailField);

        phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(50, 350, 150, 30);
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 20));
        phoneLabel.setForeground(Color.BLACK);
        l1.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(200, 350, 150, 30);
        phoneField.setFont(new Font("Arial", Font.BOLD, 20));
        phoneField.setForeground(Color.BLACK);
        l1.add(phoneField);

        designationLabel = new JLabel("Designation");
        designationLabel.setBounds(50, 400, 150, 30);
        designationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        designationLabel.setForeground(Color.BLACK);
        l1.add(designationLabel);

        designationField = new JTextField();
        designationField.setBounds(200, 400, 150, 30);
        designationField.setFont(new Font("Arial", Font.BOLD, 20));
        designationField.setForeground(Color.BLACK);
        l1.add(designationField);

        //next column

        fatherNameLabel = new JLabel("Father Name");
        fatherNameLabel.setBounds(450, 150, 150, 30);
        fatherNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        fatherNameLabel.setForeground(Color.BLACK);
        l1.add(fatherNameLabel);

        fatherNamTextField = new JTextField();
        fatherNamTextField.setBounds(650, 150, 150, 30);
        fatherNamTextField.setFont(new Font("Arial", Font.BOLD, 20));
        fatherNamTextField.setForeground(Color.BLACK);
        l1.add(fatherNamTextField);


        employeeIdLabel = new JLabel("Employee ID");
        employeeIdLabel.setBounds(450, 200, 150, 30);
        employeeIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
        employeeIdLabel.setForeground(Color.BLACK);
        l1.add(employeeIdLabel);



        dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(450, 250, 150, 30);
        dobLabel.setFont(new Font("Arial", Font.BOLD, 20));
        dobLabel.setForeground(Color.BLACK);
        l1.add(dobLabel);

        dobField = new JDateChooser();
        dobField.setBounds(650, 250, 150, 30);
        dobField.setDateFormatString("yyyy-MM-dd");
        l1.add(dobField);



        

        departmentLabel = new JLabel("Department");
        departmentLabel.setBounds(450, 300, 150, 30);
        departmentLabel.setFont(new Font("Arial", Font.BOLD, 20));
        departmentLabel.setForeground(Color.BLACK);
        l1.add(departmentLabel);

        departmentComboBox = new JComboBox(departments);
        departmentComboBox.setBounds(650, 300, 150, 30);
        departmentComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        departmentComboBox.setForeground(Color.BLACK);
        l1.add(departmentComboBox);

        // dobField = new JTextField();
        // dobField.setBounds(650, 250, 150, 30);
        // dobField.setFont(new Font("Arial", Font.BOLD, 20));
        // dobField.setForeground(Color.BLACK);
        // l1.add(dobField);

        submitButton = new JButton("Submit");
        submitButton.setBounds(250, 480, 150, 40);
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(Color.BLACK);
        submitButton.addActionListener((ActionListener) this);
        l1.add(submitButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(450, 480, 150, 40);
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.addActionListener((ActionListener) this);
        l1.add(cancelButton);





        
        

        frame.setLocation(500, 250);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new AddEmployee();   
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submitButton) {
            String name = nameField.getText();
            String age = ageField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String designation = designationField.getText();
            String fatherName = fatherNamTextField.getText();
            LocalDate dob = dobField.getDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            String department = (String) departmentComboBox.getSelectedItem();

            PreparedStatement ps;
            try {
                Connectionclass connection = new Connectionclass();
                ps = connection.conn.prepareStatement("INSERT INTO Employee (name, father_name, age, address, email, phone, designation, date_of_birth, department) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps.setString(1, name);
                ps.setString(2, fatherName);
                ps.setString(3, age);
                ps.setString(4, address);
                ps.setString(5, email);
                ps.setString(6, phone);
                ps.setString(7, designation);
                ps.setString(8, dob.toString());
                ps.setString(9, department);


                
                ps.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Employee added successfully");
                frame.setVisible(false);
                new Dashboard();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            
        }
        else if(e.getSource() == cancelButton) {
            frame.setVisible(false);
            new Dashboard();
        }
    }
}
