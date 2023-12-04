package ra.service.impl;

import org.springframework.stereotype.Service;
import ra.model.History;
import ra.model.Order;
import ra.service.IGenericService;
import ra.ultil.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Service
public class HistoryService implements IGenericService<History,Integer> {
    @Override
    public List<History> findAll() {
        List<History> orders = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call getAllOrders}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                History history = new History();
                history.setId(rs.getInt("id"));
                history.setProduct_name(rs.getString("product_name"));
                history.setQuantity(rs.getInt("quantity"));
                history.setDate(rs.getDate("export_date"));
                history.setByUser(rs.getString("username"));
                history.setPhone(rs.getString("phone"));
                history.setReceiver(rs.getString("receiver"));
                history.setAddress(rs.getString("address"));
                history.setPrice(rs.getDouble("total"));
                history.setStatus(rs.getBoolean("status"));
                orders.add(history);
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return orders;
    }

    @Override
    public History findById(Integer id) {
        return null;
    }

    @Override
    public void save(History history) {

    }

    @Override
    public void delete(Integer id) {

    }

    public List<History> getOrderByUser(int id){
        List<History> histories = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call getOrderByUserId(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()){
                History history = new History();
                history.setId(rs.getInt("id"));
                history.setProduct_name(rs.getString("product_name"));
                history.setQuantity(rs.getInt("quantity"));
                history.setDate(rs.getDate("export_date"));
                history.setPhone(rs.getString("phone"));
                history.setReceiver(rs.getString("receiver"));
                history.setAddress(rs.getString("address"));
                history.setPrice(rs.getDouble("total"));
                history.setStatus(rs.getBoolean("status"));
                histories.add(history);
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
        return histories;
    }
}
