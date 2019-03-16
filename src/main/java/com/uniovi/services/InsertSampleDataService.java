package com.uniovi.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Chat;
import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;

	@Autowired
	private OffersService offersService;

	@Autowired
	private RolesService rolesService;

	@Autowired
	private ChatsService chatsService;
	
	@Autowired
	private MessagesService messagesService;

	@PostConstruct
	public void init() {
		User user1 = new User("uo216936@uniovi.es", "Lino", "Menéndez");
		user1.setPassword("123456");
		user1.setRole(rolesService.getRoles()[0]);
		User user2 = new User("cruzadaeterna@gmail.com", "Marshal", "Helbrecht");
		user2.setPassword("123456");
		user2.setRole(rolesService.getRoles()[0]);
		User user3 = new User("armaggedon41@hotmail.com", "Sebastian", "Yarrick");
		user3.setPassword("123456");
		user3.setRole(rolesService.getRoles()[0]);
		User user4 = new User("helsreach@yahoo.es", "Merek", "Grimaldus");
		user4.setPassword("123456");
		user4.setRole(rolesService.getRoles()[0]);
		User user5 = new User("alpha-legion@gmail.com", "Sindri", "Myr");
		user5.setPassword("123456");
		user5.setRole(rolesService.getRoles()[0]);
		User user6 = new User("admin@email.com", "Jorge", "Fidalgo");
		user6.setPassword("admin");
		user6.setRole(rolesService.getRoles()[1]);

		List<Offer> user1Offers = null;
		try {
			user1Offers = new ArrayList<Offer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					add(new Offer("Espada toledana", "Puro acero templado en las forjas de Castilla",
							df.parse("2018-02-01"), 120.0, user1));
					add(new Offer("La Comunidad del Anillo", "Por JRR Tolkien, parte de la trilogía de ESDLA",
							df.parse("2019-03-01"), 20.0, user1));
					add(new Offer("BMW 320i", "Siempre en garaje. Persona mayor y no fumadora. Muy buen estado", null,
							70.0, user1));
				}
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user1.setOffers(new HashSet<>(user1Offers));

		List<Offer> user2Offers = null;
		try {
			user2Offers = new ArrayList<Offer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					add(new Offer("Guitarra española", "Marca Alhambra", df.parse("2018-03-10"), 165.99, user2));
					add(new Offer("La Divina Comedia", "Clásico indiscutible de Dante Alighieri",
							df.parse("2019-03-04"), 11.6, user2));
					add(new Offer("TES III: Morrowind", "Edición GOTY", null, 15.5, user2));
				}
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user2.setOffers(new HashSet<>(user2Offers));

		List<Offer> user3Offers = null;
		try {
			user3Offers = new ArrayList<Offer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					add(new Offer("Cadillac CTS-V", "2005, 250 CV", null, 4500.0, user3));
					add(new Offer("Paraguas negro", "Ligeramente oxidado", df.parse("2019-03-01"), 4.3, user3));
					add(new Offer("Motosierra", "Engrasada y lista para la acción.", null, 74.0, user3));
				}
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user3.setOffers(new HashSet<>(user3Offers));

		List<Offer> user4Offers = null;
		try {
			user4Offers = new ArrayList<Offer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					add(new Offer("Relicario", "Manifestación material de la voluntad divina", df.parse("2018-01-151"),
							438.0, user4));
					add(new Offer("Gaita asturiana", "Fabricación artesanal por Varillas", df.parse("2019-03-03"),
							70.3, user4));
					add(new Offer("Nokia 3000", "Absolutamente indestructible", null, 20.0, user4));
				}
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user4.setOffers(new HashSet<>(user4Offers));

		List<Offer> user5Offers = null;
		try {
			user5Offers = new ArrayList<Offer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					add(new Offer("Yelmo templario",
							"Siglo XII. Perteneciente a la Orden de los Pobres Compañeros de Cristo"
									+ " y del Templo de Salomón. ",
							df.parse("2018-01-04"), 80.0, user5));
					add(new Offer("Cheytac Intervención", "Fusil de largo alcance.", df.parse("2019-08-17"), 25.0,
							user5));
					add(new Offer("Libro de salmos ucraniano.", "Encontrado en Pripyat. Ligeramente irradiado.", null,
							15.0, user5));
				}
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user5.setOffers(new HashSet<>(user5Offers));

		// Asignar compras hecas por usuarios -> FALLA
//		Set<Offer> user10Purchases = new HashSet<>();
//		user10Purchases.add(user2Offers.get(0));
//		user10Purchases.add(user2Offers.get(1));
//		user1.setPurchases(user10Purchases);
//		
//		Set<Offer> user20Purchases = new HashSet<>();
//		user10Purchases.add(user3Offers.get(0));
//		user10Purchases.add(user3Offers.get(1));
//		user2.setPurchases(user20Purchases);
//		
//		Set<Offer> user30Purchases = new HashSet<>();
//		user10Purchases.add(user4Offers.get(0));
//		user10Purchases.add(user4Offers.get(1));
//		user3.setPurchases(user30Purchases);
//		
//		Set<Offer> user40Purchases = new HashSet<>();
//		user10Purchases.add(user5Offers.get(0));
//		user10Purchases.add(user5Offers.get(1));
//		user4.setPurchases(user40Purchases);
//		
//		Set<Offer> user50Purchases = new HashSet<>();
//		user10Purchases.add(user1Offers.get(0));
//		user10Purchases.add(user1Offers.get(1));
//		user5.setPurchases(user50Purchases);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);

		// Asignar compras hecas por usuarios
		offersService.buyOffer(user1, user2Offers.get(1));
		offersService.buyOffer(user1, user3Offers.get(2));

		offersService.buyOffer(user2, user3Offers.get(1));
		offersService.buyOffer(user2, user4Offers.get(2));

		offersService.buyOffer(user3, user4Offers.get(1));
		offersService.buyOffer(user3, user5Offers.get(2));

		offersService.buyOffer(user4, user5Offers.get(1));
		offersService.buyOffer(user4, user1Offers.get(2));

		offersService.buyOffer(user5, user1Offers.get(1));
		offersService.buyOffer(user5, user2Offers.get(2));
		
		// Añadir chats
		Chat chat11 = new Chat(user2Offers.get(1), user1);
		Chat chat12 = new Chat(user3Offers.get(2), user1);
		chatsService.addChat(chat11);
		chatsService.addChat(chat12);
		
		Chat chat21 = new Chat(user3Offers.get(1), user2);
		Chat chat22 = new Chat(user4Offers.get(2), user2);
		chatsService.addChat(chat21);
		chatsService.addChat(chat22);
		
		Chat chat31 = new Chat(user4Offers.get(1), user3);
		Chat chat32 = new Chat(user5Offers.get(2), user3);
		chatsService.addChat(chat31);
		chatsService.addChat(chat32);
		
		Chat chat41 = new Chat(user5Offers.get(1), user4);
		Chat chat42 = new Chat(user1Offers.get(2), user4);
		chatsService.addChat(chat41);
		chatsService.addChat(chat42);
		
		Chat chat51 = new Chat(user1Offers.get(1), user5);
		Chat chat52 = new Chat(user2Offers.get(2), user5);
		chatsService.addChat(chat51);
		chatsService.addChat(chat52);
		
		// Añadir mensajes a los chats
		Message message11 = new Message(chat11, user1, "Hola, estoy interesado en tu oferta");
		Message message12 = new Message(chat11, user2, "Buenas");
		Message message13 = new Message(chat11, user1, "El precio es negocialbe?");
		Message message14 = new Message(chat11, user2, "No, lo siento");
		messagesService.addMessage(message11);
		messagesService.addMessage(message12);
		messagesService.addMessage(message13);
		messagesService.addMessage(message14);

	}

}