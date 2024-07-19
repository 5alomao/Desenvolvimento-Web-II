package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Company;
import model.ModelException;
import model.Seller;
import model.dao.CompanyDAO;
import model.dao.DAOFactory;
import model.dao.SellerDAO;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/sellers", "/seller/form", "/seller/insert" })
public class SellersController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getRequestURI();

		switch (action) {
		case "/crud-manager/seller/form": {
			loadCompanies(req);
			ControllerUtil.forward(req, resp, "/form-seller.jsp");
			break;
		}
		default:
			listSellers(req);

			ControllerUtil.transferSessionMessagesToRequest(req);

			ControllerUtil.forward(req, resp, "/sellers.jsp");
		}
	}

	private void loadCompanies(HttpServletRequest req) {
		CompanyDAO dao = DAOFactory.createDAO(CompanyDAO.class);
		List<Company> companies = new ArrayList<>();
		try {
			companies = dao.listAll();
		} catch (ModelException e) {
			ControllerUtil.errorMessage(req, "Erro ao carregar as empresas.");
		}
		req.setAttribute("companies", companies);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getRequestURI();

		switch (action) {
		case "/crud-manager/seller/insert": {
			insertSeller(req, resp);
			ControllerUtil.redirect(resp, req.getContextPath() + "/sellers");
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + action);
		}
	}

	private void insertSeller(HttpServletRequest req, HttpServletResponse resp) {
		String sellerName = req.getParameter("seller_name");
		String sellerEmail = req.getParameter("seller_email");
		String sellerFone = req.getParameter("seller_fone");
		String sellerCompany = req.getParameter("seller_company");
		int sellerCompanyId = Integer.parseInt(sellerCompany);

		Seller seller = new Seller();
		seller.setName(sellerName);
		seller.setEmail(sellerEmail);
		seller.setFone(sellerFone);
		seller.setCompany(new Company(sellerCompanyId));

		SellerDAO dao = DAOFactory.createDAO(SellerDAO.class);

		try {
			if (dao.save(seller))
				ControllerUtil.sucessMessage(req, "Vendedor " + seller.getName() + " salvo com sucesso.");
			else
				ControllerUtil.errorMessage(req, "Vendedor " + seller.getName() + " não pode ser salvo.");
		} catch (ModelException e) {
			e.printStackTrace(); // log
			ControllerUtil.errorMessage(req, sellerCompany);
		}
	}

	private void listSellers(HttpServletRequest req) {

		SellerDAO dao = DAOFactory.createDAO(SellerDAO.class);

		List<Seller> sellers = new ArrayList<>();

		try {
			sellers = dao.listAll();
		} catch (ModelException e) {
			ControllerUtil.errorMessage(req, "Erro ao carregar dados dos vendedores.");
		}

		req.setAttribute("sellers", sellers);
	}

}
