import by.epam.medicalweb.util.Validator;
import by.epam.medicalweb.util.impl.ValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ValidatorTest {
    Validator validator;
    @BeforeClass
    public void init(){
        validator = ValidatorImpl.getInstance();
    }

    @Test
    public void checkNameTest() {
        boolean isCorrect = validator.checkName("Ekaterina");
        Assert.assertTrue(isCorrect);
    }
    @Test
    public void checkEmailTest() {
        boolean isCorrect = validator.checkEmail("katestasevich30@gmail.com");
        Assert.assertTrue(isCorrect);
    }
    @Test
    public void checkLoginTest() {
        boolean isCorrect = validator.checkLogin("Ekaterina30");
        Assert.assertTrue(isCorrect);
    }
    @Test
    public void checkPasswordTest(){
        boolean isCorrect = validator.checkPassword("ksdfjhbkead_sd");
        Assert.assertTrue(isCorrect);
    }
    @Test
    public void checkBirthDateTest(){
        boolean isCorrect = validator.checkBirthDate("2011-12-12");
        Assert.assertTrue(isCorrect);
    }
    @Test
    public void checkPhoneNumberTest(){
        boolean isCorrect = validator.checkPhoneNumber("256783565");
        Assert.assertTrue(isCorrect);
    }

}
