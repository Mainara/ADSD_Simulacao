import java.util.ArrayList;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;


public class Simulator {
	
	private String distributionName;
	private double mean;
	private int simulationTime;
	private Service server;
	private int requestsProcessed;
	private int requestsCount;
	private AbstractRealDistribution distribution;
	private int busyElements;
	private double std_deviation;
	private double lower_bound;
	private double upper_bound;
	public Simulator(String distributionName,double mean,int simulationTime){
		this.distributionName = distributionName;
		this.mean = mean;
		this.simulationTime = simulationTime;
		this.requestsProcessed = 0;
		this.requestsCount = 0;
		this.busyElements = 0;
		this.server = new Service();
		setDistribution();
	}

	public Simulator(String distributionName, double median, int simulationTime, double standard_deviation ){
		this.distributionName = distributionName;
		this.mean = median;
		this.simulationTime = simulationTime;
		this.requestsProcessed = 0;
		this.requestsCount = 0;
		this.busyElements = 0;
		this.server = new Service();
		this.std_deviation = standard_deviation;
		setDistribution();
	}

	public Simulator(String distributionName, double median, int simulationTime, double lower_bound, double upper_bound){
		this.distributionName = distributionName;
		this.mean = median;
		this.simulationTime = simulationTime;
		this.requestsProcessed = 0;
		this.requestsCount = 0;
		this.busyElements = 0;
		this.server = new Service();
		this.lower_bound = lower_bound;
		this.upper_bound = upper_bound;
		setDistribution();
	}	

	public void startSimulation(){
		ArrayList<ProcessEvent> listOfRequests = new ArrayList<ProcessEvent>();
		for ( int currentTime = 0; currentTime < this.simulationTime;currentTime++){
			//Define when new request will appear
			double requestTime = currentTime + Math.floor( this.distribution.sample() );
			
			//Verify if some waiting process will start now
			if(listOfRequests.size() <= 2){
				listOfRequests.add( new ProcessEvent(requestTime,this.mean) );
				this.requestsCount++;
			}
			for(int requestIndex = 0 ; requestIndex < listOfRequests.size();requestIndex++){	
				
				if( server.isBusy() ){
					this.busyElements++;
				}
				// Move waiting process from requestList to server.processEvent
				if(listOfRequests.get(requestIndex).getTimeToStart() == currentTime && !server.isBusy() ){
					ProcessEvent waitingProcess = listOfRequests.get(requestIndex);
					listOfRequests.remove( requestIndex );
					server.processNewEvent( waitingProcess );
					this.requestsProcessed += 1;
				}
				
			}
			server.verifyIfEventDone( currentTime );
		}
		System.out.println("Distriuição usada na chegada : " + this.distributionName);
		System.out.println("Duração da Simulação: " + simulationTime);
		System.out.println("Quantidade de Requisições recebidas: " + this.requestsCount);
		System.out.println("Quantidade de Requisições atendidas: " + this.requestsProcessed);
		System.out.println("Tempo médio de atendimento : " + this.server.getMeanProcessedTime() );
		System.out.println("Quantidade média de elementos em espera : " + this.busyElements / this.requestsCount );
		
	}
	
	public void setDistribution(){
		if(distributionName.compareTo("normal") == 0 ){
			this.distribution = new NormalDistribution(this.mean, this.std_deviation);
		}else if(distributionName.compareTo("exponencial") == 0 ){
			this.distribution =  new ExponentialDistribution(this.mean);
		}else{
			this.distribution = new UniformRealDistribution(this.lower_bound, this.upper_bound);
		}
	}
}
