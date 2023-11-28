/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fitnessapp.screens;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author orion90
 */
public class RoomModal extends javax.swing.JDialog {

    /**
     * Creates new form ActivityModal
     */
    private final String flatStyle = FlatClientProperties.STYLE;

    public RoomModal() {
        FlatRobotoFont.install();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 15));
        FlatMacLightLaf.registerCustomDefaultsSource("fitnessapp.properties");
        FlatMacLightLaf.setup();
        this.setBackground(null);
        this.setUndecorated(true);
        initComponents();
       
        btnAdded.putClientProperty(flatStyle, "arc:20;background:@accentColor;foreground:#ffffff");
        btnAdded.setIcon(new FlatSVGIcon("fitnessapp/icons/plus.svg"));
        container.putClientProperty(flatStyle, "arc:20;background:#fff");
        btnClose.setIcon(new FlatSVGIcon("fitnessapp/icons/close.svg"));
        btnClose.putClientProperty(flatStyle, "buttonType:roundRect;borderColor:#fff");
        
    }

    public JButton getBtnAdded() {
        return btnAdded;
    }

    public void setBtnAdded(JButton btnAdded) {
        this.btnAdded = btnAdded;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButton btnClose) {
        this.btnClose = btnClose;
    }
    public JTextField getInputRoomName() {
        return inputRoomName;
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
        jLabel1 = new javax.swing.JLabel();
        btnClose = new javax.swing.JButton();
        center = new javax.swing.JPanel();
        labelContainer = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        inputRoomName = new javax.swing.JTextField();
        footer = new javax.swing.JPanel();
        btnAdded = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(400, 370));
        setMinimumSize(new java.awt.Dimension(400, 370));
        setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setName("Ajouter Salle"); // NOI18N
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(400, 370));
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        container.setBackground(new java.awt.Color(255, 255, 255));
        container.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 2, true));
        container.setMaximumSize(new java.awt.Dimension(400, 300));
        container.setMinimumSize(new java.awt.Dimension(400, 300));
        container.setPreferredSize(new java.awt.Dimension(400, 250));
        container.setLayout(new java.awt.BorderLayout());

        header.setBackground(new java.awt.Color(220, 220, 220));
        header.setOpaque(false);
        header.setPreferredSize(new java.awt.Dimension(600, 60));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Ajouter une Salle");

        btnClose.setContentAreaFilled(false);
        btnClose.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE)
                .addGap(58, 58, 58))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        container.add(header, java.awt.BorderLayout.PAGE_START);

        center.setBackground(new java.awt.Color(221, 221, 221));
        center.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 20));
        center.setOpaque(false);
        center.setPreferredSize(new java.awt.Dimension(376, 60));
        center.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelContainer.setBackground(new java.awt.Color(255, 255, 255));
        labelContainer.setMaximumSize(new java.awt.Dimension(400, 146));
        labelContainer.setMinimumSize(new java.awt.Dimension(400, 146));
        labelContainer.setName(""); // NOI18N
        labelContainer.setOpaque(false);
        labelContainer.setPreferredSize(new java.awt.Dimension(400, 100));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Nom de salle");

        inputRoomName.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        javax.swing.GroupLayout labelContainerLayout = new javax.swing.GroupLayout(labelContainer);
        labelContainer.setLayout(labelContainerLayout);
        labelContainerLayout.setHorizontalGroup(
            labelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelContainerLayout.createSequentialGroup()
                .addGroup(labelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputRoomName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addGroup(labelContainerLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        labelContainerLayout.setVerticalGroup(
            labelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelContainerLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputRoomName, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        center.add(labelContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 356, 120));

        container.add(center, java.awt.BorderLayout.CENTER);

        footer.setBackground(new java.awt.Color(221, 221, 221));
        footer.setOpaque(false);

        btnAdded.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnAdded.setForeground(new java.awt.Color(255, 255, 255));
        btnAdded.setText("Ajouter");

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(btnAdded, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(btnAdded, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        container.add(footer, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(container, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdded;
    private javax.swing.JButton btnClose;
    private javax.swing.JPanel center;
    private javax.swing.JPanel container;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JTextField inputRoomName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel labelContainer;
    // End of variables declaration//GEN-END:variables
}