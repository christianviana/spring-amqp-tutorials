package org.springframework.amqp.tutorials.tut2;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.util.StopWatch;

@RabbitListener(queues = "hello")
public class Tut2Receiver {

    private final int instance;

    
    public Tut2Receiver(int i) {
        this.instance = i;
    }

    @RabbitHandler
    public void receive(String in) throws InterruptedException {
    	StopWatch watch = new StopWatch();
        watch.start();
        System.out.println("instance " + this.instance +
            " [x] Received '" + in + "'");
        doWork2(in);
        watch.stop();
        System.out.println("instance " + this.instance +
            " [x] Done " + in + " in " + watch.getTotalTimeSeconds() + "s");
    }

    private void doWork(String in) throws InterruptedException {
        for (char ch : in.toCharArray()) {
            if (ch == '.') {
                Thread.sleep(1000);
            }
        }
    }
    
    private void doWork2(String in) throws InterruptedException {
    	int i = Integer.valueOf(in);
    	if (i%2 == 0) {
            Thread.sleep(500);	
    	} else {
    	    Thread.sleep(2300);
    	}
    }
    	
    
}