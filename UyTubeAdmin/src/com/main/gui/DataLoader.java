package com.main.gui;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.main.logic.controller.ControllerFactory;
import com.main.logic.dts.VideoDT;
import com.main.logic.interfaces.ICategoryController;
import com.main.logic.interfaces.IPlaylistController;
import com.main.logic.interfaces.IUserController;
import com.main.logic.interfaces.IVideoController;
import com.main.logic.utils.RatingEnum;
import java.io.InputStream;

public class DataLoader implements IDataLoader {

    private static DataLoader instance = null;
    private static boolean loaded = false;

    public static DataLoader getInstance() {
        if (instance == null) {
            instance = new DataLoader();
        }

        return instance;
    }

    @Override
    public void cargarDatosDePrueba() throws Exception {
        ICategoryController categoryController = ControllerFactory.getInstance().getCategoryController();
        IVideoController videoController = ControllerFactory.getInstance().getVideoController();
        IUserController userController = ControllerFactory.getInstance().getUserController();
        IPlaylistController playlistController = ControllerFactory.getInstance().getPlaylistController();

        // Categorias
        categoryController.createCategory("Música");
        categoryController.createCategory("Deporte");
        categoryController.createCategory("Carnaval");
        categoryController.createCategory("Noticias");
        categoryController.createCategory("Entretenimiento");
        categoryController.createCategory("Comida");
        categoryController.createCategory("Videojuegos");
        categoryController.createCategory("Ciencia y Tecnología");
        categoryController.createCategory("ONG y activismo");
        categoryController.createCategory("Gente y blogs");
        categoryController.createCategory("Mascotas y animales");
        categoryController.createCategory("Viajes y eventos");

        // Avatars

        InputStream defAvatarStream = getClass().getClassLoader().getResourceAsStream("emptyAvatar.png");
        byte[] defAvatarBytes = new byte[defAvatarStream.available()];
        defAvatarStream.read(defAvatarBytes);
        
        InputStream hrAvatarStream = getClass().getClassLoader().getResourceAsStream("hr-avatar.jpg");
        byte[] hrAvatarBytes = new byte[hrAvatarStream.available()];
        hrAvatarStream.read(hrAvatarBytes);
        
        InputStream mbAvatarStream1 = getClass().getClassLoader().getResourceAsStream("mb-avatar.jpg");
        byte[] mbAvatarBytes = new byte[mbAvatarStream1.available()];
        mbAvatarStream1.read(mbAvatarBytes);
        
        InputStream tcAvatarStream = getClass().getClassLoader().getResourceAsStream("tc-avatar.jpg");
        byte[] tcAvatarBytes = new byte[tcAvatarStream.available()];
        tcAvatarStream.read(tcAvatarBytes);
        
        InputStream csAvatarStream = getClass().getClassLoader().getResourceAsStream("cs-avatar.jpg");
        byte[] csAvatarBytes = new byte[csAvatarStream.available()];
        csAvatarStream.read(csAvatarBytes);
        
        InputStream khAvatarStream = getClass().getClassLoader().getResourceAsStream("kh-avatar.jpg");
        byte[] khAvatarBytes = new byte[khAvatarStream.available()];
        khAvatarStream.read(khAvatarBytes);
        
        InputStream arAvatarStream = getClass().getClassLoader().getResourceAsStream("ar-avatar.jpg");
        byte[] arAvatarBytes = new byte[arAvatarStream.available()];
        arAvatarStream.read(arAvatarBytes);
        
        InputStream apAvatarStream = getClass().getClassLoader().getResourceAsStream("ap-avatar.jpg");
        byte[] apAvatarBytes = new byte[apAvatarStream.available()];
        apAvatarStream.read(apAvatarBytes);

        // Users and channels
        LocalDate datehr = LocalDate.of(1962, 2, 25);
        userController.createUser("hrubino", "Horacio", "Rubino", "horacio.rubino@guambia.com.uy", datehr,
                hrAvatarBytes, "El canal Horacio es para publicar contenido divertido", "Canal Horacio", false);
        userController.setPassword("hrubino", "Rufus123");

        LocalDate datemb = LocalDate.of(1972, 6, 14);
        userController.createUser("mbusca", "Martín", "Buscaglia", "Martin.bus@agadu.org.uy", datemb,
                mbAvatarBytes, "Mi canal para colgar cosas", "El Bocha", false);
        userController.setPassword("mbusca", "Cookie234");

        LocalDate datehg = LocalDate.of(1954, 1, 7);
        userController.createUser("hectorg", "Héctor", "Guido", "hector.gui@elgalpon.org.uy", datehg,
                defAvatarBytes, "Canal HG", "Héctor", false);
        userController.setPassword("hectorg", "Poncho345");

        LocalDate datetc = LocalDate.of(1971, 7, 24);
        userController.createUser("tabarec", "Tabaré", "Cardozo", "tabare.car@agadu.org.uy", datetc,
                tcAvatarBytes, "Mi música e ainda mais", "Tabaré", false);
        userController.setPassword("tabarec", "Ketchup1");

        LocalDate datecs = LocalDate.of(1947, 1, 1);
        userController.createUser("cachilas", "Waldemar \"Cachila\"", "Silva", "Cachila.sil@c1080.org.uy", datecs,
                csAvatarBytes, "Para juntar cosas", "El Cachila", true);
        userController.setPassword("cachilas", "Sancho456");

        LocalDate datejb = LocalDate.of(1967, 3, 16);
        userController.createUser("juliob", "Julio", "Bocca", "juliobocca@sodre.com.uy", datejb,
                defAvatarBytes, "Canal de JB", "Julio", false);
        userController.setPassword("juliob", "Salome56");

        LocalDate datedp = LocalDate.of(1975, 1, 1);
        userController.createUser("diegop", "Diego", "Parodi", "diego@efectocine.com", datedp,
                defAvatarBytes, "Canal de DP", "Diego", false);
        userController.setPassword("diegop", "Ruffo678");

        LocalDate datekh = LocalDate.of(1840, 4, 25);
        userController.createUser("kairoh", "Kairo", "Herrera", "kairoher@pilsenrock.com.uy", datekh,
                khAvatarBytes, "Videos de grandes canciones de hoy y siempre ", "Kairo música", false);
        userController.setPassword("kairoh", "Corbata15");

        LocalDate daterh = LocalDate.of(1940, 8, 3);
        userController.createUser("robinh", "Robin", "Henderson", "Robin.h@tinglesa.com.uy", daterh,
                defAvatarBytes, "Henderson", "Robin", false);
        userController.setPassword("robinh", "Aquiles67");

        LocalDate datemt = LocalDate.of(1960, 4, 1);
        userController.createUser("marcelot", "Marcelo", "Tinelli", "marcelot@ideasdelsur.com.ar", datemt,
                defAvatarBytes, "Todo lo que querías y más !", "Tinelli total", false);
        userController.setPassword("marcelot", "Mancha890");

        LocalDate dateen = LocalDate.of(1952, 7, 17);
        userController.createUser("novick", "Edgardo", "Novik", "edgardo@novick.com.uy", dateen,
                defAvatarBytes, "Preparando las elecciones", "Con la gente", false);
        userController.setPassword("novick", "Xenon987");

        LocalDate datesp = LocalDate.of(1950, 1, 28);
        userController.createUser("sergiop", "Sergio", "Puglia", "puglia@alpanpan.com.uy", datesp,
                defAvatarBytes, "Programas del ciclo y videos de cocina masterchef", "Puglia invita", false);
        userController.setPassword("sergiop", "Sultan876");

        LocalDate datear = LocalDate.of(1976, 3, 17);
        userController.createUser("chino", "Alvaro", "Recoba", "chino@trico.org.uy", datear,
                arAvatarBytes, "Canal de goles con Naciona", "Chino Recoba", true);
        userController.setPassword("chino", "Laika765");

        LocalDate dateap = LocalDate.of(1955, 2, 14);
        userController.createUser("tonyp", "Antonio", "Pacheco", "eltony@manya.org.uy", dateap,
                apAvatarBytes, "Todos los goles con Peñarol", "Tony Pacheco", true);
        userController.setPassword("tonyp", "Kitty543");

        LocalDate datenj = LocalDate.of(1960, 8, 9);
        userController.createUser("nicoJ", "Nicolás", "Jodal", "jodal@artech.com.uy", datenj,
                defAvatarBytes, "Canal información C y T", "Desde Genexus", false);
        userController.setPassword("nicoJ", "Albino80");

        // Default playlists

        playlistController.createDefaultPlaylist("Escuchar más tarde");
        playlistController.createDefaultPlaylist("Deporte total");
        playlistController.createDefaultPlaylist("Novedades generales");

        // Particular playlists
        playlistController.createParticularPlaylist("Nostalgia", "kairoh", false);
        playlistController.createParticularPlaylist("De fiesta", "tabarec", true);
        playlistController.createParticularPlaylist("Novedades FING", "hectorg", false);
        playlistController.createParticularPlaylist("De todo un poco", "cachilas", true);
        playlistController.createParticularPlaylist("Noticias y CYT", "nicoJ", false);
        playlistController.createParticularPlaylist("Solo deportes", "juliob", false);


        // Videos
        
        // V1 id 0
        String desc1 = "Tema Oficial de la cobertura celeste de Monte Carlo Televisión Canal 4 para el Mundial de Futbol FIFA Rusia 2018."; 
        VideoDT vid = videoController.createVideo("tabarec", "Locura celeste", 184, "https://youtu.be/PAfbzKcePx0",
                desc1, LocalDate.of(2018, 6, 5), "Música");
        vid.setPrivate(true);
        videoController.modifyVideo(vid, vid.getVideoId());
        // V1 id 1
        vid = videoController.createVideo("cachilas", "Locura celeste", 184, "https://youtu.be/PAfbzKcePx0",
                desc1, LocalDate.of(2018, 6, 5), "Música");
        vid.setPrivate(true);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V2 id 2
        String desc2 = "";
        vid = videoController.createVideo("tabarec", "Niño payaso", 258, "https://youtu.be/K-uEIUnyZPg",
                desc2, LocalDate.of(2016, 10, 20), "Música");
        vid.setPrivate(true);
        videoController.modifyVideo(vid, vid.getVideoId());
        // V2 id 3
        vid = videoController.createVideo("cachilas", "Niño payaso", 258, "https://youtu.be/K-uEIUnyZPg",
                desc2, LocalDate.of(2016, 10, 20), "Música");
        vid.setPrivate(true);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V3 id 4
        String desc3 = "#AppetiteForDestruction: The Debut Album, Remastered and Expanded. Available now as Box Set, Super Deluxe, Double LP, and Double CD here: https://lnk.to/AppetiteForDestruction...";
        vid = videoController.createVideo("juliob", "Sweet child'o mine", 302, "https://youtu.be/1w7OgIMMRc4",
                desc3, LocalDate.of(2009, 12, 24), "Música");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());
        // V3 id 5
        vid = videoController.createVideo("kairoh", "Sweet child'o mine", 302, "https://youtu.be/1w7OgIMMRc4",
                desc3, LocalDate.of(2009, 12, 24), "Música");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V4 id 6
        String desc4 = "Bruce Springsteen's official music video for 'Dancing In The Dark'. Click to listen to Bruce Springsteen on Spotify: http://smarturl.it/BSpringSpot?IQid=B…";
        vid = videoController.createVideo("kairoh", "Dancing in the Dark", 238, "https://youtu.be/129kuDCQtHs",
                desc4, LocalDate.of(2009, 10, 3), "Música");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V5 id 7
        String desc5 = "Listen to more Michael Jackson: https://MichaelJackson.lnk.to/Stream!to\n\nMichael Jackson's 14-minute short film \"Thriller\" revolutionized the music video genre forever.\n";
        vid = videoController.createVideo("juliob", "Thriller", 822, "https://youtu.be/sOnqjkJTMaA",
                desc5, LocalDate.of(2009, 10, 2), "Música");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());
        // V5 id 8
        vid = videoController.createVideo("kairoh", "Thriller", 822, "https://youtu.be/sOnqjkJTMaA",
                desc5, LocalDate.of(2009, 10, 2), "Música");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V6 id 9
        String desc6 = "Del Ciclo más Universidad realizado por la UdelaR, compartimos con ustedes un audiovisual realizado en 2016 por los 100 años de la denominación Facultad de Ingeniería.";
        vid = videoController.createVideo("hectorg", "100 años de FING", 686, "https://youtu.be/peGS4TBxSaI",
                desc6, LocalDate.of(2017, 8, 3), "Noticias");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V7 id 10
        String desc7 = "50 años del Instituto de Computación. Facultad de Ingeniería. UDELAR. 22 de noviembre 2017.\nLa mesa de apertura estuvo integrada por Simon, el rector de la Universidad de la República (Udelar), Roberto Markarian; la ministra de Industria, Energía y Minería, Carolina Cosse\n";
        vid = videoController.createVideo("hectorg", "50 años del InCo", 1642, "https://youtu.be/GzOJSk4urlM",
                desc7, LocalDate.of(2017, 11, 24), "Noticias");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V8 id 11
        String desc8 = "La muestra más grande de la ingeniería nacional se realizó el jueves 19, viernes 20 y sábado 21 de octubre de 2017. Ingeniería deMuestra fue organizada por la Facultad de Ingeniería de la Universidad de la República y su Fundación Julio Ricaldoni.\n";
        vid = videoController.createVideo("hectorg", "Ingeniería de Muestra 2017", 60, "https://youtu.be/RnaYRA1k5j4",
                "", LocalDate.of(2017, 10, 25), "Noticias");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V9 id 12
        vid = videoController.createVideo("cachilas", "Etapa A contramano Liguilla", 3435, "https://youtu.be/Es6GRMHXeCQ",
                "", LocalDate.of(2015, 12, 17), "Carnaval");
        vid.setPrivate(true);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V10 id 13
        vid = videoController.createVideo("cachilas", "Etapa Don Timoteo Liguilla", 3098, "https://youtu.be/I_spHBU9ZsI",
                "", LocalDate.of(2015, 12, 18), "Carnaval");
        vid.setPrivate(true);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V11 id 14
        String desc9 = "TORNEO CLAUSURA 2018\nFECHA 1";
        vid = videoController.createVideo("juliob", "Show de goles", 263, "https://www.youtu.be/g46w4_kD_lA",
                desc9, LocalDate.of(2018, 7, 23), "Deporte");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V12 id 15
        vid = videoController.createVideo("tabarec", "Pacheco goles más recordados", 348, "https://youtu.be/wlEd6-HsIxI",
                "", LocalDate.of(2013, 6, 5), "Deporte");
        vid.setPrivate(true);
        videoController.modifyVideo(vid, vid.getVideoId());
        // V12 id 16
        vid = videoController.createVideo("tonyp", "Pacheco goles más recordados", 348, "https://youtu.be/wlEd6-HsIxI",
                "", LocalDate.of(2013, 6, 5), "Deporte");
        vid.setPrivate(true);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V13 id 17
        String desc10 = "Recordemos la ceremonia de inauguración del Estadio de Peñarol.\nLlamado \"Estadio Campeón del Siglo\".\n";
        vid = videoController.createVideo("juliob", "Inauguración Estadio Peñarol", 12446, "https://youtu.be/U6XPJ8Vz72A",
                desc10, LocalDate.of(2016, 4, 4), "Deporte");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V14 id 18
        String desc11 = "My Favorites\n\n\"El Chino\"\n";
        vid = videoController.createVideo("cachilas", "Recoba 20 mejores goles", 816, "https://youtu.be/Gy3fZhWdLEQ",
                desc11, LocalDate.of(2011, 1, 14), "Deporte");
        vid.setPrivate(true);
        // V14 id 19
        videoController.modifyVideo(vid, vid.getVideoId());
        vid = videoController.createVideo("chino", "Recoba 20 mejores goles", 816, "https://youtu.be/Gy3fZhWdLEQ",
                desc11, LocalDate.of(2011, 1, 14), "Deporte");
        vid.setPrivate(true);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V15 id 20
        String desc12 = "Segunda parte de la entrevista realizada por la periodista Paula Echevarría al director de CUTI\n";
        vid = videoController.createVideo("nicoJ", "Entrevista a director CUTI", 339, "https://youtu.be/Eq5uBEzI6qs",
                desc12, LocalDate.of(2017, 4, 3), "Ciencia y Tecnología");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());
        
        // V16 id 21
        String desc13 = "VEA ESTE CONTENIDO EN EnPerspectiva.net: http://www.enperspectiva.net/en-persp...\n\nEn Uruguay hay un ingeniero por cada tres abogados y cada seis médicos.\n";
        vid = videoController.createVideo("nicoJ", "Ventana al futuro Uruguay y déficit de ingenieros", 1161, "https://youtu.be/zBR2pnASlQE",
                desc13, LocalDate.of(2016, 7, 20), "Ciencia y Tecnología");
        vid.setPrivate(false);
        videoController.modifyVideo(vid, vid.getVideoId());

        // Videos a playlists
        playlistController.addVideoToPlaylist("Nostalgia", "Sweet child'o mine", "kairoh", "kairoh");
        playlistController.addVideoToPlaylist("Nostalgia", "Dancing in the Dark", "kairoh", "kairoh");
        playlistController.addVideoToPlaylist("Nostalgia", "Thriller", "kairoh", "kairoh");

        playlistController.addVideoToPlaylist("De fiesta", "Locura celeste", "tabarec", "tabarec");
        playlistController.addVideoToPlaylist("De fiesta", "Niño payaso", "tabarec", "tabarec");
        playlistController.addVideoToPlaylist("De fiesta", "Show de goles", "juliob", "tabarec");

        playlistController.addVideoToPlaylist("Novedades FING", "100 años de FING", "hectorg", "hectorg");
        playlistController.addVideoToPlaylist("Novedades FING", "50 años del InCo", "hectorg", "hectorg");
        playlistController.addVideoToPlaylist("Novedades FING", "Ingeniería de Muestra 2017", "hectorg", "hectorg");

        playlistController.addVideoToPlaylist("De todo un poco", "Locura celeste", "cachilas", "cachilas");
        playlistController.addVideoToPlaylist("De todo un poco", "Niño payaso", "cachilas", "cachilas");
        playlistController.addVideoToPlaylist("De todo un poco", "Etapa A contramano Liguilla", "cachilas", "cachilas");
        playlistController.addVideoToPlaylist("De todo un poco", "Etapa Don Timoteo Liguilla", "cachilas", "cachilas");
        playlistController.addVideoToPlaylist("De todo un poco", "Inauguración Estadio Peñarol", "juliob", "cachilas");

        playlistController.addVideoToPlaylist("Noticias y CYT", "Ingeniería de Muestra 2017", "hectorg", "nicoJ");
        playlistController.addVideoToPlaylist("Noticias y CYT", "Ventana al futuro Uruguay y déficit de ingenieros", "nicoJ", "nicoJ");

        playlistController.addVideoToPlaylist("Solo deportes", "Show de goles", "juliob", "juliob");
        playlistController.addVideoToPlaylist("Solo deportes", "Inauguración Estadio Peñarol", "juliob", "juliob");

        // Ratings

        videoController.rateVideo(10L, "sergiop", RatingEnum.DISLIKE);
        videoController.rateVideo(11L, "sergiop", RatingEnum.LIKE);
        videoController.rateVideo(14L, "sergiop", RatingEnum.LIKE);

        videoController.rateVideo(6L, "nicoJ", RatingEnum.DISLIKE);
        videoController.rateVideo(10L, "nicoJ", RatingEnum.LIKE);

        videoController.rateVideo(10L, "kairoh", RatingEnum.LIKE);
        videoController.rateVideo(17L, "kairoh", RatingEnum.LIKE);

        videoController.rateVideo(11L, "marcelot", RatingEnum.LIKE);
        videoController.rateVideo(6L, "marcelot", RatingEnum.LIKE);


        // Seguimientos
        userController.createFollowRelationship("hrubino", "hectorg");
        userController.createFollowRelationship("hrubino", "diegop");

        userController.createFollowRelationship("mbusca", "tabarec");
        userController.createFollowRelationship("mbusca", "cachilas");
        userController.createFollowRelationship("mbusca", "kairoh");

        userController.createFollowRelationship("hectorg", "mbusca");
        userController.createFollowRelationship("hectorg", "juliob");

        userController.createFollowRelationship("tabarec", "hrubino");
        userController.createFollowRelationship("tabarec", "cachilas");

        userController.createFollowRelationship("cachilas", "hrubino");

        userController.createFollowRelationship("juliob", "mbusca");
        userController.createFollowRelationship("juliob", "diegop");

        userController.createFollowRelationship("diegop", "hectorg");

        userController.createFollowRelationship("kairoh", "sergiop");

        userController.createFollowRelationship("robinh", "hectorg");
        userController.createFollowRelationship("robinh", "juliob");
        userController.createFollowRelationship("robinh", "diegop");

        userController.createFollowRelationship("marcelot", "cachilas");
        userController.createFollowRelationship("marcelot", "juliob");
        userController.createFollowRelationship("marcelot", "kairoh");

        userController.createFollowRelationship("novick", "hrubino");
        userController.createFollowRelationship("novick", "tabarec");
        userController.createFollowRelationship("novick", "cachilas");

        userController.createFollowRelationship("sergiop", "mbusca");
        userController.createFollowRelationship("sergiop", "juliob");
        userController.createFollowRelationship("sergiop", "diegop");

        userController.createFollowRelationship("chino", "tonyp");

        userController.createFollowRelationship("tonyp", "chino");

        userController.createFollowRelationship("nicoJ", "diegop");


        // Comentarios

        LocalDateTime date1 = LocalDate.of(2017, 12, 5).atTime(14, 35);
        videoController.commentVideo("nicoJ", "Fue un gran evento", date1, 10L);

        LocalDateTime date2 = LocalDate.of(2017, 12, 8).atTime(1, 47);
        videoController.commentComment("hrubino", "Para el próximo aniversario ofrezco vamo’ con los Momo",
                date2, 10L, 0L);

        LocalDateTime date3 = LocalDate.of(2017, 12, 10).atTime(17, 9);
        videoController.commentComment("tabarec", "Yo ofrezco a la banda tb",
                date3, 10L, 1L);

        LocalDateTime date4 = LocalDate.of(2017, 9, 7).atTime(4, 56);
        videoController.commentVideo("nicoJ", "Felicitaciones FING!!!", date4, 9L);

        LocalDateTime date5 = LocalDate.of(2017, 10, 23).atTime(12, 58);
        videoController.commentVideo("kairoh", "Un gusto cubrir eventos como este.", date5, 11L);

        LocalDateTime date6 = LocalDate.of(2016, 11, 14).atTime(5, 34);
        videoController.commentVideo("kairoh", "Peñarol peñarol!!!", date6, 17L);

        LocalDateTime date7 = LocalDate.of(2017, 10, 30).atTime(2, 17);
        videoController.commentVideo("marcelot", "Rock and Rolllll", date7, 5L);

        LocalDateTime date8 = LocalDate.of(2018, 8, 25).atTime(18, 0);
        videoController.commentVideo("marcelot", "Anoche explotó!!!", date8, 6L);
        
        LocalDateTime date9 = LocalDate.of(2018, 9, 11).atTime(3, 45);
        videoController.commentComment("marcelot", "Se viene la edición 2018!!!",
                date9, 11L, 4L);

        LocalDateTime date10 = LocalDate.of(2017, 9, 15).atTime(12, 29);
        videoController.commentVideo("tabarec", "Mi preferido por lejos!!", date10, 0L);
        
        
        // Historial
        userController.addNewVisit("hrubino", 9l); // HR - V6
        userController.addNewVisit("hrubino", 9l);
        
        userController.addNewVisit("hrubino", 14l);  // HR - V11
        userController.addNewVisit("hrubino", 14l);
        userController.addNewVisit("hrubino", 14l);
        userController.addNewVisit("hrubino", 14l);
        
        userController.addNewVisit("mbusca", 5l); // MB - V3
        userController.addNewVisit("mbusca", 5l);
        userController.addNewVisit("mbusca", 5l);
        userController.addNewVisit("mbusca", 5l);
        userController.addNewVisit("mbusca", 5l);
        
        userController.addNewVisit("mbusca", 8l); // MB - V5
        userController.addNewVisit("mbusca", 8l);
        userController.addNewVisit("mbusca", 8l);
        userController.addNewVisit("mbusca", 8l);
        
        userController.addNewVisit("mbusca", 9l); // MB - V6
        
        userController.addNewVisit("mbusca", 10l); // MB - V7
        
        userController.addNewVisit("mbusca", 11l); // MB - V8
        
        userController.addNewVisit("mbusca", 14l);  // MB - V11
        userController.addNewVisit("mbusca", 14l);
        userController.addNewVisit("mbusca", 14l);
        
        userController.addNewVisit("hectorg", 9l); // HG - V6
        
        userController.addNewVisit("hectorg", 10l); // HG - V7
        
        userController.addNewVisit("hectorg", 11l); // HG - V8
        
        userController.addNewVisit("hectorg", 14l);  // HG - V11
        userController.addNewVisit("hectorg", 14l);
        userController.addNewVisit("hectorg", 14l);
        userController.addNewVisit("hectorg", 14l);
        userController.addNewVisit("hectorg", 14l);
        userController.addNewVisit("hectorg", 14l);
        userController.addNewVisit("hectorg", 14l);
        userController.addNewVisit("hectorg", 14l);
        userController.addNewVisit("hectorg", 14l);
        userController.addNewVisit("hectorg", 14l);

        userController.addNewVisit("tabarec", 0l); // TC - V1
        
        userController.addNewVisit("tabarec", 2l); // TC - V2
        
        userController.addNewVisit("tabarec", 15l); // TC - V12 
        
        userController.addNewVisit("cachilas", 1l); // CS - V1
        
        userController.addNewVisit("cachilas", 3l); // CS - V2
        
        userController.addNewVisit("cachilas", 5l); // CS - V3
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        userController.addNewVisit("cachilas", 5l);
        
        userController.addNewVisit("cachilas", 12l); // CS - V9
        
        userController.addNewVisit("cachilas", 13l); // CS - V10
        userController.addNewVisit("cachilas", 13l);
        
        userController.addNewVisit("cachilas", 14l); // CS - V11
        userController.addNewVisit("cachilas", 14l);
        userController.addNewVisit("cachilas", 14l);
        userController.addNewVisit("cachilas", 14l);
        userController.addNewVisit("cachilas", 14l);
        
        userController.addNewVisit("juliob", 4l); // JB - V3
        
        userController.addNewVisit("juliob", 7l); // JB - V5
        
        userController.addNewVisit("juliob", 14l); // JB - V11
        userController.addNewVisit("juliob", 14l);
        
        userController.addNewVisit("juliob", 17l); // JB - V13
        userController.addNewVisit("juliob", 17l);
        
        userController.addNewVisit("diegop", 5l); // DP - V3
        userController.addNewVisit("diegop", 5l);
        
        userController.addNewVisit("diegop", 8l); // DP - V5
        
        userController.addNewVisit("kairoh", 5l); // KH - V3
        userController.addNewVisit("kairoh", 5l);
        userController.addNewVisit("kairoh", 5l);
        userController.addNewVisit("kairoh", 5l);
        userController.addNewVisit("kairoh", 5l);
        userController.addNewVisit("kairoh", 5l);
        userController.addNewVisit("kairoh", 5l);
        userController.addNewVisit("kairoh", 5l);
        
        userController.addNewVisit("kairoh", 6l); // KH - V4
        userController.addNewVisit("kairoh", 6l);
        userController.addNewVisit("kairoh", 6l);
        userController.addNewVisit("kairoh", 6l);
        userController.addNewVisit("kairoh", 6l);
        userController.addNewVisit("kairoh", 6l);
        
        userController.addNewVisit("kairoh", 8l); // KH - V5
        userController.addNewVisit("kairoh", 8l);
        
        userController.addNewVisit("kairoh", 13l); // KH - V10
        
        userController.addNewVisit("sergiop", 9l); // SP - V6
        userController.addNewVisit("sergiop", 9l);
        userController.addNewVisit("sergiop", 9l);
        userController.addNewVisit("sergiop", 9l);
        userController.addNewVisit("sergiop", 9l);
        
        userController.addNewVisit("sergiop", 10l); // SP - V7
        
        userController.addNewVisit("sergiop", 11l); // SP - V8
        
        userController.addNewVisit("sergiop", 14l); // SP - V11
        userController.addNewVisit("sergiop", 14l);
        
        userController.addNewVisit("chino", 10l); // AR - V7
        
        userController.addNewVisit("tonyp", 9l); // AP - V6
        
        userController.addNewVisit("nicoJ", 6l); // NJ - V4
        userController.addNewVisit("nicoJ", 6l);
        userController.addNewVisit("nicoJ", 6l);
        userController.addNewVisit("nicoJ", 6l);
        userController.addNewVisit("nicoJ", 6l);
        userController.addNewVisit("nicoJ", 6l);
        userController.addNewVisit("nicoJ", 6l);
        userController.addNewVisit("nicoJ", 6l);
        
        userController.addNewVisit("nicoJ", 10l); // NJ - V7   
        userController.addNewVisit("nicoJ", 10l);
        userController.addNewVisit("nicoJ", 10l);
        
        userController.addNewVisit("nicoJ", 11l); // NJ - V8
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        userController.addNewVisit("nicoJ", 11l);
        
        userController.addNewVisit("nicoJ", 20l); // NJ - V15 
        userController.addNewVisit("nicoJ", 20l);
        userController.addNewVisit("nicoJ", 20l);
        userController.addNewVisit("nicoJ", 20l);
        userController.addNewVisit("nicoJ", 20l);
        userController.addNewVisit("nicoJ", 20l);
        userController.addNewVisit("nicoJ", 20l);
        userController.addNewVisit("nicoJ", 20l);
        userController.addNewVisit("nicoJ", 20l);
        userController.addNewVisit("nicoJ", 20l);
        
        userController.addNewVisit("nicoJ", 21l); // NJ - V16
        userController.addNewVisit("nicoJ", 21l);
        userController.addNewVisit("nicoJ", 21l);
        userController.addNewVisit("nicoJ", 21l);
        
        loaded = true;
    }

    public boolean areLoaded() {
        return loaded;
    }

}
