package org.evanharmon.neighborshare;

import org.evanharmon.neighborshare.models.Group;
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
		Item newItem = new Item(1, "Lawn Mower", "Vroom", "path", Category.LAWNCARE);
		Item secondItem = new Item(2, "Pressure Cooker", "Boom", "path", Category.COOKING);

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

		Group harmons = new Group(1, "The Nietzsches", "Nietzsches, etc.");
		Group taylors = new Group(2, "The Wittgensteins", "Wittgenstein...");

		harmons.addUser(fred);
		taylors.addUser(ludwig);

		Group.addAllGroups(harmons);
		Group.addAllGroups(taylors);

		System.out.println(Group.getAllGroups());


	}

}
