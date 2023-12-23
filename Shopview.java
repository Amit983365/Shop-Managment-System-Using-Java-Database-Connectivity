package com.qsp.shop.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.qsp.shop.controller.ShopController;
import com.qsp.shop.model.Product;

public class Shopview {
	 static Scanner myInput=new Scanner(System.in);
	 static Product product=new Product();
	 static ShopController shopController=new ShopController();
     
	 public static void main(String[] args) {
		do {
			System.out.println("Select Operation to perform : ");
			System.out.println("1.Add product\n2.Remove Product\n3.Update Product details\n4.fetch product\n0.exit");
			System.out.print("Enter digit respective to desired option: ");
			int userInput=myInput.nextInt();
			myInput.nextLine();
			switch (userInput) {
			case 0:
				myInput.close();
				System.out.println("------Exited------");
				System.exit(0);
				break;
				
            case 1:
            	System.out.print("How many products you want to add? \n1.Single product\n2.Multiple products");
            	int productsCount=myInput.nextInt();
            	myInput.nextLine();
            	if(productsCount==1) {
            		System.out.print("Enter product id : ");
    				int i_p_id=myInput.nextInt();
    				myInput.nextLine();
    				System.out.print("Enter product name : ");
    				String i_p_name=myInput.nextLine();
    				System.out.print("Enter price : ");
    				int i_p_price=myInput.nextInt();
    				myInput.nextLine();
    				System.out.print("Enter Quantity : ");
    				int i_p_quantity=myInput.nextInt();
    				myInput.nextLine();
    				boolean i_p_availability=false;
    				if (i_p_quantity>0) {
    					i_p_availability=true;
    				} 
    				if ((shopController.addProduct(i_p_id, i_p_name, i_p_price, i_p_quantity, i_p_availability))!= 0){
    						System.out.println("product added");
    					}else {
    						System.out.println("Product not added");
    					}	
    				} else {
    					boolean toContinue=true;
    					ArrayList<Product> products=new ArrayList<Product>();
    					do {
    						Product product=new Product();
    						System.out.print("enter id :");
    						product.setP_id(myInput.nextInt());
    						myInput.nextLine();
    						System.out.print("enter name :");
    						product.setP_name(myInput.nextLine());
    						System.out.print("enter price :");
    						product.setP_price(myInput.nextInt());
    						myInput.nextLine();
    						System.out.print("enter quantity :");
    						int quantity=myInput.nextInt();
    						product.setP_quantity(quantity);
    						myInput.nextLine();
    						boolean i_p_availability=false;
    						if(quantity>0) {
    							i_p_availability=true;
    						}
    						product.setP_availability(i_p_availability);
    						products.add(product);
    						System.out.print("Press 1 to continue adding products,Press 0 to stop adding products : ");
    						int toAdd=myInput.nextInt();
    						myInput.nextLine();
    						if(toAdd==0) {
    							toContinue=false;
    						}
    					}while(toContinue);
    					shopController.addMultipleProducts(products);
            	}
			    break;
				
            case 2:
				  System.out.println("Enter product Id to remove : ");
				  int productIdToRemove=myInput.nextInt();
				  myInput.nextLine();
				  if (shopController.removeProduct(productIdToRemove)!=0) {
					System.out.println("Ha Delete ho gaya");
				
				} else {
					System.out.println("Product with given id does not exists, No remove operation performed");
				}
				System.out.println();
				break;
  				
            case 3:
				System.out.println("Enter Product id to update : ");
				int productIdToUpdate=myInput.nextInt();
				myInput.nextLine();
				ResultSet product=shopController.fetchProduct(productIdToUpdate);
				try {
					if (product.next()) {
						System.out.println("What you want to update");
						System.out.println("1.Name\n2.Price\n3.Quantity");
						System.out.println("Enter number respective to desired option : ");
						byte updateOption=myInput.nextByte();
						myInput.nextLine();
						switch (updateOption) {
						case 1:
							System.out.print("Enter name to update : ");
							String nameToUpdate=myInput.nextLine();
							if (shopController.updateProductName(productIdToUpdate, nameToUpdate)!=0) {
								System.out.println("Records updates");
							} else {
					            System.out.println("Records not updated");
							}
							break;
					    case 2:
					    	System.out.print("Enter price to update : ");
							int priceToUpdate=myInput.nextInt();
							if (shopController.updateProductPrice(productIdToUpdate, priceToUpdate)!=0) {
								System.out.println("Records updates");
							} else {
					            System.out.println("Records not updated");
							}
							
							break;
					    case 3:
					    	System.out.print("Enter Quantity to update : ");
							int quantityToUpdate=myInput.nextInt();
							if (shopController.updateProductPrice(productIdToUpdate,quantityToUpdate)!=0) {
								System.out.println("Records updates");
							} else {
					            System.out.println("Records not updated");
							}
							break;

						default:
							System.out.println("Invalid selection");
							break;
						}
					} else {
					   System.out.println("Product with given id does not exist, update operation performed");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    break;
				
            case 4:
				System.out.print("Enter Product id to fetch : ");
				int productIdToFind=myInput.nextInt();
				myInput.nextLine();
				ResultSet fetchProduct=shopController.fetchProduct(productIdToFind);

				
				boolean next = false;
				try {
					next = fetchProduct.next();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (next) {
					System.out.println("PRODUCT DETAILS");
					try {
						System.out.println("Id : " +fetchProduct.getInt(1));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						System.out.println("Name : "+fetchProduct.getString(2));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						System.out.println("Price : "+fetchProduct.getInt(3));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						System.out.println("Quantity : "+fetchProduct.getInt(4));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					  try {
						if (fetchProduct.getBoolean(5)) {
							System.out.println("Availability : Available");
						} else {
							System.out.println("Availability : Not Available");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
                    System.out.println("Product with id : "+ productIdToFind+"does not exist");
                    System.out.println();
				}
				break;

			default:
				System.out.println("------INVALID SELECTED------");
				break;
			}
		}while(true);
	}
}
