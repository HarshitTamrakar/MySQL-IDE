package newapp;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TextArea;
import newapp.Pattern_generator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 0kl
 */
public class Table_generator {

    public void table(ResultSet rs, TextArea t) {
        try {
            t.appendText("\n");
            ResultSetMetaData rsmd = rs.getMetaData();
            int col_count = rsmd.getColumnCount();
            List<Integer> max_column_length = new ArrayList<>();
            rs.beforeFirst();
            for (int i = 1; i <= col_count; i++) {
                int max_length = 0;
                while (rs.next()) {
                    if (max_length < rs.getString(i).length()) {
                        max_length = rs.getString(i).length();
                    }
                }
                if (max_length < rs.getMetaData().getColumnName(i).length()) {
                    max_length = rs.getMetaData().getColumnName(i).length();
                }
                max_column_length.add(max_length);
                rs.beforeFirst();
            }//-------------------------------------------------
            Pattern_generator pg = new Pattern_generator();
            pg.pattern1(max_column_length, t);
            rs.beforeFirst();
            List<String> ls1 = new ArrayList<>();
            for (int j = 1; j <= col_count; j++) {
                ls1.add(rs.getMetaData().getColumnName(j));

            }
            pg.pattern2(max_column_length, ls1, t);
            pg.pattern1(max_column_length, t);
            while (rs.next()) {
                List<String> ls = new ArrayList<>();
                for (int j = 1; j <= col_count; j++) {
                    ls.add(rs.getString(j));

                }
                pg.pattern2(max_column_length, ls, t);
            }
            //----------------------------------------------------------------
            pg.pattern1(max_column_length, t);

        } catch (SQLException e) {
            t.appendText(e.getMessage());
        }///sdgfgsdkjgfkjsdgfkgsdjfgksdgfksgdkfg
    }

}
