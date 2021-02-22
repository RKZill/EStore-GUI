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

/*This method creates a searchwindow and takes in the products, the hashmap, and the filename incase the user
decides to exit here, the file will still be saved to.
The searchwindow is a simple GUI with 4 input textboxes, 2 buttons to search or reset, and a feedback box
the inputs are ID, keywords, year start and year end. This will search the hashmap as well as the list of
products and see if any match what the user entered and if they do, it will print to the feedback box*/
public class SearchWindow extends JFrame{
  public static final int WIDTH = 800;
  public static final int HEIGHT = 800;
  public int x = 1;
  public static HashMap <String,ArrayList<Integer>> hashString = new HashMap<String,ArrayList<Integer>>();

  public SearchWindow(ArrayList<Product> listofproducts,HashMap <String,ArrayList<Integer>> hashString,String fileName){
    super();
    int i = -1;
    setSize(WIDTH,HEIGHT);
    setTitle("eStoreSearch");
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout()); //im using borderlayout
    JPanel buttonPanel = new JPanel(); //buttons, self explnitory
    buttonPanel.setLayout(new GridLayout(1,4));
    JButton qButton = new JButton("Quit");
    qButton.setPreferredSize(new Dimension (100,50));
    JLabel comLabel = new JLabel("Commands:");
    comLabel.setPreferredSize(new Dimension(100,50));
    buttonPanel.add(comLabel);
    JButton backButton = new JButton("Back");
    backButton.setPreferredSize(new Dimension(100,50)); //back button
    backButton.addActionListener(new ActionListener(){ //if clicked
      public void actionPerformed (ActionEvent e){
        FirstWindow a = new FirstWindow(listofproducts,hashString,fileName); //go to the first window
        a.setVisible(true); //set that window visable
        dispose(); //dispose of current window
      }
    });

    qButton.addActionListener(new ActionListener(){ //quit button
      public void actionPerformed (ActionEvent e){
        dispose(); //dispose window and end program
        System.exit(0);
      }
    });
    buttonPanel.add(backButton); //add these buttons to the button panel
    buttonPanel.add(qButton);

    add(buttonPanel,BorderLayout.NORTH); //add button pnale to layout
    JPanel valuePanel = new JPanel();
    valuePanel.setLayout(new GridLayout(8,1)); //input textboxes below
    JLabel title = new JLabel("Searching products");
    title.setPreferredSize(new Dimension(150,50));
    JLabel ID = new JLabel("Product ID:");
    ID.setPreferredSize(new Dimension (100,25));
    JLabel description = new JLabel("<html>Description<br/>Keywords:<html>");
    description.setPreferredSize(new Dimension (150,25));

    JLabel Syear = new JLabel("Start Year:");
    Syear.setPreferredSize(new Dimension (100,25));
    JLabel Nyear = new JLabel("End Year:");
    Nyear.setPreferredSize(new Dimension (100,25));
    valuePanel.add(title); //add all these labels
    valuePanel.add(ID);
    valuePanel.add(description);
    valuePanel.add(Syear);
    valuePanel.add(Nyear);
    add(valuePanel,BorderLayout.WEST); //add them to theleft side

    JPanel inputPanel = new JPanel();
    inputPanel.setPreferredSize(new Dimension(50,50)); //make new panel for inputs
    inputPanel.setLayout(new GridLayout(8,1));

    JTextField text1 = new JTextField(6); //inputs here
    text1.setPreferredSize(new Dimension(50,50));
    JTextField text2 = new JTextField(6);
    JTextField text3 = new JTextField(6);
    JTextField text4 = new JTextField(6);
    JTextField blank = new JTextField(6);
    blank.setVisible(false);
    inputPanel.add(blank); //dd all inputs to the input panel
    inputPanel.add(blank);
    inputPanel.add(text1);
    inputPanel.add(text2);
    inputPanel.add(text3);
    inputPanel.add(text4);
    add(inputPanel, BorderLayout.CENTER); //add input to borderlayout

    JPanel textPanel = new JPanel(); //make the feedback box
    JTextArea feedBack = new JTextArea(10,10);
    feedBack.setEditable(false); //not edidable

