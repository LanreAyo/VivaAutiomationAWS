package Utilities;

public class AdPostItem {

    public String Gender;
    public String Title;
    public String Description;
    public Categories categories;
    public String Lookingfor;
    public String age;
    public String PostCode;

    public AdPostItem getAdPostitem() {

        Description = Credentials.Description;
        Title = Credentials.Title;
        Gender = Credentials.gender;
        Lookingfor = Credentials.LookingFor;
        age = Credentials.Age;
        PostCode = Credentials.PostCode;
        return this;
    }


    public AdPostItem creatAdPostItem(){


        return getAdPostitem();
    }

    public AdPostItem withoutTitle() {


        AdPostItem adPostItem= creatAdPostItem();
        adPostItem.Title="no title here";

        return adPostItem;

    }
}