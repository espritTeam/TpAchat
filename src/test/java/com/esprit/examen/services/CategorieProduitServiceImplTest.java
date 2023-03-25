package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;
@Slf4j
class CategorieProduitServiceImplTest {


    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }




    @Test
    void testAddCategorieProduit() {
        // create a new CategorieProduit object to add
        CategorieProduit cp = new CategorieProduit();
        cp.setIdCategorieProduit(1L);
        cp.setCodeCategorie("TEST");
        cp.setLibelleCategorie("Test Category");
        // set up mock repository to return the same object that was passed in
        when(categorieProduitRepository.save(cp)).thenReturn(cp);
        // call the service method
        CategorieProduit result = categorieProduitService.addCategorieProduit(cp);
        // verify that the service method returned the expected result
        log.info("categorie ajouter="+cp.getLibelleCategorie());
        assertEquals(cp, result);
        // verify that the repository method was called once
        verify(categorieProduitRepository, times(1)).save(cp);
    }


    @Test
    void testRetrieveAllCategorieProduits() {
        // create a list of CategorieProduits
        List<CategorieProduit> cpList = new ArrayList<CategorieProduit>();
        CategorieProduit cp1 = new CategorieProduit();
        cp1.setIdCategorieProduit(1L);
        cp1.setCodeCategorie("TEST1");
        cp1.setLibelleCategorie("Test Category 1");
        cpList.add(cp1);
        CategorieProduit cp2 = new CategorieProduit();
        cp2.setIdCategorieProduit(2L);
        cp2.setCodeCategorie("TEST2");
        cp2.setLibelleCategorie("Test Category 2");
        cpList.add(cp2);
        // set up mock repository to return the list of CategorieProduits
        when(categorieProduitRepository.findAll()).thenReturn(cpList);
        // call the service method
        List<CategorieProduit> result = categorieProduitService.retrieveAllCategorieProduits();
        // verify that the service method returned the expected result

        assertEquals(cpList.size(), result.size());
        assertTrue(result.contains(cp1));
        assertTrue(result.contains(cp2));
        // verify that the repository method was called once
        verify(categorieProduitRepository, times(1)).findAll();
    }
    @Test
    void testDeleteCategorieProduit() {
        // create an id for the CategorieProduit to delete
        Long id = 1L;
        // call the service method
        categorieProduitService.deleteCategorieProduit(id);
        // verify that the repository method was called once
        verify(categorieProduitRepository, times(1)).deleteById(id);
        log.info("test delete passed");
    }

}