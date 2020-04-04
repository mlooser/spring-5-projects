package org.mlooser.learn.spring.worldgdp.controller.view;

import org.mlooser.learn.spring.worldgdp.dao.CityDAO;
import org.mlooser.learn.spring.worldgdp.dao.CountryDAO;
import org.mlooser.learn.spring.worldgdp.dao.LookupDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/")
public class ViewController {
    private final CountryDAO countryDao;
    private final LookupDAO lookupDao;
    private final CityDAO cityDao;

    public ViewController(CountryDAO countryDao, LookupDAO lookupDao, CityDAO cityDao) {
        this.countryDao = countryDao;
        this.lookupDao = lookupDao;
        this.cityDao = cityDao;
    }

    @GetMapping({"/countries", "/"})
    public String getCountries(Model model, @RequestParam Map<String, Object> params) {
        model.addAttribute("continents", lookupDao.getContinents());
        model.addAttribute("regions", lookupDao.getRegions());
        model.addAttribute("countries", countryDao.getCountries(params));
        model.addAttribute("count", countryDao.getCountriesCount(params));

        return "countries";
    }

    @GetMapping("/countries/{code}")
    public String getCountryDetail(@PathVariable String code, Model model) {
        model.addAttribute("c", countryDao.getCountryDetail(code));
        return "country";
    }

    @GetMapping("/countries/{code}/form")
    public String editCountry(@PathVariable String code, Model model) {
        model.addAttribute("c", countryDao.getCountryDetail(code));
        model.addAttribute("cities", cityDao.getCities(code));
        model.addAttribute("continents", lookupDao.getContinents());
        model.addAttribute("regions", lookupDao.getRegions());

        return "country-form";
    }
}
