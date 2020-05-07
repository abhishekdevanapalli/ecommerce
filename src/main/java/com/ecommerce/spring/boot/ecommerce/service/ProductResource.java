package com.ecommerce.spring.boot.ecommerce.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.spring.boot.ecommerce.model.Order;
import com.ecommerce.spring.boot.ecommerce.model.Product;
import com.ecommerce.spring.boot.ecommerce.model.Status;

@RestController
public class ProductResource {

	private String notAvailable = "Selected item is not available for placing an order";
	private String outOfStock = "Selected item is Out of Stock or selected quantity is not available. Available quantity is: ";

	@Autowired
	private ProductRepository productRepository;

	// retrieve all the available products
	@GetMapping("/products")
	public List<Product> retrieveAllProducts() {
		return productRepository.findAll();
	}

	// retrieve a particular product by id
	@GetMapping("/products/{id}")
	public Product retrieveProduct(@PathVariable long id) throws Exception {
		Optional<Product> product = productRepository.findById(id);

		if (!product.isPresent())
			throw new Exception("id-" + id);

		return product.get();
	}

	// delete a product by id
	@DeleteMapping("/products/{id}")
	public void deleteProduct(@PathVariable long id) {
		productRepository.deleteById(id);
	}

	@PostMapping("/products")
	public ResponseEntity<Object> addProduct(@RequestBody Product product) {
		Product savedProduct = productRepository.save(product);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedProduct.getId()).toUri();

		return ResponseEntity.created(location).build();

	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Object> updateProduct(@RequestBody Product product, @PathVariable long id) {

		Optional<Product> productOptional = productRepository.findById(id);

		if (!productOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			product.setId(id);

			productRepository.save(product);

			return ResponseEntity.noContent().build();
		}
	}

	@Autowired
	private OrderRepository orderRepository;

	@PostMapping("/products/order/{id}/{email}/{quantity}")
	public Status placeSingleOrder(@PathVariable long id, @PathVariable String email, @PathVariable int quantity) {
		Optional<Product> orderedProduct = productRepository.findById(id);
		Status status = new Status();

		if (!orderedProduct.isPresent()) {
			status.setMessage(notAvailable);
		} else {

			if (quantity > orderedProduct.get().getQuantity()) {
				status.setMessage(outOfStock + orderedProduct.get().getQuantity());
			} else {
				int randInt = Math.abs(ThreadLocalRandom.current().nextInt());

				String message = "Your order has been placed successfully. Use this id: " + randInt
						+ " to track your order. Thanks for shopping with us";
				status.setMessage(message);

				// update the quantity in database

				updateQuantityAfterOrder(quantity, orderedProduct);

				// change the quantity in response
				Product product = orderedProduct.get();
				product.setQuantity(quantity);
				status.setProduct(product);

				// save order into database
				Order order = new Order();
				order.setId(randInt);
				order.setEmail(email);
				order.setQuantity(quantity);

				orderRepository.save(order);
			}
		}
		return status;

	}

	private void updateQuantityAfterOrder(int quantity, Optional<Product> orderedProduct) {
		Product product = orderedProduct.get();
		quantity = product.getQuantity() - quantity;
		product.setQuantity(quantity);
//		updateProduct(product, product.getId());
		productRepository.save(product);
	}

}