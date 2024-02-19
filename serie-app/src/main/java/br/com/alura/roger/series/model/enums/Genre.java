package br.com.alura.roger.series.model.enums;

public enum Genre {
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    BIOGRAPHY("Biography"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DOCUMENTARY("Documentary"),
    DRAMA("Drama"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    FILM_NOIR("Film-Noir"),
    GAME_SHOW("Game-Show"),
    HISTORY("History"),
    HORROR("Horror"),
    MUSIC("Music"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    NEWS("News"),
    REALITY_TV("Reality-TV"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science-Fiction"),
    SPORT("Sport"),
    TALK_SHOW("Talk-Show"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western");

    private String genreDescription;

    Genre(String genreDescription) {
        this.genreDescription = genreDescription;
    }

    public static Genre fromDescription(String genreDescription) {
        for (Genre genre : Genre.values()) {
            if (genre.genreDescription.equalsIgnoreCase(genreDescription)) {
                return genre;
            }
        }
        throw  new IllegalArgumentException("Genre not found: " + genreDescription);
    }
}
