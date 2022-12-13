package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
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
