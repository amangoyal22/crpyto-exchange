package com.finance.crpyto.components;

import com.finance.crpyto.dao.VendorDetailsDao;
import com.finance.crpyto.enums.RepoEnum;
import com.finance.crpyto.registries.ExchangeRegistry;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The type Populate component.
 */
@Component
@AllArgsConstructor
public class PopulateComponent {

  /**
   * The Vendor details dao.
   */
  private final VendorDetailsDao vendorDetailsDao;

  /**
   * Populate and update exchange list.
   */
  public void populateAndUpdateExchangeList() {

    final var listOfExchange = vendorDetailsDao.findAll()
        .stream()
        .filter(vendorDetails -> RepoEnum.ACTIVE.ordinal() == vendorDetails.getStatus())
        .map(vendorDetails -> ExchangeRegistry.get(vendorDetails.getId()).getExchangeDetails())
        .collect(Collectors.toList()).stream()
        .flatMap(List::stream)
        .collect(Collectors.toList());
  }
}
