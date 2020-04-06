package org.mlooser.learn.spring.worldgdp.controller.api;

import javax.validation.Valid;

import org.mlooser.learn.spring.worldgdp.dao.CountryLanguageDAO;
import org.mlooser.learn.spring.worldgdp.model.CountryLanguage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/worldgdp/api/languages")
public class CountryLanguageApiController {

    private final CountryLanguageDAO cLanguageDao;

    public CountryLanguageApiController(CountryLanguageDAO cLanguageDao) {
        this.cLanguageDao = cLanguageDao;
    }

    @GetMapping("/{countryCode}")
    public ResponseEntity<?> getLanguages(
            @PathVariable String countryCode,
            @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {

        return ResponseEntity.ok(cLanguageDao.getLanguages(countryCode, pageNo));
    }

    @PostMapping("/")
    public ResponseEntity<?> addLanguage(
            @Valid @RequestBody CountryLanguage language) {

        if (cLanguageDao.ifLanguageExists(language.getCountryCode(), language.getLanguage())) {
            return ResponseEntity.badRequest()
                    .body("Language already exists for country");
        }
        cLanguageDao.addLanguage(language);
        return ResponseEntity.ok(language);
    }

    @DeleteMapping("/{countryCode}/language/{language}")
    public ResponseEntity<?> deleteLanguage(@PathVariable String countryCode,
                                            @PathVariable String language) {

        cLanguageDao.deleteLanguage(countryCode, language);
        return ResponseEntity.ok().build();
    }
}
