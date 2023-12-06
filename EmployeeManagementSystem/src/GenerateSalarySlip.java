import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateSalarySlip extends JFrame implements ActionListener {
    JComboBox<Integer> employeeIdComboBox, yearComboBox, monthComboBox;
    JButton generateButton;
    JTextArea salaryDetailsArea;
    JLabel employeeIdLabel, yearLabel, monthLabel;
    Connectionclass connection;

    public GenerateSalarySlip() {
        connection = new Connectionclass();

        ImageIcon backgroundImage = new ImageIcon(new ImageIcon(
                "/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/11116.jpg")
                .getImage().getScaledInstance(600, 700, Image.SCALE_DEFAULT));
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

        yearLabel = new JLabel("Year");
        yearLabel.setBounds(50, 100, 150, 30);
        yearLabel.setFont(new Font("Arial", Font.BOLD, 20));
        yearLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(yearLabel);

        yearComboBox = new JComboBox<>();
        for (int i = 2020; i <= 2030; i++) {
            yearComboBox.addItem(i);
        }
        yearComboBox.setBounds(250, 100, 180, 30);
        yearComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        yearComboBox.setForeground(Color.BLACK);
        backgroundImageLabel.add(yearComboBox);

        monthLabel = new JLabel("Month");
        monthLabel.setBounds(50, 150, 150, 30);
        monthLabel.setFont(new Font("Arial", Font.BOLD, 20));
        monthLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(monthLabel);

        monthComboBox = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            monthComboBox.addItem(i);
        }
        monthComboBox.setBounds(250, 150, 180, 30);
        monthComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        monthComboBox.setForeground(Color.BLACK);
        backgroundImageLabel.add(monthComboBox);

        generateButton = new JButton("Generate");
        generateButton.setBounds(50, 200, 150, 30);
        generateButton.setFont(new Font("Arial", Font.BOLD, 20));
        generateButton.setForeground(Color.BLACK);
        generateButton.addActionListener(this);
        backgroundImageLabel.add(generateButton);

        salaryDetailsArea = new JTextArea();
        // SET THE DETAILS AREA TRANSPARENT
        salaryDetailsArea.setOpaque(false);
        salaryDetailsArea.setBounds(50, 250, 400, 400);
        salaryDetailsArea.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
        salaryDetailsArea.setForeground(Color.BLACK);
        salaryDetailsArea.setEditable(false);
        backgroundImageLabel.add(salaryDetailsArea);

        loadEmployeeIds();

        setLayout(new FlowLayout());
        setSize(600, 700);
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

    private void generateSalarySlip(int employeeId, int year, int month) {
        try {
            PreparedStatement employeePs = connection.conn.prepareStatement("SELECT name, email, phone FROM Employee WHERE employee_id = ?");
            employeePs.setInt(1, employeeId);
            ResultSet employeeRs = employeePs.executeQuery();
            
            if (employeeRs.next()) {
        
                salaryDetailsArea.setText("Employee Information:\n"
                        + "Name: " + employeeRs.getString("name") + "\n"
                        + "Email: " + employeeRs.getString("email") + "\n"
                        + "Phone: " + employeeRs.getString("phone") + "\n\n"
                        + "Salary Details:\n");
    
                // Fetch salary details from the Salary table
                PreparedStatement salaryPs = connection.conn.prepareStatement(
                        "SELECT * FROM Salary WHERE employee_id = ? AND YEAR(salary_date) = ? AND MONTH(salary_date) = ?");
                salaryPs.setInt(1, employeeId);
                salaryPs.setInt(2, year);
                salaryPs.setInt(3, month);
                ResultSet salaryRs = salaryPs.executeQuery();
                
                if (salaryRs.next()) {
                    // Display salary details
                    salaryDetailsArea.append("Basic Salary: " + salaryRs.getDouble("basic_salary") + "\n"
                            + "Bonuses: " + salaryRs.getDouble("bonuses") + "\n"
                            + "Deductions: " + salaryRs.getDouble("deductions") + "\n"
                            + "Net Salary: " + salaryRs.getDouble("net_salary"));
                } else {
                    salaryDetailsArea.append("Record not found");
                }
            } else {
                salaryDetailsArea.setText("Employee not found");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            generateSalarySlip((int) employeeIdComboBox.getSelectedItem(), (int) yearComboBox.getSelectedItem(),
                    (int) monthComboBox.getSelectedItem());
        }
    }

    public static void main(String[] args) {
        new GenerateSalarySlip();
    }
}