package cnpm.ergo.DAO.implement;

import java.util.List;

import cnpm.ergo.DAO.interfaces.IMarketingCampaignDao;
import cnpm.ergo.configs.JPAConfig;
import cnpm.ergo.entity.CampaignImageEntity;
import cnpm.ergo.entity.MarketingCampaignEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MarketingCampaignDaoImpl implements IMarketingCampaignDao {

	private EntityManager entityManager = JPAConfig.getEntityManager();
	@Override
	public void update(MarketingCampaignEntity campaignEntity) {
		EntityTransaction transaction = entityManager.getTransaction();
	    try {
	        transaction.begin(); 
	        entityManager.merge(campaignEntity);
	        transaction.commit(); 
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        throw new RuntimeException("Cập nhật MarketingCampaign không thành công: " + e.getMessage(), e);
	    }
	}

	@Override
	public void delete(Long campaignId) {
		EntityTransaction transaction = entityManager.getTransaction();
	    try {
	        transaction.begin();
	        MarketingCampaignEntity campaign = entityManager.find(MarketingCampaignEntity.class, campaignId);
	        if (campaign != null) {
	            entityManager.remove(campaign);
	        } else {
	            throw new RuntimeException("Không tìm thấy MarketingCampaign với ID: " + campaignId);
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        throw new RuntimeException("Xóa lỗi: " + e.getMessage(), e);
	    }
	}

	@Override
	public List<MarketingCampaignEntity> findAll() {
		try {
	        return entityManager.createQuery("SELECT m FROM MarketingCampaignEntity m", MarketingCampaignEntity.class)
	                            .getResultList();
	    } catch (Exception e) {
	        throw new RuntimeException("Lỗi truy vấn: " + e.getMessage(), e);
	    }
	}
	@Override
	public MarketingCampaignEntity findById(Long id) {
	    return entityManager.find(MarketingCampaignEntity.class, id);
	}

	@Override
	public void insert(MarketingCampaignEntity campaignEntity, List<CampaignImageEntity> images) {
		EntityTransaction transaction = entityManager.getTransaction();
        try {
        	for (CampaignImageEntity image : images) {
                image.setMarketingCampaign(campaignEntity);  
            }
        	campaignEntity.setCampaignImages(images);
            transaction.begin();
            entityManager.persist(campaignEntity); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Không thêm được MarketingCampaignEntity này: " + e.getMessage(), e);
        }
	}
}
