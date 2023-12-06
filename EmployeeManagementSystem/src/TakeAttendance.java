import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TakeAttendance extends JFrame implements ActionListener {
    JComboBox<String> employeeIdComboBox, firstHalfComboBox, secondHalfComboBox;
    JLabel employeeIdLabel, nameLabel, emailLabel, firstHalfLabel, secondHalfLabel;
    JTextField nameField, emailField;
    JButton submitButton;
    Connectionclass connectionclass;

    TakeAttendance() {
        
        connectionclass = new Connectionclass();
        ImageIcon backgroundImage = new ImageIcon(new ImageIcon("/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/3870277.jpg").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        JLabel backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setBounds(0, 0, 500, 500);
        add(backgroundImageLabel);

        employeeIdLabel = new JLabel("Employee ID");
        employeeIdLabel.setBounds(50, 50, 150, 30);
        employeeIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
        employeeIdLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(employeeIdLabel);

        employeeIdComboBox = new JComboBox<>();
        employeeIdComboBox.setBounds(250, 50, 180, 30);
        employeeIdComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        employeeIdComboBox.setForeground(Color.BLACK);
        employeeIdComboBox.addActionListener(this);
        backgroundImageLabel.add(employeeIdComboBox);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 100, 150, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(nameLabel);  

        nameField = new JTextField();
        nameField.setBounds(250, 100, 180, 30);
        nameField.setFont(new Font("Arial", Font.BOLD, 20));
        nameField.setForeground(Color.BLACK);
        backgroundImageLabel.add(nameField);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 150, 150, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(250, 150, 180, 30);
        emailField.setFont(new Font("Arial", Font.BOLD, 20));
        emailField.setForeground(Color.BLACK);
        backgroundImageLabel.add(emailField);

        firstHalfLabel = new JLabel("First Half");
        firstHalfLabel.setBounds(50, 200, 150, 30);
        firstHalfLabel.setFont(new Font("Arial", Font.BOLD, 20));
        firstHalfLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(firstHalfLabel);


        firstHalfComboBox = new JComboBox<>();
        firstHalfComboBox.setBounds(250, 200, 180, 30);
        firstHalfComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        firstHalfComboBox.setForeground(Color.BLACK);
        firstHalfComboBox.addItem("Present");
        firstHalfComboBox.addItem("Absent");
        backgroundImageLabel.add(firstHalfComboBox);

        secondHalfLabel = new JLabel("Second Half");
        secondHalfLabel.setBounds(50, 250, 150, 30);
        secondHalfLabel.setFont(new Font("Arial", Font.BOLD, 20));
        secondHalfLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(secondHalfLabel);
        

        secondHalfComboBox = new JComboBox<>();
        secondHalfComboBox.setBounds(250, 250, 180, 30);
        secondHalfComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        secondHalfComboBox.setForeground(Color.BLACK);
        secondHalfComboBox.addItem("Present");
        secondHalfComboBox.addItem("Absent");
        backgroundImageLabel.add(secondHalfComboBox);

        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 350, 150, 30);
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(Color.BLACK);
        submitButton.addActionListener(this);
        backgroundImageLabel.add(submitButton);

        loadEmployeeIds();

        setLayout(null);
        setVisible(true);
        setSize(500, 500);
        setLocation(400, 250);


    }

    private void loadEmployeeIds() {
        try {
            // Connectionclass connectionclass = new Connectionclass();
            PreparedStatement ps = connectionclass.conn.prepareStatement("SELECT employee_id FROM Employee");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employeeIdComboBox.addItem(rs.getString("employee_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading employee IDs. Please try again.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == employeeIdComboBox) {
            loadEmployeeDetails((String) employeeIdComboBox.getSelectedItem());
        } else if (e.getSource() == submitButton) {
            insertAttendanceData();
        }
    }

    private void loadEmployeeDetails(String employeeId) {
        try {
            // Connectionclass connectionclass = new Connectionclass();
            PreparedStatement ps = connectionclass.conn.prepareStatement("SELECT name, email FROM Employee WHERE employee_id = ?");
            ps.setString(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                emailField.setText(rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading employee details. Please try again.");
        }
    }

    private void insertAttendanceData() {
        try {
            // Connectionclass connectionclass = new Connectionclass();
            PreparedStatement ps = connectionclass.conn.prepareStatement("INSERT INTO Attendance (employee_id, first_half_status, second_half_status, date) VALUES (?, ?, ?, CURDATE())");
            ps.setString(1, (String) employeeIdComboBox.getSelectedItem());
            ps.setString(2, (String) firstHalfComboBox.getSelectedItem());
            ps.setString(3, (String) secondHalfComboBox.getSelectedItem());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Attendance recorded successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error recording attendance. Please try again.");
        }
    }

    public static void main(String[] args) {
        new TakeAttendance();
    }
}
