package ra.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.model.Cart;
import ra.model.Order;
import ra.service.IGenericService;
import ra.ultil.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
public class OrderService implements IGenericService<Order,Integer> {
    @Autowired
    BillingService billingService;
    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call getAllOrders}");
            ResultSet resultSet = callSt.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUser_id(resultSet.getInt("user_id"));
                order.setExport_Date(resultSet.getDate("export_date"));
                order.setStatus(resultSet.getBoolean("status"));
                orders.add(order);
            }

        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(connection);
        }
        return orders;
    }

    @Override
    public Order findById(Integer id) {
        Connection connection = null;
        Order order = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{CALL findOrderById(?)}");
            callSt.setInt(1, id);
            ResultSet resultSet = callSt.executeQuery();
            while(resultSet.next()) {
                order.setId(resultSet.getInt("id"));
                order.setUser_id(resultSet.getInt("user_id"));
                order.setExport_Date(resultSet.getDate("export_date"));
                order.setStatus(resultSet.getBoolean("status"));
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
         ConnectDB.closeConnection(connection);
        }
        return order;
    }

    @Override
    public void save(Order order) {
    }
    public void insertOrder(Order order, List<Cart> carts) {
        Connection connection = null;
        try {
            connection = ConnectDB.getConnection();
            connection.setAutoCommit(false);

            CallableStatement callSt1 = connection.prepareCall("{call insertOrders(?, ?)}");
            callSt1.setInt(1, order.getUser_id());
            callSt1.registerOutParameter(2, Types.INTEGER);
            callSt1.executeUpdate();

            int order_id = callSt1.getInt(2);

            for (Cart cart : carts) {
                try {
                    CallableStatement callSt2 = connection.prepareCall("{call insertOrders_detail(?, ?, ?, ?)}");
                    callSt2.setInt(1, cart.getProduct().getId());
                    callSt2.setInt(2, cart.getQuantity());
                    callSt2.setDouble(3, cart.getTotalPrice());
                    callSt2.setInt(4, order_id);
                    callSt2.executeUpdate();
                } catch (Exception e) {
                    connection.rollback();
                    throw new RuntimeException("Error while adding order detail", e);
                }
            }

            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace(); // Print specific error message
            throw new RuntimeException("Error while adding order", e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
    }


    @Override
    public void delete(Integer id) {
        Connection conn = null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call deletelOrder(?)}");
            callSt.setInt(1,id);
            callSt.executeUpdate();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }
    }

    public void  toggleOrder(int id) {
        Connection conn =null;
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call toggleOrder(?)}");
            callSt.setInt(1,id);
            callSt.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally{
            ConnectDB.closeConnection(conn);
        }
    }




}
