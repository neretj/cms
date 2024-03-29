package com.mojdan.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mojdan.app.model.address.Address;
import com.mojdan.app.model.address.AddressRepository;
import com.mojdan.app.model.category.Category;
import com.mojdan.app.model.category.CategoryRepository;
import com.mojdan.app.model.fileinfo.FileInfo;
import com.mojdan.app.model.fileinfo.FileInfoRepository;
import com.mojdan.app.model.notification.Notification;
import com.mojdan.app.model.notification.NotificationRepository;
import com.mojdan.app.model.notification.NotificationStatus;
import com.mojdan.app.model.notification.NotificationType;
import com.mojdan.app.model.order.ClientOrder;
import com.mojdan.app.model.order.ClientOrderRepository;
import com.mojdan.app.model.order.OrderStatus;
import com.mojdan.app.model.product.Product;
import com.mojdan.app.model.product.ProductRepository;
import com.mojdan.app.model.shop.Shop;
import com.mojdan.app.model.shop.ShopRepository;
import com.mojdan.app.model.tag.Tag;
import com.mojdan.app.model.tag.TagRepository;
import com.mojdan.app.model.user.Customer;
import com.mojdan.app.model.user.CustomerRepository;
import com.mojdan.app.model.user.ERole;
import com.mojdan.app.model.user.Role;
import com.mojdan.app.model.user.RoleRepository;
import com.mojdan.app.model.user.Seller;
import com.mojdan.app.model.user.SellerRepository;
import com.mojdan.app.model.user.Status;
import com.mojdan.app.model.user.User;
import com.mojdan.app.model.user.UserRepository;
import com.mojdan.app.service.product.ProductUtil;
import com.mojdan.app.service.storage.StorageService;

@EntityScan("com.mojdan.app.model")
@SpringBootApplication
public class MojdanApplication {

