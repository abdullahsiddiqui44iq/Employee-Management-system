import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class ViewAllEmployee extends JFrame {
    JTable employeeTable;
    JLabel mainHeadingLabel;
    Connectionclass connectionclass;

    ViewAllEmployee() {
        connectionclass = new Connectionclass();
        setLayout(new FlowLayout());
        ImageIcon backgroundImage = new ImageIcon(new ImageIcon("/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/print.jpg").getImage().getScaledInstance(700, 700, Image.SCALE_DEFAULT));
        JLabel backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setBounds(0, 0, 700, 700);
        add(backgroundImageLabel);

        mainHeadingLabel = new JLabel("All Employees");
        mainHeadingLabel.setBounds(250, 50, 500, 30);
        mainHeadingLabel.setFont(new Font("Arial", Font.BOLD, 25));
        mainHeadingLabel.setForeground(Color.BLACK);
        backgroundImageLabel.add(mainHeadingLabel);


        employeeTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setBounds(2, 100, 700, 300);
        backgroundImageLabel.add(scrollPane);

        loadAllEmployees();

        setLocation(400, 250);
        setVisible(true);
        setSize(700, 700);


    }

    private void loadAllEmployees() {
        try {
            // Connectionclass connectionclass = new Connectionclass();
            PreparedStatement ps = connectionclass.conn.prepareStatement("SELECT * FROM Employee");
            ResultSet rs = ps.executeQuery();
            employeeTable.setModel(buildTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading employee data. Please try again.");
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }

    public static void main(String[] args) {
        new ViewAllEmployee();
    }
}