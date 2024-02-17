package com.bookmyshow.serviceimp;

import com.bookmyshow.Repository.TheaterRepository;
import com.bookmyshow.dto.TheaterDto;
import com.bookmyshow.entity.Theater;
import com.bookmyshow.services.TheaterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterServiceImpl implements TheaterService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TheaterRepository theaterRepository;

    @Override
    public TheaterDto addTheaterDetails(TheaterDto theaterDto){
        Theater theater = BookingDtoToBooking(theaterDto);
        Theater theater1 = theaterRepository.save(theater);
        return BookingToBookingDTo(theater1);

    }
    public TheaterDto BookingToBookingDTo(Theater theater){
        TheaterDto map = modelMapper.map(theater, TheaterDto.class);
        return  map;
    }
    public Theater BookingDtoToBooking(TheaterDto theaterDto){
        Theater mapped = modelMapper.map(theaterDto, Theater.class);
        return mapped;
    }
}
