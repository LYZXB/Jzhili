package com.jzl.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jzl.beans.common.Message;
import com.jzl.beans.user.PushSetting;
import com.jzl.repository.PushSettingRepository;

/**
 * 
 * @ClassName: PushSettingRepositoryImpl  
 * @Description: 推送设置数据接口实现类  
 * @author Sombra  
 * @date 2018年4月9日 下午4:37:24  
 * @version V1.0
 */
@Repository
public class PushSettingRepositoryImpl extends AbstractRepositoryImpl implements PushSettingRepository {

	@Override
	public List<PushSetting> findNeedPushData(Integer count) {
		String sql = "select * from pushsetting where IFNULL(pushDate,0) < UNIX_TIMESTAMP(CAST(SYSDATE() as date))*1000 and pushType != 0 and stat = 1 limit 0," + count;
		return jdbcTemplate.query(sql, (rs, num) -> {
			PushSetting setting = new PushSetting();
			setting.setGco(rs.getString("gco"));
			setting.setStat(rs.getInt("stat"));
			setting.setUserName(rs.getString("userName"));
			setting.setPushType(rs.getInt("pushType"));
			setting.setForwardDays(rs.getInt("forwardDays"));
			setting.setPushDate(rs.getLong("pushDate"));
			return setting;
		});
	}

	@Override
	public Message refreshPushDate(String gco) {
		String sql = "update pushsetting set pushDate = ? where gco = ?";
		return jdbcTemplate.update(sql, System.currentTimeMillis(), gco) == 1 ? Message.SUCCESS : Message.FAILURE;
	}

	@Override
	public Integer insertPushSet(PushSetting pushSet) {
		String sql = "INSERT INTO`pushsetting` (`userName`, `pushType`, `forwardDays`, `gco`) VALUES (?, ?, ?, ?)";
		return jdbcTemplate.update(sql, pushSet.getUserName(), pushSet.getPushType(), pushSet.getForwardDays(), pushSet.getGco());
	}

	@Override
	public Integer updatePushSet(PushSetting pushSet) {
		String sql = "update pushsetting set forwardDays = ?, stat = ?, pushType = ? where gco = ?";
		return jdbcTemplate.update(sql, pushSet.getForwardDays(), pushSet.getStat(), pushSet.getPushType(), pushSet.getGco());
	}

	@Override
	public PushSetting findPushByUsername(String userName) {
		String sql = "select * from pushsetting where userName = ?";
		List<PushSetting> pushList = jdbcTemplate.query(sql, (rs, num) -> {
			PushSetting setting = new PushSetting();
			setting.setGco(rs.getString("gco"));
			setting.setStat(rs.getInt("stat"));
			setting.setUserName(rs.getString("userName"));
			setting.setPushType(rs.getInt("pushType"));
			setting.setForwardDays(rs.getInt("forwardDays"));
			setting.setPushDate(rs.getLong("pushDate"));
			return setting;
		}, userName);
		if (pushList != null  && pushList.size() > 0)
			return pushList.get(0);
		else
			return null;
	}

}
