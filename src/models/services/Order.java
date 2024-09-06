package models.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import models.entities.Client;
import models.enums.OrderStatus;

public class Order {
	private Instant moment;
	private OrderStatus status;
	private Client client;
	List<OrderItem> list = new ArrayList<>();
	DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").withZone(ZoneId.systemDefault());
	DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	public Order(Instant moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}
	
	public void addItem(OrderItem item) {
		list.add(item);
	}
	
	public void removeItem(OrderItem item) {
		list.remove(item);
	}
	
	public Double total() {
		double sum = 0;
		for(OrderItem item: list) {
			sum += item.subtTotal();
		}
		
		return sum;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order moment: " + fmt.format(moment) + "\n");
		sb.append("Order status: " + status + "\n");
		sb.append("Client: " + client.getName() + " (" + fmt2.format(client.getBirthDate()) + ") - " + client.getEmail() + "\n");
		sb.append("Order items: \n");
		for (OrderItem item: list) {
			sb.append(item.getProduct().getName() + ", $" + String.format("%.2f", item.getPrice()) + ", Quantity: " + item.getQuantity() + ", Subtotal: $"  + String.format("%.2f", item.subtTotal())+ "\n");
		}
		sb.append("Total price: " + String.format("%.2f", total()));
		return sb.toString();
	}

	
	
}
