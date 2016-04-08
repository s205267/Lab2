package it.polito.tdp.spellchecker.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
	public class ParolaDAO {
		String jdbcURL = "jdbc:mysql://localhost/dizionario?user=root";

		/*public VoceRubrica findVoceByNome(String nomeCercato){
		
		try{	
			Connection conn = DriverManager.getConnection(jdbcURL);
			
			Statement st = conn.createStatement();
			
			String sql ="Select nome,email,telefono From voce WHERE nome=\""+nomeCercato+"\"";
			
			ResultSet res = st.executeQuery(sql);
			
			if(res.next())
			{
				String nome = res.getString("nome");
				String telefono= res.getString("telefono");
				String email = res.getString("email");
				VoceRubrica v = new VoceRubrica(nome,email,telefono);
				res.close();
				conn.close();
				return v;
			}
			else
			{	
				res.close();
				conn.close();
				return null;
			}
		}
	catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}*/
	
		
		
		
		public List<String> elencoParole()
		{
			List<String> parole = new ArrayList<String>();
			
			try{	
				Connection conn = DriverManager.getConnection(jdbcURL);
				
				Statement st = conn.createStatement();
				
				String sql= "Select nome From parola";				
				ResultSet res = st.executeQuery(sql);
				
				while(res.next())
				{
					String parola = res.getString("nome");
					parole.add(parola);
					
				}
				return parole;
			}
		catch(SQLException e)
			{
				e.printStackTrace();
			}
			return null;
		}
		/** 
		 * Cerca una parola nel dizionario. Se la trova 
		 *  restituisce {@code true}, altrimenti {@code false}
		 * @param parola la parola da ancdare a ricercare
		 * @return {@code true } se la parola è presente nel dizionario
		 * {@code false } se la parola è inesistente
		 */
		public boolean contains(String parola){
			
			try {
				Connection conn = DriverManager.getConnection(jdbcURL);
				Statement st = conn.createStatement();
				
				String sql = "select id from  parola where nome=\""+parola+"\"";
				
				ResultSet res = st.executeQuery(sql);
				
				//ora uso l' IF perchè ho al più una riga
				if (res.next()) {
					// found
					
					//prima libero la memoria...
					res.close();
					conn.close();

					//...e poi faccio return
					return true;
				} else {
					// not found
					res.close();
					conn.close();
					return false;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			return false; //dopo il catch devo sempre metterci un return
		}
		
		


	
	
}


