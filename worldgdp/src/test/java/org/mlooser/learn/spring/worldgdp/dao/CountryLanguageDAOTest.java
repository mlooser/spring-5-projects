package org.mlooser.learn.spring.worldgdp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mlooser.learn.spring.worldgdp.config.TestDBConfiguration;
import org.mlooser.learn.spring.worldgdp.model.CountryLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringJUnitConfig(classes = {
        TestDBConfiguration.class,
        CountryLanguageDAO.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class CountryLanguageDAOTest {

    @Autowired
    private CountryLanguageDAO countryLanguageDAO;

    @Test
    public void getLanguagesTest() {
        List<CountryLanguage> languages = countryLanguageDAO.getLanguages("AFG", 0);
        assertThat(languages)
                .hasSize(5);
    }

    @Test
    public void addLanguageTest() {
        CountryLanguage newCL = new CountryLanguage();
        newCL.setLanguage("xxx");
        newCL.setPercentage(0.1);
        newCL.setIsOfficial(true);
        newCL.setCountryCode("yyy");

        assertFalse(
                countryLanguageDAO.ifLanguageExists(newCL.getCountryCode(), newCL.getLanguage() ));

        countryLanguageDAO.addLanguage(newCL);

        List<CountryLanguage> cls = countryLanguageDAO.getLanguages(newCL.getCountryCode(),0);
        assertThat(cls)
                .hasSize(1);

        CountryLanguage addedCL = cls.get(0);

        assertThat(newCL)
                .isEqualTo(addedCL);

        assertTrue(
                countryLanguageDAO.ifLanguageExists(newCL.getCountryCode(), newCL.getLanguage() ));

        countryLanguageDAO.deleteLanguage(newCL.getCountryCode(), newCL.getLanguage());
        assertFalse(
                countryLanguageDAO.ifLanguageExists(newCL.getCountryCode(), newCL.getLanguage() ));
    }
}
