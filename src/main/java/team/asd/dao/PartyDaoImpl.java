package team.asd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import team.asd.entity.Party;
import team.asd.mapper.PartyMapper;

import java.util.List;

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
    public List<Party> readByUserTypeNameAndState(Party party) {
        return partyMapper.readByUserTypeNameAndState(party);
    }

    @Override
    public List<Party> readByEmailUserTypeNameAndState(Party party) {
        return partyMapper.readByEmailUserTypeNameAndState(party);
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
    public void deleteById(Integer id) {
        partyMapper.deleteById(id);
    }
}
