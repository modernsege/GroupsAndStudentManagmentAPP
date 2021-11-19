package dziennik;

import java.util.Comparator;

public class Student implements Comparable<Student>{
    public String name;
    public String surname;
    public StudentCondition studentCondition;
    public Integer yearOfBirth;
    public double points;


    public Student(String name, String surname, StudentCondition studentCondition, Integer yearOfBirth, double points){
        this.name = name;
        this.surname = surname;
        this.studentCondition = studentCondition;
        this.yearOfBirth = yearOfBirth;
        this.points = points;
    }
    public void print(){
        System.out.printf("\n\n%s %s\nDate of birth: %d\nStudentCondition: %s\nPoints: %f", name, surname, yearOfBirth, studentCondition, points);
    }

    public int compareTo(Student student) {
        int compareSurname = surname.compareTo(student.surname);

        if(compareSurname == 0){
            return name.compareTo(student.name);
        }
        else {
            return compareSurname;
        }
    }
}

class ComparatorPoints implements Comparator<Student>{
    @Override
    public int compare(Student s1, Student s2){
        double pointDiff = s1.points - s2.points;
        if (pointDiff == 0){
            return s1.compareTo(s2);
        }
        else if(pointDiff>0){
            return 1;
        }
        else {
            return -1;
        }
    }
}
