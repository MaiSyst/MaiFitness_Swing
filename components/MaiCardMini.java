/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fitnessapp.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import fitnessapp.utilities.Constants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import fitnessapp.utilities.MaiCardButtonEvent;

/**
 *
 * @author orion90
 */
public final class MaiCardMini extends JPanel {

    private final String flatStyle = FlatClientProperties.STYLE;
    private final JLabel titleLabel;
    private final JLabel descriptionLabel;
    private static final int heightCard = 80;
    private static final int widthCard = 300;
    private final String cardId;
    private final MaiCardButtonEvent<String> buttonEvent;
    private final MaiCardButtonEvent<String> buttonPressedEvent;
    private final MaiCardButtonEvent<String> buttonShowEvent;
    private final String primaryColor;
    private final String lightColor;
    private final int arc = heightCard;
    private final int rand;

    public MaiCardMini(final String title, final String description, final String cardId, 
            final MaiCardButtonEvent<String> buttonEvent,MaiCardButtonEvent<String> buttonShowEvent, boolean isPressed) {
        super();
        this.titleLabel = new JLabel(title);
        this.descriptionLabel = new JLabel(description);
        this.cardId = cardId;
        this.buttonShowEvent=buttonShowEvent;
        if (isPressed) {
            this.buttonEvent = buttonEvent;
            this.buttonPressedEvent = buttonEvent;
        } else {
            this.buttonEvent = buttonEvent;
            this.buttonPressedEvent = null;
        }
        
        this.rand = new Random().nextInt(Constants.COLORS.size());
        primaryColor = Constants.COLORS.get(rand);
        lightColor = primaryColor + "35";
        titleLabel.putClientProperty(flatStyle, "font: semibold $h3.regular.font");
        descriptionLabel.putClientProperty(flatStyle, "font: $h4.regular.font");
        this.setLayout(new BorderLayout());
        leftComponent();
        centerComponent();
        this.putClientProperty(flatStyle, "arc:" + arc + ";background:" + lightColor + ";");
        this.setPreferredSize(new Dimension(widthCard, heightCard));
        this.setSize(widthCard, heightCard);
    }

    public MaiCardMini(final String title, final String description, final String cardId,
            final MaiCardButtonEvent<String> buttonEvent, 
            final MaiCardButtonEvent<String> buttonPressedEvent,final MaiCardButtonEvent<String>buttonShowEvent) {
        super();
        this.titleLabel = new JLabel(title);
        this.descriptionLabel = new JLabel(description);
        this.cardId = cardId;
        this.buttonEvent = buttonEvent;
        this.buttonPressedEvent = buttonPressedEvent;
        this.buttonShowEvent=buttonShowEvent;
        this.rand = new Random().nextInt(Constants.COLORS.size());
        primaryColor = Constants.COLORS.get(rand);
        lightColor = primaryColor + "35";

        titleLabel.putClientProperty(flatStyle, "font: semibold $h3.regular.font");
        descriptionLabel.putClientProperty(flatStyle, "font: $h4.regular.font");
        this.setLayout(new BorderLayout());
        leftComponent();
        centerComponent();
        rightComponent();
        this.putClientProperty(flatStyle, "arc:" + arc + ";background:" + lightColor + ";");
        this.setPreferredSize(new Dimension(widthCard, heightCard));
        this.setSize(widthCard, heightCard);
    }
    private void rightComponent(){
        final JPanel right=new JPanel(new BorderLayout());
        right.setOpaque(false);
        JButton showDetail=new JButton(new FlatSVGIcon(Constants.ICONS_PATH+"show.svg"));
        showDetail.setPreferredSize(new Dimension(heightCard, heightCard));
        showDetail.setHorizontalAlignment(SwingConstants.CENTER);
        showDetail.setSize(new Dimension(heightCard, heightCard));
        showDetail.setPreferredSize(new Dimension(heightCard, heightCard));
        showDetail.putClientProperty(flatStyle, "arc:" + heightCard + ";background:" + primaryColor + ";buttonType:borderless;focusedBorderColor:" + primaryColor);
        right.add(showDetail, BorderLayout.CENTER);
        this.add(right, BorderLayout.EAST);
        showDetail.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                buttonShowEvent.onEvent(cardId);
            }
            
        });
    }
    private void centerComponent() {
        final JPanel center = new JPanel(new GridLayout(2, 1));
        center.add(titleLabel);
        center.add(descriptionLabel);
        center.setOpaque(false);
        center.setBorder(new EmptyBorder(0, 10, 0, 0));
        this.add(center, BorderLayout.CENTER);

    }

    public String getCardId() {
        return cardId;
    }

    public void title(String newTitle) {
        this.titleLabel.setText(newTitle);
    }
    public String title(){return titleLabel.getText();}
    private void leftComponent() {
        JPanel left = new JPanel(new BorderLayout());
        left.setOpaque(false);
        final JButton icons = new JButton(new FlatSVGIcon(Constants.ICONS_PATH + "house.svg"));
        icons.setPreferredSize(new Dimension(heightCard, heightCard));
        icons.setHorizontalAlignment(SwingConstants.CENTER);
        icons.setSize(new Dimension(heightCard, heightCard));
        icons.setPreferredSize(new Dimension(heightCard, heightCard));
        icons.putClientProperty(flatStyle, "arc:" + heightCard + ";background:" + primaryColor + ";buttonType:borderless;focusedBorderColor:" + primaryColor);
        left.add(icons, BorderLayout.CENTER);
        this.add(left, BorderLayout.WEST);
        
        icons.addMouseListener(new MouseAdapter() {
            Timer timer = null;

            @Override
            public String toString() {
                return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                if (e.getClickCount() == 2) {
                    if(buttonEvent!=null){
                        buttonEvent.onEvent(cardId, titleLabel.getText());
                    }

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                if (timer == null) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                           if(buttonPressedEvent!=null){
                                buttonPressedEvent.onEvent(cardId,titleLabel.getText());
                           }

                        }

                    }, 600);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                if (timer != null) {
                    timer.cancel();
                    timer = null;
                }
            }

        });
    }
}
