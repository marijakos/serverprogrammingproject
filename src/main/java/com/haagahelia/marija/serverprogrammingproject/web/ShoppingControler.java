package com.haagahelia.marija.serverprogrammingproject.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.haagahelia.marija.serverprogrammingproject.domain.NewProductForm;
import com.haagahelia.marija.serverprogrammingproject.domain.Product;
import com.haagahelia.marija.serverprogrammingproject.domain.ProductRepository;
import com.haagahelia.marija.serverprogrammingproject.domain.ShoppingList;
import com.haagahelia.marija.serverprogrammingproject.domain.ShoppingListForm;
import com.haagahelia.marija.serverprogrammingproject.domain.ShoppingListProduct;
import com.haagahelia.marija.serverprogrammingproject.domain.ShoppingListProductRepository;
import com.haagahelia.marija.serverprogrammingproject.domain.ShoppingListRepository;
import com.haagahelia.marija.serverprogrammingproject.service.UserDetailServiceImpl;

@Controller
public class ShoppingControler {
    private static final Logger log = LoggerFactory.getLogger(ShoppingControler.class);
    
    @Autowired
    private ProductRepository productRepository;
 
    @Autowired
    private ShoppingListProductRepository interRepository;
    
    @Autowired
    private ShoppingListRepository listRepository;
    
    @Autowired
    private UserDetailServiceImpl userDetails; 
    
    // Show all products for logged user
    @RequestMapping(value="/shopping")
    public String userList(Model model) {  
        addDefaultAttributesToModel(model);
        return "shopping";
    }    
    
    // Show all products for logged user
    @RequestMapping(value="/createShoppingList")
    public String createShoppingList(Model model) { 
        ShoppingList shoppingList = new ShoppingList(userDetails.getLoggedUserId());
        listRepository.save(shoppingList);
        addDefaultAttributesToModel(model);
        return "shopping";
    }    
    
    // Show all products for logged user
    @RequestMapping(value="/shopping/saveproduct", method = RequestMethod.POST)
    public String saveProduct(Model model, 
            @Valid @ModelAttribute("newProduct") NewProductForm newProductForm, 
            BindingResult bindingResult) {
        
        if (!bindingResult.hasErrors()) { // validation errors
            Long userId = userDetails.getLoggedUserId();
            Product newProduct = new Product();
            newProduct.setUserId(userId); 
            newProduct.setName(newProductForm.getProductName());
            // Check if product already exists
            if (productRepository.findByUserIdAndName(userId, newProductForm.getProductName()) == null) { 
                productRepository.save(newProduct);
            }
            else {
                bindingResult.rejectValue("productName", "err.productName", "Product name already exists");       
            }
        }
        addDefaultAttributesToModel(model);
        return "shopping";
    }    

    // Delete selected products
    @RequestMapping(value="/shopping/moveproduct", params = "delete", method = RequestMethod.POST)
    public String deleteProduct(Model model, @RequestParam(value="products") List<String> products) {
        for (String productId: products) {
            productRepository.deleteById(Long.valueOf(productId));
        }
        addDefaultAttributesToModel(model);
        return "shopping";
    }
    
    // Move selected products to shopping list
    @RequestMapping(value="/shopping/moveproduct", params = "move", method = RequestMethod.POST)
    public String moveProduct(Model model, @RequestParam(value="products") List<String> products) {
        Long userId = userDetails.getLoggedUserId();
        Optional<ShoppingList> result = listRepository.findTopByUserIdOrderByShoppingListIdDesc(userId);
        if (result.isPresent()) {
            for (String productId: products) {
                productRepository.findById(Long.valueOf(productId));
                ShoppingListProduct slp = new ShoppingListProduct(result.get().getShoppingListId(), 
                        Long.valueOf(productId), 1.f);
                interRepository.save(slp);
            }        
        }
        addDefaultAttributesToModel(model);
        return "shopping";
    }
 
    // Delete product from shopping list    
    @RequestMapping(value = "/shoppingdelete/{id}", method = RequestMethod.GET)
    public String deleteProductFromShoppingList(@PathVariable("id") Long productId, Model model) {
        try {
            interRepository.deleteById(productId);
        } catch (Exception e) {
        }
        addDefaultAttributesToModel(model);
        return "shopping";
    } 

    // save product list (quantity changed from default (1) by user)
    @RequestMapping(value = "/shopping/shoppinglist", method = RequestMethod.POST)
    public String saveProductsFromShoppingList(Model model, @RequestParam Map<String,String> allRequestParams) {
        for (String key: allRequestParams.keySet()) {
            if (key.startsWith("productKey")) {
                String oldKey = key;
                try {
                    Long slpId = Long.valueOf(key.replaceAll("productKey", ""));
                    Optional<ShoppingListProduct> slp = interRepository.findById(slpId);
                    if (slp.isPresent()) {
                        slp.get().setQuantity(Float.valueOf(allRequestParams.get(oldKey)));
                        interRepository.save(slp.get());
                    }
                } catch (Exception e) {
                    log.debug(e.toString());
                }
            }
        }
        addDefaultAttributesToModel(model);
        return "shopping";
    } 
    
    // add default attributes to model
    private void addDefaultAttributesToModel(Model model) {
        Long userId = userDetails.getLoggedUserId();
    	// find current shopping list for currently logged user
        Optional<ShoppingList> result = listRepository.findTopByUserIdOrderByShoppingListIdDesc(userId);
        List<ShoppingListForm> formList = new ArrayList<>();
        if (result.isPresent()) {
            List<ShoppingListProduct> list = interRepository.findByShoppingListId(result.get().getShoppingListId());
            for (ShoppingListProduct item: list) {
                Optional<Product> product = productRepository.findById(item.getProductId());
                if (product.isPresent()) {
                    formList.add(new ShoppingListForm(item.getShoppingListProductId(), product.get().getName(), item.getQuantity()));
                }
            }
        }
        
        model.addAttribute("products", productRepository.findByUserId(userId));
        model.addAttribute("shoppingList", formList);
        model.addAttribute("loggedUser", userDetails.getLoggedUserFullName());        
    }
}
