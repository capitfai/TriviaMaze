/*
 * TriviaMaze
 * Fall 2023
 */
package model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Question class using SQLite databases to populate table of questions and answers.
 * (Referenced from SQLite demo provided by Professor Capaul.
 *
 * @author Tom Capaul
 * @author Faith Capito
 * @author David Hoang
 * @version Fall 2023
 */
public class QuestionDatabase {

    public static void main(String[] args) {
        SQLiteDataSource ds = null;

        try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:Questions.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println( "Opened database successfully" );

        String query = "CREATE TABLE IF NOT EXISTS questions ( " +
                "QUESTION TEXT NOT NULL, " +
                "ANSWER TEXT NOT NULL )";
        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println( "Created questions table successfully" );

        //next insert two rows of data
        System.out.println( "Attempting to insert two rows into questions table" );

        String query1 = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'Last name of Java creator?', 'Gosling' )";
        String query2 = "INSERT INTO questions ( QUESTION, ANSWER ) VALUES ( 'This statement is false', 'paradox' )";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query1 );
            System.out.println( "1st executeUpdate() returned " + rv );

            rv = stmt.executeUpdate( query2 );
            System.out.println( "2nd executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }


        //now query the database table for all its contents and display the results
        System.out.println( "Selecting all rows from questions table" );
        query = "SELECT * FROM questions";

        try ( Connection conn = ds.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            // and print it
            while ( rs.next() ) {
                String question = rs.getString( "QUESTION" );
                String answer = rs.getString( "ANSWER" );

                System.out.println( "Result: Question = " + question +
                        ", Answer = " + answer );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
        System.out.println("press enter to close program/window");
        Scanner input = new Scanner(System.in);
        input.nextLine();
    }

}