    JScrollPane scroll = new JScrollPane(feedBack); //make a scroll panel

    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); //set scroll bars always there
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    JPanel addresetPanel = new JPanel(); //make panel for add/reset
    addresetPanel.setPreferredSize(new Dimension(100,50));
    addresetPanel.setLayout(new GridLayout(2,1));
    add(scroll, BorderLayout.SOUTH); //add the scrollable text area to the bottom



    //search buttons and reset buttons
    JButton aButton = new JButton("Search");
    aButton.setPreferredSize(new Dimension (100,50));
    JButton rButton = new JButton("Reset");
    rButton.setPreferredSize(new Dimension (100,50));
    addresetPanel.add(aButton); //add these tot he panel
    addresetPanel.add(rButton);
    add(addresetPanel, BorderLayout.EAST); //add it to the panel

    aButton.addActionListener(new ActionListener(){ //if search is clicked
      public void actionPerformed (ActionEvent e){
        String ID = text1.getText(); //retrieving text inputs
        String keyWords = text2.getText();
        String yearss = text3.getText();
        String yearns = text4.getText();
        int years=0; //starting variables
        int yearn=100000;
        int state=-1;
        try{ //try to construct the search terms
          Searching search = new Searching(ID,keyWords,yearss,yearns); //this doesnt make an object just checks inputs
          state=1; //success
        }
        catch (Exception x){ //catch exceptions for invalid search terms
          feedBack.setText(x.getMessage());
        }
        if (state==1){ //if search terms are valid, continue to the search
          if (yearss.equals("")){ //if starting year empty make it 0
            years=0;
          }
          else {
            years=Integer.parseInt(yearss); //if not empty the start year is what the user wants

          }
          if(yearns.equals("")){ //same thing goes for year end except will be 10000 to be a maximum
            yearn = 100000;
          }
          else{
            yearn = Integer.parseInt(yearns);
          }
          int i;
          if (!keyWords.isEmpty()){ //if keywords are searched for
            ArrayList<Integer> array = new ArrayList<Integer>(); //make a new arraylist of integers we will use to store matching keywrods
            HashMap <String,ArrayList<Integer>> hashString = initalizeHashMap(listofproducts);
            array = hashSearch(listofproducts, keyWords,hashString); //get the keyword array from hashsearch
            if (ID.isEmpty()&&yearss.isEmpty()&&yearns.isEmpty()){ //if searching for only keywords
              feedBack.setText("");
              for (i=0; i<array.size(); i++){

                feedBack.append("\n");
                feedBack.append(listofproducts.get(array.get(i)).toString());
              }
            }
            else if (!ID.isEmpty()&&yearss.isEmpty()&&yearns.isEmpty()){ //keywords and ID
              feedBack.setText("");
              for (i=0; i<array.size(); i++){
                if (listofproducts.get(array.get(i)).getProductID().equals(ID) ){
                  feedBack.append("\n");
                  feedBack.append(listofproducts.get(array.get(i)).toString());
                }
              }
            }
            else if (ID.isEmpty()&&!(yearss.isEmpty()&&yearns.isEmpty())){ //keywrods and time
              feedBack.setText("");
              for ( i=0; i<array.size(); i++){
                if (Integer.parseInt(listofproducts.get(array.get(i)).getYear())>=years
                &&Integer.parseInt(listofproducts.get(array.get(i)).getYear())<=yearn ){

                  feedBack.append("\n");
                  feedBack.append(listofproducts.get(array.get(i)).toString());
                }
              }
            }
            else if (!ID.isEmpty()&&!(yearss.isEmpty()&&yearns.isEmpty())){ //keywords, time, and ID
              feedBack.setText("");
              for (i=0; i<array.size(); i++){
                if (Integer.parseInt(listofproducts.get(array.get(i)).getYear())>=years
                &&Integer.parseInt(listofproducts.get(array.get(i)).getYear())<=yearn
                &&listofproducts.get(array.get(i)).getProductID().equals(ID)) {

                  feedBack.append("\n");
                  feedBack.append(listofproducts.get(array.get(i)).toString());
                }
              }
            }
          }
          else{
            if (ID.isEmpty()&&(yearss.isEmpty()&&yearns.isEmpty())){ //print everything if no search term was specified
              feedBack.setText("");
              for (i=0;i<listofproducts.size();i++){

                feedBack.append("\n");
                feedBack.append(listofproducts.get(i).toString());
              }
            }
            else if (!ID.isEmpty()&&(yearss.isEmpty()&&yearns.isEmpty())){ //print matching ID
              feedBack.setText("");
              for ( i=0; i<listofproducts.size();i++){ //loop through
                if (listofproducts.get(i).getProductID().equals(ID)){//test to see if theyre any matching IDS

                  feedBack.append("\n");
                  feedBack.append(listofproducts.get(i).toString());
                }
              }
            }
            else if (ID.isEmpty()&&!(yearss.isEmpty()&&yearns.isEmpty())){ //print matching time period
              feedBack.setText("");
              for (i=0; i<listofproducts.size(); i++){ //loops through
                if (Integer.parseInt(listofproducts.get(i).getYear())>=years//if it matches the time period, then print
                &&Integer.parseInt(listofproducts.get(i).getYear())<=yearn ){
                  feedBack.append("\n");
                  feedBack.append(listofproducts.get(i).toString());
                }
              }
            }
            else if (!ID.isEmpty()&&!!(yearss.isEmpty()&&yearns.isEmpty())){ //print matching time period and ID
             //this is the exact same as printTime and printID but adding the id statements together
             feedBack.setText("");
              for ( i=0; i<listofproducts.size(); i++){
                if (Integer.parseInt(listofproducts.get(i).getYear())>=years
                &&Integer.parseInt(listofproducts.get(i).getYear())<=yearn ){
                  if (listofproducts.get(i).getProductID().equals(ID)){
                    feedBack.append("\n");
                    feedBack.append(listofproducts.get(i).toString());
                  }
                }
              }
            }
          }
        }
      }
    });

    rButton.addActionListener(new ActionListener(){ //reset button, resets textboxes
      public void actionPerformed (ActionEvent e){
        text1.setText(""); //sets all the text to blank.
        text2.setText("");
        text3.setText("");
        text4.setText("");
        feedBack.setText("");
      }
    });
  }


  /**This will update the hashmap if a new product is added*/
