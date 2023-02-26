package com.sena.javasql;

public class Product {
        private int codProd;
        private String nombPro;
        private int stock;
        private double valorUnitario;

        public Product(int codProd, String nombPro, int stock, double valorUnitario) {
            this.codProd = codProd;
            this.nombPro = nombPro;
            this.stock = stock;
            this.valorUnitario = valorUnitario;
        }

        public int getCodProd() {
            return codProd;
        }

        public void setCodProd(int codProd) {
            this.codProd = codProd;
        }

        public String getNombPro() {
            return nombPro;
        }

        public void setNombPro(String nombPro) {
            this.nombPro = nombPro;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public double getValorUnitario() {
            return valorUnitario;
        }

        public void setValorUnitario(double valorUnitario) {
            this.valorUnitario = valorUnitario;
        }
    }


