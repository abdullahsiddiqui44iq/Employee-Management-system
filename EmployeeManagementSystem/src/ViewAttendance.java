import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ViewAttendance extends JFrame implements ActionListener {
    JComboBox<String> employeeIdComboBox;
    JButton searchButton;
    JTable attendanceTable;

    ViewAttendance() {
        
        ImageIcon backgroundImage = new ImageIcon(new ImageIcon("/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/print.jpg").getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT));
        JLabel backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setBounds(0, 0, 700, 700);
        add(backgroundImageLabel);

        employeeIdComboBox = new JComboBox<>();
        employeeIdComboBox.setBounds(50, 50, 150, 30);
        employeeIdComboBox.setFont(new Font("Arial", Font.BOLD, 20));
        employeeIdComboBox.setForeground(Color.BLACK);
        employeeIdComboBox.addActionListener(this);
        backgroundImageLabel.add(employeeIdComboBox);

        searchButton = new JButton("Search");
        searchButton.setBounds(250, 50, 150, 30);
        searchButton.setFont(new Font("Arial", Font.PLAIN, 20));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBackground(Color.BLACK);
        searchButton.addActionListener(this);
        backgroundImageLabel.add(searchButton);

        attendanceTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(2, 100, 700, 300);
        backgroundImageLabel.add(scrollPane);

        loadEmployeeIds();
        loadAllAttendance();

        setLayout(new FlowLayout());
        setLocation(400, 250);
        setVisible(true);
        setSize(700, 700);

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

    private void loadAllAttendance() {
        try {
            Connectionclass connectionclass = new Connectionclass();
            PreparedStatement ps = connectionclass.conn.prepareStatement("SELECT * FROM Attendance");
            ResultSet rs = ps.executeQuery();
            attendanceTable.setModel(buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading attendance data. Please try again.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            new ViewEmployeeAttendance((String) employeeIdComboBox.getSelectedItem());
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            // Exclude the Department ID column
            if (!metaData.getColumnName(column).equalsIgnoreCase("department_id")) {
                columnNames.add(metaData.getColumnName(column));
            }
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                // Exclude the Department ID column
                if (!metaData.getColumnName(columnIndex).equalsIgnoreCase("department_id")) {
                    // Format the date column
                    if (metaData.getColumnName(columnIndex).equalsIgnoreCase("date")) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        vector.add(sdf.format(rs.getObject(columnIndex)));
                    } else {
                        vector.add(rs.getObject(columnIndex));
                    }
                }
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
    }
}

class ViewEmployeeAttendance extends JFrame {
    JTable attendanceTable;

    ViewEmployeeAttendance(String employeeId) {
        setLayout(new FlowLayout());

        attendanceTable = new JTable();

        add(new JScrollPane(attendanceTable));

        loadEmployeeAttendance(employeeId);

        setSize(500, 300);
        setLocation(400, 250);
        setVisible(true);
    }

    private void loadEmployeeAttendance(String employeeId) {
        try {
            Connectionclass connectionclass = new Connectionclass();
            PreparedStatement ps = connectionclass.conn.prepareStatement("SELECT * FROM Attendance WHERE employee_id = ?");
            ps.setString(1, employeeId);
            ResultSet rs = ps.executeQuery();
            attendanceTable.setModel(ViewAttendance.buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading attendance data. Please try again.");
        }
    }

    public static void main(String[] args) {
        new ViewAttendance();
    }
}