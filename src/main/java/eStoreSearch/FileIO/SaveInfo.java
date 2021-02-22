package eStoreSearch;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.stream.Collectors;

class SaveInfo{
  public static int saveInfo(ArrayList <Product> listofproducts, String file){
    if (file.isEmpty()){
      return 0;
    }
    try{ //try to write to file
      PrintWriter fileWriter = null;
      fileWriter = new PrintWriter(new FileOutputStream(file));
      for (int i=0;i<listofproducts.size();i++){
        if (listofproducts.get(i) instanceof Book){ //if adding book
          fileWriter.println("type = \""+"book"+"\""); //print the type and its attributes
          fileWriter.println("productID = \""+listofproducts.get(i).getProductID()+"\"");
          fileWriter.println("description = \""+listofproducts.get(i).getDescription()+"\"");
          fileWriter.println("price = \""+listofproducts.get(i).getPrice()+"\"");
          fileWriter.println("year = \""+listofproducts.get(i).getYear()+"\"");
          fileWriter.println("author(s) = \""+((Book)listofproducts.get(i)).getAuthor()+"\"");
          fileWriter.println("publisher = \""+((Book)listofproducts.get(i)).getPublisher()+"\"");
        }
        else if (listofproducts.get(i) instanceof Electronic){ //if adding electronic
          fileWriter.println("type = \""+"electronic"+"\"");
          fileWriter.println("productID = \""+listofproducts.get(i).getProductID()+"\"");
          fileWriter.println("description = \""+listofproducts.get(i).getDescription()+"\"");
          fileWriter.println("price = \""+listofproducts.get(i).getPrice()+"\"");
          fileWriter.println("year = \""+listofproducts.get(i).getYear()+"\"");
          fileWriter.println("maker = \""+((Electronic)listofproducts.get(i)).getMaker()+"\"");
        }
      }
      fileWriter.close(); //cloe file stream
    }
    catch (FileNotFoundException e){ //check for erros
      System.out.println("Failure to open file. You entered '"+file+"'\n Double check spelling and for existence of file");
      return -1;
    }
    catch (Exception e){ //general check jsut in case something happens
      System.out.println("Failure to open file. You entered '"+file+"'\n Double check spelling and for existence of file");
      return -1;
    }
    return 1; //success
  }
}
