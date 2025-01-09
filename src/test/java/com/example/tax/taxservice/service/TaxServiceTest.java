package com.example.tax.taxservice.service;

import com.example.tax.taxservice.model.Country;
import com.example.tax.taxservice.model.CountryTaxHistory;
import com.example.tax.taxservice.model.TaxType;
import org.easymock.EasyMock;
import org.easymock.*;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaxServiceTest extends EasyMockSupport {
    @Mock
    private CountryTaxHistoryService countryTaxHistoryService;
    @Mock
    private CountryService countryService;
    @Mock
    private TaxTypeService taxTypeService;
    @TestSubject
    TaxService target;

    @BeforeEach
    void setUp() {
        countryTaxHistoryService = EasyMock.createMock(CountryTaxHistoryService.class);
        countryService = EasyMock.createMock(CountryService.class);
        taxTypeService = EasyMock.createMock(TaxTypeService.class);
        target = new TaxService(countryTaxHistoryService, countryService, taxTypeService);
    }

    @Test
    void calculateAdjustedValue() {
        Country usa = new Country();
        usa.setPkCountryId(1L);

        TaxType irl = new TaxType();
        irl.setPkTaxTypeId(1L);

        CountryTaxHistory item = new CountryTaxHistory();
        item.setCountry(usa);
        item.setTaxType(irl);
        item.setTaxRate(6D);

        EasyMock.expect(countryService.findByCode("USA")).andReturn(usa);
        EasyMock.expect(taxTypeService.findByCode("IRL")).andReturn(irl);
        EasyMock.expect(countryTaxHistoryService.findLastTaxFromHistoryByCountryAndTaxType(usa, irl)).andReturn(item);
        EasyMock.replay(countryService);
        EasyMock.replay(taxTypeService);
        EasyMock.replay(countryTaxHistoryService);
        Double adjusted = target.calculateAdjustedValue("USA", "IRL",100D);
        Double expectedAdjused = 106D;
        assertEquals(adjusted, expectedAdjused);
        verifyAll();
    }

    @Test
    void calculateAdjustedValueToDate() {
        Country usa = new Country();
        usa.setPkCountryId(1L);

        TaxType irl = new TaxType();
        irl.setPkTaxTypeId(1L);

        Date date = new Date();

        CountryTaxHistory item = new CountryTaxHistory();
        item.setCountry(usa);
        item.setTaxType(irl);
        item.setTaxRate(7D);

        EasyMock.expect(countryService.findByCode("USA")).andReturn(usa);
        EasyMock.expect(taxTypeService.findByCode("IRL")).andReturn(irl);
        EasyMock.expect(countryTaxHistoryService.findLastBeforeDateTaxFromHistoryByCountryAndTaxType(usa, irl, date)).andReturn(item);
        EasyMock.replay(countryService);
        EasyMock.replay(taxTypeService);
        EasyMock.replay(countryTaxHistoryService);
        Double adjusted = target.calculateAdjustedValueToDate("USA", "IRL",date, 100D);
        Double expectedAdjused = 107D;
        assertEquals(adjusted, expectedAdjused);
        verifyAll();
    }

    @Test
    void getTaxList() {
        Country usa = new Country();
        usa.setPkCountryId(1L);

        TaxType irl = new TaxType();
        irl.setPkTaxTypeId(1L);

        Date date = new Date();

        CountryTaxHistory item = new CountryTaxHistory();
        item.setCountry(usa);
        item.setTaxType(irl);
        item.setTaxRate(7D);

        List<CountryTaxHistory> list = new ArrayList<>();
        list.add(item);

        EasyMock.expect(countryService.findByCode("USA")).andReturn(usa);
        EasyMock.expect(taxTypeService.findByCode("IRL")).andReturn(irl);
        EasyMock.expect(countryTaxHistoryService.findTaxHistoryByCountryAndTaxType(usa, irl)).andReturn(list);
        EasyMock.replay(countryService);
        EasyMock.replay(taxTypeService);
        EasyMock.replay(countryTaxHistoryService);
        List<CountryTaxHistory>  resultList = target.getTaxList("USA", "IRL");
        assertEquals(list, resultList);
        verifyAll();
    }
}