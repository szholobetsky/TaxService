package com.example.tax.taxservice.service;

import com.example.tax.taxservice.model.Country;
import com.example.tax.taxservice.model.CountryTaxHistory;
import com.example.tax.taxservice.model.TaxType;
import com.example.tax.taxservice.repository.CountryTaxHistoryRepository;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.easymock.EasyMock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryTaxHistoryServiceTest extends EasyMockSupport {

    @Mock
    private CountryTaxHistoryRepository countryTaxHistoryRepository;

    @TestSubject
    private CountryTaxHistoryService target;

    @BeforeEach
    void setUp() {
        countryTaxHistoryRepository = EasyMock.createMock(CountryTaxHistoryRepository.class);
        target = new CountryTaxHistoryService(countryTaxHistoryRepository);
    }

    @Test
    void findTaxHistoryByCountryAndTaxType() {
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

        EasyMock.expect(countryTaxHistoryRepository.findAllByCountryAndTaxTypeOrderByActualBeforeDesc(usa, irl)).andReturn(list);
        EasyMock.replay(countryTaxHistoryRepository);
        List<CountryTaxHistory> resultList = target.findTaxHistoryByCountryAndTaxType(usa, irl);
        assertEquals(list, resultList);

    }

    @Test
    void findLastTaxFromHistoryByCountryAndTaxType() {
        Country usa = new Country();
        usa.setPkCountryId(1L);

        TaxType irl = new TaxType();
        irl.setPkTaxTypeId(1L);

        Date date2 = new Date(2L);
        Date date1 = new Date(1L);

        CountryTaxHistory item1 = new CountryTaxHistory();
        item1.setCountry(usa);
        item1.setTaxType(irl);
        item1.setActualBefore(date2);
        item1.setTaxRate(7D);

        CountryTaxHistory item2 = new CountryTaxHistory();
        item2.setCountry(usa);
        item2.setTaxType(irl);
        item2.setActualBefore(date1);
        item2.setTaxRate(8D);

        List<CountryTaxHistory> list = new ArrayList<>();
        list.add(item1);
        list.add(item2);


        EasyMock.expect(countryTaxHistoryRepository.findAllByCountryAndTaxTypeOrderByActualBeforeDesc(usa, irl)).andReturn(list);
        EasyMock.replay(countryTaxHistoryRepository);
        List<CountryTaxHistory> resultList = target.findTaxHistoryByCountryAndTaxType(usa, irl);
        assertEquals(list.stream().findFirst(), resultList.stream().findFirst());
    }

    @Test
    void findLastBeforeDateTaxFromHistoryByCountryAndTaxType_date2() {
        Country usa = new Country();
        usa.setPkCountryId(1L);

        TaxType irl = new TaxType();
        irl.setPkTaxTypeId(1L);

        Date date3 = new Date(3L);
        Date date2 = new Date(2L);
        Date date1 = new Date(1L);

        CountryTaxHistory item1 = new CountryTaxHistory();
        item1.setCountry(usa);
        item1.setTaxType(irl);
        item1.setActualBefore(date3);
        item1.setTaxRate(7D);

        CountryTaxHistory item2 = new CountryTaxHistory();
        item2.setCountry(usa);
        item2.setTaxType(irl);
        item2.setActualBefore(date1);
        item2.setTaxRate(8D);

        CountryTaxHistory itemCurr = new CountryTaxHistory();
        itemCurr.setCountry(usa);
        itemCurr.setTaxType(irl);
        itemCurr.setTaxRate(5D);

        List<CountryTaxHistory> list = new ArrayList<>();
        list.add(itemCurr);
        list.add(item2);
        list.add(item1);


        EasyMock.expect(countryTaxHistoryRepository.findAllByCountryAndTaxTypeOrderByActualBeforeAsc(usa, irl)).andReturn(list);
        EasyMock.replay(countryTaxHistoryRepository);
        CountryTaxHistory result = target.findLastBeforeDateTaxFromHistoryByCountryAndTaxType(usa, irl, date2);
        assertEquals(7D, result.getTaxRate());
    }

    @Test
    void findLastBeforeDateTaxFromHistoryByCountryAndTaxType_date4() {
        Country usa = new Country();
        usa.setPkCountryId(1L);

        TaxType irl = new TaxType();
        irl.setPkTaxTypeId(1L);

        Date date4 = new Date(4L);
        Date date3 = new Date(3L);
        Date date2 = new Date(2L);
        Date date1 = new Date(1L);

        CountryTaxHistory item1 = new CountryTaxHistory();
        item1.setCountry(usa);
        item1.setTaxType(irl);
        item1.setActualBefore(date3);
        item1.setTaxRate(7D);

        CountryTaxHistory item2 = new CountryTaxHistory();
        item2.setCountry(usa);
        item2.setTaxType(irl);
        item2.setActualBefore(date1);
        item2.setTaxRate(8D);

        CountryTaxHistory itemCurr = new CountryTaxHistory();
        itemCurr.setCountry(usa);
        itemCurr.setTaxType(irl);
        itemCurr.setTaxRate(5D);

        List<CountryTaxHistory> list = new ArrayList<>();
        list.add(itemCurr);
        list.add(item2);
        list.add(item1);

        EasyMock.expect(countryTaxHistoryRepository.findAllByCountryAndTaxTypeOrderByActualBeforeAsc(usa, irl)).andReturn(list);
        EasyMock.replay(countryTaxHistoryRepository);
        CountryTaxHistory result = target.findLastBeforeDateTaxFromHistoryByCountryAndTaxType(usa, irl, date4);
        assertEquals(5D, result.getTaxRate());
    }

    @Test
    void findLastBeforeDateTaxFromHistoryByCountryAndTaxType_date1() {
        Country usa = new Country();
        usa.setPkCountryId(1L);

        TaxType irl = new TaxType();
        irl.setPkTaxTypeId(1L);

        Date date4 = new Date(4L);
        Date date3 = new Date(3L);
        Date date2 = new Date(2L);
        Date date1 = new Date(1L);

        CountryTaxHistory item1 = new CountryTaxHistory();
        item1.setCountry(usa);
        item1.setTaxType(irl);
        item1.setActualBefore(date3);
        item1.setTaxRate(7D);

        CountryTaxHistory item2 = new CountryTaxHistory();
        item2.setCountry(usa);
        item2.setTaxType(irl);
        item2.setActualBefore(date2);
        item2.setTaxRate(8D);

        CountryTaxHistory itemCurr = new CountryTaxHistory();
        itemCurr.setCountry(usa);
        itemCurr.setTaxType(irl);
        itemCurr.setTaxRate(5D);

        List<CountryTaxHistory> list = new ArrayList<>();
        list.add(itemCurr);
        list.add(item2);
        list.add(item1);


        EasyMock.expect(countryTaxHistoryRepository.findAllByCountryAndTaxTypeOrderByActualBeforeAsc(usa, irl)).andReturn(list);
        EasyMock.replay(countryTaxHistoryRepository);
        CountryTaxHistory result = target.findLastBeforeDateTaxFromHistoryByCountryAndTaxType(usa, irl, date1);
        assertEquals(8D, result.getTaxRate());
    }
}