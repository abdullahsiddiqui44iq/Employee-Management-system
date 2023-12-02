import javax.swing.* ;

import java.awt.*;
import java.awt.event.*;


public class Dashboard extends JFrame implements ActionListener  {
    JPanel panel;
    Font menuFont, meniItemFont;

    Dashboard(){
        super("Dashboard");
        setSize(1550, 800);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon backgroundImage = new ImageIcon(new ImageIcon("/home/abdullah/Documents/aybees data/FAST/sem5/DB th/project/EmployeeManagementSystem/resources/icons/front.jpg").getImage().getScaledInstance(1550, 800, Image.SCALE_DEFAULT));
        JLabel l1 = new JLabel(backgroundImage);
        l1.setBounds(0, 0, 1550, 800);
        add(l1);

        //fonts
        menuFont = new Font("Times New Roman", Font.BOLD, 20);
        meniItemFont = new Font("Times New Roman", Font.PLAIN, 20);

        //menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.BLACK);
        JMenu Profile = new JMenu("Profile");
        JMenuItem viewProfile = new JMenuItem("View Profile");
        JMenuItem addEmployee = new JMenuItem("Add Employee");
        Profile.add(viewProfile);
        Profile.add(addEmployee);
        menuBar.add(Profile);

        JMenu manage = new JMenu("Manage");
        JMenuItem updateDetails = new JMenuItem("Update details");
        // JMenuItem updatePassword = new JMenuItem("Update Password");
        manage.add(updateDetails);
        menuBar.add(manage);

        JMenu Attendance = new JMenu("Attendance");
        JMenuItem viewAttendance = new JMenuItem("View Attendance");
        JMenuItem takeAttendance = new JMenuItem("Take Attendance");
        Attendance.add(viewAttendance);
        Attendance.add(takeAttendance);
        menuBar.add(Attendance);

        JMenu Leave = new JMenu("Leave");
        JMenuItem applyLeave = new JMenuItem("Apply Leave");
        JMenuItem viewLeave = new JMenuItem("View Leave");
        Leave.add(applyLeave);
        Leave.add(viewLeave);
        menuBar.add(Leave);

        JMenu salary = new JMenu("Salary");
        JMenuItem addSalary = new JMenuItem("Add Salary");
        JMenuItem salarySlip = new JMenuItem("Generate Salary Slip");
        salary.add(addSalary);
        salary.add(salarySlip);
        menuBar.add(salary);

        JMenu Delete = new JMenu("Delete");
        JMenuItem deleteEmployee = new JMenuItem("Delete Employee");
        Delete.add(deleteEmployee);
        menuBar.add(Delete);

        JMenu Exit = new JMenu("Exit");
        JMenuItem logoutItem = new JMenuItem("Logout");
        Exit.add(logoutItem);
        menuBar.add(Exit);

        //adding menubar to frame
        setJMenuBar(menuBar);

        //setting fonts
        Profile.setFont(menuFont);
        manage.setFont(menuFont);
        Attendance.setFont(menuFont);
        Leave.setFont(menuFont);
        salary.setFont(menuFont);
        Delete.setFont(menuFont);
        Exit.setFont(menuFont);

        viewProfile.setFont(meniItemFont);
        addEmployee.setFont(meniItemFont);
        updateDetails.setFont(meniItemFont);
        viewAttendance.setFont(meniItemFont);
        takeAttendance.setFont(meniItemFont);
        applyLeave.setFont(meniItemFont);
        viewLeave.setFont(meniItemFont);
        addSalary.setFont(meniItemFont);
        salarySlip.setFont(meniItemFont);
        deleteEmployee.setFont(meniItemFont);
        logoutItem.setFont(meniItemFont);

        //change text color of menus to light gray
        Profile.setForeground(Color.LIGHT_GRAY);
        manage.setForeground(Color.LIGHT_GRAY);
        Attendance.setForeground(Color.LIGHT_GRAY);
        Leave.setForeground(Color.LIGHT_GRAY);
        salary.setForeground(Color.LIGHT_GRAY);
        Delete.setForeground(Color.LIGHT_GRAY);
        Exit.setForeground(Color.LIGHT_GRAY);
        
        


       



    }
    
    public void actionPerformed(ActionEvent ae){
        String msg = ae.getActionCommand();
        if(msg.equals("View Profile")){
            new ViewProfile().setVisible(true);
        }
        else if(msg.equals("Add Employee")){
            new AddEmployee();
        }
        else if(msg.equals("Update details")){
            new UpdateDetails().setVisible(true);
        }
        else if(msg.equals("View Attendance")){
            new ViewAttendance().setVisible(true);
        }
        else if(msg.equals("Take Attendance")){
            new TakeAttendance().setVisible(true);
        }
        else if(msg.equals("Apply Leave")){
            new ApplyLeave().setVisible(true);
        }
        else if(msg.equals("View Leave")){
            new ViewLeave().setVisible(true);
        }
        else if(msg.equals("Add Salary")){
            new AddSalary().setVisible(true);
        }
        else if(msg.equals("Generate Salary Slip")){
            new GenerateSalarySlip().setVisible(true);
        }
        else if(msg.equals("Delete Employee")){
            new DeleteEmployee().setVisible(true);
        }
        else if(msg.equals("Logout")){
            new Login().setVisible(true);
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }
}
