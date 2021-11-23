package Homeworks.Homework25.Fragments;

import Homeworks.Homework25.Pages.CitrusMainPage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Selenide.$;

public class CitrusAddedProductModal extends CitrusMainPage {

    private final SelenideElement addedProductModal = $("div[class='el-dialog__body'");
    private final SelenideElement closeAddedProductModal = $("i[class^='el-dialog__close']");

    public CitrusAddedProductModal closeAddedProductModal() {
        closeAddedProductModal.click();
        addedProductModal.should(disappear);
        return this;
    }
}
