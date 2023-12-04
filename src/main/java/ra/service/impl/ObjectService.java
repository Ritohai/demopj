package ra.service.impl;

import org.springframework.stereotype.Service;
import ra.model.Object;
import ra.service.IGenericService;
import ra.ultil.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class ObjectService implements IGenericService<Object, Integer> {
    @Override
    public List<Object> findAll() {
        List<Object> objects = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call getuser_object}");
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                Object object = new Object();
                object.setId(resultSet.getInt("id"));
                object.setObject_name(resultSet.getString("object"));
                objects.add(object);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return objects;
    }

    @Override
    public Object findById(Integer id) {
        Connection connection = null;
        Object object = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call findObjectById(?)}");
            callSt.setInt(1, id);
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                object = new Object();
                object.setId(resultSet.getInt("id"));
                object.setObject_name(resultSet.getString("object"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return object;
    }

    @Override
    public void save(Object object) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            if (object.getId() == 0) {
                CallableStatement callSt = conn.prepareCall("{call insertObject(?)}");
                callSt.setString(1, object.getObject_name());
                callSt.executeUpdate();
            } else {
                CallableStatement callSt = conn.prepareCall("{call updateObject(?,?)}");
                callSt.setInt(1, object.getId());
                callSt.setString(2, object.getObject_name());
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
            CallableStatement callSt = conn.prepareCall("{call deleteObject(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
