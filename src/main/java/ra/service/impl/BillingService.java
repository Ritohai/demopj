package ra.service.impl;

import org.springframework.stereotype.Service;
import ra.model.Billing;
import ra.service.IGenericService;
import ra.ultil.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

@Service
public class BillingService implements IGenericService<Billing, Integer> {
    @Override
    public List<Billing> findAll() {
        return null;
    }

    @Override
    public Billing findById(Integer id) {
        Connection connection = null;
        Billing billing = null;
        try {
            connection = ConnectDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call findOrderById(?)}");
            callSt.setInt(1, id);
            ResultSet resultSet = callSt.executeQuery();
            if (resultSet.next()) {
                billing = new Billing(); // Tạo một đối tượng Billing
                billing.setId(resultSet.getInt("id"));
                billing.setReceiver(resultSet.getString("receiver"));
                billing.setPhone(resultSet.getString("phone"));
                billing.setAddress(resultSet.getString("address"));
                billing.setOrtherInfo(resultSet.getString("ortherInfo"));
                billing.setUser_id(resultSet.getInt("user_id"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(connection);
        }
        return billing; // Trả về đối tượng Billing hoặc null nếu không tìm thấy
    }

    @Override
    public void save(Billing billing) {
        Connection conn = null;
        try {
            if (findById(billing.getId()) == null) {
                conn = ConnectDB.getConnection();
                CallableStatement callSt = conn.prepareCall("{call insertBilling(?,?,?,?,?)}");
                callSt.setString(1, billing.getReceiver());
                callSt.setString(2, billing.getPhone());
                callSt.setString(3, billing.getAddress());
                callSt.setString(4, billing.getOrtherInfo());
                callSt.setInt(5, billing.getUser_id());
                callSt.executeUpdate();
            }else {
                //
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            ConnectDB.closeConnection(conn);
        }

    }

    @Override
    public void delete(Integer id) {

    }
}
