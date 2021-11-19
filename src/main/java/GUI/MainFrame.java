package GUI;

import dziennik.ClassContainer;
import dziennik.StudentCondition;
import facade.FacadeGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton addButtonStudent;
    private JButton removeButtonGroup;
    private JButton removeButtonStudent;
    private JButton changeInfoButtonStudent;
    private JButton addButtonGroup;
    private JButton changeInfoButtonGroup;
    private JComboBox sortComboBox;
    private JTextField searchTextField;
    private JPanel studentsPanel;
    private JPanel groupsPanel;
    private JPanel sortPanel;
    private JPanel searchPanel;
    private JScrollPane groupScrollPane;
    private JScrollPane studentsScrollPane;
    private JScrollPane studentInfoScrollPane;
    private JTable studentsTable;
    private JPanel groupsPane;
    private JTable groupsTable;
    private JTable studentInfo;


    public MainFrame(ClassContainer database){
        FacadeGUI connector = new FacadeGUI(database);


        getGroupsData(connector);




        removeButtonGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String temp = groupsTable.getValueAt(groupsTable.getSelectedRow(), 0).toString();
                connector.removeClass(temp);
                getGroupsData(connector);
            }
        });


        removeButtonStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String temp1 = groupsTable.getValueAt(groupsTable.getSelectedRow(), 0).toString();
                String temp2 = studentsTable.getValueAt(studentsTable.getSelectedRow(), 0).toString();
                String temp3 = studentsTable.getValueAt(studentsTable.getSelectedRow(), 1).toString();
                connector.removeStudent(temp1, temp2, temp3);

                getStudentsData(connector);
            }
        });


        addButtonGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTextField className = new JTextField(10);
                JTextField maxStudents = new JTextField(5);

                JPanel addClassPanel = new JPanel(new GridLayout(2,2));
                addClassPanel.add(new JLabel("Class Name: "));
                addClassPanel.add(className);
                addClassPanel.add(new JLabel("Max amount of students: "));
                addClassPanel.add(maxStudents);

                GridLayout inputsGrid = new GridLayout(1,2);

                int result = JOptionPane.showConfirmDialog(null, addClassPanel,
                        "Please Enter Needed Values", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    connector.addClass(className.getText(), Integer.parseInt(maxStudents.getText()));
                    getGroupsData(connector);
                }

            }
        });


        addButtonStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String className = (groupsTable.getValueAt(groupsTable.getSelectedRow(), 0)).toString();
                JTextField studentName = new JTextField(10);
                JTextField studentSurname = new JTextField(10);
                String[] conditions = {"odrabiajacy","chory","nieobecny", "obecny"};
                JComboBox studentCondition = new JComboBox(conditions);
                studentCondition.setSelectedIndex(0);
                JTextField studentYearOfBirth = new JTextField(10);
                JTextField studentPoints = new JTextField(10);


                JPanel addStudentPanel = new JPanel(new GridLayout(6,2));
                addStudentPanel.add(new JLabel("Student name: "));
                addStudentPanel.add(studentName);
                addStudentPanel.add(new JLabel("Student surname: "));
                addStudentPanel.add(studentSurname);
                addStudentPanel.add(new JLabel("Student condition: "));
                addStudentPanel.add(studentCondition);
                addStudentPanel.add(new JLabel("Student year of birth: "));
                addStudentPanel.add(studentYearOfBirth);
                addStudentPanel.add(new JLabel("Student points: "));
                addStudentPanel.add(studentPoints);



                int result = JOptionPane.showConfirmDialog(null, addStudentPanel,
                        "Please Enter Needed Values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    connector.addStudent(className, studentName.getText(), studentSurname.getText(), StudentCondition.values()[studentCondition.getSelectedIndex()], Integer.parseInt(studentYearOfBirth.getText()), Double.parseDouble(studentPoints.getText()));
                    getGroupsData(connector);
                }



            }
        });

        changeInfoButtonGroup.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JTextField className = new JTextField(10);
                JTextField maxStudents = new JTextField(5);

                JPanel addClassPanel = new JPanel(new GridLayout(2,2));
                addClassPanel.add(new JLabel("Class Name: "));
                addClassPanel.add(className);
                addClassPanel.add(new JLabel("Max amount of students: "));
                addClassPanel.add(maxStudents);


                int result = JOptionPane.showConfirmDialog(null, addClassPanel,
                        "Please Enter Needed Values", JOptionPane.OK_CANCEL_OPTION);


                if (result == JOptionPane.OK_OPTION) {
                    try {
                        String oldClassName = (groupsTable.getValueAt(groupsTable.getSelectedRow(), 0)).toString();
                        connector.editClass(oldClassName, className.getText(), Integer.parseInt(maxStudents.getText()));
                        getGroupsData(connector);
                    }catch (Exception ignored){}

                }
            }
        });

        changeInfoButtonStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String className = (groupsTable.getValueAt(groupsTable.getSelectedRow(), 0)).toString();
                JTextField studentName = new JTextField(10);
                JTextField studentSurname = new JTextField(10);
                String[] conditions = {"odrabiajacy","chory","nieobecny", "obecny"};
                JComboBox studentCondition = new JComboBox(conditions);
                studentCondition.setSelectedIndex(0);
                JTextField studentYearOfBirth = new JTextField(10);
                JTextField studentPoints = new JTextField(10);


                JPanel addStudentPanel = new JPanel(new GridLayout(6,2));
                addStudentPanel.add(new JLabel("Student name: "));
                addStudentPanel.add(studentName);
                addStudentPanel.add(new JLabel("Student surname: "));
                addStudentPanel.add(studentSurname);
                addStudentPanel.add(new JLabel("Student condition: "));
                addStudentPanel.add(studentCondition);
                addStudentPanel.add(new JLabel("Student year of birth: "));
                addStudentPanel.add(studentYearOfBirth);
                addStudentPanel.add(new JLabel("Student points: "));
                addStudentPanel.add(studentPoints);



                int result = JOptionPane.showConfirmDialog(null, addStudentPanel,
                        "Please Enter Needed Values", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String oldName = (studentsTable.getValueAt(studentsTable.getSelectedRow(), 0)).toString();
                    String oldSurname = (studentsTable.getValueAt(studentsTable.getSelectedRow(), 1)).toString();
                    connector.editStudent(className, oldName, oldSurname, studentName.getText(), studentSurname.getText(), StudentCondition.values()[studentCondition.getSelectedIndex()], studentYearOfBirth.getText(), studentPoints.getText());
                    getStudentsData(connector);
                }

            }
        });


        sortComboBox.addItem("Sort surname");
        sortComboBox.addItem("Sort points");

        sortComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer sortFlag = sortComboBox.getSelectedIndex();
                String className = (groupsTable.getValueAt(groupsTable.getSelectedRow(), 0)).toString();
                connector.sort(className, sortFlag);
                getStudentsData(connector);
            }
        });

        searchTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = searchTextField.getText();
                String[][] foundStudents = connector.search(value);
                String[] fieldNames= {"Name", "Surname", "Points", "Class"};
                studentsTable = new JTable(foundStudents, fieldNames);
                studentsScrollPane.setViewportView(studentsTable);
                studentsTable.setDefaultEditor(Object.class, null);
                studentsTable.setCellSelectionEnabled(false);
                studentsTable.setRowSelectionAllowed(true);
                studentInfoScrollPane.setViewportView(studentInfo);

                studentsTable.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        String[] fieldNamesForInfo = {"Name", "Surname","Condition", "Birthyear", "Points", "Class"};
                        String temp1 = (studentsTable.getValueAt(studentsTable.getSelectedRow(), 3)).toString();
                        String temp2 = (studentsTable.getValueAt(studentsTable.getSelectedRow(), 0)).toString();
                        String temp3 = (studentsTable.getValueAt(studentsTable.getSelectedRow(), 1)).toString();
                        String[][] tempTable = new String[1][6];
                        tempTable[0] = connector.studentInfo(temp1, temp2, temp3);
                        studentInfo = new JTable(tempTable, fieldNamesForInfo );
                        studentInfoScrollPane.setViewportView(studentInfo);
                        studentInfo.setCellSelectionEnabled(false);
                        studentInfo.setRowSelectionAllowed(true);
                        studentInfo.setDefaultEditor(Object.class, null);

                    }
                });
                }
            });


