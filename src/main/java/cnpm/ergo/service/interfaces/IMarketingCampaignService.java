package cnpm.ergo.service.interfaces;

import java.util.List;

import cnpm.ergo.entity.CampaignImageEntity;
import cnpm.ergo.entity.MarketingCampaignEntity;

public interface IMarketingCampaignService {
	void addCampaign(MarketingCampaignEntity campaignEntity, List<CampaignImageEntity> images);
	void updateCampaign(MarketingCampaignEntity campaignEntity);
	void deleteCampaign(Long Id);
	List<MarketingCampaignEntity> findAllMarketingCampaign();
	MarketingCampaignEntity findById(Long id);
}
