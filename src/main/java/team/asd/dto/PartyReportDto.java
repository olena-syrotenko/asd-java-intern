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
	@JsonProperty("id")
	@ApiModelProperty(notes = "Party id", example = "1")
	private Integer id;

	@JsonProperty("name")
	@ApiModelProperty(notes = "Name of party", example = "test party")
	private String name;

	@JsonProperty("state")
	@ApiModelProperty(notes = "Party state", example = "Created")
	private String state;

	@JsonProperty("postal_address")
	@ApiModelProperty(notes = "Postal address of party", example = "Mr John Smith. 132, My Street, Bigtown BG23 4YZ")
	private String postalAddress;

	@JsonProperty("email_address")
	@ApiModelProperty(notes = "Email address of party", example = "john.smith@example.com")
	private String emailAddress;

	@JsonProperty("mobile_phone")
	@ApiModelProperty(notes = "Mobile phone number of party", example = "(555) 555-1234")
	private String mobilePhone;

	@JsonProperty("password")
	@ApiModelProperty(notes = "Hashed password", example = "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824")
	private String password;

	@JsonProperty("currency")
	@ApiModelProperty(notes = "Default currency for usage", example = "USD")
	private String currency;

	@JsonProperty("user_type")
	@ApiModelProperty(notes = "User type", example = "Customer")
	private String userType;

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
