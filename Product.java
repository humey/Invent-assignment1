// Program written by Keeylan Hume Assignment 2
// Student ID c33020396
public class Product
{
    private String name; // variable to hold name
    private double price; // variable to hold price
    private double weight; // variable to hold weight
    private int quantity, updatedQuantity; // variable to hold quantity and updated quantity when adding

    //Constructer to make product default values
    public Product(){
      name = "";
      price = 0;
      weight = 0;
      quantity = 0;
    }
    // Method to get products name
    public String getName(){
        return name;
    }
    // Method to set the name variable
    public void setName(String n){
        name = n;
    }
    // Method to set the price variable
    public void setPrice(double p){
        price = p;
    }
    // Method to get products price
    public double getPrice(){
        return price;
    }
    // Method to set the quantity
    public void setQuantity(int quantityOnHand){
    if (quantityOnHand > 0){
        quantity = quantityOnHand;
    }
      else{
            quantityOnHand = 0;
        }
    }
    // Method to get product quantity
    public int getQuantityOnHand(){
     return quantity;   
    }
    // Method to return products item value
    public double getItemValue(){
     return (price * (double)(quantity)); 
    }
    // Method to set the products weight
    public void setWeight(double w){
    weight = w;   
    }
    // Method to get products weight
    public double getWeight(){
    return weight;
    }    
    // Method to add extra quantity to products
    public void addQuantity(int newQuantity){
    updatedQuantity = quantity + newQuantity;
    quantity = updatedQuantity;       
    }
    // Method to remove quantity from product
    public int removeQuantity(int newQuantity, int a){
       if(newQuantity > quantity){
           System.out.println("You cant remove more then what is in your depot");
           a = -1;
           return a;
       }else{        
           updatedQuantity = quantity - newQuantity;
           quantity = updatedQuantity;
        }
        return a;
        }
    }