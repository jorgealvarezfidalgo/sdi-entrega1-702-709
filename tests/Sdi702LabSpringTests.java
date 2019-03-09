package com.uniovi.tests;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Sdi702LabSpringTests {

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
		driver.navigate().to(URL);
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

	// PR01. Acceder a la página principal /
	@Test
	public void PR01() {
		PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
	}

	// PR02. OPción de navegación. Pinchar en el enlace Registro en la página home
	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
	}

	// PR03. OPción de navegación. Pinchar en el enlace Identificate en la página
	// home
	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
	}

	// PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta a
	// Español
	@Test
	public void PR04() {
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(),
				PO_Properties.getENGLISH());
		// SeleniumUtils.esperarSegundos(driver, 2);
	}

	// PR05. Prueba del formulario de registro. registro con datos correctos
	@Test
	public void PR05() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "77777778A", "Josefo", "Perez", "77777", "77777");
		// Comprobamos que entramos en la sección privada
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	// PR06. Prueba del formulario de registro. DNI repetido en la BD, Nombre corto,
	// .... pagination pagination-­‐centered, Error.signup.dni.length
	@Test
	public void PR06() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "99999990A", "Josefo", "Perez", "77777", "77777");
		PO_View.getP(); // COmprobamos el error de DNI repetido.
		PO_RegisterView.checkKey(driver, "Error.signup.dni.duplicate", PO_Properties.getSPANISH());
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "99999990B", "Jose", "Perez", "77777", "77777"); // COmprobamos el error de
																							// Nombre corto .
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.name.length", PO_Properties.getSPANISH());
		PO_RegisterView.fillForm(driver, "99", "Josefo", "Perez", "77777", "77777");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.dni.length", PO_Properties.getSPANISH());
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "P", "77777", "77777");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.lastName.length", PO_Properties.getSPANISH());
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Perez", "7", "7");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.password.length", PO_Properties.getSPANISH());
		PO_RegisterView.fillForm(driver, "99999990B", "Josefo", "Perez", "77777", "22222");
		PO_View.getP();
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}

	// PRN. Loguearse con exito desde el ROl de Usuario, 99999990D, 123456
	@Test
	public void PR07() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
	}

	// PRN. Loguearse con exito desde el ROl de Profesor
	@Test
	public void PR08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999993D", "123456");
		// COmprobamos que entramos en la pagina privada de Profesor
		PO_HomeView.clickOptionById(driver, "markdropdown", "id", "addmark");
	}

	// PRN. Loguearse con exito desde el ROl de Admin
	@Test
	public void PR09() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999988F", "123456");
		// COmprobamos que entramos en la pagina privada de Admin
		PO_HomeView.clickOptionById(driver, "userdropdown", "id", "adduser");
	}
	
	@Test
	public void PR10() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123457");
		//Misma página
		PO_View.checkElement(driver, "class", "btn btn-primary");
	}
	
	@Test
	public void PR11() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "99999990A", "123456");
		// COmprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Notas del usuario");
		
		PO_HomeView.clickOption(driver, "logout", "id", "login");
	}
	
	@Test  public void PR12() {    
		//Vamos al formulario de logueo.    
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); 
		//Rellenamos el formulario    
		PO_LoginView.fillForm(driver, "99999990A" , "123456" );    
		//COmprobamos que entramos en la pagina privada de Alumno    
		PO_View.checkElement(driver, "text", "Notas del usuario");    
		//Contamos el número de filas de notas    
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free",  "//tbody/tr", 
				PO_View.getTimeout());    
		Assert.assertTrue(elementos.size() == 4);    
		//Ahora nos desconectamos    
		 PO_PrivateView.clickOption(driver, "logout", "id", "login");
	}
	
	//PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion = Nota A2.  
	//P13. Ver la lista de Notas.   
	@Test   
	public void PR13() {    
		//Vamos al formulario de logueo.    
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");   
		//Rellenamos el formulario    
		PO_LoginView.fillForm(driver, "99999990A" , "123456" );    
		//COmprobamos que entramos en la pagina privada de Alumno    
		PO_View.checkElement(driver, "text", "Notas del usuario");    
		SeleniumUtils.esperarSegundos(driver, 1);    //Contamos las notas    
		By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/following-sibling::*[2]");    
		driver.findElement(enlace).click();    
		SeleniumUtils.esperarSegundos(driver, 1);    
		//Esperamos por la ventana de detalle    
		PO_View.checkElement(driver, "text", "Detalles de la nota");     
		SeleniumUtils.esperarSegundos(driver, 1);    
		//Ahora nos desconectamos 
		 PO_PrivateView.clickOption(driver, "logout", "id", "login");
	}
	
	//P14. Loguearse como profesor y Agregar Nota A2.   
	//P14. Esta prueba podría encapsularse mejor ...    
	@Test   
	public void PR14() {    
		//Vamos al formulario de logueo.   
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");  
		//Rellenamos el formulario   
		PO_LoginView.fillForm(driver, "99999993D" , "123456" );   
		//COmprobamos que entramos en la pagina privada del Profesor   
		PO_View.checkElement(driver, "text", "99999993D");   
		//Pinchamos en la opción de menu de Notas: 
		//li[contains(@id, 'marks-menu')]/a  
		PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'marks-menu')]/a", 0);
		PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'mark/add')]", 0);
		//Ahora vamos a rellenar la nota. //option[contains(@value, '4')]   
		PO_PrivateView.fillFormAddMark(driver, 3, "Nota Nueva 1", "8");   
		//Esperamos a que se muestren los enlaces de paginación la lista de notas   
		PO_PrivateView.getElementsAndClick(driver,"//a[contains(@class, 'page-link')]", 3);
		//Comprobamos que aparece la nota en la pagina   
		PO_View.checkElement(driver, "text", "Nota Nueva 1");   
		//Ahora nos desconectamos   
		 PO_PrivateView.clickOption(driver, "logout", "id", "login");
	}
	
	//PRN. Loguearse como profesor, vamos a la ultima página y Eliminamos la Nota Nueva 1.   
	//PRN. Ver la lista de Notas.   
	@Test 
	 public void PR15() {     
		//Vamos al formulario de logueo.    
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");   
		//Rellenamos el formulario    
		PO_LoginView.fillForm(driver, "99999993D" , "123456" );    
		//COmprobamos que entramos en la pagina privada del Profesor    
		PO_View.checkElement(driver, "text", "99999993D");    
		//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a   
		PO_PrivateView.getElementsAndClick(driver,"//li[contains(@id, 'marks-menu')]/a", 0);
		//Pinchamos en la opción de lista de notas.    
		PO_PrivateView.getElementsAndClick(driver,"//a[contains(@href,  'mark/list')]", 0);  
		//Esperamos a que se muestren los enlaces de paginacion la lista de notas   
		PO_PrivateView.getElementsAndClick(driver,"//a[contains(@class, 'page-link')]", 3);  
		//Esperamos a que aparezca la Nueva nota en la ultima pagina     
		//Y Pinchamos en el enlace de borrado de la Nota "Nota Nueva 1"    
		//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(text(),  'mark/delete')]"    
		PO_PrivateView.getElementsAndClick(driver,"//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(@href, 'mark/delete')]", 0);    
		PO_PrivateView.getElementsAndClick(driver,"//a[contains(@class, 'page-link')]", 3);     
		SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Nota Nueva 1",PO_View.getTimeout() );    
		//Ahora nos desconectamos    
		 PO_PrivateView.clickOption(driver, "logout", "id", "login"); 
	}
	
	//PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion = Nota A2.  
		//P13. Ver la lista de Notas.   
		@Test   
		public void PR16() {    
			//Vamos al formulario de logueo.    
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");   
			//Rellenamos el formulario    
			PO_LoginView.fillForm(driver, "99999988F" , "123456" );    
			//COmprobamos que entramos en la pagina privada de Alumno    
			PO_View.checkElement(driver, "text", "99999988F");  
			PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'users-menu')]/a", 0);
			PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'user/list')]", 0);
			PO_PrivateView.getElementsAndClick(driver,"//td[contains(text(), 'Pedro')]/following-sibling::*/a[contains(@href, 'user/details')]", 0);    
			PO_View.checkElement(driver, "text", "Díaz");    
			//Ahora nos desconectamos 
			 PO_PrivateView.clickOption(driver, "logout", "id", "login");
		}
		
		//P14. Loguearse como profesor y Agregar Nota A2.   
		//P14. Esta prueba podría encapsularse mejor ...    
		@Test   
		public void PR17() {    
			//Vamos al formulario de logueo.   
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");  
			//Rellenamos el formulario   
			PO_LoginView.fillForm(driver, "99999988F" , "123456" );   
			//COmprobamos que entramos en la pagina privada del Profesor   
			PO_View.checkElement(driver, "text", "99999988F");   
			//Pinchamos en la opción de menu de Notas: 
			//li[contains(@id, 'marks-menu')]/a  
			PO_PrivateView.getElementsAndClick(driver, "//li[contains(@id,  'users-menu')]/a", 0);
			PO_PrivateView.getElementsAndClick(driver, "//a[contains(@href, 'user/add')]", 0);
			//Ahora vamos a rellenar la nota. //option[contains(@value, '4')]   
			PO_PrivateView.fillFormAddUser(driver, 0, "14531939E", "Malcador", "Sigilita", "77777", "77777");   
			//Esperamos a que se muestren los enlaces de paginación la lista de notas   
			PO_PrivateView.getElementsAndClick(driver,"//a[contains(@class, 'page-link')]", 2);
			//Comprobamos que aparece la nota en la pagina   
			PO_View.checkElement(driver, "text", "Malcador");   
			//Ahora nos desconectamos   
			 PO_PrivateView.clickOption(driver, "logout", "id", "login");
		}
		
		//PRN. Loguearse como profesor, vamos a la ultima página y Eliminamos la Nota Nueva 1.   
		//PRN. Ver la lista de Notas.   
		@Test 
		 public void PR18() {     
			//Vamos al formulario de logueo.    
			PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");   
			//Rellenamos el formulario    
			PO_LoginView.fillForm(driver, "99999988F" , "123456" );      
			PO_View.checkElement(driver, "text", "99999988F");    
			PO_PrivateView.getElementsAndClick(driver,"//li[contains(@id, 'users-menu')]/a", 0);
			PO_PrivateView.getElementsAndClick(driver,"//a[contains(@href,  'user/list')]", 0);  
			PO_PrivateView.getElementsAndClick(driver,"//a[contains(@class, 'page-link')]", 2);   
			PO_PrivateView.getElementsAndClick(driver,"//td[contains(text(), 'Malcador')]/following-sibling::*/a[contains(@href, 'user/delete')]", 0);    
			PO_PrivateView.getElementsAndClick(driver,"//a[contains(@class, 'page-link')]", 2);     
			SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Malcador",PO_View.getTimeout() );    
			//Ahora nos desconectamos    
			 PO_PrivateView.clickOption(driver, "logout", "id", "login"); 
		}

}
