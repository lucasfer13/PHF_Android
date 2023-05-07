package com.example.phf_android;

public class Constants {
    public static final String MOSTRAR_ANIMALS_ADAPTER = "SELECT `animal`.`imatgePerfil`, `animal`.`nom`, `animal`.`cartilla`, `tipusanimal`.`Nom` FROM `animal` LEFT JOIN `tipusanimal` ON `animal`.`idTipusAnimal` = `tipusanimal`.`idTipusAnimal`;";
    public static final String AFEGIR_USUARI_REGISTRE = "INSERT INTO `usuaris` (`DocumentIdentitat`, `nom`, `cognom1`, `cognom2`, `nomUsuari`, `contrasenya`, `telefon`, `correu`, `actiu` , `tipusUsuari`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', 1, 3);";
    public static final String FER_LOGIN = "SELECT * FROM `usuaris` WHERE nomUsuari='%s';";
}
