package dziennik.exceptions;

public class maxNumOfStudentsException extends Exception{
    public maxNumOfStudentsException(Integer flag){
        if(flag == 0){
          System.out.println("Max number of students must be a positive number");
        }else if(flag == 1){
            System.out.println("Max students you provided exceeds limit");
        }else if (flag == 2){
            System.out.println("Max number of students must be higher than current number of students");
        }
    }

}
