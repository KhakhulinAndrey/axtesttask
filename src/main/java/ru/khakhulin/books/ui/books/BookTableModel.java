package ru.khakhulin.books.ui.books;

import ru.khakhulin.books.model.Book;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class BookTableModel extends AbstractTableModel {

    private static final int ID = 0;
    private static final int AUTHOR = 1;
    private static final int NAME = 2;
    private static final int ISBN = 3;
    private static final int SUBJECT = 4;

    private String[] columnNames = {"ID","Автор", "Книга", "ISBN", "Тематика"};

    private List<Book> books;

    public BookTableModel(List<Book> books) {
        this.books = books;
    }

    public Book getBook(int row){
        return books.get(row);
    }

    public void updateData(List<Book> books) {
        this.books = books;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {//количество строк
        return books.size();
    }

    @Override
    public int getColumnCount() {//количество столбцов
        return columnNames.length;
    }

    @Override
    public Class getColumnClass(int c) {//тип данных столбца
        return getValueAt(0,c).getClass();
    }

    @Override
    public String getColumnName(int col) {//название столбца
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Book book = books.get(row);
        switch (col){
            case ID:
                return book.getId();
            case AUTHOR:
                return book.getAuth_id();
            case NAME:
                return book.getName();
            case ISBN:
                return book.getIsbn();
            case SUBJECT:
                return book.getSubject();
            default:
                return book.getName();
        }
    }



}
