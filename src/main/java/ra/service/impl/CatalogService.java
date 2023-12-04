package ra.service.impl;

import org.springframework.stereotype.Service;
import ra.model.Catalog;
import ra.service.IGenericService;
import ra.ultil.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogService implements IGenericService<Catalog, Integer> {
    @Override
    public List<Catalog> findAll() {
        List<Catalog> catalogs = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call getAllCatalog}");
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                Catalog catalog = new Catalog();
                catalog.setId(resultSet.getInt("id"));
                catalog.setCatalog_name(resultSet.getString("catalog_name"));
                catalogs.add(catalog);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return catalogs;
    }

    @Override
    public Catalog findById(Integer id) {
        Connection connection = null;
        Catalog catalog = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call findCatalogById(?)}");
            callSt.setInt(1, id);
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                catalog = new Catalog();
                catalog.setId(resultSet.getInt("id"));
                catalog.setCatalog_name(resultSet.getString("catalog_name"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return catalog;
    }

    @Override
    public void save(Catalog catalog) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            if (catalog.getId() == 0) {
                CallableStatement callSt = conn.prepareCall("{call insertCatalog(?)}");
                callSt.setString(1, catalog.getCatalog_name());
                callSt.executeUpdate();
            } else {
                CallableStatement callSt = conn.prepareCall("{call updateCatalog(?,?)}");
                callSt.setInt(1, catalog.getId());
                callSt.setString(2, catalog.getCatalog_name());
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
            CallableStatement callSt = conn.prepareCall("{call deleteCatalog(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
