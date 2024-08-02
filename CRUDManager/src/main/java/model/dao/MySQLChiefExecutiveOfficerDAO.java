package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.ChiefExecutiveOfficer;
import model.Company;
import model.ModelException;

public class MySQLChiefExecutiveOfficerDAO implements ChiefExecutiveOfficerDAO {

	@Override
	public boolean save(ChiefExecutiveOfficer ceo) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlInsert = "INSERT INTO ceos VALUES (DEFAULT, ?, ?, ?, ?, ?);";

		db.prepareStatement(sqlInsert);

		db.setString(1, ceo.getName());
		db.setString(2, ceo.getGender());
		db.setString(3, ceo.getEmail());
		db.setString(4, ceo.getFone());
		db.setInt(5, ceo.getCompany().getId());

		return db.executeUpdate() > 0;
	}

	@Override
	public boolean update(ChiefExecutiveOfficer ceo) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlUpdate = "UPDATE ceos SET name = ?, gender = ?, email = ?, fone = ?, company_id = ? where id = ?";

		db.prepareStatement(sqlUpdate);

		db.setString(1, ceo.getName());
		db.setString(2, ceo.getGender());
		db.setString(3, ceo.getEmail());
		db.setString(4, ceo.getFone());
		db.setInt(5, ceo.getCompany().getId());
		db.setInt(6, ceo.getId());

		return db.executeUpdate() > 0;
	}

	@Override
	public boolean delete(ChiefExecutiveOfficer ceo) throws ModelException {
		DBHandler db = new DBHandler();

		String sqlDelete = "DELETE FROM ceos WHERE id = ?";

		db.prepareStatement(sqlDelete);

		db.setInt(1, ceo.getId());

		return db.executeUpdate() > 0;
	}

	@Override
	public ChiefExecutiveOfficer findById(int id) throws ModelException {
		DBHandler db = new DBHandler();

		String sql = "SELECT * FROM ceos WHERE id = ?";

		db.prepareStatement(sql);

		db.setInt(1, id);
		db.executeQuery();

		ChiefExecutiveOfficer ceo = null;

		while (db.next()) {
			ceo = createCEO(db);
			break;
		}

		return ceo;
	}

	@Override
	public List<ChiefExecutiveOfficer> listAll() throws ModelException {
		DBHandler db = new DBHandler();

		List<ChiefExecutiveOfficer> ceos = new ArrayList<>();

		String sqlQuery = "SELECT c.id as ID, c.name as Name, c.gender as Gender, c.email as Email, c.fone as Fone, cp.id as company_id from ceos c inner join companies cp on c.company_id = cp.id;";

		db.createStatement();

		db.executeQuery(sqlQuery);

		while (db.next()) {
			ChiefExecutiveOfficer ceo = createCEO(db);

			ceos.add(ceo);
		}

		return ceos;
	}

	private ChiefExecutiveOfficer createCEO(DBHandler db) throws ModelException {
		ChiefExecutiveOfficer ceo = new ChiefExecutiveOfficer(db.getInt("id"));
		ceo.setName(db.getString("name"));
		ceo.setGender(db.getString("gender"));
		ceo.setEmail(db.getString("email"));
		ceo.setFone(db.getString("fone"));

		CompanyDAO companyDAO = DAOFactory.createDAO(CompanyDAO.class);

		Company company = companyDAO.findById(db.getInt("company_id"));

		ceo.setCompany(company);

		return ceo;
	}
}