/////////////////////////////////////////////////////////

        setContentPane(mainPanel);
        setTitle("Stutent and groups manager");
        setSize(1000, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);



    }



    private void getGroupsData(FacadeGUI connector){
        String[] fieldNames1 = {"Class name", "Capacity"};
        groupsTable = new JTable(connector.getClassData(), fieldNames1 );
        groupsTable.setCellSelectionEnabled(false);
        groupsTable.setRowSelectionAllowed(true);
        groupsTable.setDefaultEditor(Object.class, null);
        groupScrollPane.setViewportView(groupsTable);

        String[] fieldNames2 = {"Name", "Surname", "Points", "Class"};
        String[][] fieldNames22 = {};
        studentsTable = new JTable(fieldNames22, fieldNames2);
        studentsTable.setCellSelectionEnabled(false);
        studentsTable.setRowSelectionAllowed(true);
        studentsTable.setDefaultEditor(Object.class, null);

        String[] fieldNamesForInfo = {"Name", "Surname","Condition", "Birthyear", "Points", "Class"};
        String[][] fieldNamesForInfo2 = {};
        studentInfo = new JTable(fieldNamesForInfo2, fieldNamesForInfo);
        studentInfo.setCellSelectionEnabled(false);
        studentInfo.setRowSelectionAllowed(true);
        studentInfo.setDefaultEditor(Object.class, null);


        groupScrollPane.setViewportView(groupsTable);
        studentsScrollPane.setViewportView(studentsTable);
        studentInfoScrollPane.setViewportView(studentInfo);

        groupsTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] fieldNames= {"Name", "Surname", "Points", "Class"};
                studentsTable = new JTable(connector.getStudentsData( (groupsTable.getValueAt(groupsTable.getSelectedRow(), 0)).toString() ), fieldNames );
                studentsScrollPane.setViewportView(studentsTable);
                studentsTable.setDefaultEditor(Object.class, null);
                studentsTable.setCellSelectionEnabled(false);
                studentsTable.setRowSelectionAllowed(true);

                getStudentsData(connector);
            }
        });

    }

    private void getStudentsData(FacadeGUI connector){
        String[] fieldNames= {"Name", "Surname", "Points", "Class"};
        studentsTable = new JTable(connector.getStudentsData( (groupsTable.getValueAt(groupsTable.getSelectedRow(), 0)).toString() ), fieldNames );
        studentsScrollPane.setViewportView(studentsTable);
        studentsTable.setDefaultEditor(Object.class, null);
        studentsTable.setCellSelectionEnabled(false);
        studentsTable.setRowSelectionAllowed(true);
        studentInfoScrollPane.setViewportView(studentInfo);

        studentsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String[] fieldNamesForInfo = {"Name", "Surname","Condition", "Birthyear", "Points", "Class"};
                String temp1 = (groupsTable.getValueAt(groupsTable.getSelectedRow(), 0)).toString();
                String temp2 = (studentsTable.getValueAt(studentsTable.getSelectedRow(), 0)).toString();
                String temp3 = (studentsTable.getValueAt(studentsTable.getSelectedRow(), 1)).toString();
                String[][] tempTable = new String[1][6];
                tempTable[0] = connector.studentInfo(temp1, temp2, temp3);
                studentInfo = new JTable(tempTable, fieldNamesForInfo );
                studentInfoScrollPane.setViewportView(studentInfo);
                studentInfo.setCellSelectionEnabled(false);
                studentInfo.setRowSelectionAllowed(true);
                studentInfo.setDefaultEditor(Object.class, null);

            }
        });


        String[] fieldNamesForInfo = {"Name", "Surname","Condition", "Birthyear", "Points", "Class"};
        String[][] fieldNamesForInfo2 = {};
        studentInfo = new JTable(fieldNamesForInfo2, fieldNamesForInfo);
        studentInfo.setCellSelectionEnabled(false);
        studentInfo.setRowSelectionAllowed(true);
        studentInfo.setDefaultEditor(Object.class, null);

        studentInfoScrollPane.setViewportView(studentInfo);

        studentsScrollPane.setViewportView(studentsTable);
    }


}
