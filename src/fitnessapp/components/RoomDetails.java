/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package fitnessapp.components;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author maisyst
 */
public class RoomDetails extends javax.swing.JDialog {

    /**
     * Creates new form RoomDetails
     */
    public RoomDetails(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        undescorePanel.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelCardStandard.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelCardGold.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        panelCardPrime.putClientProperty(FlatClientProperties.STYLE, "arc:20");
       
    }

    public JLabel getNameManager() {
        return NameManager;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public JLabel getRoomName() {
        return roomName;
    }

    public JLabel getSubscriptionGoldTotal() {
        return subscriptionGoldTotal;
    }

    public JLabel getSubscriptionPrimeTotal() {
        return subscriptionPrimeTotal;
    }

    public JLabel getSubscriptionStandardTotal() {
        return subscriptionStandardTotal;
    }

    public JLabel getNumberPlanning() {
        return numberPlanning;
    }

    public JLabel getNumbreSubscribe() {
        return numbreSubscribe;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        iconTitle = new javax.swing.JLabel();
        roomName = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        NameManager = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        numbreSubscribe = new javax.swing.JLabel();
        numberPlanning = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        panelCardGold = new javax.swing.JPanel();
        subscriptionGoldTotal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panelCardPrime = new javax.swing.JPanel();
        subscriptionPrimeTotal = new javax.swing.JLabel();
        panelCardStandard = new javax.swing.JPanel();
        subscriptionStandardTotal = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        undescorePanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(725, 500));
        setMinimumSize(new java.awt.Dimension(725, 500));
        setModal(true);
        setName(""); // NOI18N
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(725, 500));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        container.setBackground(new java.awt.Color(255, 255, 255));
        container.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 0), 2, true));
        container.setLayout(new java.awt.BorderLayout());

        header.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        header.setOpaque(false);
        header.setPreferredSize(new java.awt.Dimension(792, 60));

        iconTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fitnessapp/icons/emf50x50.png"))); // NOI18N

        roomName.setFont(new java.awt.Font("SansSerif", 1, 25)); // NOI18N
        roomName.setForeground(new java.awt.Color(0, 0, 0));
        roomName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        roomName.setText("jLabel2");

        btnClose.setContentAreaFilled(false);
        btnClose.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(iconTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(roomName, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(iconTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomName, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        container.add(header, java.awt.BorderLayout.PAGE_START);

        main.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        main.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Gérant                         :");

        NameManager.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        NameManager.setForeground(new java.awt.Color(0, 0, 0));
        NameManager.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Nombre d'abonnés :");

        numbreSubscribe.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        numbreSubscribe.setForeground(new java.awt.Color(0, 0, 0));
        numbreSubscribe.setText("jLabel4");

        numberPlanning.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        numberPlanning.setForeground(new java.awt.Color(0, 0, 0));
        numberPlanning.setText("jLabel4");

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Nombre de plannings :");

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Abonnement");

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Gold");

        panelCardGold.setBackground(new java.awt.Color(159, 199, 0));
        panelCardGold.setMaximumSize(new java.awt.Dimension(100, 100));

        subscriptionGoldTotal.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        subscriptionGoldTotal.setForeground(new java.awt.Color(255, 255, 255));
        subscriptionGoldTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subscriptionGoldTotal.setText("jLabel2");

        javax.swing.GroupLayout panelCardGoldLayout = new javax.swing.GroupLayout(panelCardGold);
        panelCardGold.setLayout(panelCardGoldLayout);
        panelCardGoldLayout.setHorizontalGroup(
            panelCardGoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCardGoldLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(subscriptionGoldTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panelCardGoldLayout.setVerticalGroup(
            panelCardGoldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCardGoldLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(subscriptionGoldTotal)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Prime");

        panelCardPrime.setBackground(new java.awt.Color(0, 184, 73));
        panelCardPrime.setMaximumSize(new java.awt.Dimension(100, 100));
        panelCardPrime.setMinimumSize(new java.awt.Dimension(100, 100));

        subscriptionPrimeTotal.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        subscriptionPrimeTotal.setForeground(new java.awt.Color(255, 255, 255));
        subscriptionPrimeTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subscriptionPrimeTotal.setText("jLabel2");

        javax.swing.GroupLayout panelCardPrimeLayout = new javax.swing.GroupLayout(panelCardPrime);
        panelCardPrime.setLayout(panelCardPrimeLayout);
        panelCardPrimeLayout.setHorizontalGroup(
            panelCardPrimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(subscriptionPrimeTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        panelCardPrimeLayout.setVerticalGroup(
            panelCardPrimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCardPrimeLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(subscriptionPrimeTotal)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        panelCardStandard.setBackground(new java.awt.Color(0, 102, 255));
        panelCardStandard.setMaximumSize(new java.awt.Dimension(100, 100));
        panelCardStandard.setMinimumSize(new java.awt.Dimension(100, 100));

        subscriptionStandardTotal.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        subscriptionStandardTotal.setForeground(new java.awt.Color(255, 255, 255));
        subscriptionStandardTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subscriptionStandardTotal.setText("jLabel2");

        javax.swing.GroupLayout panelCardStandardLayout = new javax.swing.GroupLayout(panelCardStandard);
        panelCardStandard.setLayout(panelCardStandardLayout);
        panelCardStandardLayout.setHorizontalGroup(
            panelCardStandardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCardStandardLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(subscriptionStandardTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panelCardStandardLayout.setVerticalGroup(
            panelCardStandardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCardStandardLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(subscriptionStandardTotal)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Standard");

        undescorePanel.setBackground(new java.awt.Color(153, 204, 0));

        javax.swing.GroupLayout undescorePanelLayout = new javax.swing.GroupLayout(undescorePanel);
        undescorePanel.setLayout(undescorePanelLayout);
        undescorePanelLayout.setHorizontalGroup(
            undescorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );
        undescorePanelLayout.setVerticalGroup(
            undescorePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 11, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(numbreSubscribe, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                            .addComponent(NameManager, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(numberPlanning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelCardGold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(mainLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel8)))
                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainLayout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addComponent(undescorePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelCardPrime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainLayout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(24, 24, 24)))
                                .addGap(122, 122, 122)))
                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(panelCardStandard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90))))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(NameManager))
                .addGap(26, 26, 26)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(numbreSubscribe))
                .addGap(26, 26, 26)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(numberPlanning))
                .addGap(31, 31, 31)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(undescorePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelCardStandard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(mainLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelCardPrime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(mainLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(panelCardGold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        container.add(main, java.awt.BorderLayout.CENTER);

        getContentPane().add(container);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NameManager;
    private javax.swing.JButton btnClose;
    private javax.swing.JPanel container;
    private javax.swing.JPanel header;
    private javax.swing.JLabel iconTitle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel main;
    private javax.swing.JLabel numberPlanning;
    private javax.swing.JLabel numbreSubscribe;
    private javax.swing.JPanel panelCardGold;
    private javax.swing.JPanel panelCardPrime;
    private javax.swing.JPanel panelCardStandard;
    private javax.swing.JLabel roomName;
    private javax.swing.JLabel subscriptionGoldTotal;
    private javax.swing.JLabel subscriptionPrimeTotal;
    private javax.swing.JLabel subscriptionStandardTotal;
    private javax.swing.JPanel undescorePanel;
    // End of variables declaration//GEN-END:variables
}
