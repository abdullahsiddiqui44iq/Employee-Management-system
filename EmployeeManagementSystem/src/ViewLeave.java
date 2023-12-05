import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewLeave extends JFrame implements ActionListener {
    JComboBox<String> employeeIdComboBox;
    JTable leaveTable;
    JLabel employeeIdLabel;
    JButton searchButton;

    ViewLeave() {
        setLayout(new FlowLayout());

        ImageIcon backgroundImage = new ImageIcon(new ImageIcon(
                "/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/3243589.jpg")
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

        searchButton = new JButton("Search");
        searchButton.setBounds(450, 50, 100, 30);
        searchButton.setFont(new Font("Arial", Font.BOLD, 20));
        searchButton.setForeground(Color.BLACK);
        searchButton.addActionListener(this);
        backgroundImageLabel.add(searchButton);

        leaveTable = new JTable();
        leaveTable.setOpaque(false);
        ((DefaultTableCellRenderer) leaveTable.getDefaultRenderer(Object.class)).setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(leaveTable);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBounds(50, 150, 500, 500);
        leaveTable.setFont(new Font("Arial", Font.PLAIN, 15));
        leaveTable.setForeground(Color.BLACK);

        // Add the JScrollPane to the backgroundImageLabel, not the JTable
        backgroundImageLabel.add(scrollPane);

        loadEmployeeIds();
        loadLeaveData();
        employeeIdComboBox.setSelectedItem(null);

        setSize(600, 700);
        setLocation(400, 250);
        setVisible(true);
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

    private void loadLeaveData() {
        try {
            Connectionclass connectionclass = new Connectionclass();
            PreparedStatement ps = connectionclass.conn.prepareStatement("SELECT * FROM LeaveView");
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new String[] { "Leave ID", "Employee ID", "Employee Name", "Start Date", "End Date", "Reason" }, 0);
            while (rs.next()) {
                model.addRow(
                        new Object[] { rs.getInt("leave_id"), rs.getInt("employee_id"), rs.getString("employee_name"),
                                rs.getDate("start_date"), rs.getDate("end_date"), rs.getString("reason") });
            }
            leaveTable.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading leave data. Please try again.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            if (employeeIdComboBox.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "Please select an employee ID.");
            } else {
                new EmployeeLeaveWindow(employeeIdComboBox.getSelectedItem().toString());
            }
        }
    }

    class EmployeeLeaveWindow extends JFrame {

        EmployeeLeaveWindow(String employeeId) {

            loadEmployeeLeaveData(employeeId);

        }

        private void loadEmployeeLeaveData(String employeeId) {
            try {
                Connectionclass connectionclass = new Connectionclass();
                PreparedStatement ps = connectionclass.conn
                        .prepareStatement("SELECT * FROM LeaveView WHERE employee_id = ?");
                ps.setString(1, employeeId);
                ResultSet rs = ps.executeQuery();

                DefaultTableModel model = new DefaultTableModel(
                        new String[] { "Leave ID", "Employee ID", "Employee Name", "Start Date", "End Date", "Reason" },
                        0);
                while (rs.next()) {
                    model.addRow(new Object[] { rs.getInt("leave_id"), rs.getInt("employee_id"),
                            rs.getString("employee_name"), rs.getDate("start_date"), rs.getDate("end_date"),
                            rs.getString("reason") });
                }
                leaveTable.setModel(model);
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading leave data. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        new ViewLeave();
    }
}