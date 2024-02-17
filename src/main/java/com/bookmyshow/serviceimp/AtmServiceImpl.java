package com.bookmyshow.serviceimp;

import com.bookmyshow.Repository.AtmRepository;
import com.bookmyshow.dto.AtmDto;
import com.bookmyshow.dto.UserDto;
import com.bookmyshow.entity.ATM;
import com.bookmyshow.entity.Users;
import com.bookmyshow.exception.ResourceNotFoundException;
import com.bookmyshow.services.AtmService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtmServiceImpl implements AtmService {
    @Autowired
    private AtmRepository atmRepository;
    @Autowired
   private ModelMapper modelMapper;


    @Override
    public AtmDto atmCardDetails(AtmDto atmDto) {
        ATM atm = AtmDtoTOAtm(atmDto);
        ATM atm1 = atmRepository.save(atm);
        return AtmTOAtmDto(atm1);
    }

    public AtmDto AtmTOAtmDto(ATM atm){
        AtmDto map = modelMapper.map(atm, AtmDto.class);
        return map;
    }
    public ATM AtmDtoTOAtm(AtmDto atmDto){
        ATM map1 = modelMapper.map(atmDto, ATM.class);
        return map1;

    }

    @Override
    public AtmDto getCardDetails(Integer atmId) {
        ATM atm = atmRepository.findById(atmId).
                orElseThrow(() -> new ResourceNotFoundException("atmId Not Found"));

       return  modelMapper.map(atm, AtmDto.class);
    }
}
