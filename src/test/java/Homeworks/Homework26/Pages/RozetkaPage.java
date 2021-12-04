package Homeworks.Homework26.Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.*;

public class RozetkaPage {

    private final SelenideElement searchField = $("input[name='search']");
    private final ElementsCollection lenovoProducts = $$("div.goods-tile__inner");
    private final String lenovoItemPrice = "span[class='goods-tile__price-value']";
    private final String lenovoItemTitle = "span.goods-tile__title";


    public RozetkaPage performSearch(String searchItem) {
        searchField.val(searchItem).sendKeys(Keys.ENTER);
        sleep(2000);
        return this;
    }

    public RozetkaPage open() {
        Selenide.open("/");
        return this;
    }

    public ElementsCollection lenovoProductOnPage() {
        return lenovoProducts;
    }

    private int getItemPrice(String price) {
        return Integer.parseInt(price.replaceAll(" ", ""));
    }

    public RozetkaPage getLenovoProductsPrices(){
        List<Integer> lenovoProductsPrices = new ArrayList<>();
        for (WebElement prices : lenovoProducts) {
            WebElement price = prices.findElement(By.cssSelector("span.goods-tile__price-value"));
            int p = Integer.parseInt(price.getText().replaceAll(" ", ""));
            lenovoProductsPrices.add(p);
        }
        System.out.println(lenovoProductsPrices);
        return this;
    }

    public RozetkaPage getAllLenovoPrices() {
        List<String> prices = lenovoProductOnPage().stream()
                .map(p -> p.$(lenovoItemPrice).getText().replaceAll(" ", ""))
                .collect(Collectors.toList());
        System.out.println("Prices: " + prices);
        return this;
    }

    public RozetkaPage getAllLenovoTitles() {
        List<String> titles = lenovoProductOnPage().stream()
                .map(t -> t.$(lenovoItemTitle).text())
                .collect(Collectors.toList());
        System.out.println("Titles: " + titles);
        return this;
    }

    public RozetkaPage getAllItemsWithPriceAbove(int price) {
        List<Integer> pricesAbove = lenovoProductOnPage().stream()
                .map(p -> p.$(lenovoItemPrice).text())
                .map(this::getItemPrice)
                .filter(f -> f >= price)
                .collect(Collectors.toList());
        System.out.println("Product with price above " + price + ": " + pricesAbove);
        return this;
    }

    public RozetkaPage qtyProductsWithPriceAbove(int price) {
        List<Long> countProducts = Collections.singletonList(lenovoProductOnPage().stream()
                .map(p -> p.$(lenovoItemPrice).text())
                .map(this::getItemPrice)
                .filter(f -> f <= price)
                .count());
        System.out.println(countProducts + " products with price lower than " + price);
        return this;
    }

    public RozetkaPage findItemWithMaxPrice() {
        List<Integer> maxPrice = lenovoProductOnPage().stream()
                .map(p -> p.$(lenovoItemPrice).text())
                .map(this::getItemPrice)
                .collect(Collectors.toList());
        int value = maxPrice.stream()
                .max(Integer::compareTo)
                .get();
        System.out.println("Max price of product on page: " + value);
        return this;
    }

    public RozetkaPage findProductsPricesByPartialTitleText(String text) {
        List<String> prices = lenovoProductOnPage().stream()
                .filter(t -> t.$(lenovoItemTitle).text().contains(text))
                .map(p -> p.$(lenovoItemPrice).text())
                .collect(Collectors.toList());
        System.out.println("Prices of product which contains " + text + ": " + prices);
        return this;
    }
}
