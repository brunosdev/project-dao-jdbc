package application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department obj = new Department(01, "Books");

		Seller seller = new Seller(13, "Adrian", "adrian@gmail.com", new Date(), 2000.0, obj);

		System.out.println(seller);
	}
}
