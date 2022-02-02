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
        boolean isCorrect = validator.isCorrectName("Ekaterina");
        Assert.assertTrue(isCorrect);
    }
    @Test
    public void checkEmailTest() {
        boolean isCorrect = validator.isCorrectEmail("katestasevich30@gmail.com");
        Assert.assertTrue(isCorrect);
    }
    @Test
    public void checkLoginTest() {
        boolean isCorrect = validator.isCorrectLogin("Ekaterina30");
        Assert.assertTrue(isCorrect);
    }
    @Test
    public void checkPasswordTest(){
        boolean isCorrect = validator.isCorrectPassword("sfhyegjse");
        Assert.assertTrue(isCorrect);
    }
   /* @Test
    public void checkBirthDateTest(){
        boolean isCorrect = validator.isCorrectBirthDate("2011-12-12");
        Assert.assertTrue(isCorrect);
    }*/
    @Test
    public void checkPhoneNumberTest(){
        boolean isCorrect = validator.isCorrectPhoneNumber("256783565");
        Assert.assertTrue(isCorrect);
    }

}
