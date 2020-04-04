package org.mlooser.learn.spring.worldgdp.controller.api;

import org.mlooser.learn.spring.worldgdp.dao.CityDAO;
import org.mlooser.learn.spring.worldgdp.model.City;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/cities")
public class CityApiController {

    private final CityDAO cityDao;

    public CityApiController(CityDAO cityDao) {
        this.cityDao = cityDao;
    }

    @GetMapping("/{countryCode}")
    public ResponseEntity<?> getCities(
            @PathVariable String countryCode,
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {

        return new ResponseEntity<>(
                cityDao.getCities(countryCode, pageNo),
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> addCity(
            @Valid @RequestBody City city) {

        cityDao.addCity(city);
        return new ResponseEntity<>(city, HttpStatus.CREATED);
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable Long cityId) {
        cityDao.deleteCity(cityId);
        return ResponseEntity.ok().build();
    }
}
