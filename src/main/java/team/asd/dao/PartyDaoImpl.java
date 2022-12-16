package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.entity.Party;
import team.asd.entity.PartyReport;
import team.asd.mapper.PartyMapper;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class PartyDaoImpl implements PartyDao {
    private final PartyMapper partyMapper;

    @Autowired
    public PartyDaoImpl(PartyMapper partyMapper) {
        this.partyMapper = partyMapper;
    }

    @Override
    public Party readById(Integer id) {
        return partyMapper.readById(id);
    }

    @Override
    public List<Party> readByUserTypeNameState(String userType, String name, String state) {
        return partyMapper.readByUserTypeNameState(userType, name, state);
    }

    @Override
    public List<Party> readByEmailUserTypeNameState(String emailAddress, String userType, String name, String state) {
        return partyMapper.readByEmailUserTypeNameState(emailAddress, userType, name, state);
    }

    @Override
    public List<Party> readByChannelMaskUserType(String mask, String userType) {
        return partyMapper.readByChannelMaskUserType(mask, userType);
    }

    @Override
    public PartyReport readReportById(Integer id) {
        return partyMapper.readReportById(id);
    }

    @Override
    public List<PartyReport> readReportByPageItems(Integer page, Integer itemsPerPage) {
        return partyMapper.readReportByPageItems(page, itemsPerPage);
    }

    @Override
    public void create(Party party) {
        partyMapper.create(party);
    }

    @Override
    public void update(Party party) {
        partyMapper.update(party);
    }

    @Override
    public void updateWithDelay(Party party) {
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            Thread.currentThread()
                    .interrupt();
        }
        partyMapper.update(party);
    }

    @Override
    public void deleteById(Integer id) {
        partyMapper.deleteById(id);
    }
}
