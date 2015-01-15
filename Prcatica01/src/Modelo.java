import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.mysql.jdbc.ResultSetMetaData;

public class Modelo extends Conexion {

	public Modelo() {
		createTableTest();
		createTableAnswer();
		createTableQuestion();
		createTableUser();

	}

	public boolean createTableTest() {
		boolean res = false;

		String q = "CREATE TABLE IF NOT EXISTS TEST  (_id TINYINT(2) NOT NULL AUTO_INCREMENT, Name VARCHAR(20), Date DATE, Score INT(6), Number_questions TINYINT(3), PRIMARY KEY (_id)) ENGINE=InnoDB";

		try {
			Statement st = this.getConexion().createStatement();
			st.execute(q);
			st.close();
			res = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	public boolean createTableUser() {
		boolean res = false;

		String q = "CREATE TABLE IF NOT EXISTS USER  (_id TINYINT(2) NOT NULL AUTO_INCREMENT, Name VARCHAR(20), Email VARCHAR(30), PRIMARY KEY (_id)) ENGINE=InnoDB";

		try {
			Statement st = this.getConexion().createStatement();
			st.execute(q);
			st.close();
			res = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	public boolean createTableAnswer() {
		boolean res = false;

		String q = "CREATE TABLE IF NOT EXISTS ANSWER  (_id TINYINT(2) NOT NULL AUTO_INCREMENT, Text TEXT, Is_correct BOOLEAN, Question_id TINYINT(2), PRIMARY KEY (_id)) ENGINE=InnoDB";

		try {
			Statement st = this.getConexion().createStatement();
			st.execute(q);
			st.close();
			res = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	public boolean createTableQuestion() {
		boolean res = false;

		String q = "CREATE TABLE IF NOT EXISTS QUESTION (_id TINYINT(2) NOT NULL AUTO_INCREMENT, Text TEXT, Test_id TINYINT(2), PRIMARY KEY (_id)) ENGINE=InnoDB";

		try {
			Statement st = this.getConexion().createStatement();
			st.execute(q);
			st.close();
			res = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	public boolean alterTableAnswer() {
		boolean res = false;

		String q = "ALTER TABLE ANSWER ADD CONSTRAINT FK_ANSWERS_QUESTIONS FOREIGN KEY (Question_id) REFERENCES QUESTION (_id)";

		try {
			Statement st = this.getConexion().createStatement();
			st.execute(q);
			st.close();
			res = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}
	
	public boolean alterTableQuestion() {
		boolean res = false;

		String q = "ALTER TABLE QUESTION ADD CONSTRAINT FK_QUESTION_TEST FOREIGN KEY (Test_id) REFERENCES TEST (_id)";

		try {
			Statement st = this.getConexion().createStatement();
			st.execute(q);
			st.close();
			res = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return res;
	}

	public void printTestInfo() {
		ResultSet res = null;
		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(
					"SELECT _id FROM TEST");
			res = pstm.executeQuery();
			ResultSetMetaData rsm = (ResultSetMetaData) res.getMetaData();

			while (res.next()) {
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					System.out.println(rsm.getColumnLabel(i) + ": "
							+ res.getObject((rsm.getColumnLabel(i))));
				}
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (res != null)
				try {
					res.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
		}
	}

	public void printUser() {
		ResultSet res = null;

		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(
					"SELECT * FROM USER");
			res = pstm.executeQuery();

			ResultSetMetaData rsm = (ResultSetMetaData) res.getMetaData();

			while (res.next()) {
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					System.out.println(rsm.getColumnLabel(i) + ": "
							+ res.getObject((rsm.getColumnLabel(i))));
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (res != null)
				try {
					res.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
		}
	}
	
	private void printAnswer(String text) {
		String q = "SELECT Text, Is_correct FROM ANSWER WHERE Question_id = (SELECT _id FROM  QUESTION WHERE Text = ?)";
		ResultSet res = null;

		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(q);
			pstm.setString(1, text);
			res = pstm.executeQuery();

			ResultSetMetaData rsm = (ResultSetMetaData) res.getMetaData();

			while (res.next()) {
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					System.out.println(res.getObject((rsm.getColumnLabel(i))));
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (res != null)
				try {
					res.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
		}
	}

	public void printQuestion() {
		ResultSet res = null;
		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(
					"SELECT * FROM QUESTION");
			res = pstm.executeQuery();
			ResultSetMetaData rsm = (ResultSetMetaData) res.getMetaData();

			while (res.next()) {
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					System.out.println(rsm.getColumnLabel(i) + ": "
							+ res.getObject((rsm.getColumnLabel(i))));
				}
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (res != null)
				try {
					res.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
		}
	}
	

	private void addQuestion(Question question) {
		
			PreparedStatement pstm = null;
			String q = " INSERT INTO QUESTION (Text, Test_id) VALUES (?, ?) ";

			try {
				pstm = this.getConexion().prepareStatement(q);
				pstm.setString(1, question.getText());
				pstm.setInt(2, question.getTest_id());
				pstm.execute();
				
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			} finally {
				if (pstm != null)
					try {
						pstm.close();
					} catch (SQLException e) {
						System.err.println(e.getMessage());
					}
			}
	}
	private int getAutoIncrementQuestion (){
		ResultSet res = null;
		int antoincrement = 0;
		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(
					"SELECT AUTO_INCREMENT FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'Quizit' AND  TABLE_NAME = 'QUESTION'");
			res = pstm.executeQuery();
			ResultSetMetaData rsm = (ResultSetMetaData) res.getMetaData();
			
			while (res.next()) {
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					antoincrement = res.getInt((rsm.getColumnLabel(i)));
				}
			}	

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (res != null)
				try {
					res.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
		}
		return antoincrement;
	}
	
	private void addAnswer(Answer answer) {
		
		PreparedStatement pstm = null;
		String q = "INSERT INTO ANSWER (Text, Is_correct, Question_id) VALUES ( ? ,? ,?) ";

		try {
			pstm = this.getConexion().prepareStatement(q);
			
			pstm.setString(1, answer.getText());
			pstm.setBoolean(2, answer.getIs_correct());
			pstm.setInt(3, getAutoIncrementQuestion()-1);
			pstm.execute();
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
		}
}

	public void addAnswer_question (Question question, Answer answer, Answer answer2){
		
		addQuestion(question);
		addAnswer(answer);
		addAnswer(answer2);
		
	}
	
	public void printTest(int id_test){
		String q = "SELECT Text FROM QUESTION WHERE Test_id = (SELECT _id FROM  TEST WHERE _id = ?)";
		String text = "";
		ResultSet res = null;
	
		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(q);
			pstm.setInt(1, id_test);
			res = pstm.executeQuery();

			ResultSetMetaData rsm = (ResultSetMetaData) res.getMetaData();

			while (res.next()) {
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					System.out.println(res.getObject((rsm.getColumnLabel(i))));
					text = (String) res.getObject((rsm.getColumnLabel(i)));
					printAnswer(text);
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (res != null)
				try {
					res.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
		}
	}
	
	public void createNewTest (String nameTest){
		Random rand = new Random();
		
		String q = "INSERT INTO TEST ( _id, Name, Date, Score, Number_questions) VALUES (NULL, ?, CURRENT_DATE(), '0', '0')";
		
		String q2 ="UPDATE QUESTION SET Test_id = (SELECT _id FROM TEST WHERE  Name = ?) WHERE _id = ?";
		
		ResultSet res = null;
		ResultSet res2 = null;
		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(q);
			
			pstm.setString(1, nameTest);
			pstm.execute();
			
			PreparedStatement pstm2 = this.getConexion().prepareStatement(q2);
			pstm2.setString(1, nameTest);
			pstm2.setInt(2, rand.nextInt(numColums()));
			
			pstm2.execute();
			

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (res != null)
				try {
					res.close();
					
					res2.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
		}
	}
	
	public int numColums (){
		ResultSet res = null;
		int num_co = 0;
		try {
			PreparedStatement pstm = this.getConexion().prepareStatement(
					"SELECT count(_id) FROM QUESTION");
			res = pstm.executeQuery();
			ResultSetMetaData rsm = (ResultSetMetaData) res.getMetaData();
			
			while (res.next()) {
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					num_co = res.getInt((rsm.getColumnLabel(i)));
				}
			}	

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} finally {
			if (res != null)
				try {
					res.close();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
		}
		return num_co;
	}
	
	public void startTest (String user, String test){
		
	}

}
