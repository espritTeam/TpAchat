package com.esprit.examen.services;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ReglementServiceImplTest {

    @Autowired
    private IReglementService reglementService;

    @Autowired
    private ReglementRepository reglementRepository;

    @Autowired
    private FactureRepository factureRepository;

    @Test
    public void testRetrieveAllReglements() {
        List<Reglement> reglements = reglementService.retrieveAllReglements();
        Assertions.assertNotNull(reglements);
    }

    @Test
    public void testAddReglement() {
        Reglement r = new Reglement();
        r.setPayee(true);
        r.setMontantPaye(500);
        r.setMontantRestant(700);

        Reglement savedReglement = reglementService.addReglement(r);
        Assertions.assertNotNull(savedReglement);
        assertEquals(r.getMontantPaye(), savedReglement.getMontantPaye());
        assertEquals(r.getPayee(), savedReglement.getPayee());
    }

    @Test
    public void testRetrieveReglement() {
        Reglement r = new Reglement();
        r.setPayee(true);
        r.setMontantPaye(500);

        Reglement savedReglement = reglementRepository.save(r);

        Reglement retrievedReglement = reglementService.retrieveReglement(savedReglement.getIdReglement());
        Assertions.assertNotNull(retrievedReglement);
        assertEquals(savedReglement.getMontantPaye(), retrievedReglement.getMontantPaye());
        assertEquals(savedReglement.getPayee(), retrievedReglement.getPayee());
    }

    @Test
    public void testRetrieveReglementByFacture() {
        Facture f = new Facture();
        f.setIdFacture(1000L);
        f.setDateCreationFacture(new Date());

        Reglement r1 = new Reglement();
        r1.setMontantPaye(0);
        r1.setPayee(false);
        r1.setMontantRestant(500);

        Reglement r2 = new Reglement();
        r2.setMontantPaye(1000);
        r2.setPayee(true);
        r2.setMontantRestant(1000);

        List<Reglement> reglements = new ArrayList<>();
        reglements.add(r1);
        reglements.add(r2);
        f.setReglements((Set<Reglement>) reglements);

        Facture savedFacture = factureRepository.save(f);

        List<Reglement> retrievedReglements = reglementService.retrieveReglementByFacture(savedFacture.getIdFacture());
        Assertions.assertNotNull(retrievedReglements);
        assertEquals(2, retrievedReglements.size());
    }
    @Test
    public void testGetChiffreAffaireEntreDeuxDate() {
        // Set up mock behavior for repository method
        Date startDate = new Date();
        Date endDate = new Date();
        float expectedChiffreAffaire = 1000.0f;
        when(reglementRepository.getChiffreAffaireEntreDeuxDate(startDate, endDate)).thenReturn(expectedChiffreAffaire);

        // Call service method and assert result
        float actualChiffreAffaire = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
        assertEquals(expectedChiffreAffaire, actualChiffreAffaire, 0.01f);
    }
}