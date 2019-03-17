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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Sleeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Offer;
import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;
import com.uniovi.services.InsertSampleDataService;
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
	@Autowired
	private InsertSampleDataService insertDataService;

	// En Windows (Debe ser la versión 65.0.1 y desactivar las
	// actualizacioens automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\linom\\Google Drive\\Informática\\Sistemas Distribuidos e Internet (SDI)\\5. Web testing con Selenium\\Material-Sesión5\\geckodriver024win64.exe";

	// //Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8080";

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
		insertDataService.init();

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
		driver.navigate().to("http://localhost:8080/user/list");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "loginbtn", PO_View.getTimeout());
	}

	@Test
	public void PR29() {
		driver.navigate().to("http://localhost:8080/offer/listown");
		SeleniumUtils.EsperaCargaPagina(driver, "id", "loginbtn", PO_View.getTimeout());
	}

	@Test
	public void PR30() {

		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "cruzadaeterna@gmail.com", "123456");

		driver.navigate().to("http://localhost:8080/user/list");
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

		PO_View.checkElement(driver, "text", "100.0");

		PO_PrivateView.fillFormAddOffer(driver, "SR2 Normandy", "Modelo a escala de una nave mítica.", "134",
				"2018-11-05", true);
		SeleniumUtils.EsperaCargaPagina(driver, "text", "SR2 Normandy", PO_View.getTimeout());
		SeleniumUtils.EsperaCargaPagina(driver, "text", "80.0", PO_View.getTimeout());

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

		PO_View.checkElement(driver, "text", "100.0");

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'La Divina Comedia')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		SeleniumUtils.EsperaCargaPagina(driver, "text", "80.0", PO_View.getTimeout());

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

		SeleniumUtils.EsperaCargaPagina(driver, "text", "100.0", PO_View.getTimeout());

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'La Divina Comedia')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		SeleniumUtils.EsperaCargaPagina(driver, "text", "80.0", PO_View.getTimeout());

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'TES III: Morrowind')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		SeleniumUtils.EsperaCargaPagina(driver, "text", "60.0", PO_View.getTimeout());

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'Guitarra española')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		SeleniumUtils.EsperaCargaPagina(driver, "text", "40.0", PO_View.getTimeout());

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'TES III: Morrowind')]/following-sibling::*/div/div/button[contains(@id, 'normalButton')]",
				0);
		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'La Divina Comedia')]/following-sibling::*/div/div/button[contains(@id, 'normalButton')]",
				0);
		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'Guitarra española')]/following-sibling::*/div/div/button[contains(@id, 'normalButton')]",
				0);

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'La Divina Comedia')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		SeleniumUtils.EsperaCargaPagina(driver, "text", "20.0", PO_View.getTimeout());

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'Guitarra española')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		SeleniumUtils.EsperaCargaPagina(driver, "text", "0.0", PO_View.getTimeout());

		PO_PrivateView.getElementsAndClick(driver,
				"//td[contains(text(), 'TES III: Morrowind')]/following-sibling::*/div/div/button[contains(@id, 'highlightButton')]",
				0);

		PO_View.getP();
		PO_View.checkKey(driver, "Error.saldo", PO_Properties.getSPANISH());

		PO_View.checkElement(driver, "text", "0.0");

	}

}
