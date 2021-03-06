package corbos.orderapi.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import corbos.orderapi.data.OrderDao;
import corbos.orderapi.models.Order;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderDao dao;

    public OrderController(OrderDao dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Order> all() {
        return dao.getAll();
    }
    
    @PostMapping("/logon")
    @ResponseStatus(HttpStatus.CREATED)
    public Order create() {
        return dao.add();
    }
    
    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public String update(@RequestBody Order order) {
        return dao.update(order); 

    }
    
    @PostMapping("/cancel/{orderId}")
    @ResponseStatus(HttpStatus.CREATED)
    public String cancel(@PathVariable int orderId) {
        return dao.cancel(orderId); 

    }
    
    @GetMapping("/current")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Order> current() {
        return dao.current(); 

    }
    
    @GetMapping("/orders/{clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Order> getClientId(@PathVariable int clientId) {
        return dao.getClientId(clientId); 

    }
    
	/*
	 * @GetMapping("/{id}") public ResponseEntity<Order> findById(@PathVariable int
	 * id) { Order result = dao.findById(id); if (result == null) { return new
	 * ResponseEntity(null, HttpStatus.NOT_FOUND); } return
	 * ResponseEntity.ok(result); }
	 * 
	 * @PutMapping("/{id}") public ResponseEntity update(@PathVariable int
	 * id, @RequestBody Order order) { ResponseEntity response = new
	 * ResponseEntity(HttpStatus.NOT_FOUND); if (id != order.getId()) { response =
	 * new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY); } else if
	 * (dao.update(order)) { response = new ResponseEntity(HttpStatus.NO_CONTENT); }
	 * return response; }
	 */
    
}