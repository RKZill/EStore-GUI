package eStoreSearch;
/*The class book is a child class of product, containing fields for author and publisher*/
class Book extends Product{
  private String author = "";
  private String publisher = "";
  Book(String productID, String description, String price, String year, String author, String publisher)throws Exception{
    super(productID,description,price,year);
    setAuthor(author);
    setPublisher(publisher);
  }
  Book(Book b){ //copy constructor
    super(b);
    author = b.author;
    publisher = b.publisher;
  }
  public String getAuthor(){ //getters and setters below. For each variable in the class
    return this.author;
  }
  public void setAuthor(String author){
    this.author = author;
  }
  public String getPublisher(){ //getters and setters below. For each variable in the class
    return this.publisher;
  }
  public void setPublisher(String publisher){
    this.publisher = publisher;
  }
  //override the tostring method
  public String toString(){
    return getProductID()+" "+getDescription()+" $"+getPrice()+" "+getYear()+" "+getAuthor()+
    " "+getPublisher();
  }
}
