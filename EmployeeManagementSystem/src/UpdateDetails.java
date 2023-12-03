import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateDetails extends JFrame implements ActionListener {
    JComboBox<Integer> employeeIdComboBox;
    JComboBox departmentComboBox;
    String[] departments = { "HR", "IT", "Finance", "Marketing", "Sales", "Management" };
    JTextField addressField, phoneField, emailField, designationField;
    JLabel employeeIdLabel, addressLabel, phoneLabel, emailLabel, designationLabel, departmentLabel;
    JButton updateButton;
    Connectionclass connection;

    public UpdateDetails() {
        connection = new Connectionclass();
        ImageIcon backgroundImage = new ImageIcon(new ImageIcon("/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/remove.jpg").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
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

        addressLabel = new JLabel("Address");
        addressLabel.setBounds(50, 100, 150, 30);
        addressLabel.setFont(new Font("Arial", Font.BOLD, 20));
        addressLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(250, 100, 180, 30);
        addressField.setFont(new Font("Arial", Font.BOLD, 20));
        addressField.setForeground(Color.BLACK);
        backgroundImageLabel.add(addressField);

        phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(50, 150, 150, 30);
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 20));
        phoneLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(250, 150, 180, 30);
        phoneField.setFont(new Font("Arial", Font.BOLD, 20));
        phoneField.setForeground(Color.BLACK);
        backgroundImageLabel.add(phoneField);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 200, 150, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(250, 200, 180, 30);
        emailField.setFont(new Font("Arial", Font.BOLD, 20));
        emailField.setForeground(Color.BLACK);
        backgroundImageLabel.add(emailField);

        designationLabel = new JLabel("Designation");
        designationLabel.setBounds(50, 250, 150, 30);
        designationLabel.setFont(new Font("Arial", Font.BOLD, 20));
        designationLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(designationLabel);

        designationField = new JTextField();
        designationField.setBounds(250, 250, 180, 30);
        designationField.setFont(new Font("Arial", Font.BOLD, 20));
        designationField.setForeground(Color.BLACK);
        backgroundImageLabel.add(designationField);

        departmentLabel = new JLabel("Department");
        departmentLabel.setBounds(50, 300, 150, 30);
        departmentLabel.setFont(new Font("Arial", Font.BOLD, 20));
        departmentLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(departmentLabel);

        departmentComboBox = new JComboBox(departments);
        departmentComboBox.setBounds(250, 300, 180, 30);
        departmentComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        departmentComboBox.setForeground(Color.BLACK);
        backgroundImageLabel.add(departmentComboBox);


        updateButton = new JButton("Update");
        updateButton.setBounds(150, 350, 150, 30);
        updateButton.setFont(new Font("Arial", Font.PLAIN, 20));
        updateButton.setForeground(Color.WHITE);
        updateButton.setBackground(Color.BLACK);
        updateButton.addActionListener(this);
        backgroundImageLabel.add(updateButton);

        fillComboBox();

        setLayout(null);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 250);
        setVisible(true);
    }

    private void fillComboBox() {
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

    private void fillFields(int employeeId) {
        try {
            PreparedStatement ps = connection.conn.prepareStatement("SELECT * FROM Employee WHERE employee_id = ?");
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                addressField.setText(rs.getString("address"));
                phoneField.setText(rs.getString("phone"));
                emailField.setText(rs.getString("email"));
                designationField.setText(rs.getString("designation"));
                departmentComboBox.setSelectedItem(rs.getString("department"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateDetails(int employeeId) {
        try {
            PreparedStatement ps = connection.conn.prepareStatement("UPDATE Employee SET address = ?, phone = ?, email = ?, designation = ?, department = ? WHERE employee_id = ?");
            ps.setString(1, addressField.getText());
            ps.setString(2, phoneField.getText());
            ps.setString(3, emailField.getText());
            ps.setString(4, designationField.getText());
            ps.setString(5, (String) departmentComboBox.getSelectedItem());
            ps.setInt(6, employeeId);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Details updated successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == employeeIdComboBox) {
            fillFields((int) employeeIdComboBox.getSelectedItem());
        } else if (e.getSource() == updateButton) {
            updateDetails((int) employeeIdComboBox.getSelectedItem());
        }
    }

    public static void main(String[] args) {
        new UpdateDetails();
    }
}