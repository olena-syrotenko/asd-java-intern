package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartyReportDto {
	@JsonProperty("party")
	@ApiModelProperty(notes = "General party information")
	private PartyDto party;

	@JsonProperty("channel_partner")
	@ApiModelProperty(notes = "Additional information for party with ChannelPartner type")
	private PartyChannelDto channelPartner;

	@JsonProperty("property_manager")
	@ApiModelProperty(notes = "Additional information for party with PropertyManager type")
	private PartyManagerDto propertyManager;

	@JsonProperty("manager_to_channel")
	@ApiModelProperty(notes = "Additional information about managers to channels for party with ChannelPartner or PropertyManager type")
	private List<ManagerToChannelDto> managerToChannel;
}
