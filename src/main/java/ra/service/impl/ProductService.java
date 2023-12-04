package ra.service.impl;

import org.springframework.stereotype.Service;
import ra.model.Product;
import ra.service.IGenericService;
import ra.ultil.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService implements IGenericService<Product,Integer> {

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection conn =null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt =conn.prepareCall("{call getAllProducts}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setImage_url(rs.getString("image_url"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setCatalog(rs.getInt("catalog_id"));
                product.setSize(rs.getInt("size_id"));
                product.setBrand(rs.getInt("brand_id"));
                product.setColor(rs.getInt("color_id"));
                product.setUser_object(rs.getInt("object_id"));
                product.setImport_date(rs.getDate("import_date"));
                product.setImport_price(rs.getDouble("import_price"));
                product.setExport_price(rs.getDouble("export_price"));
                product.setStatus(rs.getBoolean("status"));
                products.add(product);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return products;
    }

    public List<Product> getFivedProducts(){
        List<Product> products = new ArrayList<>();
        Connection conn =null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt =conn.prepareCall("{call getFirstFiveProducts}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setImage_url(rs.getString("image_url"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setCatalog(rs.getInt("catalog_id"));
                product.setSize(rs.getInt("size_id"));
                product.setBrand(rs.getInt("brand_id"));
                product.setColor(rs.getInt("color_id"));
                product.setUser_object(rs.getInt("object_id"));
                product.setImport_date(rs.getDate("import_date"));
                product.setImport_price(rs.getDouble("import_price"));
                product.setExport_price(rs.getDouble("export_price"));
                product.setStatus(rs.getBoolean("status"));
                products.add(product);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return products;
    }

    public List<Product> getPageProducts(int id) {
        List<Product> products = new ArrayList<>();
        Connection conn =null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt =conn.prepareCall("{call GetPagedProducts(?)}");
            callSt.setInt(1,id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setImage_url(rs.getString("image_url"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setCatalog(rs.getInt("catalog_id"));
                product.setSize(rs.getInt("size_id"));
                product.setBrand(rs.getInt("brand_id"));
                product.setColor(rs.getInt("color_id"));
                product.setUser_object(rs.getInt("object_id"));
                product.setImport_date(rs.getDate("import_date"));
                product.setImport_price(rs.getDouble("import_price"));
                product.setExport_price(rs.getDouble("export_price"));
                product.setStatus(rs.getBoolean("status"));
                products.add(product);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return products;
    }

    @Override
    public Product findById(Integer id) {
        Product product = null;
        Connection conn =null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call findProductById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setImage_url(rs.getString("image_url"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setCatalog(rs.getInt("catalog_id"));
                product.setSize(rs.getInt("size_id"));
                product.setBrand(rs.getInt("brand_id"));
                product.setColor(rs.getInt("color_id"));
                product.setUser_object(rs.getInt("object_id"));
                product.setImport_date(rs.getDate("import_date"));
                product.setImport_price(rs.getDouble("import_price"));
                product.setExport_price(rs.getDouble("export_price"));
                product.setStatus(rs.getBoolean("status"));
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return product;
    }

    @Override
    public void save(Product product) {
        Connection conn = null;
        try {
            if(product.getId()==0){
                conn = ConnectDB.getConnection();
                CallableStatement callSt = conn.prepareCall("{call insertProduct(?,?,?,?,?,?,?,?,?,?,?)}");
                callSt.setString(1,product.getProduct_name());
                callSt.setString(2,product.getImage_url());
                callSt.setString(3,product.getDescription());
                callSt.setInt(4, product.getStock());
                callSt.setInt(5,product.getCatalog());
                callSt.setInt(6,product.getSize());
                callSt.setInt(7,product.getBrand());
                callSt.setInt(8,product.getColor());
                callSt.setInt(9,product.getUser_object());
                callSt.setDouble(10,product.getImport_price());
                callSt.setDouble(11,product.getExport_price());
                callSt.executeUpdate();

            }else {
                conn = ConnectDB.getConnection();
                CallableStatement callSt = conn.prepareCall("{call updateProduct(?,?,?,?,?,?,?,?,?,?,?,?)}");
                callSt.setInt(1,product.getId());
                callSt.setString(2,product.getProduct_name());
                callSt.setString(3,product.getImage_url());
                callSt.setString(4,product.getDescription());
                callSt.setInt(5, product.getStock());
                callSt.setInt(6,product.getCatalog());
                callSt.setInt(7,product.getSize());
                callSt.setInt(8,product.getBrand());
                callSt.setInt(9,product.getColor());
                callSt.setInt(10,product.getUser_object());
                callSt.setDouble(11,product.getImport_price());
                callSt.setDouble(12,product.getExport_price());
                callSt.executeUpdate();
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call deleteProduct(?)}");
            callSt.setInt(1,id);
            callSt.executeUpdate();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }


    public List<Product> searchProduct(String keyWord){
        Connection conn = null;
        List<Product> listSearch = new ArrayList<Product>();
        try {
            conn = ConnectDB.getConnection();
            CallableStatement call = conn.prepareCall("{call searchProduct(?)}");
            call.setString(1, keyWord);
            ResultSet rs = call.executeQuery();
            while(rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setImage_url(rs.getString("image_url"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setCatalog(rs.getInt("catalog_id"));
                product.setSize(rs.getInt("size_id"));
                product.setBrand(rs.getInt("brand_id"));
                product.setColor(rs.getInt("color_id"));
                product.setUser_object(rs.getInt("object_id"));
                product.setImport_date(rs.getDate("import_date"));
                product.setImport_price(rs.getDouble("import_price"));
                product.setExport_price(rs.getDouble("export_price"));
                product.setStatus(rs.getBoolean("status"));
                listSearch.add(product);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return listSearch;
    }

    public List<Product> getProductsByObject(int id){
        Connection conn = null;
        List<Product> list = new ArrayList<Product>();
        try {
            conn = ConnectDB.getConnection();
            CallableStatement call = conn.prepareCall("{call getProductByObjecj(?)}");
            call.setInt(1, id);
            ResultSet rs = call.executeQuery();
            while(rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setImage_url(rs.getString("image_url"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setCatalog(rs.getInt("catalog_id"));
                product.setSize(rs.getInt("size_id"));
                product.setBrand(rs.getInt("brand_id"));
                product.setColor(rs.getInt("color_id"));
                product.setUser_object(rs.getInt("object_id"));
                product.setImport_date(rs.getDate("import_date"));
                product.setImport_price(rs.getDouble("import_price"));
                product.setExport_price(rs.getDouble("export_price"));
                product.setStatus(rs.getBoolean("status"));
                list.add(product);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }


    public List<Product> filterProduct(double min, double max){
        Connection conn = null;
        List<Product> list = new ArrayList<Product>();
        try {
            conn = ConnectDB.getConnection();
            CallableStatement call = conn.prepareCall("{call filterProducf(?,?)}");
            call.setDouble(1, min);
            call.setDouble(2, max);
            ResultSet rs = call.executeQuery();
            while(rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setImage_url(rs.getString("image_url"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                product.setCatalog(rs.getInt("catalog_id"));
                product.setSize(rs.getInt("size_id"));
                product.setBrand(rs.getInt("brand_id"));
                product.setColor(rs.getInt("color_id"));
                product.setUser_object(rs.getInt("object_id"));
                product.setImport_date(rs.getDate("import_date"));
                product.setImport_price(rs.getDouble("import_price"));
                product.setExport_price(rs.getDouble("export_price"));
                product.setStatus(rs.getBoolean("status"));
                list.add(product);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return list;
    }

    public void toggleProStatus(int id){
        Connection conn = null;
        try {
                conn = ConnectDB.getConnection();
                CallableStatement callSt = conn.prepareCall("{call togleStatusPro(?)}");
                callSt.setInt(1, id);
                callSt.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
