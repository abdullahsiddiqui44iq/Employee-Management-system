import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddSalary extends JFrame implements ActionListener {
    JComboBox<Integer> employeeIdComboBox;
    JTextField basicSalaryField, bonusesField, deductionsField, employeeNameField, emailField;
    JLabel employeeIdLabel, employeeName , basicSalaryLabel, bonusesLabel, deductionsLabel, salaryDateLabel, emailLabel;
    JDateChooser salaryDateChooser;
    JButton submitButton;
    Connectionclass connection;
    
    public AddSalary() {
        connection = new Connectionclass();

        ImageIcon backgroundImage = new ImageIcon(new ImageIcon("/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/11116.jpg").getImage().getScaledInstance(600, 700, Image.SCALE_DEFAULT));
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

        employeeName = new JLabel("Employee Name");
        employeeName.setBounds(50, 100, 180, 30);
        employeeName.setFont(new Font("Arial", Font.BOLD, 20));
        employeeName.setForeground(Color.BLACK);
        backgroundImageLabel.add(employeeName);

        employeeNameField = new JTextField();
        employeeNameField.setBounds(250, 100, 250, 30);
        employeeNameField.setFont(new Font("Arial", Font.BOLD, 20));
        employeeNameField.setForeground(Color.BLACK);
        employeeNameField.setEditable(false);
        backgroundImageLabel.add(employeeNameField);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 150, 150, 30);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
        emailLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(250, 150, 250, 30);
        emailField.setFont(new Font("Arial", Font.BOLD, 20));
        emailField.setForeground(Color.BLACK);
        emailField.setEditable(false);
        backgroundImageLabel.add(emailField);

        basicSalaryLabel = new JLabel("Basic Salary");
        basicSalaryLabel.setBounds(50, 200, 150, 30);
        basicSalaryLabel.setFont(new Font("Arial", Font.BOLD, 20));
        basicSalaryLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(basicSalaryLabel);

        basicSalaryField = new JTextField();
        basicSalaryField.setBounds(250, 200, 250, 30);
        basicSalaryField.setFont(new Font("Arial", Font.BOLD, 20));
        basicSalaryField.setForeground(Color.BLACK);
        backgroundImageLabel.add(basicSalaryField);

        bonusesLabel = new JLabel("Bonuses");
        bonusesLabel.setBounds(50, 250, 150, 30);
        bonusesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        bonusesLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(bonusesLabel);

        bonusesField = new JTextField();
        bonusesField.setBounds(250, 250, 250, 30);
        bonusesField.setFont(new Font("Arial", Font.BOLD, 20));
        bonusesField.setForeground(Color.BLACK);
        backgroundImageLabel.add(bonusesField);

        deductionsLabel = new JLabel("Deductions");
        deductionsLabel.setBounds(50, 300, 150, 30);
        deductionsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        deductionsLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(deductionsLabel);

        deductionsField = new JTextField();
        deductionsField.setBounds(250, 300, 250, 30);
        deductionsField.setFont(new Font("Arial", Font.BOLD, 20));
        deductionsField.setForeground(Color.BLACK);
        backgroundImageLabel.add(deductionsField);

        salaryDateLabel = new JLabel("Salary Date");
        salaryDateLabel.setBounds(50, 350, 150, 30);
        salaryDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
        salaryDateLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(salaryDateLabel);

        salaryDateChooser = new JDateChooser();
        salaryDateChooser.setBounds(250, 350, 250, 30);
        salaryDateChooser.setFont(new Font("Arial", Font.PLAIN, 20));
        salaryDateChooser.setForeground(Color.BLACK);
        backgroundImageLabel.add(salaryDateChooser);

        submitButton = new JButton("Submit");

        submitButton.setBounds(150, 400, 150, 30);
        submitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(Color.BLACK);
        submitButton.addActionListener(this);
        backgroundImageLabel.add(submitButton);

        fillComboBox();
        
        setSize(600, 700);
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
                employeeNameField.setText(rs.getString("name") + " " + rs.getString("father_name"));
                emailField.setText(rs.getString("email"));
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private void addSalary(int employeeId) {
        try {
            PreparedStatement ps = connection.conn.prepareStatement("INSERT INTO Salary (employee_id, basic_salary, bonuses, deductions, salary_date) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, employeeId);
            ps.setDouble(2, Double.parseDouble(basicSalaryField.getText()));
            ps.setDouble(3, Double.parseDouble(bonusesField.getText()));
            ps.setDouble(4, Double.parseDouble(deductionsField.getText()));
            ps.setDate(5, new java.sql.Date(salaryDateChooser.getDate().getTime()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salary added successfully");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == employeeIdComboBox) {
            fillFields((int) employeeIdComboBox.getSelectedItem());
        } else if (e.getSource() == submitButton) {
            addSalary((int) employeeIdComboBox.getSelectedItem());
        }
    }

    public static void main(String[] args) {
        new AddSalary();
    }
}