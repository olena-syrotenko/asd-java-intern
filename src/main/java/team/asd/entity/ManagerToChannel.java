package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ManagerToChannel {
	private Integer id;
	private Integer propertyManagerId;
	private Integer channelPartnerId;
	private Double channelPartnerCommission;
	private Boolean isFundsHolder;
	private Boolean isNetRate;
	private Date version;
}
