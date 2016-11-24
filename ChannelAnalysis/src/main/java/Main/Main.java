package Main;

import Core.FileConsumer;
import Core.FileProducer;
import Utils.Common;

import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * Created by ouhiroshi on 2016/11/23.
 */
public class Main {

    public static void main(String[] args) {

        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(Common.CONTAIN_MAX_COUNT);

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < Common.DONWLOAD_MAX_COUNT; i++) {
            FileProducer producer = new FileProducer(queue);
            service.submit(producer);
        }
        for (int i = 0; i < Common.DECODE_MAX_COUNT; i++) {
            FileConsumer consumer = new FileConsumer(queue);
            service.submit(consumer);
        }

    }
}
