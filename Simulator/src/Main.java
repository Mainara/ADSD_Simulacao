
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		//Get input
		Scanner input = new Scanner(System.in);
		System.out.println("Digite o tipo de distribuição desejada: ");
		String distributionName = input.nextLine();
		System.out.println("Valor médio do tempo de serviço: ");
		double median = input.nextDouble();
		System.out.println("Duração da simulação: ");
		int simulationTime = input.nextInt();
		System.out.println("Quantidade de repetições desejadas: ");
		int numberOfTests = input.nextInt();
		double standard_deviation = 0;
		if( distributionName.compareTo("normal") == 0 ){
			System.out.println("Digite o desvio padrão : ");
			standard_deviation = input.nextDouble();
		}
		double lower_bound = 0;
		double upper_bound = 0;
		if( distributionName.compareTo("uniforme") == 0  ){
			System.out.println("Digite os limites para a função: ");
			lower_bound = input.nextDouble();
			upper_bound = input.nextDouble();
		}		
		//Simulate
		for(int i = 0; i < numberOfTests;i++){
			Simulator simulator;
			if(distributionName.compareTo("exponencial") == 0 ){
				simulator = new Simulator(distributionName,median,simulationTime);
			}else if(distributionName.compareTo("normal") == 0 ){
				simulator = new Simulator(distributionName,median,simulationTime,standard_deviation);
			}else{
				simulator = new Simulator(distributionName,median,simulationTime,lower_bound,upper_bound);
			}
			simulator.startSimulation();
		}
	}
}
