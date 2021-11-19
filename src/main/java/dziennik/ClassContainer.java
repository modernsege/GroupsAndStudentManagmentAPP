package dziennik;

import java.util.*;

public class ClassContainer {
    public Map<String, Class> groups =  new HashMap<String, Class>();


    public void addClass(String groupName, Integer capacity){
        groups.put(groupName, new Class(groupName, new ArrayList<Student>(), capacity));
    }
    public void addClass(Class c){
        groups.put(c.groupName, c);
    }

    public void removeClass(String groupName){
        groups.remove(groupName);
    }

    public List<Class> findEmpty(){
        List<Class> foundClasses = new ArrayList<Class>();
        groups.forEach((k, v) -> {
            if(v.studentList.isEmpty()){
                foundClasses.add(v);
            }
        });
        return foundClasses;
    }

    public void summary(){
        groups.forEach((k, v) -> {
            System.out.printf("\nNazwa grupy: %s, Procentowe zapelnienie: %f",v.groupName, ((float)v.studentList.size()/(float)v.maxNumberOfStudents)*100.0);
        });
    }
}
