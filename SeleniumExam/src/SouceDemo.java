import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

public class SouceDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//Crear una instancia de navegador y configurar el entorno de prueba.
		System.setProperty("webdriver.edge.driver.", "/Users/juanpabloalvarezfox/Documentos/msedgedriver");
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://www.saucedemo.com/");
		
		//Abra la página de inicio de sesión y verifique el logotipo de Swag Labs.
		Assert.assertEquals(driver.findElement(By.className("login_logo")).getText(), "Swag Labs");
		
		//Inicie sesión con credenciales válidas.
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		
		//Abra el menú para acceder al botón Cerrar sesión y verificar que es visible.
		driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.id("logout_sidebar_link")).isDisplayed());
		driver.findElement(By.id("react-burger-cross-btn")).click();
		Thread.sleep(2000);
		
		//Verifique que se muestre la página de productos.
		Assert.assertEquals(driver.findElement(By.className("title")).getText(), "Products");
		
		//Navegue a la página de detalles del producto y verifique el nombre del producto.
		String productName;
		productName = driver.findElement(By.xpath("//*[@id='item_4_title_link']/div")).getText();
		driver.findElement(By.xpath("//*[@id='item_4_title_link']/div")).click();
		Assert.assertEquals(driver.findElement(By.className("inventory_details_name")).getText(), productName);
		driver.navigate().back();
		
		//Agregue 3 articulos al carrito.
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
		driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
		
		//Verifique el funcionamiento del boton "Eliminar".
		int cartValue = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
		driver.findElement(By.id("remove-sauce-labs-backpack")).click();
		cartValue = cartValue-1;
		Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), String.valueOf(cartValue));
		
		//Navegue a la página del carrito de compras y verifique los artículos en el carrito
		String product1 = driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div")).getText();
		String product2 = driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div")).getText();
		driver.findElement(By.className("shopping_cart_link")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div")).getText(), product1);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div")).getText(), product2);
		
		//Continúe con el proceso de pago y verifique la página de pago
		driver.findElement(By.id("checkout")).click();
		Assert.assertEquals(driver.findElement(By.className("title")).getText(), "Checkout: Your Information");
		
		//Ingrese la información de envío y pago requerida
		String name = "Juan", lastname = "Alvarez", zipcode = "83150";
		driver.findElement(By.id("first-name")).sendKeys(name);
		driver.findElement(By.id("last-name")).sendKeys(lastname);
		driver.findElement(By.id("postal-code")).sendKeys(zipcode);
		
		
		//Continúe con la página de confirmación y verifique los artículos en la descripción general
		driver.findElement(By.id("continue")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"item_0_title_link\"]/div")).getText(), product1);
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"item_1_title_link\"]/div")).getText(), product2);
		
		//Cerrar sesión en el sitio web.
		driver.findElement(By.id("react-burger-menu-btn")).click();
		Thread.sleep(1500);
		driver.findElement(By.id("logout_sidebar_link")).click();
		
		//Verifique el usuario: locked_out_user
		driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//h3[@data-test='error']")).getText(), "Epic sadface: Sorry, this user has been locked out.");
		
		//Cerrar la sesión (driver)
		driver.close();
		
		
		
		
		
		
		
		
		/*
		driver.findElement(By.id("inputUsername")).sendKeys("pablo");
		driver.findElement(By.name("inputPassword")).sendKeys("pablo");
		driver.findElement(By.className("signInBtn")).click();
		System.out.println(driver.findElement(By.cssSelector("p.error")).getText());
		
		driver.findElement(By.linkText("Forgot your password?")).click();
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("Pablo");
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("pablo.af@hotmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Phone Number']")).sendKeys("6221277523");
		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
		*/
	}

}
