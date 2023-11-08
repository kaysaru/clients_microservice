package kz.iceberg.clients.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientsServiceJavaApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void contextLoads() {
    }

    @Test
    void testTwoRequests() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10000);

        List<Pair<Long, Long>> times = new LinkedList<>();

        long startTime, endTime, testStart = System.nanoTime(), testEnd;

        for (int i = 0; i < 10000; ++i) {
            startTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
            CompletableFuture<Void> request1 = CompletableFuture.runAsync(() -> {
                webTestClient.get()
                        .uri("/client")
                        .exchange()
                        .expectStatus().isOk();
            }, executor);
            endTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
            times.add(new Pair<>(startTime, endTime));
        }

//        long startTime = System.nanoTime();
//        CompletableFuture<Void> request1 = CompletableFuture.runAsync(() -> {
//            webTestClient.get()
//                    .uri("/client")
//                    .exchange()
//                    .expectStatus().isOk();
//        }, executor);
//        long endTime = System.nanoTime();


//        long startTime2 = System.nanoTime();
//        CompletableFuture<Void> request2 = CompletableFuture.runAsync(() -> {
//            webTestClient.get()
//                    .uri("/client")
//                    .exchange()
//                    .expectStatus().isOk();
//        }, executor);
//
//        CompletableFuture<Void> combined = CompletableFuture.allOf(request1, request2);
//        long endTime2 = System.nanoTime();


        Thread.sleep(1000);
//        combined.join(); // Wait for both requests to complete


//        Thread.sleep(10000);


//        request2.join();


//        startTime = TimeUnit.NANOSECONDS.toMillis(startTime);
//        endTime = TimeUnit.NANOSECONDS.toMillis(endTime);
//        startTime2 = TimeUnit.NANOSECONDS.toMillis(startTime2);
//        endTime2 = TimeUnit.NANOSECONDS.toMillis(endTime2);

//        System.out.println("1st req: " + (endTime - startTime) + " ms");
//        System.out.println("2nd req: " + (endTime2 - startTime2) + " ms");

//        long durationInMillis = TimeUnit.NANOSECONDS.toMillis((endTime - startTime) + (endTime2 - startTime2));

        testEnd = System.nanoTime();
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(testEnd - testStart);

        times.forEach(pair -> {
            System.out.println(pair.first + "ms --- " + pair.second + " ms");
        });
        System.out.println("Time taken for both requests: " + durationInMillis + " ms");
    }

    class Pair<T1, T2> {
        public T1 first;
        public T2 second;

        Pair(T1 first, T2 second) {
            this.first = first;
            this.second = second;
        }

    }
}
