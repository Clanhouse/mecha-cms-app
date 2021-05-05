package com.crm.service;

import com.crm.dto.response.CarResponse;
import org.springframework.data.domain.Page;

public interface CarService {

    Page<CarResponse> getCarsPaginated(final int page, final int size);
}