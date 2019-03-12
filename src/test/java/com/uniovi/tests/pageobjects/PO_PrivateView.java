package com.uniovi.tests.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_PrivateView extends PO_NavView {

	static public void fillFormAddMark(WebDriver driver, int userOrder, String descriptionp,  String scorep)   {             
		//Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla            
		SeleniumUtils.esperarSegundos(driver, 5);            
		//Seleccionamos el alumnos userOrder      
		new Select (driver.findElement(By.id("user"))).selectByIndex(userOrder);      
		//Rellenemos el campo de descripción      
		WebElement description = driver.findElement(By.name("description"));    
		description.clear();    
		description.sendKeys(descriptionp);   
		WebElement score = driver.findElement(By.name("score"));    
		score.click();    score.clear();    score.sendKeys(scorep);    
		By boton = By.className("btn");    
		driver.findElement(boton).click();    
	}
	
	static public void fillFormAddUser(WebDriver driver, int roleOrder, String dnip,  String namep,
			String lastNamep, String passwordp, String passwordConfirmp)   {             
		//Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla            
		SeleniumUtils.esperarSegundos(driver, 5);            
		//Seleccionamos el alumnos userOrder      
		new Select (driver.findElement(By.id("role"))).selectByIndex(roleOrder);      
		//Rellenemos el campo de descripción      
		WebElement description = driver.findElement(By.name("dni"));    
		description.clear();    
		description.sendKeys(dnip);
		WebElement name = driver.findElement(By.name("name"));    
		name.clear();    
		name.sendKeys(namep);   
		WebElement lastName = driver.findElement(By.name("lastName"));    
		lastName.clear();    
		lastName.sendKeys(lastNamep);   
		WebElement password = driver.findElement(By.name("password"));    
		password.clear();    
		password.sendKeys(passwordp);   
		WebElement passwordConfirm = driver.findElement(By.name("passwordConfirm"));    
		passwordConfirm.clear();    
		passwordConfirm.sendKeys(passwordConfirmp);  
		By boton = By.className("btn");    
		driver.findElement(boton).click();    
	}
	
	static public void getElementsAndClick(WebDriver driver, String path, int element) {
		List<WebElement>  elementos = PO_View.checkElement(driver, "free", path); 
		elementos.get(element).click();
	}
}