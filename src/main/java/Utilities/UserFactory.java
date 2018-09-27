package Utilities;

public class UserFactory {


    public Credentials credentials = new Credentials();


    public User basicUser() {

        User basicUser = new User();

        basicUser.setEmail(credentials.newEmail);
        basicUser.setUsername(credentials.newUsername);
        basicUser.setPassword(credentials.newPassword);
        basicUser.setCategoryValue(credentials.categoryValue);
        basicUser.setCardNumber(credentials.cardNumber);
        basicUser.setcardHolderName(credentials.cardholderName);
        basicUser.setCardExpiry(credentials.cardExpiry);
        basicUser.setCardSecurityCode(credentials.cardSecurityCode);

        //This creates a pre-populated template for an ad-post
        AdPostItem adPostItem = new AdPostItem();
        adPostItem.creatAdPostItem();

        AlertItem alertItem = new AlertItem();
        alertItem.Category = credentials.alertCategory;
        alertItem.Gender = credentials.alertGender;
        alertItem.LikesInterests = credentials.alertLikesInterests;
        alertItem.Location = credentials.alertLocation;
        alertItem.MinAge = credentials.alertMinAge;
        alertItem.MaxAge = credentials.alertMaxAge;
        alertItem.LookingFor = credentials.alertLookingFor;


        basicUser.setAdPostItem(adPostItem);
        basicUser.setAlertItem(alertItem);
        return basicUser;
    }

    public User basicUserNoCardnumber(){

        User noCardUser= basicUser();
        noCardUser.setCardNumber("");

        return noCardUser;
    }

    public User basicUserInvalidCardnumberLength() {
        User invalidLength = basicUser();
        invalidLength.setCardNumber(credentials.cardinvalidLength);

        return invalidLength;

    }

    public User basicUserNoCardholderName(){

        User noNameUser= basicUser();
        noNameUser.setcardHolderName("");
        return noNameUser;
    }

    public User validUser() {

        User validUser = new User();

        validUser.setEmail(credentials.newEmail);
        validUser.setUsername(credentials.newUsername);
        validUser.setPassword(credentials.newPassword);
        validUser.setUserType(UserType.Individual);

        return validUser;
    }

    public User noPostCodeUser() {
        User NoPostCodeUser =  basicUser();
        NoPostCodeUser.setPostcode("");

        return  NoPostCodeUser;
    }

    public User noTitleUser() {
        User NoTitleUser = basicUser();


        NoTitleUser.adPostItem.Title="";

        return  NoTitleUser;

    }

    public User registerProUser() {

        User registerProUser  = validUser();
        registerProUser.setUserType(UserType.Professional);
        registerProUser.setAddress(credentials.newAddress);
        registerProUser.setCompany(credentials.NewCompany);

        return registerProUser;
    }
    public User invalidUserNoEmail() {

        User invalidUserNoEmail = validUser();
        invalidUserNoEmail.setEmail(credentials.noEmail);
        invalidUserNoEmail.setPassword(credentials.noPassword);

        return invalidUserNoEmail;
    }

    public User invalidUserNoPassword() {

        User invalidUserNoPassword = validUser();
        invalidUserNoPassword.setPassword(credentials.noPassword);

        return  invalidUserNoPassword;
    }

    public User invalidUserInvalidPassword() {

        User invalidUserInvalidPassword = validUser();
        invalidUserInvalidPassword.setPassword(credentials.invalidPassword);

        return  invalidUserInvalidPassword;
    }

    public User InvalidUserNoUsername () {

        User  invalidUserNoUsername = invalidUserNoPassword();
        invalidUserNoUsername.setUsername(credentials.noUsername);
        return invalidUserNoUsername;
    }

    public User InvalidUserNoData () {

        User  invalidUserNoData = InvalidUserNoUsername();
        invalidUserNoData.setEmail(credentials.noEmail);

        return invalidUserNoData;
    }

    public User InvalidUserInvalidEmail () {

        User invalidUserInvalidEmail = InvalidUserNoUsername();
        invalidUserInvalidEmail.setEmail(credentials.invalidEmail);

        return invalidUserInvalidEmail;
    }

    public User RegisteredUserIncorrectPassword () {

        User registeredUser = validUser();
        registeredUser.setEmail(credentials.RegisteredEmail);
        registeredUser.setPassword(credentials.incorrectPassword);

        return registeredUser;
    }

    public User RegisteredUser () {

        User registeredUser = validUser();
        registeredUser.setEmail(credentials.RegisteredEmail);
        registeredUser.setPassword(credentials.RegisteredPassword);

        return registeredUser;
    }

    public User InvalidProUserNoCompany () {

        User invalidProUserNoCompany = registerProUser();
        invalidProUserNoCompany.setCompany(credentials.noCompany);

        return  invalidProUserNoCompany;
    }

    public User InvalidProUserNoAddress () {

        User invalidProUserNoAddress = registerProUser();
        invalidProUserNoAddress.setAddress(credentials.noAddress);

        return invalidProUserNoAddress;
    }


    public User basicUserInvalidExpiry() {

        User invalidExpiry = basicUser();
        invalidExpiry.setCardExpiry(credentials.invalidExpiry);
        return  invalidExpiry;
    }
}
