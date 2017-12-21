import org.apache.commons.math3.*;
import org.apache.commons.math3.distribution.ExponentialDistribution;

public class ProcessEvent {
	double timeToStart;
	double timeToFinish;
	private ExponentialDistribution distribution;
	
	public ProcessEvent(double requestTime, double exponentialMean) {
		this.timeToStart = requestTime;
		this.distribution = new ExponentialDistribution(exponentialMean);
		this.timeToFinish = requestTime + Math.floor(this.distribution.sample());
	}
	
	public double getTimeToStart(){
		return this.timeToStart;
	}
}
