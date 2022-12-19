package team.asd.entity;

import team.asd.constants.ChannelPartnerState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChannelPartner {
	private Integer id;
	private Integer partyId;
	private String abbreviation;
	private String channelName;
	private ChannelPartnerState state;
	private Double commission;
	private Double bpCommission;
	private Boolean isFundsHolder;
	private Date version;
}
