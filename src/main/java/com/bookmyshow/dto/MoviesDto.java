    package com.bookmyshow.dto;


    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public class MoviesDto {

        private Integer movieId;

        private String moviePoster;

        private String movieName;

        private String releaseDate;

        private String category;

        private String genre;

        private float rating;

        private String country;
    //
    //    private BookedTicketsDto bookedTickets;


    }
