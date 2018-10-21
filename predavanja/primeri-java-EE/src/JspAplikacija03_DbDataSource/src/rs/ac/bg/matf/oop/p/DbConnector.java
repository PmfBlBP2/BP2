package rs.ac.bg.matf.oop.p;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import javax.naming.Context;

import javax.naming.InitialContext;

import javax.sql.DataSource;

public class DbConnector {

	private static DbConnector connector_ = null;

	public static DbConnector getInstance() throws Exception {

		if (connector_ == null) {

			connector_ = new DbConnector();

		}

		return connector_;

	}

	public Connection getConnection() throws Exception {

		// Get DataSource

		Context ctx = new InitialContext();

		DataSource ds = (DataSource) ctx
				.lookup("java:comp/env/jdbc/mydatabase");

		Connection c = ds.getConnection();

		return c;

	}

	public String getFirstLastName() throws Exception {

		String first = "";

		String last = "";

		try {

			Connection con = getConnection();

			Statement st = con.createStatement();

			ResultSet res = st.executeQuery("SELECT * FROM info");

			while (res.next()) {

				first = res.getString("firstname");

				last = res.getString("lastname");

				System.out.println(first + " " + last);

			}

			con.close();

		}

		catch (SQLException s) {

			System.out.println("SQL code does not execute.");

		}

		return first + " " + last;

	}

}