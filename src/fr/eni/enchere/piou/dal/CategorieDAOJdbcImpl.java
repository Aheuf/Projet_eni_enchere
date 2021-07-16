package fr.eni.enchere.piou.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bo.Categorie;

public class CategorieDAOJdbcImpl implements DAO<Categorie> {

	private static final String SELECT_ALL = "SELECT * FROM CATEGORIES";
	
	private static final String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
	
	@Override
	public void insert(Categorie object) throws BusinessException {
		
	}

	@Override
	public void delete(int id) throws BusinessException {
		
	}

	@Override
	public List<Categorie> selectAll() throws BusinessException {
		List<Categorie> categories = new ArrayList<Categorie>();
		try(Connection cnx = ConnectionProvider.getConnection())
		{
			Statement stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery(SELECT_ALL);
			while(rs.next())
			{
				categories.add(new Categorie(rs.getInt("no_categorie"), rs.getString("libelle")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
            System.out.println("SELECT ALL Categories pas content");

            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.READ_OBJECT_ECHEC);
			throw businessException;
		}
		return categories;
	}

	@Override
	public List<Categorie> selectByMotCle(String montCle) throws BusinessException {
		return null;
	}

	@Override
	public List<Categorie> selectById(int id) throws BusinessException {
        PreparedStatement pstmt = null;
        List<Categorie> categories = new ArrayList<Categorie>();

        try (Connection con = ConnectionProvider.getConnection()) {
            pstmt = con.prepareStatement(SELECT_BY_ID);
            
            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	Categorie categorie = null;
                int noCategorie = rs.getInt(1);						
                String libelle = rs.getString(2);					

                categorie = new Categorie(noCategorie, libelle);	
                categories.add(categorie);
            }
            
        } catch (SQLException e) {  	
            System.out.println("SELECT BY ID Categories pas content");
            e.printStackTrace();
            
            BusinessException businessException = new BusinessException();
            businessException.ajouterErreur(CodesResultatDAL.READ_OBJECT_ECHEC);
			throw businessException;
        }
        return categories;
	}

	@Override
	public void update(Categorie object) throws BusinessException {
		
	}

}
