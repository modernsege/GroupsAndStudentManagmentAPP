import GUI.MainFrame;
import dziennik.*;
import dziennik.Class;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ClassContainer database = DataGenerator.getInstance().generate(); //Singleton
        database.summary();
        database.groups.get("Class 1").summary();

        MainFrame frame = new MainFrame(database);

    }
}
