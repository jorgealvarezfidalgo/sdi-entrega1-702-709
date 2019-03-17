package com.uniovi.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
							df.parse("2018-02-01"), 45.0, user1));
					add(new Offer("La Comunidad del Anillo", "Por JRR Tolkien, parte de la trilogía de ESDLA",
							df.parse("2019-03-01"), 20.0, user1));
					add(new Offer("BMW 320i", "Siempre en garaje. Persona mayor y no fumadora. Muy buen estado", null,
							40.0, user1));
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
					add(new Offer("Guitarra española", "Marca Alhambra", df.parse("2018-03-10"), 35.99, user2));
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
					add(new Offer("Cadillac CTS-V", "2005, 250 CV", null, 45.0, user3));
					add(new Offer("Paraguas negro", "Ligeramente oxidado", df.parse("2019-03-01"), 4.3, user3));
					add(new Offer("Motosierra", "Engrasada y lista para la acción.", null, 24.0, user3));
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
							1000.0, user4));
					add(new Offer("Gaita asturiana", "Fabricación artesanal por Varillas", df.parse("2019-03-03"), 50.3,
							user4));
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
							df.parse("2018-01-04"), 30.7, user5));
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
		Chat chat12 = new Chat(user2Offers.get(1), user1);
		Chat chat13 = new Chat(user3Offers.get(2), user1);
		chatsService.addChat(chat12);
		chatsService.addChat(chat13);

		Chat chat34 = new Chat(user4Offers.get(1), user3);
		Chat chat35 = new Chat(user5Offers.get(2), user3);
		chatsService.addChat(chat34);
		chatsService.addChat(chat35);

		// Chats vacíos

//		Chat chat23 = new Chat(user3Offers.get(1), user2);
//		Chat chat24 = new Chat(user4Offers.get(2), user2);
//		chatsService.addChat(chat23);
//		chatsService.addChat(chat24);

//		Chat chat45 = new Chat(user5Offers.get(1), user4);
//		Chat chat41 = new Chat(user1Offers.get(2), user4);
//		chatsService.addChat(chat45);
//		chatsService.addChat(chat41);

//		Chat chat51 = new Chat(user1Offers.get(1), user5);
//		Chat chat52 = new Chat(user2Offers.get(2), user5);
//		chatsService.addChat(chat51);
//		chatsService.addChat(chat52);

		// Añadir mensajes a los chats

		Message message121 = new Message(chat12, user1, "Hola, estoy interesado en tu oferta");
		Message message122 = new Message(chat12, user2, "Buenas");
		Message message123 = new Message(chat12, user1, "El precio es negocialbe?");
		Message message124 = new Message(chat12, user2, "No, lo siento");
		messagesService.addMessage(message121);
		messagesService.addMessage(message122);
		messagesService.addMessage(message123);
		messagesService.addMessage(message124);

		Message message131 = new Message(chat13, user1, "Te interesa cambio?");
		Message message132 = new Message(chat13, user3, "No, solo dinero");
		Message message133 = new Message(chat13, user1, "Vale, entiendo");
		Message message134 = new Message(chat13, user3, "Te sigue interesando?");
		messagesService.addMessage(message131);
		messagesService.addMessage(message132);
		messagesService.addMessage(message133);
		messagesService.addMessage(message134);

		Message message341 = new Message(chat34, user3, "Buenos días");
		Message message342 = new Message(chat34, user4, "Hola");
		Message message343 = new Message(chat34, user3, "Gran oferta");
		Message message344 = new Message(chat34, user4, "Gracias, tengo a mucha gente interesada");
		messagesService.addMessage(message341);
		messagesService.addMessage(message342);
		messagesService.addMessage(message343);
		messagesService.addMessage(message344);

		Message message351 = new Message(chat35, user3, "El precio es de dos unidades?");
		Message message352 = new Message(chat35, user5, "De una");
		Message message353 = new Message(chat35, user3, "vaya");
		Message message354 = new Message(chat35, user5, "Es lo que hay");
		messagesService.addMessage(message351);
		messagesService.addMessage(message352);
		messagesService.addMessage(message353);
		messagesService.addMessage(message354);

		// Borrado de prueba de chats
//		chatsService.deleteChat(chat12);
//		chatsService.deleteChat(chat13);
//		chatsService.deleteChat(chat34);
//		chatsService.deleteChat(chat35);

		// Chats vacíos

//		chatsService.deleteChat(chat23);
//		chatsService.deleteChat(chat24);
//		chatsService.deleteChat(chat45);
//		chatsService.deleteChat(chat41);
//		chatsService.deleteChat(chat51);
//		chatsService.deleteChat(chat52);

	}

}