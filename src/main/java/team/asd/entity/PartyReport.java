package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyReport {
	private Integer id;
	private String name;
	private String state;
	private String postalAddress;
	private String emailAddress;
	private String mobilePhone;
	private String password;
	private String currency;
	private String userType;
	private PartyChannel channelPartner;
	private PartyManager propertyManager;
	private List<ManagerToChannel> managerToChannel;
}
