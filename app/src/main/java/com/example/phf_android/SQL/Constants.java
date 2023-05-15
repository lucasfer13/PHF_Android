package com.example.phf_android.SQL;

public class Constants {
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String MOSTRAR_ANIMALS_ADAPTER = "SELECT `animal`.`idAnimal`, `animal`.`imatgePerfil`, `animal`.`nom`, `animal`.`cartilla`, `tipusanimal`.`Nom`, `animal`.`pes`, `animal`.`edat` FROM `animal` " +
            " LEFT JOIN `tipusanimal` ON `animal`.`idTipusAnimal` = `tipusanimal`.`idTipusAnimal`" +
            " JOIN usuaris u ON u.idUsuari = animal.idUsuari" +
            " WHERE u.idUsuari = %d";

    public static final String CERCAR_TIPUS_HABITACIONS_GUARDERIA = "SELECT th.idTipusHabitacio, th.nom, th.preu FROM tipushabitacio th" +
            " JOIN guarderia g ON th.idGuarderia = g.idGuarderia" +
            " WHERE g.idGuarderia = %d";

    public static final String CERCAR_TIPUS_ANIMALS_TIPUS_HABITACIONS = "SELECT ta.idTipusAnimal, ta.nom FROM tipusanimal ta" +
            " JOIN animal_tipushabitacio ath ON ath.idTipusAnimal = ta.idTipusAnimal" +
            " JOIN tipushabitacio th ON th.idTipusHabitacio = ath.idTipusHabitacio" +
            " WHERE th.idTipusHabitacio = %d";

    public static final String CERCAR_TIPUS_ANIMALS_MASCOTA = "SELECT * FROM tipusanimal ta" +
            " JOIN animal a ON a.idTipusAnimal = ta.idTipusAnimal" +
            " WHERE a.idAnimal = %d";

    public static final String INSERT_MASCOTA = "INSERT INTO animal (idTipusAnimal, idUsuari, nom, cartilla, pes, edat) VALUES (%d, %d,'%s', '%s', %e, %d)";
    public static final String CERCAR_TIPUS_ANIMALS = "SELECT * FROM tipusanimal";
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
            " WHERE (v.dataFi < '%s' AND v.dataFi < '%s') OR (v.dataInici > '%s' AND v.dataInici > '%s')" +
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

    public static final String HABITACIONS_OCUPADES = "SELECT COUNT(*) FROM habitacions h" +
            " JOIN detallreserva dr ON dr.idHabitacio = h.idHabitacio" +
            " JOIN reserves r ON r.idReserva = dr.idReserva" +
            " JOIN guarderia g ON r.idGuarderia = g.idGuarderia" +
            " JOIN tipushabitacio th ON th.idGuarderia = g.idGuarderia" +
            " WHERE ((dr.dataInici >= '%s' AND dr.dataFi <= '%s') OR (dr.dataInici >= '%s' AND dr.dataFi <= '%s')) AND th.idTipusHabitacio = %d";

    public static final String HABITACIONS_TOTALS = "SELECT COUNT(*) FROM habitacions h" +
            " JOIN guarderia g ON h.idGuarderia = g.idGuarderia" +
            " JOIN tipushabitacio th ON th.idGuarderia = g.idGuarderia" +
            " WHERE th.idTipusHabitacio = %d";

    public static final String VACANCES_GUARDERIA = "SELECT COUNT(*) FROM guarderia g" +
            " JOIN vacances v ON v.idGuarderia = g.idGuarderia" +
            " WHERE ((v.dataInici <= '%s' AND v.dataFi >= '%s') OR (v.dataInici <= '%s' AND v.dataFi >= '%s')) AND g.idGuarderia = %d";

    public static final String AFEGIR_USUARI_REGISTRE = "INSERT INTO `usuaris` (`DocumentIdentitat`, `nom`, `cognom1`, `cognom2`, `nomUsuari`, `contrasenya`, `telefon`, `correu`, `actiu` , `tipusUsuari`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', 1, 3);";
    public static final String FER_LOGIN = "SELECT * FROM `usuaris` WHERE nomUsuari='%s';";

    public static final String INSERT_RESERVA = "INSERT INTO reserves (idUsuari, idGuarderia, preuTotal, acceptada) VALUES (%d, %d, %f, 1)";

    public static final String INSERT_DETALL_RESERVA = "INSERT  INTO detallreserva (idReserva, idAnimal, idHabitacio, preu, dataInici, dataFi) VALUES (%d, %d, %d, %f, '%s', '%s')";

    public static final String GET_HABITACIO_DISPONIBLE = "SELECT h.idHabitacio FROM habitacions h WHERE h.idTipusHabitacio = %d AND h.idHabitacio NOT IN (SELECT h.idHabitacio FROM habitacions h" +
            " JOIN detallreserva dr ON dr.idHabitacio = h.idHabitacio" +
            " JOIN reserves r ON r.idReserva = dr.idReserva" +
            " JOIN guarderia g ON r.idGuarderia = g.idGuarderia" +
            " JOIN tipushabitacio th ON th.idGuarderia = g.idGuarderia" +
            " WHERE ((dr.dataInici >= '%s' AND dr.dataFi <= '%s') OR (dr.dataInici >= '%s' AND dr.dataFi <= '%s')) AND th.idTipusHabitacio = %d) LIMIT 1";
    public static final String MODIFICAR_USUARI = "UPDATE usuaris SET DocumentIdentitat = '%s', nom = '%s', cognom1 = '%s', cognom2 = '%s', nomUsuari = '%s', correu = '%s' WHERE IdUsuari = %d;";
    public static final String MODIFICAR_CONTRASENYA = "UPDATE usuaris SET `contrasenya` = '%s' WHERE IdUsuari = %d;";

    public static final String GET_RESERVES_USUARI = "SELECT g.nom, r.preuTotal, dr.dataInici, dr.dataFi FROM reserves r" +
            " JOIN detallreserva dr ON dr.idReserva = r.idReserva" +
            " JOIN guarderia g ON g.idGuarderia = r.idGuarderia" +
            " WHERE r.idUsuari = %d";

    public static final String GET_RESERVES_ANIMAL = "SELECT g.nom, r.preuTotal, dr.dataInici, dr.dataFi FROM reserves r" +
            " JOIN detallreserva dr ON dr.idReserva = r.idReserva" +
            " JOIN guarderia g ON g.idGuarderia = r.idGuarderia" +
            " WHERE dr.idAnimal = %d";
}
