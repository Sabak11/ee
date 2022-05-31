package AutoM;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class RegistrationPage {



    @Test
    public void regPage() {

        openRegPage(); // გადასვლა რეგისტრაციის გვერდზე

        // რეგისტრაციის გვერდი - გადამოწმება
        Assert.assertEquals("სწრაფი რეგისტრაცია", $(".registration-titles").getText());


        // სახელის შევსება, გადამომწება

        $("#firstName").click();
        $("#firstName").setValue(name);
        Assert.assertEquals(name, $("#firstName").getValue());
        //Assert.assertFalse("ველი შევსებულია გადამოწმება", $("#firstName").is(Condition.empty));


        // გვარის შევსება, გადამომწება

        $("#lastName").click();
        $("#lastName").setValue(fakelastName);
        Assert.assertFalse("ველი შევსებულია გადამოწმება", $("#lastName").is(Condition.empty));

        $("#email").click(); // იმეილის  შევსება, გადამომწება
        $("#email").setValue(email);
        Assert.assertFalse($("#email").is(Condition.empty));


        // პაროლის შევსება, გადამომწება

        $("#password").click();
        $("#password").setValue(pass);
        Assert.assertEquals(pass, $("#password").getValue());

        // პაროლის გამეორება, გადამომწება

        $("#confirmPassword").click();
        $("#confirmPassword").setValue(pass);
        Assert.assertEquals(pass, $("#confirmPassword").getValue());


        // რეგისტრაცია ღილაკი, enabled

        Assert.assertTrue( $("#singup").is(Condition.enabled));
        //Assert.assertEquals("რეგისტრაცია", $("#singup").getText());

        sleep(5000);

    }



        //Negative Test case

    @Test
    public void regPageNegative(){

        openRegPage(); // გადასვლა რეგისტრაციის გვერდზე

        // რეგისტრაციის გვერდი - გადამოწმება
        Assert.assertEquals("სწრაფი რეგისტრაცია", $(".registration-titles").getText());

        //რეგისტრაცია ღილაკი არის Disabled

        Assert.assertTrue( $("#singup").is(Condition.disabled));

        //სახელის ველის მანდატორობა

        $("#firstName").click();
        $("#lastName").click();
        Assert.assertEquals("სახელი სავალდებულოა", $(".text-danger").getText());


        //გვარის ველის მანდატორობა

        $("#lastName").click();
        $("#email").click();
        //$(byText("გვარი სავალდებულოა")).shouldHave(Condition.visible);
        Assert.assertEquals("გვარი სავალდებულოა", $(".text-danger",1).getText());

        //Email ველის მანდატორობა

        $("#email").click();
        $("#password").click();
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());

        //პაროლის ველის მანდატორობა

        $("#password").click();
        $("#confirmPassword").click();
        Assert.assertEquals("პაროლი სავალდებულოა.", $(".text-danger",3).getText());

        //განმეორებითი პაროლის ველის მანდატორობა

        $("#confirmPassword").click();
        $("#password").click();
        Assert.assertEquals("პაროლის გამეორება სავალდებულოა", $(".text-danger",4).getText());


        //Email არასწორი ფორმატი
        // 1
        $("#email").setValue("test");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        $("#email").sendKeys(Keys.CONTROL,"a",Keys.DELETE);
        sleep(2000);

        // 2
        $("#email").setValue("test@");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        $("#email").sendKeys(Keys.CONTROL,"a",Keys.DELETE);

        // 3
        $("#email").setValue("test@gmail");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        $("#email").sendKeys(Keys.CONTROL,"a",Keys.DELETE);

        // 4
        $("#email").setValue("test@gmail.");
        Assert.assertEquals("ელ. ფოსტა სავალდებულოა", $(".text-danger",2).getText());
        

        // პაროლის ველი 6 სიმბოლოზე ნაკლები

        $("#password").click();
        $("#password").setValue("1235");
        //Assert.assertEquals("პაროლი სავალდებულოა.", $(".text-danger",2).getText());
        Assert.assertTrue( $(byText("პაროლი სავალდებულოა.")).isDisplayed());


        // პაროლის ველიში განსხვავებული მნიშნოლობები

        $("#password").setValue("31125115");

        $("#confirmPassword").setValue("311251156665");
        Assert.assertTrue( $(byText("პაროლის გამეორება სავალდებულოა")).isDisplayed());
        sleep(5000);

       /*if ($("#password").getValue() == $("#confirmPassword").getValue()) {
       }*/


    }




        //Add card is empty

    /*@Test
            public void addCardEmpty() {

        Selenide.open("https://ee.ge");
        $("#rcc-confirm-button").click();
        $(".cart-count").click();
        $(byText("კალათა ცარიელია")).shouldHave(Condition.visible);
        $("#search_list").click();
        $("#search_list").setValue("მობილური").pressEnter();
        $(".add_to_cart", 0).click();
        $(".cart-count").click();
        $(byText("კალათა")).shouldHave(Condition.visible);
        $(By.cssSelector("span.fa-trash")).click();
        $(byText("კალათა ცარიელია")).shouldHave(Condition.visible);

        sleep(5000);

    }*/



        //Method 4

       /* @Test
        public void empty (){

            Selenide.open("https://ee.ge");
            $("#rcc-confirm-button").click();
            $(".cart-count").click();
            $(byText("კალათა ცარიელია")).shouldHave(Condition.visible);
            $(".fa-heart-o",0).click();
            $(".h1_css").shouldHave(Condition.visible);
            $("#search_list").click();
            $("#search_list").setValue("კომპიუტერი").pressEnter();
            $(".add_to_cart", 0).click();
            $(".cart-count").click();
            $(By.cssSelector("span.save-icon")).click();
            $(".fa-heart-o",0).click();
            $(".add_to_cart").shouldHave(Condition.visible, Duration.ofMillis(5000));
            $(by ("src", "/images/mainlogo.png" )).click();
            $(".cart-count").click();
            $(byText("კალათა ცარიელია")).shouldHave(Condition.visible);
            $(".fa-heart-o",0).click();
            $(".fa-times",0).click();
            $(byText("ვერ მოიძებნა")).shouldHave(Condition.visible);

            sleep(5000);



    }*/


    Faker faker = new Faker();
    String name = faker.name().firstName();


    Faker fakeLastName = new Faker();
    String fakelastName = faker.name().lastName();


    Faker fakeEmail = new Faker();
    String email = faker.internet().emailAddress();

    Faker fakePass = new Faker();
    String pass = faker.internet().password();



    public void openRegPage(){
        Selenide.open("https://ee.ge");
        $(byText("რეგისტრაცია")).click();

    }


}

