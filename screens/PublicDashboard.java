/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fitnessapp.screens;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import fitnessapp.controllers.AccountPopupController;
import fitnessapp.controllers.CoachController;
import fitnessapp.controllers.MemberController;
import fitnessapp.controllers.PlanningController;
import fitnessapp.controllers.PublicDashboardController;
import fitnessapp.models.ActivityModel;
import fitnessapp.models.AuthResponse;
import fitnessapp.models.SubscriptionModel;
import fitnessapp.utilities.Constants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import raven.toast.Notifications;

/**
 *
 * @author orion90
 */
public class PublicDashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    private final MemberController memberController;
    private final CoachController coachController;
    private final PlanningController planningController;
    private AccountPopupController accountPopupController=null;

    public PublicDashboard(AuthResponse authResponse) {
        initComponents();

        memberController = new MemberController(null,
                btnAddMember, tableMember, inputSearchMember,
                this, btnResubscribe,
                authResponse.token(),authResponse.roomId());
        final PublicDashboardController controller = new PublicDashboardController(
                this,
                inputCheckMember,
                btnValidCheck,
                firstNameMember,
                lastNameMember,
                birthdateMember,
                addressMember,
                comboActivities,
                comboSubscription,
                priceToPay,
                remainMontant,
                priceSubscription,
                btnSaveMember,
                msgFirstName,
                msgLastName,
                msgAddress,
                msgMontant,
                btnRefreshing,
                authResponse.token(),
                authResponse.roomId()
        );
        
        coachController = new CoachController(
                inputSearchCoach,
                null,
                null,
                tableCoach,
                null,
                authResponse.token(), this, 
                filterActivityCoach,
                false);

        planningController = new PlanningController(this,
                ComboFilterDay,
                ComboFilterActivity,
                null, resetAction,
                btnDeletePlanning,
                btnAddPlanning, tablePlanning,
                numberPlanningSelected,
                authResponse.token(),
                null,
                false,
                authResponse.roomId()
        );
        controller.subscribe(memberController,planningController,coachController);
        firstNameMember.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrer le prenom du client.");
        lastNameMember.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrer le nom du client.");
        addressMember.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrer l'adresse du client.");
        priceToPay.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Montant du client.");
        birthdateMember.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "yyyy-MM-dd");

        inputCheckMember.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Entrer identification EMF du membres...");
        inputCheckMember.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(Constants.ICONS_PATH + "search.svg"));
        undescore.putClientProperty(FlatClientProperties.STYLE, "arc:20");
        btnValidCheck.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "check.svg"));
        btnSaveMember.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "save.svg"));
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(accountPopupController!=null){
                    accountPopupController.dispose();
                    accountPopupController=null;
                }
            }

        });
        container.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(accountPopupController!=null){
                    accountPopupController.dispose();
                    accountPopupController=null;
                }
            }

        });
        
        signOut.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "accountUser.svg"));
        signOut.addActionListener(l -> {
            if(accountPopupController==null){
                accountPopupController=new AccountPopupController(this,authResponse.token(),authResponse.username(),signOut.getLocation());
                accountPopupController.show();
            }else{
                accountPopupController.dispose();
                accountPopupController=null;
            }          
        });
        
        init();
    }

    

    private void init() {
        menu.setIconAt(0, new FlatSVGIcon(Constants.ICONS_PATH + "home.svg"));
        menu.setIconAt(1, new FlatSVGIcon(Constants.ICONS_PATH + "planning.svg"));
        menu.setIconAt(2, new FlatSVGIcon(Constants.ICONS_PATH + "members.svg"));
        menu.setIconAt(3, new FlatSVGIcon(Constants.ICONS_PATH + "coach.svg"));
        menu.putClientProperty("FlatLaf.style", "font: semibold $h3.regular.font;");
        Notifications.getInstance().setJFrame(this);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        dateChooser1 = new com.raven.datechooser.DateChooser();
        container = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        signOut = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnRefreshing = new javax.swing.JButton();
        menu = new javax.swing.JTabbedPane();
        home = new javax.swing.JPanel();
        homeContainer = new javax.swing.JPanel();
        infoContainer = new javax.swing.JPanel();
        inputCheckMember = new javax.swing.JTextField();
        btnValidCheck = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        right = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        priceToPay = new javax.swing.JTextField();
        priceSubscription = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        remainMontant = new javax.swing.JLabel();
        btnSaveMember = new javax.swing.JButton();
        msgMontant = new javax.swing.JLabel();
        left = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        containerInputFirstName = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        firstNameMember = new javax.swing.JTextField();
        msgFirstName = new javax.swing.JLabel();
        containerInputLastName = new javax.swing.JPanel();
        lastNameMember = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        msgLastName = new javax.swing.JLabel();
        containerTitle = new javax.swing.JPanel();
        undescore = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        birthdateAndAddress = new javax.swing.JPanel();
        containerInputAddress = new javax.swing.JPanel();
        msgAddress = new javax.swing.JLabel();
        addressMember = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        containerInputBirthdate = new javax.swing.JPanel();
        birthdateMember = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        msgBirthdate = new javax.swing.JLabel();
        subscriptionAndActivities = new javax.swing.JPanel();
        containerSubscription = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        comboSubscription = new javax.swing.JComboBox<>();
        containerActivities = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        comboActivities = new javax.swing.JComboBox<>();
        plannings = new javax.swing.JPanel();
        planningContainer = new javax.swing.JPanel();
        planningHeader = new javax.swing.JPanel();
        centerHeader = new javax.swing.JPanel();
        headerLeft = new javax.swing.JPanel();
        btnDeletePlanning = new javax.swing.JButton();
        numberPlanningSelected = new javax.swing.JLabel();
        headerCombox = new javax.swing.JPanel();
        ComboFilterDay = new javax.swing.JComboBox<>();
        ComboFilterActivity = new javax.swing.JComboBox<>();
        headerRightCombox = new javax.swing.JPanel();
        resetAction = new javax.swing.JButton();
        btnAddPlanning = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablePlanning = new javax.swing.JTable();
        members = new javax.swing.JPanel();
        memberContainer = new javax.swing.JPanel();
        memberMain = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableMember = new javax.swing.JTable();
        memberHeader = new javax.swing.JPanel();
        memberHeaderLeft = new javax.swing.JPanel();
        memberHeaderCenter = new javax.swing.JPanel();
        inputSearchMember = new javax.swing.JTextField();
        memberHeaderRight = new javax.swing.JPanel();
        btnAddMember = new javax.swing.JButton();
        btnResubscribe = new javax.swing.JButton();
        coach = new javax.swing.JPanel();
        coachHeader = new javax.swing.JPanel();
        coachHeaderSideLeft = new javax.swing.JPanel();
        filterActivityCoach = new javax.swing.JComboBox<>();
        coachHeaderCenter = new javax.swing.JPanel();
        inputSearchCoach = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableCoach = new javax.swing.JTable();

        dateChooser1.setForeground(new java.awt.Color(102, 204, 0));
        dateChooser1.setDateFormat("yyyy-MM-dd");
        dateChooser1.setTextRefernce(birthdateMember);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setName("dashboard"); // NOI18N

        container.setBackground(new java.awt.Color(255, 255, 255));
        container.setLayout(new java.awt.BorderLayout(5, 5));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        header.setOpaque(false);
        header.setPreferredSize(new java.awt.Dimension(1200, 80));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fitnessapp/icons/emf50x50.png"))); // NOI18N

        signOut.setBackground(new java.awt.Color(255, 255, 255));
        signOut.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        signOut.setForeground(new java.awt.Color(0, 0, 0));
        signOut.setToolTipText("Se deconnecter");
        signOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signOut.setMaximumSize(new java.awt.Dimension(40, 40));
        signOut.setMinimumSize(new java.awt.Dimension(40, 40));
        signOut.setPreferredSize(new java.awt.Dimension(50, 50));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fitnessapp/icons/chevronDown.png"))); // NOI18N

        btnRefreshing.setText("Refresh");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 906, Short.MAX_VALUE)
                .addComponent(btnRefreshing, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(signOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRefreshing, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1)
                        .addComponent(signOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, headerLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        container.add(header, java.awt.BorderLayout.PAGE_START);

        menu.setBackground(new java.awt.Color(255, 255, 255));
        menu.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        menu.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        home.setBackground(new java.awt.Color(255, 255, 255));
        home.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 5, 20, 5));
        home.setToolTipText("");
        home.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        home.setLayout(new java.awt.BorderLayout());

        homeContainer.setBackground(new java.awt.Color(255, 255, 255));
        homeContainer.setLayout(new java.awt.BorderLayout(0, 10));

        infoContainer.setBackground(new java.awt.Color(255, 255, 255));
        infoContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 20));
        infoContainer.setPreferredSize(new java.awt.Dimension(1069, 50));
        infoContainer.setLayout(new java.awt.BorderLayout());

        inputCheckMember.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        infoContainer.add(inputCheckMember, java.awt.BorderLayout.CENTER);

        btnValidCheck.setBackground(new java.awt.Color(23, 174, 1));
        btnValidCheck.setMaximumSize(new java.awt.Dimension(50, 50));
        btnValidCheck.setMinimumSize(new java.awt.Dimension(50, 50));
        btnValidCheck.setPreferredSize(new java.awt.Dimension(50, 50));
        infoContainer.add(btnValidCheck, java.awt.BorderLayout.LINE_END);

        homeContainer.add(infoContainer, java.awt.BorderLayout.PAGE_START);

        main.setBackground(new java.awt.Color(255, 255, 255));
        main.setOpaque(false);
        main.setLayout(new java.awt.BorderLayout());

        right.setBackground(new java.awt.Color(255, 255, 255));
        right.setPreferredSize(new java.awt.Dimension(400, 616));
        right.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(153, 204, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(5, 625));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 616, Short.MAX_VALUE)
        );

        right.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        priceToPay.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        priceToPay.setForeground(new java.awt.Color(0, 0, 0));

        priceSubscription.setFont(new java.awt.Font("SansSerif", 1, 35)); // NOI18N
        priceSubscription.setForeground(new java.awt.Color(0, 153, 0));
        priceSubscription.setText("0");

        jPanel5.setBackground(new java.awt.Color(142, 187, 7));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        remainMontant.setFont(new java.awt.Font("SansSerif", 1, 40)); // NOI18N
        remainMontant.setForeground(new java.awt.Color(0, 153, 51));
        remainMontant.setText("0");

        btnSaveMember.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnSaveMember.setForeground(new java.awt.Color(51, 153, 0));
        btnSaveMember.setText("Enrégistrer");

        msgMontant.setForeground(new java.awt.Color(255, 51, 0));
        msgMontant.setPreferredSize(new java.awt.Dimension(64, 30));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceSubscription, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(remainMontant, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceToPay)
                            .addComponent(msgMontant, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnSaveMember, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(msgMontant, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceToPay, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(priceSubscription, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(remainMontant, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addComponent(btnSaveMember, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        right.add(jPanel2, java.awt.BorderLayout.CENTER);

        main.add(right, java.awt.BorderLayout.LINE_END);

        left.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setOpaque(false);

        containerInputFirstName.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Pénom");

        msgFirstName.setForeground(new java.awt.Color(255, 51, 51));
        msgFirstName.setMaximumSize(new java.awt.Dimension(56, 30));
        msgFirstName.setMinimumSize(new java.awt.Dimension(56, 30));
        msgFirstName.setPreferredSize(new java.awt.Dimension(56, 20));

        javax.swing.GroupLayout containerInputFirstNameLayout = new javax.swing.GroupLayout(containerInputFirstName);
        containerInputFirstName.setLayout(containerInputFirstNameLayout);
        containerInputFirstNameLayout.setHorizontalGroup(
            containerInputFirstNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerInputFirstNameLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(containerInputFirstNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerInputFirstNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(msgFirstName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(firstNameMember, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE))
                    .addComponent(jLabel3))
                .addContainerGap())
        );
        containerInputFirstNameLayout.setVerticalGroup(
            containerInputFirstNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerInputFirstNameLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(firstNameMember, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(msgFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        containerInputLastName.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Nom");

        msgLastName.setForeground(new java.awt.Color(255, 0, 0));
        msgLastName.setMaximumSize(new java.awt.Dimension(64, 30));
        msgLastName.setMinimumSize(new java.awt.Dimension(64, 30));
        msgLastName.setPreferredSize(new java.awt.Dimension(64, 20));

        javax.swing.GroupLayout containerInputLastNameLayout = new javax.swing.GroupLayout(containerInputLastName);
        containerInputLastName.setLayout(containerInputLastNameLayout);
        containerInputLastNameLayout.setHorizontalGroup(
            containerInputLastNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerInputLastNameLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(containerInputLastNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(containerInputLastNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(msgLastName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lastNameMember, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addGap(0, 0, 0))
        );
        containerInputLastNameLayout.setVerticalGroup(
            containerInputLastNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerInputLastNameLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addComponent(lastNameMember, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(msgLastName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        containerTitle.setOpaque(false);

        undescore.setBackground(new java.awt.Color(117, 195, 0));

        javax.swing.GroupLayout undescoreLayout = new javax.swing.GroupLayout(undescore);
        undescore.setLayout(undescoreLayout);
        undescoreLayout.setHorizontalGroup(
            undescoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 63, Short.MAX_VALUE)
        );
        undescoreLayout.setVerticalGroup(
            undescoreLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ajouter un membre");

        javax.swing.GroupLayout containerTitleLayout = new javax.swing.GroupLayout(containerTitle);
        containerTitle.setLayout(containerTitleLayout);
        containerTitleLayout.setHorizontalGroup(
            containerTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(containerTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, containerTitleLayout.createSequentialGroup()
                        .addComponent(undescore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)))
                .addContainerGap())
        );
        containerTitleLayout.setVerticalGroup(
            containerTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(undescore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        birthdateAndAddress.setOpaque(false);

        containerInputAddress.setOpaque(false);

        msgAddress.setForeground(new java.awt.Color(255, 51, 0));
        msgAddress.setMaximumSize(new java.awt.Dimension(63, 30));
        msgAddress.setMinimumSize(new java.awt.Dimension(63, 30));
        msgAddress.setPreferredSize(new java.awt.Dimension(63, 20));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Adresse");

        javax.swing.GroupLayout containerInputAddressLayout = new javax.swing.GroupLayout(containerInputAddress);
        containerInputAddress.setLayout(containerInputAddressLayout);
        containerInputAddressLayout.setHorizontalGroup(
            containerInputAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerInputAddressLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(containerInputAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(addressMember)
                    .addComponent(msgAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        containerInputAddressLayout.setVerticalGroup(
            containerInputAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerInputAddressLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addGap(0, 0, 0)
                .addComponent(addressMember, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(msgAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        containerInputBirthdate.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Date de naissance");

        msgBirthdate.setForeground(new java.awt.Color(255, 0, 0));
        msgBirthdate.setMaximumSize(new java.awt.Dimension(61, 30));
        msgBirthdate.setMinimumSize(new java.awt.Dimension(61, 30));
        msgBirthdate.setPreferredSize(new java.awt.Dimension(61, 20));

        javax.swing.GroupLayout containerInputBirthdateLayout = new javax.swing.GroupLayout(containerInputBirthdate);
        containerInputBirthdate.setLayout(containerInputBirthdateLayout);
        containerInputBirthdateLayout.setHorizontalGroup(
            containerInputBirthdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerInputBirthdateLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(containerInputBirthdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(msgBirthdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(birthdateMember, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(0, 0, 0))
        );
        containerInputBirthdateLayout.setVerticalGroup(
            containerInputBirthdateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerInputBirthdateLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(birthdateMember, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(msgBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout birthdateAndAddressLayout = new javax.swing.GroupLayout(birthdateAndAddress);
        birthdateAndAddress.setLayout(birthdateAndAddressLayout);
        birthdateAndAddressLayout.setHorizontalGroup(
            birthdateAndAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(birthdateAndAddressLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(containerInputBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(containerInputAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        birthdateAndAddressLayout.setVerticalGroup(
            birthdateAndAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(birthdateAndAddressLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(birthdateAndAddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(containerInputBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(containerInputAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        subscriptionAndActivities.setOpaque(false);

        containerSubscription.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Abonnements");

        javax.swing.GroupLayout containerSubscriptionLayout = new javax.swing.GroupLayout(containerSubscription);
        containerSubscription.setLayout(containerSubscriptionLayout);
        containerSubscriptionLayout.setHorizontalGroup(
            containerSubscriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerSubscriptionLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(containerSubscriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(comboSubscription, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        containerSubscriptionLayout.setVerticalGroup(
            containerSubscriptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerSubscriptionLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel7)
                .addGap(0, 0, 0)
                .addComponent(comboSubscription, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        containerActivities.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("SansSerif", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Activités");

        javax.swing.GroupLayout containerActivitiesLayout = new javax.swing.GroupLayout(containerActivities);
        containerActivities.setLayout(containerActivitiesLayout);
        containerActivitiesLayout.setHorizontalGroup(
            containerActivitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerActivitiesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(containerActivitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(comboActivities, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        containerActivitiesLayout.setVerticalGroup(
            containerActivitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(containerActivitiesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel8)
                .addGap(0, 0, 0)
                .addComponent(comboActivities, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout subscriptionAndActivitiesLayout = new javax.swing.GroupLayout(subscriptionAndActivities);
        subscriptionAndActivities.setLayout(subscriptionAndActivitiesLayout);
        subscriptionAndActivitiesLayout.setHorizontalGroup(
            subscriptionAndActivitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subscriptionAndActivitiesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(containerSubscription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(containerActivities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        subscriptionAndActivitiesLayout.setVerticalGroup(
            subscriptionAndActivitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subscriptionAndActivitiesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(subscriptionAndActivitiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(containerActivities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(containerSubscription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(containerInputLastName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(containerInputFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(223, 223, 223)
                        .addComponent(containerTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(202, 202, 202))
                    .addComponent(birthdateAndAddress, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subscriptionAndActivities, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(containerTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(containerInputFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(containerInputLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(birthdateAndAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(subscriptionAndActivities, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        left.add(jPanel3);

        main.add(left, java.awt.BorderLayout.CENTER);

        homeContainer.add(main, java.awt.BorderLayout.CENTER);

        home.add(homeContainer, java.awt.BorderLayout.CENTER);

        menu.addTab("Accueil", null, home, "Accueil");

        plannings.setBackground(new java.awt.Color(255, 255, 255));
        plannings.setLayout(new java.awt.BorderLayout(0, 10));

        planningContainer.setBackground(new java.awt.Color(255, 255, 255));
        planningContainer.setLayout(new java.awt.BorderLayout(0, 10));

        java.awt.GridBagLayout planningHeaderLayout = new java.awt.GridBagLayout();
        planningHeaderLayout.columnWidths = new int[] {120, 700, 120};
        planningHeaderLayout.rowHeights = new int[] {50};
        planningHeaderLayout.columnWeights = new double[] {0.5, 0.5, 0.5};
        planningHeader.setLayout(planningHeaderLayout);

        centerHeader.setPreferredSize(new java.awt.Dimension(500, 50));
        java.awt.GridBagLayout centerHeaderLayout = new java.awt.GridBagLayout();
        centerHeaderLayout.columnWidths = new int[] {100, 400, 120};
        centerHeaderLayout.rowHeights = new int[] {50};
        centerHeaderLayout.columnWeights = new double[] {0.2, 0.7, 0.2};
        centerHeader.setLayout(centerHeaderLayout);

        headerLeft.setMinimumSize(new java.awt.Dimension(100, 50));
        headerLeft.setPreferredSize(new java.awt.Dimension(100, 50));

        btnDeletePlanning.setBackground(new java.awt.Color(255, 0, 0));
        btnDeletePlanning.setPreferredSize(new java.awt.Dimension(50, 50));

        numberPlanningSelected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numberPlanningSelected.setText("(0)");
        numberPlanningSelected.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout headerLeftLayout = new javax.swing.GroupLayout(headerLeft);
        headerLeft.setLayout(headerLeftLayout);
        headerLeftLayout.setHorizontalGroup(
            headerLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberPlanningSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnDeletePlanning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        headerLeftLayout.setVerticalGroup(
            headerLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDeletePlanning, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(headerLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberPlanningSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        centerHeader.add(headerLeft, gridBagConstraints);

        headerCombox.setPreferredSize(new java.awt.Dimension(400, 50));
        java.awt.GridBagLayout headerComboxLayout = new java.awt.GridBagLayout();
        headerComboxLayout.columnWeights = new double[] {0.3, 0.8};
        headerCombox.setLayout(headerComboxLayout);

        ComboFilterDay.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        ComboFilterDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tous les jours", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche", " " }));
        ComboFilterDay.setMinimumSize(new java.awt.Dimension(200, 50));
        ComboFilterDay.setPreferredSize(new java.awt.Dimension(200, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        headerCombox.add(ComboFilterDay, gridBagConstraints);

        ComboFilterActivity.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        ComboFilterActivity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Toutes les activites" }));
        ComboFilterActivity.setMinimumSize(new java.awt.Dimension(250, 50));
        ComboFilterActivity.setPreferredSize(new java.awt.Dimension(250, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        headerCombox.add(ComboFilterActivity, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        centerHeader.add(headerCombox, gridBagConstraints);

        headerRightCombox.setPreferredSize(new java.awt.Dimension(200, 50));

        resetAction.setBackground(new java.awt.Color(74, 134, 255));
        resetAction.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        resetAction.setForeground(new java.awt.Color(255, 255, 255));
        resetAction.setText("Reset");
        resetAction.setPreferredSize(new java.awt.Dimension(120, 50));

        btnAddPlanning.setBackground(new java.awt.Color(102, 204, 0));
        btnAddPlanning.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout headerRightComboxLayout = new javax.swing.GroupLayout(headerRightCombox);
        headerRightCombox.setLayout(headerRightComboxLayout);
        headerRightComboxLayout.setHorizontalGroup(
            headerRightComboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerRightComboxLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(resetAction, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddPlanning, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        headerRightComboxLayout.setVerticalGroup(
            headerRightComboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerRightComboxLayout.createSequentialGroup()
                .addGroup(headerRightComboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resetAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddPlanning, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        centerHeader.add(headerRightCombox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        planningHeader.add(centerHeader, gridBagConstraints);

        planningContainer.add(planningHeader, java.awt.BorderLayout.PAGE_START);

        tablePlanning.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PlannigId", "Jour", "Heure du debut", "Heure de fin", "Activité", "Salle"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tablePlanning);

        planningContainer.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        plannings.add(planningContainer, java.awt.BorderLayout.CENTER);

        menu.addTab("Plannings", plannings);

        members.setBackground(new java.awt.Color(255, 255, 255));
        members.setLayout(new java.awt.BorderLayout());

        memberContainer.setBackground(new java.awt.Color(255, 255, 255));
        memberContainer.setLayout(new java.awt.BorderLayout());

        memberMain.setOpaque(false);
        memberMain.setLayout(new java.awt.BorderLayout());

        tableMember.setBackground(new java.awt.Color(255, 255, 255));
        tableMember.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Prenom", "Nom", "Date de naissance", "Adresse", "Action", "CustomerId", "RoomName", "Expirate", "SubscriptionType"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMember.setRowHeight(45);
        jScrollPane3.setViewportView(tableMember);

        memberMain.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        memberHeader.setOpaque(false);
        memberHeader.setPreferredSize(new java.awt.Dimension(1079, 60));
        memberHeader.setLayout(new java.awt.GridBagLayout());

        memberHeaderLeft.setMaximumSize(new java.awt.Dimension(200, 61));
        memberHeaderLeft.setOpaque(false);
        memberHeaderLeft.setPreferredSize(new java.awt.Dimension(200, 61));

        javax.swing.GroupLayout memberHeaderLeftLayout = new javax.swing.GroupLayout(memberHeaderLeft);
        memberHeaderLeft.setLayout(memberHeaderLeftLayout);
        memberHeaderLeftLayout.setHorizontalGroup(
            memberHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        memberHeaderLeftLayout.setVerticalGroup(
            memberHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        memberHeader.add(memberHeaderLeft, new java.awt.GridBagConstraints());

        memberHeaderCenter.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 10));
        memberHeaderCenter.setMinimumSize(new java.awt.Dimension(85, 50));
        memberHeaderCenter.setOpaque(false);
        memberHeaderCenter.setPreferredSize(new java.awt.Dimension(500, 38));
        memberHeaderCenter.setLayout(new java.awt.BorderLayout());

        inputSearchMember.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        inputSearchMember.setPreferredSize(new java.awt.Dimension(95, 50));
        memberHeaderCenter.add(inputSearchMember, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        memberHeader.add(memberHeaderCenter, gridBagConstraints);

        memberHeaderRight.setOpaque(false);

        btnAddMember.setBackground(new java.awt.Color(130, 185, 0));
        btnAddMember.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        btnAddMember.setForeground(new java.awt.Color(255, 255, 255));
        btnAddMember.setMinimumSize(new java.awt.Dimension(106, 50));
        btnAddMember.setPreferredSize(new java.awt.Dimension(106, 50));

        btnResubscribe.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnResubscribe.setForeground(new java.awt.Color(51, 153, 0));
        btnResubscribe.setText("Réabonner");

        javax.swing.GroupLayout memberHeaderRightLayout = new javax.swing.GroupLayout(memberHeaderRight);
        memberHeaderRight.setLayout(memberHeaderRightLayout);
        memberHeaderRightLayout.setHorizontalGroup(
            memberHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memberHeaderRightLayout.createSequentialGroup()
                .addComponent(btnResubscribe)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddMember, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        memberHeaderRightLayout.setVerticalGroup(
            memberHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memberHeaderRightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(memberHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAddMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnResubscribe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );

        memberHeader.add(memberHeaderRight, new java.awt.GridBagConstraints());

        memberMain.add(memberHeader, java.awt.BorderLayout.PAGE_START);

        memberContainer.add(memberMain, java.awt.BorderLayout.CENTER);

        members.add(memberContainer, java.awt.BorderLayout.CENTER);

        menu.addTab("Membres", members);

        coach.setBackground(new java.awt.Color(255, 255, 255));
        coach.setOpaque(false);
        coach.setLayout(new java.awt.BorderLayout());

        coachHeader.setLayout(new java.awt.GridBagLayout());

        coachHeaderSideLeft.setPreferredSize(new java.awt.Dimension(250, 50));

        filterActivityCoach.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        filterActivityCoach.setMinimumSize(new java.awt.Dimension(200, 50));
        filterActivityCoach.setPreferredSize(new java.awt.Dimension(250, 50));

        javax.swing.GroupLayout coachHeaderSideLeftLayout = new javax.swing.GroupLayout(coachHeaderSideLeft);
        coachHeaderSideLeft.setLayout(coachHeaderSideLeftLayout);
        coachHeaderSideLeftLayout.setHorizontalGroup(
            coachHeaderSideLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coachHeaderSideLeftLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(filterActivityCoach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        coachHeaderSideLeftLayout.setVerticalGroup(
            coachHeaderSideLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coachHeaderSideLeftLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(filterActivityCoach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        coachHeader.add(coachHeaderSideLeft, gridBagConstraints);

        coachHeaderCenter.setPreferredSize(new java.awt.Dimension(500, 50));

        inputSearchCoach.setPreferredSize(new java.awt.Dimension(500, 50));

        javax.swing.GroupLayout coachHeaderCenterLayout = new javax.swing.GroupLayout(coachHeaderCenter);
        coachHeaderCenter.setLayout(coachHeaderCenterLayout);
        coachHeaderCenterLayout.setHorizontalGroup(
            coachHeaderCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coachHeaderCenterLayout.createSequentialGroup()
                .addComponent(inputSearchCoach, javax.swing.GroupLayout.DEFAULT_SIZE, 893, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        coachHeaderCenterLayout.setVerticalGroup(
            coachHeaderCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coachHeaderCenterLayout.createSequentialGroup()
                .addComponent(inputSearchCoach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        coachHeader.add(coachHeaderCenter, gridBagConstraints);

        coach.add(coachHeader, java.awt.BorderLayout.PAGE_START);

        tableCoach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prenom", "Nom", "Phone", "Adresse", "Spécilité"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tableCoach);

        coach.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        menu.addTab("Entraineurs", coach);

        container.add(menu, java.awt.BorderLayout.CENTER);

        getContentPane().add(container, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboFilterActivity;
    private javax.swing.JComboBox<String> ComboFilterDay;
    private javax.swing.JTextField addressMember;
    private javax.swing.JPanel birthdateAndAddress;
    private javax.swing.JTextField birthdateMember;
    private javax.swing.JButton btnAddMember;
    private javax.swing.JButton btnAddPlanning;
    private javax.swing.JButton btnDeletePlanning;
    private javax.swing.JButton btnRefreshing;
    private javax.swing.JButton btnResubscribe;
    private javax.swing.JButton btnSaveMember;
    private javax.swing.JButton btnValidCheck;
    private javax.swing.JPanel centerHeader;
    private javax.swing.JPanel coach;
    private javax.swing.JPanel coachHeader;
    private javax.swing.JPanel coachHeaderCenter;
    private javax.swing.JPanel coachHeaderSideLeft;
    private javax.swing.JComboBox<ActivityModel> comboActivities;
    private javax.swing.JComboBox<SubscriptionModel> comboSubscription;
    private javax.swing.JPanel container;
    private javax.swing.JPanel containerActivities;
    private javax.swing.JPanel containerInputAddress;
    private javax.swing.JPanel containerInputBirthdate;
    private javax.swing.JPanel containerInputFirstName;
    private javax.swing.JPanel containerInputLastName;
    private javax.swing.JPanel containerSubscription;
    private javax.swing.JPanel containerTitle;
    private com.raven.datechooser.DateChooser dateChooser1;
    private javax.swing.JComboBox<String> filterActivityCoach;
    private javax.swing.JTextField firstNameMember;
    private javax.swing.JPanel header;
    private javax.swing.JPanel headerCombox;
    private javax.swing.JPanel headerLeft;
    private javax.swing.JPanel headerRightCombox;
    private javax.swing.JPanel home;
    private javax.swing.JPanel homeContainer;
    private javax.swing.JPanel infoContainer;
    private javax.swing.JTextField inputCheckMember;
    private javax.swing.JTextField inputSearchCoach;
    private javax.swing.JTextField inputSearchMember;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField lastNameMember;
    private javax.swing.JPanel left;
    private javax.swing.JPanel main;
    private javax.swing.JPanel memberContainer;
    private javax.swing.JPanel memberHeader;
    private javax.swing.JPanel memberHeaderCenter;
    private javax.swing.JPanel memberHeaderLeft;
    private javax.swing.JPanel memberHeaderRight;
    private javax.swing.JPanel memberMain;
    private javax.swing.JPanel members;
    private javax.swing.JTabbedPane menu;
    private javax.swing.JLabel msgAddress;
    private javax.swing.JLabel msgBirthdate;
    private javax.swing.JLabel msgFirstName;
    private javax.swing.JLabel msgLastName;
    private javax.swing.JLabel msgMontant;
    private javax.swing.JLabel numberPlanningSelected;
    private javax.swing.JPanel planningContainer;
    private javax.swing.JPanel planningHeader;
    private javax.swing.JPanel plannings;
    private javax.swing.JLabel priceSubscription;
    private javax.swing.JTextField priceToPay;
    private javax.swing.JLabel remainMontant;
    private javax.swing.JButton resetAction;
    private javax.swing.JPanel right;
    private javax.swing.JButton signOut;
    private javax.swing.JPanel subscriptionAndActivities;
    private javax.swing.JTable tableCoach;
    private javax.swing.JTable tableMember;
    private javax.swing.JTable tablePlanning;
    private javax.swing.JPanel undescore;
    // End of variables declaration//GEN-END:variables
}
