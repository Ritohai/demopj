package ra.service.impl;

import org.springframework.stereotype.Service;
import ra.model.Brand;
import ra.service.IGenericService;
import ra.ultil.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class BrandService implements IGenericService<Brand, Integer> {
    @Override
    public List<Brand> findAll() {
        List<Brand> brands = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call getBrand}");
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                Brand brand = new Brand();
                brand.setId(resultSet.getInt("id"));
                brand.setBrand_name(resultSet.getString("brand_name"));
                brands.add(brand);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return brands;
    }

    @Override
    public Brand findById(Integer id) {
        Connection connection = null;
        Brand brand = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call findBrandById(?)}");
            callSt.setInt(1, id);
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                brand = new Brand();
                brand.setId(resultSet.getInt("id"));
                brand.setBrand_name(resultSet.getString("brand_name"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return brand;
    }

    @Override
    public void save(Brand brand) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            if (brand.getId() == 0) {
                CallableStatement callSt = conn.prepareCall("{call insertBrand(?)}");
                callSt.setString(1, brand.getBrand_name());
                callSt.executeUpdate();
            } else {
                CallableStatement callSt = conn.prepareCall("{call updateBrand(?,?)}");
                callSt.setInt(1, brand.getId());
                callSt.setString(2, brand.getBrand_name());
                callSt.executeUpdate();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }

    @Override
    public void delete(Integer id) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call deleteBrand(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
