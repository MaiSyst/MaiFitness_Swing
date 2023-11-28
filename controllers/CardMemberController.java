
package fitnessapp.controllers;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import fitnessapp.components.CardMember;
import fitnessapp.utilities.Constants;
import fitnessapp.utilities.MaiUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author maisyst
 */
public final class CardMemberController {

    private final CardMember cardMember;
    private final JFrame parent;
    public CardMemberController(JFrame parent, String identity, 
            String firstName, 
            String lastName,
            String birthDate, String address, 
            String roomName,String expirateDate,
            BufferedImage imageBuffered) {
        cardMember = new CardMember(parent, true);
        cardMember.getCard().putClientProperty(FlatClientProperties.STYLE, "arc:30;background:#ffffff;");
        cardMember.getBtnClose().addActionListener(l -> cardMember.dispose());
        cardMember.getBtnClose().setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "close.svg"));
        cardMember.getBtnPrint().setIcon(new FlatSVGIcon(Constants.ICONS_PATH + "printer.svg"));
        cardMember.getBtnPrint().addActionListener(l -> printCardMember());
        cardMember.getFirstName().setText(MaiUtils.toCapitalCase(firstName));
        cardMember.getLastName().setText(lastName.toUpperCase());
        cardMember.getAddress().setText(MaiUtils.toCapitalCase(address));
        cardMember.getBirthday().setText(birthDate);
        cardMember.getIdentity().setText(identity);
        cardMember.getRoomName().setText(roomName);
        cardMember.getExpirateDate().setText(expirateDate);
        this.parent=parent;
        cardMember.getQrCode().setIcon(new ImageIcon(imageBuffered));
    }

    private void printCardMember() {
        try {
            PrinterJob printerJob = PrinterJob.getPrinterJob();
            cardMember.getCard().setBackground(new Color(170,255,127,50));
            printerJob.setPrintable(new MaiPrintable(cardMember.getCard()));
            printerJob.setJobName("Card Member");
            var isPrint = printerJob.printDialog();
            if (isPrint) {
                printerJob.print();
                cardMember.dispose();
                JOptionPane.showMessageDialog(parent, "Imprimer avec success", 
                        "Impression",JOptionPane.INFORMATION_MESSAGE);
                cardMember.getCard().setBackground(Color.WHITE);
            }
        } catch (PrinterException ex) {
            Logger.getLogger(CardMemberController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void show() {
        cardMember.setVisible(true);
    }

    static final class MaiPrintable implements java.awt.print.Printable {
        private final Component component;
        public MaiPrintable(Component component){
            this.component=component;
           
        }
        @Override
        public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
            if (page > 0) {
                return NO_SUCH_PAGE;
            }
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            component.paint(g2d);
            return PAGE_EXISTS;
        }

    }
}
