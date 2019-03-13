package com.uniovi.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;

@Service
public class InsertSampleDataService {

	@Autowired
	private UsersService usersService;

	@Autowired
	private RolesService rolesService;

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
		
		Set<Offer> user1Offers = null;
		try {
			user1Offers = new HashSet<Offer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					add(new Offer("Espada toledana", "Puro acero templado en las forjas de Castilla", df.parse("2018-02-01"),120.0, user1));
					add(new Offer("La Comunidad del Anillo", "Por JRR Tolkien, parte de la trilogía de ESDLA", df.parse("2019-03-01"),54.6, user1));
					add(new Offer("BMW 320i", "Siempre en garaje. Persona mayor y no fumadora. Muy buen estado", null ,3000.0, user1));
				}
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user1.setOffers(user1Offers);
		
		Set<Offer> user2Offers = null;
		try {
			user2Offers = new HashSet<Offer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					add(new Offer("Guitarra española", "Marca Alhambra", df.parse("2018-03-10"),165.99, user2));
					add(new Offer("La Divina Comedia", "Clásico indiscutible de Dante Alighieri", df.parse("2019-03-04"),25.6, user2));
					add(new Offer("TES III: Morrowind", "Edición GOTY", null ,15.5, user2));
				}
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user2.setOffers(user2Offers);
		
		Set<Offer> user3Offers = null;
		try {
			user3Offers = new HashSet<Offer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					add(new Offer("Cadillac CTS-V", "2005, 250 CV", null, 4500.0, user3));
					add(new Offer("Paraguas negro", "Ligeramente oxidado", df.parse("2019-03-01"),4.3, user3));
					add(new Offer("Motosierra", "Engrasada y lista para la acción.", null ,134.0, user3));
				}
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user3.setOffers(user3Offers);
		
		Set<Offer> user4Offers = null;
		try {
			user4Offers = new HashSet<Offer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					add(new Offer("Relicario", "Manifestación material de la voluntad divina", df.parse("2018-01-151"),438.0, user4));
					add(new Offer("Gaita asturiana", "Fabricación artesanal por Varillas", df.parse("2019-03-03"),1537.3, user4));
					add(new Offer("Nokia 3000", "Absolutamente indestructible", null ,3000.0, user4));
				}
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user4.setOffers(user4Offers);
		
		Set<Offer> user5Offers = null;
		try {
			user5Offers = new HashSet<Offer>() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					add(new Offer("Yelmo templario", "Siglo XII. Perteneciente a la Orden de los Pobres Compañeros de Cristo"
							+ " y del Templo de Salomón. ", df.parse("2018-01-04"),120.0, user5));
					add(new Offer("Cheytac Intervención", "Fusil de largo alcance.", df.parse("2019-08-17"),1876.5, user5));
					add(new Offer("Libro de salmos ucraniano.", "Encontrado en Pripyat. Ligeramente irradiado.", null ,15.0, user5));
				}
			};
		} catch (ParseException e) {
			e.printStackTrace();
		}
		user5.setOffers(user5Offers);

		usersService.addUser(user1);
		usersService.addUser(user2);
		usersService.addUser(user3);
		usersService.addUser(user4);
		usersService.addUser(user5);
		usersService.addUser(user6);
		
		
	}

}