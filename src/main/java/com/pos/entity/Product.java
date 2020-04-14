package com.pos.entity;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;



/**
 * @author yathi
 *
 */
@SuppressWarnings("deprecation")
@Entity
@Table(name="Product")
@Data
public class Product {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String displayName;
    private String vendor;
    private String catagory;
    private String brand;
    private String description;
    @Min(value = 0)
    private BigDecimal weight;
    //@Column(unique = true)
    @NotNull(message = "Barcode cannot be null")
    @NotEmpty(message = "Barcode cannot be empty")
    private String barcode;
	

}
