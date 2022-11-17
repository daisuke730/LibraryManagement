import java.sql.*;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

public class DBManager{
	private static final String URL = "jdbc:mySQL://localhost/shark";
	private static final String NAME = "root";
	private static final String PASS = "kcsf";
	private static Connection con;
	private static Statement stmt;
	private static ResultSet result;
	private Date nowDate, returnSche;
	private Calendar calendar;
	
	DBManager(){
		//�R���X�g���N�^
		try{
			con = DriverManager.getConnection(URL,NAME,PASS);
			stmt = con.createStatement();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public boolean serchUser(int user){
		//�ݏo��ʂ�userConfirm�������ɌĂяo�����
		try{
			result = stmt.executeQuery("SELECT user_id FROM  user WHERE user_id = " + user + ";");
			if(result.getString("user_id").equals("")){
				return false;
			}else{
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	public boolean serchBook(int book){
		//�ݏo��ʂ�bookConfirm�������ɌĂяo�����
		try{
			result = stmt.executeQuery("SELECT book_id FROM book_manager WHERE book_id = " + book + ";");
			if(result.getString("book_id").equals("")){
				return false;
			}else{
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	public void lendBook(int user, int book){
		//�ݏo��ʂ�submit�������ɌĂяo�����
		nowDate = new Date();//���ݎ������擾
		calendar = Calendar.getInstance();
		calendar.setTime(nowDate);//�J�����_�[��nowDate�̎���������
		calendar.add(Calendar.DAY_OF_MONTH, 7);//�V�����Z
		returnSche = calendar.getTime();//���Z��̎���������
		try{
			//borrow_user�\�ɐ}���ݏo����ǉ�����
			result = stmt.executeQuery("SELECT stock FROM book_manager WHERE book_id = " + book + ";");
			int i = result.getInt("stock");
			if(0 < i){
				stmt.executeUpdate("UPDATE book_manager SET stock = " + (i - 1) + ";");
				stmt.executeUpdate("INSERT INTO borrow_user VALUES(" +
									user + "," + book + "," + nowDate + "," + returnSche + ");");
			}else{
				
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public boolean userPossesion(int user, int book){
		//�ԋp��ʂ�bookConfirm�������ɌĂяo�����
		try{
			result = stmt.executeQuery("SELECT book_id FROM borrow_user WHERE user_id = " + user + " and book_id = " + book + ";");
			if(result.getString("book_id").equals("")){
				return false;
			}else{
				return true;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public void returnBook(List<Integer> list){
		//�ԋp��ʂ�submit�������ɌĂяo�����
		int cnt = 0;
		try{
			for(int i = 0; result.next() == true; i++){
				stmt.executeUpdate("DELETE FROM borrow_user WHERE book_id = " + list.get(i) + ";");
			}
			for(int i = 0; result.next() == true; i++){
				result = stmt.executeQuery("SELECT stock FROM book_manager WHERE book_id = " + list.get(i) + ";");
				cnt = result.getInt("stock") + 1;
				stmt.executeUpdate("UPDATE book_manager SET stock = " + cnt + " WHERE book_id = " + list.get(i) + ";");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public List<String> returnSche(int user){
		//�ԋp��ʂ�userConfirm�������ɌĂяo�����
		Date returnDate;
		nowDate = new Date();
		List<String> nameList = new ArrayList<>();
		try{
			while(result.next()){
				result = stmt.executeQuery("SELECT book_manager.book_name borrow_user.scheduled_return_date " +
											"FROM book_manager borrow_user WHERE book_manager.book_id = borrow_user.book_id;");
				returnDate = result.getDate("sheduled_return_date");
				if(returnDate.before(nowDate) == true){
					nameList.add(result.getString("book_name"));
				}else{
					nameList.add(result.getString("book_name") + "[�x]");
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return nameList;
	}
	
	public List<String> serchBookName(String name){
		//�����{�^����serchBtn�������ɌĂяo�����
		List<String> nameList = new ArrayList<>();
		try{
			while(result.next()){
				result = stmt.executeQuery("SELECT book_name FROM book_manager WHERE book_name LIKE '%" + name + "%';");
				nameList.add(result.getString("book_name"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return nameList;
	}
	
	public List<String> serchBookCategory(String name){
		//�����{�^����serchBtn�������ɌĂяo�����
		List<String> categoryList = new ArrayList<>();
		try{
			while(result.next()){
				result = stmt.executeQuery("SELECT category FROM book_manager WHERE book_name LIKE '%" + name + "%';");
				categoryList.add(result.getString("category"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return categoryList;
	}
	
	public List<Integer> serchBookStock(String name){
		//�����{�^����serchBtn�������ɌĂяo�����
		List<Integer> stockList = new ArrayList<>();
		try{
			while(result.next()){
				result = stmt.executeQuery("SELECT stock FROM book_manager WHERE book_name LIKE '%" + name + "%';");
				stockList.add(result.getInt("stock"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return stockList;
	}
}