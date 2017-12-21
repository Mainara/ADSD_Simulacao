
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
		
		//Simulate
		for(int i = 0; i < numberOfTests;i++){
			Simulator simulator = new Simulator(distributionName,median,simulationTime);
			simulator.startSimulation();
		}
	}
}
