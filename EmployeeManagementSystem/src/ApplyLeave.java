import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class ApplyLeave extends JFrame implements ActionListener {
    JComboBox<String> employeeIdComboBox;
    JTextField nameField, emailField;
    JSpinner startDateSpinner, endDateSpinner;
    JComboBox<String> reasonComboBox;
    JButton submitButton, cancelButton;
    JLabel employeeIdLabel, nameLabel, emailLabel, startDateLabel, endDateLabel, reasonLabel;

    ApplyLeave() {
        setLayout(new FlowLayout());

        ImageIcon backgroundImage = new ImageIcon(new ImageIcon("/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/8934923.jpg").getImage().getScaledInstance(600, 700, Image.SCALE_DEFAULT));
        JLabel backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setBounds(0, 0, 600, 700);
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
        nameField.setEditable(false);
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
        emailField.setEditable(false);
        backgroundImageLabel.add(emailField);

        startDateLabel = new JLabel("Start Date");
        startDateLabel.setBounds(50, 200, 150, 30);
        startDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        startDateLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(startDateLabel);

        startDateSpinner = new JSpinner(new SpinnerDateModel());
        startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy-MM-dd"));
        startDateSpinner.setBounds(250, 200, 180, 30);
        startDateSpinner.setFont(new Font("Arial", Font.BOLD, 20));
        startDateSpinner.setForeground(Color.BLACK);
        backgroundImageLabel.add(startDateSpinner);

        endDateLabel = new JLabel("End Date");
        endDateLabel.setBounds(50, 250, 150, 30);
        endDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        endDateLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(endDateLabel);

        endDateSpinner = new JSpinner(new SpinnerDateModel());
        endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "yyyy-MM-dd"));
        endDateSpinner.setBounds(250, 250, 180, 30);
        endDateSpinner.setFont(new Font("Arial", Font.BOLD, 20));
        endDateSpinner.setForeground(Color.BLACK);
        backgroundImageLabel.add(endDateSpinner);

        reasonLabel = new JLabel("Reason");
        reasonLabel.setBounds(50, 300, 150, 30);
        reasonLabel.setFont(new Font("Arial", Font.BOLD, 20));
        reasonLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(reasonLabel);

        reasonComboBox = new JComboBox<>(new String[] {"Sick", "Vacation", "Personal", "Other"});
        reasonComboBox.setBounds(250, 300, 180, 30);
        reasonComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        reasonComboBox.setForeground(Color.BLACK);
        backgroundImageLabel.add(reasonComboBox);

        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 400, 150, 30);
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(Color.BLACK);
        submitButton.addActionListener(this);
        backgroundImageLabel.add(submitButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(350, 400, 150, 30);
        cancelButton.setFont(new Font("Arial", Font.PLAIN, 20));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.addActionListener(this);
        backgroundImageLabel.add(cancelButton);

        loadEmployeeIds();

        

        setLocation(400, 250);
        setVisible(true);
        setSize(600, 700);
    }

    private void loadEmployeeIds() {
        try {
            Connectionclass connectionclass = new Connectionclass();
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
            applyLeave();
        } else if (e.getSource() == cancelButton) {
            dispose();
        }
    }

    private void loadEmployeeDetails(String employeeId) {
        try {
            Connectionclass connectionclass = new Connectionclass();
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

    private void applyLeave() {
        try {
            Date startDate = (Date) startDateSpinner.getValue();
            Date endDate = (Date) endDateSpinner.getValue();
            Date today = new Date();
    
            if (startDate.before(today)) {
                JOptionPane.showMessageDialog(this, "Start date cannot be before today.");
                return;
            }
    
            if (endDate.before(startDate)) {
                JOptionPane.showMessageDialog(this, "End date cannot be before start date.");
                return;
            }
    
            Connectionclass connectionclass = new Connectionclass();
            PreparedStatement ps = connectionclass.conn.prepareStatement("INSERT INTO EmployeeLeave (employee_id, start_date, end_date, reason, status) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, (String) employeeIdComboBox.getSelectedItem());
            ps.setDate(2, new java.sql.Date(startDate.getTime()));
            ps.setDate(3, new java.sql.Date(endDate.getTime()));
            ps.setString(4, (String) reasonComboBox.getSelectedItem());
            ps.setString(5, "Pending");
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Leave applied successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error applying leave. Please try again.");
        }
    }

    public static void main(String[] args) {
        new ApplyLeave();
    }
}