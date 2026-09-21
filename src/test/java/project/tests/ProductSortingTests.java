package project.tests;

import java.util.ArrayList;

import static dorusqa.z.Z.*;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import project.base.BaseTest;
import project.testdata.expected.InventoryExpected;

public class ProductSortingTests extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUpProductSorting()
    {
        // Log in and verify the inventory page is loaded
        loginPage.authentication(standardUser);
        Assert.assertEquals(InventoryExpected.URL, inventoryPage.getUrl());
        Assert.assertTrue(inventoryPage.isInventoryPageLoaded());
    }

    @Test(groups = {"Regression", "Product Catalog"}, description = TC_PS_001)
    public void sorProductsByNameInAscendingOrder() {

        // Sort products by name in ascending order
        inventoryPage.sortProductsBy(InventoryExpected.SORTING_ASCENDING_BY_NAME);
        
        // Create the expected order from the actual product list
        List<String> actualProducts = inventoryPage.getAllProductNames();
        List<String> expectedProducts = new ArrayList<>(actualProducts);
        Collections.sort(expectedProducts);
        
        // Verify products are sorted by name in ascending order
        softAssertion.assertEquals(expectedProducts, actualProducts);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Product Catalog"}, description = TC_PS_002)
    public void sorProductsByNameInDescendingOrder() {

        // Sort products by name in descending order
        inventoryPage.sortProductsBy(InventoryExpected.SORTING_DESCENDING_BY_NAME);
        
        // Create the expected descending order from the actual product list
        List<String> actualProducts = inventoryPage.getAllProductNames();
        List<String> expectedProducts = new ArrayList<>(actualProducts);
        Collections.sort(expectedProducts);
        Collections.reverse(expectedProducts);
        
        // Verify products are sorted by name in descending order
        softAssertion.assertEquals(expectedProducts, actualProducts);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Product Catalog"}, description = TC_PS_003)
    public void sorProductsByPriceInAscendingOrder() {

        // Sort products by price in ascending order
        inventoryPage.sortProductsBy(InventoryExpected.SORTING_ASCENDING_BY_PRICE);
        
        // Create the expected ascending order from the actual price list
        List<Double> actualPrices = inventoryPage.getAllProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        // Verify products are sorted by price in ascending order
        softAssertion.assertEquals(expectedPrices, actualPrices);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Product Catalog"}, description = TC_PS_004)
    public void sorProductsByPriceInDescendingOrder() {

        // Sort products by price in descending order
        inventoryPage.sortProductsBy(InventoryExpected.SORTING_DESCENDING_BY_PRICE);
        
        // Create the expected descending order from the actual price list
        List<Double> actualPrices = inventoryPage.getAllProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);
        Collections.reverse(expectedPrices);
        
        // Verify products are sorted by price in descending order
        softAssertion.assertEquals(expectedPrices, actualPrices);
        softAssertion.assertAll();
    }
}

