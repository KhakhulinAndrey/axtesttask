package ru.khakhulin.books.UI;

import ru.khakhulin.books.dao.BookDAO;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by 1 on 20.09.2017.
 */
public class BookSearchApp extends JFrame{

    private JPanel contentPane;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnUpdate;
    private JScrollPane scrollPane;
    private JTable table;

    private BookDAO bookDAO;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    BookSearchApp frame = new BookSearchApp();
                    frame.setVisible(true);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }

    public BookSearchApp(){
        try{
            bookDAO = new BookDAO();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error: "+ e , "Error", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("Books");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,450,300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        contentPane.add(panel, BorderLayout.NORTH);

        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        table = new JTable();
        scrollPane.setViewportView(table);



    }
}
