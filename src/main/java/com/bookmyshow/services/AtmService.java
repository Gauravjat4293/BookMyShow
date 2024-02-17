package com.bookmyshow.services;

import com.bookmyshow.dto.AtmDto;

public interface AtmService {
     AtmDto atmCardDetails(AtmDto atmDto);

     AtmDto getCardDetails(Integer atmId);
}
