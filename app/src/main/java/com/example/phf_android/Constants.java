package com.example.phf_android;

public class Constants {
    public static final String MOSTRAR_ANIMALS_ADAPTER = "SELECT `animal`.`imatgePerfil`, `animal`.`nom`, `animal`.`cartilla`, `tipusanimal`.`Nom` FROM `animal` LEFT JOIN `tipusanimal` ON `animal`.`idTipusAnimal` = `tipusanimal`.`idTipusAnimal`;";
}
