package cnpm.ergo.service.implement;

import java.util.List;

import cnpm.ergo.DAO.implement.MarketingCampaignDaoImpl;
import cnpm.ergo.DAO.interfaces.IMarketingCampaignDao;
import cnpm.ergo.entity.CampaignImageEntity;
import cnpm.ergo.entity.MarketingCampaignEntity;
import cnpm.ergo.service.interfaces.IMarketingCampaignService;

public class MarketingCampaignServiceImpl implements IMarketingCampaignService{

	public IMarketingCampaignDao campaignDao = new MarketingCampaignDaoImpl();

	@Override
	public void updateCampaign(MarketingCampaignEntity campaignEntity) {
		campaignDao.update(campaignEntity);
	}

	@Override
	public void deleteCampaign(Long Id) {
		campaignDao.delete(Id);
	}

	@Override
	public List<MarketingCampaignEntity> findAllMarketingCampaign() {
		List<MarketingCampaignEntity> campaignEntities = campaignDao.findAll();
		return campaignEntities;
	}

	@Override
	public MarketingCampaignEntity findById(Long id) {
		return campaignDao.findById(id);
	}

	@Override
	public void addCampaign(MarketingCampaignEntity campaignEntity, List<CampaignImageEntity> images) {
		campaignDao.insert(campaignEntity, images);
	}
}
