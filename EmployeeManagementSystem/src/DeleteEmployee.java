import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteEmployee extends JFrame implements ActionListener {
    JComboBox<Integer> employeeIdComboBox;
    JTextField nameField, fatherNameField, phoneField, emailField;
    JButton deleteButton;
    Connectionclass connection;

    public DeleteEmployee() {
        connection = new Connectionclass();

        ImageIcon backgroundImage = new ImageIcon(new ImageIcon("/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/3173472.jpg").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        JLabel backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setBounds(0, 0, 500, 500);
        add(backgroundImageLabel);

        JLabel employeeIdLabel = new JLabel("Employee ID");
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

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 100, 150, 30);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(nameLabel);


        nameField = new JTextField();
        nameField.setBounds(250, 100, 180, 30);
        nameField.setFont(new Font("Arial", Font.BOLD, 20));
        nameField.setForeground(Color.BLACK);
        nameField.setEditable(false);
        backgroundImageLabel.add(nameField);

        JLabel fatherNameLabel = new JLabel("Father Name");
        fatherNameLabel.setBounds(50, 150, 150, 30);
        fatherNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        fatherNameLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(fatherNameLabel);


        fatherNameField = new JTextField();
        fatherNameField.setBounds(250, 150, 180, 30);
        fatherNameField.setFont(new Font("Arial", Font.BOLD, 20));
        fatherNameField.setForeground(Color.BLACK);
        fatherNameField.setEditable(false);
        backgroundImageLabel.add(fatherNameField);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(50, 200, 150, 30);
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 20));
        phoneLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(250, 200, 180, 30);
        phoneField.setFont(new Font("Arial", Font.BOLD, 20));
        phoneField.setForeground(Color.BLACK);
        phoneField.setEditable(false);
        backgroundImageLabel.add(phoneField);
        


        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 250, 150, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(250, 250, 180, 30);
        emailField.setFont(new Font("Arial", Font.BOLD, 20));
        emailField.setForeground(Color.BLACK);
        emailField.setEditable(false);
        backgroundImageLabel.add(emailField);



        deleteButton = new JButton("Delete");
        deleteButton.setBounds(50, 300, 150, 30);
        deleteButton.setFont(new Font("Arial", Font.BOLD, 20));
        deleteButton.setForeground(Color.BLACK);
        deleteButton.addActionListener(this);
        backgroundImageLabel.add(deleteButton);


        loadEmployeeIds();

        setLayout(new FlowLayout());
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadEmployeeIds() {
        try {
            PreparedStatement ps = connection.conn.prepareStatement("SELECT employee_id FROM Employee");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                employeeIdComboBox.addItem(rs.getInt("employee_id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadEmployeeDetails(int employeeId) {
        try {
            PreparedStatement ps = connection.conn.prepareStatement("SELECT name, father_name, phone, email FROM Employee WHERE employee_id = ?");
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nameField.setText(rs.getString("name"));
                fatherNameField.setText(rs.getString("father_name"));
                phoneField.setText(rs.getString("phone"));
                emailField.setText(rs.getString("email"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteEmployee(int employeeId) {
        try {
            // Manually delete Attendance records first
            PreparedStatement deleteAttendance = connection.conn.prepareStatement("DELETE FROM Attendance WHERE employee_id = ?");
            deleteAttendance.setInt(1, employeeId);
            deleteAttendance.executeUpdate();
    
            // Now you can safely delete the employee
            PreparedStatement deleteEmployee = connection.conn.prepareStatement("DELETE FROM Employee WHERE employee_id = ?");
            deleteEmployee.setInt(1, employeeId);
            deleteEmployee.executeUpdate();
    
            JOptionPane.showMessageDialog(null, "Employee deleted successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == employeeIdComboBox) {
            loadEmployeeDetails((int) employeeIdComboBox.getSelectedItem());
        } else if (e.getSource() == deleteButton) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete " + nameField.getText() + "?", "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                deleteEmployee((int) employeeIdComboBox.getSelectedItem());
            }
        }
    }

    public static void main(String[] args) {
        new DeleteEmployee();
    }
}