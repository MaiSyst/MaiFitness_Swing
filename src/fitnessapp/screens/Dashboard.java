/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package fitnessapp.screens;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import fitnessapp.controllers.AccountPopupController;
import fitnessapp.controllers.ActivityController;
import fitnessapp.controllers.CoachController;
import fitnessapp.controllers.DashboardController;
import fitnessapp.controllers.MemberController;
import fitnessapp.controllers.PlanningController;
import fitnessapp.controllers.RoomController;
import fitnessapp.controllers.SubscriptionController;
import fitnessapp.controllers.UserController;
import fitnessapp.models.AuthResponse;
import fitnessapp.utilities.Constants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import raven.toast.Notifications;

/**
 *
 * @author orion90
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    private static final String STYLE_CARD = "arc:20;";
    private static final String FLAT_STYLE = FlatClientProperties.STYLE;
    private final ActivityController activityController;
    private final RoomController roomController;
    private final MemberController memberController;
    private final CoachController coachController;
    private final SubscriptionController subscriptionController;
    private final PlanningController planningController;
    private final DashboardController controller;
    private final AuthResponse authResponse;
    private final UserController userController;
    private AccountPopupController accountPopupController;

    public Dashboard(AuthResponse authResponse) {
        this.authResponse = authResponse;
        initComponents();
        activityController = new ActivityController(this, btnAddActivity, inputSearchActivity,
                btnDeleteActivity, tableActivity,
                authResponse.token(),
                numberActivitiesSelected);
        
        controller = new DashboardController(suscribeActiveInfo,
                numberSubscribeStandard, numberSubscribeGold,
                numberSubscribePrime,
                annualMontant,
                btnRefreshHome,
                tableSubscribeActive, authResponse.token());
        roomController = new RoomController(
                this,
                btnAddRoom, roomBody, 
                inputSearchRoom, 
                authResponse.token(),
                controller.getDataList()
        );
        memberController = new MemberController(btnDeleteMember,
                btnAddMember, tableMember, inputSearchMember,
                this,btnResubcribe, authResponse.token(), controller);
        coachController = new CoachController(
                inputSearchCoach,
                btnDeleteCoach,
                btnAddCoach,
                tableCoach,
                numberCoachSelected,
                authResponse.token(), this, 
                filterActivityCoach,
                true);
        subscriptionController = new SubscriptionController(this,
                standardCard,
                primeCard,
                goldCard,
                standardMonth,
                standardPrice,
                primeMonth,
                primePrice,
                goldMonth,
                goldPrice,
                authResponse.token());
        planningController = new PlanningController(this,
                ComboFilterDay,
                ComboFilterActivity,
                ComboFilterRoom, resetAction,
                btnDeletePlanning,
                btnAddPlanning, tablePlanning,
                numberPlanningSelected, authResponse.token(),
                roomController,
                true
        );
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(accountPopupController!=null){
                    accountPopupController.dispose();
                }
            }

        });
        signOut.setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "accountUser.svg"));
        signOut.addActionListener(l -> {
            accountPopupController = new AccountPopupController(this,authResponse.token(),authResponse.username(), signOut.getLocation());
            accountPopupController.show();

        });
        userController = new UserController(
                this,
                btnAddUser,
                btnDeleteUser, inputSearchUser,
                numberUsersSelected, 
                tableUsers,
                authResponse.token(),
                roomController
        );
        roomController.subscribe(userController,planningController);
        activityController.subscribe(coachController,planningController);
        init();
    }

    private void init() {
        menu.setIconAt(0, new FlatSVGIcon(Constants.ICONS_PATH + "home.svg"));
        menu.setIconAt(1, new FlatSVGIcon(Constants.ICONS_PATH + "activity.svg"));
        menu.setIconAt(2, new FlatSVGIcon(Constants.ICONS_PATH + "planning.svg"));
        menu.setIconAt(3, new FlatSVGIcon(Constants.ICONS_PATH + "members.svg"));
        menu.setIconAt(4, new FlatSVGIcon(Constants.ICONS_PATH + "room.svg"));
        menu.setIconAt(5, new FlatSVGIcon(Constants.ICONS_PATH + "coach.svg"));
        menu.setIconAt(6, new FlatSVGIcon(Constants.ICONS_PATH + "user.svg"));
        menu.setIconAt(7, new FlatSVGIcon(Constants.ICONS_PATH + "subscription.svg"));
        menu.putClientProperty("FlatLaf.style", "font: semibold $h3.regular.font;");
        subscribeInfo.putClientProperty(FLAT_STYLE, STYLE_CARD + "background:#00ff7f30");
        subscribeInfoTotal.putClientProperty(FLAT_STYLE, STYLE_CARD + "background:#ff557f30");
        subscribeInfoMoney.putClientProperty(FLAT_STYLE, STYLE_CARD + "background:#aa55ff30");
        Notifications.getInstance().setJFrame(this);
        menu.addChangeListener(l->{
            JTabbedPane tabbed=(JTabbedPane)l.getSource();
            btnRefreshHome.setVisible(tabbed.getSelectedIndex()==0);
        });

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

        container = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        signOut = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnRefreshHome = new javax.swing.JButton();
        menu = new javax.swing.JTabbedPane();
        home = new javax.swing.JPanel();
        homeContainer = new javax.swing.JPanel();
        infoContainer = new javax.swing.JPanel();
        subscribeInfo = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        suscribeActiveInfo = new javax.swing.JLabel();
        subscribeInfoTotal = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        numberSubscribeStandard = new javax.swing.JLabel();
        numberSubscribePrime = new javax.swing.JLabel();
        numberSubscribeGold = new javax.swing.JLabel();
        subscribeInfoMoney = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        annualMontant = new javax.swing.JLabel();
        tableInfoContainer = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableSubscribeActive = new javax.swing.JTable();
        activity = new javax.swing.JPanel();
        activityContainer = new javax.swing.JPanel();
        activityMain = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableActivity = new javax.swing.JTable();
        activityHeader = new javax.swing.JPanel();
        activityHeaderLeft = new javax.swing.JPanel();
        btnDeleteActivity = new javax.swing.JButton();
        numberActivitiesSelected = new javax.swing.JLabel();
        activityHeaderCenter = new javax.swing.JPanel();
        inputSearchActivity = new javax.swing.JTextField();
        activityHeaderRight = new javax.swing.JPanel();
        btnAddActivity = new javax.swing.JButton();
        plannings = new javax.swing.JPanel();
        planningContainer = new javax.swing.JPanel();
        planningHeader = new javax.swing.JPanel();
        leftHeader = new javax.swing.JPanel();
        numberPlanningSelected = new javax.swing.JLabel();
        btnDeletePlanning = new javax.swing.JButton();
        centerHeader = new javax.swing.JPanel();
        headerCombox = new javax.swing.JPanel();
        ComboFilterDay = new javax.swing.JComboBox<>();
        ComboFilterActivity = new javax.swing.JComboBox<>();
        ComboFilterRoom = new javax.swing.JComboBox<>();
        headerRightCombox = new javax.swing.JPanel();
        resetAction = new javax.swing.JButton();
        rightHeader = new javax.swing.JPanel();
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
        btnDeleteMember = new javax.swing.JButton();
        numberMemberSelected = new javax.swing.JLabel();
        memberHeaderCenter = new javax.swing.JPanel();
        inputSearchMember = new javax.swing.JTextField();
        memberHeaderRight = new javax.swing.JPanel();
        btnAddMember = new javax.swing.JButton();
        btnResubcribe = new javax.swing.JButton();
        room = new javax.swing.JPanel();
        roomContainer = new javax.swing.JPanel();
        roomMain = new javax.swing.JPanel();
        roomHeader = new javax.swing.JPanel();
        roomHeaderLeft = new javax.swing.JPanel();
        roomHeaderCenter = new javax.swing.JPanel();
        inputSearchRoom = new javax.swing.JTextField();
        roomHeaderRight = new javax.swing.JPanel();
        btnAddRoom = new javax.swing.JButton();
        roomBody = new javax.swing.JPanel();
        coach = new javax.swing.JPanel();
        coachHeader = new javax.swing.JPanel();
        coachHeaderLeft = new javax.swing.JPanel();
        numberCoachSelected = new javax.swing.JLabel();
        btnDeleteCoach = new javax.swing.JButton();
        coachHeaderSideLeft = new javax.swing.JPanel();
        filterActivityCoach = new javax.swing.JComboBox<>();
        coachHeaderCenter = new javax.swing.JPanel();
        inputSearchCoach = new javax.swing.JTextField();
        coachHeaderRight = new javax.swing.JPanel();
        btnAddCoach = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableCoach = new javax.swing.JTable();
        users = new javax.swing.JPanel();
        usersContainer = new javax.swing.JPanel();
        userMain = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableUsers = new javax.swing.JTable();
        usersHeader = new javax.swing.JPanel();
        usersHeaderLeft = new javax.swing.JPanel();
        btnDeleteUser = new javax.swing.JButton();
        numberUsersSelected = new javax.swing.JLabel();
        userHeaderCenter = new javax.swing.JPanel();
        inputSearchUser = new javax.swing.JTextField();
        userHeaderRight = new javax.swing.JPanel();
        btnAddUser = new javax.swing.JButton();
        subscription = new javax.swing.JPanel();
        subscriptionContainer = new javax.swing.JPanel();
        subscriptionMain = new javax.swing.JPanel();
        subscriptionHeader = new javax.swing.JPanel();
        subscriptionHeaderRight = new javax.swing.JPanel();
        bodySubscription = new javax.swing.JPanel();
        standardCard = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        standardMonth = new javax.swing.JLabel();
        standardPrice = new javax.swing.JLabel();
        primeCard = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        primePrice = new javax.swing.JLabel();
        primeMonth = new javax.swing.JLabel();
        goldCard = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        goldMonth = new javax.swing.JLabel();
        goldPrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1200, 800));
        setName("dashboard"); // NOI18N

        container.setBackground(new java.awt.Color(255, 255, 255));
        container.setLayout(new java.awt.BorderLayout(5, 5));

        header.setBackground(new java.awt.Color(255, 255, 255));
        header.setOpaque(false);
        header.setPreferredSize(new java.awt.Dimension(1200, 60));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fitnessapp/icons/emf50x50.png"))); // NOI18N

        signOut.setBackground(new java.awt.Color(255, 255, 255));
        signOut.setToolTipText("Se deconnecter");
        signOut.setMaximumSize(new java.awt.Dimension(35, 35));
        signOut.setMinimumSize(new java.awt.Dimension(35, 35));
        signOut.setPreferredSize(new java.awt.Dimension(50, 50));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fitnessapp/icons/chevronDown.png"))); // NOI18N

        btnRefreshHome.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnRefreshHome.setForeground(new java.awt.Color(102, 204, 0));
        btnRefreshHome.setText("Refresh");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 842, Short.MAX_VALUE)
                .addComponent(btnRefreshHome, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(signOut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addGap(26, 26, 26))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefreshHome, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(signOut, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        homeContainer.setLayout(new java.awt.BorderLayout());

        infoContainer.setBackground(new java.awt.Color(255, 255, 255));
        infoContainer.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 20, 20, 20));
        infoContainer.setPreferredSize(new java.awt.Dimension(1069, 200));
        infoContainer.setLayout(new java.awt.GridLayout(1, 1, 20, 0));

        subscribeInfo.setBackground(new java.awt.Color(51, 51, 255));
        subscribeInfo.setMaximumSize(new java.awt.Dimension(300, 150));

        jLabel3.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel3.setText("Abonnés");

        suscribeActiveInfo.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        suscribeActiveInfo.setText("100 abonnes active");

        javax.swing.GroupLayout subscribeInfoLayout = new javax.swing.GroupLayout(subscribeInfo);
        subscribeInfo.setLayout(subscribeInfoLayout);
        subscribeInfoLayout.setHorizontalGroup(
            subscribeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subscribeInfoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(subscribeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(suscribeActiveInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        subscribeInfoLayout.setVerticalGroup(
            subscribeInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, subscribeInfoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(suscribeActiveInfo)
                .addGap(61, 61, 61))
        );

        infoContainer.add(subscribeInfo);

        subscribeInfoTotal.setMaximumSize(new java.awt.Dimension(300, 150));

        jLabel4.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel4.setText("Abonnés par type");

        numberSubscribeStandard.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        numberSubscribeStandard.setText("* 10 abonnés standard");

        numberSubscribePrime.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        numberSubscribePrime.setText("* 5 abonnés prime");

        numberSubscribeGold.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        numberSubscribeGold.setText("* 20 abonnés gold");

        javax.swing.GroupLayout subscribeInfoTotalLayout = new javax.swing.GroupLayout(subscribeInfoTotal);
        subscribeInfoTotal.setLayout(subscribeInfoTotalLayout);
        subscribeInfoTotalLayout.setHorizontalGroup(
            subscribeInfoTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subscribeInfoTotalLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(subscribeInfoTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numberSubscribeGold, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numberSubscribePrime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(subscribeInfoTotalLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 52, Short.MAX_VALUE))
                    .addComponent(numberSubscribeStandard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        subscribeInfoTotalLayout.setVerticalGroup(
            subscribeInfoTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subscribeInfoTotalLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numberSubscribeStandard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numberSubscribePrime)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numberSubscribeGold)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        infoContainer.add(subscribeInfoTotal);

        subscribeInfoMoney.setMaximumSize(new java.awt.Dimension(300, 150));

        jLabel6.setFont(new java.awt.Font("SF Pro Display", 1, 24)); // NOI18N
        jLabel6.setText("Montants global");

        annualMontant.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        annualMontant.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        annualMontant.setText("1.000.000 FCFA");

        javax.swing.GroupLayout subscribeInfoMoneyLayout = new javax.swing.GroupLayout(subscribeInfoMoney);
        subscribeInfoMoney.setLayout(subscribeInfoMoneyLayout);
        subscribeInfoMoneyLayout.setHorizontalGroup(
            subscribeInfoMoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subscribeInfoMoneyLayout.createSequentialGroup()
                .addGroup(subscribeInfoMoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(subscribeInfoMoneyLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(subscribeInfoMoneyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(annualMontant, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        subscribeInfoMoneyLayout.setVerticalGroup(
            subscribeInfoMoneyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(subscribeInfoMoneyLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel6)
                .addGap(41, 41, 41)
                .addComponent(annualMontant)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        infoContainer.add(subscribeInfoMoney);

        homeContainer.add(infoContainer, java.awt.BorderLayout.PAGE_START);

        tableInfoContainer.setBackground(new java.awt.Color(255, 255, 255));
        tableInfoContainer.setOpaque(false);
        tableInfoContainer.setLayout(new java.awt.BorderLayout());

        tableSubscribeActive.setBackground(new java.awt.Color(255, 255, 255));
        tableSubscribeActive.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date Debut", "Date Fin", "Nom du Client", "Abonnement", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableSubscribeActive);

        tableInfoContainer.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        homeContainer.add(tableInfoContainer, java.awt.BorderLayout.CENTER);

        home.add(homeContainer, java.awt.BorderLayout.CENTER);

        menu.addTab("Accueil", null, home, "Accueil");

        activity.setBackground(new java.awt.Color(255, 255, 255));
        activity.setLayout(new java.awt.BorderLayout());

        activityContainer.setBackground(new java.awt.Color(255, 255, 255));
        activityContainer.setLayout(new java.awt.BorderLayout());

        activityMain.setOpaque(false);
        activityMain.setLayout(new java.awt.BorderLayout());

        tableActivity.setBackground(new java.awt.Color(255, 255, 255));
        tableActivity.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Label", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableActivity);

        activityMain.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        activityHeader.setOpaque(false);
        activityHeader.setPreferredSize(new java.awt.Dimension(1079, 60));
        activityHeader.setLayout(new java.awt.GridBagLayout());

        activityHeaderLeft.setMaximumSize(new java.awt.Dimension(200, 61));
        activityHeaderLeft.setOpaque(false);
        activityHeaderLeft.setPreferredSize(new java.awt.Dimension(200, 61));

        btnDeleteActivity.setBackground(new java.awt.Color(255, 0, 51));
        btnDeleteActivity.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        btnDeleteActivity.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteActivity.setMinimumSize(new java.awt.Dimension(50, 50));
        btnDeleteActivity.setPreferredSize(new java.awt.Dimension(50, 50));

        numberActivitiesSelected.setFont(new java.awt.Font("SF Pro Display", 0, 18)); // NOI18N
        numberActivitiesSelected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numberActivitiesSelected.setText("(0)");
        numberActivitiesSelected.setPreferredSize(new java.awt.Dimension(60, 23));

        javax.swing.GroupLayout activityHeaderLeftLayout = new javax.swing.GroupLayout(activityHeaderLeft);
        activityHeaderLeft.setLayout(activityHeaderLeftLayout);
        activityHeaderLeftLayout.setHorizontalGroup(
            activityHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(activityHeaderLeftLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(numberActivitiesSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        activityHeaderLeftLayout.setVerticalGroup(
            activityHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(activityHeaderLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(activityHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDeleteActivity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numberActivitiesSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        activityHeader.add(activityHeaderLeft, new java.awt.GridBagConstraints());

        activityHeaderCenter.setMinimumSize(new java.awt.Dimension(85, 50));
        activityHeaderCenter.setOpaque(false);
        activityHeaderCenter.setPreferredSize(new java.awt.Dimension(500, 38));
        activityHeaderCenter.setLayout(new java.awt.BorderLayout());

        inputSearchActivity.setPreferredSize(new java.awt.Dimension(95, 50));
        activityHeaderCenter.add(inputSearchActivity, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        activityHeader.add(activityHeaderCenter, gridBagConstraints);

        activityHeaderRight.setOpaque(false);

        btnAddActivity.setBackground(new java.awt.Color(130, 185, 0));
        btnAddActivity.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        btnAddActivity.setForeground(new java.awt.Color(255, 255, 255));
        btnAddActivity.setMaximumSize(new java.awt.Dimension(50, 50));
        btnAddActivity.setMinimumSize(new java.awt.Dimension(50, 50));
        btnAddActivity.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout activityHeaderRightLayout = new javax.swing.GroupLayout(activityHeaderRight);
        activityHeaderRight.setLayout(activityHeaderRightLayout);
        activityHeaderRightLayout.setHorizontalGroup(
            activityHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(activityHeaderRightLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnAddActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        activityHeaderRightLayout.setVerticalGroup(
            activityHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, activityHeaderRightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddActivity, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        activityHeader.add(activityHeaderRight, new java.awt.GridBagConstraints());

        activityMain.add(activityHeader, java.awt.BorderLayout.PAGE_START);

        activityContainer.add(activityMain, java.awt.BorderLayout.CENTER);

        activity.add(activityContainer, java.awt.BorderLayout.CENTER);

        menu.addTab("Activites", activity);

        plannings.setBackground(new java.awt.Color(255, 255, 255));
        plannings.setLayout(new java.awt.BorderLayout(0, 10));

        planningContainer.setBackground(new java.awt.Color(255, 255, 255));
        planningContainer.setLayout(new java.awt.BorderLayout(0, 10));

        java.awt.GridBagLayout planningHeaderLayout = new java.awt.GridBagLayout();
        planningHeaderLayout.columnWidths = new int[] {120, 700, 120};
        planningHeaderLayout.rowHeights = new int[] {50};
        planningHeaderLayout.columnWeights = new double[] {0.5, 0.5, 0.5};
        planningHeader.setLayout(planningHeaderLayout);

        leftHeader.setPreferredSize(new java.awt.Dimension(120, 50));

        numberPlanningSelected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numberPlanningSelected.setText("(0)");
        numberPlanningSelected.setMaximumSize(new java.awt.Dimension(60, 50));
        numberPlanningSelected.setMinimumSize(new java.awt.Dimension(60, 50));
        numberPlanningSelected.setPreferredSize(new java.awt.Dimension(60, 50));

        btnDeletePlanning.setBackground(new java.awt.Color(253, 22, 22));
        btnDeletePlanning.setMaximumSize(new java.awt.Dimension(50, 50));
        btnDeletePlanning.setMinimumSize(new java.awt.Dimension(50, 50));
        btnDeletePlanning.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout leftHeaderLayout = new javax.swing.GroupLayout(leftHeader);
        leftHeader.setLayout(leftHeaderLayout);
        leftHeaderLayout.setHorizontalGroup(
            leftHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(numberPlanningSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeletePlanning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        leftHeaderLayout.setVerticalGroup(
            leftHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftHeaderLayout.createSequentialGroup()
                .addGroup(leftHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberPlanningSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeletePlanning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        planningHeader.add(leftHeader, gridBagConstraints);

        centerHeader.setPreferredSize(new java.awt.Dimension(500, 50));
        java.awt.GridBagLayout centerHeaderLayout = new java.awt.GridBagLayout();
        centerHeaderLayout.columnWidths = new int[] {500, 120};
        centerHeaderLayout.rowHeights = new int[] {50};
        centerHeaderLayout.columnWeights = new double[] {0.5, 0.2};
        centerHeader.setLayout(centerHeaderLayout);

        headerCombox.setPreferredSize(new java.awt.Dimension(300, 50));
        java.awt.GridBagLayout headerComboxLayout = new java.awt.GridBagLayout();
        headerComboxLayout.columnWeights = new double[] {0.5, 0.5, 0.5};
        headerCombox.setLayout(headerComboxLayout);

        ComboFilterDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tous les jours", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche", " " }));
        ComboFilterDay.setPreferredSize(new java.awt.Dimension(95, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        headerCombox.add(ComboFilterDay, gridBagConstraints);

        ComboFilterActivity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboFilterActivity.setPreferredSize(new java.awt.Dimension(95, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        headerCombox.add(ComboFilterActivity, gridBagConstraints);

        ComboFilterRoom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ComboFilterRoom.setPreferredSize(new java.awt.Dimension(95, 50));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        headerCombox.add(ComboFilterRoom, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        centerHeader.add(headerCombox, gridBagConstraints);

        headerRightCombox.setPreferredSize(new java.awt.Dimension(150, 50));

        resetAction.setBackground(new java.awt.Color(74, 134, 255));
        resetAction.setFont(new java.awt.Font("SF Pro Display", 0, 16)); // NOI18N
        resetAction.setForeground(new java.awt.Color(255, 255, 255));
        resetAction.setText("Reset");
        resetAction.setPreferredSize(new java.awt.Dimension(120, 50));

        javax.swing.GroupLayout headerRightComboxLayout = new javax.swing.GroupLayout(headerRightCombox);
        headerRightCombox.setLayout(headerRightComboxLayout);
        headerRightComboxLayout.setHorizontalGroup(
            headerRightComboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerRightComboxLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(resetAction, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerRightComboxLayout.setVerticalGroup(
            headerRightComboxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerRightComboxLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(resetAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        centerHeader.add(headerRightCombox, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        planningHeader.add(centerHeader, gridBagConstraints);

        rightHeader.setPreferredSize(new java.awt.Dimension(100, 50));

        btnAddPlanning.setBackground(new java.awt.Color(130, 195, 0));
        btnAddPlanning.setMaximumSize(new java.awt.Dimension(50, 50));
        btnAddPlanning.setMinimumSize(new java.awt.Dimension(50, 50));
        btnAddPlanning.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout rightHeaderLayout = new javax.swing.GroupLayout(rightHeader);
        rightHeader.setLayout(rightHeaderLayout);
        rightHeaderLayout.setHorizontalGroup(
            rightHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAddPlanning, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
        rightHeaderLayout.setVerticalGroup(
            rightHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightHeaderLayout.createSequentialGroup()
                .addComponent(btnAddPlanning, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        planningHeader.add(rightHeader, gridBagConstraints);

        planningContainer.add(planningHeader, java.awt.BorderLayout.PAGE_START);

        tablePlanning.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Jour", "Heure du debut", "Heure de fin", "Activité", "Salle"
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
                "ID", "Prenom", "Nom", "Date de naissance", "Adresse", "Action", "CustomerId", "RoomName", "Expirate"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false, false
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

        btnDeleteMember.setBackground(new java.awt.Color(255, 0, 51));
        btnDeleteMember.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        btnDeleteMember.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteMember.setMinimumSize(new java.awt.Dimension(110, 50));
        btnDeleteMember.setPreferredSize(new java.awt.Dimension(97, 50));

        javax.swing.GroupLayout memberHeaderLeftLayout = new javax.swing.GroupLayout(memberHeaderLeft);
        memberHeaderLeft.setLayout(memberHeaderLeftLayout);
        memberHeaderLeftLayout.setHorizontalGroup(
            memberHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memberHeaderLeftLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(numberMemberSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteMember, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        memberHeaderLeftLayout.setVerticalGroup(
            memberHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memberHeaderLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(memberHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberMemberSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        memberHeader.add(memberHeaderLeft, new java.awt.GridBagConstraints());

        memberHeaderCenter.setMinimumSize(new java.awt.Dimension(85, 50));
        memberHeaderCenter.setOpaque(false);
        memberHeaderCenter.setPreferredSize(new java.awt.Dimension(500, 38));
        memberHeaderCenter.setLayout(new java.awt.BorderLayout());

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
        btnAddMember.setToolTipText("Ajouter un membre");
        btnAddMember.setMinimumSize(new java.awt.Dimension(106, 50));
        btnAddMember.setPreferredSize(new java.awt.Dimension(106, 50));

        btnResubcribe.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        btnResubcribe.setForeground(new java.awt.Color(102, 204, 0));
        btnResubcribe.setText("Réabonné");

        javax.swing.GroupLayout memberHeaderRightLayout = new javax.swing.GroupLayout(memberHeaderRight);
        memberHeaderRight.setLayout(memberHeaderRightLayout);
        memberHeaderRightLayout.setHorizontalGroup(
            memberHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memberHeaderRightLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnResubcribe)
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
                    .addComponent(btnResubcribe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(23, 23, 23))
        );

        memberHeader.add(memberHeaderRight, new java.awt.GridBagConstraints());

        memberMain.add(memberHeader, java.awt.BorderLayout.PAGE_START);

        memberContainer.add(memberMain, java.awt.BorderLayout.CENTER);

        members.add(memberContainer, java.awt.BorderLayout.CENTER);

        menu.addTab("Membres", members);

        room.setBackground(new java.awt.Color(255, 255, 255));
        room.setOpaque(false);
        room.setLayout(new java.awt.BorderLayout());

        roomContainer.setBackground(new java.awt.Color(255, 255, 255));
        roomContainer.setOpaque(false);
        roomContainer.setLayout(new java.awt.BorderLayout());

        roomMain.setOpaque(false);
        roomMain.setLayout(new java.awt.BorderLayout());

        roomHeader.setOpaque(false);
        roomHeader.setPreferredSize(new java.awt.Dimension(1079, 60));
        roomHeader.setLayout(new java.awt.GridBagLayout());

        roomHeaderLeft.setMaximumSize(new java.awt.Dimension(200, 61));
        roomHeaderLeft.setOpaque(false);
        roomHeaderLeft.setPreferredSize(new java.awt.Dimension(200, 61));

        javax.swing.GroupLayout roomHeaderLeftLayout = new javax.swing.GroupLayout(roomHeaderLeft);
        roomHeaderLeft.setLayout(roomHeaderLeftLayout);
        roomHeaderLeftLayout.setHorizontalGroup(
            roomHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        roomHeaderLeftLayout.setVerticalGroup(
            roomHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        roomHeader.add(roomHeaderLeft, new java.awt.GridBagConstraints());

        roomHeaderCenter.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 20, 0, 0));
        roomHeaderCenter.setMinimumSize(new java.awt.Dimension(85, 50));
        roomHeaderCenter.setOpaque(false);
        roomHeaderCenter.setPreferredSize(new java.awt.Dimension(500, 38));
        roomHeaderCenter.setLayout(new java.awt.BorderLayout());

        inputSearchRoom.setPreferredSize(new java.awt.Dimension(95, 50));
        roomHeaderCenter.add(inputSearchRoom, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        roomHeader.add(roomHeaderCenter, gridBagConstraints);

        roomHeaderRight.setOpaque(false);

        btnAddRoom.setBackground(new java.awt.Color(130, 185, 0));
        btnAddRoom.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        btnAddRoom.setForeground(new java.awt.Color(255, 255, 255));
        btnAddRoom.setMinimumSize(new java.awt.Dimension(106, 50));
        btnAddRoom.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout roomHeaderRightLayout = new javax.swing.GroupLayout(roomHeaderRight);
        roomHeaderRight.setLayout(roomHeaderRightLayout);
        roomHeaderRightLayout.setHorizontalGroup(
            roomHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roomHeaderRightLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnAddRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roomHeaderRightLayout.setVerticalGroup(
            roomHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roomHeaderRightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddRoom, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        roomHeader.add(roomHeaderRight, new java.awt.GridBagConstraints());

        roomMain.add(roomHeader, java.awt.BorderLayout.PAGE_START);

        roomBody.setOpaque(false);
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 30);
        flowLayout2.setAlignOnBaseline(true);
        roomBody.setLayout(flowLayout2);
        roomMain.add(roomBody, java.awt.BorderLayout.CENTER);

        roomContainer.add(roomMain, java.awt.BorderLayout.CENTER);

        room.add(roomContainer, java.awt.BorderLayout.CENTER);

        menu.addTab("Salles", room);

        coach.setBackground(new java.awt.Color(255, 255, 255));
        coach.setOpaque(false);
        coach.setLayout(new java.awt.BorderLayout());

        coachHeader.setLayout(new java.awt.GridBagLayout());

        coachHeaderLeft.setPreferredSize(new java.awt.Dimension(150, 50));

        numberCoachSelected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numberCoachSelected.setText("(0)");
        numberCoachSelected.setMaximumSize(new java.awt.Dimension(100, 50));
        numberCoachSelected.setMinimumSize(new java.awt.Dimension(100, 50));
        numberCoachSelected.setPreferredSize(new java.awt.Dimension(100, 50));

        btnDeleteCoach.setBackground(new java.awt.Color(255, 30, 30));
        btnDeleteCoach.setMaximumSize(new java.awt.Dimension(50, 50));
        btnDeleteCoach.setMinimumSize(new java.awt.Dimension(50, 50));
        btnDeleteCoach.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout coachHeaderLeftLayout = new javax.swing.GroupLayout(coachHeaderLeft);
        coachHeaderLeft.setLayout(coachHeaderLeftLayout);
        coachHeaderLeftLayout.setHorizontalGroup(
            coachHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coachHeaderLeftLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(numberCoachSelected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnDeleteCoach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        coachHeaderLeftLayout.setVerticalGroup(
            coachHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coachHeaderLeftLayout.createSequentialGroup()
                .addGroup(coachHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numberCoachSelected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteCoach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        coachHeader.add(coachHeaderLeft, gridBagConstraints);

        coachHeaderSideLeft.setPreferredSize(new java.awt.Dimension(150, 50));

        filterActivityCoach.setPreferredSize(new java.awt.Dimension(150, 50));

        javax.swing.GroupLayout coachHeaderSideLeftLayout = new javax.swing.GroupLayout(coachHeaderSideLeft);
        coachHeaderSideLeft.setLayout(coachHeaderSideLeftLayout);
        coachHeaderSideLeftLayout.setHorizontalGroup(
            coachHeaderSideLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coachHeaderSideLeftLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(filterActivityCoach, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        coachHeader.add(coachHeaderSideLeft, gridBagConstraints);

        coachHeaderCenter.setPreferredSize(new java.awt.Dimension(500, 50));

        inputSearchCoach.setPreferredSize(new java.awt.Dimension(500, 50));

        javax.swing.GroupLayout coachHeaderCenterLayout = new javax.swing.GroupLayout(coachHeaderCenter);
        coachHeaderCenter.setLayout(coachHeaderCenterLayout);
        coachHeaderCenterLayout.setHorizontalGroup(
            coachHeaderCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coachHeaderCenterLayout.createSequentialGroup()
                .addComponent(inputSearchCoach, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
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

        coachHeaderRight.setPreferredSize(new java.awt.Dimension(150, 50));

        btnAddCoach.setBackground(new java.awt.Color(130, 195, 0));
        btnAddCoach.setMaximumSize(new java.awt.Dimension(50, 50));
        btnAddCoach.setMinimumSize(new java.awt.Dimension(50, 50));
        btnAddCoach.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout coachHeaderRightLayout = new javax.swing.GroupLayout(coachHeaderRight);
        coachHeaderRight.setLayout(coachHeaderRightLayout);
        coachHeaderRightLayout.setHorizontalGroup(
            coachHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coachHeaderRightLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnAddCoach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(94, 94, 94))
        );
        coachHeaderRightLayout.setVerticalGroup(
            coachHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(coachHeaderRightLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnAddCoach, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 10);
        coachHeader.add(coachHeaderRight, gridBagConstraints);

        coach.add(coachHeader, java.awt.BorderLayout.PAGE_START);

        tableCoach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Prenom", "Nom", "Phone", "Adresse", "Spécilité"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tableCoach);

        coach.add(jScrollPane5, java.awt.BorderLayout.CENTER);

        menu.addTab("Entraineurs", coach);

        users.setBackground(new java.awt.Color(255, 255, 255));
        users.setOpaque(false);
        users.setLayout(new java.awt.BorderLayout());

        usersContainer.setBackground(new java.awt.Color(255, 255, 255));
        usersContainer.setOpaque(false);
        usersContainer.setLayout(new java.awt.BorderLayout());

        userMain.setOpaque(false);
        userMain.setLayout(new java.awt.BorderLayout());

        tableUsers.setBackground(new java.awt.Color(255, 255, 255));
        tableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Prénom", "Nom", "Date de naissance", "Adresse", "Phone", "Salle", "Actions", "ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUsers.setEditingColumn(7);
        tableUsers.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tableUsers);
        if (tableUsers.getColumnModel().getColumnCount() > 0) {
            tableUsers.getColumnModel().getColumn(8).setResizable(false);
            tableUsers.getColumnModel().getColumn(8).setPreferredWidth(0);
        }

        userMain.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        usersHeader.setOpaque(false);
        usersHeader.setPreferredSize(new java.awt.Dimension(1079, 60));
        usersHeader.setLayout(new java.awt.GridBagLayout());

        usersHeaderLeft.setMaximumSize(new java.awt.Dimension(200, 61));
        usersHeaderLeft.setOpaque(false);
        usersHeaderLeft.setPreferredSize(new java.awt.Dimension(200, 61));

        btnDeleteUser.setBackground(new java.awt.Color(255, 0, 51));
        btnDeleteUser.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        btnDeleteUser.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteUser.setMinimumSize(new java.awt.Dimension(110, 50));
        btnDeleteUser.setPreferredSize(new java.awt.Dimension(97, 50));

        javax.swing.GroupLayout usersHeaderLeftLayout = new javax.swing.GroupLayout(usersHeaderLeft);
        usersHeaderLeft.setLayout(usersHeaderLeftLayout);
        usersHeaderLeftLayout.setHorizontalGroup(
            usersHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersHeaderLeftLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(numberUsersSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDeleteUser, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        usersHeaderLeftLayout.setVerticalGroup(
            usersHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(usersHeaderLeftLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(usersHeaderLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numberUsersSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        usersHeader.add(usersHeaderLeft, new java.awt.GridBagConstraints());

        userHeaderCenter.setMinimumSize(new java.awt.Dimension(85, 50));
        userHeaderCenter.setOpaque(false);
        userHeaderCenter.setPreferredSize(new java.awt.Dimension(500, 38));
        userHeaderCenter.setLayout(new java.awt.BorderLayout());

        inputSearchUser.setPreferredSize(new java.awt.Dimension(95, 50));
        userHeaderCenter.add(inputSearchUser, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 3.0;
        usersHeader.add(userHeaderCenter, gridBagConstraints);

        userHeaderRight.setOpaque(false);

        btnAddUser.setBackground(new java.awt.Color(130, 185, 0));
        btnAddUser.setFont(new java.awt.Font("SF Pro Display", 1, 18)); // NOI18N
        btnAddUser.setForeground(new java.awt.Color(255, 255, 255));
        btnAddUser.setMinimumSize(new java.awt.Dimension(106, 50));
        btnAddUser.setPreferredSize(new java.awt.Dimension(106, 50));

        javax.swing.GroupLayout userHeaderRightLayout = new javax.swing.GroupLayout(userHeaderRight);
        userHeaderRight.setLayout(userHeaderRightLayout);
        userHeaderRightLayout.setHorizontalGroup(
            userHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userHeaderRightLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        userHeaderRightLayout.setVerticalGroup(
            userHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userHeaderRightLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddUser, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        usersHeader.add(userHeaderRight, new java.awt.GridBagConstraints());

        userMain.add(usersHeader, java.awt.BorderLayout.PAGE_START);

        usersContainer.add(userMain, java.awt.BorderLayout.CENTER);

        users.add(usersContainer, java.awt.BorderLayout.CENTER);

        menu.addTab("Utilisateurs", users);

        subscription.setBackground(new java.awt.Color(255, 255, 255));
        subscription.setOpaque(false);
        subscription.setLayout(new java.awt.BorderLayout());

        subscriptionContainer.setBackground(new java.awt.Color(255, 255, 255));
        subscriptionContainer.setOpaque(false);
        subscriptionContainer.setLayout(new java.awt.BorderLayout());

        subscriptionMain.setOpaque(false);
        subscriptionMain.setLayout(new java.awt.BorderLayout());

        subscriptionHeader.setOpaque(false);
        subscriptionHeader.setPreferredSize(new java.awt.Dimension(1079, 60));
        subscriptionHeader.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout subscriptionHeaderRightLayout = new javax.swing.GroupLayout(subscriptionHeaderRight);
        subscriptionHeaderRight.setLayout(subscriptionHeaderRightLayout);
        subscriptionHeaderRightLayout.setHorizontalGroup(
            subscriptionHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        subscriptionHeaderRightLayout.setVerticalGroup(
            subscriptionHeaderRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        subscriptionHeader.add(subscriptionHeaderRight, new java.awt.GridBagConstraints());

        subscriptionMain.add(subscriptionHeader, java.awt.BorderLayout.PAGE_START);

        bodySubscription.setBorder(javax.swing.BorderFactory.createEmptyBorder(100, 1, 1, 1));
        bodySubscription.setOpaque(false);
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 0);
        flowLayout1.setAlignOnBaseline(true);
        bodySubscription.setLayout(flowLayout1);

        standardCard.setBackground(new java.awt.Color(102, 102, 255));
        standardCard.setMaximumSize(new java.awt.Dimension(600, 800));
        standardCard.setMinimumSize(new java.awt.Dimension(280, 400));
        standardCard.setPreferredSize(new java.awt.Dimension(280, 400));

        jLabel8.setFont(new java.awt.Font("SF Pro Display", 1, 30)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Standard");

        standardMonth.setFont(new java.awt.Font("SF Pro Display", 1, 30)); // NOI18N
        standardMonth.setForeground(new java.awt.Color(255, 255, 255));
        standardMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        standardMonth.setText("Mois");

        standardPrice.setFont(new java.awt.Font("SF Pro Display", 1, 30)); // NOI18N
        standardPrice.setForeground(new java.awt.Color(255, 255, 255));
        standardPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        standardPrice.setText("7.500 FCFA");

        javax.swing.GroupLayout standardCardLayout = new javax.swing.GroupLayout(standardCard);
        standardCard.setLayout(standardCardLayout);
        standardCardLayout.setHorizontalGroup(
            standardCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(standardCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(standardCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(standardMonth, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(standardPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                .addContainerGap())
        );
        standardCardLayout.setVerticalGroup(
            standardCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(standardCardLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
                .addComponent(standardPrice)
                .addGap(90, 90, 90)
                .addComponent(standardMonth)
                .addGap(73, 73, 73))
        );

        bodySubscription.add(standardCard);

        primeCard.setBackground(new java.awt.Color(153, 0, 153));
        primeCard.setMaximumSize(new java.awt.Dimension(600, 800));
        primeCard.setMinimumSize(new java.awt.Dimension(280, 400));
        primeCard.setPreferredSize(new java.awt.Dimension(280, 400));

        jLabel10.setFont(new java.awt.Font("SF Pro Display", 1, 30)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Prime");

        primePrice.setFont(new java.awt.Font("SF Pro Display", 1, 30)); // NOI18N
        primePrice.setForeground(new java.awt.Color(255, 255, 255));
        primePrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        primePrice.setText("7.500 FCFA");

        primeMonth.setFont(new java.awt.Font("SF Pro Display", 1, 30)); // NOI18N
        primeMonth.setForeground(new java.awt.Color(255, 255, 255));
        primeMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        primeMonth.setText("6 mois");

        javax.swing.GroupLayout primeCardLayout = new javax.swing.GroupLayout(primeCard);
        primeCard.setLayout(primeCardLayout);
        primeCardLayout.setHorizontalGroup(
            primeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(primeCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(primeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(primePrice, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(primeMonth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        primeCardLayout.setVerticalGroup(
            primeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(primeCardLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(107, 107, 107)
                .addComponent(primePrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addComponent(primeMonth)
                .addGap(74, 74, 74))
        );

        bodySubscription.add(primeCard);

        goldCard.setBackground(new java.awt.Color(130, 185, 0));
        goldCard.setMaximumSize(new java.awt.Dimension(600, 800));
        goldCard.setMinimumSize(new java.awt.Dimension(280, 400));
        goldCard.setPreferredSize(new java.awt.Dimension(280, 400));

        jLabel11.setFont(new java.awt.Font("SF Pro Display", 1, 30)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Gold");

        goldMonth.setFont(new java.awt.Font("SF Pro Display", 1, 30)); // NOI18N
        goldMonth.setForeground(new java.awt.Color(255, 255, 255));
        goldMonth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goldMonth.setText("Une année");

        goldPrice.setFont(new java.awt.Font("SF Pro Display", 1, 30)); // NOI18N
        goldPrice.setForeground(new java.awt.Color(255, 255, 255));
        goldPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        goldPrice.setText("7.500 FCFA");

        javax.swing.GroupLayout goldCardLayout = new javax.swing.GroupLayout(goldCard);
        goldCard.setLayout(goldCardLayout);
        goldCardLayout.setHorizontalGroup(
            goldCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(goldCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(goldCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(goldMonth, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(goldPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                .addContainerGap())
        );
        goldCardLayout.setVerticalGroup(
            goldCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(goldCardLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 104, Short.MAX_VALUE)
                .addComponent(goldPrice)
                .addGap(94, 94, 94)
                .addComponent(goldMonth)
                .addGap(73, 73, 73))
        );

        bodySubscription.add(goldCard);

        subscriptionMain.add(bodySubscription, java.awt.BorderLayout.CENTER);

        subscriptionContainer.add(subscriptionMain, java.awt.BorderLayout.CENTER);

        subscription.add(subscriptionContainer, java.awt.BorderLayout.CENTER);

        menu.addTab("Abonnements", subscription);

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
    private javax.swing.JComboBox<String> ComboFilterRoom;
    private javax.swing.JPanel activity;
    private javax.swing.JPanel activityContainer;
    private javax.swing.JPanel activityHeader;
    private javax.swing.JPanel activityHeaderCenter;
    private javax.swing.JPanel activityHeaderLeft;
    private javax.swing.JPanel activityHeaderRight;
    private javax.swing.JPanel activityMain;
    private javax.swing.JLabel annualMontant;
    private javax.swing.JPanel bodySubscription;
    private javax.swing.JButton btnAddActivity;
    private javax.swing.JButton btnAddCoach;
    private javax.swing.JButton btnAddMember;
    private javax.swing.JButton btnAddPlanning;
    private javax.swing.JButton btnAddRoom;
    private javax.swing.JButton btnAddUser;
    private javax.swing.JButton btnDeleteActivity;
    private javax.swing.JButton btnDeleteCoach;
    private javax.swing.JButton btnDeleteMember;
    private javax.swing.JButton btnDeletePlanning;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnRefreshHome;
    private javax.swing.JButton btnResubcribe;
    private javax.swing.JPanel centerHeader;
    private javax.swing.JPanel coach;
    private javax.swing.JPanel coachHeader;
    private javax.swing.JPanel coachHeaderCenter;
    private javax.swing.JPanel coachHeaderLeft;
    private javax.swing.JPanel coachHeaderRight;
    private javax.swing.JPanel coachHeaderSideLeft;
    private javax.swing.JPanel container;
    private javax.swing.JComboBox<String> filterActivityCoach;
    private javax.swing.JPanel goldCard;
    private javax.swing.JLabel goldMonth;
    private javax.swing.JLabel goldPrice;
    private javax.swing.JPanel header;
    private javax.swing.JPanel headerCombox;
    private javax.swing.JPanel headerRightCombox;
    private javax.swing.JPanel home;
    private javax.swing.JPanel homeContainer;
    private javax.swing.JPanel infoContainer;
    private javax.swing.JTextField inputSearchActivity;
    private javax.swing.JTextField inputSearchCoach;
    private javax.swing.JTextField inputSearchMember;
    private javax.swing.JTextField inputSearchRoom;
    private javax.swing.JTextField inputSearchUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPanel leftHeader;
    private javax.swing.JPanel memberContainer;
    private javax.swing.JPanel memberHeader;
    private javax.swing.JPanel memberHeaderCenter;
    private javax.swing.JPanel memberHeaderLeft;
    private javax.swing.JPanel memberHeaderRight;
    private javax.swing.JPanel memberMain;
    private javax.swing.JPanel members;
    private javax.swing.JTabbedPane menu;
    private javax.swing.JLabel numberActivitiesSelected;
    private javax.swing.JLabel numberCoachSelected;
    private javax.swing.JLabel numberMemberSelected;
    private javax.swing.JLabel numberPlanningSelected;
    private javax.swing.JLabel numberSubscribeGold;
    private javax.swing.JLabel numberSubscribePrime;
    private javax.swing.JLabel numberSubscribeStandard;
    private javax.swing.JLabel numberUsersSelected;
    private javax.swing.JPanel planningContainer;
    private javax.swing.JPanel planningHeader;
    private javax.swing.JPanel plannings;
    private javax.swing.JPanel primeCard;
    private javax.swing.JLabel primeMonth;
    private javax.swing.JLabel primePrice;
    private javax.swing.JButton resetAction;
    private javax.swing.JPanel rightHeader;
    private javax.swing.JPanel room;
    private javax.swing.JPanel roomBody;
    private javax.swing.JPanel roomContainer;
    private javax.swing.JPanel roomHeader;
    private javax.swing.JPanel roomHeaderCenter;
    private javax.swing.JPanel roomHeaderLeft;
    private javax.swing.JPanel roomHeaderRight;
    private javax.swing.JPanel roomMain;
    private javax.swing.JButton signOut;
    private javax.swing.JPanel standardCard;
    private javax.swing.JLabel standardMonth;
    private javax.swing.JLabel standardPrice;
    private javax.swing.JPanel subscribeInfo;
    private javax.swing.JPanel subscribeInfoMoney;
    private javax.swing.JPanel subscribeInfoTotal;
    private javax.swing.JPanel subscription;
    private javax.swing.JPanel subscriptionContainer;
    private javax.swing.JPanel subscriptionHeader;
    private javax.swing.JPanel subscriptionHeaderRight;
    private javax.swing.JPanel subscriptionMain;
    private javax.swing.JLabel suscribeActiveInfo;
    private javax.swing.JTable tableActivity;
    private javax.swing.JTable tableCoach;
    private javax.swing.JPanel tableInfoContainer;
    private javax.swing.JTable tableMember;
    private javax.swing.JTable tablePlanning;
    private javax.swing.JTable tableSubscribeActive;
    private javax.swing.JTable tableUsers;
    private javax.swing.JPanel userHeaderCenter;
    private javax.swing.JPanel userHeaderRight;
    private javax.swing.JPanel userMain;
    private javax.swing.JPanel users;
    private javax.swing.JPanel usersContainer;
    private javax.swing.JPanel usersHeader;
    private javax.swing.JPanel usersHeaderLeft;
    // End of variables declaration//GEN-END:variables
}
