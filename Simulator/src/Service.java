import java.util.ArrayList;

public class Service {
	private ProcessEvent runningProcess;
	private int processedEventsCount;
	private double totalProcessedTime = 0;
	public void processNewEvent( ProcessEvent newProcess ) {
		this.runningProcess = newProcess;
		this.processedEventsCount = 0;
		
	}

	public void verifyIfEventDone(int currentTime) {
		if(runningProcess == null ){
			return;
		}
		if( runningProcess.timeToFinish == currentTime ){
			this.totalProcessedTime += (this.runningProcess.timeToFinish - this.runningProcess.timeToStart );
			this.runningProcess = null;
			this.processedEventsCount++;
			
		}
	}
	
	public double getMeanProcessedTime(){
		return this.totalProcessedTime / this.processedEventsCount;
	}
	
	

	public boolean isBusy() {
		return (this.runningProcess != null);
	}

}
