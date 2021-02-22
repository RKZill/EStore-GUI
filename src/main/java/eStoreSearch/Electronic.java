package eStoreSearch;
/* a child class of product, electronics contins the additonal input of maker */
class Electronic extends Product{
  private String maker="";
  Electronic(String productID, String description, String price, String year, String maker) throws Exception{
    super(productID,description,price,year); //super
    setMaker(maker); //set
  }
  public String getMaker(){
    return this.maker;
  }
  public void setMaker(String maker){
    this.maker = maker;
  }

  public String toString(){
    return getProductID()+" "+getDescription()+" $"+getPrice()+" "+getYear()+" "+getMaker();
  }
}
