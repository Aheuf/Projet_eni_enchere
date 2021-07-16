package fr.eni.enchere.piou.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.piou.BusinessException;
import fr.eni.enchere.piou.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements DAO<Utilisateur> {

	private static final String INSERTUTILISATEUR = "INSERT INTO UTILISATEURS(pseudo,nom,prenome,mail,telephone,rue,code_postal,ville,mot_de_passe,credit,administrateur) VALUES(?,?,?,?,?,?,?,?,?,?,?);";
	private static final String UPDATEUTILISATEUR = "UPDATE UTILISATEURS SET pseudo=?,nom,prenome=?,mail=?,telephone=?,rue=?,code_postal=?,ville=?,mot_de_passe=?,credit=?,administrateur=? WHERE no_utilisateur +";
	private static final String DELETEUTILISATEUR = "DELETE FROM UTILISATEURS WHERE no_utilisateur=";

	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {

		if (utilisateur == null) {

			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);
			throw businessException;

		}
		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(INSERTUTILISATEUR, PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("erreur dall utilisateur insert");

			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_ECHEC);

			throw businessException;

		}

	}

	@Override
	public void delete(int id) throws BusinessException {

		PreparedStatement prep = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pstmt = cnx.prepareStatement(DELETEUTILISATEUR + id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("erreur dall utilisateur delete");

			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_NULL);

			throw businessException;

		}

	}

	@Override
	public List<Utilisateur> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectByMotCle(String montCle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Utilisateur> selectById(int id) throws BusinessException {
		Statement stmt = null;
		List<Utilisateur> utilisateur = new ArrayList<Utilisateur>();
		Utilisateur u = null;

		try (Connection cnx = ConnectionProvider.getConnection()) {
			stmt = cnx.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM UTILISATEURS WHERE no_utilisateur= " + id);

			while (rs.next()) {

				int noUtilisateur = rs.getInt("no_utilisateur");
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String email = rs.getString("email");
				String telephone = rs.getString("telephone");
				String rue = rs.getString("rue");
				String codePostal = rs.getString("code_postal");
				String ville = rs.getString("pseudo");
				String motDePasse = rs.getString("pseudo");
				int credit = rs.getInt("no_utilisateur");
				boolean administrateur = rs.getBoolean("administrateur");

				u = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, codePostal, ville,
						motDePasse, credit, administrateur);
				utilisateur.add(u);
			}
		} catch (SQLException e) {
			System.out.println("erreur dall utilisateur selectbyid");

			e.printStackTrace();
			BusinessException businessException = new BusinessException();

            businessException.ajouterErreur(CodesResultatDAL.READ_OBJECT_ECHEC);

			throw businessException;

		}
		return utilisateur;
	}

	@Override
	public void update(Utilisateur utilisateur) throws BusinessException {
		int id = utilisateur.getNoUtilisateur();

		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(UPDATEUTILISATEUR + id);
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdministrateur());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("erreur dall utilisateur update");

			e.printStackTrace();
			BusinessException businessException = new BusinessException();

			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJET_ECHEC);

			throw businessException;

		}

	}

}