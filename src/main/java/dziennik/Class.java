package dziennik;

import dziennik.exceptions.maxNumOfStudentsException;

import java.util.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Class {
    public String groupName;
    public List<Student> studentList = new ArrayList<Student>();
    public Integer maxNumberOfStudents;
    public Class(String groupName, List<Student> studentList, Integer maxNumberOfStudents){
        this.groupName = groupName;
        this.studentList = studentList;
        this.maxNumberOfStudents = maxNumberOfStudents;
    }

    public void setMaxNumberOfStudents(Integer newMaxNumOfStudents) throws maxNumOfStudentsException {
        if(newMaxNumOfStudents < 0){
            throw new maxNumOfStudentsException(0);
        }else if(newMaxNumOfStudents > 1000){
            throw new maxNumOfStudentsException(1);
        }else if (newMaxNumOfStudents < maxNumberOfStudents){
            throw new maxNumOfStudentsException(2);
        }

        this.maxNumberOfStudents = newMaxNumOfStudents;
    }

    public void addStudent(Student student){

        if(studentList.size() < maxNumberOfStudents){
            boolean studentExistsInList = false;
            for (Student value : studentList) {
                if (student.name.equals(value.name) && student.surname.equals(value.surname)) {
                    studentExistsInList = true;
                    System.out.println("\nStudent juz jest w grupie!");
                    return;
                }
            }
            studentList.add(student);
            System.out.println("\nStudent pomyslnie dodany");
        }
        else{
            System.err.println("\nZa duzo studentow w grupie");
        }
    }


    public void addPoints(Student student, double points){
        for (Student value : studentList) {
            if (student.name.equals(value.name) && student.surname.equals(value.surname)) {
                value.points += points;
                System.out.println("\nPunkty pomyslnie dodane");
            }
        }
    }

    public Student getStudent(Student student){
        if(student.points <= 0){
            int index = studentList.indexOf(student);
            studentList.remove(index);
            return student;
        }
        return null;
    }

    public void removeStudent(String name, String surname){
        for(int i = 0; i<studentList.size(); i++){
            if (Objects.equals(studentList.get(i).name, name) && Objects.equals(studentList.get(i).surname, surname)){
                studentList.remove(i);
            }
        }
    }


    public void changeCondition(Student student, StudentCondition studentCondition){
        for (Student value : studentList) {
            if (student.name.equals(value.name) && student.surname.equals(value.surname)) {
                value.studentCondition = studentCondition;
                System.out.println("\nStan studenta pomyslnie zmieniony");
            }
        }
    }

    public void removePoints(Student student, double pointsToBeRemoved){
        for (Student value : studentList) {
            if (student.name.equals(value.name) && student.surname.equals(value.surname)) {
                value.points -= pointsToBeRemoved;
                System.out.println("\nPunkty studenta pomyslnie zaktualizowane");
            }
        }
    }

    public Student search(String surname){
        for(Student value : studentList){
            if(surname.compareTo(value.surname) == 0){
                return value;
            }
        }
        return null;
    }

    public Student search(String name, String surname){
        for(Student value : studentList){
            if(surname.equals(value.surname) && name.equals(value.name)){
                return value;
            }
        }
        return null;
    }

    public List<Student> searchPartial(String val){
        List<Student> foundStudents = new ArrayList<Student>();
        for (Student value : studentList) {
            String concatenate;
            String concatenateInverse;
            concatenate = value.surname+" "+value.name;
            concatenateInverse = value.name+" "+value.surname;
            if (concatenate.contains(val) || concatenateInverse.contains(val)) {
                foundStudents.add(value);
            }
        }
        return  foundStudents;
    }

    public Integer countByCondition(StudentCondition studentCondition){
        Integer counter = 0;
        for(Student value : studentList){
            if(value.studentCondition.equals(studentCondition)){
                counter++;
            }
        }
        return counter;
    }

    public void summary(){
        for(Student value : studentList){
            value.print();
            }
    }

    public List<Student> sortByName(){
        Collections.sort(studentList);
        return studentList;
    }

    public List<Student> sortByPoints(){
        Collections.sort(studentList, new ComparatorPoints());
        return studentList;
    }

    public Student max(){
        return Collections.max(studentList, new ComparatorPoints());
    }


}
