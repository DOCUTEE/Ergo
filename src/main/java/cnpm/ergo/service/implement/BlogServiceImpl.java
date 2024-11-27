package cnpm.ergo.service.implement;

import java.util.List;
import cnpm.ergo.service.interfaces.IBlogService;
import cnpm.ergo.DAO.implement.BlogDaoImpl;
import cnpm.ergo.DAO.interfaces.IBlogDao;
import cnpm.ergo.entity.Blog;

public class BlogServiceImpl implements IBlogService {

    private final IBlogDao blogDao = new BlogDaoImpl();

    @Override
    public void addBlog(Blog blog) {
        blogDao.insert(blog);
    }

    @Override
    public void updateBlog(Blog blog) {
        blogDao.update(blog);
    }

    @Override
    public void deleteBlog(int blogId) {
        try {
            blogDao.delete(blogId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting blog with ID: " + blogId);
        }
    }

    @Override
    public Blog getBlogById(int blogId) {
        return blogDao.findById(blogId);
    }

    @Override
    public List<Blog> getAllBlogs() {
        return blogDao.findAll();
    }

    @Override
    public List<Blog> searchBlogsByTitle(String title) {
        return blogDao.searchByTitle(title);
    }

    @Override
    public int getBlogCount() {
        return blogDao.count();
    }
}
