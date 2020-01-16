// Program written by Keeylan Hume Assignment 2
// Student ID c33020396
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class Depot 
     {
      private String name, tempName; // variable to hold name
      private Product [] products = new Product[5];
     public Depot(String name){ 
       this.name = "";
        for (int i = 0; i < products.length; i++){
            products[i] = new Product();   
        }
     }  
     
     public static void addDepot(Depot [] depots, String name){ // Method to add a depot
         for(int i = 0; i < depots.length; i++){ // loops through the depots
             if(depots[i].getDepotName().equals("")){ // if a name is "" sets the depot to name inputted
                 depots[i].setDepotName(name); 
                 break;
             }
         }
     } 
     
     public void removeDepot(Depot [] depots, String name){
         for (int i = 0; i < depots.length; i++){ // loops through the depots
             if (depots[i].getDepotName().equals(name)){ // if name matches the depot set it to ""
                 depots[i].setDepotName("");
                 break;
             }
         }      
     }        
      // Sets depots name
     public void setDepotName(String n){
        name = n;
     } 
     
      // Gets depots name
     public String getDepotName(){
        return name;   
     }
     
     public void copyItem(Depot [] depots, String productName, String name, int t, int quantity){ // copies information of an item if it already exists in another depot
         addloop:
         for(int i = 0; i < depots.length; i++){  // loops through depots           
             for (int p = 0; p < products.length; p++){ // loops through the products in depot i
                 if (depots[i].products[p].getName().equals(productName)){ // if productname matches  
                     for (int l = 0; l < products.length; l++){  // loop through the products again
                      if (depots[t].products[l].getName().equals("")){ // making sure depot t "that has been passed in" is not full
                         depots[t].products[l].setWeight(depots[i].products[p].getWeight()); // Copies weight from product p
                         depots[t].products[l].setPrice(depots[i].products[p].getPrice());   // Copies price from product p
                         depots[t].products[l].setName(productName);                         // sets the new productname from user input
                         depots[t].products[l].setQuantity(quantity);                        // sets the new quantity from user input
                         System.out.println("Adding additional items of " +products[l].getName() + " into " +depots[t].getDepotName());
                         break addloop;
                        }else if (l == products.length-1){ 
                         System.out.println(depots[t].getDepotName() + " is full");   
                        }
                    }
                 }                
             } 
         }
     }
      
     public void setProduct(Depot [] depots,String name, String productName, double price, double weight, int quantity, int a){           
              for (int p = 0; p < products.length; p++){ // loops through the products
                  if (depots[a].products[p].getName().equals("")){ // runs if one of the product spot is empty and add the information into slots
                      depots[a].products[p].setName(productName);
                      depots[a].products[p].setPrice(price);
                      depots[a].products[p].setWeight(weight);
                      depots[a].products[p].setQuantity(quantity);
                      break;
                  }else if (p == products.length-1){ // if there are already 4 products in the matching depot
                    System.out.println("You're depot is full");                      
                  }
        }
     }
        
     public boolean checkForProduct(Depot [] depots, String productName, int i){
       for(int p = 0; p < products.length; p++){
           if (depots[i].products[p].getName().equals(productName)){ // check if the product exists in the depot[i]
               return true;                
           }
       }
       return false;         
     }

     public String printDetails(Depot [] depots, String productName, int i){          
        for(int p = 0; p < products.length; p++){
             if (depots[i].products[p].getName().equals(productName)){ // print the details of the specified product
                 System.out.println(productName + " is in " +depots[i].getDepotName() + " price " + products[p].getPrice() + " weight " + products[p].getWeight());
            }           
        } 
        return "";
     }
     
     public void addQuantity(Depot[] depots, String productName, int i, int quantity){
         for (int p = 0; p < products.length; p++){
             if (depots[i].products[p].getName().equals(productName)){ // if the product name matches add the quantity specified
                 products[p].addQuantity(quantity);
                 System.out.println("Adding additional items");
             }
         }
     }
     
     public void removeQuantity(Depot[] depots, String productName, int i, int quantity){
         for (int p = 0; p < products.length; p++){
             if(depots[i].products[p].getName().equals(productName)){ // removes the specified quantity
                 int a = 0;
                 a = products[p].removeQuantity(quantity,a);
                 if (a < 0){ // breaks from error removing more then the possible amount
                     break;
                    }
                 System.out.println("Removing " + quantity + " " + products[p].getName());
                 if(products[p].getQuantityOnHand() <= 0){ // if the quantity will be less then 0 ... delete the whole product from the depot
                     System.out.println(products[p].getName() + "has been completely removed from " + depots[i].getDepotName());
                     depots[i].products[p].setName("");
                     depots[i].products[p].setPrice(0);
                     depots[i].products[p].setWeight(0);
                     depots[i].products[p].setQuantity(0);
                     int index = p; // index to hold the position of the deleted product
                     for (p = index; p < products.length - 1; p++){ // uses p and moves other objects across in the array
                        products[p] = products[p+1];                          
                     }                    
                 }
             }
         }    
     }
        
     public boolean checkDepot(Depot [] depots, int i){  // checks if a depot is empty or not with a boolean method       
         for (int p = 0; i < products.length; i++){
             if (depots[i].products[p].getName().equals("")){
             return true;
            }
         }
         return false;
     }
     
     public void numberOfProducts(Depot [] depots, int i){ // method for printing depot name with how many products it holds
         int number = 0;
         for(int p = 0, l = 0; p < products.length;p++){
             if(!depots[i].products[p].getName().equals("")){
              l++;              
             }
             number = l;
         }
         System.out.println(depots[i].getDepotName() + " has " + number + " products");
         
     }
        
     public void printProducts(Depot [] depots, int a){ // prints all the products within the specified depot
         for (int p = 0; p < products.length; p++){
             if(!depots[a].products[p].getName().equals("")){
             System.out.println("Product " + depots[a].products[p].getName() + " has a price of $" + depots[a].products[p].getPrice() + " weight " +depots[a].products[p].getWeight() +"kg, and quantity " + depots[a].products[p].getQuantityOnHand());             
            }
         }
     }
     
     public void productQuery(Depot [] depots, String productName, int i){ // method for finding a product in any depot 
         for (int p = 0; p < products.length; p++){
            if (depots[i].products[p].getName().equals(productName)){
               System.out.println("Product " +products[p].getName() +  " is in depot " +depots[i].getDepotName() + " with quantity " +products[p].getQuantityOnHand());  
            }        
         }
     }
     
     public void depotValue(Depot [] depots, int a){ // method for totalling the depot value
        int total = 0;
        for (int p = 0; p < products.length; p++){ // loops through the products of depot[a] and calculates the total value of depot
            if (!depots[a].products[p].getName().equals("")){
             total += products[p].getItemValue();                   
            }            
        } 
        System.out.println("Depot " + depots[a].getDepotName() + " has a cumulative product value of $" +total);
     }
     
     public void addDetails(Depot[] depots,PrintWriter outputStream, int a){ // Method for exporting program information to a txt file        
         for (int p = 0; p < products.length; p++){
            if(!depots[a].products[p].getName().equals("")){
             outputStream.println(depots[a].getDepotName() + " " + products[p].getName()+ " " + products[p].getPrice()+ " " + products[p].getWeight()+ " " + products[p].getQuantityOnHand());
            }else if (depots[a].products[0].getName().equals("")){ // if the depot is empty, only print the depotname on its own line
             outputStream.println(depots[a].getDepotName());
             break;
            }
        }
     }
     
     public void importDetails(Depot [] depots, Scanner inputStream, int i){
         for (int p = 0; p < products.length;){ // loops through products
             tempName = inputStream.next(); // sets tempname to the product name in file         
                if (products[p].getName().equals("")){ // product p is empty                                         
                    depots[i].products[p].setName(tempName);
                    depots[i].products[p].setPrice(inputStream.nextDouble());
                    depots[i].products[p].setWeight(inputStream.nextDouble());
                    depots[i].products[p].setQuantity(inputStream.nextInt());           
                    break;
                }   else if (products[p].getName().equals(tempName)){ // trying to add the same product, technically it ignores the lines and adds the new quantity
                        depots[i].products[p].setPrice(inputStream.nextDouble());
                        depots[i].products[p].setWeight(inputStream.nextDouble()); 
                        depots[i].products[p].addQuantity(inputStream.nextInt());             
                }
                else{
                    p++;
                }
            }
     }
          
     public int productCheck(Depot [] depots, String productName, int l){ // for case 7, check how many specified products are in depots  
         for (int k = 0; k < depots.length; k++){
           for(int i = 0; i < products.length; i++){
             if(depots[k].products[i].getName().equals(productName)){
                l++; 
                }  
            } 
         }
         return l;
     }
  }