package com.uniovi.tests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Chat;
import com.uniovi.entities.Message;
import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.ChatsService;
import com.uniovi.services.MessagesService;
import com.uniovi.services.OffersService;
import com.uniovi.services.RolesService;
import com.uniovi.services.UsersService;
import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class SdiEntrega1702709Tests {


	@Autowired
	private UsersService usersService;
	@Autowired
	private RolesService rolesService;
	@Autowired
	private OffersService offersService;
	@Autowired
	private MessagesService messagesService;
	@Autowired
	private ChatsService chatsService;
	@Autowired
	private UsersRepository usersRepository;


	// En Windows (Debe ser la versión 65.0.1 y desactivar las
	// actualizacioens automáticas)):
	static String PathFirefox65 = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\woest\\OneDrive\\Documentos\\SDI\\geckodriver024win64.exe";
	//static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	//static String Geckdriver024 = "C:\\Users\\linom\\Google Drive\\Informática\\Sistemas Distribuidos e Internet (SDI)\\5. Web testing con Selenium\\Material-Sesión5\\geckodriver024win64.exe";

	// //Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URLLOCAL = "http://localhost:8080";
	static String URLREMOTA = "http://35.181.48.225:8080";
	static String URL = URLLOCAL;
	

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Before
	public void setUp() throws Exception {
		initDb();
		driver.navigate().to(URL);
	}

	public void initDb() {
		usersRepository.deleteAll();
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

	@After
	public void tearDown() throws Exception {
		driver.manage().deleteAllCookies();
	}

	@BeforeClass
	static public void begin() {
	}

	@AfterClass
	static public void end() {
		driver.quit();
	}

	@Test
	public void PR01() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "boromir@gondor.nz", "Boromir", "Hijo De Denethor", "Osgiliath", "Osgiliath");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "Ofertas destacadas");
	}

	@Test
	public void PR02() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.

		PO_RegisterView.fillForm(driver, "", "Garrus", "Vakarian", "N7N7N7", "N7N7N7");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

		PO_RegisterView.fillForm(driver, "arcangel@omega.ci", "", "Vakarian", "N7N7N7", "N7N7N7");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

		PO_RegisterView.fillForm(driver, "arcangel@omega.ci", "Garrus", "", "N7N7N7", "N7N7N7");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

	}

	@Test
	public void PR03() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "arcangel@omega.ci", "Garrus", "Vakarian", "N7N7N7", "N7N7N");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}

	@Test
	public void PR04() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "cruzadaeterna@gmail.com", "Faramir", "Hijo De Denethor", "3age", "3age");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
	}

	@Test
	public void PR05() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_HomeView.clickOptionById(driver, "userdropdown", "id", "seeusers");
	}

	@Test
	public void PR06() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_HomeView.clickOptionById(driver, "offerdropdown", "id", "addoffer");
	}

	// PRN. Loguearse con exito desde el ROl de Admin
	@Test
	public void PR07() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "", "");
		PO_View.checkElement(driver, "id", "loginbtn");
	}

	@Test
	public void PR08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "armaggeddon");
		PO_View.checkElement(driver, "id", "loginbtn");
	}

	@Test
	public void PR09() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "rogal-dorn@hotmail.com", "damnthecodex");
		PO_View.checkElement(driver, "id", "loginbtn");
	}

	@Test
	public void PR10() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_HomeView.clickOptionById(driver, "offerdropdown", "id", "addoffer");

		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "id", "loginbtn");
	}

	@Test
	public void PR11() {
		// El usuario no está autenticado
		SeleniumUtils.textoNoPresentePagina(driver, "Cerrar sesión");
	}

	@Test
	public void PR12() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_HomeView.clickOptionById(driver, "userdropdown", "id", "seeusers");

		PO_HomeView.clickOptionById(driver, "seeusers", "id", "tableUsers");

		PO_View.checkElement(driver, "free", "//td[contains(text(), 'uo216936@uniovi.es')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'cruzadaeterna@gmail.com')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'armaggedon41@hotmail.com')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'helsreach@yahoo.es')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'alpha-legion@gmail.com')]");
	}

	@Test
	public void PR13() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_HomeView.clickOptionById(driver, "userdropdown", "id", "seeusers");

		PO_HomeView.clickOptionById(driver, "seeusers", "id", "tableUsers");
		List<WebElement> eliminar = PO_View.checkElement(driver, "free", "//input[contains(@id,'deleteChk')]");
		String id = eliminar.get(0).getAttribute("id").replaceFirst("deleteChk", "");

		User userToDelete = usersService.getUser(Long.parseLong(id, 10));

		PO_View.checkElement(driver, "free", "//td[contains(text(), '" + userToDelete.getEmail() + "')]");
		eliminar.get(0).click();

		PO_HomeView.clickOptionById(driver, "deleteButton", "id", "tableUsers");
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, userToDelete.getEmail(), PO_View.getTimeout());

	}

	@Test
	public void PR14() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_HomeView.clickOptionById(driver, "userdropdown", "id", "seeusers");

		PO_HomeView.clickOptionById(driver, "seeusers", "id", "tableUsers");

		List<WebElement> eliminar = PO_View.checkElement(driver, "free", "//input[contains(@id,'deleteChk')]");
		String id = eliminar.get(eliminar.size() - 1).getAttribute("id").replaceFirst("deleteChk", "");

		User userToDelete = usersService.getUser(Long.parseLong(id, 10));

		PO_View.checkElement(driver, "free", "//td[contains(text(), '" + userToDelete.getEmail() + "')]");
		eliminar.get(eliminar.size() - 1).click();

		PO_HomeView.clickOptionById(driver, "deleteButton", "id", "tableUsers");
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, userToDelete.getEmail(), PO_View.getTimeout());

	}

	@Test
	public void PR15() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_HomeView.clickOptionById(driver, "userdropdown", "id", "seeusers");

		PO_HomeView.clickOptionById(driver, "seeusers", "id", "tableUsers");

		List<WebElement> eliminar = PO_View.checkElement(driver, "free", "//input[contains(@id,'deleteChk')]");
		String id1 = eliminar.get(1).getAttribute("id").replaceFirst("deleteChk", "");
		String id2 = eliminar.get(2).getAttribute("id").replaceFirst("deleteChk", "");
		String id3 = eliminar.get(3).getAttribute("id").replaceFirst("deleteChk", "");

		User userToDelete1 = usersService.getUser(Long.parseLong(id1, 10));
		User userToDelete2 = usersService.getUser(Long.parseLong(id2, 10));
		User userToDelete3 = usersService.getUser(Long.parseLong(id3, 10));

		PO_View.checkElement(driver, "free", "//td[contains(text(), '" + userToDelete1.getEmail() + "')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), '" + userToDelete2.getEmail() + "')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), '" + userToDelete3.getEmail() + "')]");
		eliminar.get(1).click();
		eliminar.get(2).click();
		eliminar.get(3).click();

		PO_HomeView.clickOptionById(driver, "deleteButton", "id", "tableUsers");

		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, userToDelete1.getEmail(), PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, userToDelete2.getEmail(), PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, userToDelete3.getEmail(), PO_View.getTimeout());
	}

	@Test
	public void PR16() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/add')]", 0);

		PO_PrivateView.fillFormAddOffer(driver, "Nanomáquinas", "Se activan en respuesta al trauma físico", "666",
				"2019-03-04", false);
		// Comprobamos que aparece la nota en la pagina
		SeleniumUtils.EsperaCargaPagina(driver, "text", "Nanomáquinas", PO_View.getTimeout());
	}

	@Test
	public void PR17() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/add')]", 0);

		PO_PrivateView.fillFormAddOffer(driver, "", "Se activan en respuesta al trauma físico", "666", "2019-03-04",
				false);

		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());

	}

	@Test
	public void PR18() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listown')]", 0);

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tr");
		Assert.assertTrue(elementos.size() == 4);
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'Guitarra española')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'La Divina Comedia')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'TES III: Morrowind')]");
	}

	@Test
	public void PR19() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listown')]", 0);

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tr");
		Assert.assertTrue(elementos.size() == 4);
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'Guitarra española')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'La Divina Comedia')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'TES III: Morrowind')]");
	}

	@Test
	public void PR20() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listown')]", 0);

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tr");
		Assert.assertTrue(elementos.size() == 4);
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'Guitarra española')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'La Divina Comedia')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'TES III: Morrowind')]");

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'Guitarra española')]/following-sibling::*/a[contains(@href, 'offer/delete')]",
				0);
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tr", PO_View.getTimeout());
		Assert.assertTrue(elementos.size() == 3);
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Guitarra española", PO_View.getTimeout());
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'La Divina Comedia')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'TES III: Morrowind')]");
	}

	@Test
	public void PR21() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		// Seleccionamos el menú del nav para comprar
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'purchases-menu')]/a", 0);
		// Y en ese menú la opción de buscar ofertas
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listothers')]", 0);

		// Buscamos con el campo vacío
		PO_PrivateView.fillSearchOffer(driver, "");

		// Hay 12 ofertas en total. 5 en la primera página
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		Assert.assertTrue(elementos.size() == 5);
		By enlace = By.xpath("//html/body/div/div[2]/ul/li[3]/a");
		driver.findElement(enlace).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		// Y 5 en la segunda
		Assert.assertTrue(elementos.size() == 5);

		enlace = By.xpath("//html/body/div/div[2]/ul/li[4]/a");
		driver.findElement(enlace).click();
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		// Y 2 en la tercera
		Assert.assertTrue(elementos.size() == 2);
	}

	@Test
	public void PR22() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		// Seleccionamos el menú del nav para comprar
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'purchases-menu')]/a", 0);
		// Y en ese menú la opción de buscar ofertas
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listothers')]", 0);

		// Buscamos un producto que no existe
		PO_PrivateView.fillSearchOffer(driver, "diplodocus");

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tr");
		// Un único resultado (la cabecera de la tabla, vacía)
		Assert.assertTrue(elementos.size() == 1);
	}

	@Test
	public void PR23() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		// Seleccionamos el menú del nav para comprar
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'purchases-menu')]/a", 0);
		// Y en ese menú la opción de buscar ofertas
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listothers')]", 0);

		// Buscamos productos que tengan en su título "la"
		PO_PrivateView.fillSearchOffer(driver, "cadillac");
		List<WebElement> saldos = PO_View.checkElement(driver, "free", "//nav/div/div[2]/ul[3]/li[2]/span");
		double saldo = Double.valueOf(saldos.get(0).getText());
		Assert.assertTrue(saldo == 75.7);

		// Compramos un podructo de 20
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//td/div/div/button");
		elementos.get(0).click();
		saldos = PO_View.checkElement(driver, "free", "//nav/div/div[2]/ul[3]/li[2]/span");
		saldo = Double.valueOf(saldos.get(0).getText());
		Assert.assertTrue(saldo == 75.7 - 45.0);
	}

	@Test
	public void PR24() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		// Seleccionamos el menú del nav para comprar
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'purchases-menu')]/a", 0);
		// Y en ese menú la opción de buscar ofertas
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listothers')]", 0);

		// Buscamos productos que tengan en su título "anillo"
		PO_PrivateView.fillSearchOffer(driver, "cadillac");
		List<WebElement> saldos = PO_View.checkElement(driver, "free", "//nav/div/div[2]/ul[3]/li[2]/span");
		double saldo = Double.valueOf(saldos.get(0).getText());
		Assert.assertTrue(saldo == 75.7);

		// Compramos dos productos que suman 100
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//td/div/div/button");
		elementos.get(0).click();
		PO_PrivateView.fillSearchOffer(driver, "yelmo");
		elementos = PO_View.checkElement(driver, "free", "//td/div/div/button");
		elementos.get(0).click();

		saldos = PO_View.checkElement(driver, "free", "//nav/div/div[2]/ul[3]/li[2]/span");
		saldo = Double.valueOf(saldos.get(0).getText());
		Assert.assertTrue(saldo == 75.7 - 45.0 - 30.7);
	}
	
	@Test
	public void PR25() {
		// Vamos al formulario de logueo.
				PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
				// Rellenamos el formulario
				PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
				PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

				// Seleccionamos el menú del nav para comprar
				PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'purchases-menu')]/a", 0);
				// Y en ese menú la opción de buscar ofertas
				PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listothers')]", 0);

				// Buscamos productos que tengan en su título "anillo"
				PO_PrivateView.fillSearchOffer(driver, "relicario");
				List<WebElement> saldos = PO_View.checkElement(driver, "free", "//nav/div/div[2]/ul[3]/li[2]/span");
				double saldo = Double.valueOf(saldos.get(0).getText());
				Assert.assertTrue(saldo == 75.7);
				List<WebElement> elementos = PO_View.checkElement(driver, "free", "//td/div/div/button");
				elementos.get(0).click();
				Assert.assertTrue(saldo == 75.7);
				PO_View.checkElement(driver, "text", "Saldo insuficiente para destacar la oferta.");
				
	}

	@Test
	public void PR26() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		// Seleccionamos el menú del nav para comprar
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'purchases-menu')]/a", 0);
		// Y en ese menú la opción de listar las ofertas compradas
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listpurchases')]", 0);

		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tbody/tr");
		Assert.assertTrue(elementos.size() == 2);
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'Paraguas negro')]");
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'Nokia 3000')]");


	}

	@Test
	public void PR27() {
		// Página principal
		PO_View.getP();
		PO_View.checkKey(driver, "language.change", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "signup.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "login.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "welcome.message", PO_Properties.getSPANISH());

		PO_HomeView.changeIdiom(driver, "btnEnglish");

		PO_View.getP();
		PO_View.checkKey(driver, "language.change", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "signup.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "login.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "welcome.message", PO_Properties.getENGLISH());

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		PO_View.checkKey(driver, "logout.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "offersmanager.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "purchasesmenu.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "destacadas.offers", PO_Properties.getENGLISH());

		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_View.checkKey(driver, "addoffer.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "seeownoffer.message", PO_Properties.getENGLISH());

		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'purchases-menu')]/a", 0);
		PO_View.checkKey(driver, "purchasesearch.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "purchasedoffers.message", PO_Properties.getENGLISH());

		PO_HomeView.changeIdiom(driver, "btnSpanish");

		PO_View.checkKey(driver, "logout.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "offersmanager.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "purchasesmenu.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "destacadas.offers", PO_Properties.getSPANISH());

		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_View.checkKey(driver, "addoffer.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "seeownoffer.message", PO_Properties.getSPANISH());

		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'purchases-menu')]/a", 0);
		PO_View.checkKey(driver, "purchasesearch.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "purchasedoffers.message", PO_Properties.getSPANISH());

		// Agregar oferta

		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/add')]", 0);

		PO_View.checkKey(driver, "title.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "description.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "cost.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "date.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "destacada.message", PO_Properties.getSPANISH());

		PO_HomeView.changeIdiom(driver, "btnEnglish");

		PO_View.checkKey(driver, "title.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "description.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "cost.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "date.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "destacada.message", PO_Properties.getENGLISH());

		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		// Listado de usuarios
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "admin@email.com");

		PO_HomeView.clickOptionById(driver, "userdropdown", "id", "seeusers");

		PO_HomeView.clickOptionById(driver, "seeusers", "id", "tableUsers");

		PO_HomeView.changeIdiom(driver, "btnEnglish");

		PO_View.checkKey(driver, "currentusers.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "email.word", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "surname.message", PO_Properties.getENGLISH());
		PO_View.checkKey(driver, "users.message", PO_Properties.getENGLISH());

		PO_HomeView.changeIdiom(driver, "btnSpanish");

		PO_View.checkKey(driver, "currentusers.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "email.word", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "name.message", PO_Properties.getSPANISH());
		PO_View.checkKey(driver, "surname.message", PO_Properties.getSPANISH());

	}

	@Test
	public void PR28() {
		driver.navigate().to(URL + "/user/list");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "loginbtn", PO_View.getTimeout());
	}

	@Test
	public void PR29() {
		driver.navigate().to(URL + "/offer/listown");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "loginbtn", PO_View.getTimeout());
	}

	@Test
	public void PR30() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");

		driver.navigate().to(URL + "/user/list");
		PO_View.checkKey(driver, "forbidden.message", PO_Properties.getSPANISH());
	}

	@Test
	public void PR31() {

		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		// Seleccionamos el menú del nav para comprar
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'purchases-menu')]/a", 0);
		// Y en ese menú la opción de buscar ofertas
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listothers')]", 0);

		// Escribimos el primer mensaje a una oferta
		PO_PrivateView.fillSearchOffer(driver, "espada");
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tbody/tr/td/div/div/a");
		elementos.get(0).click();
		PO_PrivateView.fillNewComment(driver, "Hola, bonita espada");

		// Y comrprobamos que está en la lista de mensajes
		PO_View.checkElement(driver, "text", "bonita espada");

	}

	@Test
	public void PR32() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		// Seleccionamos el menú del nav para ir al listado de chats
		PO_PrivateView.getElementsAndClick(driver, "//li/a[contains(@href, '/chat/list')]", 0);

		// Hacemos click en una conversación del listado
		PO_PrivateView.getElementsAndClick(driver, "//tbody/tr/td/a", 0);

		// Comprobamos que no existe el mensaje que vamos a añdir
		SeleniumUtils.EsperaCargaPagina(driver, "text", "No, lo siento", PO_View.getTimeout());
		SeleniumUtils.textoNoPresentePagina(driver, "Ya no te interesa?");

		// Y añadimos el nuevo comentario y lo comprobamos
		PO_PrivateView.fillNewComment(driver, "Ya no te interesa?");

		// Y comrprobamos que está en la lista de mensajes
		PO_View.checkElement(driver, "text", "Ya no te interesa?");

	}

	@Test
	public void PR33() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		// Seleccionamos el menú del nav para ir al listado de chats
		PO_PrivateView.getElementsAndClick(driver, "//li/a[contains(@href, '/chat/list')]", 0);
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tbody/tr");

		// Comprobamos que hay el número de chats adecuados
		Assert.assertTrue(elementos.size() == 1);

		// Y ques esos elementos son los adecuados
		PO_View.checkElement(driver, "text", "Clásico indiscutible de Dante Alighieri");

	}

	@Test
	public void PR36() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/add')]", 0);

		PO_View.checkElement(driver, "text", "75.7");

		PO_PrivateView.fillFormAddOffer(driver, "SR2 Normandy", "Modelo a escala de una nave mítica.", "134",
				"2018-11-05", true);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "SR2 Normandy", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", "55.7", PO_View.getTimeout());

		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "helsreach@yahoo.es", "123456");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "helsreach@yahoo.es", PO_View.getTimeout());

		PO_View.checkKey(driver, "destacadas.offers", PO_Properties.getSPANISH());

		PO_View.checkElement(driver, "text", "SR2 Normandy");
	}

	@Test
	public void PR37() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");

		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listown')]", 0);

		PO_View.checkElement(driver, "text", "75.7");

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'La Divina Comedia')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		SeleniumUtils.EsperaCargaPagina(driver, "text", "55.7", PO_View.getTimeout());

		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "helsreach@yahoo.es", "123456");
		SeleniumUtils.EsperaCargaPagina(driver, "text", "helsreach@yahoo.es", PO_View.getTimeout());

		PO_View.checkKey(driver, "destacadas.offers", PO_Properties.getSPANISH());

		SeleniumUtils.EsperaCargaPagina(driver, "text", "La Divina Comedia", PO_View.getTimeout());

		PO_View.checkElement(driver, "text", "La Divina Comedia");
	}

	@Test
	public void PR38() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listown')]", 0);

		SeleniumUtils.EsperaCargaPagina(driver, "text", "75.7", PO_View.getTimeout());

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'La Divina Comedia')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		SeleniumUtils.EsperaCargaPagina(driver, "text", "55.7", PO_View.getTimeout());

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'TES III: Morrowind')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		SeleniumUtils.EsperaCargaPagina(driver, "text", "35.7", PO_View.getTimeout());

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'Guitarra española')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		SeleniumUtils.EsperaCargaPagina(driver, "text", "15.7", PO_View.getTimeout());

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'TES III: Morrowind')]/following-sibling::*/div/div/button[contains(@id, 'normalButton')]",
				0);
//		PO_PrivateView.getElementsAndClick(driver,
//				"//td[contains(text(), 'La Divina Comedia')]/following-sibling::*/div/div/button[contains(@id, 'normalButton')]",
//				0);
		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'Guitarra española')]/following-sibling::*/div/div/button[contains(@id, 'normalButton')]",
				0);

//		PO_PrivateView.getElementsAndClick(driver,
//				"//td[contains(text(), 'La Divina Comedia')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
//				0);
//
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "20.0", PO_View.getTimeout());
//
//		PO_PrivateView.getElementsAndClick(driver,
//				"//td[contains(text(), 'Guitarra española')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
//				0);
//
//		SeleniumUtils.EsperaCargaPagina(driver, "text", "0.0", PO_View.getTimeout());

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'TES III: Morrowind')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		PO_View.getP();
		PO_View.checkKey(driver, "Error.saldo", PO_Properties.getSPANISH());

		PO_View.checkElement(driver, "text", "15.7");

	}

}
