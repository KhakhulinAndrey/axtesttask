package ru.khakhulin.books.ui.authors;

import ru.khakhulin.books.model.Author;

import javax.swing.table.AbstractTableModel;
import java.util.List;

/**
 * Created by Admin on 22.09.2017.
 */
public class AuthorTableModel extends AbstractTableModel {

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int PATRONYMIC = 2;
    private static final int SURNAME = 3;
    private static final int BIRTHDAY = 4;

    private String[] columnNames = { "ID", "Имя", "Отчетсво",
            "Фамилия", "Дата рождения" };
    private List<Author> authors;

    public AuthorTableModel(List<Author> authors) {
        this.authors = authors;
    }

    public Author getAuthor(int row){
        return authors.get(row);
    }

    public void updateData(List<Author> authors) {
        this.authors = authors;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return authors.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public Object getValueAt(int row, int col) {
        Author author = authors.get(row);

        switch (col) {
            case ID:
                return author.getId();
            case NAME:
                return author.getName();
            case PATRONYMIC:
                return author.getPatronymic();
            case SURNAME:
                return author.getSurname();
            case BIRTHDAY:
                return author.getBirthday().toString();
            default:
                return author.getName();
        }
    }
}
