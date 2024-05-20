package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Model.BankAccount;
import java.awt.Font;
import java.awt.Color;

public class BankTransferFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private BankAccount account;
    private JTextField depositAmountField;
    private JTextField withdrawAmountField;
    private JLabel balanceLabel;

    /**
     * Khởi chạy ứng dụng.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BankTransferFrame frame = new BankTransferFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Tạo khung.
     */
    public BankTransferFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 790, 427);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        account = new BankAccount(5000000); // Số dư ban đầu

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblDeposit = new JLabel("Số tiền gửi:");
        lblDeposit.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblDeposit.setBounds(10, 76, 317, 62);
        panel.add(lblDeposit);

        depositAmountField = new JTextField();
        depositAmountField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        depositAmountField.setBounds(334, 76, 317, 62);
        panel.add(depositAmountField);
        depositAmountField.setColumns(10);

        JLabel lblWithdraw = new JLabel("Số tiền rút:");
        lblWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblWithdraw.setBounds(10, 143, 317, 62);
        panel.add(lblWithdraw);

        withdrawAmountField = new JTextField();
        withdrawAmountField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        withdrawAmountField.setBounds(334, 143, 317, 62);
        panel.add(withdrawAmountField);
        withdrawAmountField.setColumns(10);

        JButton btnDeposit = new JButton("Gửi tiền");
        btnDeposit.setForeground(new Color(128, 255, 128));
        btnDeposit.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnDeposit.setBounds(10, 210, 317, 62);
        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(depositAmountField.getText());
                new Thread(new DepositMoney(account, amount)).start();
                updateBalance();
            }
        });
        panel.add(btnDeposit);

        JButton btnWithdraw = new JButton("Rút tiền");
        btnWithdraw.setForeground(new Color(255, 0, 0));
        btnWithdraw.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnWithdraw.setBounds(334, 210, 317, 62);
        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(withdrawAmountField.getText());
                if (account.getBalance() >= amount) {
                    new Thread(new WithdrawMoney(account, amount)).start();
                    updateBalance();
                } else {
                    JOptionPane.showMessageDialog(null, "Không đủ số dư để rút tiền!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(btnWithdraw);

        JLabel lblBalance = new JLabel("Số dư hiện tại:");
        lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblBalance.setBounds(10, 277, 317, 62);
        panel.add(lblBalance);

        balanceLabel = new JLabel("5000000");
        balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        balanceLabel.setBounds(334, 277, 317, 62);
        panel.add(balanceLabel);
        
        JLabel lblMinhHaRt = new JLabel("MINH HỌA RÚT GỬI TIỀN");
        lblMinhHaRt.setForeground(new Color(0, 0, 255));
        lblMinhHaRt.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblMinhHaRt.setBounds(164, 0, 529, 62);
        panel.add(lblMinhHaRt);

        updateBalance();
    }

    private void updateBalance() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                balanceLabel.setText(String.valueOf(account.getBalance()));
            }
        });
    }
}
