package corbos.orderapi.data;

import java.util.List;

import corbos.orderapi.models.Order;

public interface OrderDao {

    Order add();
    
    String update(Order order);
    String cancel(int orderId);
    List<Order> current() ;
    List<Order> getClientId(int clientId);

    List<Order> getAll();

    Order findById(int id);

	/*
	 * // true if item exists and is updated boolean update(Order order);
	 */

    // true if item exists and is deleted
    boolean deleteById(int id);
}