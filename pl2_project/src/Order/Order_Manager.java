
package Order;

import java.io.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;
public class Order_Manager extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Order_Manager.class.getName());
 private int idToCancel; 
  private int orderId;
  private int CustId;
  private OrderDetails x;
  private Order y=new Order();
  
  
    public Order_Manager() {
        initComponents();
        loadTableData();
        
        
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        CustInfo = new javax.swing.JButton();
        fName = new javax.swing.JTextField();
        mealName = new javax.swing.JLabel();
        fQuantity = new javax.swing.JTextField();
        mealQuantity = new javax.swing.JLabel();
        btnSaveItem = new javax.swing.JButton();
        makeId = new javax.swing.JLabel();
        fMakeId = new javax.swing.JTextField();
        btnCancle = new javax.swing.JButton();
        btnmakrOrder = new javax.swing.JButton();
        CustomerId = new javax.swing.JLabel();
        fCustId = new javax.swing.JTextField();
        fDeleteId = new javax.swing.JTextField();
        deleteId = new javax.swing.JLabel();
        btnBille = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Meal_id", "Meal_Name", "Price"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("order mangement");

        CustInfo.setText("Cust info");
        CustInfo.addActionListener(this::CustInfoActionPerformed);

        fName.addActionListener(this::fNameActionPerformed);

        mealName.setText("Meal name");

        fQuantity.addActionListener(this::fQuantityActionPerformed);

        mealQuantity.setText("Quantity");

        btnSaveItem.setText("save item");
        btnSaveItem.addActionListener(this::btnSaveItemActionPerformed);

        makeId.setText("Order id");

        fMakeId.addActionListener(this::fMakeIdActionPerformed);

        btnCancle.setText("Delet oder");
        btnCancle.addActionListener(this::btnCancleActionPerformed);

        btnmakrOrder.setText("make order");
        btnmakrOrder.addActionListener(this::btnmakrOrderActionPerformed);

        CustomerId.setText("Customer id");

        fCustId.addActionListener(this::fCustIdActionPerformed);

        fDeleteId.addActionListener(this::fDeleteIdActionPerformed);

        deleteId.setText("Order id");

        btnBille.setText("Bille");
        btnBille.addActionListener(this::btnBilleActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(makeId, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(mealName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(mealQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(CustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(fCustId, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(fMakeId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(fQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnSaveItem))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(fDeleteId, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                                .addComponent(btnCancle))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(deleteId, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(231, 231, 231)
                                        .addComponent(CustInfo)))
                                .addGap(11, 11, 11))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(185, 185, 185)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnmakrOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBille, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mealName))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mealQuantity)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(btnSaveItem)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(makeId))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(fMakeId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CustomerId)
                            .addComponent(fCustId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnmakrOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBille, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fDeleteId, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteId)
                            .addComponent(btnCancle))
                        .addGap(71, 71, 71)
                        .addComponent(CustInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void loadTableData() {

    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Meal_id");
    model.addColumn("Meal_Name");
    model.addColumn("Price");
    jTable1.setModel(model);

    try {
        
        File file = new File("meals.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] row = line.split(",");
            model.addRow(row);
        }

        sc.close();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}


    private void CustInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustInfoActionPerformed
       
    }//GEN-LAST:event_CustInfoActionPerformed

    private void btnSaveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveItemActionPerformed
        String name=fName.getText();
        int Qusntity=Integer.parseInt(fQuantity.getText());
         x =new OrderDetails(name,Qusntity);
         y.makeOrder(x);
         fName.setText("");
         fQuantity.setText("");
        
    }//GEN-LAST:event_btnSaveItemActionPerformed

    private void fQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fQuantityActionPerformed

    private void fMakeIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fMakeIdActionPerformed
      
      
    }//GEN-LAST:event_fMakeIdActionPerformed

    private void fNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fNameActionPerformed

    private void fCustIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fCustIdActionPerformed
        
     
    }//GEN-LAST:event_fCustIdActionPerformed

    private void fDeleteIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fDeleteIdActionPerformed
         
    }//GEN-LAST:event_fDeleteIdActionPerformed

    private void btnBilleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBilleActionPerformed
       
    }//GEN-LAST:event_btnBilleActionPerformed

    private void btnCancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancleActionPerformed
        idToCancel=Integer.parseInt(fDeleteId.getText());
        Order.deleteOrderFromFile(idToCancel);
        fDeleteId.setText("");
        
    }//GEN-LAST:event_btnCancleActionPerformed

    private void btnmakrOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmakrOrderActionPerformed
        orderId=Integer.parseInt(fMakeId.getText());
        CustId=Integer.parseInt(fCustId.getText());
        y.setCustomerId(CustId);
        y.setOrderId(orderId);
  
        
        try {
            
            y.saveOrder();
            fMakeId.setText("");
            fCustId.setText("");
            
        } catch (FileNotFoundException ex) {
            System.getLogger(Order_Manager.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
     
    }//GEN-LAST:event_btnmakrOrderActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new Order_Manager().setVisible(true));
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CustInfo;
    private javax.swing.JLabel CustomerId;
    private javax.swing.JButton btnBille;
    private javax.swing.JButton btnCancle;
    private javax.swing.JButton btnSaveItem;
    private javax.swing.JButton btnmakrOrder;
    private javax.swing.JLabel deleteId;
    private javax.swing.JTextField fCustId;
    private javax.swing.JTextField fDeleteId;
    private javax.swing.JTextField fMakeId;
    private javax.swing.JTextField fName;
    private javax.swing.JTextField fQuantity;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel makeId;
    private javax.swing.JLabel mealName;
    private javax.swing.JLabel mealQuantity;
    // End of variables declaration//GEN-END:variables

    
}
