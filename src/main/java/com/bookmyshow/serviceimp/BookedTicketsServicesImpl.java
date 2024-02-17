package com.bookmyshow.serviceimp;

import com.bookmyshow.Repository.*;
import com.bookmyshow.dto.*;
import com.bookmyshow.entity.*;
import com.bookmyshow.exception.ResourceNotFoundException;
import com.bookmyshow.services.BookedSeatsService;
import com.bookmyshow.services.BookedTicketsService;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookedTicketsServicesImpl implements BookedTicketsService {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private MoviesServiceImpl moviesService;
    @Autowired
    private AtmServiceImpl atmService;

    @Autowired
    private BookedTicketsRepository bookedTicketsRepository;

    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    private AtmRepository atmRepository;
    @Autowired
    private BookedSeatsRepository bookedSeatsRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public BookedTicketsDto createBookingDetails(BookedTicketsDto bookedTicketsDto) {

        UserDto userDto = Optional.ofNullable(userService.getUserById(bookedTicketsDto.getUsers().getUserId())).orElseThrow(() -> new ResourceNotFoundException("Id is not valid"));


        MoviesDto moviesDto = Optional.ofNullable(moviesService.getMovieById(bookedTicketsDto.getMovies().getMovieId())).orElseThrow(() -> new ResourceNotFoundException("movie Not Found"));

        AtmDto atmDetails = Optional.ofNullable(atmService.getCardDetails(bookedTicketsDto.getAtm().getAtmId())).orElseThrow(() -> new ResourceNotFoundException("Atm Details Not Found"));


        BookedTickets bookedTickets = modelMapper.map(bookedTicketsDto, BookedTickets.class);
        Users users = modelMapper.map(userDto, Users.class);
        Movies movies = modelMapper.map(moviesDto, Movies.class);
        ATM atm = modelMapper.map(atmDetails, ATM.class);

        bookedTickets.setUsers(users);
        bookedTickets.setMovies(movies);
        bookedTickets.setAtm(atm);

        //Seat Booking Logic
        List<BookedSeatsDto> seats = bookedTicketsDto.getBookedSeats();
        List<BookedSeats> seatsList = seats.stream().map(bookedSeatsDto -> {
            BookedSeats seats1 = modelMapper.map(bookedSeatsDto, BookedSeats.class);
            seats1.setBookedTickets(bookedTickets);
            return seats1;
        }).collect(Collectors.toList());

        if (seatsList.size() > 4) {
            throw new IllegalArgumentException("You can only book up to 4 seats at a time.");
        }

        boolean seatAvailable = checkSeatsAvailability(bookedTickets.getTheaterName(), bookedTickets.getLocation(), bookedTickets.getTime(), seats, users);

        if (!seatAvailable) {
            throw new ResourceNotFoundException("These Seats are already Booked.");
        }

        int totalSeats = seatsList.size();
        BookedTickets save = bookedTicketsRepository.save(bookedTickets);
        List<BookedSeats> all = bookedSeatsRepository.saveAll(seatsList);
        bookedTickets.setBookedSeats(all);


        //FOOD AND FOOD PRICES
        String food1 = bookedTicketsDto.getFood();
        String[] split = food1.split("=");
        double foodPrice = Double.parseDouble(split[1]);
        bookedTickets.setFoodPrice(foodPrice);


        //TOTAL PRICE
        double totalPrice = foodPrice + ((totalSeats) * bookedTicketsDto.getTicketPrice());
        bookedTickets.setTotalPrice(totalPrice);
        bookedTickets.setTicketPrice(bookedTickets.getTicketPrice());

        return bookedTicketsDtoToBookedTickets(save);


    }


    public boolean checkSeatsAvailability(String theaterName, String location, String time, List<BookedSeatsDto> bookedSeats, Users users) {
        List<BookedTickets> byTheaterNameAndLocationAndTime =
                bookedTicketsRepository.findByTheaterNameAndLocationAndTime(theaterName, location, time);

        return byTheaterNameAndLocationAndTime == null || byTheaterNameAndLocationAndTime.isEmpty() || byTheaterNameAndLocationAndTime.
                stream().flatMap(checked -> checked.getBookedSeats().stream()).noneMatch(bookedSeats2 -> bookedSeats.stream().
                        anyMatch(bookedSeatsDto -> bookedSeats2.getSeatNumber().equals(bookedSeatsDto.getSeatNumber())));
    }


    @Override
    public List<BookedTicketsDto> getBookedTicketsByEmail(String email) {

        Users users = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("EmailId is not found"));

        List<BookedTickets> bookedTickets = users.getBookedTickets();
        List<BookedTicketsDto> collect = bookedTickets.stream().map(bookedTickets1 -> modelMapper.map(bookedTickets1, BookedTicketsDto.class)).collect(Collectors.toList());
        return collect;


    }


    private BookedTicketsDto bookedTicketsDtoToBookedTickets(BookedTickets bookedTickets) {
        BookedTicketsDto map = modelMapper.map(bookedTickets, BookedTicketsDto.class);
        return map;
    }

    private BookedTickets bookedTicketsDtoToBookedTickets(BookedTicketsDto bookedTicketsDto) {
        BookedTickets map = modelMapper.map(bookedTicketsDto, BookedTickets.class);
        return map;
    }

}
