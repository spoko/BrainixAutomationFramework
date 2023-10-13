package POMtests;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductPage;
import base.DataProviders;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoginTests extends TestUtil {

    @Test
    public void successfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(productPage.isAt());
    }

    @Test
    public void unSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_userw", "secret_sauwce");

        Assert.assertTrue(loginPage.isAt());
    }

    @Test(dataProvider = "wrongUsers")
    public void unSuccessfulLogin1(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(userName, password);

        Assert.assertTrue(loginPage.isAt());
    }

    @Test(dataProvider = "validUsersFromCsv")
    public void successfulLogin1(String userName, String password) {
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login(userName, password);

        Assert.assertTrue(productPage.isAt());
    }
}
