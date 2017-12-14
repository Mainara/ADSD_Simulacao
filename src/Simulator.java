import java.time.Instant;
import java.time.Duration;
import java.util.Queue;
import modelos.*;
import ProcessSimulator;

public class Simulator {
    
    private double timeOfService;
    private Queue<Fregues> queue;
    private static ProcessSimulator process;
    private Distribution distribution;

    public Simulator( double timeOfService, string typeOfDistribution ){
        this.tempoDeServico = timeOfService;
    }

    public void run(){        
        generateStack();
        Instant start = Instant.now();
        while( Duration.between( start, Instant.now() ).toMinutes() < timeOfService ){
            listenService();
        }
    }

    public void listenService(){
        if ( process.checkProcess() ){
            process.processNewRequest( this.queue.dequeue() );
        }else{
            this.queue.enqueue( new Fregues( distribution.generateValue() ) );   
        }
    }
}