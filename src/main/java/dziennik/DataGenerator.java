package dziennik;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DataGenerator {
    private static DataGenerator instance; //Singleton

    private DataGenerator (){
    }

    public ClassContainer generate(){
        ClassContainer generatedContainer = new ClassContainer();
        String Names[] = {"Laura", "Marcin", "Mariola", "Martyna", "Natalia", "Piotr", "Rafal", "Szymon", "Sebastian", "Tobiasz", "Urszula", "Vladimir", "Wojciech", "Zbigniew", "Agnieszka", "Adrian", "Agata", "Bonifacy", "Barbara", "Bartek", "Cecylia", "Dorota", "Dorian", "Eugeniusz", "Elzbieta", "Faustyna", "Faust", "Franek", "Fryderyk", "Grazyna", "Gawel", "Halina", "Herbert", "Iga", "Igor", "Jacek", "Janina", "Kamil", "Katarzyna"};
        String Surnames[] = {"Kalinowski", "Masa", "Nara", "Oslo", "Pomidor", "Rakieta", "Sosna", "Trakor", "Usas", "Was", "Zaza", "Agni", "Azai", "Awi", "Bezimienny", "Battlerage", "Bofur", "Cari", "Carrion", "Calvary", "Cyjanek", "Crow", "Dori", "DeZakreble", "Drow", "Dissous", "Eryn", "Ericsson", "Ester", "Egilow", "Gryc", "Garada", "Glab", "Hrow", "Ileou", "Kagur", "Krayn", "Kowi"};

        int maxNumberOfStudents = 100;
        int minNumberOfStudents = 10;
        int maxNumberOfGroups = 8;
        int minNumberOfGroups = 4;

        Random random = new Random();

        int randomNumberOfGroups = random.nextInt(maxNumberOfGroups - minNumberOfGroups+1) + minNumberOfGroups;


        for (int i = 0; i<randomNumberOfGroups; i++){
            List<Student> studentList = new ArrayList<Student>();
            int randomNumberOfStudents = random.nextInt(maxNumberOfStudents - minNumberOfStudents+1) + minNumberOfStudents;
            int randomYear = random.nextInt(4) + 1998;
            for(int j = 0; j<randomNumberOfStudents; j++){
                int randomIndexOfNames = random.nextInt(Names.length);
                int randomIndexOfSurnames = random.nextInt(Surnames.length);
                int randomIndexOfStudentCondition = random.nextInt(4);
                int randomPoints = random.nextInt(101);
                Student st = new Student(Names[randomIndexOfNames], Surnames[randomIndexOfSurnames], StudentCondition.values()[randomIndexOfStudentCondition] , randomYear, randomPoints);
                studentList.add(st);
            }
            Integer classNum = i+1;
            Class cl = new Class("Class "+classNum.toString(), studentList, maxNumberOfStudents);
            generatedContainer.addClass(cl);
        }

        generatedContainer.addClass("Klasa z1", maxNumberOfStudents);


        return generatedContainer;
    }

    public static DataGenerator getInstance() { //Singleton
        if(instance == null){
            instance = new DataGenerator();
        }
        return instance;
    }
}
