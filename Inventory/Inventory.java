package Inventory;
import Users.user_login;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Inventory extends javax.swing.JFrame {

    static DefaultTableModel model ;
    static InventoryEmployee employee = new InventoryEmployee();
    UpdateProduct updateProduct = new UpdateProduct();
    AddProduct addProduct = new AddProduct();
    int x,y;
    
    public Inventory() {
        initComponents();
        model = (DefaultTableModel) stock.getModel();
        displayAllProducts();
    }
    
    protected static void displayAllProducts(){
        printResultSet(InventoryEmployee.listAllProducts());
    }
    
    protected static void printResultSet(ResultSet r){
        try {
            while(r.next()){
                model.addRow(new Object[]{
                r.getString("id"),
                r.getString("name"),
                r.getString("category"),
                r.getString("production_date"),
                r.getString("expiry_date"),
                r.getString("price"),
                r.getString("quantity"),
                r.getString("damages"),
                r.getString("shortage")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inventory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        bar = new javax.swing.JPanel();
        close1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        subTitle = new javax.swing.JLabel();
        profileL = new javax.swing.JLabel();
        updateL = new javax.swing.JLabel();
        deleteL = new javax.swing.JLabel();
        addL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stock = new javax.swing.JTable();
        s = new javax.swing.JLabel();
        input = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        notification = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventory Managment");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(51, 51, 51));
        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        bar.setBackground(new java.awt.Color(51, 51, 51));
        bar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        bar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barMouseDragged(evt);
            }
        });
        bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                barMousePressed(evt);
            }
        });

        close1.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 20)); // NOI18N
        close1.setForeground(new java.awt.Color(255, 255, 255));
        close1.setText("      X");
        close1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                close1MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("  Hyper Market Management System");

        subTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        subTitle.setForeground(new java.awt.Color(255, 255, 255));
        subTitle.setText("  Inventory Department");

        javax.swing.GroupLayout barLayout = new javax.swing.GroupLayout(bar);
        bar.setLayout(barLayout);
        barLayout.setHorizontalGroup(
            barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barLayout.createSequentialGroup()
                .addComponent(subTitle)
                .addGap(182, 182, 182)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(close1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        barLayout.setVerticalGroup(
            barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(close1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(subTitle))
        );

        profileL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        profileL.setForeground(new java.awt.Color(255, 255, 255));
        profileL.setText(" Update Profile");
        profileL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profileLMouseClicked(evt);
            }
        });

        updateL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updateL.setForeground(new java.awt.Color(255, 255, 255));
        updateL.setText(" Update product");
        updateL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateLMouseClicked(evt);
            }
        });

        deleteL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deleteL.setForeground(new java.awt.Color(255, 255, 255));
        deleteL.setText(" Delete product");
        deleteL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    deleteLMouseClicked(evt);
                } catch (NumberFormatException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        addL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addL.setForeground(new java.awt.Color(255, 255, 255));
        addL.setText(" Add product");
        addL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addLMouseClicked(evt);
            }
        });

        stock.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        stock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "category", "production_date", "expiry_date", "price ", "quantity", "damages", "shortage"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(stock);
        if (stock.getColumnModel().getColumnCount() > 0) {
            stock.getColumnModel().getColumn(0).setMinWidth(40);
            stock.getColumnModel().getColumn(0).setMaxWidth(50);
            stock.getColumnModel().getColumn(2).setMinWidth(110);
            stock.getColumnModel().getColumn(2).setMaxWidth(120);
            stock.getColumnModel().getColumn(5).setMinWidth(50);
            stock.getColumnModel().getColumn(5).setMaxWidth(60);
            stock.getColumnModel().getColumn(6).setMinWidth(50);
            stock.getColumnModel().getColumn(6).setMaxWidth(60);
            stock.getColumnModel().getColumn(7).setMinWidth(60);
            stock.getColumnModel().getColumn(7).setMaxWidth(70);
            stock.getColumnModel().getColumn(8).setMinWidth(60);
            stock.getColumnModel().getColumn(8).setMaxWidth(70);
        }

        s.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        s.setForeground(new java.awt.Color(255, 255, 255));
        s.setText("Search");

        input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputKeyReleased(evt);
            }
        });

        notification.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        notification.setForeground(new java.awt.Color(255, 51, 51));
        notification.setText(" Notifications");
        notification.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notificationMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(profileL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteL)
                            .addComponent(notification, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 23, Short.MAX_VALUE))
                    .addComponent(updateL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(s)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 717, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(s)
                            .addComponent(input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addComponent(notification, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(profileL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addL, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateL, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(deleteL, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void inputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputKeyReleased
       String keyWord = input.getText();
       if(keyWord != ""){
           model.setRowCount(0);
           printResultSet(employee.search(keyWord));
       }
       else
          displayAllProducts();
    }//GEN-LAST:event_inputKeyReleased

    private void stockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockMouseClicked
        //updateProductForm -> name category price quantity damages shortage 
        //table -> id name category pdate edate price quantity damages shortage
        int i = stock.getSelectedRow();
        String id = (String) model.getValueAt(i, 0);
        String name = (String) model.getValueAt(i, 1);
        String category = (String) model.getValueAt(i, 2);
        String price = (String) model.getValueAt(i, 5);
        String quantity = (String) model.getValueAt(i, 6);
        String damages = (String) model.getValueAt(i, 7);
        String shortage = (String) model.getValueAt(i, 8);
        String[] data = new String[]{name,category,price,quantity,damages,shortage};
        updateProduct.setDataToTextFields(data); 
        updateProduct.setID(Integer.parseInt(id));
    }//GEN-LAST:event_stockMouseClicked
//                txt_price.setText((String)  new DecimalFormat("#.##").format(v.get(5)));

    private void close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_close1MouseClicked
        int r=JOptionPane.showConfirmDialog(null, "Are you sure you want to sign out?", "Sign out",JOptionPane.YES_NO_OPTION);
        if(r==0){
            this.dispose();
            user_login login = new user_login();
            login.setVisible(true);
        }
    }//GEN-LAST:event_close1MouseClicked

    private void barMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barMouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx-x, yy-y);
    }//GEN-LAST:event_barMouseDragged

    private void barMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_barMousePressed

    private void updateLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateLMouseClicked
        int i = -1;
        i = stock.getSelectedRow();
        if(i==-1)
        JOptionPane.showMessageDialog(null, "Select a product","Error",JOptionPane.ERROR_MESSAGE);
        else{
            updateProduct.setVisible(true);
            updateProduct.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_updateLMouseClicked

    private void deleteLMouseClicked(java.awt.event.MouseEvent evt) throws NumberFormatException, SQLException {//GEN-FIRST:event_deleteLMouseClicked
        int r,c=0;
        c = stock.getSelectedRow();
        if(c<0)
        JOptionPane.showMessageDialog(null, "Select a product","Error",JOptionPane.ERROR_MESSAGE);
        else{
            int x = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete selected products?",
                "Warning",JOptionPane.WARNING_MESSAGE);
            if(x==0){
                String id = (String) model.getValueAt(c, 0);
                r = employee.deleteProduct(Integer.parseInt(id));
                if(r==1){
                    model.removeRow(c);
                    c++;
                }
            }
        }
    }//GEN-LAST:event_deleteLMouseClicked

    private void addLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addLMouseClicked
        addProduct.setVisible(true);
        addProduct.setLocationRelativeTo(null);
    }//GEN-LAST:event_addLMouseClicked

    private void profileLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileLMouseClicked
        Users.UpdateProfile updateProfile = new Users.UpdateProfile();
        updateProfile.setVisible(true);
    }//GEN-LAST:event_profileLMouseClicked

    private void notificationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationMouseClicked
        new NotificationCenter().setVisible(true); 
        notification.setForeground(Color.WHITE);
    }//GEN-LAST:event_notificationMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Inventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addL;
    private javax.swing.JPanel bar;
    private javax.swing.JLabel close1;
    private javax.swing.JLabel deleteL;
    private javax.swing.JTextField input;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel notification;
    private javax.swing.JLabel profileL;
    private javax.swing.JLabel s;
    private javax.swing.JTable stock;
    private javax.swing.JLabel subTitle;
    private javax.swing.JLabel updateL;
    // End of variables declaration//GEN-END:variables
}