public static HashMap<String,ArrayList<Integer>> initalizeHashMap(ArrayList<Product> listofproducts){
  HashMap <String,ArrayList<Integer>> hashString = new HashMap<String,ArrayList<Integer>>();
  for (int i=0;i<listofproducts.size();i++){ // loop through array size
    StringTokenizer keyWords = new StringTokenizer(listofproducts.get(i).getDescription()," "); //tokenize inputs on spaces
    while (keyWords.hasMoreTokens()){ //go through all keywords
      String nextToken = keyWords.nextToken().toLowerCase(); //make keyword lower case
      hashString.putIfAbsent(nextToken, new ArrayList <Integer>()); //put if absent into hashmap
      hashString.get(nextToken).add(i); //add the number at which the word was found into that words corresponding placement in the hashmap
    }
  }
  return hashString; //idk why I have to return it as the variable is publicly declared in this class but yeah it doesnt work if I dont
}
  /*This takes in an input and will use the hshmap to find the arraylist that cotnains all the searchWords
  the user wants to enter. it will return this arrylist of integers and the program will use that to determine
  which products match the keywords*/
  public static ArrayList<Integer> hashSearch(ArrayList<Product> listofproducts,String input,HashMap <String,ArrayList<Integer>> hashString){
    StringTokenizer searchWords = new StringTokenizer(input," "); //tokenize keywrods at a space
    ArrayList<Integer> list = new ArrayList<Integer>(); //initalize arraylists used to add
    ArrayList<Integer> current = new ArrayList<Integer>();
    for (int i=0;i<listofproducts.size();i++){ //make an arraylist containg the numbers 0 theough list size
      list.add(i);
    }
    while (searchWords.hasMoreTokens()){ //while there is a keywrod to search for
      current = hashString.get(searchWords.nextToken().toLowerCase()); //current arraylist is equal to the arraylist containging the current word
      if (current!=null){
        list.retainAll(current); //if the arraylist contains values add it to the list using an intersection
      }
      else{ //if we get a null aka a word that doesnt exist on any products, then we return an rmepty arraylist
        return new ArrayList<Integer>();
      }
    }

    return list; //return the list at the end to send to the search processing
  }










}
