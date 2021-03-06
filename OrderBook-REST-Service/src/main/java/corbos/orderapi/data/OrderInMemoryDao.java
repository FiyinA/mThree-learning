package corbos.orderapi.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

import corbos.orderapi.models.Order;

@Repository
public class OrderInMemoryDao implements OrderDao {

    private static final List<Order> orders = new ArrayList<>();
    enum Side { Buy, Sell; }
    enum OrderStatus {BEGIN,NEW,PARTIAL,COMPLETED,CANCELED;}

    @Override
    public Order add() {
    	int nextOrderId = orders.stream()
                .mapToInt(i -> i.getOrderID())
                .max()
                .orElse(0) + 1;
        int nextClientId = orders.stream()
                .mapToInt(i -> i.getClientID())
                .max()
                .orElse(0) + 1;
        Order order = new Order();
        
        order.setOrderID(nextOrderId);
        order.setClientID(nextClientId);
        order.setOrderStatus(OrderStatus.BEGIN.name());
        orders.add(order);
        return order;
    }
    
    @Override
    public String update(Order order) {
    	int i = 0;
    	for (Order x : orders) {
    		if ( order.getOrderID() == x.getOrderID() && order.getClientID() == x.getClientID()) {
    				if (x.getOrderStatus().equals("BEGIN") || x.getOrderStatus().equals("NEW") || x.getOrderStatus().equals("PARTIAL")){
    					orders.add(i, order);
    					return "Order ID:"+ order.getOrderID() + " Client ID:"+order.getClientID();
    				}
    		} i++;
    	} 
     return "Check Order ID:"+order.getOrderID()+ ", Status ID and Order Status";
    }
    
    @Override
    public String cancel(int orderId) {
    	for (Order x : orders) {
    		if ( orderId == x.getOrderID() ) {
    			if (x.getOrderStatus().equals("COMPLETED") || x.getOrderStatus().equals("CANCEL") ) {
    				return "Order "+  x.getOrderID()+" is already"+" Order Status:"+x.getOrderStatus();
    			}
    			x.setOrderStatus("CANCEL");
    			return "Successfully updated. Order Id:"+x.getOrderID()+" Order Status:"+x.getOrderStatus();
    		}	
    	}
    	return "order Id could not be found";
    }
    
    @Override
    public List<Order> current() {
    	List <Order> resultOrder = new ArrayList();
    	for (Order x : orders) {
    		if ( x.getOrderStatus().equals("COMPLETED") || x.getOrderStatus().equals("CANCEL"))
	    		{    	
	    			//do nothing
	    		}
    		else {
    			resultOrder.add(x);
    		 	}
    		}
     return resultOrder;
    }
    
    @Override
    public List<Order> getClientId(int clientId) {
    	List <Order> resultOrder = new ArrayList();
    	for (Order x : orders) {
    		if ( clientId == x.getClientID() )
	    		{    	
    				resultOrder.add(x);
	    		}
    		}
     return resultOrder;
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(orders);
    }

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * @Override public Order findById(int id) { return orders.stream() .filter(i ->
	 * i.getId() == id) .findFirst() .orElse(null); }
	 * 
	 * @Override public boolean update(Order order) {
	 * 
	 * int index = 0; while (index < orders.size() && orders.get(index).getId() !=
	 * order.getId()) { index++; }
	 * 
	 * if (index < orders.size()) { orders.set(index, order); } return index <
	 * orders.size(); }
	 * 
	 * @Override public boolean deleteById(int id) { return orders.removeIf(i ->
	 * i.getId() == id); }
	 */

}