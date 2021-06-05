package com.nimel.mymicroservices.beerservice.entities;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {
	@Id
	@GeneratedValue(generator = "UUID")
	private UUID id;
	@Version
	private Long version;
	@CreationTimestamp
	private Timestamp createdDate;
	@UpdateTimestamp
	private Timestamp lastModifiedDate;
	private String beerName;
	private String beerStyle;
	private String upc;
	private int price;
	private int minOnHand;
	private int quantityToBrew;

}
