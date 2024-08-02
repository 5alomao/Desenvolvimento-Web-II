package model.dao;

import java.util.List;

import model.ChiefExecutiveOfficer;
import model.ModelException;

public interface ChiefExecutiveOfficerDAO {

	boolean save(ChiefExecutiveOfficer ceo) throws ModelException;

	boolean update(ChiefExecutiveOfficer ceo) throws ModelException;

	boolean delete(ChiefExecutiveOfficer ceo) throws ModelException;

	List<ChiefExecutiveOfficer> listAll() throws ModelException;

	ChiefExecutiveOfficer findById(int id) throws ModelException;

}