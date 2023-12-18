
package Seller;

import DatabaseConncection.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Seller {

    DatabaseConnection dc = new DatabaseConnection();

    public Vector getCategories() {
        Vector resultVector = new Vector();
        try {

            ResultSet rs = dc.executeQuery("select distinct category from stock");
            while (rs.next()) {
                resultVector.add(rs.getString("category"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultVector;
    }

    public Vector getProduct(String s) {
        Vector resultVector = new Vector();
        try {
            ResultSet rs = dc.executeQuery("select name from stock where category ='" + s + "'");
            while (rs.next()) {

                resultVector.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultVector;
    }

    public String getBill(String q, String pro) {
        if (q.equals("")) {
            return "";
        }

        try {
            int qu = Integer.parseInt(q);

            ResultSet rs = dc.executeQuery("select quantity,offer,price from stock where name ='" + pro + "'");
            if (rs.next()) {
                if (qu > rs.getInt("quantity")) {
                    return "ERROR Quantity not enough";
                } else {
                    if (rs.getDouble("offer") > 0) {
                        double p = rs.getDouble("price");
                        double o = rs.getDouble("offer");

                        o = p * o / 100;
                        p -= o;
                        return String.valueOf(p * qu);
                    } else {
                        return String.valueOf(qu * rs.getDouble("price"));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error while Connecting To The Database",
                        "Database Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return "ERROR";
    }

    public boolean addToOrder(String category, String product, int quantity, double bill, boolean orderIsFinished) {

        try {
            int order_id = 0;

            ResultSet rs = dc.executeQuery("select max(order_id) as mx from orders");

            if (rs.next()) {
                order_id = rs.getInt("mx");
            }
            if (orderIsFinished) {
                order_id++;
            }
            ResultSet resultSet = dc.executeQuery("select quantity,bill from orders where order_id='" + order_id
                    + "' and product_name='" + product + "'");
            if (resultSet.next()) {
                int quant = resultSet.getInt("quantity") + quantity;
                double b = resultSet.getDouble("bill") + bill;

                dc.excuteUpdate("update orders set quantity='" + quant + "' , bill='" + b + "'where order_id='" + order_id
                        + "' and product_name='" + product + "'");
            } else {
                dc.excuteUpdate(
                        "insert into orders (order_id,product_name,product_category,quantity,bill,date) values ('"
                                + order_id + "','" + product + "','" + category + "','" + quantity + "','" + bill
                                + "','" + LocalDateTime.now() + "')");
            }
            // update quantity
            rs = dc.executeQuery("select quantity from stock where name ='" + product + "'");
            if (rs.next()) {
                int q;
                q = rs.getInt("quantity");
                q -= quantity;
                dc.excuteUpdate("update stock set quantity='" + q + "' where name = '" + product + "'");
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    String getTotal(String orderId) {
        try {
            ResultSet rs = dc.executeQuery("select sum(bill) as s from orders where order_id ='" + orderId + "'");
            if (rs.next()) {
                return rs.getString("s");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    void search(DefaultTableModel dft, int order_id) {
        try {
            ResultSet Rs = dc.executeQuery("select * from orders where order_id='" + order_id + "'");
            ResultSetMetaData RSMD = Rs.getMetaData();
            int Column_count = RSMD.getColumnCount();
            DefaultTableModel DFT = dft;
            DFT.setRowCount(0);
            while (Rs.next()) {
                Vector v = new Vector();
                for (int i = 1; i <= Column_count; i++) {
                    v.add(Rs.getString("id"));
                    v.add(Rs.getString("order_id"));
                    v.add(Rs.getString("product_name"));
                    v.add(Rs.getString("product_category"));
                    v.add(Rs.getString("quantity"));
                    v.add(Rs.getString("bill"));
                    v.add(Rs.getString("date"));
                }
                DFT.addRow(v);
            }
        } catch (Exception e) {
        }
    }

    double updateQuantity(int qdiff, String pname, String order_id) {

        ResultSet rs1 = dc.executeQuery("select quantity from stock where name='" + pname + "'");
        int squantity = 0;
        try {
            if (rs1.next())
                squantity = rs1.getInt("quantity");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        ResultSet rs2 = dc.executeQuery(
                "select quantity from orders where order_id='" + order_id + "' and product_name='" + pname + "'");
        int oquantity = 0;
        try {
            if (rs2.next())
                oquantity = rs2.getInt("quantity");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            if (qdiff == 0) {
                return 0;
            } // higher quantity
            else if (qdiff < 0) {
                // quantity is enough
                qdiff = Math.abs(qdiff);
                if (squantity - qdiff >= 0) {

                    squantity -= qdiff;
                    dc.excuteUpdate("update stock set quantity='" + squantity + "' where name='" + pname + "'");

                    oquantity += qdiff;
                    String bill = getBill(String.valueOf(oquantity), pname);
                    dc.excuteUpdate("update orders set quantity ='" + oquantity + "' , bill='" + bill
                            + "' where product_name='" + pname + "' and order_id='" + order_id + "'");

                    return Double.parseDouble(bill);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Current quantity For this Product Won't Satisfy What You Need ", "Quantity Error",
                            JOptionPane.ERROR_MESSAGE);
                    return 0;
                }
            } else {
                squantity += qdiff;
                dc.excuteUpdate("update stock set quantity='" + squantity + "' where name='" + pname + "'");

                oquantity -= qdiff;
                double bill = Double.parseDouble(getBill(String.valueOf(oquantity), pname));

                dc.excuteUpdate("update orders set quantity ='" + oquantity + "' , bill='" + bill
                        + "' where product_name='" + pname + "' and order_id='" + order_id + "'");

                return bill;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    void deleteTransaction(String order_id, String pname, String quantity) throws SQLException {
        dc.excuteUpdate("delete from orders where order_id='" + order_id + "'and product_name='" + pname + "'");
        ResultSet rs = dc.executeQuery("select quantity from stock where name='" + pname + "'");
        try {
            if (rs.next()) {
                int nquantity = rs.getInt("quantity") + Integer.parseInt(quantity);
                dc.excuteUpdate("update stock set quantity='" + nquantity + "' where name='" + pname + "'");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void deleteOrder(String orderId) {
        ResultSet rs = dc.executeQuery("select count(*) as c from orders where order_id='" + orderId + "'");
        Vector<String> p = new Vector();
        Vector<String> q = new Vector();

        try {
            if (rs.next()) {
                int count = rs.getInt("c");
                rs = dc.executeQuery("select product_name,quantity from orders where order_id='" + orderId + "'");
                while (rs.next()) {
                    p.add(rs.getString("product_name"));
                    q.add(rs.getString("quantity"));
                }
                for (int i = 0; i < count; i++) {
                    deleteTransaction(orderId, p.get(i), q.get(i));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}