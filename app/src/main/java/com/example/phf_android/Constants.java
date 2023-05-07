package com.example.phf_android;

public class Constants {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String MOSTRAR_ANIMALS_ADAPTER = "SELECT `animal`.`imatgePerfil`, `animal`.`nom`, `animal`.`cartilla`, `tipusanimal`.`Nom` FROM `animal` LEFT JOIN `tipusanimal` ON `animal`.`idTipusAnimal` = `tipusanimal`.`idTipusAnimal`;";
    public static final String AFEGIR_USUARI_REGISTRE = "INSERT INTO `usuaris` (`documentIdentitat`, `nom`, `cognom1`, `cognom2`, `nomUsuari`, `contrasenya`, `correu`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s');";
    public static final String CERCAR_GUARDERIES_AMB_VACANCES = "SELECT g.idGuarderia FROM guarderia g JOIN" +
            " JOIN vacances v ON v.idGuarderia = g.idGuarderia " +
            " WHERE v.dataInici <= '{0}' AND v.dataFinal >= '{1}'" +
            " ";

    public static final String CERCAR_GUARDERIES = "SELECT * FROM guarderia";
    public static final String CERCAR_MILLOR_GUARDERIES = "SELECT g.idGuarderia as idGuarderia, g.nom as gnom, c.nom as cnom, g.descripcio as descripcio, g.avrating as rating FROM guarderia g" +
            " JOIN codipostal cp ON cp.idCP = g.idCP" +
            " JOIN ciutat c ON c.idCiutat = cp.idCiutat" +
            " WHERE g.actiu = 1" +
            " ORDER BY avRating DESC LIMIT 10";

    public static final String CERCAR_SERVEIS = "SELECT * FROM serveis";
    public static final String CERCAR_GUARDERIES_ENABLED = "SELECT g.idGuarderia as idGuarderia, g.nom as gnom, c.nom as cnom, g.descripcio as descripcio, g.avrating as rating FROM guarderia g" +
            " JOIN codipostal cp ON cp.idCP = g.idCP" +
            " JOIN ciutat c ON c.idCiutat = cp.idCiutat" +
            " WHERE g.nom LIKE '%%%s%%' AND g.actiu = 1 AND NOT g.idGuarderia = ANY (" +
            "SELECT v.idGuarderia FROM vacances v" +
            " WHERE v.dataFi >= '%s' AND v.dataInici <= '%s'" +
            ")";
    public static final String CERCAR_SERVEIS_BY_IDGUARDERIA = "SELECT * FROM serveis s" +
            " JOIN serveisguarderia sg ON sg.idServei = s.idServei" +
            " WHERE sg.idGuarderia = %d";

    public static final String SORTED_GUARDERIES = "SELECT g.idGuarderia as idGuarderia, g.nom as gnom, c.nom as cnom, g.descripcio as descripcio, g.avrating as rating FROM guarderia g" +
            " JOIN codipostal cp ON cp.idCP = g.idCP" +
            " JOIN ciutat c ON c.idCiutat = cp.idCiutat" +
            " WHERE g.actiu = 1" +
            " ORDER BY avRating DESC";

    public static final String CERCAR_RATINGS_BY_IDGUARDERIA = "SELECT u.nom, r.rating FROM guarderia g" +
            " JOIN valoracions r ON g.idGuarderia = r.idGuarderia" +
            " JOIN usuaris u ON r.idUsuari = u.idUsuari" +
            " WHERE g.idGuarderia = %d";
}
