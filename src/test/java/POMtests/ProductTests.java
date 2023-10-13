package POMtests;

import base.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;

public class ProductTests extends TestUtil {

    @Test
    public void successfulAddingOfAItem(){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");
        productPage.addItemToTheCart("backpack");

        Assert.assertEquals(productPage.getItemsInTheCart(), 1, "Because we`ve just added an item");

        //productPage.removeItemFromTheCart("backpack");
        //Assert.assertEquals(productPage.getItemsInTheCart(), 0, "Because we`ve just removed an item");
    }
}
