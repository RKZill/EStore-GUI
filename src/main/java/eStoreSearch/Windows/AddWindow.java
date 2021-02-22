package eStoreSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.ArrayList;
/*This method creates a sing window titles addwindow. It hashmap (to update it if needed) the list of products, and the
filename to save new additions. The window features a combobox to choose between electronic and book. This will change
the possible attributes the user can enter for each product.
If the user enters an invalid input, it will tell the user and allow them to enter the value again.
*/
public class AddWindow extends JFrame implements ActionListener{
  public static final int WIDTH = 800;
  public static final int HEIGHT = 800;
  public int x = 1;
  public AddWindow(ArrayList<Product> listofproducts,HashMap <String,ArrayList<Integer>> hashString,String fileName){
    super();
    int i = -1;
    setSize(WIDTH,HEIGHT);
    setTitle("eStoreSearch");
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    JPanel buttonPanel = new JPanel(); //buttons
    buttonPanel.setLayout(new GridLayout(1,4));
    JButton qButton = new JButton("Quit");
    qButton.setPreferredSize(new Dimension (100,50));
    JButton backButton = new JButton("Back");
    backButton.setPreferredSize(new Dimension(100,50));
    JLabel comLabel = new JLabel("Commands:");
    comLabel.setPreferredSize(new Dimension(100,50));
    buttonPanel.add(comLabel);
    backButton.addActionListener(new ActionListener(){ //back button
      public void actionPerformed (ActionEvent e){
        FirstWindow a = new FirstWindow(listofproducts,hashString,fileName); //goes back to the first window
        a.setVisible(true);
        dispose(); //closes current window
      }
    });
    qButton.addActionListener(new ActionListener(){ //quit button
      public void actionPerformed (ActionEvent e){
        dispose(); //closes current window
        return; //quits
      }
    });
    buttonPanel.add(backButton); //add the buttons to the panels
    buttonPanel.add(qButton);
    add(buttonPanel,BorderLayout.NORTH); //add the button panel to the borderlayout

    JPanel valuePanel = new JPanel(); //make the panel for the input titles
    valuePanel.setLayout(new GridLayout(8,1)); //make it a gridlayout
    JLabel type = new JLabel("Type:"); //various labels
    type.setPreferredSize(new Dimension (100,25));
    JLabel ID = new JLabel("Product ID:");
    ID.setPreferredSize(new Dimension (100,25));
    JLabel description = new JLabel("Description:");
    description.setPreferredSize(new Dimension (100,25));
    JLabel price = new JLabel("Price:");
    price.setPreferredSize(new Dimension (100,25));
    JLabel year = new JLabel("Year:");
    year.setPreferredSize(new Dimension (100,25));
    JLabel author = new JLabel("Author:");
    author.setPreferredSize(new Dimension (100,25));
    author.setVisible(true); //set the visability of the product specific textfields to false, these will be set to true later
    JLabel publisher = new JLabel("Publisher:");
    publisher.setPreferredSize(new Dimension (100,25));
    publisher.setVisible(true);
    valuePanel.add(publisher);
    valuePanel.add(author);
    JLabel maker = new JLabel("Maker:");
    maker.setPreferredSize(new Dimension (100,25));
    maker.setVisible(false);
    valuePanel.add(type); //add the inputs to the input panel
    valuePanel.add(ID);
    valuePanel.add(description);
    valuePanel.add(price);
    valuePanel.add(year);


    add(valuePanel,BorderLayout.WEST); //add the input panel to the borderlayout at the left side

    JPanel inputPanel = new JPanel(); //make the panel for the input fields
    inputPanel.setPreferredSize(new Dimension(100,50)); //set size
    JPanel addresetPanel = new JPanel();
    addresetPanel.setPreferredSize(new Dimension(100,50)); //make the panels for add/reset
    addresetPanel.setLayout(new GridLayout(2,1));
    inputPanel.setLayout(new GridLayout(8,1)); //the layout will be gridlayout



    String[] choices = {"Book","Electronics"};   //these are the choices
    JComboBox<String> choiceList = new JComboBox<String>(choices);
    choiceList.setSelectedIndex(0);                    //set the select
    choiceList.addActionListener(this);                //add action listener
    choiceList.setPreferredSize(new Dimension(100,100));
    JTextArea feedBack = new JTextArea(10,100);    // make the feedBack
    feedBack.setEditable(false);
    JScrollPane scroll = new JScrollPane(feedBack);// make the feedback a scroll pane
    add(scroll, BorderLayout.SOUTH);
    JTextField text1 = new JTextField(6); //make the text inputs
    JTextField text2 = new JTextField(6);
    JTextField text3 = new JTextField(6);
    JTextField text4 = new JTextField(6);
    JTextField text5 = new JTextField(6);
    JTextField text6 = new JTextField(6);
    text5.setVisible(true); //set visability of the special 2 case specific
    text6.setVisible(true);



    inputPanel.add(choiceList); //add the combobox and the text inputs to the input panel
    inputPanel.add(text1);
    inputPanel.add(text2);
    inputPanel.add(text3);
    inputPanel.add(text4);
    inputPanel.add(text5);
    inputPanel.add(text6);

    choiceList.addActionListener(new ActionListener(){ //combobox action listener
      public void actionPerformed (ActionEvent e){
        JComboBox cb = (JComboBox)e.getSource(); //make new combox that is the combobox
        String choice = (String)cb.getSelectedItem(); //make a string which will be the choice of the combo box
        if (choice.equals("Electronics")){ //if you choose electronic
          x=0;
          valuePanel.remove(author); //remove all the stuff for book
          valuePanel.remove(publisher);
          text5.setVisible(true); //make equal another text field for maker
          valuePanel.add(maker);
          maker.setVisible(true); //set the label of that etxt field to visable
          inputPanel.remove(text6);
          valuePanel.revalidate();//revaluate the input panel and the value panel
          valuePanel.repaint();
          inputPanel.revalidate();
          inputPanel.repaint();
        }
        if (choice.equals("Book")){ //comboboc choise for book
          x=2;
          inputPanel.add(text6); //exact same as above for electronic except
          valuePanel.remove(maker); //i remove the stuff for electronic instead of book,
          text5.setVisible(true); //and i add the stuff for book instead as well
          text6.setVisible(true);
          valuePanel.add(author);
          valuePanel.add(publisher);
          author.setVisible(true);
          publisher.setVisible(true);
          valuePanel.revalidate();
          valuePanel.repaint();
        }
      }
    });


    add(inputPanel, BorderLayout.CENTER); //add th einput panel tot he centre of the screen

    JButton aButton = new JButton("Add"); //make the add button adnt he reset button
    aButton.setPreferredSize(new Dimension (100,50)); //set their size
    JButton rButton = new JButton("Reset");
    rButton.setPreferredSize(new Dimension (100,50));
    addresetPanel.add(aButton); //add them tot eh ass/reset panel
    addresetPanel.add(rButton);
    add(addresetPanel, BorderLayout.EAST); //add the reset panel to the left side

    aButton.addActionListener(new ActionListener(){ //if user clicks add
      public void actionPerformed (ActionEvent e){
        String ID = text1.getText(); //make strings of all the inputs for book
        String description = text2.getText();
        String price = text3.getText();
        String year = text4.getText();
        String autmak = text5.getText();
        String publisher = text6.getText();
        if (x==1){ //if type is not specificed
          feedBack.setText("Error. Must incluse product type"); //tell the user
        }
        if (x==2){ //book
          try { //try to add a books
            //this creates a new product of type Book
            Product newBook = new Book (new Book(ID , description , price , year , autmak, publisher));
            checkdupID(listofproducts, ID); //check for duplicate ID
            listofproducts.add(newBook); //add book if creation and dupe checka re valid
            feedBack.setText("Succesfully Added your product - Book !"); //message for adding a book
            updateHashMap(hashString,listofproducts); //update the hashmap
            SaveInfo.saveInfo(listofproducts,fileName); //save the new info to the file

          }
          catch (Exception b){ //catch exceptions
            feedBack.setText(b.getMessage()); //print messaage
          }
        }
        if (x==0){ //if electronic is selected
          try{ //exact same as above
            Electronic newElectronic = new Electronic (ID, description, price, year, autmak);
            checkdupID(listofproducts, ID);
            listofproducts.add(newElectronic);
            feedBack.setText("Succesfully Added your product - Electronic !");
            updateHashMap(hashString, listofproducts);
            SaveInfo.saveInfo(listofproducts,fileName);
          }
          catch (Exception b){
            feedBack.setText(b.getMessage());
          }
        }
      }
    });

    rButton.addActionListener(new ActionListener(){ //reset button
      public void actionPerformed (ActionEvent e){
        text1.setText(""); //Sets all text fields to blank
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text5.setText("");
        text6.setText("");
      }
    });
  }
  /** this will initalize the hashmap we use for searcing, and return the new hashmap*/
  public static HashMap <String,ArrayList<Integer>> updateHashMap(HashMap <String,ArrayList<Integer>> hashString,ArrayList<Product> listofproducts){
    StringTokenizer keyWords = new StringTokenizer(listofproducts.get(listofproducts.size()-1).getDescription()," ");
    String nextToken = keyWords.nextToken().toLowerCase();
    hashString.putIfAbsent(nextToken, new ArrayList <Integer>()); //add if its not lready there
    hashString.get(nextToken).add(listofproducts.size()-1);
    return hashString; //return a hashmap
  }
  /*Checks for duplicate IDS. if a dupe is found it throws an exception and no product is made. It will then inform the user
  and allow them to reenter another ID*/
  public static void checkdupID(ArrayList<Product> listofproducts,String ID) throws Exception{
    for (int i=0;i<listofproducts.size();i++){
      if (listofproducts.get(i).getProductID().equals(ID)){
        throw new Exception ("Error. Product ID already exists");
      }
    }
  }
  /*empty actionpreformed*/
  public void actionPerformed(ActionEvent e){
    String buttonString = e.getActionCommand();
  }


}
