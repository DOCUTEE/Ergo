package cnpm.ergo.controller.customer;

import cnpm.ergo.service.implement.CategoryServiceImpl;
import cnpm.ergo.service.implement.MarketingCampaignServiceImpl;
import cnpm.ergo.service.implement.ProductServiceImpl;
import cnpm.ergo.service.interfaces.ICategoryService;
import cnpm.ergo.service.interfaces.IMarketingCampaignService;
import cnpm.ergo.service.interfaces.IProductService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CustomerHomeController", value = "/home")
public class CustomerHomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Get list of Marketing Campaign
        IMarketingCampaignService marketingCampaignService = new MarketingCampaignServiceImpl();
        request.setAttribute("marketingCampaigns", marketingCampaignService.findAllMarketingCampaign());

        //Get 10 first products
        IProductService productService = new ProductServiceImpl();
        request.setAttribute("products", productService.getAllProducts(1,10));

        //get all categories
        ICategoryService categoriesService = new CategoryServiceImpl();
        request.setAttribute("categories", categoriesService.getAllCategories());
        request.getRequestDispatcher("/customer/views/Home.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}