	private static final Logger log = LoggerFactory.getLogger(MojdanApplication.class);

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(MojdanApplication.class);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepo, CustomerRepository customerRepo,
			AddressRepository addressRepo, RoleRepository roleRepository, ProductRepository productRepo,
			ClientOrderRepository clientOrderRepo, ShopRepository storeRepo, CategoryRepository categoryRepo,
			SellerRepository sellerRepo, NotificationRepository notificationRepo, TagRepository tagRepo,
			FileInfoRepository fileInfoRepo) {
		return (args) -> {
			// save a few users

			Address addr = new Address("123 street", "Madrid", "Spain", new Long(28006));
			addr = addressRepo.save(addr);

			Role roleUser = new Role(ERole.ROLE_USER);
			roleUser = roleRepository.save(roleUser);

			Role roleAdmin = new Role(ERole.ROLE_ADMIN);
			roleAdmin = roleRepository.save(roleAdmin);

			Role roleModerator = new Role(ERole.ROLE_MODERATOR);
			roleModerator = roleRepository.save(roleModerator);

			User user1 = userRepo
					.save(new User("jack@gmail.com", "jack", bCryptPasswordEncoder.encode("jack"), "988232321", Status.ACTIVE, roleUser));
			Customer c1 = customerRepo.save(new Customer(user1, "Jack", "Aaa", addr));

			User user2 = userRepo.save(
					new User("chloe@gmail.com", "chloe", bCryptPasswordEncoder.encode("chloe"), "678737362", Status.ACTIVE, roleUser));
			Customer c2 = customerRepo.save(new Customer(user2, "Chloe", "Heinz", addr));

			User user3 = userRepo
					.save(new User("kim@gmail.com", "kim", bCryptPasswordEncoder.encode("kim"), "664563322", Status.ACTIVE, roleUser));
			Customer c3 = customerRepo.save(new Customer(user3, "Kim", "Jones", addr));

			User user4 = userRepo.save(new User("michelle@outlook.com", "michelle", bCryptPasswordEncoder.encode("michelle"), "603213233",
					Status.ACTIVE, roleUser));
			Customer c4 = customerRepo.save(new Customer(user4, "Michelle", "Michelle", addr));

			User user5 = userRepo.save(
					new User("admin@yahoo.com", "admin", bCryptPasswordEncoder.encode("admin"), "603213233", Status.ACTIVE, roleUser));
			customerRepo.save(new Customer(user5, "admin", "--", addr));

			User user6 = userRepo.save(
					new User("jack@gmail.com", "jack2", bCryptPasswordEncoder.encode("jack"), "988232321", Status.ACTIVE, roleUser));
			customerRepo.save(new Customer(user6, "Jack", "Aaa", addr));

			User user7 = userRepo.save(
					new User("chloefrank@hotmail.com", "chloe2", bCryptPasswordEncoder.encode("chloe"), "678737362", Status.ACTIVE, roleUser));
			customerRepo.save(new Customer(user7, "Chloe", "Heinz", addr));

			Shop storeIkea = storeRepo.save(new Shop("Ikea", addr));
			Shop storeZara = storeRepo.save(new Shop("Zara", addr));

			generateProductAndClientOrderData(productRepo, clientOrderRepo, storeRepo, categoryRepo, tagRepo,
					fileInfoRepo, addr, c1, c2, c3, storeIkea, storeZara);

			/*** SELLER ***/

			User user8 = userRepo.save(
					new User("emmafin@gmail.com", "emmafin", bCryptPasswordEncoder.encode("123456"), "664563322", Status.ACTIVE, roleAdmin));
			sellerRepo.save(new Seller(user8, "Emma", "Finn", addr, storeZara));

			User user9 = userRepo.save(new User("juanlopez@gmail.com", "juanlopez", bCryptPasswordEncoder.encode("123456"), "603213233",
					Status.ACTIVE, roleAdmin));
			sellerRepo.save(new Seller(user9, "Juan", "Lopez", addr, storeZara));

			User user10 = userRepo.save(new User("admin@gmail.com", "adminuser", bCryptPasswordEncoder.encode("123456"), "603213233",
					Status.ACTIVE, roleAdmin));
			sellerRepo.save(new Seller(user10, "Admin", "User", addr, storeIkea));

			generateNotificationData(notificationRepo, storeIkea, storeZara);

			storageService.deleteAll();
			storageService.init();

			// fetch all users
			log.info("Users found with findAll():");
			log.info("-------------------------------");
			for (User user : userRepo.findAll()) {
				log.info(user.toString());
			}
			log.info("");

			// fetch an individual user by ID
			Optional<User> user = userRepo.findById(1L);
			log.info("User found with findById(1L):");
			log.info("--------------------------------");
			log.info(user.toString());
			log.info("");

			// fetch users by last name
			log.info("User found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			Optional<User> usertwo = userRepo.findByUsername("Bauer");
			log.info("User found with findByUsername(Bauer):");
			log.info("--------------------------------");
			log.info(usertwo.toString());
			log.info("");
			// for (User bauer : repository.findByLastName("Bauer")) {
			// log.info(bauer.toString());
			// }
			log.info("");
		};
	}

	private void generateNotificationData(NotificationRepository notificationRepo, Shop storeIkea, Shop storeZara) {
		Notification n1 = notificationRepo.save(new Notification(storeZara, "New user registered",
				NotificationStatus.OPEN, NotificationType.NEW_REGISTER));
		Notification n2 = notificationRepo.save(
				new Notification(storeZara, "New order done", NotificationStatus.OPEN, NotificationType.NEW_ORDER));
		Notification n3 = notificationRepo.save(new Notification(storeZara, "Pending order needs action",
				NotificationStatus.OPEN, NotificationType.ACTION_PENDING));
		Notification n4 = notificationRepo.save(new Notification(storeZara, "There is no stock for product x",
				NotificationStatus.OPEN, NotificationType.ALERT));
		Notification n5 = notificationRepo.save(new Notification(storeIkea, "New user registered",
				NotificationStatus.OPEN, NotificationType.NEW_REGISTER));
		Notification n6 = notificationRepo.save(
				new Notification(storeIkea, "New order done", NotificationStatus.OPEN, NotificationType.NEW_ORDER));
		Notification n7 = notificationRepo.save(new Notification(storeIkea, "Pending order needs action",
				NotificationStatus.OPEN, NotificationType.ACTION_PENDING));
		Notification n8 = notificationRepo.save(new Notification(storeIkea, "There is no stock for product x",
				NotificationStatus.OPEN, NotificationType.ALERT));
	}

	private void generateProductAndClientOrderData(ProductRepository productRepo, ClientOrderRepository clientOrderRepo,
			ShopRepository storeRepo, CategoryRepository categoryRepo, TagRepository tagRepo,
			FileInfoRepository fileInfoRepo, Address addr, Customer c1, Customer c2, Customer c3, Shop storeIkea,
			Shop storeZara) {
		Category categoryF = categoryRepo.save(new Category("Furniture"));

		Tag tag = tagRepo.save(new Tag("Tag1"));
		Tag tag2 = tagRepo.save(new Tag("Tag2"));
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(new Tag("Tag1"));
		tags.add(new Tag("Tag2"));
		List<FileInfo> images = new ArrayList<FileInfo>();
		byte[] img = {};
		FileInfo file = fileInfoRepo.save(new FileInfo("aaa", "png", img));
		images.add(file);
		Product a = new Product("Table Ikea", categoryF, storeIkea, new BigDecimal(80.60), images, true, new Date());
		a.setTags(tags);
		Product product1_1 = productRepo.save(a);
		Product b = new Product("Chair Ikea", categoryF, storeIkea, new BigDecimal(40.60), null, true, new Date());
		Product product1_2 = productRepo.save(b);
		Product product1_3 = productRepo.save(
				new Product("Wardrobe Ikea", categoryF, storeIkea, new BigDecimal(43.35), null, true, new Date()));
		Product product1_4 = productRepo.save(
				new Product("Shelving Ikea", categoryF, storeIkea, new BigDecimal(23.90), null, true, new Date()));
		Product product1_5 = productRepo
				.save(new Product("Table Ikea", categoryF, storeIkea, new BigDecimal(54), null, true, new Date()));
		Product product1_6 = productRepo
				.save(new Product("Chair Ikea", categoryF, storeIkea, new BigDecimal(40.60), null, true, new Date()));
		Product product1_7 = productRepo.save(
				new Product("Wardrobe Ikea", categoryF, storeIkea, new BigDecimal(332.35), null, true, new Date()));
		Product product1_8 = productRepo.save(
				new Product("Shelving Ikea", categoryF, storeIkea, new BigDecimal(43.90), null, true, new Date()));

		List<Product> products = new ArrayList<Product>();
		products.add(product1_1);
		products.add(product1_2);
		products.add(product1_3);
		products.add(product1_4);

		ClientOrder clientOrder1 = new ClientOrder(products, new Date(), c3, new BigDecimal(123.0),
				OrderStatus.COMPLETED);
		clientOrderRepo.save(clientOrder1);

		Category categoryC = categoryRepo.save(new Category("Clothes"));
		Product product2_1 = productRepo.save(
				new Product("Shirt White", categoryC, storeZara, new BigDecimal(29.99), null, true, new Date()));
		Product product2_2 = productRepo.save(
				new Product("Jacket White", categoryC, storeZara, new BigDecimal(29.99), null, true, new Date()));
		Product product2_3 = productRepo.save(
				new Product("Trouser White", categoryC, storeZara, new BigDecimal(29.99), null, true, new Date()));
		Product product2_4 = productRepo.save(
				new Product("Skirt White", categoryC, storeZara, new BigDecimal(29.99), null, true, new Date()));

		List<Product> products2 = new ArrayList<Product>();
		products2.add(product2_1);
		products2.add(product2_2);
		products2.add(product2_3);
		products2.add(product2_4);
		Double total = ProductUtil.getTotalAmount(products2);

		ClientOrder clientOrder2 = new ClientOrder(products2, new Date(), c2, new BigDecimal(total),
				OrderStatus.PENDING);
		clientOrderRepo.save(clientOrder2);

		List<Product> products3 = new ArrayList<Product>();
		products3.add(product2_4);
		products3.add(product2_3);
		total = ProductUtil.getTotalAmount(products3);

		ClientOrder clientOrder3 = new ClientOrder(products3, new Date(), c3, new BigDecimal(total),
				OrderStatus.COMPLETED);
		clientOrderRepo.save(clientOrder3);

		List<Product> products4 = new ArrayList<Product>();
		products4.add(product1_4);
		products4.add(product2_1);
		products4.add(product2_3);
		total = ProductUtil.getTotalAmount(products4);

		ClientOrder clientOrder4 = new ClientOrder(products4, new Date(), c1, new BigDecimal(total),
				OrderStatus.CANCELED);
		clientOrderRepo.save(clientOrder4);

		List<Product> products5 = new ArrayList<Product>();
		products5.add(product2_4);
		products5.add(product1_1);
		products5.add(product1_3);
		total = ProductUtil.getTotalAmount(products5);

		ClientOrder clientOrder5 = new ClientOrder(products5, new Date(), c2, new BigDecimal(total),
				OrderStatus.PENDING);
		clientOrderRepo.save(clientOrder5);

		ClientOrder clientOrder6 = new ClientOrder(products5, new Date(), c2, new BigDecimal(total),
				OrderStatus.COMPLETED);
		clientOrderRepo.save(clientOrder6);

		ClientOrder clientOrder7 = new ClientOrder(products5, new Date(), c2, new BigDecimal(total),
				OrderStatus.COMPLETED);
		clientOrderRepo.save(clientOrder7);
	}

}
