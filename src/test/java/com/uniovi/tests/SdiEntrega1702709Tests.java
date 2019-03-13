package com.uniovi.tests;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;
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
	private UsersRepository usersRepository;

	// En Windows (Debe ser la versión 65.0.1 y desactivar las
	// actualizacioens automáticas)):
	static String PathFirefox65 = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\woest\\OneDrive\\Documentos\\SDI\\geckodriver024win64.exe";

	// //Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

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
			user1Offers = new HashSet<Offer>() {
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
		SeleniumUtils.esperarSegundos(driver, 2);
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver,userToDelete.getEmail(), PO_View.getTimeout());

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
		SeleniumUtils.esperarSegundos(driver, 2);
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
		SeleniumUtils.esperarSegundos(driver, 1);

		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, userToDelete1.getEmail(), PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, userToDelete2.getEmail(), PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, userToDelete3.getEmail(), PO_View.getTimeout());
	}
	
	@Test   
	public void PR16() {    
		//Vamos al formulario de logueo.   
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");  
		//Rellenamos el formulario   
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com" , "123456" );  
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");   
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/add')]", 0);
		
		PO_PrivateView.fillFormAddOffer(driver, "Nanomáquinas", "Se activan en respuesta al trauma físico",
				"666", "2019-03-04");   
		SeleniumUtils.esperarSegundos(driver, 2);
		//Comprobamos que aparece la nota en la pagina   
		PO_View.checkElement(driver, "text", "Nanomáquinas");   }
	
	@Test   
	public void PR17() {    
		//Vamos al formulario de logueo.   
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");  
		//Rellenamos el formulario   
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com" , "123456" );  
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com");   
		
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/add')]", 0);

		PO_PrivateView.fillFormAddOffer(driver, "", "Se activan en respuesta al trauma físico",
				"666", "2019-03-04");   
		
		SeleniumUtils.esperarSegundos(driver, 2);
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		 
	}
	
	@Test   
	public void PR18() {    
		//Vamos al formulario de logueo.    
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");   
		//Rellenamos el formulario    
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com" , "123456" );  
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
		//Vamos al formulario de logueo.    
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");   
		//Rellenamos el formulario    
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com" , "123456" );  
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
		//Vamos al formulario de logueo.    
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");   
		//Rellenamos el formulario    
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com" , "123456" );  
		PO_View.checkElement(driver, "text", "cruzadaeterna@gmail.com"); 
		
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'offers-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'offer/listown')]", 0);
		
		List<WebElement> elementos = PO_View.checkElement(driver, "free", "//tr");
		Assert.assertTrue(elementos.size() == 4);
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'Guitarra española')]");   
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'La Divina Comedia')]"); 
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'TES III: Morrowind')]"); 
		
		PO_PrivateView.getElementsAndClick(driver,"//td[contains(text(), 'Guitarra española')]/following-sibling::*/a[contains(@href, 'offer/delete')]", 0);
		SeleniumUtils.esperarSegundos(driver, 2);
		
		elementos = PO_View.checkElement(driver, "free", "//tr");
		Assert.assertTrue(elementos.size() == 3);
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Guitarra española", PO_View.getTimeout());   
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'La Divina Comedia')]"); 
		PO_View.checkElement(driver, "free", "//td[contains(text(), 'TES III: Morrowind')]"); 
	}
	
	@Test
	public void PR27() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());
		// SeleniumUtils.esperarSegundos(driver, 2);
	}

}
