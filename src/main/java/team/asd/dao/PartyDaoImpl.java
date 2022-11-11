package team.asd.dao;

import org.springframework.stereotype.Repository;
import team.asd.entity.Party;

@Repository
public class PartyDaoImpl implements PartyDao {
    @Override
    public Party readById(Integer id) {
        return Party.builder()
                .id(10)
                .name("test")
                .build();
    }

    @Override
    public void create(Party party) {

    }

    @Override
    public void update(Party party) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
