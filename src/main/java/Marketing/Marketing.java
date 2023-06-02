
package Marketing;

import java.sql.*;
import java.text.DecimalFormat;

import DatabaseConncection.*;
import java.time.LocalDate;
import java.util.Vector;

public class Marketing {

    DatabaseConnection dc = new DatabaseConnection();

    ResultSet rs;

    public Vector makeReports(String name) {
        Vector resultVector = new Vector();

        Date date;

        try {
            rs = dc.executeQuery("SELECT * from stock where name ='" + name + "'");

            if (rs.next()) {
                double price = rs.getDouble("price");
                double offer = rs.getDouble("offer");
                

                offer = price * offer / 100;
                price -= offer;

                resultVector.add(rs.getString("id"));
                resultVector.add(rs.getString("name"));
                resultVector.add(rs.getString("category"));
                date = rs.getDate("production_date");
                resultVector.add(date.toLocalDate().toString());
                date = rs.getDate("expiry_date");
                resultVector.add(date.toLocalDate().toString());
                resultVector.add(new DecimalFormat("#.##").format(price));
                resultVector.add(rs.getString("quantity"));
                resultVector.add(rs.getString("damages"));
                resultVector.add(rs.getString("shortage"));
                resultVector.add(rs.getString("offer"));
            } else {
                resultVector.add(0, -1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultVector;
    }

    public int makeOffer(String product_name, double offer, String ldate) {
        try {
            rs = dc.executeQuery("select offer,offerEndDate from stock where name ='" + product_name + "'");
            if (rs.next()) {
                if (Double.parseDouble(rs.getString("offer")) > 0)
                    if (OfferEnded(LocalDate.parse(rs.getString("offerEndDate"))))
                        return dc.excuteUpdate("update stock set offer='" + offer + "',offerEndDate='"
                                + LocalDate.parse(ldate) + "' where name ='" + product_name + "'");
                    else
                        return 0;
                else
                    return dc.excuteUpdate("update stock set offer='" + offer + "',offerEndDate='"
                            + LocalDate.parse(ldate) + "' where name ='" + product_name + "'");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public boolean OfferEnded(LocalDate endDate) {
        return (endDate.isBefore(LocalDate.now()));
        // endDate.equals(LocalDate.now())
    }
}
