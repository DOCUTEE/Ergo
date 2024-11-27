package cnpm.ergo.DAO.interfaces;

import java.util.List;

import cnpm.ergo.entity.CampaignImageEntity;
import cnpm.ergo.entity.MarketingCampaignEntity;

public interface IMarketingCampaignDao {
	void insert(MarketingCampaignEntity campaignEntity, List<CampaignImageEntity> images);
	void update(MarketingCampaignEntity campaignEntity);
	void delete(Long campaignId);
	List<MarketingCampaignEntity> findAll();
	MarketingCampaignEntity findById(Long id);
}
