package com.api.bootsbootique.entities;

import jakarta.persistence.*;

import com.api.bootsbootique.enums.BootType;

@Entity
@Table(name = "BOOTS")
public class Boot {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "ID")
        private Integer id;
        @Enumerated(EnumType.STRING)
        @Column(name = "TYPE")
        private BootType type;
        @Column(name = "SIZE")
        private Float size;
        @Column(name = "QUANTITY")
        private Integer quantity;
        @Column(name = "MATERIAL")
        private String material;

        // Constructors
        public Boot() {
        }

        public Boot(BootType type, Float size, Integer quantity, String material) {
            this.type = type;
            this.size = size;
            this.quantity = quantity;
            this.material = material;
        }

        // Getters and Setters
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public BootType getType() {
            return type;
        }

        public void setType(BootType type) {
            this.type = type;
        }

        public Float getSize() {
            return size;
        }

        public void setSize(Float size) {
            this.size = size;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

}
