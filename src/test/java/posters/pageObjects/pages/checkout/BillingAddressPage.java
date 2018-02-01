/**
 * 
 */
package posters.pageObjects.pages.checkout;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

/**
 * @author pfotenhauer
 */
public class BillingAddressPage extends AbstractCheckoutPage
{
    private SelenideElement headline = $("#titleBillAddr");

    @Override
    public void isExpectedPage()
    {
        headline.should(exist);
    }

    @Override
    public void validateStructure()
    {
        super.validateStructure();

        // Headline
        // Assert the headline is there and starts with a capital letter
        headline.should(matchText("[A-Z].{3,}"));
        // First address
        // Makes sure at least one address is visible
        $("#billAddr0").shouldBe(visible);
    }

    /**
     * @param position
     *            The position of the billing address you want to choose
     * @return PPayment
     */
    public PaymentPage selectBillingAddress(int position)
    {
        final int index = position - 1;
        // Select address
        // Checks the radio button belonging to the delivery address with index @{index}
        $("#billAddr" + index + " input").scrollTo().click();
        // Open the billing address page in the checkout process
        // Clicks the continue button
        $("#btnUseBillAddress").scrollTo().click();

        return new PaymentPage();
    }
}
