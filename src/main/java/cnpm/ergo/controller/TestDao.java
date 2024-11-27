package cnpm.ergo.controller;

import java.util.ArrayList;
import java.util.List;

import cnpm.ergo.DAO.implement.MarketingCampaignDaoImpl;
import cnpm.ergo.entity.CampaignImageEntity;
import cnpm.ergo.entity.MarketingCampaignEntity;
import cnpm.ergo.service.implement.CampaignImageServiceImpl;
import cnpm.ergo.service.implement.MarketingCampaignServiceImpl;

public class TestDao {
	public static void main(String[] args) {
		// Tạo instance DAO
		MarketingCampaignDaoImpl campaignDao = new MarketingCampaignDaoImpl();
		MarketingCampaignServiceImpl campaignService = new MarketingCampaignServiceImpl();
		CampaignImageServiceImpl campaignImageServiceImpl = new CampaignImageServiceImpl();
		// Thêm một chiến dịch mới
		MarketingCampaignEntity newCampaign = new MarketingCampaignEntity();
		newCampaign.setContent("test DAO lần 4");
		newCampaign.setIsDelete(false);
		newCampaign.setVoucherId(12L);
		List<CampaignImageEntity> campaignImageEntities = new ArrayList<CampaignImageEntity>();
		CampaignImageEntity campaignImageEntity1 = new CampaignImageEntity();
		campaignImageEntity1.setImagePath("A");
		CampaignImageEntity campaignImageEntity2 = new CampaignImageEntity();
		campaignImageEntity2.setImagePath("B");
		campaignImageEntities.add(campaignImageEntity1);
		campaignImageEntities.add(campaignImageEntity2);
		// Thêm
        System.out.println("==== Thêm chiến dịch ====");
        try {
        	campaignService.addCampaign(newCampaign, campaignImageEntities);
            System.out.println("Thêm thành công chiến dịch: " + newCampaign.getContent());
        } catch (Exception e) {
            System.err.println("Lỗi thêm chiến dịch: " + e.getMessage());
        }

		// Sửa
//        System.out.println("\n==== Cập nhật chiến dịch ====");
//        try {
//        	newCampaign = campaignService.findById(4L);
//            newCampaign.setContent("Sốc không giảm");
//            System.out.println(newCampaign.getCampaignId());
//            campaignService.updateCampaign(newCampaign);
//            System.out.println("Cập nhật thành công mô tả chiến dịch: " + newCampaign.getContent());
//        } catch (Exception e) {
//            System.err.println("Lỗi cập nhật chiến dịch: " + e.getMessage());
//        }
		// Xóa
//		System.out.println("\n==== Xóa chiến dịch ====");
//		try {
//			campaignDao.delete(6L);
//			System.out.println("Xóa thành công chiến dịch");
//		} catch (Exception e) {
//			System.err.println("Lỗi xóa chiến dịch: " + e.getMessage());
//		}

		// Lấy tất cả các chiến dịch
		System.out.println("\n==== Danh sách chiến dịch ====");
		try {
			List<MarketingCampaignEntity> campaigns = campaignDao.findAll();
			campaigns.forEach(campaign -> System.out.println("Chiến dịch: " + campaign.getContent()));
		} catch (Exception e) {
			System.err.println("Lỗi lấy danh sách chiến dịch: " + e.getMessage());
		}

		// Lấy các image campainMarketing
		System.out.println("===== Lấy các image của chiến dịch 7 =====");
		// Giả sử campaignId là 1
		Long campaignId = 7L;
		// Gọi phương thức findImagesByCampaignId
		List<CampaignImageEntity> images = campaignImageServiceImpl.findImagesByCampaignId(campaignId);
		// In kết quả
		if (images.isEmpty()) {
			System.out.println("Không có hình ảnh nào cho chiến dịch với campaignId: " + campaignId);
		} else {
			for (CampaignImageEntity image : images) {
				System.out.println("Image ID: " + image.getImageId() + ", Image Path: " + image.getImagePath());
			}
		}
	}
}
