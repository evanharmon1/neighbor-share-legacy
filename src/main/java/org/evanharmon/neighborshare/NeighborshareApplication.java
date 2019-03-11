package org.evanharmon.neighborshare;

import org.evanharmon.neighborshare.models.Item;
import org.evanharmon.neighborshare.models.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeighborshareApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeighborshareApplication.class, args);

		Item newItem = new Item(1L,"mower", Category.LAWNCARE, "here", "there");

		Item.addAllItems(newItem);
	}

}
