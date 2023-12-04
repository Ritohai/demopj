package ra.service.impl;

import org.springframework.stereotype.Service;
import ra.model.Size;
import ra.service.IGenericService;
import ra.ultil.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class SizeService implements IGenericService<Size,Integer> {
    @Override
    public List<Size> findAll() {
        List<Size> sizes = new ArrayList<>();
        Connection connection =null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call getSize}");
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                Size size = new Size();
                size.setId(resultSet.getInt("id"));
                size.setSize_name(resultSet.getString("size_name"));
                sizes.add(size);
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return sizes;
    }

    @Override
    public Size findById(Integer id) {
        Connection connection =null;
        Size size =null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call findSizeById(?)}");
            callSt.setInt(1, id);
            ResultSet resultSet = callSt.executeQuery();
            while(resultSet.next()) {
                size = new Size();
                size.setId(resultSet.getInt("id"));
                size.setSize_name(resultSet.getString("size_name"));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally{
            ConnectDB.closeConnection(connection);
        }
        return size;
    }

    @Override
    public void save(Size size) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            if (size.getId() == 0) {
                CallableStatement callSt = conn.prepareCall("{call insertSize(?)}");
                callSt.setString(1, size.getSize_name());
                callSt.executeUpdate();
            }else {
                CallableStatement callSt = conn.prepareCall("{call updateSize(?,?)}");
                callSt.setInt(1, size.getId());
                callSt.setString(2, size.getSize_name());
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
            CallableStatement callSt = conn.prepareCall("{call deleteSize(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
