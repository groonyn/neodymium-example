package com.xceptance.neodymium.scripting.template.selenide.page;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PHome extends BasicPage
{

    @Override
    public boolean isExpectedPage()
    {
        return $("#titleIndex").exists();
    }

    @Override
    public void validateStructure()
    {
        // Verifies the company Logo and name are visible.
        $("a#brand").shouldBe(visible);

        // Verifies the Navigation bar is visible
        $("#categoryMenu .nav").shouldBe(visible);
        // Asserts there's categories in the nav bar.
        $$("#categoryMenu .nav > li.dropdown.header-menu-item").shouldHave(sizeGreaterThan(0));

        // Asserts the first headline is there.
        $("#titleIndex.h2").shouldBe(matchText("[A-Z].{3,}"));
        // Asserts the animated poster rotation is there.
        $("#pShopCarousel").shouldBe(visible);

        // Verifies the "Hot products" section is there.
        $("#main .margin_top20 .h2").shouldBe(matchText("[A-Z].{3,}"));
        // Asserts there's a list of items under "Hot Products".
        $("#productList").shouldBe(visible);
        // Asserts there's at least 1 item in the list.
        $$("#productList > li").shouldHave(sizeGreaterThan(0));
    }

    public void validate()
    {
        validateStructure();
        footer().validate();
    }

    public void validateSuccessfulOrder()
    {
        // Wait until javascript makes the success message visible
        // Waits until javascript makes the success message visible.
        // Verify that the correct message is displayed
        $("#successMessage").shouldBe(visible);
        // The message displays the correct text.
        $("#successMessage").should(exactText("× Thank you for shopping with us!"));
        // Verify that the mini cart is empty again
        miniCart().validateTotalCount(0);
        miniCart().validateSubtotal("$0.00");
    }
}
