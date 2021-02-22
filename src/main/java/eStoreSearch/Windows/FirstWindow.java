package eStoreSearch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

/*This is the inital window the user will see. It features a couple messages explainging how it works
and the status of file input (if a file was selected or not selected). You can either quit, add, or search*/

public class FirstWindow extends JFrame{
  public static final int WIDTH = 800;
  public static final int HEIGHT = 800;
  public FirstWindow(ArrayList<Product>listofproducts,HashMap <String,ArrayList<Integer>> hashString,String fileName){
    super();
    setSize(WIDTH,HEIGHT);
    setTitle("eStoreSearch");
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new GridLayout(1,4));

    JButton aButton = new JButton("Add");
    aButton.setPreferredSize(new Dimension (100,50));
    JButton sButton = new JButton("Search");
    sButton.setPreferredSize(new Dimension (100,50));
    JButton qButton = new JButton("Quit");
    qButton.setPreferredSize(new Dimension (100,50));
    JLabel comLabel = new JLabel("Commands:");
    comLabel.setPreferredSize(new Dimension(100,50));
    buttonPanel.add(comLabel);
    buttonPanel.add(aButton);
    buttonPanel.add(sButton);
    JLabel message = new JLabel("");
    if (listofproducts.size()>0){
       message.setText("<html>You have entered a filename in the command line, product(s) have been added"+
       "<br/ The Program will attempt to append all new additions to the file when the program is quit");
    }
    else{
       message.setText("You have either entered a file with no products, or your no file was choosen");
    }
    add(message,BorderLayout.SOUTH);
    JLabel welcomeMessage = new JLabel("<html> Welcome to EStoreSearch <br/>    Choose a command from the commands menu above for"
    +"<br/>     adding a product, searching products, or quitting the program<html>");
    welcomeMessage.setFont(new Font("Ariel",Font.PLAIN,20));

    add(welcomeMessage , BorderLayout.CENTER);
    aButton.addActionListener(new ActionListener(){
      public void actionPerformed (ActionEvent e){
        AddWindow a = new AddWindow(listofproducts,hashString,fileName);
        a.setVisible(true);
        dispose();
      }
    });
    sButton.addActionListener(new ActionListener(){
      public void actionPerformed (ActionEvent e){
        SearchWindow a = new SearchWindow(listofproducts,hashString,fileName);
        a.setVisible(true);
        dispose();
      }
    });
    qButton.addActionListener(new ActionListener(){
      public void actionPerformed (ActionEvent e){
        dispose();
        return;
      }
    });
    buttonPanel.add(qButton);
    add(buttonPanel,BorderLayout.NORTH);

  }

}
