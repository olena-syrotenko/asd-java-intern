package team.asd.mapper;

import org.apache.ibatis.annotations.Mapper;
import team.asd.entity.ManagerToChannel;

import java.util.List;

@Mapper
public interface ManagerToChannelMapper {
	ManagerToChannel readById(Integer id);

	ManagerToChannel readByPropManagerIdChanPartnerId(Integer propertyManagerId, Integer channelPartnerId);

	List<ManagerToChannel> readByChannelPartnerIdNetRate(Integer channelPartnerId, Integer netRate);

	List<ManagerToChannel> readByFundsHolder(Integer fundsHolder);

	void create(ManagerToChannel managerToChannel);

	void createList(List<ManagerToChannel> managerToChannels);

	void update(ManagerToChannel managerToChannel);

	void deleteById(Integer id);
}
