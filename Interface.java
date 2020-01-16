// Program written by Keeylan Hume Assignment 2
// Student ID c33020396
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class Interface implements Serializable
{
  private Depot [] depots = new Depot[4];
  public int option, a;
  private String name, productName, fileName, tempName;
  private double price, weight;
  private int quantity;
  private static int total = 0;
  
   public void run(){
      Scanner console = new Scanner(System.in);
      // Initilising depots to default value
      for (int i = 0; i < depots.length;){
          if(i < depots.length){
              depots[i] = new Depot(name);
              i++;
          }
      }
        
      do {
        menu();
        option = console.nextInt();
        switchloop:
        switch (option){
          case 1: // Add a depot        
                if (checkDepots(depots)){ // Runs if the depots are less than 4
                    System.out.println("Enter your depots name");
                    name = console.next().toLowerCase();
                    if (checkName1(depots, name)){ // run check method sending the depot array and inputted name
                        System.out.println("Depot name already exists");
                        break;
                    }
                    else{
                        depots[0].addDepot(depots, name);
                        total++;
                    }              
                }else{ 
                    System.out.println("Only 4 depots are allowed");
                    break;
                }
          break; // case 1 finished
         
          case 2: // remove a depot
                System.out.println("Enter depot name to remove");
                name = console.next().toLowerCase();
                    if (checkName1(depots, name)){ 
                        System.out.println("Removing " +name + " depot");
                        depots[0].removeDepot(depots, name);
                        total--;
                    }
                    else{
                        System.out.println("Depot name doesn't exist");  
                    }
          break;      
          case 3: // Add product
                System.out.println("Enter a product to add");
                productName = console.next().toLowerCase();
                for(int i = 0; i < depots.length; i++){ // for loop that checks product name          
                   if(depots[i].checkForProduct(depots, productName, i)){
                       depots[i].printDetails(depots,productName, i);
                       System.out.println("Please enter a quantity");// update quantity code
                       quantity = console.nextInt();
                       while (quantity < 0){
                          System.out.println("Quantity needs to be a positive number");     
                          quantity = console.nextInt();
                       }
                       System.out.println("Thank you. Please enter the depot you would like to add " + productName + " too");
                       name = console.next().toLowerCase();                        
                       a = checkName(depots,name,a); // prints out if doesnt exist. else returns a as the index
                       if (a >= 0){ // uses a as depot reference
                           if(depots[a].checkForProduct(depots,productName, a)){
                               depots[a].addQuantity(depots,productName,a,quantity);                         
                           }else{ // if above fails means product exists in another depot
                               depots[a].copyItem(depots,productName,name,a,quantity);   
                           }
                       }
                     break switchloop;
                    }                   
                }
                System.out.println("Enter products price");  
                price = console.nextDouble();
                while (price < 0){
                    System.out.println("Price needs to be a positive number");     
                    price = console.nextDouble();
                }
                System.out.println("Enter products weight *in kg's*");  
                weight = console.nextDouble();
                while (weight < 0){
                    System.out.println("Weight needs to be a positive number");     
                    weight = console.nextDouble();
                }
                System.out.println("Enter products quantity");
                quantity = console.nextInt();
                while (quantity < 0){
                    System.out.println("Quantity needs to be a positive number");     
                    quantity = console.nextInt();
                }
                System.out.println("Thank you. Please enter the depot you would like to add " + productName + " too");   
                name = console.next().toLowerCase();   
                a = checkName(depots,name,a);
                if(a >= 0){  // uses a ad depot reference                 
                    depots[a].setProduct(depots, name, productName, price, weight, quantity, a);    
                }               
          break;
            
          case 4: // remove product
                System.out.println("Enter the product you would like to remove");
                productName = console.next().toLowerCase();               
                for (int i = 0; i < depots.length; i++){ // checks if the product entered exists in any depot
                   if (depots[i].checkForProduct(depots,productName,i)){
                       System.out.println("Enter the depot");
                       name = console.next().toLowerCase();
                       System.out.println("Enter the quantity you would like to remove");
                       quantity = console.nextInt();            
                       a = checkName(depots,name,a);
                       if(a < 0){
                           break switchloop;
                        }
                       if (a >= 0){ // If the depot exists
                            if(depots[a].checkForProduct(depots, productName, a)){ // if the product exists in the depot specified
                                depots[a].removeQuantity(depots,productName,a,quantity);
                                break switchloop;
                            }else{
                                System.out.println("Product does not exist in this depot");
                                break switchloop;
                            }
                       }    
                   }
                }
                System.out.println("Product does not exist"); // change this and 
          break;
            
          case 5: //query for a list of depots
                if(total >= 1){
                    for(int i = 0; i < total; i++){  // loops through the depots, with total being the logical counter                 
                        depots[i].numberOfProducts(depots,i);                   
                    }
                }else{
                    System.out.println("No depots exist");  
                }
          break;
          case 6: // query list of products within a stated depot   
                 System.out.println("Enter the depot name");
                 name = console.next().toLowerCase();
                 a = checkName(depots,name,a);
                 if (a >= 0){ // uses a as depot reference
                     depots[a].printProducts(depots, a);                    
                 }else{
                     System.out.println("Depot doesn't exist");   
                 }
          break;    
            
          case 7: // query about a products presence in any depot
                System.out.println("Enter product name");
                int l = 0;
                productName = console.next().toLowerCase();
                l = depots[0].productCheck(depots, productName, l);
                if (l == 0){
                    System.out.println("Product doesn't exist");                    
                }else{
                    for (int k = 0; k < depots.length; k++){ // loops throug the depot checking specified name               
                    if (depots[k].checkForProduct(depots,productName,k)){                    
                        depots[k].productQuery(depots, productName, k);                 
                    }//if (total >= 0 && !depots[k].checkForProduct(depots,productName,k)){
                        //System.out.println("Product entered does not exist");
                }
            }
                //Syd
          break;
          
          case 8: // cumulative value of depots
                System.out.println("Enter the depot name");
                name = console.next().toLowerCase();
                a = checkName(depots, name, a); // sends name to method and returns a 
                if (a >= 0){ // uses a as the depot name reference
                    depots[a].depotValue(depots, a);                   
                }else{ // if a is less then 0, the depot doesnt exist
                    System.out.println("Depot doesn't exist");
                }
          break;
          
          case 9: // exporting depot and product to txt
                System.out.println("Enter the name of the file you would like to create");
                fileName = console.next().toLowerCase();
                PrintWriter outputStream = null;                
                try{
                    outputStream = new PrintWriter (fileName);
                }catch (FileNotFoundException e){
                    System.out.println("Error creating the file " +fileName);                    
                }
                
                for(int a = 0; a < total; a++){ // loops through the depots and sends the depot reference to the method
                 depots[a].addDetails(depots,outputStream,a);  
                }
                outputStream.close(); // closes the file               
          break; 
          
          case 10: // importing from file *** Couldn't figure out how to ignore an empty depot therefor it errors if there is one in the file
                System.out.println("Enter the file name you would like to import");
                fileName = console.next().toLowerCase();
                Scanner inputStream;         
                try{                 
                    inputStream = new Scanner (new File (fileName));                    
                    while (inputStream.hasNext()){ // run while the file has a readable line
                       for (int i = 0; i < depots.length;){ // loops through depots                
                           if (depots[i].getDepotName().equals("") && !depots[i].getDepotName().equals(tempName)){// if the depot name is ""                     
                               depots[i].setDepotName(inputStream.next());
                               tempName = depots[i].getDepotName();
                               depots[i].importDetails(depots, inputStream, i);
                            }else if (depots[i].getDepotName().equals(tempName)){ // if the second line has the same depot name
                                tempName = inputStream.next();
                                if(depots[i].getDepotName().equals(tempName)){
                                    depots[i].importDetails(depots, inputStream, i);
                                }else{ // the depot name is different, i goes up 1 and starts again for the new depot
                                    i++;
                                    depots[i].setDepotName(tempName);
                                    depots[i].importDetails(depots, inputStream, i);
                                }                        
                          }else{
                              i++; 
                          }                        
                       } 
                    }
                   inputStream.close();     
                }
                catch (Exception e){
                    System.out.println("Error opening file");            
                }
          break;
          
          case 11:
                System.exit(0);
          default: 
                System.out.println("Invalid selection");
          break;
          
        }        
      }
      while (option !=11);

   }
   public static void main(String[] args) {
    Interface intFace = new Interface();
    intFace.run();
   }
   
   public boolean checkName1(Depot [] depots, String name){ //boolean check name for no index
     boolean check = false;
      for (int i = 0; i < depots.length; i++){ // loops through the array and stops if name matches any other depot name
          if(depots[i].getDepotName().equals(name)){
          return true;
        }
      }
      return check;
   }
  
   public int checkName(Depot [] depots, String name, int a){ // check name for add product ** returns index
       for (int i = 0; i < depots.length+1; i++){ // loops through the array and stops if name matches any other depot name
        if (i == depots.length){
            System.out.println("Depots you entered does not exist");
            a = -1;
            return a; 
        }
        if(depots[i].getDepotName().equals(name)){
            a = i;
            return a;
        }
      }
       return 5;            
    }
   
   public boolean checkDepots(Depot [] depots){ 
     boolean check = false;
     for(int i = 0; i < depots.length; i++){
         if (depots[i].getDepotName().equals("")){
         return true;         
        }
    }
    return check;
   }
   
    public void menu(){
      System.out.println("\n(1) Add depot");
      System.out.println("(2) Remove a depot");
      System.out.println("(3) Add a product");
      System.out.println("(4) Remove a product");
      System.out.println("(5) Query depots");
      System.out.println("(6) Query products in a depot");
      System.out.println("(7) Find a product");
      System.out.println("(8) Depot cumulative value");
      System.out.println("(9) Export depot and product information");
      System.out.println("(10) Import depot and product information");
      System.out.println("(11) Exit");
   }
  
}