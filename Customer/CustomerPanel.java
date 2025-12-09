
package Customer;
import javax.swing.*;


public class CustomerPanel extends javax.swing.JPanel {
    private Customer currentCustomer;
    
 

  public void setCurrentCustomer(Customer c) {
    this.currentCustomer = c;
}


    public CustomerPanel() {
        initComponents();
        nameLabel.setVisible(false);
        phoneLabel.setVisible(false);
        addressLabel.setVisible(false);
        pointsLabel.setVisible(false);
        totalSpentLabel.setVisible(false);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jButton1 = new javax.swing.JButton();
        Notifcations_btn = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        pointsLabel = new javax.swing.JLabel();
        totalSpentLabel = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setFont(new java.awt.Font("Sylfaen", 0, 12)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Profile");
        jButton1.addActionListener(this::Profile_btn_ActionPerformed);

        Notifcations_btn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Notifcations_btn.setText("Notifications");
        Notifcations_btn.addActionListener(this::Notifcations_btnActionPerformed);

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        nameLabel.setText("Name:");

        addressLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        addressLabel.setText("Address:");

        phoneLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        phoneLabel.setText("Phone number:");

        pointsLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        pointsLabel.setText("Loyalty points:");

        totalSpentLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        totalSpentLabel.setText("Total money spent:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Notifcations_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addressLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(phoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pointsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(totalSpentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                .addGap(48, 48, 48))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Notifcations_btn, jButton1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Notifcations_btn)
                    .addComponent(jButton1))
                .addGap(16, 16, 16)
                .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(addressLabel)
                .addGap(18, 18, 18)
                .addComponent(phoneLabel)
                .addGap(18, 18, 18)
                .addComponent(pointsLabel)
                .addGap(18, 18, 18)
                .addComponent(totalSpentLabel)
                .addGap(0, 112, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Notifcations_btn, jButton1});

        nameLabel.getAccessibleContext().setAccessibleName("jlabel1");
    }// </editor-fold>//GEN-END:initComponents

    private void Notifcations_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Notifcations_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Notifcations_btnActionPerformed

    private void Profile_btn_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Profile_btn_ActionPerformed
                                               
     if (currentCustomer == null) {
        JOptionPane.showMessageDialog(this, "No customer loaded!");
        return;
    }
    nameLabel.setText("Name: " + currentCustomer.getName());
    phoneLabel.setText("Phone: " + currentCustomer.getPhoneNum());
    addressLabel.setText("Address: " + currentCustomer.getAddress());
    pointsLabel.setText("Loyalty Points: " + currentCustomer.getLoyaltyPoints());
    totalSpentLabel.setText("Total Spent: " + currentCustomer.getTotalSpent());
    nameLabel.setVisible(true);
    phoneLabel.setVisible(true);
    addressLabel.setVisible(true);
    pointsLabel.setVisible(true);
    totalSpentLabel.setVisible(true);
    }//GEN-LAST:event_Profile_btn_ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Notifcations_btn;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel pointsLabel;
    private javax.swing.JLabel totalSpentLabel;
    // End of variables declaration//GEN-END:variables
}
