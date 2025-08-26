package qa.vikindor.tests;

import org.junit.jupiter.api.Test;
import qa.vikindor.config.TestBase;
import qa.vikindor.pages.PracticeFormPage;
import qa.vikindor.pages.components.ResultModal;

import static qa.vikindor.pages.components.ResultModal.*;
import static qa.vikindor.utils.RandomUtils.*;


public class PracticeFormTests extends TestBase {

    PracticeFormPage practiceFormPage = new PracticeFormPage();
    ResultModal resultModal = new ResultModal();

    private final String
            firstName = getRandomFirstName(),
            lastName = getRandomLastName(),
            fullName = firstName + " " + lastName,
            email = getRandomUserEmail(firstName, lastName),
            gender = getRandomGender(),
            mobile = getRandomUserNumber(),
            day = getRandomDay(),
            month = getRandomMonth(),
            year = getRandomYear(),
            birthday = day + " " + month + "," + year,
            subject = getRandomSubject(),
            hobby = getRandomHobby(),
            picture = getRandomPicture(),
            address = getRandomAddress(),
            state = getRandomState(),
            city = getRandomCity(state);

    @Test
    void shouldSubmitFormWithAllFieldsTest() {

        practiceFormPage
                .openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setUserEmail(email)
                .setGender(gender)
                .setUserNumber(mobile)
                .setBirthDate(day,month,year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture(picture)
                .setAddress(address)
                .setState(state)
                .setCity(city)
                .submit();

        resultModal.shouldAppear();
        resultModal
                .shouldHaveTitle(tableTitle)
                .shouldHaveValue(tableRowName, fullName)
                .shouldHaveValue(tableRowEmail,email)
                .shouldHaveValue(tableRowGender,gender)
                .shouldHaveValue(tableRowMobile,mobile)
                .shouldHaveValue(tableRowDateOfBirth,birthday)
                .shouldHaveValue(tableRowSubjects,subject)
                .shouldHaveValue(tableRowHobby,hobby)
                .shouldHaveValue(tableRowPicture,picture)
                .shouldHaveValue(tableRowAddress,address)
                .shouldHaveValue(tableRowStateAndCity,state);
    }

    @Test
    void shouldSubmitFormWithRequiredFieldsTest() {

        practiceFormPage
                .openPage()
                .removeBanner()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(mobile)
                .submit();

        resultModal.shouldAppear();
        resultModal
                .shouldHaveTitle(tableTitle)
                .shouldHaveValue(tableRowName, fullName)
                .shouldHaveValue(tableRowGender,gender)
                .shouldHaveValue(tableRowMobile,mobile);
    }

    @Test
    void shouldSubmitEmptyFormTest() {

        practiceFormPage
                .openPage()
                .removeBanner()
                .submit()
                .shouldBeValidated()
                .shouldHaveRedBorder("firstName")
                .shouldHaveRedBorder("lastName")
                .shouldHaveRedBorder("gender")
                .shouldHaveRedBorder("userNumber");
    }
}
