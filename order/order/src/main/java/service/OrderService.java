package service;

import entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import repository.OrderRepository;

import java.util.Arrays;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public Order placeOrder(OrderRequest request) {

        ResponseEntity<InventoryBatch[]> response =
                restTemplate.getForEntity(
                        "http://localhost:8081/inventory/" + request.getProductId(),
                        InventoryBatch[].class
                );

        InventoryBatch batch = Arrays.stream(response.getBody())
                .filter(b -> b.getQuantity() >= request.getQuantity())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No inventory available"));

        restTemplate.postForEntity(
                "http://localhost:8081/inventory/update",
                new InventoryUpdateRequest(batch.getBatchId(), request.getQuantity()),
                Void.class
        );

        Order order = new Order();
        order.setProductId(request.getProductId());
        order.setProductName(batch.getProductName());
        order.setQuantity(request.getQuantity());
        order.setStatus("PLACED");
        order.setOrderDate(LocalDate.now());

        return repository.save(order);
    }
}
