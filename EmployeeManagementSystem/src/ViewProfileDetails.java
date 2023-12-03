import java.awt.*;
import java.sql.PreparedStatement;

import javax.swing.*;

public class ViewProfileDetails {

    JFrame frame;
    JLabel mainHeading, nameLabel, ageLabel, addressLabel, phoneLabel, emailLabel, designationLabel, departmentLabel,
            employeeIdLabel, fatherNameLabel, dobLabel, backgroundImageLabel, departmentIdLabel;

    ViewProfileDetails(int employeeId) {

        frame = new JFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundImage = new ImageIcon(new ImageIcon(
                "/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/print.jpg")
                .getImage().getScaledInstance(800, 800, Image.SCALE_DEFAULT));
        backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setBounds(0, 0, 800, 800);
        frame.add(backgroundImageLabel);

        mainHeading = new JLabel("Employee Details");
        mainHeading.setBounds(150, 50, 500, 50);
        mainHeading.setFont(new Font("Arial", Font.BOLD, 30));
        mainHeading.setForeground(Color.BLACK);
        backgroundImageLabel.add(mainHeading);

        nameLabel = new JLabel();


        try {
            Connectionclass connection = new Connectionclass();
            PreparedStatement ps = connection.conn.prepareStatement("SELECT * FROM EmployeeView where employee_id = ?");
            ps.setInt(1, employeeId);
            var rs = ps.executeQuery();
            if (rs.next()) {
                employeeIdLabel = new JLabel("Employee ID");
                employeeIdLabel.setBounds(50, 150, 150, 30);
                employeeIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
                employeeIdLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(employeeIdLabel);

                employeeIdLabel = new JLabel(Integer.toString(rs.getInt("employee_id")));
                employeeIdLabel.setBounds(250, 150, 150, 30);
                employeeIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
                employeeIdLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(employeeIdLabel);

                nameLabel = new JLabel("Name");
                nameLabel.setBounds(50, 200, 150, 30);
                nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
                nameLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(nameLabel);

                nameLabel = new JLabel(rs.getString("name"));
                nameLabel.setBounds(250, 200, 150, 30);
                nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
                nameLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(nameLabel);

                fatherNameLabel = new JLabel("Father Name");
                fatherNameLabel.setBounds(50, 250, 150, 30);
                fatherNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
                fatherNameLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(fatherNameLabel);

                fatherNameLabel = new JLabel(rs.getString("father_name"));
                fatherNameLabel.setBounds(250, 250, 150, 30);
                fatherNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
                fatherNameLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(fatherNameLabel);

                ageLabel = new JLabel("Age");
                ageLabel.setBounds(50, 300, 150, 30);
                ageLabel.setFont(new Font("Arial", Font.BOLD, 20));
                ageLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(ageLabel);

                ageLabel = new JLabel(Integer.toString(rs.getInt("age")));
                ageLabel.setBounds(250, 300, 150, 30);
                ageLabel.setFont(new Font("Arial", Font.BOLD, 20));
                ageLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(ageLabel);

                addressLabel = new JLabel("Address");
                addressLabel.setBounds(50, 350, 150, 30);
                addressLabel.setFont(new Font("Arial", Font.BOLD, 20));
                addressLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(addressLabel);

                addressLabel = new JLabel(rs.getString("address"));
                addressLabel.setBounds(250, 350, 150, 30);
                addressLabel.setFont(new Font("Arial", Font.BOLD, 20));
                addressLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(addressLabel);

                emailLabel = new JLabel("Email");
                emailLabel.setBounds(50, 400, 150, 30);
                emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
                emailLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(emailLabel);

                emailLabel = new JLabel(rs.getString("email"));
                emailLabel.setBounds(250, 400, 150, 30);
                emailLabel.setFont(new Font("Arial", Font.BOLD, 20));
                emailLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(emailLabel);

                phoneLabel = new JLabel("Phone");
                phoneLabel.setBounds(50, 450, 150, 30);
                phoneLabel.setFont(new Font("Arial", Font.BOLD, 20));
                phoneLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(phoneLabel);

                phoneLabel = new JLabel(rs.getString("phone"));
                phoneLabel.setBounds(250, 450, 150, 30);
                phoneLabel.setFont(new Font("Arial", Font.BOLD, 20));
                phoneLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(phoneLabel);

                designationLabel = new JLabel("Designation");
                designationLabel.setBounds(50, 500, 150, 30);
                designationLabel.setFont(new Font("Arial", Font.BOLD, 20));
                designationLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(designationLabel);

                designationLabel = new JLabel(rs.getString("designation"));
                designationLabel.setBounds(250, 500, 150, 30);
                designationLabel.setFont(new Font("Arial", Font.BOLD, 20));
                designationLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(designationLabel);

                departmentLabel = new JLabel("Department");
                departmentLabel.setBounds(50, 550, 150, 30);
                departmentLabel.setFont(new Font("Arial", Font.BOLD, 20));
                departmentLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(departmentLabel);

                departmentLabel = new JLabel(rs.getString("department"));
                departmentLabel.setBounds(250, 550, 150, 30);
                departmentLabel.setFont(new Font("Arial", Font.BOLD, 20));
                departmentLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(departmentLabel);

                departmentIdLabel = new JLabel("Department ID");
                departmentIdLabel.setBounds(50, 600, 150, 30);
                departmentIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
                departmentIdLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(departmentIdLabel);

                departmentIdLabel = new JLabel(Integer.toString(rs.getInt("department_id")));
                departmentIdLabel.setBounds(250, 600, 150, 30);
                departmentIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
                departmentIdLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(departmentIdLabel);

                dobLabel = new JLabel("Date of Birth");
                dobLabel.setBounds(50, 650, 150, 30);
                dobLabel.setFont(new Font("Arial", Font.BOLD, 20));
                dobLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(dobLabel);

                dobLabel = new JLabel(rs.getString("date_of_birth"));
                dobLabel.setBounds(250, 650, 150, 30);
                dobLabel.setFont(new Font("Arial", Font.BOLD, 20));
                dobLabel.setForeground(Color.BLACK);
                backgroundImageLabel.add(dobLabel);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame.setLayout(null);
        frame.setLocation(400, 250);
        frame.setVisible(true);

    }

}
