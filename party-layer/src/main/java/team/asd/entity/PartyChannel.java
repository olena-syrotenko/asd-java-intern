package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyChannel {
	private String abbreviation;
	private String channelName;
	private Double commission;
	private Integer isFundsHolder;
}
