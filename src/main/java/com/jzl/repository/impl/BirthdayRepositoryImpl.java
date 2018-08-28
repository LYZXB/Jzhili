package com.jzl.repository.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.jzl.beans.birthday.Birthday;
import com.jzl.beans.common.PageBean;
import com.jzl.repository.BirthdayRepository;

/**
 * 
 * @ClassName: BirthdayRepository
 * @Description: 生日管理数据操作
 * @author Sombra
 * @date 2017年11月21日 上午11:55:08
 * @version V1.0
 */
@Repository
public class BirthdayRepositoryImpl extends AbstractRepositoryImpl implements BirthdayRepository {
	Logger LOGGER = Logger.getLogger(BirthdayRepositoryImpl.class);

	public List<Birthday> queryAll(String dataSigns, PageBean pagebean) {
		String dataSigns4In = dataSigns.replace(",", "','");
		String sql = "select * from birthday where dataSigns in ('" + dataSigns4In + "') ";
		if (StringUtils.isNotBlank(pagebean.getSort())) {
			sql += " order by " + pagebean.getSort() + " " + pagebean.getOrder();
		}
		sql += "	limit " + pagebean.getPage() + "," + pagebean.getLimit();
		LOGGER.info("查询生日数据:" + sql);
		return jdbcTemplate.query(sql, (rs, num) -> {

			Birthday birthday = new Birthday();
			birthday.setId(rs.getInt("id"));
			birthday.setGco(rs.getString("gco"));
			birthday.setName(rs.getString("name"));
			birthday.setAge(rs.getInt("age"));
			birthday.setBirthday(rs.getString("birthday"));
			birthday.setLunar_birthday(rs.getString("lunar_birthday"));
			birthday.setNext_lunar(rs.getString("next_lunar"));
			birthday.setLunar_days(rs.getInt("lunar_days"));
			birthday.setSolar_days(rs.getInt("solar_days"));
			birthday.setNext_solar(rs.getString("next_solar"));
			birthday.setType(rs.getInt("type"));
			birthday.setUpdateTime(rs.getLong("updateTime"));
			return birthday;
		});
	}
	
	@Override
	public Long queryAllCount(String dataSigns) {
		String dataSigns4In = dataSigns.replace(",", "','");
		String sql = "select count(*) from birthday where dataSigns in ('" + dataSigns4In + "') ";
		return jdbcTemplate.queryForObject(sql, Long.class);
	}

	@Override
	public Integer delBirthday(String gco) {
		String sql = "delete from birthday where gco = ?";
		return jdbcTemplate.update(sql, gco);
	}

	@Override
	public Integer insertBirthday(Birthday birthday) {
		String sql = "insert into birthday (gco, name, age, birthday, lunar_birthday, "
				+ "	next_lunar, next_solar, lunar_days, solar_days, type, dataSigns) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(sql, birthday.getGco(), birthday.getName(), birthday.getAge(),
				birthday.getBirthday(), birthday.getLunar_birthday(), birthday.getNext_lunar(),
				birthday.getNext_solar(), birthday.getLunar_days(), birthday.getSolar_days(), birthday.getType(),
				birthday.getDataSigns());
	}

	@Override
	public Integer updateBirthday(Birthday birthday) {
		String sql = "update birthday set name = ?, age = ?, birthday = ?, lunar_birthday = ?, "
				+ " next_lunar = ?, next_solar = ?, lunar_days = ?, solar_days = ?, type = ? where gco = ?";
		return jdbcTemplate.update(sql, birthday.getName(), birthday.getAge(), birthday.getBirthday(),
				birthday.getLunar_birthday(), birthday.getNext_lunar(), birthday.getNext_solar(),
				birthday.getLunar_days(), birthday.getSolar_days(), birthday.getType(), birthday.getGco());
	}

	@Override
	public void updateBirthday(List<Birthday> birthdays) {
		String sql = "update birthday set name = ?, age = ?, birthday = ?, lunar_birthday = ?, "
				+ " next_lunar = ?, next_solar = ?, lunar_days = ?, solar_days = ?, type = ?, updateTime = ? where gco = ?";
		jdbcTemplate.batchUpdate(sql, birthdays, birthdays.size(), (ps, birthday) -> {
			int i = 1;
			ps.setString(i++, birthday.getName());
			ps.setInt(i++, birthday.getAge());
			ps.setString(i++, birthday.getBirthday());
			ps.setString(i++, birthday.getLunar_birthday());
			ps.setString(i++, birthday.getNext_lunar());
			ps.setString(i++, birthday.getNext_solar());
			ps.setInt(i++, birthday.getLunar_days());
			ps.setInt(i++, birthday.getSolar_days());
			ps.setInt(i++, birthday.getType());
			ps.setLong(i++, birthday.getUpdateTime());
			ps.setString(i++, birthday.getGco());
		});
	}

	@Override
	public List<Birthday> findAllNeedUpdateBirthday() {
		String sql = "select * from birthday where updateTime < UNIX_TIMESTAMP(CAST(SYSDATE()AS DATE))*1000";
		return jdbcTemplate.query(sql, (rs, num) -> {

			Birthday birthday = new Birthday();
			birthday.setId(rs.getInt("id"));
			birthday.setGco(rs.getString("gco"));
			birthday.setName(rs.getString("name"));
			birthday.setAge(rs.getInt("age"));
			birthday.setBirthday(rs.getString("birthday"));
			birthday.setLunar_birthday(rs.getString("lunar_birthday"));
			birthday.setNext_lunar(rs.getString("next_lunar"));
			birthday.setLunar_days(rs.getInt("lunar_days"));
			birthday.setSolar_days(rs.getInt("solar_days"));
			birthday.setNext_solar(rs.getString("next_solar"));
			birthday.setType(rs.getInt("type"));
			birthday.setUpdateTime(rs.getLong("updateTime"));
			return birthday;
		});
	}

	@Override
	public List<Birthday> findAllNeedPushByLunarDays(Integer days) {
		String sql = "select * from birthday where needPush = 1 and lunar_days = " + days;
		return jdbcTemplate.query(sql, (rs, num) -> {

			Birthday birthday = new Birthday();
			birthday.setId(rs.getInt("id"));
			birthday.setGco(rs.getString("gco"));
			birthday.setName(rs.getString("name"));
			birthday.setAge(rs.getInt("age"));
			birthday.setBirthday(rs.getString("birthday"));
			birthday.setLunar_birthday(rs.getString("lunar_birthday"));
			birthday.setNext_lunar(rs.getString("next_lunar"));
			birthday.setLunar_days(rs.getInt("lunar_days"));
			birthday.setSolar_days(rs.getInt("solar_days"));
			birthday.setNext_solar(rs.getString("next_solar"));
			birthday.setType(rs.getInt("type"));
			birthday.setUpdateTime(rs.getLong("updateTime"));
			return birthday;
		});
	}

}
