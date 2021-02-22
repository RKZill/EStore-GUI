package eStoreSearch;
class Searching{
  Searching(String ID, String keyword, String years, String yearN) throws Exception{
    setID(ID);
    setYears(years);
    setYearn(yearN);
  }

  public void setID(String productID) throws Exception{
    if (productID.isEmpty()){
      return;
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
    return;
  }

  public void setYears(String year) throws Exception{
    if (year.isEmpty()){
      return;
    }
    try{
      Integer.parseInt(year);
    }
    catch (Exception e){
      throw new Exception("Error. year is non numeric");
    }
    if (Integer.parseInt(year)<0 ||Integer.parseInt(year)>9999){
      throw new Exception("Error. Year was not in range 0000-9999");
    }

  }
  public void setYearn(String year) throws Exception{
    if (year.isEmpty()){
      return;
    }
    try{
      Integer.parseInt(year);
    }
    catch (Exception e){
      throw new Exception("Error. year is non numeric");
    }
    if (Integer.parseInt(year)<0 ||Integer.parseInt(year)>9999){
      throw new Exception("Error. Year was not in range 0000-9999");
    }

  }





}
