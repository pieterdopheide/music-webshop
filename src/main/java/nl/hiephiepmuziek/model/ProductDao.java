package nl.hiephiepmuziek.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// Enum singleton
public enum ProductDao {
	instance;
	
	private Map<Integer, Product> products = new HashMap<>();
	
	private ProductDao() {
		Connection db = null;
        
        try {
        	Context initContext = new InitialContext();
        	Context envContext  = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/hiephiepmuziek");
            db = ds.getConnection();
        	
            PreparedStatement st = db.prepareStatement("SELECT * FROM products");
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Product e = getFromResultSet(rs);
                products.put(e.getId(), e);
            }
            
        } catch (SQLException sqle){
        	sqle.printStackTrace();
        } catch (NamingException ne) {
			ne.printStackTrace();
		} finally {
       		try {
       			if (db != null) {
       				db.close();
       			}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
        }
	}
	
	public Map<Integer, Product> getModel() {
		return products;
	}
	
	protected Connection getConnection() throws SQLException, NamingException {
        InitialContext ic = new InitialContext();
        DataSource ds = (DataSource) ic.lookup("jdbc/hiephiepmuziek");
        return ds.getConnection();
    }
	
	public Product getFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setSnippet(rs.getString("snippet"));
        product.setImageurl(rs.getString("imageurl"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setStock(rs.getInt("stock"));
        return product;
    }

}
