import java.util.ArrayList;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.UniformRealDistribution;


public class Simulator {
	
	private String distributionName;
	private double avarage;
	private int simulationTime;
	private Service server;
	private int requestsProcessed;
	private int requestsCount;
	private AbstractRealDistribution distribution;
	private int busyElements;
	public Simulator(String distributionName,double median,int simulationTime){
		this.distributionName = distributionName;
		this.avarage = median;
		this.simulationTime = simulationTime;
		this.requestsProcessed = 0;
		this.requestsCount = 0;
		this.busyElements = 0;
		this.server = new Service();
		setDistribution();
	}
	
	public void startSimulation(){
		ArrayList<ProcessEvent> listOfRequests = new ArrayList<ProcessEvent>();
		for ( int currentTime = 0; currentTime < this.simulationTime;currentTime++){
			//Define when new request will appear
			double requestTime = currentTime + Math.floor( this.distribution.sample() );
			
			//Verify if some waiting process will start now
			if(listOfRequests.size() <= 2){
				listOfRequests.add( new ProcessEvent(requestTime,this.avarage) );
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
		System.out.println("Duração da Simulação: " + simulationTime);
		System.out.println("Quantidade de Requisições recebidas: " + this.requestsCount);
		System.out.println("Quantidade de Requisições atendidas: " + this.requestsProcessed);
		System.out.println("Tempo médio de atendimento : " + this.server.getMeanProcessedTime() );
		System.out.println("Quantidade média de elementos em espera : " + this.busyElements / this.requestsCount );
		
	}
	
	public void setDistribution(){
		if(this.distributionName.toLowerCase() == "normal"){
			this.distribution = new NormalDistribution();
		}else if(this.distributionName.toLowerCase() == "exponencial"){
			this.distribution =  new ExponentialDistribution(this.avarage);
		}else{
			this.distribution = new UniformRealDistribution();
		}
	}
}
