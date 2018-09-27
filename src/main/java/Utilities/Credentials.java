package Utilities;

public class Credentials {

    EmailGenerator emailGenerator = new EmailGenerator();
    public static String registeredPassword = "Test1234x";
    public static String newPassword = "blahblahblah";
    public String newEmail =  emailGenerator.GenerateEmail("Lanre");

    public static String UnregisteredEmail = "invalid1234@dm-companies.com";
    public String noEmail = emailGenerator.GenerateInvalidEmail();
    public String invalidEmail = emailGenerator.GenerateEmail(".");
    public static String incorrectPassword = "Wrong123";
    public static String noPassword = "";
    public static String invalidPassword = ".";

    public static String newUsername = "NewUser12345";
    public static String noUsername = "";
    public static String noCompany = "";
    public static String newAddress = "1-4 Warwick Street London";
    public static String noAddress = "";
    public static String confirmAccountUrl = "account.php?account_notify=account_not_confirmed";


    //User Card Details
    public  static String cardNumber="4567350000427977";
    public static  String cardholderName="Masaki Pierce";
    public static  String cardExpiry="0119";
    public static  String cardSecurityCode="111";
    public  static String cardinvalidLength="456735000";
    public String invalidExpiry="9100";


    //Card Error Messages

    public static  String invalidCardLengthErrorMessage= "Verify card number";
    public static String cardExpiryErrorMessage = "Enter a valid date";
    //Email Error Messages
    public static String emailErrorMessage = "This is a required field.";
    public static String requiredFieldErrorMessage = "*This is a required field";
    public static String invalidEmailErrorMessage = "Please enter a valid email address";
    public static String confirmEmailErrorMessage = "This is a required field.";
    public static String companyNameErrorMessage = "This is a required field.";
    public static String companyAddressErrorMessage = "This is a required field.";
    public static String passwordErrorMessage = "This is a required field.";
    public static String usernameErrorMessage = "This is a required field.";
    public static String emailExistsErrorMessage = "The email address exists but with a different password";

    public static String NewUsername = "NewUser12345";
    public static String NewCompany = "NewCompany12345";
    public static String NewAddress = "1-4 Warwick Street\nLondon";


    public static String RegisteredIndividual2 = "william.preston+ind2@dm-companies.com";
    public static String Username1 = "New User";
    public static String ValidPhoneNumber1 = "02123456578";
    public static String ValidInformation1 = "This is a test account\nThis is part of an automation test";
    public static String ValidActivites1 = "Testing, Automation Testing";
    public static String ValidWebsite1 = "http://vivastreet.co.uk";
    public static String DOBDay1 = "9";
    public static String DOBMonth1 = "February";
    public static String Gender = "Male";
    public static String InvalidURL = "asdfasdfasdfasdf123";
    public static String ValidCompanyName1 = "A New Company";
    public static String ValidCompanyAddress1 = "1-4 Warwick Street\nLondon";
    public static String ValidPostCode1 = "SE16 2HS";
    public static String ValidPhoneNumberTwo1 = "02392503055";
    public static String ValidShopName1 = "A Shop";


//    public static String RegisteredEmail = "william.preston1@dm-companies.com";
//    public static String RegisteredPassword = "Test1234x";

    public static String RegisteredEmail = "psolankiqaviva+123@gmail.com";
    public static String RegisteredPassword = "Test123";

    public static String ChangePassword = "Test123";

    public static String InvalidEmail = "invaliddm-companies.com";
    public static String IncorrectPassword = "Wrong123test123";
    public static String InvalidPasswordShort = "P";
    public static String InvalidUsername = "U";


    //for posting ad
    public static String PostCode = "SW3 1ER";
    public static String Title = "This is a test advert by Vivastreet QA - Please ignore";
    public static String Description = "This is a test advert by Vivastreet QA - Please ignore";
    public static String gender = "Man";
    public static String Age = "18";
    public static String LookingFor = "Woman";
    public static String categoryValue="Adult Dating";

    //for alert screen
    public String alertCategory = "Adult Dating";
    public String alertGender = "Woman";
    public String alertLikesInterests = "Films";
    public String alertLocation = "East Anglia";
    public String alertMinAge = "18";
    public String alertMaxAge = "34";
    public String alertLookingFor = "BMW E30";
}
