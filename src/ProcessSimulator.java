import java.time.Instant;
import java.time.Duration;
public class ProcessSimulator{

    private double timeOfProcess;
    private Fregues fregues;
    private Instant start;

    public void processNewRequest(Fregues fregues,double timeOfProcess){
        if(fregues != null){
            throw new Exception("Process is busy!");
        }else{
            this.fregues = fregues;
            this.timeOfProcess = timeOfProcess;
            this.start = Instant.now();
        }
    }

    public boolean checkProcess(){
        if( Duration.between( this.start, Instant.now() ).toMinutes() > this.timeOfProcess ){
            this.timeOfProcess = 0;
            this.fregues = null;
            return true;
        }
        return false;
    }
}