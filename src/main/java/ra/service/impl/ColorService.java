package ra.service.impl;

import org.springframework.stereotype.Service;
import ra.model.Color;
import ra.service.IGenericService;
import ra.ultil.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColorService implements IGenericService<Color, Integer> {
    @Override
    public List<Color> findAll() {
        List<Color> colors = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call getColor}");
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                Color color = new Color();
                color.setId(resultSet.getInt("id"));
                color.setColor_name(resultSet.getString("color_name"));
                colors.add(color);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return colors;
    }

    @Override
    public Color findById(Integer id) {
        Connection connection = null;
        Color color = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call findColorById(?)}");
            callSt.setInt(1, id);
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                color = new Color();
                color.setId(resultSet.getInt("id"));
                color.setColor_name(resultSet.getString("color_name"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return color;
    }

    @Override
    public void save(Color color) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            if (color.getId() == 0) {
                CallableStatement callSt = conn.prepareCall("{call insertColor(?)}");
                callSt.setString(1, color.getColor_name());
                callSt.executeUpdate();
            } else {
                CallableStatement callSt = conn.prepareCall("{call updateColor(?,?)}");
                callSt.setInt(1, color.getId());
                callSt.setString(2, color.getColor_name());
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
            CallableStatement callSt = conn.prepareCall("{call deleteColor(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
    }
}
