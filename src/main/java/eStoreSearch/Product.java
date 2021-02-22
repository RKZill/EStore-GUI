
package eStoreSearch;
/* this is the product class, which is abstract so only its child classes can be an object*/
abstract class Product{
  private String productID="";
  private String description="";
  private String price="";
  private String year="";
  Product(String productID, String description, String price , String year)throws Exception{
    setProductID(productID);
    setDescription(description);
    setPrice(price);
    setYear(year);
  }
  Product (Product p){ //copy constructor
    productID = p.productID;
    description = p.description;
    price = p.price;
    year = p.year;
  }
  public String getProductID(){ //getters and setters below. For each variable in the class
    return this.productID;
  }
  public void setProductID(String productID) throws Exception{
    if (productID.isEmpty()){
      throw new Exception ("Error, Product ID is required");
    }
    try {
      Integer.parseInt(productID);
    }
    catch (Exception e){
      throw new Exception ("Product ID was non numeric");
    }
    if (productID.length()!=6){
      throw new Exception ("Error. ID not right length");
    }
    this.productID = productID;
  }
  public String getDescription(){ //getters and setters below. For each variable in the class
    return this.description;
  }
  public void setDescription(String description)throws Exception{
    if (description.isEmpty()){
      throw new Exception ("Error, description is required");
    }
    this.description = description;
  }
  public String getPrice(){ //getters and setters below. For each variable in the class
    return this.price;
  }
  public void setPrice(String price)throws Exception{
    if (price.isEmpty()){
      this.price="0.00";
      return;
    }
    try {
      Double.parseDouble(price);
    }
    catch(Exception e){
      throw new Exception("Error. Price is not valid");
    }
    this.price = price;
  }
  public String getYear(){ //getters and setters below. For each variable in the class
    return this.year;
  }
  public void setYear(String year) throws Exception{
    try{
      Integer.parseInt(year);
    }
    catch (Exception e){
      throw new Exception("Error. year is non numeric");
    }
    if (Integer.parseInt(year)<1000 ||Integer.parseInt(year)>9999){
      throw new Exception("Error. Year was not in range 1000-9999");
    }
    this.year = year;
  }
  abstract public String toString();
}
