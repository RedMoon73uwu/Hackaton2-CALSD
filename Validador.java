package com.example.agenda.util;

public class Validador {

        private Validador() { }
        public static boolean esValido(String texto) {
            return texto != null && !texto.isBlank();
        }
}
