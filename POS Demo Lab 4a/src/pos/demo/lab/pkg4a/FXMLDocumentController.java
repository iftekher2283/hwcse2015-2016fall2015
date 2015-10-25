/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pos.demo.lab.pkg4a;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author kmhasan
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField productIDField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productCategoryField;
    @FXML
    private TextField unitPriceField;
    private Product products[];
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        products = new Product[100];
    }    

    @FXML
    private void handleSaveAction(ActionEvent event) {
        int id = Integer.parseInt(productIDField.getText());
        String name = productNameField.getText();
        String category = productCategoryField.getText();
        double price = Double.parseDouble(unitPriceField.getText());
        
        Product p = new Product(id, name, category, price);
        
        try {
            RandomAccessFile output = new RandomAccessFile("product.txt", "rw");
            output.seek(output.length());
            output.writeBytes(p.getId() + "\n");
            output.writeBytes(p.getName() + "\n");
            output.writeBytes(p.getCategory() + "\n");
            output.writeBytes(p.getPrice() + "\n");
            
            productIDField.setText("");
            productNameField.setText("");
            productCategoryField.setText("");
            unitPriceField.setText("");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleLoadAction(ActionEvent event) {
        try {
            RandomAccessFile input = new RandomAccessFile("product.txt", "r");
            String line;
            
            for (int i = 0; ; i++) {
                line = input.readLine();
                if (line == null)
                    break;
                int id = Integer.parseInt(line);
                line = input.readLine();
                String name = line;
                line = input.readLine();
                String category = line;
                line = input.readLine();
                double price = Double.parseDouble(line);

                Product p = new Product(id, name, category, price);
                products[i] = p;
            }
            productIDField.setText(products[0].getId() + "");
            productNameField.setText(products[0].getName());
            productCategoryField.setText(products[0].getCategory());
            unitPriceField.setText(products[0].getPrice() + "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleNewAction(ActionEvent event) {
        productIDField.setText("");
        productNameField.setText("");
        productCategoryField.setText("");
        unitPriceField.setText("");
    }

    @FXML
    private void handlePreviousAction(ActionEvent event) {
        int pr_id = Integer.parseInt(productIDField.getText());
        String pr_name = productNameField.getText();
        String pr_category = productCategoryField.getText();
        double pr_unit_price = Double.parseDouble(unitPriceField.getText());
        
        Product pr_product = new Product(pr_id, pr_name, pr_category, pr_unit_price);
        if (pr_product != null){
            try{
                RandomAccessFile input = new RandomAccessFile("product.txt", "r");
                String line;

                for (int i = 0; ; i++) {
                    line = input.readLine();
//                    if (line == null)
//                        break;
                    int id = Integer.parseInt(line);
                    line = input.readLine();
                    String name = line;
                    line = input.readLine();
                    String category = line;
                    line = input.readLine();
                    double price = Double.parseDouble(line);

                    Product p = new Product(id, name, category, price);
                    products[i] = p;
                    if(products[i].getId() == pr_product.getId() && i > 0){
                        productIDField.setText(products[i-1].getId() + "");
                        productNameField.setText(products[i-1].getName());
                        productCategoryField.setText(products[i-1].getCategory());
                        unitPriceField.setText(products[i-1].getPrice() + "");
                        break;
                    }
//                    else if(products[0] == pr_product){
//                        productIDField.setText(products[0].getId() + "");
//                        productNameField.setText(products[0].getName());
//                        productCategoryField.setText(products[0].getCategory());
//                        unitPriceField.setText(products[0].getPrice() + "");
//                        break;
//                    }
                }
            }catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleNextAction(ActionEvent event) {
        int pr_id = Integer.parseInt(productIDField.getText());
        String pr_name = productNameField.getText();
        String pr_category = productCategoryField.getText();
        double pr_unit_price = Double.parseDouble(unitPriceField.getText());
        
        Product pr_product = new Product(pr_id, pr_name, pr_category, pr_unit_price);
        if (pr_product != null){
            try{
                RandomAccessFile input = new RandomAccessFile("product.txt", "r");
                String line;

                for (int i = 0; ; i++) {
                    line = input.readLine();
//                    if (line == null)
//                        break;
                    int id = Integer.parseInt(line);
                    line = input.readLine();
                    String name = line;
                    line = input.readLine();
                    String category = line;
                    line = input.readLine();
                    double price = Double.parseDouble(line);

                    Product p = new Product(id, name, category, price);
                    products[i] = p;
                    if(products[i].getId() == pr_product.getId() && i < products.length){
                        line = input.readLine();
                        int next_id = Integer.parseInt(line);
                        line = input.readLine();
                        String next_name = line;
                        line = input.readLine();
                        String next_category = line;
                        line = input.readLine();
                        double next_unit_price = Double.parseDouble(line);
                        Product next_product = new Product(next_id, next_name, next_category, next_unit_price);
                        products[i+1] = next_product;
                        productIDField.setText(products[i+1].getId() + "");
                        productNameField.setText(products[i+1].getName());
                        productCategoryField.setText(products[i+1].getCategory());
                        unitPriceField.setText(products[i+1].getPrice() + "");
                        break;

                    }
//                    else if(products[0] == pr_product){
//                        productIDField.setText(products[0].getId() + "");
//                        productNameField.setText(products[0].getName());
//                        productCategoryField.setText(products[0].getCategory());
//                        unitPriceField.setText(products[0].getPrice() + "");
//                        break;
//                    }
                }
            }catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
