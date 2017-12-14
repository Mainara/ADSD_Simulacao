import java.util.Scanner;

public class Simulacao {

    public static void main(String[] args ) throws Exception {
        Scanner input = new Scanner( System.in );
        System.out.println("Write your distribution type ...");
        String typeOfDistribution = input.nextLine();
        System.out.println("Write time avarage ...");
        double timeAvarage = input.nextDouble();
        if( typeOfDistribution.toLowerCase() == "uniforme" ){
            
        }else if ( typeOfDistribution.toLowerCase() == "exponencial" ){

        }else if ( typeOfDistribution.toLowerCase() == "normal" ){

        }else{
            System.out.println("Wrong type! \n");
            return;
        }
    }

}