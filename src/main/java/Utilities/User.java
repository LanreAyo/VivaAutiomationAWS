package Utilities;

public class User {

    public  String email;
    public String password;
    public  String username;
    public  UserType userType;
    public String company;
    public String address;
    public String categoryValue;
    public AlertItem alertItem;
    public String cardNumber;
    public  Categories categories;
    public String postcode;
    public String title;
    public AdPostItem adPostItem;
    public CardDetails cardDetails;


    public void setAlertItem(AlertItem alertItem) {
        this.alertItem = alertItem;
    }


    public User() {
        cardDetails = new CardDetails();
    }

    //Expiry

    public void setCardExpiry(String expiry) {
        cardDetails.setCardExpiry(expiry);
    }

    public String getCardExpiry() {
        return cardDetails.cardExpiry;
    }

    // CardHolderName

    public  void setcardHolderName(String Name){cardDetails.setCardHolderName(Name);}

    public  String getcardHolderName(){return cardDetails.cardHolderName;}

    // Security Code
    public void setCardSecurityCode(String securityCode){cardDetails.setSecurityCode(securityCode);}

    public String getCardSecurityCode(){return cardDetails.securityCode;}

    //Card Number
    public void setCardNumber(String cardNumber) {cardDetails.setCardNumber(cardNumber);}

    public String getCardNumber() {return cardDetails.cardNumber;}


    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }


    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }


    //------------

    public String getTitle() { return title;}

    public void setTitle(String title) {
        this.title = title;
    }

    //------------

    public AdPostItem getAdPostItem() {
        return adPostItem;
    }

    public void setAdPostItem(AdPostItem adPostItem) {
        this.adPostItem = adPostItem;
    }

    public  String getEmail() {
        return email;
    }

    public  void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
