package bootcamp.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import bootcamp.model.products.Product;

@Component
public class ProductDao {
	
	private final String GET_PRODUCTS = "SELECT id, name, description, retail_price, wholesale_price FROM product";
    private final String GET_PRODUCT_PRICE = "SELECT retail_price FROM product where id = ?";
	private static final BigDecimal retailMultiplier = new BigDecimal(2.5); 
    
	private final String WHERE_ID = " where id = ?";
	private final String GET_PRODUCT_BY_ID_SQL = GET_PRODUCTS + WHERE_ID;
    private final String UPDATE_PRODUCT_PRICE = "UPDATE product SET retail_price=?, wholesale_price=?";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Value("#{'${listOfThings}'.split(',')}")
	private List<String> listOfThings;
	
	@Autowired
	private List<Product> items;
	
	public List<Product> getProducts() {
		return jdbcTemplate.query(GET_PRODUCTS, new BeanPropertyRowMapper<>(Product.class));
	}
	
	public Product getProductById(Integer id) {
		return jdbcTemplate.queryForObject(GET_PRODUCT_BY_ID_SQL, new Object[] {id}, new BeanPropertyRowMapper<>(Product.class));
	}
	
	public void updateProductPrice(List<Product> products) {
		for (Product p : products) {
			jdbcTemplate.update(UPDATE_PRODUCT_PRICE + WHERE_ID, new Object[] {p.getRetail_price().multiply(retailMultiplier), p.getRetail_price(), p.getId()});
//			p.setRetail_price(p.getWholesale_price().multiply(retailMultiplier));
		}
	}
	
	public List<String> getListOfThings(){
		return listOfThings;
	}
	
	public List<Product> getListOfProducts(){
		return items;
	}
	
	// update product in table with new data call, set new retail price on the way in
	public void updateProductList(List<Product> productList) { 
		for (Product p : productList) {
			p.setRetail_price(p.getWholesale_price().multiply(retailMultiplier)); //set new retail price
		}
	}
	
	public double getPriceOfProduct(int id) {
		List<Double> temp = jdbcTemplate.queryForList(GET_PRODUCT_PRICE, new Object[] {id}, Double.class);
		return temp.get(0);
	}
	
}
