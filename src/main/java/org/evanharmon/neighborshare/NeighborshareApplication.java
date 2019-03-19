package org.evanharmon.neighborshare;

import org.evanharmon.neighborshare.models.Item;
import org.evanharmon.neighborshare.models.Category;
import org.evanharmon.neighborshare.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NeighborshareApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeighborshareApplication.class, args);

		//Prototyping
		Item newItem = new Item("Lawn Mower", "Vroom", Category.LAWNCARE);
		Item secondItem = new Item("Pressure Cooker", "Boom", Category.COOKING);

		Item.addAllItems(newItem);
		Item.addAllItems(secondItem);

		User fred = new User("fred@nietzsche.com", "pass", "Fred", "Nietzsche");
		User ludwig = new User("ludwig@wittgenstein.com", "pass", "Ludwig", "Wittgenstein");

		User.addAllUsers(fred);
		User.addAllUsers(ludwig);

		newItem.setOwner(fred);
		secondItem.setOwner((ludwig));

		System.out.println(newItem.getOwner());
		System.out.println((secondItem.getOwner()));

	}

}
