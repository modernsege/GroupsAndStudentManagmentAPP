package facade;

import dziennik.ClassContainer;
import dziennik.Student;
import dziennik.StudentCondition;
import dziennik.exceptions.maxNumOfStudentsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FacadeGUI {
    ClassContainer database;

    public FacadeGUI (ClassContainer database) {
        this.database = database;
    }

    //Class
    public void addClass(String name, Integer capacity){
        database.addClass(name, capacity);
    }

    public String[][] getClassData(){
        String classes[][] = new String[database.groups.size()][2];
        Double capacity;
        int i =0;
        for(String key : database.groups.keySet()){
            classes[i][0] = database.groups.get(key).groupName;
            capacity = ((double) database.groups.get(key).studentList.size()/(double)database.groups.get(key).maxNumberOfStudents)*100.0;
            classes[i][1] = capacity.toString() +" %";
            i++;
        }

        return classes;
    }

    public void removeClass(String name){
        database.removeClass(name);
    }

    public Double editClass(String name, String newName, Integer maxNumberOfStudents) throws maxNumOfStudentsException {
        Double capacity;

        if(maxNumberOfStudents!=null)
            database.groups.get(name).setMaxNumberOfStudents(maxNumberOfStudents);
        if(newName!=null)
            database.groups.get(name).groupName = newName;

        capacity = ((double) database.groups.get(name).studentList.size()/(double)database.groups.get(name).maxNumberOfStudents)*100.0;

        database.groups.put(newName, database.groups.remove(name));

        return capacity;
    }

    //Students
    public void addStudent(String className, String name, String surname, StudentCondition condition, Integer yearOfBirth, double points){
        Student newStudent = new Student(name, surname, condition, yearOfBirth, points);
        database.groups.get(className).addStudent(newStudent);
    }

    public void removeStudent(String className, String name, String surname){
        database.groups.get(className).removeStudent(name, surname);
    }

    public String[][] getStudentsData(String className){
        String students[][] = new String[database.groups.get(className).studentList.size()][4];

        for(int i=0; i<database.groups.get(className).studentList.size(); i++){
            students[i][0] = database.groups.get(className).studentList.get(i).name;
            students[i][1] = database.groups.get(className).studentList.get(i).surname;
            students[i][2] = Double.toString(database.groups.get(className).studentList.get(i).points);
            students[i][3] = className;
        }

        return students;
    }

    public void editStudent(String className, String name, String surname, String newName, String newSurname, StudentCondition newCondition, String newYearOfBirth, String newPoints) {
        Student st = database.groups.get(className).search(name, surname);

        if(!Objects.equals(newName, "")){
            st.name = newName;
        }
        if(!newSurname.equals("")){
            st.surname = newSurname;
        }
        if(!newYearOfBirth.equals("")){
            st.yearOfBirth = Integer.parseInt(newYearOfBirth);
        }
        if(!newPoints.equals("")){
            st.points = Double.parseDouble(newPoints);
        }


        st.studentCondition = newCondition;
    }

    public String[][] search(String value){
        int amountOfFountStudents = 0;

       // String foundStudents[][] = new String[100][4];
        List<List<String>> fStudents = new ArrayList<List<String>>();

        for(String key : database.groups.keySet()){
            List<Student> found = database.groups.get(key).searchPartial(value);
            if(found != null){
                amountOfFountStudents += found.size();
            }
            else {
                List<Student> empty = new ArrayList<Student>();
            }
            for(int i =0; i< found.size(); i++){
                List<String> temp = new ArrayList<String>();
                temp.add(found.get(i).name);
                temp.add(found.get(i).surname);
                temp.add(Double.toString(found.get(i).points));
                temp.add(key);

                fStudents.add(temp);
            }
        }

        String[][] foundStudents = new String[fStudents.size()][4];

        for(int i = 0; i < fStudents.size(); i++){
            foundStudents[i][0] = fStudents.get(i).get(0);
            foundStudents[i][1] = fStudents.get(i).get(1);
            foundStudents[i][2] = fStudents.get(i).get(2);
            foundStudents[i][3] = fStudents.get(i).get(3);
        }


        return foundStudents;
    }

    public String[][] sort(String className, Integer sortingFlag){
        String[][] sortedStudents = new String[database.groups.get(className).studentList.size()][4];
        List<Student> studentList = new ArrayList<Student>();
        if(sortingFlag == 0){ //sortowanie wg nazwisk
            studentList = database.groups.get(className).sortByName();
        }
        else if(sortingFlag == 1){ //sortowanie wg punktow
            studentList = database.groups.get(className).sortByPoints();
        }
        else{
            studentList = database.groups.get(className).studentList;
        }

        for(int i = 0; i < sortedStudents.length; i++){
            sortedStudents[i][0] = studentList.get(i).name;
            sortedStudents[i][1] = studentList.get(i).surname;
            sortedStudents[i][2] = Double.toString(studentList.get(i).points);
            sortedStudents[i][3] = className;
        }

        return sortedStudents;
    }

    public String[] studentInfo(String className, String name, String surname){
        Student tempStudent = database.groups.get(className).search(name, surname);
        String[] studentInfo = new String[6];
        studentInfo[0] = tempStudent.name;
        studentInfo[1] = tempStudent.surname;
        studentInfo[2] = tempStudent.studentCondition.toString();
        studentInfo[3] = tempStudent.yearOfBirth.toString();
        studentInfo[4] = Double.toString(tempStudent.points);
        studentInfo[5] = className;

        return studentInfo;
    }

}
